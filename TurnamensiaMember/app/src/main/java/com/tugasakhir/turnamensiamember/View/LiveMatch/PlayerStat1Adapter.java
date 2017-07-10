package com.tugasakhir.turnamensiamember.View.LiveMatch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.R;

/**
 * Created by alvin on 7/10/2017.
 */

public class PlayerStat1Adapter extends RecyclerView.Adapter<PlayerStat1ViewHolder> {
    @Override
    public PlayerStat1ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player_stat_1, parent, false);
        return new PlayerStat1ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayerStat1ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
