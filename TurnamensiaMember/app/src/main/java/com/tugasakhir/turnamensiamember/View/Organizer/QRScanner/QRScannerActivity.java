package com.tugasakhir.turnamensiamember.View.Organizer.QRScanner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.Result;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.QRScannerResultResponse;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Tournament.TournamentPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;
import com.tugasakhir.turnamensiamember.View.Organizer.Team.OQrCodeResultActivity;

import org.parceler.Parcels;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.tugasakhir.turnamensiamember.View.Organizer.Match.OMatchViewHolder.MATCH_KEY;
import static com.tugasakhir.turnamensiamember.View.Organizer.Match.OMatchViewHolder.MATCH_TEAM_ATTENDANCE_TITLE_KEY;

/**
 * Created by Andrianto on 7/23/2017.
 */

public class QRScannerActivity extends BaseActivity implements ZXingScannerView.ResultHandler {
    public static final String MATCH_ID_KEY = "QRResultMatchID";
    public static final String QR_CODE_IDENTIFIER_KEY = "QRCodeIdentifier";
    public static final String QR_RESULT_TOURNAMENT_KEY = "QRResultTournament";
    public static final String QR_RESULT_TOURNAMENT_REGISTRATION_KEY = "QRResultTournamentRegistration";
    public static final String QR_RESULT_TEAM_KEY = "QRResultTEAM";
    public static final String QR_RESULT_MEMBER_KEY = "QRResultMEMBER";
    public static final String QR_RESULT_TITLE_KEY = "QRResultTITLE";

    private ProgressDialog mProgressDialog;
    private ZXingScannerView mScannerView;

    private SessionManager mSessionManager;

    private TournamentPresenter mTournamentPresenter;

    private String token;
    private Long match_id;
    private String qr_identifier;
    private String qr_result_title;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        getLayoutInflater().inflate(R.layout.activity_qrscanner, mBaseLayout);

        showUpCaretMenu();

        qr_result_title = getIntent().getExtras().getString(MATCH_TEAM_ATTENDANCE_TITLE_KEY, "");
        setTitle(qr_result_title);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        mScannerView = new ZXingScannerView(this);
        contentFrame.addView(mScannerView);

        mSessionManager = new SessionManager(getApplicationContext());

        mTournamentPresenter = new TournamentPresenter(new iPresenterResponse() {
            @Override
            public void doSuccess(Response response) {
                mProgressDialog.dismiss();

                Intent intent = new Intent(getApplicationContext(), OQrCodeResultActivity.class);
                intent.putExtra(MATCH_ID_KEY, match_id);
                intent.putExtra(QR_CODE_IDENTIFIER_KEY, qr_identifier);
                intent.putExtra(QR_RESULT_TOURNAMENT_KEY, Parcels.wrap(((QRScannerResultResponse) response).getTournament()));
                intent.putExtra(QR_RESULT_TOURNAMENT_REGISTRATION_KEY, Parcels.wrap(((QRScannerResultResponse) response).getTournament_registration()));
                intent.putExtra(QR_RESULT_TEAM_KEY, Parcels.wrap(((QRScannerResultResponse) response).getTeam()));
                intent.putExtra(QR_RESULT_MEMBER_KEY, Parcels.wrap(((QRScannerResultResponse) response).getMember()));
                intent.putExtra(QR_RESULT_TITLE_KEY, qr_result_title);
                startActivity(intent);
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

        token = mSessionManager.getTokenLoggedIn();
        match_id = getIntent().getLongExtra(MATCH_KEY, 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
//        Toast.makeText(this, "Contents = " + rawResult.getText() +
//                ", Format = " + rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();

        mProgressDialog.show();
        qr_identifier = rawResult.getText();
        mTournamentPresenter.doGetMatchAttendance(token, match_id, qr_identifier);

        // Note:
        // * Wait 2 seconds to resume the preview.
        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
        // * I don't know why this is the case but I don't have the time to figure out.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(QRScannerActivity.this);
            }
        }, 2000);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.setGroupVisible(R.id.group_setting, false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }
}
