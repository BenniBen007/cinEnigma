package com.example.cineenigma2.data;

import android.content.Context;

import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cineenigma2.R;

public class CritiqueListAdapter extends RecyclerView.Adapter<CritiqueListAdapter.CritiqueViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    public CritiqueListAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    @Override
    public CritiqueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.critique_list_item, parent, false);
        return new CritiqueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CritiqueViewHolder holder, int position) {

        if (!mCursor.moveToPosition(position)) {
            return;
        }

        //Récupération de tous les champs du tuple
        String titreFilmGet = mCursor.getString(mCursor.getColumnIndex(Critique.CritiqueEntree.COLUMN_TITRE));
        String dateHeureGet = mCursor.getString(mCursor.getColumnIndex(Critique.CritiqueEntree.COLUMN_DATEHEURE));
        String noteScenarioGet = mCursor.getString(mCursor.getColumnIndex(Critique.CritiqueEntree.COLUMN_NOTESCENARIO));
        String noteRealisationGet = mCursor.getString(mCursor.getColumnIndex(Critique.CritiqueEntree.COLUMN_NOTEREAL));
        String noteMusiqueGet = mCursor.getString(mCursor.getColumnIndex(Critique.CritiqueEntree.COLUMN_NOTEMUSIQUE));
        String descriptionGet = mCursor.getString(mCursor.getColumnIndex(Critique.CritiqueEntree.COLUMN_DESCRIPTION));

        //Recuperation de l'id du tuple
        long id = mCursor.getLong(mCursor.getColumnIndex(Critique.CritiqueEntree._ID));

        //Affichage dans le RecyclerView
        holder.titreFilm.setText(titreFilmGet);
        holder.dateHeureFilm.setText(dateHeureGet);
        holder.noteScenario.setText(noteScenarioGet);
        holder.noteRealisation.setText(noteRealisationGet);
        holder.noteMusique.setText(noteMusiqueGet);
        holder.description.setText(descriptionGet);

        //Défintion du tag
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    class CritiqueViewHolder extends RecyclerView.ViewHolder {

        //Création des textview qui vont contenir les varaibles
        TextView titreFilm;
        TextView dateHeureFilm;
        TextView noteScenario;
        TextView noteRealisation;
        TextView noteMusique;
        TextView description;

        public CritiqueViewHolder(View itemView) {
            super(itemView);
            //Liaison entre les variables et le layout
            titreFilm = (TextView) itemView.findViewById(R.id.titreFilmShow);
            dateHeureFilm = (TextView) itemView.findViewById(R.id.dateHeureShow);
            noteScenario = (TextView) itemView.findViewById(R.id.noteScenarioShow);
            noteRealisation = (TextView) itemView.findViewById(R.id.noteRealisationShow);
            noteMusique = (TextView) itemView.findViewById(R.id.noteMusiqueShow);
            description = (TextView) itemView.findViewById(R.id.descriptionShow);
        }

    }
}
