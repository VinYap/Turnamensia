package com.tugasakhir.turnamensiamember.View.Authentication;

import android.os.Bundle;
import android.view.Menu;

import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

public class AuthActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_auth, mBaseLayout);

        if (savedInstanceState != null) {
            return;
        }

        showUpCaretMenu();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layout_auth, LoginFragment.newInstance())
                .commit();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.setGroupVisible(R.id.group_setting, false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }
}
