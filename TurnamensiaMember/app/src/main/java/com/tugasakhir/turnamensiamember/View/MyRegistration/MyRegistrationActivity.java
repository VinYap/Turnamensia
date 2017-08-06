package com.tugasakhir.turnamensiamember.View.MyRegistration;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.widget.Toast;

import com.tugasakhir.turnamensiamember.Model.Basic.MyRegistration;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.MyRegisterResponse;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.MyRegistration.MyRegistrationPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

import java.util.List;

public class MyRegistrationActivity extends BaseActivity implements iPresenterResponse {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private ProgressDialog mProgressDialog;
    private SessionManager mSessionManager;
    private MyRegistrationPresenter mMyRegistrationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_my_registration, mBaseLayout);

        showUpCaretMenu();

        mTabLayout = (TabLayout) findViewById(R.id.my_registration_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.my_registration_pager);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mSessionManager = new SessionManager(this);
        mMyRegistrationPresenter = new MyRegistrationPresenter(this);

        String token = mSessionManager.getTokenLoggedIn();

        this.setTitle("Registration Status");

        mProgressDialog.show();
        mMyRegistrationPresenter.doGetParticipantMyRegister(token);
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();
        List<MyRegistration> myRegistrations = ((MyRegisterResponse) response).getRegistrations();
        if (myRegistrations != null && myRegistrations.size() <= 2) {
            mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        }
        MyRegistrationPagerAdapter adapter = new MyRegistrationPagerAdapter(getSupportFragmentManager(), myRegistrations);
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void doFail(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doConnectionError(int message) {
        mProgressDialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.setGroupVisible(R.id.group_setting, false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }

}
