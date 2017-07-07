package com.tugasakhir.turnamensiamember.View.Main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private RecyclerView mTournamentRV;

    private List<Tournament> mTournaments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main, mBaseLayout);

        mTournamentRV = (RecyclerView) findViewById(R.id.main_tournament);
        mTournamentRV.setLayoutManager(new LinearLayoutManager(this));
        mTournamentRV.setHasFixedSize(true);

        initializeData();

        MainAdapter adapter = new MainAdapter(mTournaments);
        mTournamentRV.setAdapter(adapter);
//        startActivity(new Intent(this, AuthActivity.class));
//        startActivity(new Intent(this, TournamentActivity.class));
    }

    private void initializeData() {
        mTournaments = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Tournament tournament = new Tournament();
            tournament.setName("DOTA BATTLE GROUND");
            tournament.setDate("1 Juni 2017 - 7 Juni 2017");
            tournament.setRegistration("Registrasi sebelum 12 Mei 2017");
            tournament.setStatus("On Going");
            tournament.setPrice("Rp. 10.000 / team");
            tournament.setPhotoId(R.drawable.ib);
            mTournaments.add(tournament);
        }
    }
}
