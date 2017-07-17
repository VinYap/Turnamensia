package com.tugasakhir.turnamensiamember.View.Authentication;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Presenter.Authentication.RegisterPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements iPresenterResponse {
    private EditText mNameET;
    private EditText mEmailET;
    private EditText mPasswordET;
    private EditText mConfirmPasswordET;
    private Button mSignUpB;

    private ProgressDialog mProgressDialog;
    private RegisterPresenter mRegisterPresenter;

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

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mRegisterPresenter = new RegisterPresenter(this);

        mSignUpB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameET.getText().toString();
                String email = mEmailET.getText().toString();
                String password = mPasswordET.getText().toString();
                String passwordConfirmation = mConfirmPasswordET.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(passwordConfirmation)) {
                    Toast.makeText(getContext(), "Please fill the blank fields.", Toast.LENGTH_SHORT).show();
                }
                else if (!password.equals(passwordConfirmation)) {
                    Toast.makeText(getContext(), "Password and Password Confirmatrion doesn't match", Toast.LENGTH_SHORT).show();
                }
                else {
                    mProgressDialog.show();
                    mRegisterPresenter.doParticipantRegister(name, email, password, passwordConfirmation);
                }
            }
        });

        return view;
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();
        Toast.makeText(getContext(), response.getMessage()[0], Toast.LENGTH_SHORT).show();
        ((AuthActivity)getActivity()).getSupportFragmentManager().popBackStack();
    }

    @Override
    public void doFail(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doConnectionError(int message) {
        mProgressDialog.dismiss();
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
