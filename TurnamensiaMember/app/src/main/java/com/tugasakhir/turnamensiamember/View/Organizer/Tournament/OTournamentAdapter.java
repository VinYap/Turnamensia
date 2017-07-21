package com.tugasakhir.turnamensiamember.View.Organizer.Tournament;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * Created by Asus on 14/06/2017.
 */

public class OTournamentAdapter extends RecyclerView.Adapter<OTournamentViewHolder> {
    private List<Tournament> mTournaments;

    public OTournamentAdapter(List<Tournament> tournaments) {
        this.mTournaments = tournaments;
    }

    @Override
    public OTournamentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_organizer_tournament, parent, false);
        return new OTournamentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OTournamentViewHolder holder, int position) {
        Tournament tournament = mTournaments.get(position);
        holder.bindHolder(tournament);
    }

    @Override
    public int getItemCount() {
        return mTournaments.size();
    }
}
