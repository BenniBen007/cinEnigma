package com.example.cineenigma2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CritiqueHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mycritiques.db";

    private static final int DATABASE_VERSION = 1;

    public CritiqueHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_WAITLIST_TABLE = "CREATE TABLE " + Critique.CritiqueEntree.TABLE_NAME + " (" +
                Critique.CritiqueEntree._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Critique.CritiqueEntree.COLUMN_TITRE + " TEXT NOT NULL, " +
                Critique.CritiqueEntree.COLUMN_DATEHEURE + " TEXT NOT NULL, " +
                Critique.CritiqueEntree.COLUMN_NOTESCENARIO + " TEXT NOT NULL," +
                Critique.CritiqueEntree.COLUMN_NOTEREAL + " TEXT NOT NULL," +
                Critique.CritiqueEntree.COLUMN_NOTEMUSIQUE + " TEXT NOT NULL," +
                Critique.CritiqueEntree.COLUMN_DESCRIPTION + " TEXT NOT NULL" +

                "); ";

        db.execSQL(SQL_CREATE_WAITLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "  + Critique.CritiqueEntree.TABLE_NAME);
        onCreate(db);
    }

}
