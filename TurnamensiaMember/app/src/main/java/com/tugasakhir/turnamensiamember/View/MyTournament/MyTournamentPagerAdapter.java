package com.tugasakhir.turnamensiamember.View.MyTournament;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tugasakhir.turnamensiamember.Model.Response.MyTournamentResponse;

/**
 * Created by Asus on 29/07/2017.
 */

public class MyTournamentPagerAdapter extends FragmentStatePagerAdapter {
    private MyTournamentResponse myTournamentResponse;

    public MyTournamentPagerAdapter(FragmentManager fm, MyTournamentResponse myTournamentResponse) {
        super(fm);
        this.myTournamentResponse = myTournamentResponse;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return MyTournamentFragment.newInstance(myTournamentResponse.getIn_progress_tournaments());
        if (position == 1) return MyTournamentFragment.newInstance(myTournamentResponse.getCompleted_tournaments());
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) return "In Progress";
        else if (position == 1) return "Completed";
        return null;
    }
}
