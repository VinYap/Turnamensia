package com.tugasakhir.turnamensiaorganizer.View.Main;

import android.content.Intent;
import android.os.Bundle;

import com.tugasakhir.turnamensiaorganizer.R;
import com.tugasakhir.turnamensiaorganizer.View.BaseActivity;
import com.tugasakhir.turnamensiaorganizer.View.Tournament.TournamentActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main, mBaseLayout);

        //startActivity(new Intent(this, AuthActivity.class));
        startActivity(new Intent(this, TournamentActivity.class));
    }
}
