package com.example.cineenigma2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cineenigma2.data.Critique;
import com.example.cineenigma2.data.CritiqueHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase mDb;

    private EditText titreFilm;
    private EditText dateHeureFilm;
    private EditText noteScenarioFilm;
    private EditText noteRealisationFilm;
    private EditText noteMusiqueFilm;
    private EditText descriptionFilm;

    private Button changeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Liaison entre la variable et le layout
        changeLayout = (Button) findViewById(R.id.allCritiques);

        View.OnClickListener handler = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Si je clique sur le bouton "voir les critiques"
                if(v == changeLayout){
                    //Je change de vue
                    Intent intentSecondary = new Intent(MainActivity.this , SecondActivity.class);
                    MainActivity.this.startActivity(intentSecondary);
                }
            }
        };

        //Je mets sur le button le listener
        changeLayout.setOnClickListener(handler);

        //Recuperation de la date actuel avec formattage
        String currentDate = new SimpleDateFormat("dd-MM-yyyy - HH:mm:ss", Locale.getDefault()).format(new Date());

        //Liaison entre les inputs et le layout
        titreFilm = (EditText) findViewById(R.id.editTextFilm);
        dateHeureFilm = (EditText) findViewById(R.id.editDateHeure);
        dateHeureFilm.setText(currentDate);
        noteScenarioFilm = (EditText) findViewById(R.id.editNoteScenario);
        noteRealisationFilm = (EditText) findViewById(R.id.editNoteRealisation);
        noteMusiqueFilm = (EditText) findViewById(R.id.editNoteMusique);
        descriptionFilm = (EditText) findViewById(R.id.editDescription);

        //Appel du helper
        CritiqueHelper dbHelper = new CritiqueHelper(this);

        //Acces a la bdd
        mDb = dbHelper.getWritableDatabase();
    }

    public void onClickEnvoyerButton(View v){
        //Si les champs sont vides jenvoie un message Toast pour informer l'utilisateur
        if(titreFilm.getText().length() == 0){
            Toast.makeText(this, "Le titre du film n'est pas renseigné.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(dateHeureFilm.getText().length() == 0){
            Toast.makeText(this, "La date et heure n'est pas renseignée.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(noteScenarioFilm.getText().length() == 0){
            Toast.makeText(this, "La note de scénario n'est pas renseignée.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(noteRealisationFilm.getText().length() == 0){
            Toast.makeText(this, "La note de réalisation n'est pas renseignée.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(noteMusiqueFilm.getText().length() == 0){
            Toast.makeText(this, "La note de musique n'est pas renseignée.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(descriptionFilm.getText().length() == 0){
            Toast.makeText(this, "La description n'est pas renseignée.", Toast.LENGTH_SHORT).show();
            return;
        }

        //J'ajoute la critique dans la bdd
        addCritique(titreFilm.getText().toString(), dateHeureFilm.getText().toString(), noteScenarioFilm.getText().toString(), noteRealisationFilm.getText().toString(), noteMusiqueFilm.getText().toString(), descriptionFilm.getText().toString());

        //J'informe l'utilisateur
        Toast.makeText(this, "La critique est envoyée !", Toast.LENGTH_SHORT).show();

        //Envoi par mail selon l'application de l'utilisateur
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"contact@swapper.ovh"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Sujet du mail");
        i.putExtra(Intent.EXTRA_TEXT   , "Voici la critique : "+titreFilm.getText().toString()+
                "\n Date et heure de projection : "+ dateHeureFilm.getText().toString() +
                "\n- Note du scénario : "+noteScenarioFilm.getText().toString()+
                "\n- Note de la realisation : "+noteRealisationFilm.getText().toString()+
                "\n- Note de la musique : "+noteMusiqueFilm.getText().toString()+
                "\n Description : "+descriptionFilm.getText().toString()
        );
        try {
            startActivity(Intent.createChooser(i, "Envoie du mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Pas d'application de mail sur l'appareil.", Toast.LENGTH_SHORT).show();
        }
    }

    public long addCritique(String titre, String dateHeureFilm, String noteScenario, String noteRealisation, String noteMusique, String description){
        //Creation d'une variable qui va contenir mon tuple
        ContentValues cv = new ContentValues();
        //Définition des colonnes par rapport aux input text
        cv.put(Critique.CritiqueEntree.COLUMN_TITRE, titre.toString());
        cv.put(Critique.CritiqueEntree.COLUMN_DATEHEURE, dateHeureFilm.toString());
        cv.put(Critique.CritiqueEntree.COLUMN_NOTESCENARIO, noteScenario.toString());
        cv.put(Critique.CritiqueEntree.COLUMN_NOTEREAL, noteRealisation.toString());
        cv.put(Critique.CritiqueEntree.COLUMN_NOTEMUSIQUE, noteMusique.toString());
        cv.put(Critique.CritiqueEntree.COLUMN_DESCRIPTION, description.toString());
        //Insertion dans la base
        return mDb.insert(Critique.CritiqueEntree.TABLE_NAME, null, cv);
    }

}