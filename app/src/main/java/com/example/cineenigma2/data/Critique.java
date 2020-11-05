package com.example.cineenigma2.data;

import android.provider.BaseColumns;

public class Critique {

    public static final class CritiqueEntree implements BaseColumns{
        public static final String TABLE_NAME = "critique";
        public static final String COLUMN_TITRE = "titreFilm";
        public static final String COLUMN_DATEHEURE = "dateHeureFilm";
        public static final String COLUMN_NOTESCENARIO = "noteScenarioFilm";
        public static final String COLUMN_NOTEREAL = "noteRealisationFilm";
        public static final String COLUMN_NOTEMUSIQUE = "noteMusiqueFilm";
        public static final String COLUMN_DESCRIPTION = "descriptionFilm";
    }

}
