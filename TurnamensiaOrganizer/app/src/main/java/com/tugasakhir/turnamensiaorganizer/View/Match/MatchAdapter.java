package com.tugasakhir.turnamensiaorganizer.View.Match;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiaorganizer.Model.Basic.Match;
import com.tugasakhir.turnamensiaorganizer.R;

import java.util.List;

/**
 * Created by Asus on 18/06/2017.
 */

public class MatchAdapter extends RecyclerView.Adapter<MatchViewHolder> {
    private List<Match> mMatchs;

    public MatchAdapter(List<Match> match) {
        this.mMatchs = match;
    }

    @Override
    public MatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match, parent, false);
        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MatchViewHolder holder, int position) {
        Match match = mMatchs.get(position);
        holder.bindHolder(match, position);
    }

    @Override
    public int getItemCount() {
        return mMatchs.size();
    }
}
