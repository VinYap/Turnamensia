package com.tugasakhir.turnamensiamember.View.MyTournament;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyQrCodeFragment extends Fragment {
    private ImageView mQrCodeIV;

    private String uri;

    public MyQrCodeFragment() {
        // Required empty public constructor
    }

    public static MyQrCodeFragment newInstance(String uri) {
        MyQrCodeFragment fragment = new MyQrCodeFragment();
        fragment.uri = uri;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_qr_code, container, false);

        mQrCodeIV = (ImageView) view.findViewById(R.id.qr_code);

        Picasso.with(getContext()).load(uri).into(mQrCodeIV);

        return view;
    }

}
