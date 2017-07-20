package com.tugasakhir.turnamensiamember.View.Main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * Created by Asus on 26/06/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {
    private List<Tournament> mTournaments;

    public MainAdapter(List<Tournament> tournaments) {
        this.mTournaments = tournaments;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_tournament, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        Tournament tournament = mTournaments.get(position);
        holder.bindHolder(tournament, position);
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
