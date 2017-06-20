package com.tugasakhir.turnamensiaorganizer.View.Match;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tugasakhir.turnamensiaorganizer.Model.Basic.Match;
import com.tugasakhir.turnamensiaorganizer.R;
import com.tugasakhir.turnamensiaorganizer.View.Authentication.AuthActivity;

import static com.tugasakhir.turnamensiaorganizer.View.Tournament.TournamentViewHolder.TOURNAMENT_KEY;

/**
 * Created by Asus on 18/06/2017.
 */

public class MatchViewHolder extends RecyclerView.ViewHolder {
    private CardView mMatchCV;
    private TextView mMatchRoundTV;
    private TextView mMatchDateTV;
    private TextView mRadiantTV;
    private TextView mDireTV;
    private ImageView mRadiantIV;
    private ImageView mDireIV;
    private ImageView mQrCodeIV;

    private int id;

    public MatchViewHolder(View view) {
        super(view);

        mMatchCV = (CardView) itemView.findViewById(R.id.match_cardview);
        mMatchRoundTV = (TextView) itemView.findViewById(R.id.match_round);
        mMatchDateTV = (TextView) itemView.findViewById(R.id.match_date);
        mRadiantTV = (TextView) itemView.findViewById(R.id.match_radiant);
        mDireTV = (TextView) itemView.findViewById(R.id.match_dire);
        mRadiantIV = (ImageView) itemView.findViewById(R.id.match_radiant_image);
        mDireIV = (ImageView) itemView.findViewById(R.id.match_dire_image);
        mQrCodeIV = (ImageView) itemView.findViewById(R.id.match_qr_code);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Context context = itemView.getContext();
                Intent intent = new Intent(context, AuthActivity.class);
                intent.putExtra(TOURNAMENT_KEY, id);
                context.startActivity(intent);
            }
        };

        SpannableString radiantSpanString = new SpannableString(mRadiantTV.getText());
        radiantSpanString.setSpan(clickableSpan, 0, radiantSpanString.length(), 0);
        mRadiantTV.setText(radiantSpanString);
        mRadiantTV.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void bindHolder(Match match, int position) {
        id = position;

        mMatchRoundTV.setText(match.getMatchRound());
        mMatchDateTV.setText(match.getMatchDate());
        mRadiantTV.setText(match.getRadiant());
        mDireTV.setText(match.getDire());
        mRadiantIV.setImageResource(match.getRadiantPhotoId());
        mDireIV.setImageResource(match.getDirePhotoId());
        mQrCodeIV.setImageResource(match.getQrCodeId());
    }
}
