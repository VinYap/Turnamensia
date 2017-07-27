package com.tugasakhir.turnamensiamember.View.Main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * Created by Andrianto on 7/22/2017.
 */

public class MainOrganizerAdapter extends RecyclerView.Adapter<MainOrganizerViewHolder> {
    private List<Tournament> mTournaments;

    public MainOrganizerAdapter(List<Tournament> tournaments) {
        this.mTournaments = tournaments;
    }

    @Override
    public MainOrganizerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_tournament, parent, false);
        return new MainOrganizerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainOrganizerViewHolder holder, int position) {
        Tournament tournament = mTournaments.get(position);
        holder.bindHolder(tournament);
    }

    @Override
    public int getItemCount() {
        if (mTournaments != null) return mTournaments.size();
        return 0;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.mTournaments = tournaments;
    }
}
