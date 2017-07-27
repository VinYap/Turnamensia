package com.tugasakhir.turnamensiamember.View.Tournament;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.Model.Basic.LiveMatch;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * Created by Asus on 02/07/2017.
 */

public class TournamentLiveMatchAdapter extends RecyclerView.Adapter<TournamentLiveMatchViewHolder> {
    private List<LiveMatch> mLiveMatches;

    public TournamentLiveMatchAdapter(List<LiveMatch> liveMatches) {
        this.mLiveMatches = liveMatches;
    }

    @Override
    public TournamentLiveMatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tournament_live_match, parent, false);
        return new TournamentLiveMatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TournamentLiveMatchViewHolder holder, int position) {
        LiveMatch liveMatch = mLiveMatches.get(position);
        holder.bindHolder(liveMatch);
    }

    @Override
    public int getItemCount() {
        return mLiveMatches.size();
    }
}
