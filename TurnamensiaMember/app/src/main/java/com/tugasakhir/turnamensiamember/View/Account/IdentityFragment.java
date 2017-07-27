package com.tugasakhir.turnamensiamember.View.Account;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.PictureResponse;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Account.AccountProfilePresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class IdentityFragment extends Fragment implements iPresenterResponse {
    private ImageView mIdentityIV;
    private Button mUploadB;

    private ProgressDialog mProgressDialog;
    private SessionManager mSessionManager;
    private AccountProfilePresenter mAccountProfilePresenter;

    private static final Integer IMAGE_CODE = 2;

    public IdentityFragment() {
        // Required empty public constructor
    }

    public static IdentityFragment newInstance() {
        return new IdentityFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_identity, container, false);

        mIdentityIV = (ImageView) view.findViewById(R.id.identity);
        mUploadB = (Button) view.findViewById(R.id.upload_identity);

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mSessionManager = new SessionManager(getContext());
        mAccountProfilePresenter = new AccountProfilePresenter(this);

        mUploadB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), IMAGE_CODE);
            }
        });

        String token = mSessionManager.getTokenLoggedIn();

        mProgressDialog.show();
        mAccountProfilePresenter.doGetParticipantIdentification(token);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_CODE && resultCode == RESULT_OK && data != null) {
            mProgressDialog.show();
            Uri selectedImage = data.getData();
            File file = new File(getRealPathFromURI(getContext(), selectedImage));
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part image = MultipartBody.Part.createFormData("identity_card", file.getName(), requestFile);
            String token = mSessionManager.getTokenLoggedIn();
            mAccountProfilePresenter.doUpdateParticipantIdentification(token, image);
        }
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();
        Picasso.with(getContext()).load(((PictureResponse) response).getFile_path()).into(mIdentityIV);
        Toast.makeText(getContext(), response.getMessage()[0], Toast.LENGTH_SHORT).show();
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

    private String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
