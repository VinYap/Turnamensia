package com.tugasakhir.turnamensiaorganizer.View.Tournament;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tugasakhir.turnamensiaorganizer.Model.Basic.Tournament;
import com.tugasakhir.turnamensiaorganizer.R;
import com.tugasakhir.turnamensiaorganizer.View.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class TournamentActivity extends BaseActivity {
    private RecyclerView mTournamentRV;

    private List<Tournament> mTournaments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_tournament, mBaseLayout);

        mTournamentRV = (RecyclerView) findViewById(R.id.tournament);
        mTournamentRV.setLayoutManager(new LinearLayoutManager(this));
        mTournamentRV.setHasFixedSize(true);

        initializeData();

        TournamentAdapter adapter = new TournamentAdapter(mTournaments);
        mTournamentRV.setAdapter(adapter);
    }

    private void initializeData() {
        mTournaments = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Tournament tournament = new Tournament();
            tournament.setName("DOTA BATTLE GROUND");
            tournament.setDate("1 Juni 2017 - 7 Juni 2017");
            tournament.setRegistration("Registrasi sebelum 12 Mei 2017");
            tournament.setStatus("On Going");
            tournament.setPhotoId(R.drawable.ib);
            mTournaments.add(tournament);
        }
    }
}
