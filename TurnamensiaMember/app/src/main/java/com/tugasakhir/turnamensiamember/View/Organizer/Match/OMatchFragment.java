package com.tugasakhir.turnamensiamember.View.Organizer.Match;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.Model.Basic.OrganizerMatch;
import com.tugasakhir.turnamensiamember.R;

import org.parceler.Parcels;

import java.util.List;

import static com.tugasakhir.turnamensiamember.View.Tournament.TournamentActivity.IS_CLICKABLE;

/**
 * A simple {@link Fragment} subclass.
 */
public class OMatchFragment extends Fragment {
    private RecyclerView mMatchRV;

    private List<OrganizerMatch> matches;
    private Boolean isClickable;

    public OMatchFragment() {
        // Required empty public constructor
    }

    public static OMatchFragment newInstance(List<OrganizerMatch> matches, Boolean isClickable) {
        Bundle bundle = new Bundle();
        Parcelable wrapped = Parcels.wrap(matches);
        bundle.putParcelable("MATCHES", wrapped);
        bundle.putBoolean(IS_CLICKABLE, isClickable);

        OMatchFragment f = new OMatchFragment();
        f.setArguments(bundle);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_organizer_match, container, false);

        Bundle bundle = getArguments();
        matches = Parcels.unwrap(bundle.getParcelable("MATCHES"));
        isClickable = bundle.getBoolean(IS_CLICKABLE);

        mMatchRV = (RecyclerView) view.findViewById(R.id.match);
        mMatchRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mMatchRV.setHasFixedSize(true);

        OMatchAdapter adapter = new OMatchAdapter(matches, isClickable);
        mMatchRV.setAdapter(adapter);
        return view;
    }
}
