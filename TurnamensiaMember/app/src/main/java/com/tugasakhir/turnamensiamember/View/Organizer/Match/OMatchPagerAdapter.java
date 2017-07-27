package com.tugasakhir.turnamensiamember.View.Organizer.Match;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tugasakhir.turnamensiamember.Model.Basic.OrganizerMatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Asus on 18/06/2017.
 */

public class OMatchPagerAdapter extends FragmentStatePagerAdapter {
    private String[] titles;
    private List<List<OrganizerMatch>> matches;

    public OMatchPagerAdapter(FragmentManager fm, Map<String, List<OrganizerMatch>> matches) {
        super(fm);

        this.titles = new String[matches.size()];
        this.matches = new ArrayList<>();

        Integer index = 0;
        for (Map.Entry<String, List<OrganizerMatch>> entry : matches.entrySet()) {
            this.titles[index] = entry.getKey();
            this.matches.add(index, entry.getValue());

            index++;
        }
    }

    @Override
    public Fragment getItem(int position) {
        return OMatchFragment.newInstance(matches.get(position));
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
