package com.tugasakhir.turnamensiamember.View.LiveMatch;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.API.ConnectionAPI;
import com.tugasakhir.turnamensiamember.Model.Basic.Dota2Items;
import com.tugasakhir.turnamensiamember.Model.Basic.Dota2LiveMatchPlayer;
import com.tugasakhir.turnamensiamember.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alvin on 7/10/2017.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private ImageView mHeroIV;
    private TextView mPlayerTV;
    private ImageView mItemIV1;
    private ImageView mItemIV2;
    private ImageView mItemIV3;
    private ImageView mItemIV4;
    private ImageView mItemIV5;
    private ImageView mItemIV6;

    private List<ImageView> mItems;

    public ItemViewHolder(View itemView) {
        super(itemView);

        mHeroIV = (ImageView) itemView.findViewById(R.id.items_hero);
        mPlayerTV = (TextView) itemView.findViewById(R.id.items_player_name);
        mItemIV1 = (ImageView) itemView.findViewById(R.id.items_name_1);
        mItemIV2 = (ImageView) itemView.findViewById(R.id.items_name_2);
        mItemIV3 = (ImageView) itemView.findViewById(R.id.items_name_3);
        mItemIV4 = (ImageView) itemView.findViewById(R.id.items_name_4);
        mItemIV5 = (ImageView) itemView.findViewById(R.id.items_name_5);
        mItemIV6 = (ImageView) itemView.findViewById(R.id.items_name_6);

        mItems = new ArrayList<>(7);
        mItems.add(null);
        mItems.add(mItemIV1);
        mItems.add(mItemIV2);
        mItems.add(mItemIV3);
        mItems.add(mItemIV4);
        mItems.add(mItemIV5);
        mItems.add(mItemIV6);
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
        for (Dota2Items item: player.getItems()) {
            if (mItems.get(item.getItem_order()) != null) {
                if (item.getPicture_file_name() != null) {
                    String image = ConnectionAPI.getBaseUrl() + "/img/dota-2/items/" + item.getPicture_file_name();
                    Picasso.with(itemView.getContext()).load(image).into(mItems.get(item.getItem_order()));
                }
            }
        }
    }
}
