package com.tugasakhir.turnamensiamember.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.tugasakhir.turnamensiamember.Model.Basic.User;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.Account.AccountActivity;
import com.tugasakhir.turnamensiamember.View.Authentication.AuthActivity;
import com.tugasakhir.turnamensiamember.View.Registration.RegistrationActivity;
import com.tugasakhir.turnamensiamember.View.Tournament.TournamentActivity;

import static com.tugasakhir.turnamensiamember.R.id.toolbar;

public abstract class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    protected DrawerLayout mDrawerLayout;
    protected Toolbar mToolbar;
    protected ActionBarDrawerToggle mDrawerToggle;
    protected FrameLayout mBaseLayout;
    protected NavigationView mNavigationView;
    private Menu mNavigationMenu;

    private SessionManager mSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mToolbar = (Toolbar) findViewById(toolbar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        mBaseLayout = (FrameLayout) findViewById(R.id.layout_base);
        mNavigationMenu = mNavigationView.getMenu();

        mSessionManager = new SessionManager(getApplicationContext());

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    showUpCaretMenu();
                } else {
                    showHamburgerMenu();
                }
            }
        });
    }

    protected void showUpCaretMenu() {
        mDrawerToggle.setDrawerIndicatorEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    protected void showHamburgerMenu() {
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mDrawerToggle.syncState();
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        doUserLogin(mSessionManager.isUserLoggedIn());
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.login_register) {
            startActivity(new Intent(this, AuthActivity.class));
        }
        else if (id == R.id.my_account) {
            startActivity(new Intent(this, AccountActivity.class));
        }
        else if (id == R.id.my_tournament_participant) {
            startActivity(new Intent(this, TournamentActivity.class));
        }
        else if (id == R.id.registration) {
            startActivity(new Intent(this, RegistrationActivity.class));
        }
        else if (id == R.id.notification) {

        }
        else if (id == R.id.sign_out) {
            mSessionManager.doClearSession();
            doUserLogin(mSessionManager.isUserLoggedIn());
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void doUserLogin(Boolean isLogin) {
        User user = null;
        if (isLogin) {
            user = mSessionManager.getUserLoggedIn();
        }
        else {

        }

        mNavigationMenu.setGroupVisible(R.id.group_auth, !isLogin);
        mNavigationMenu.setGroupVisible(R.id.group_participant, user != null && user.getMember_type() == 1);
        mNavigationMenu.setGroupVisible(R.id.group_organizer, user != null && user.getMember_type() == 2);
        mNavigationMenu.setGroupVisible(R.id.group_sign_out, isLogin);
    }
}
