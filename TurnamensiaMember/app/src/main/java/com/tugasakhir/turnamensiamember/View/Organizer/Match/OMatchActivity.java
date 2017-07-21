package com.tugasakhir.turnamensiamember.View.Organizer.Match;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;
import com.tugasakhir.turnamensiamember.View.Organizer.Tournament.OTournamentViewHolder;

public class OMatchActivity extends BaseActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private int id;

    private String[] titles = new String[] {"Tab 1", "Tab 2", "Tab 3", "Tab 4", "Tab 5", "Tab 6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_organizer_match, mBaseLayout);

        showUpCaretMenu();

        id = getIntent().getIntExtra(OTournamentViewHolder.TOURNAMENT_KEY, -1);
        mTabLayout = (TabLayout) findViewById(R.id.match_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.match_pager);

        OMatchPagerAdapter adapter = new OMatchPagerAdapter(getSupportFragmentManager(), titles);
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }
}
