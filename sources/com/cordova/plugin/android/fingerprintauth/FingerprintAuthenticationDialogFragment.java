package com.cordova.plugin.android.fingerprintauth;

import android.app.DialogFragment;
import android.app.KeyguardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.adobe.phonegap.push.PushConstants;
import com.cordova.plugin.android.fingerprintauth.FingerprintUiHelper;

public class FingerprintAuthenticationDialogFragment extends DialogFragment implements FingerprintUiHelper.Callback {
    private static final int REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS = 1;
    private static final String TAG = "FingerprintAuthDialog";
    private Button mCancelButton;
    private FingerprintManager.CryptoObject mCryptoObject;
    private View mFingerprintContent;
    private FingerprintUiHelper mFingerprintUiHelper;
    FingerprintUiHelper.FingerprintUiHelperBuilder mFingerprintUiHelperBuilder;
    private KeyguardManager mKeyguardManager;
    private Button mSecondDialogButton;
    private Stage mStage = Stage.FINGERPRINT;

    public enum Stage {
        FINGERPRINT,
        NEW_FINGERPRINT_ENROLLED,
        BACKUP
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setStyle(1, 16974393);
        this.mKeyguardManager = (KeyguardManager) getContext().getSystemService("keyguard");
        this.mFingerprintUiHelperBuilder = new FingerprintUiHelper.FingerprintUiHelperBuilder(getContext(), (FingerprintManager) getContext().getSystemService(FingerprintManager.class));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        Log.d(TAG, "disableBackup: " + FingerprintAuth.mDisableBackup);
        View v = inflater.inflate(getResources().getIdentifier("fingerprint_dialog_container", "layout", FingerprintAuth.packageName), container, false);
        TextView dialogTitleTextView = (TextView) v.findViewById(getResources().getIdentifier("fingerprint_auth_dialog_title", PushConstants.CHANNEL_ID, FingerprintAuth.packageName));
        if (FingerprintAuth.mDialogTitle != null) {
            dialogTitleTextView.setText(FingerprintAuth.mDialogTitle);
        }
        TextView dialogMessageTextView = (TextView) v.findViewById(getResources().getIdentifier("fingerprint_description", PushConstants.CHANNEL_ID, FingerprintAuth.packageName));
        if (FingerprintAuth.mDialogMessage != null) {
            dialogMessageTextView.setText(FingerprintAuth.mDialogMessage);
        }
        TextView dialogHintTextView = (TextView) v.findViewById(getResources().getIdentifier("fingerprint_status", PushConstants.CHANNEL_ID, FingerprintAuth.packageName));
        if (FingerprintAuth.mDialogHint != null) {
            dialogHintTextView.setText(FingerprintAuth.mDialogHint);
        }
        this.mCancelButton = (Button) v.findViewById(getResources().getIdentifier("cancel_button", PushConstants.CHANNEL_ID, FingerprintAuth.packageName));
        this.mCancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FingerprintAuth.onCancelled();
                FingerprintAuthenticationDialogFragment.this.dismissAllowingStateLoss();
            }
        });
        this.mSecondDialogButton = (Button) v.findViewById(getResources().getIdentifier("second_dialog_button", PushConstants.CHANNEL_ID, FingerprintAuth.packageName));
        if (FingerprintAuth.mDisableBackup) {
            this.mSecondDialogButton.setVisibility(8);
        }
        this.mSecondDialogButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FingerprintAuthenticationDialogFragment.this.goToBackup();
            }
        });
        this.mFingerprintContent = v.findViewById(getResources().getIdentifier("fingerprint_container", PushConstants.CHANNEL_ID, FingerprintAuth.packageName));
        int identifier = getResources().getIdentifier("new_fingerprint_enrolled_description", PushConstants.CHANNEL_ID, FingerprintAuth.packageName);
        int fingerprint_icon_id = getResources().getIdentifier("fingerprint_icon", PushConstants.CHANNEL_ID, FingerprintAuth.packageName);
        int fingerprint_status_id = getResources().getIdentifier("fingerprint_status", PushConstants.CHANNEL_ID, FingerprintAuth.packageName);
        this.mFingerprintUiHelper = this.mFingerprintUiHelperBuilder.build((ImageView) v.findViewById(fingerprint_icon_id), (TextView) v.findViewById(fingerprint_status_id), this);
        updateStage();
        if (!this.mFingerprintUiHelper.isFingerprintAuthAvailable()) {
            goToBackup();
        }
        return v;
    }

    public void onResume() {
        super.onResume();
        if (this.mStage == Stage.FINGERPRINT) {
            this.mFingerprintUiHelper.startListening(this.mCryptoObject);
        }
    }

    public void setStage(Stage stage) {
        this.mStage = stage;
    }

    public void onPause() {
        super.onPause();
        this.mFingerprintUiHelper.stopListening();
    }

    public void setCryptoObject(FingerprintManager.CryptoObject cryptoObject) {
        this.mCryptoObject = cryptoObject;
    }

    /* access modifiers changed from: private */
    public void goToBackup() {
        this.mStage = Stage.BACKUP;
        updateStage();
    }

    private void updateStage() {
        int cancel_id = getResources().getIdentifier("cancel", "string", FingerprintAuth.packageName);
        switch (this.mStage) {
            case FINGERPRINT:
                this.mCancelButton.setText(cancel_id);
                this.mSecondDialogButton.setText(getResources().getIdentifier("use_backup", "string", FingerprintAuth.packageName));
                this.mFingerprintContent.setVisibility(0);
                return;
            case NEW_FINGERPRINT_ENROLLED:
            case BACKUP:
                if (this.mStage == Stage.NEW_FINGERPRINT_ENROLLED) {
                }
                if (!this.mKeyguardManager.isKeyguardSecure()) {
                    Toast.makeText(getContext(), getString(getResources().getIdentifier("secure_lock_screen_required", "string", FingerprintAuth.packageName)), 1).show();
                    return;
                } else if (FingerprintAuth.mDisableBackup) {
                    FingerprintAuth.onError("backup disabled");
                    return;
                } else {
                    showAuthenticationScreen();
                    return;
                }
            default:
                return;
        }
    }

    private void showAuthenticationScreen() {
        Intent intent = this.mKeyguardManager.createConfirmDeviceCredentialIntent((CharSequence) null, (CharSequence) null);
        if (intent != null) {
            startActivityForResult(intent, 1);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            getActivity();
            if (resultCode == -1) {
                FingerprintAuth.onAuthenticated(false, (FingerprintManager.AuthenticationResult) null);
            } else {
                FingerprintAuth.onCancelled();
            }
            dismissAllowingStateLoss();
        }
    }

    public void onAuthenticated(FingerprintManager.AuthenticationResult result) {
        FingerprintAuth.onAuthenticated(true, result);
        dismissAllowingStateLoss();
    }

    public void onError(CharSequence errString) {
        if (FingerprintAuth.mDisableBackup) {
            FingerprintAuth.onError(errString);
            dismissAllowingStateLoss();
        } else if (getActivity() != null && isAdded()) {
            goToBackup();
        }
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        FingerprintAuth.onCancelled();
    }
}
