package com.tugasakhir.turnamensiamember.View.MyRegistration;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyRegistrationFragment extends Fragment {
    private RecyclerView mRegistrationRV;

    public MyRegistrationFragment() {
        // Required empty public constructor
    }

    public static MyRegistrationFragment newInstance() {
        return new MyRegistrationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_registration, container, false);

        mRegistrationRV = (RecyclerView) view.findViewById(R.id.my_registration_rv);
        mRegistrationRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mRegistrationRV.setHasFixedSize(true);

        MyRegistrationAdapter adapter = new MyRegistrationAdapter();
        mRegistrationRV.setAdapter(adapter);

        return view;
    }

}
