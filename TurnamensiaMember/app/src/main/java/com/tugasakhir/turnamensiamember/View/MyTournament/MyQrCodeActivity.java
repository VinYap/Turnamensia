package com.tugasakhir.turnamensiamember.View.MyTournament;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

import static com.tugasakhir.turnamensiamember.View.MyTournament.MyTournamentViewHolder.TOURNAMENT_NAME;
import static com.tugasakhir.turnamensiamember.View.MyTournament.MyTournamentViewHolder.URI_KEY;

public class MyQrCodeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_my_qr_code, mBaseLayout);

        showUpCaretMenu();

        if (savedInstanceState != null) {
            return;
        }

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
        MenuItem mActionSettings = menu.findItem(R.id.action_settings);
        mActionSettings.setVisible(false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }
}
