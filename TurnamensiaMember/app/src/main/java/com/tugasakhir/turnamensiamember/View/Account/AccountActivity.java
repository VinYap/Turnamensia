package com.tugasakhir.turnamensiamember.View.Account;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

public class AccountActivity extends BaseActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_account, mBaseLayout);

        showUpCaretMenu();

        mTabLayout = (TabLayout) findViewById(R.id.account_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.account_pager);

        this.setTitle("My Account");

        AccountPagerAdapter adapter = new AccountPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem mActionSettings = menu.findItem(R.id.action_settings);
        mActionSettings.setVisible(false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }
}
