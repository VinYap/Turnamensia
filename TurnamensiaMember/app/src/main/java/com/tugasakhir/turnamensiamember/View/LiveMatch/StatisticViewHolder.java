package com.tugasakhir.turnamensiamember.View.LiveMatch;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.API.ConnectionAPI;
import com.tugasakhir.turnamensiamember.Model.Basic.Dota2LiveMatchPlayer;
import com.tugasakhir.turnamensiamember.R;

/**
 * Created by alvin on 7/10/2017.
 */

public class StatisticViewHolder extends RecyclerView.ViewHolder {
    private ImageView mHeroIV;
    private TextView mPlayerTV;
    private TextView mKDATV;
    private TextView mLHDNTV;
    private TextView mNetTV;

    public StatisticViewHolder(View itemView) {
        super(itemView);

        mHeroIV = (ImageView) itemView.findViewById(R.id.statistic_hero);
        mPlayerTV = (TextView) itemView.findViewById(R.id.statistic_player_name);
        mKDATV = (TextView) itemView.findViewById(R.id.statistic_kda);
        mLHDNTV = (TextView) itemView.findViewById(R.id.statistic_lhdn);
        mNetTV = (TextView) itemView.findViewById(R.id.statistic_net);
    }

    public void bindHolder(Dota2LiveMatchPlayer player) {
        if (player.getHero() != null) {
            if (player.getHero().getPicture_file_name() != null) {
                String image = ConnectionAPI.getBaseUrl() + "/img/dota-2/heroes/" + player.getHero().getPicture_file_name();
                Picasso.with(itemView.getContext()).load(image).into(mHeroIV);
            }
        }
        if (player.getMember() != null) {
            mPlayerTV.setText(player.getMember().getName());
        } else {
            mPlayerTV.setText(player.getName());
        }
        mKDATV.setText(player.getKills() + "/" + player.getDeath() + "/" + player.getAssists());
        mLHDNTV.setText(player.getLast_hits() + "/" + player.getDenies());
        if (player.getNet_worth() >= 1000) {
            mNetTV.setText(String.valueOf(Math.round(player.getNet_worth() / 100) / 10.0) + "k");
        } else {
            mNetTV.setText("" + player.getNet_worth());
        }
    }
}
