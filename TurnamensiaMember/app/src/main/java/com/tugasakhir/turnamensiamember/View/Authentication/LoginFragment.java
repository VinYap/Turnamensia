package com.tugasakhir.turnamensiamember.View.Authentication;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.LoginResponse;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Authentication.LoginPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements iPresenterResponse {
    private EditText mEmailET;
    private EditText mPasswordET;
    private TextView mSignUpTV;
    private Button mSignInB;
    private CheckBox mOrganizerCB;

    private ProgressDialog mProgressDialog;

    private LoginPresenter mLoginPresenter;
    private SessionManager mSessionManager;

    private Integer mMemberType;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mEmailET = (EditText) view.findViewById(R.id.email);
        mPasswordET = (EditText) view.findViewById(R.id.password);
        mSignUpTV = (TextView) view.findViewById(R.id.new_user);
        mSignInB = (Button) view.findViewById(R.id.sign_in);
        mOrganizerCB = (CheckBox) view.findViewById(R.id.organizer_ind);

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mLoginPresenter = new LoginPresenter(this);
        mSessionManager = new SessionManager(getContext());

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                ((AuthActivity)getActivity()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.layout_auth, RegisterFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        };

        SpannableString spannableString = new SpannableString(mSignUpTV.getText());
        spannableString.setSpan(clickableSpan, 22, 29, 0);
        mSignUpTV.setText(spannableString);
        mSignUpTV.setMovementMethod(LinkMovementMethod.getInstance());

        mSignInB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailET.getText().toString();
                String password = mPasswordET.getText().toString();
                Boolean isOrganizer = mOrganizerCB.isChecked();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getContext(), "Please fill the username and password fields.", Toast.LENGTH_SHORT).show();
                }
                else {
                    mProgressDialog.show();
                    if (isOrganizer) {
                        mMemberType = 2;
                        mLoginPresenter.doOrganizerLogin(email, password);
                    }
                    else {
                        mMemberType = 1;
                        mLoginPresenter.doParticipantLogin(email, password);
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void doSuccess(Response response) {
        ((LoginResponse) response).getUser().setMember_type(mMemberType);
        mSessionManager.doCreateSession((LoginResponse) response);
        mProgressDialog.dismiss();
        Toast.makeText(getContext(), response.getMessage()[0], Toast.LENGTH_SHORT).show();
        getActivity().finish();
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
