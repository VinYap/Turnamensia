package com.tugasakhir.turnamensiamember.View.LiveMatch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.Model.Basic.Dota2LiveMatchPlayer;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * Created by alvin on 7/10/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private List<Dota2LiveMatchPlayer> mPlayers;

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_items, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Dota2LiveMatchPlayer mPlayer = mPlayers.get(position);
        holder.bindHolder(mPlayer);
    }

    @Override
    public int getItemCount() {
        if (mPlayers != null) {
            return mPlayers.size();
        } else {
            return 0;
        }
    }

    public void setmPlayers(List<Dota2LiveMatchPlayer> mPlayers) {
        this.mPlayers = mPlayers;
    }
}
