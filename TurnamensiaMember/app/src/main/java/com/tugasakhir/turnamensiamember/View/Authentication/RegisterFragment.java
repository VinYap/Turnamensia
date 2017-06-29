package com.tugasakhir.turnamensiamember.View.Authentication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.tugasakhir.turnamensiamember.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    private EditText mNameET;
    private EditText mEmailET;
    private EditText mPasswordET;
    private EditText mConfirmPasswordET;
    private Button mSignUpB;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        mNameET = (EditText) view.findViewById(R.id.new_name);
        mEmailET = (EditText) view.findViewById(R.id.new_email);
        mPasswordET = (EditText) view.findViewById(R.id.new_password);
        mConfirmPasswordET = (EditText) view.findViewById(R.id.new_confirm_password);
        mSignUpB = (Button) view.findViewById(R.id.sign_up);

        mSignUpB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
