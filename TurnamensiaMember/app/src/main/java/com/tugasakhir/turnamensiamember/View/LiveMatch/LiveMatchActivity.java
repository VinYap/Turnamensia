package com.tugasakhir.turnamensiamember.View.LiveMatch;

import android.os.Bundle;

import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

public class LiveMatchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_live_match, mBaseLayout);

        showUpCaretMenu();
    }
}
