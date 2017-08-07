package com.tugasakhir.turnamensiamember.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoDataFragment extends Fragment {

    public NoDataFragment() {
        // Required empty public constructor
    }

    public static NoDataFragment newInstance() {
        return new NoDataFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_no_data, container, false);
    }

}
