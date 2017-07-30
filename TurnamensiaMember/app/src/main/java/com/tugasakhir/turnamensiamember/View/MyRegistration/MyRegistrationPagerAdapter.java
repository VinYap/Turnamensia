package com.tugasakhir.turnamensiamember.View.MyRegistration;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tugasakhir.turnamensiamember.Model.Basic.MyRegistration;

import java.util.List;

/**
 * Created by Asus on 29/07/2017.
 */

public class MyRegistrationPagerAdapter extends FragmentStatePagerAdapter{
    private List<MyRegistration> myRegistrations;

    public MyRegistrationPagerAdapter(FragmentManager fm, List<MyRegistration> myRegistrations) {
        super(fm);
        this.myRegistrations = myRegistrations;
    }

    @Override
    public Fragment getItem(int position) {
        if (myRegistrations != null) {
            return MyRegistrationFragment.newInstance(myRegistrations.get(position).getTournaments_registrations());
        }
        return null;
    }

    @Override
    public int getCount() {
        if (myRegistrations != null) return myRegistrations.size();
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (myRegistrations != null) return myRegistrations.get(position).getTeam_name();
        return null;
    }
}
