package com.tugasakhir.turnamensiamember.View.Account;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Asus on 26/06/2017.
 */

public class AccountPagerAdapter extends FragmentStatePagerAdapter {

    public AccountPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return AccountProfileFragment.newInstance();
        else if (position == 1) return AccountTeamFragment.newInstance();
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) return "Profile";
        else if (position == 1) return "Teams";
        return null;
    }
}
