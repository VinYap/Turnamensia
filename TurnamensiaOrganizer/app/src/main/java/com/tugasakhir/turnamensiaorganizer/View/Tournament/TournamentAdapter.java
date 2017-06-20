package com.tugasakhir.turnamensiaorganizer.View.Tournament;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiaorganizer.Model.Basic.Tournament;
import com.tugasakhir.turnamensiaorganizer.R;

import java.util.List;

/**
 * Created by Asus on 14/06/2017.
 */

public class TournamentAdapter extends RecyclerView.Adapter<TournamentViewHolder> {
    private List<Tournament> mTournaments;

    public TournamentAdapter(List<Tournament> tournaments) {
        this.mTournaments = tournaments;
    }

    @Override
    public TournamentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tournament, parent, false);
        return new TournamentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TournamentViewHolder holder, int position) {
        Tournament tournament = mTournaments.get(position);
        holder.bindHolder(tournament, position);
    }

    @Override
    public int getItemCount() {
        return mTournaments.size();
    }
}
