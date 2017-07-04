package com.tugasakhir.turnamensiamember.View.Schedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Asus on 02/07/2017.
 */

public class SchedulePagerAdapter extends FragmentStatePagerAdapter {

    public SchedulePagerAdapter(FragmentManager fm) {
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
        if (position == 0) return "Profile";
        else if (position == 1) return "Teams";
        return null;
    }
}
