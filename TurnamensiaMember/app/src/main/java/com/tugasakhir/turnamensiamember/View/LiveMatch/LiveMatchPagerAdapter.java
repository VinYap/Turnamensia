package com.tugasakhir.turnamensiamember.View.LiveMatch;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by alvin on 7/10/2017.
 */

public class LiveMatchPagerAdapter extends FragmentStatePagerAdapter {
    private PickBanFragment mPickBanFragment;
    private PlayerStatFragment mPlayerStatFragment;

    public LiveMatchPagerAdapter(FragmentManager fm, PickBanFragment mPickBanFragment, PlayerStatFragment mPlayerStatFragment) {
        super(fm);

        this.mPickBanFragment = mPickBanFragment;
        this.mPlayerStatFragment = mPlayerStatFragment;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return mPickBanFragment;
        if (position == 1) return mPlayerStatFragment;
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) return "Pick & Ban";
        if (position == 1) return "Player Stats";
        return null;
    }
}
