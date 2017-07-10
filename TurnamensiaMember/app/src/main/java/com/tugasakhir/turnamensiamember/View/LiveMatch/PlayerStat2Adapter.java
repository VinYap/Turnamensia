package com.tugasakhir.turnamensiamember.View.LiveMatch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.R;

/**
 * Created by alvin on 7/10/2017.
 */

public class PlayerStat2Adapter extends RecyclerView.Adapter<PlayerStat2ViewHolder> {
    @Override
    public PlayerStat2ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player_stat_2, parent, false);
        return new PlayerStat2ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayerStat2ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
