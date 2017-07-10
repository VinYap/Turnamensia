package com.tugasakhir.turnamensiamember.View.Tournament;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tugasakhir.turnamensiamember.View.LiveMatch.LiveMatchActivity;

/**
 * Created by Asus on 02/07/2017.
 */

public class TournamentLiveMatchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TournamentLiveMatchViewHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Context context = itemView.getContext();
        Intent intent = new Intent(context, LiveMatchActivity.class);
        context.startActivity(intent);
    }
}
