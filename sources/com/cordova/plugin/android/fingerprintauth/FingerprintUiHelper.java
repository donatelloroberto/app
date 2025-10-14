package com.cordova.plugin.android.fingerprintauth;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import com.adobe.phonegap.push.PushConstants;

@TargetApi(23)
public class FingerprintUiHelper extends FingerprintManager.AuthenticationCallback {
    static final long ERROR_TIMEOUT_MILLIS = 1600;
    static final long SUCCESS_DELAY_MILLIS = 1300;
    /* access modifiers changed from: private */
    public static FingerprintManager.AuthenticationResult fingerprintResult;
    private int mAttempts;
    /* access modifiers changed from: private */
    public final Callback mCallback;
    private CancellationSignal mCancellationSignal;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final TextView mErrorTextView;
    private final FingerprintManager mFingerprintManager;
    /* access modifiers changed from: private */
    public final ImageView mIcon;
    Runnable mResetErrorTextRunnable;
    boolean mSelfCancelled;

    public interface Callback {
        void onAuthenticated(FingerprintManager.AuthenticationResult authenticationResult);

        void onError(CharSequence charSequence);
    }

    public static class FingerprintUiHelperBuilder {
        private final Context mContext;
        private final FingerprintManager mFingerPrintManager;

        public FingerprintUiHelperBuilder(Context context, FingerprintManager fingerprintManager) {
            this.mFingerPrintManager = fingerprintManager;
            this.mContext = context;
        }

        public FingerprintUiHelper build(ImageView icon, TextView errorTextView, Callback callback) {
            return new FingerprintUiHelper(this.mContext, this.mFingerPrintManager, icon, errorTextView, callback);
        }
    }

    private FingerprintUiHelper(Context context, FingerprintManager fingerprintManager, ImageView icon, TextView errorTextView, Callback callback) {
        this.mAttempts = 0;
        this.mResetErrorTextRunnable = new Runnable() {
            public void run() {
                FingerprintUiHelper.this.mErrorTextView.setTextColor(FingerprintUiHelper.this.mErrorTextView.getResources().getColor(FingerprintUiHelper.this.mContext.getResources().getIdentifier("hint_color", PushConstants.COLOR, FingerprintAuth.packageName), (Resources.Theme) null));
                FingerprintUiHelper.this.mErrorTextView.setText(FingerprintUiHelper.this.mErrorTextView.getResources().getString(FingerprintUiHelper.this.mContext.getResources().getIdentifier("fingerprint_hint", "string", FingerprintAuth.packageName)));
                FingerprintUiHelper.this.mIcon.setImageResource(FingerprintUiHelper.this.mContext.getResources().getIdentifier("ic_fp_40px", PushConstants.DRAWABLE, FingerprintAuth.packageName));
            }
        };
        this.mFingerprintManager = fingerprintManager;
        this.mIcon = icon;
        this.mErrorTextView = errorTextView;
        this.mCallback = callback;
        this.mContext = context;
    }

    public boolean isFingerprintAuthAvailable() {
        return this.mFingerprintManager.isHardwareDetected() && this.mFingerprintManager.hasEnrolledFingerprints();
    }

    public void startListening(FingerprintManager.CryptoObject cryptoObject) {
        if (isFingerprintAuthAvailable()) {
            this.mCancellationSignal = new CancellationSignal();
            this.mSelfCancelled = false;
            this.mFingerprintManager.authenticate(cryptoObject, this.mCancellationSignal, 0, this, (Handler) null);
            this.mIcon.setImageResource(this.mContext.getResources().getIdentifier("ic_fp_40px", PushConstants.DRAWABLE, FingerprintAuth.packageName));
        }
    }

    public void stopListening() {
        if (this.mCancellationSignal != null) {
            this.mSelfCancelled = true;
            this.mCancellationSignal.cancel();
            this.mCancellationSignal = null;
        }
    }

    public void onAuthenticationError(int errMsgId, final CharSequence errString) {
        if (!this.mSelfCancelled) {
            showError(errString);
            this.mIcon.postDelayed(new Runnable() {
                public void run() {
                    FingerprintUiHelper.this.mCallback.onError(errString);
                }
            }, ERROR_TIMEOUT_MILLIS);
        }
    }

    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        showError(helpString);
    }

    public void onAuthenticationFailed() {
        this.mAttempts++;
        int fingerprint_not_recognized_id = this.mContext.getResources().getIdentifier("fingerprint_not_recognized", "string", FingerprintAuth.packageName);
        final String too_many_attempts_string = this.mIcon.getResources().getString(this.mContext.getResources().getIdentifier("fingerprint_too_many_attempts", "string", FingerprintAuth.packageName));
        if (this.mAttempts > FingerprintAuth.mMaxAttempts) {
            showError(too_many_attempts_string);
            this.mIcon.postDelayed(new Runnable() {
                public void run() {
                    FingerprintUiHelper.this.mCallback.onError(too_many_attempts_string);
                }
            }, ERROR_TIMEOUT_MILLIS);
            return;
        }
        showError(this.mIcon.getResources().getString(fingerprint_not_recognized_id));
    }

    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        fingerprintResult = result;
        this.mErrorTextView.removeCallbacks(this.mResetErrorTextRunnable);
        this.mIcon.setImageResource(this.mContext.getResources().getIdentifier("ic_fingerprint_success", PushConstants.DRAWABLE, FingerprintAuth.packageName));
        this.mErrorTextView.setTextColor(this.mErrorTextView.getResources().getColor(this.mContext.getResources().getIdentifier("success_color", PushConstants.COLOR, FingerprintAuth.packageName), (Resources.Theme) null));
        this.mErrorTextView.setText(this.mErrorTextView.getResources().getString(this.mContext.getResources().getIdentifier("fingerprint_success", "string", FingerprintAuth.packageName)));
        this.mIcon.postDelayed(new Runnable() {
            public void run() {
                FingerprintUiHelper.this.mCallback.onAuthenticated(FingerprintUiHelper.fingerprintResult);
            }
        }, SUCCESS_DELAY_MILLIS);
    }

    private void showError(CharSequence error) {
        this.mIcon.setImageResource(this.mContext.getResources().getIdentifier("ic_fingerprint_error", PushConstants.DRAWABLE, FingerprintAuth.packageName));
        this.mErrorTextView.setText(error);
        this.mErrorTextView.setTextColor(this.mErrorTextView.getResources().getColor(this.mContext.getResources().getIdentifier("warning_color", PushConstants.COLOR, FingerprintAuth.packageName), (Resources.Theme) null));
        this.mErrorTextView.removeCallbacks(this.mResetErrorTextRunnable);
        this.mErrorTextView.postDelayed(this.mResetErrorTextRunnable, ERROR_TIMEOUT_MILLIS);
    }
}
