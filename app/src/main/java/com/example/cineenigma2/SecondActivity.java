package com.example.cineenigma2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cineenigma2.data.Critique;
import com.example.cineenigma2.data.CritiqueHelper;
import com.example.cineenigma2.data.CritiqueListAdapter;

public class SecondActivity extends AppCompatActivity {

    private CritiqueListAdapter mAdapter;

    private SQLiteDatabase mDb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Création du recyclerview
        RecyclerView critiqueListRecyclerView;

        //Liaison entre la variable et le layout
        critiqueListRecyclerView = (RecyclerView) this.findViewById(R.id.showAllCritiques);

        //Definition dans le ayaout LinerLayout
        critiqueListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Appel de la bdd
        CritiqueHelper dbHelper = new CritiqueHelper(this);

        //acces a la bdd
        mDb = dbHelper.getWritableDatabase();

        //Recuperation de toutes les critiques
        Cursor cursor = getAllCritiques();

        //Initialisation de l'adapter par rapport aux critiques recuperer
        mAdapter = new CritiqueListAdapter(this, cursor);

        //Importe les critiques dans le recyclerView
        critiqueListRecyclerView.setAdapter(mAdapter);
    }


    private Cursor getAllCritiques() {
        //recupération des critiques trié par titre de film
        Cursor theCursos = mDb.query(
                Critique.CritiqueEntree.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                Critique.CritiqueEntree.COLUMN_TITRE
        );
        return theCursos;
    }
}
