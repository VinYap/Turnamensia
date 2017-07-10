package com.tugasakhir.turnamensiamember.View.LiveMatch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.tugasakhir.turnamensiamember.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PickBanFragment extends Fragment {
    private GridView mRadiantGV;
    private GridView mDireGV;

    public PickBanFragment() {
        // Required empty public constructor
    }

    public static PickBanFragment newInstance() {
        return new PickBanFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pick_ban, container, false);

        mRadiantGV = (GridView) view.findViewById(R.id.radiant_pick_ban);
        mDireGV = (GridView) view.findViewById(R.id.dire_pick_ban);

        PickBanAdapter radiantAdapter = new PickBanAdapter(getContext());
        mRadiantGV.setAdapter(radiantAdapter);

        PickBanAdapter direAdapter = new PickBanAdapter(getContext());
        mDireGV.setAdapter(direAdapter);

        return view;
    }

}
