package com.tugasakhir.turnamensiaorganizer.View.Match;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.tugasakhir.turnamensiaorganizer.R;
import com.tugasakhir.turnamensiaorganizer.View.BaseActivity;
import com.tugasakhir.turnamensiaorganizer.View.Tournament.TournamentViewHolder;

public class MatchActivity extends BaseActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private int id;

    private String[] titles = new String[] {"Tab 1", "Tab 2", "Tab 3", "Tab 4", "Tab 5", "Tab 6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_match, mBaseLayout);

        showUpCaretMenu();

        id = getIntent().getIntExtra(TournamentViewHolder.TOURNAMENT_KEY, -1);
        mTabLayout = (TabLayout) findViewById(R.id.match_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.match_pager);

        MatchPagerAdapter adapter = new MatchPagerAdapter(getSupportFragmentManager(), titles);
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }
}
