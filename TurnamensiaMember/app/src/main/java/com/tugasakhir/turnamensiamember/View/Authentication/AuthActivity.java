package com.tugasakhir.turnamensiamember.View.Authentication;

import android.os.Bundle;

import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

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
