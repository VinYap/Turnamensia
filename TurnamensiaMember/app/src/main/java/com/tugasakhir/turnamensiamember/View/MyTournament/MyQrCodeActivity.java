package com.tugasakhir.turnamensiamember.View.MyTournament;

import android.os.Bundle;
import android.view.Menu;

import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

import static com.tugasakhir.turnamensiamember.View.MyTournament.MyTournamentViewHolder.TOURNAMENT_NAME;
import static com.tugasakhir.turnamensiamember.View.MyTournament.MyTournamentViewHolder.URI_KEY;

public class MyQrCodeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_my_qr_code, mBaseLayout);

        if (savedInstanceState != null) {
            return;
        }

        showUpCaretMenu();

        String uri = getIntent().getStringExtra(URI_KEY);
        String name = getIntent().getStringExtra(TOURNAMENT_NAME);

        this.setTitle(name + " Qr Code");

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layout_my_qr_code, MyQrCodeFragment.newInstance(uri))
                .commit();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.setGroupVisible(R.id.group_setting, false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }
}
