package com.tugasakhir.turnamensiaorganizer.View.Match;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiaorganizer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchFragment extends Fragment {


    public MatchFragment() {
        // Required empty public constructor
    }

    public static MatchFragment newInstance(String text) {
        Bundle bundle = new Bundle();
        bundle.putString("text", text);

        MatchFragment f = new MatchFragment();
        f.setArguments(bundle);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_match, container, false);

        Bundle bundle = getArguments();

//        TextView textView = (TextView) view.findViewById(R.id.textView2);
//        textView.setText(bundle.getString("text"));

        return view;
    }

}
