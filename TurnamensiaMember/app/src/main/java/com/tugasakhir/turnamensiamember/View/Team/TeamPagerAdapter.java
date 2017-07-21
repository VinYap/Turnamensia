package com.tugasakhir.turnamensiamember.View.Team;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tugasakhir.turnamensiamember.Model.Response.TeamResponse;

/**
 * Created by Asus on 02/07/2017.
 */

public class TeamPagerAdapter extends FragmentStatePagerAdapter {
    TeamResponse mResponse;

    public TeamPagerAdapter(FragmentManager fm, TeamResponse response) {
        super(fm);
        this.mResponse = response;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return TeamProfileFragment.newInstance(mResponse.getTeam(), mResponse.getIs_leader());
        else if (position == 1) return TeamMemberFragment.newInstance(mResponse.getTeams_details());
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
