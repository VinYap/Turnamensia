package com.tugasakhir.turnamensiamember.View.Tournament;

import android.os.Bundle;
import android.view.Menu;

import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

import static com.tugasakhir.turnamensiamember.View.Main.MainOrganizerViewHolder.TOURNAMENT_KEY;
import static com.tugasakhir.turnamensiamember.View.Main.MainViewHolder.TOURNAMENT_NAME;

public class TournamentBracketActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_tournament_bracket, mBaseLayout);

        showUpCaretMenu();

        if (savedInstanceState != null) {
            return;
        }

        Long tournamentId = getIntent().getLongExtra(TOURNAMENT_KEY, -1);
        String tournamentName = getIntent().getStringExtra(TOURNAMENT_NAME);
        setTitle(tournamentName + " Bracket");

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layout_tournament_bracket, TournamentBracketFragment.newInstance(tournamentId))
                .commit();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.setGroupVisible(R.id.group_setting, false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }
}
