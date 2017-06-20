package com.tugasakhir.turnamensiaorganizer.View.Authentication;

import android.os.Bundle;

import com.tugasakhir.turnamensiaorganizer.R;
import com.tugasakhir.turnamensiaorganizer.View.BaseActivity;

public class AuthActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_auth, mBaseLayout);

        if (savedInstanceState !=  null) {
            return;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layout_auth, LoginFragment.newInstance())
                .commit();
    }
}
