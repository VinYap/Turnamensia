package com.tugasakhir.turnamensiamember.View.Organizer.Team;

import android.os.Bundle;

import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

public class OQrCodeResultActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_qr_code_result, mBaseLayout);

        showUpCaretMenu();
    }
}
