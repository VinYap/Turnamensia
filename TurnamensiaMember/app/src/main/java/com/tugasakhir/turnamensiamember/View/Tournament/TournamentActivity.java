package com.tugasakhir.turnamensiamember.View.Tournament;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;
import com.tugasakhir.turnamensiamember.View.Schedule.ScheduleActivity;

public class TournamentActivity extends BaseActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FloatingActionButton mScheduleFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_tournament, mBaseLayout);

//        showUpCaretMenu();

        mTabLayout = (TabLayout) findViewById(R.id.tournament_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.tournament_pager);
        mScheduleFAB = (FloatingActionButton) findViewById(R.id.schedule_fab);

        TournamentPagerAdapter adapter = new TournamentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        mScheduleFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScheduleActivity.class));
            }
        });

        mTabLayout.setupWithViewPager(mViewPager);
    }
}
