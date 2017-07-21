package com.tugasakhir.turnamensiamember.View.Organizer.Tournament;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class OTournamentActivity extends BaseActivity {
    private RecyclerView mTournamentRV;

    private List<Tournament> mTournaments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_organizer_tournament, mBaseLayout);

        mTournamentRV = (RecyclerView) findViewById(R.id.tournament);
        mTournamentRV.setLayoutManager(new LinearLayoutManager(this));
        mTournamentRV.setHasFixedSize(true);

        initializeData();

        OTournamentAdapter adapter = new OTournamentAdapter(mTournaments);
        mTournamentRV.setAdapter(adapter);
    }

    private void initializeData() {
        mTournaments = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Tournament tournament = new Tournament();
            tournament.setName("DOTA BATTLE GROUND");
//            tournament.setDate("1 Juni 2017 - 7 Juni 2017");
//            tournament.setRegistration("Registrasi sebelum 12 Mei 2017");
            tournament.setStatus("On Going");
//            tournament.setPhotoId(R.drawable.ib);
            mTournaments.add(tournament);
        }
    }
}
