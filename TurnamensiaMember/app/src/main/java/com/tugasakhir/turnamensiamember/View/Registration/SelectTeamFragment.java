package com.tugasakhir.turnamensiamember.View.Registration;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tugasakhir.turnamensiamember.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectTeamFragment extends Fragment {
    private RecyclerView mTeamRV;
    private Button mActionB;

    public SelectTeamFragment() {
        // Required empty public constructor
    }

    public static SelectTeamFragment newInstance() {
        return new SelectTeamFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_team, container, false);

        mTeamRV = (RecyclerView) view.findViewById(R.id.select_team);
        mActionB = (Button) view.findViewById(R.id.next_action);

        mTeamRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mTeamRV.setHasFixedSize(true);

        SelectTeamAdapter adapter = new SelectTeamAdapter();
        mTeamRV.setAdapter(adapter);

        mActionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RegistrationActivity)getActivity()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.layout_registration, SelectPlayerFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

}
