package com.tugasakhir.turnamensiamember.View.Organizer.Match;

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

import com.tugasakhir.turnamensiamember.Model.Basic.Match;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.Organizer.Team.OQrCodeResultActivity;
import com.tugasakhir.turnamensiamember.View.Organizer.Team.OTeamActivity;

import static com.tugasakhir.turnamensiamember.View.Organizer.Tournament.OTournamentViewHolder.TOURNAMENT_KEY;

/**
 * Created by Asus on 18/06/2017.
 */

public class OMatchViewHolder extends RecyclerView.ViewHolder {
    private CardView mMatchCV;
    private TextView mMatchRoundTV;
    private TextView mMatchDateTV;
    private TextView mRadiantTV;
    private TextView mDireTV;
    private ImageView mRadiantIV;
    private ImageView mDireIV;
    private ImageView mQrCodeIV;
    private ClickableSpan mClickableSpan;
    private Context mContext;

    private Long id;

    public OMatchViewHolder(View view) {
        super(view);

        mMatchCV = (CardView) itemView.findViewById(R.id.match_cardview);
        mMatchRoundTV = (TextView) itemView.findViewById(R.id.match_round);
        mMatchDateTV = (TextView) itemView.findViewById(R.id.match_date);
        mRadiantTV = (TextView) itemView.findViewById(R.id.match_radiant);
        mDireTV = (TextView) itemView.findViewById(R.id.match_dire);
        mRadiantIV = (ImageView) itemView.findViewById(R.id.match_radiant_image);
        mDireIV = (ImageView) itemView.findViewById(R.id.match_dire_image);
        mQrCodeIV = (ImageView) itemView.findViewById(R.id.match_qr_code);

        mContext = itemView.getContext();

        mRadiantTV.setTag("radiant");
        mDireTV.setTag("dire");
        mRadiantTV.setMovementMethod(LinkMovementMethod.getInstance());
        mDireTV.setMovementMethod(LinkMovementMethod.getInstance());
        mClickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(mContext, OTeamActivity.class);
                intent.putExtra(TOURNAMENT_KEY, (String) widget.getTag());
                mContext.startActivity(intent);
            }
        };

        mQrCodeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OQrCodeResultActivity.class);
                intent.putExtra(TOURNAMENT_KEY, "tes");
                mContext.startActivity(intent);
            }
        });
    }

    public void bindHolder(Match match) {
//        mMatchRoundTV.setText(match.getMatchRound());
//        mMatchDateTV.setText(match.getMatchDate());
//        mRadiantTV.setText(match.getRadiant());
//        mDireTV.setText(match.getDire());
//        mRadiantIV.setImageResource(match.getRadiantPhotoId());
//        mDireIV.setImageResource(match.getDirePhotoId());
//        mQrCodeIV.setImageResource(match.getQrCodeId());

        SpannableString radiantSpanString = new SpannableString(mRadiantTV.getText());
        radiantSpanString.setSpan(mClickableSpan, 0, radiantSpanString.length(), 0);
        mRadiantTV.setText(radiantSpanString);

        SpannableString direSpanString = new SpannableString(mDireTV.getText());
        direSpanString.setSpan(mClickableSpan, 0, direSpanString.length(), 0);
        mDireTV.setText(direSpanString);
    }
}
