package com.tugasakhir.turnamensiamember.View.Schedule;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;
import com.tugasakhir.turnamensiamember.View.Team.TeamPagerAdapter;

public class ScheduleActivity extends BaseActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_schedule, mBaseLayout);

        showUpCaretMenu();

        mTabLayout = (TabLayout) findViewById(R.id.schedule_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.schedule_pager);

        SchedulePagerAdapter adapter = new SchedulePagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }
}
