package com.tugasakhir.turnamensiamember.View.Team;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Asus on 02/07/2017.
 */

public class TeamPagerAdapter extends FragmentStatePagerAdapter {

    public TeamPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return TeamProfileFragment.newInstance();
        else if (position == 1) return TeamMemberFragment.newInstance();
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) return "Team";
        else if (position == 1) return "Member";
        return null;
    }
}
