package com.tugasakhir.turnamensiaorganizer.View.Authentication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tugasakhir.turnamensiaorganizer.Model.Basic.Response;
import com.tugasakhir.turnamensiaorganizer.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiaorganizer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements iPresenterResponse {
    private EditText mEmailET;
    private EditText mPasswordET;
    private TextView mSignUpTV;
    private Button mSignInB;

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

            }
        });

        return view;
    }

    @Override
    public void doSuccess(Response response) {

    }

    @Override
    public void doFail(String message) {

    }

    @Override
    public void doConnectionError(int message) {

    }
}
