package com.tugasakhir.turnamensiamember.View.Main;

import android.os.Bundle;

import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main, mBaseLayout);

        //startActivity(new Intent(this, AuthActivity.class));
//        startActivity(new Intent(this, TournamentActivity.class));
    }
}
