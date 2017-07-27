package com.tugasakhir.turnamensiamember.View.Registration;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.tugasakhir.turnamensiamember.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectPlayerFragment extends Fragment {
    private RecyclerView mPlayerRV;
    private GridView mSelectedGV;
    private Button mActionB;
    private TextView mLeftActionTV;
    private TextView mRightActionTV;

    public SelectPlayerFragment() {
        // Required empty public constructor
    }

    public static SelectPlayerFragment newInstance() {
        return new SelectPlayerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_player, container, false);

        mPlayerRV = (RecyclerView) view.findViewById(R.id.select_player);
        mSelectedGV = (GridView) view.findViewById(R.id.selected_player);
        mActionB = (Button) view.findViewById(R.id.next_action);
        mLeftActionTV = (TextView) view.findViewById(R.id.left_action);
        mRightActionTV = (TextView) view.findViewById(R.id.right_action);

        mLeftActionTV.setText("Choose Your Player");
        mRightActionTV.setText("Finish");

        mPlayerRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mPlayerRV.setHasFixedSize(true);

        SelectPlayerAdapter selectAdapter = new SelectPlayerAdapter();
        mPlayerRV.setAdapter(selectAdapter);

        SelectedPlayerAdapter selectedAdapter = new SelectedPlayerAdapter(getContext());
        mSelectedGV.setAdapter(selectedAdapter);

        mActionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RegistrationActivity)getActivity()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.layout_registration, RegistrationConfirmFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

}
