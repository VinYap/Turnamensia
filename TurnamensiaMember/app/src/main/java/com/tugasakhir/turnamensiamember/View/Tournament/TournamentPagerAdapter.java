package com.tugasakhir.turnamensiamember.View.Tournament;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tugasakhir.turnamensiamember.Model.Response.TournamentDetailResponse;

/**
 * Created by Asus on 02/07/2017.
 */

public class TournamentPagerAdapter extends FragmentStatePagerAdapter {
    private TournamentDetailResponse mResponse;

    public TournamentPagerAdapter(FragmentManager fm, TournamentDetailResponse response) {
        super(fm);
        this.mResponse = response;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return TournamentOverviewFragment.newInstance(mResponse.getTournament().getDescription());
        else if (position == 1) return TournamentDetailFragment.newInstance(mResponse.getTournament());
        else if (position == 2) return TournamentRuleFragment.newInstance(mResponse.getTournament().getRules());
        else if (position == 3) return TournamentPriceFragment.newInstance(mResponse.getTournament());
        else if (position == 4) return TournamentBracketFragment.newInstance();
        else if (position == 5) return TournamentLiveMatchFragment.newInstance(mResponse.getLive_matches_json());
        return null;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) return "Overview";
        else if (position == 1) return "Details";
        else if (position == 2) return "Rules";
        else if (position == 3) return "Prizes";
        else if (position == 4) return "Brackets";
        else if (position == 5) return "Live Match";
        return null;
    }
}
