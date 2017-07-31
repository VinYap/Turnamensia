package com.tugasakhir.turnamensiamember.View.Tournament;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.Basic.LiveMatch;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.LiveMatch.LiveMatchActivity;

/**
 * Created by Asus on 02/07/2017.
 */

public class TournamentLiveMatchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mIdTV;
    private TextView mRoundTV;
    private ImageView mRadiantPhotoIV;
    private TextView mRadiantTV;
    private TextView mRadiantScoreTV;
    private ImageView mDirePhotoIV;
    private TextView mDireTV;
    private TextView mDireScoreTV;
    private TextView mTimeTV;

    private Long id;

    public static final String LIVE_MATCH_KEY = "LIVE_MATCH_KEY";

    public TournamentLiveMatchViewHolder(View itemView) {
        super(itemView);

        mIdTV = (TextView) itemView.findViewById(R.id.match_id);
        mRoundTV = (TextView) itemView.findViewById(R.id.match_game);
        mRadiantPhotoIV = (ImageView) itemView.findViewById(R.id.radiant_image);
        mRadiantTV = (TextView) itemView.findViewById(R.id.radiant_text);
        mRadiantScoreTV = (TextView) itemView.findViewById(R.id.radiant_score);
        mDirePhotoIV = (ImageView) itemView.findViewById(R.id.dire_image);
        mDireTV = (TextView) itemView.findViewById(R.id.dire_text);
        mDireScoreTV = (TextView) itemView.findViewById(R.id.dire_score);
        mTimeTV = (TextView) itemView.findViewById(R.id.duration);

        itemView.setOnClickListener(this);
    }

    public void bindHolder(LiveMatch liveMatch) {
        int duration = liveMatch.getDuration();

        id = liveMatch.getId();
        mIdTV.setText("Match ID:".concat(id.toString()));
        mRoundTV.setText("Game " + liveMatch.getRound().toString() + "|" + liveMatch.getSeries());
        mRadiantTV.setText(liveMatch.getPlayer_1());
        mRadiantScoreTV.setText(liveMatch.getPlayer_1_score().toString());
        mDireTV.setText(liveMatch.getPlayer_2());
        mDireScoreTV.setText(liveMatch.getPlayer_2_score().toString());
        mTimeTV.setText(String.format("%02d", duration / 60) + ":" + String.format("%02d", duration % 60));
        Picasso.with(itemView.getContext()).load(liveMatch.getPlayer_1_image()).into(mRadiantPhotoIV);
        Picasso.with(itemView.getContext()).load(liveMatch.getPlayer_2_image()).into(mDirePhotoIV);
    }

    @Override
    public void onClick(View v) {
        Context context = itemView.getContext();
        Intent intent = new Intent(context, LiveMatchActivity.class);
        intent.putExtra(LIVE_MATCH_KEY, id);
        context.startActivity(intent);
    }
}
