package com.example.cineenigma2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cineenigma2.data.Critique;
import com.example.cineenigma2.data.CritiqueHelper;
import com.example.cineenigma2.data.CritiqueListAdapter;

public class SecondActivity extends AppCompatActivity {

    private CritiqueListAdapter mAdapter;

    private SQLiteDatabase mDb;
    public static final String TAG = "lesCritiques";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        RecyclerView critiqueListRecyclerView;

        critiqueListRecyclerView = (RecyclerView) this.findViewById(R.id.showAllCritiques);

        critiqueListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        CritiqueHelper dbHelper = new CritiqueHelper(this);

        mDb = dbHelper.getWritableDatabase();

        Cursor cursor = getAllCritiques();

        mAdapter = new CritiqueListAdapter(this, cursor);

        critiqueListRecyclerView.setAdapter(mAdapter);

    }


    private Cursor getAllCritiques() {

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
