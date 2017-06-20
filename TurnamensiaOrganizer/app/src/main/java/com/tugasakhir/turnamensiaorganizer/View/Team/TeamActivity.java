package com.tugasakhir.turnamensiaorganizer.View.Team;

import android.os.Bundle;

import com.tugasakhir.turnamensiaorganizer.R;
import com.tugasakhir.turnamensiaorganizer.View.BaseActivity;

public class TeamActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_team, mBaseLayout);


    }
}
