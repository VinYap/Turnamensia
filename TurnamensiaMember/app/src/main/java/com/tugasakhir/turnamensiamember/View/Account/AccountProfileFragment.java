package com.tugasakhir.turnamensiamember.View.Account;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountProfileFragment extends Fragment {


    public AccountProfileFragment() {
        // Required empty public constructor
    }

    public static AccountProfileFragment newInstance() {
        return new AccountProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_profile, container, false);
    }

}
