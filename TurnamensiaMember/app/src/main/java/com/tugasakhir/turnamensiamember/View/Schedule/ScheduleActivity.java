package com.tugasakhir.turnamensiamember.View.Schedule;

import android.os.Bundle;

import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

public class ScheduleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_schedule, mBaseLayout);

        showUpCaretMenu();
    }
}
