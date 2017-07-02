package com.tugasakhir.turnamensiamember.View.Tournament;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Asus on 02/07/2017.
 */

public class TournamentPagerAdapter extends FragmentStatePagerAdapter {

    public TournamentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return TournamentDetailFragment.newInstance();
        else if (position == 1) return TournamentPriceFragment.newInstance();
        else if (position == 2) return TournamentBracketFragment.newInstance();
        else if (position == 3) return TournamentLiveMatchFragment.newInstance();
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) return "Details";
        else if (position == 1) return "Prices";
        else if (position == 2) return "Brackets";
        else if (position == 3) return "Live Match";
        return null;
    }
}
