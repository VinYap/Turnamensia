package com.tugasakhir.turnamensiamember.View.MyTournament;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Asus on 29/07/2017.
 */

public class MyTournamentPagerAdapter extends FragmentStatePagerAdapter {

    public MyTournamentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) return "In Progress";
        else if (position == 1) return "Completed";
        return null;
    }
}
