package com.example.cineenigma2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cineenigma2.data.Critique;
import com.example.cineenigma2.data.CritiqueHelper;
import com.example.cineenigma2.data.CritiqueListAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

        changeLayout = (Button) findViewById(R.id.allCritiques);

        View.OnClickListener handler = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == changeLayout){
                    Intent intentSecondary = new Intent(MainActivity.this , SecondActivity.class);
                    MainActivity.this.startActivity(intentSecondary);
                }
            }
        };

        changeLayout.setOnClickListener(handler);

        String currentDate = new SimpleDateFormat("dd-MM-yyyy - HH:mm:ss", Locale.getDefault()).format(new Date());

        titreFilm = (EditText) findViewById(R.id.editTextFilm);
        dateHeureFilm = (EditText) findViewById(R.id.editDateHeure);
        dateHeureFilm.setText(currentDate);
        noteScenarioFilm = (EditText) findViewById(R.id.editNoteScenario);
        noteRealisationFilm = (EditText) findViewById(R.id.editNoteRealisation);
        noteMusiqueFilm = (EditText) findViewById(R.id.editNoteMusique);
        descriptionFilm = (EditText) findViewById(R.id.editDescription);

        CritiqueHelper dbHelper = new CritiqueHelper(this);

        mDb = dbHelper.getWritableDatabase();
    }

    public void onClickEnvoyerButton(View v){

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

        addCritique(titreFilm.getText().toString(), dateHeureFilm.getText().toString(), noteScenarioFilm.getText().toString(), noteRealisationFilm.getText().toString(), noteMusiqueFilm.getText().toString(), descriptionFilm.getText().toString());

        Toast.makeText(this, "La critique est envoyée !", Toast.LENGTH_SHORT).show();
    }

    public long addCritique(String titre, String dateHeureFilm, String noteScenario, String noteRealisation, String noteMusique, String description){
        ContentValues cv = new ContentValues();
        cv.put(Critique.CritiqueEntree.COLUMN_TITRE, titre.toString());
        cv.put(Critique.CritiqueEntree.COLUMN_DATEHEURE, dateHeureFilm.toString());
        cv.put(Critique.CritiqueEntree.COLUMN_NOTESCENARIO, noteScenario.toString());
        cv.put(Critique.CritiqueEntree.COLUMN_NOTEREAL, noteRealisation.toString());
        cv.put(Critique.CritiqueEntree.COLUMN_NOTEMUSIQUE, noteMusique.toString());
        cv.put(Critique.CritiqueEntree.COLUMN_DESCRIPTION, description.toString());
        return mDb.insert(Critique.CritiqueEntree.TABLE_NAME, null, cv);
    }

}