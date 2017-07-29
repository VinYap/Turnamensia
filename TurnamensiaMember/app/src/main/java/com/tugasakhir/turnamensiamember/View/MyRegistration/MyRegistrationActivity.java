package com.tugasakhir.turnamensiamember.View.MyRegistration;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.MyRegister.MyRegisterPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

public class MyRegistrationActivity extends BaseActivity implements iPresenterResponse {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private ProgressDialog mProgressDialog;
    private SessionManager mSessionManager;
    private MyRegisterPresenter mMyRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_my_registration, mBaseLayout);

        mTabLayout = (TabLayout) findViewById(R.id.my_registration_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.my_registration_pager);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mSessionManager = new SessionManager(this);
        mMyRegisterPresenter = new MyRegisterPresenter(this);

        String token = mSessionManager.getTokenLoggedIn();

        mProgressDialog.show();
        mMyRegisterPresenter.doGetParticipantMyRegister(token);
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();
//        MyRegistrationPagerAdapter adapter = new MyRegistrationPagerAdapter(getSupportFragmentManager());
//        mViewPager.setAdapter(adapter);
//
//        mTabLayout.setupWithViewPager(mViewPager);
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
}
