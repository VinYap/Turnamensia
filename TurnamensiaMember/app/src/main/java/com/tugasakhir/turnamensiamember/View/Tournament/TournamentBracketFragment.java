package com.tugasakhir.turnamensiamember.View.Tournament;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.tugasakhir.turnamensiamember.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TournamentBracketFragment extends Fragment {
    private WebView mBracketWV;

    private String url;

    public TournamentBracketFragment() {
        // Required empty public constructor
    }

    public static TournamentBracketFragment newInstance(String url) {
        TournamentBracketFragment fragment = new TournamentBracketFragment();
        fragment.url = url;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tournament_bracket, container, false);

        mBracketWV = (WebView) view.findViewById(R.id.layout_bracket);

//        WebSettings webSettings = mBracketWV.getSettings();
//        webSettings.setJavaScriptEnabled(true);

        mBracketWV.loadUrl(url);

        return view;
    }

}
