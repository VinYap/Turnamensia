package com.tugasakhir.turnamensiamember.View.Tournament;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;
import com.tugasakhir.turnamensiamember.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TournamentDetailFragment extends Fragment {
    private TextView mEventTypeTV;
    private TextView mLocationTV;
    private TextView mAddressTV;
    private TextView mEntryFeeTV;
    private TextView mMinParticipantsTV;
    private TextView mMaxParticipantsTV;
    private TextView mCurrentParticipantsTV;
    private TextView mTeamSizeTV;

    private Tournament mTournament;

    public TournamentDetailFragment() {
        // Required empty public constructor
    }

    public static TournamentDetailFragment newInstance(Tournament tournament) {
        TournamentDetailFragment fragment = new TournamentDetailFragment();
        fragment.mTournament = tournament;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tournament_detail, container, false);

        mEventTypeTV = (TextView) view.findViewById(R.id.tournament_event_type);
        mLocationTV = (TextView) view.findViewById(R.id.tournament_location);
        mAddressTV = (TextView) view.findViewById(R.id.tournament_address);
        mEntryFeeTV = (TextView) view.findViewById(R.id.tournament_entry_fee);
        mMinParticipantsTV = (TextView) view.findViewById(R.id.tournament_min_participants);
        mMaxParticipantsTV = (TextView) view.findViewById(R.id.tournament_max_participants);
        mCurrentParticipantsTV = (TextView) view.findViewById(R.id.tournament_current_participants);
        mTeamSizeTV = (TextView) view.findViewById(R.id.tournament_team_size);

        mEventTypeTV.setText(mTournament.getType());
        mLocationTV.setText(mTournament.getCity());
        mAddressTV.setText(mTournament.getAddress());
        mEntryFeeTV.setText("Rp ".concat(String.format("%,d", mTournament.getEntry_fee())));
        mMaxParticipantsTV.setText(mTournament.getMax_participant().toString());
        mCurrentParticipantsTV.setText(mTournament.getNumber_of_registers().toString());
        mTeamSizeTV.setText(mTournament.getTeam_size().toString()+ " VS " + mTournament.getTeam_size().toString());

        return view;
    }
}
