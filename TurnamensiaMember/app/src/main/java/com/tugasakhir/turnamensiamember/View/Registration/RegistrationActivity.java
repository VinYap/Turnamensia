package com.tugasakhir.turnamensiamember.View.Registration;

import android.os.Bundle;

import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

public class RegistrationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_registration, mBaseLayout);

        showUpCaretMenu();

        if (savedInstanceState !=  null) {
            return;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layout_registration, SelectTeamFragment.newInstance())
                .commit();
    }
}
