package com.tugasakhir.turnamensiamember.View.Account;


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
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Account.AccountProfilePresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragment extends Fragment implements iPresenterResponse {
    private EditText mOldPasswordET;
    private EditText mNewPasswordET;
    private EditText mNewConfirmPasswordET;
    private Button mUpdateB;

    private ProgressDialog mProgressDialog;
    private AccountProfilePresenter mAccountProfilePresenter;

    private SessionManager mSessionManager;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }

    public static ChangePasswordFragment newInstance() {
        return new ChangePasswordFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);

        mOldPasswordET = (EditText) view.findViewById(R.id.old_password);
        mNewPasswordET = (EditText) view.findViewById(R.id.new_change_password);
        mNewConfirmPasswordET = (EditText) view.findViewById(R.id.new_change_confirm_password);
        mUpdateB = (Button) view.findViewById(R.id.update_change_password);

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mSessionManager = new SessionManager(getContext());
        mAccountProfilePresenter = new AccountProfilePresenter(this);

        mUpdateB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = mOldPasswordET.getText().toString();
                String newPassword = mNewPasswordET.getText().toString();
                String newConfirmPassword = mNewConfirmPasswordET.getText().toString();

                if (TextUtils.isEmpty(oldPassword) || TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(newConfirmPassword)) {
                    Toast.makeText(getContext(), "Please fill the blank fields", Toast.LENGTH_SHORT).show();
                }
                else if (!newPassword.equals(newConfirmPassword)) {
                    Toast.makeText(getContext(), "Password and Confirm Password doesn't match", Toast.LENGTH_SHORT).show();
                }
                else {
                    mProgressDialog.show();
                    String token = mSessionManager.getTokenLoggedIn();
                    mAccountProfilePresenter.doUpdateParticipantPassword(token, oldPassword, newPassword, newConfirmPassword);
                }
            }
        });

        return view;
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();
        Toast.makeText(getContext(), response.getMessage()[0], Toast.LENGTH_SHORT).show();
        ((AccountActivity)getActivity()).getSupportFragmentManager().popBackStack();
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
