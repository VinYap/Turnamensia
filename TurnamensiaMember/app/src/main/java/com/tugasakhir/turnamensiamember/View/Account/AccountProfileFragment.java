package com.tugasakhir.turnamensiamember.View.Account;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Basic.User;
import com.tugasakhir.turnamensiamember.Model.Response.AccountProfileResponse;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Account.AccountProfilePresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountProfileFragment extends Fragment implements iPresenterResponse {
    private ImageView mImageIV;
    private ImageView mChangeImageIV;
    private ImageView mDeleteImageIV;
    private EditText mNameET;
    private EditText mEmailET;
    private EditText mSteamIdET;
    private Button mUpdateB;
    private Button mChangePasswordB;
    private Button mIdentityB;

    private ProgressDialog mProgressDialog;

    private AccountProfilePresenter mAccountProfilePresenter;
    private SessionManager mSessionManager;

    private Integer mStatus;

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
        View view = inflater.inflate(R.layout.fragment_account_profile, container, false);

        mImageIV = (ImageView) view.findViewById(R.id.account_profile_image);
        mChangeImageIV = (ImageView) view.findViewById(R.id.account_profile_change_image);
        mDeleteImageIV = (ImageView) view.findViewById(R.id.account_profile_delete_image);
        mNameET = (EditText) view.findViewById(R.id.account_profile_name);
        mEmailET = (EditText) view.findViewById(R.id.account_profile_email);
        mSteamIdET = (EditText) view.findViewById(R.id.account_profile_steam_id);
        mUpdateB = (Button) view.findViewById(R.id.account_profile_update);
        mChangePasswordB = (Button) view.findViewById(R.id.account_profile_change_password);
        mIdentityB = (Button) view.findViewById(R.id.account_profile_identity);

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mAccountProfilePresenter = new AccountProfilePresenter(this);
        mSessionManager = new SessionManager(getContext());

        mChangeImageIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 0);
            }
        });

        mUpdateB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameET.getText().toString();
                String email = mEmailET.getText().toString();
                String steamId = mSteamIdET.getText().toString();
                String token = mSessionManager.getTokenLoggedIn();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email)) {
                    Toast.makeText(getContext(), "Please fill name and email", Toast.LENGTH_SHORT).show();
                }
                else {
                    mProgressDialog.show();
                    mStatus = 1;
                    mAccountProfilePresenter.doUpdateParticipantAccountProfile(token, name, email, steamId);
                }
            }
        });

        mChangePasswordB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AccountActivity)getActivity()).getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.layout_account_profile, ChangePasswordFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        mIdentityB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//        this.getUser();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            Picasso.with(getContext()).load(selectedImage).into(mImageIV);
//            String[] filePathColumn = { MediaStore.Images.Media.DATA };
//            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
//            cursor.moveToFirst();
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            String picturePath = cursor.getString(columnIndex);
//            cursor.close();
//            ImageView imageView = (ImageView) findViewById(R.id.imgView);
//            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    @Override
    public void doSuccess(Response response) {
        if (mStatus == 0) {
            mSessionManager.doChangeUserData(((AccountProfileResponse) response).getUser());
            mProgressDialog.dismiss();
            setUser();
        }
        else {
            User user = new User();
            user.setName(mNameET.getText().toString());
            user.setEmail(mEmailET.getText().toString());
            user.setSteam32_id(mSteamIdET.getText().toString());
            user.setMember_type(mSessionManager.getUserLoggedIn().getMember_type());
            mSessionManager.doChangeUserData(user);
            mProgressDialog.dismiss();
            Toast.makeText(getContext(), response.getMessage()[0], Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void doFail(String message) {
        mProgressDialog.dismiss();
        if (mStatus == 0) setUser();
        else Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doConnectionError(int message) {
        mProgressDialog.dismiss();
        if (mStatus == 0) setUser();
        else Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void getUser() {
        mStatus = 0;
        String token = mSessionManager.getTokenLoggedIn();
        mAccountProfilePresenter.doGetParticipantAccountProfile(token);
    }

    private void setUser() {
        User user = mSessionManager.getUserLoggedIn();

        mNameET.setText(user.getName());
        mEmailET.setText(user.getEmail());
        mSteamIdET.setText(user.getSteam32_id());
        Picasso.with(getContext()).load(user.getImage()).into(mImageIV);
    }
}
