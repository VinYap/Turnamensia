package com.tugasakhir.turnamensiamember.View.LiveMatch;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

public class LiveMatchActivity extends BaseActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_live_match, mBaseLayout);

        showUpCaretMenu();

        mTabLayout = (TabLayout) findViewById(R.id.live_match_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.live_match_pager);

        LiveMatchPagerAdapter adapter = new LiveMatchPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }
}
