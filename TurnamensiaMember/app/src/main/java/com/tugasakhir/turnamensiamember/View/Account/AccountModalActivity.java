package com.tugasakhir.turnamensiamember.View.Account;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

public class AccountModalActivity extends BaseActivity {
    public static final String MODAL_TYPE = "MODAL_TYPE";
    public static final String MODAL_NAME = "MODAL_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_account_modal, mBaseLayout);

        if (savedInstanceState != null) {
            return;
        }

        showUpCaretMenu();

        this.setTitle(getIntent().getStringExtra(MODAL_NAME));

        Integer type = getIntent().getIntExtra(MODAL_TYPE, -1);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layout_account_modal, type == 0 ? ChangePasswordFragment.newInstance() : (type == 1 ? IdentityFragment.newInstance() : (type == 2 ? CreateTeamFragment.newInstance() : null)))
                .commit();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem mActionSettings = menu.findItem(R.id.action_settings);
        mActionSettings.setVisible(false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }
}
