package com.tugasakhir.turnamensiamember.View.Account;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

import static com.tugasakhir.turnamensiamember.View.Account.AccountTeamFragment.REQUEST_CODE;

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
        menu.setGroupVisible(R.id.group_setting, false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            AccountPagerAdapter adapter = new AccountPagerAdapter(getSupportFragmentManager());
            mViewPager.setAdapter(adapter);

            mTabLayout.setupWithViewPager(mViewPager);
        }
    }
}
