package com.tugasakhir.turnamensiamember.View.MyRegistration;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.Basic.Bank;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.RegistrationConfirmationResponse;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.MyRegistration.MyRegistrationPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.tugasakhir.turnamensiamember.View.MyRegistration.MyRegistrationViewHolder.REGISTRATION_KEY;
import static com.tugasakhir.turnamensiamember.View.MyRegistration.MyRegistrationViewHolder.TOURNAMENT_NAME;

public class RegistrationConfirmationActivity extends BaseActivity {
    private TextView mRegistrationIdTV;
    private TextView mTeamNameTV;
    private TextView mTotalTV;
    private EditText mTransferNameET;
    private Spinner mTransferToS;
    private Button mUploadB;
    private TextView mUploadNameTV;
    private ImageView mPaymentIV;
    private Button mConfirmB;

    private ProgressDialog mProgressDialog;
    private SessionManager mSessionManager;
    private MyRegistrationPresenter myGetRegistrationPresenter;
    private MyRegistrationPresenter myRegistrationPresenter;

    private List<Bank> banks;
    private MultipartBody.Part image;

    private static final Integer IMAGE_CODE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_registration_confirmation, mBaseLayout);

        showUpCaretMenu();

        mRegistrationIdTV = (TextView) findViewById(R.id.registration_id);
        mTeamNameTV = (TextView) findViewById(R.id.team_name);
        mTotalTV = (TextView) findViewById(R.id.total_payment);
        mTransferNameET = (EditText) findViewById(R.id.transfer_name);
        mTransferToS = (Spinner) findViewById(R.id.transfer_to);
        mUploadB = (Button) findViewById(R.id.upload);
        mUploadNameTV = (TextView) findViewById(R.id.upload_name);
        mPaymentIV = (ImageView) findViewById(R.id.payment_image);
        mConfirmB = (Button) findViewById(R.id.confirm);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mSessionManager = new SessionManager(this);

        final String token = mSessionManager.getTokenLoggedIn();
        final Long transferId = getIntent().getLongExtra(REGISTRATION_KEY, -1);

        myGetRegistrationPresenter = new MyRegistrationPresenter(new iPresenterResponse() {
            @Override
            public void doSuccess(Response response) {
                mProgressDialog.dismiss();
                RegistrationConfirmationResponse newResponse = (RegistrationConfirmationResponse) response;

                mRegistrationIdTV.setText(newResponse.getTournament_registration().getId().toString());
                mTeamNameTV.setText(newResponse.getTeam().getName());
                mTotalTV.setText("Rp ".concat(String.format("%,d", newResponse.getTournament().getEntry_fee())));

                banks = newResponse.getBanks();

                ArrayAdapter<Bank> adapter = new ArrayAdapter<Bank>(getApplicationContext(), R.layout.item_bank_spinner, banks);
                adapter.setDropDownViewResource(R.layout.item_bank_spinner);
                mTransferToS.setAdapter(adapter);

                if (newResponse.getTournament_registration_confirmation() != null) {
                    String image = newResponse.getTournament_registration_confirmation().getImage();
                    mTransferNameET.setText(newResponse.getTournament_registration_confirmation().getName());
                    for (int i = 0; i < banks.size(); i++) {
                        if (banks.get(i).getId() == newResponse.getTournament_registration_confirmation().getBank()) {
                            mTransferToS.setSelection(i);
                            break;
                        }
                    }
                    if (!TextUtils.isEmpty(image)) {
                        Picasso.with(getApplicationContext()).load(image).into(mPaymentIV);
                    }
                }
            }

            @Override
            public void doFail(String message) {
                mProgressDialog.dismiss();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void doConnectionError(int message) {
                mProgressDialog.dismiss();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

        myRegistrationPresenter = new MyRegistrationPresenter(new iPresenterResponse() {
            @Override
            public void doSuccess(Response response) {
                mProgressDialog.dismiss();
                Toast.makeText(getApplicationContext(), response.getMessage()[0], Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void doFail(String message) {
                mProgressDialog.dismiss();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void doConnectionError(int message) {
                mProgressDialog.dismiss();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

        mUploadB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), IMAGE_CODE);
            }
        });

        mConfirmB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mTransferNameET.getText().toString();
                Long bankId = ((Bank)mTransferToS.getSelectedItem()).getId();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Please fill transfer name", Toast.LENGTH_SHORT).show();
                }
                else if (image == null) {
                    Toast.makeText(getApplicationContext(), "Please choose payment file", Toast.LENGTH_SHORT).show();
                }
                else {
                    mProgressDialog.show();
                    myRegistrationPresenter.doParticipantConfirmPayment(token, transferId, image, name, bankId);
                }
            }
        });

        this.setTitle(getIntent().getStringExtra(TOURNAMENT_NAME) + " Payment Confirmation");

        mProgressDialog.show();
        myGetRegistrationPresenter.doGetParticipantConfirmPayment(token, transferId);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_CODE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            File file = new File(getRealPathFromURI(this, selectedImage));
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            image = MultipartBody.Part.createFormData("confirmation_file_name", file.getName(), requestFile);

            mUploadNameTV.setText(file.getName());
            Picasso.with(this).load(selectedImage).into(mPaymentIV);
            mPaymentIV.setVisibility(View.VISIBLE);
        }
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

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem mActionSettings = menu.findItem(R.id.action_settings);
        mActionSettings.setVisible(false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }
}
