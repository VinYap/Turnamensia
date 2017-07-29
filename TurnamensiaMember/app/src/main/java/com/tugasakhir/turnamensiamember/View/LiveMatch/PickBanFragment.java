package com.tugasakhir.turnamensiamember.View.LiveMatch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.API.ConnectionAPI;
import com.tugasakhir.turnamensiamember.Model.Basic.Dota2Heroes;
import com.tugasakhir.turnamensiamember.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PickBanFragment extends Fragment {
    private TextView mRadiantPickBanHeaderTV;
    private ImageView mRadiantPickIV1;
    private ImageView mRadiantPickIV2;
    private ImageView mRadiantPickIV3;
    private ImageView mRadiantPickIV4;
    private ImageView mRadiantPickIV5;
    private ImageView mRadiantBanIV1;
    private ImageView mRadiantBanIV2;
    private ImageView mRadiantBanIV3;
    private ImageView mRadiantBanIV4;
    private ImageView mRadiantBanIV5;
    private TextView mDirePickBanHeaderTV;
    private ImageView mDirePickIV1;
    private ImageView mDirePickIV2;
    private ImageView mDirePickIV3;
    private ImageView mDirePickIV4;
    private ImageView mDirePickIV5;
    private ImageView mDireBanIV1;
    private ImageView mDireBanIV2;
    private ImageView mDireBanIV3;
    private ImageView mDireBanIV4;
    private ImageView mDireBanIV5;

    private List<ImageView> mRadiantPicks;
    private List<ImageView> mRadiantBans;
    private List<ImageView> mDirePicks;
    private List<ImageView> mDireBans;

    public PickBanFragment() {
        // Required empty public constructor
    }

    public static PickBanFragment newInstance() {
        return new PickBanFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pick_ban, container, false);

        mRadiantPickBanHeaderTV = (TextView) view.findViewById(R.id.radiant_pick_ban_header);
        mRadiantPickIV1 = (ImageView) view.findViewById(R.id.radiant_pick_1);
        mRadiantPickIV2 = (ImageView) view.findViewById(R.id.radiant_pick_2);
        mRadiantPickIV3 = (ImageView) view.findViewById(R.id.radiant_pick_3);
        mRadiantPickIV4 = (ImageView) view.findViewById(R.id.radiant_pick_4);
        mRadiantPickIV5 = (ImageView) view.findViewById(R.id.radiant_pick_5);
        mRadiantBanIV1 = (ImageView) view.findViewById(R.id.radiant_ban_1);
        mRadiantBanIV2 = (ImageView) view.findViewById(R.id.radiant_ban_2);
        mRadiantBanIV3 = (ImageView) view.findViewById(R.id.radiant_ban_3);
        mRadiantBanIV4 = (ImageView) view.findViewById(R.id.radiant_ban_4);
        mRadiantBanIV5 = (ImageView) view.findViewById(R.id.radiant_ban_5);
        mDirePickBanHeaderTV = (TextView) view.findViewById(R.id.dire_pick_ban_header);
        mDirePickIV1 = (ImageView) view.findViewById(R.id.dire_pick_1);
        mDirePickIV2 = (ImageView) view.findViewById(R.id.dire_pick_2);
        mDirePickIV3 = (ImageView) view.findViewById(R.id.dire_pick_3);
        mDirePickIV4 = (ImageView) view.findViewById(R.id.dire_pick_4);
        mDirePickIV5 = (ImageView) view.findViewById(R.id.dire_pick_5);
        mDireBanIV1 = (ImageView) view.findViewById(R.id.dire_ban_1);
        mDireBanIV2 = (ImageView) view.findViewById(R.id.dire_ban_2);
        mDireBanIV3 = (ImageView) view.findViewById(R.id.dire_ban_3);
        mDireBanIV4 = (ImageView) view.findViewById(R.id.dire_ban_4);
        mDireBanIV5 = (ImageView) view.findViewById(R.id.dire_ban_5);

        mRadiantPicks = new ArrayList<>(6);
        mRadiantPicks.add(null);
        mRadiantPicks.add(mRadiantPickIV1);
        mRadiantPicks.add(mRadiantPickIV2);
        mRadiantPicks.add(mRadiantPickIV3);
        mRadiantPicks.add(mRadiantPickIV4);
        mRadiantPicks.add(mRadiantPickIV5);

        mRadiantBans = new ArrayList<>(6);
        mRadiantBans.add(null);
        mRadiantBans.add(mRadiantBanIV1);
        mRadiantBans.add(mRadiantBanIV2);
        mRadiantBans.add(mRadiantBanIV3);
        mRadiantBans.add(mRadiantBanIV4);
        mRadiantBans.add(mRadiantBanIV5);

        mDirePicks = new ArrayList<>(6);
        mDirePicks.add(null);
        mDirePicks.add(mDirePickIV1);
        mDirePicks.add(mDirePickIV2);
        mDirePicks.add(mDirePickIV3);
        mDirePicks.add(mDirePickIV4);
        mDirePicks.add(mDirePickIV5);

        mDireBans = new ArrayList<>(6);
        mDireBans.add(null);
        mDireBans.add(mDireBanIV1);
        mDireBans.add(mDireBanIV2);
        mDireBans.add(mDireBanIV3);
        mDireBans.add(mDireBanIV4);
        mDireBans.add(mDireBanIV5);

        return view;
    }

    public void updateHeader(Integer team, String name) {
        if (team == 1) {
            mRadiantPickBanHeaderTV.setText(name + " Pick & Ban");
        } else if (team == 2) {
            mDirePickBanHeaderTV.setText(name + " Pick & Ban");
        }
    }

    public void update(Integer team, List<Dota2Heroes> picks, List<Dota2Heroes> bans) {
        for (Dota2Heroes pick: picks) {
            if (team == 1) {
                if (mRadiantPicks.get(pick.getPick_order()) != null) {
                    if (pick.getPicture_file_name() != null) {
                        String image = ConnectionAPI.getBaseUrl() + "/img/dota-2/heroes/" + pick.getPicture_file_name();
                        Picasso.with(getContext()).load(image).into(mRadiantPicks.get(pick.getPick_order()));
                        mRadiantPicks.set(pick.getPick_order(), null);
                    }
                }
            } else if (team == 2) {
                if (mDirePicks.get(pick.getPick_order()) != null) {
                    if (pick.getPicture_file_name() != null) {
                        String image = ConnectionAPI.getBaseUrl() + "/img/dota-2/heroes/" + pick.getPicture_file_name();
                        Picasso.with(getContext()).load(image).into(mDirePicks.get(pick.getPick_order()));
                        mDirePicks.set(pick.getPick_order(), null);
                    }
                }
            }
        }

        for (Dota2Heroes ban: bans) {
            if (team == 1) {
                if (mRadiantBans.get(ban.getBan_order()) != null) {
                    if (ban.getPicture_file_name() != null) {
                        String image = ConnectionAPI.getBaseUrl() + "/img/dota-2/heroes/" + ban.getPicture_file_name();
                        Picasso.with(getContext()).load(image).into(mRadiantBans.get(ban.getBan_order()));
                        mRadiantBans.set(ban.getBan_order(), null);
                    }
                }
            } else if (team == 2) {
                if (mDireBans.get(ban.getBan_order()) != null) {
                    if (ban.getPicture_file_name() != null) {
                        String image = ConnectionAPI.getBaseUrl() + "/img/dota-2/heroes/" + ban.getPicture_file_name();
                        Picasso.with(getContext()).load(image).into(mDireBans.get(ban.getBan_order()));
                        mDireBans.set(ban.getBan_order(), null);
                    }
                }
            }
        }
    }
}
