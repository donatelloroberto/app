package com.cordova.plugin.android.fingerprintauth;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import com.cordova.plugin.android.fingerprintauth.FingerprintAuthenticationDialogFragment;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(23)
public class FingerprintAuth extends CordovaPlugin {
    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";
    private static final String CREDENTIAL_DELIMITER = "|:|";
    private static final String DIALOG_FRAGMENT_TAG = "FpAuthDialog";
    public static final String FINGERPRINT_PREF_IV = "aes_iv";
    private static final int PERMISSIONS_REQUEST_FINGERPRINT = 346437;
    private static final int REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS = 1;
    public static final String TAG = "FingerprintAuth";
    public static Activity mActivity;
    public static CallbackContext mCallbackContext;
    public static Cipher mCipher;
    private static boolean mCipherModeCrypt = true;
    private static String mClientId;
    private static String mClientSecret;
    public static Context mContext;
    public static String mDialogHint;
    public static String mDialogMessage;
    public static String mDialogTitle;
    public static boolean mDisableBackup = false;
    public static KeyGenerator mKeyGenerator;
    public static KeyStore mKeyStore;
    public static int mMaxAttempts = 6;
    public static PluginResult mPluginResult;
    private static boolean mUserAuthRequired = false;
    private static String mUsername = "";
    public static String packageName;
    public PluginAction mAction;
    public boolean mEncryptNoAuth = false;
    private FingerprintManager mFingerPrintManager;
    public FingerprintAuthenticationDialogFragment mFragment;
    public KeyguardManager mKeyguardManager;
    private String mLangCode = "en_US";

    public enum PluginAction {
        AVAILABILITY,
        ENCRYPT,
        DECRYPT,
        DELETE,
        DISMISS
    }

    public enum PluginError {
        BAD_PADDING_EXCEPTION,
        CERTIFICATE_EXCEPTION,
        FINGERPRINT_CANCELLED,
        FINGERPRINT_DATA_NOT_DELETED,
        FINGERPRINT_ERROR,
        FINGERPRINT_NOT_AVAILABLE,
        FINGERPRINT_PERMISSION_DENIED,
        FINGERPRINT_PERMISSION_DENIED_SHOW_REQUEST,
        ILLEGAL_BLOCK_SIZE_EXCEPTION,
        INIT_CIPHER_FAILED,
        INVALID_ALGORITHM_PARAMETER_EXCEPTION,
        IO_EXCEPTION,
        JSON_EXCEPTION,
        MINIMUM_SDK,
        MISSING_ACTION_PARAMETERS,
        MISSING_PARAMETERS,
        NO_SUCH_ALGORITHM_EXCEPTION,
        SECURITY_EXCEPTION,
        FRAGMENT_NOT_EXIST
    }

    public void initialize(CordovaInterface cordova2, CordovaWebView webView) {
        super.initialize(cordova2, webView);
        Log.v(TAG, "Init FingerprintAuth");
        packageName = cordova2.getActivity().getApplicationContext().getPackageName();
        mPluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
        mActivity = cordova2.getActivity();
        mContext = cordova2.getActivity().getApplicationContext();
        if (Build.VERSION.SDK_INT >= 23) {
            this.mKeyguardManager = (KeyguardManager) cordova2.getActivity().getSystemService(KeyguardManager.class);
            this.mFingerPrintManager = (FingerprintManager) cordova2.getActivity().getApplicationContext().getSystemService(FingerprintManager.class);
            try {
                mKeyGenerator = KeyGenerator.getInstance("AES", ANDROID_KEY_STORE);
                mKeyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
                try {
                    mCipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException("Failed to get an instance of Cipher", e);
                } catch (NoSuchPaddingException e2) {
                    throw new RuntimeException("Failed to get an instance of Cipher", e2);
                }
            } catch (NoSuchAlgorithmException e3) {
                throw new RuntimeException("Failed to get an instance of KeyGenerator", e3);
            } catch (NoSuchProviderException e4) {
                throw new RuntimeException("Failed to get an instance of KeyGenerator", e4);
            } catch (KeyStoreException e5) {
                throw new RuntimeException("Failed to get an instance of KeyStore", e5);
            }
        }
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        int maxAttempts;
        mCallbackContext = callbackContext;
        if (Build.VERSION.SDK_INT < 23) {
            Log.e(TAG, "minimum SDK version 23 required");
            mPluginResult = new PluginResult(PluginResult.Status.ERROR);
            mCallbackContext.error(PluginError.MINIMUM_SDK.name());
            mCallbackContext.sendPluginResult(mPluginResult);
            return true;
        }
        Log.v(TAG, "FingerprintAuth action: " + action);
        if (action.equals("availability")) {
            this.mAction = PluginAction.AVAILABILITY;
        } else if (action.equals("encrypt")) {
            this.mAction = PluginAction.ENCRYPT;
            mCipherModeCrypt = true;
        } else if (action.equals("decrypt")) {
            this.mAction = PluginAction.DECRYPT;
            mCipherModeCrypt = false;
        } else if (action.equals("delete")) {
            this.mAction = PluginAction.DELETE;
        } else if (action.equals("dismiss")) {
            this.mAction = PluginAction.DISMISS;
        }
        if (this.mAction != null) {
            JSONObject arg_object = args.getJSONObject(0);
            new JSONObject();
            if (!(this.mAction == PluginAction.AVAILABILITY || this.mAction == PluginAction.DISMISS)) {
                if (!arg_object.has("clientId")) {
                    Log.e(TAG, "Missing required parameters.");
                    mPluginResult = new PluginResult(PluginResult.Status.ERROR);
                    mCallbackContext.error(PluginError.MISSING_PARAMETERS.name());
                    mCallbackContext.sendPluginResult(mPluginResult);
                    return true;
                }
                mClientId = arg_object.getString("clientId");
                if (arg_object.has("username")) {
                    mUsername = arg_object.getString("username");
                }
            }
            switch (this.mAction) {
                case ENCRYPT:
                case DECRYPT:
                    boolean missingParam = false;
                    this.mEncryptNoAuth = false;
                    switch (this.mAction) {
                        case ENCRYPT:
                            String password = "";
                            if (arg_object.has("password")) {
                                password = arg_object.getString("password");
                            }
                            mClientSecret = mClientId + mUsername + CREDENTIAL_DELIMITER + password;
                            if (arg_object.has("encryptNoAuth")) {
                                this.mEncryptNoAuth = arg_object.getBoolean("encryptNoAuth");
                                break;
                            }
                            break;
                        case DECRYPT:
                            if (!arg_object.has("token")) {
                                missingParam = true;
                                break;
                            } else {
                                mClientSecret = arg_object.getString("token");
                                break;
                            }
                    }
                    if (missingParam) {
                        Log.e(TAG, "Missing required parameters for specified action.");
                        mPluginResult = new PluginResult(PluginResult.Status.ERROR);
                        mCallbackContext.error(PluginError.MISSING_ACTION_PARAMETERS.name());
                        mCallbackContext.sendPluginResult(mPluginResult);
                        return true;
                    }
                    if (arg_object.has("disableBackup")) {
                        mDisableBackup = arg_object.getBoolean("disableBackup");
                    }
                    if (arg_object.has("locale")) {
                        this.mLangCode = arg_object.getString("locale");
                        Log.d(TAG, "Change language to locale: " + this.mLangCode);
                    }
                    if (arg_object.has("maxAttempts") && (maxAttempts = arg_object.getInt("maxAttempts")) < 5) {
                        mMaxAttempts = maxAttempts;
                    }
                    if (arg_object.has("userAuthRequired")) {
                        mUserAuthRequired = arg_object.getBoolean("userAuthRequired");
                    }
                    if (arg_object.has("dialogTitle")) {
                        mDialogTitle = arg_object.getString("dialogTitle");
                    }
                    if (arg_object.has("dialogMessage")) {
                        mDialogMessage = arg_object.getString("dialogMessage");
                    }
                    if (arg_object.has("dialogHint")) {
                        mDialogHint = arg_object.getString("dialogHint");
                    }
                    Resources res = this.f5cordova.getActivity().getResources();
                    DisplayMetrics dm = res.getDisplayMetrics();
                    Configuration conf = res.getConfiguration();
                    if (this.mLangCode.length() == 5) {
                        conf.locale = new Locale(this.mLangCode.substring(0, 2).toLowerCase(), this.mLangCode.substring(this.mLangCode.length() - 2).toUpperCase());
                    } else {
                        conf.locale = new Locale(this.mLangCode.toLowerCase());
                    }
                    res.updateConfiguration(conf, dm);
                    SecretKey key = getSecretKey();
                    if (key == null && createKey()) {
                        key = getSecretKey();
                    }
                    if (key == null) {
                        mCallbackContext.sendPluginResult(mPluginResult);
                    } else if (this.mEncryptNoAuth) {
                        onAuthenticated(false, (FingerprintManager.AuthenticationResult) null);
                    } else if (isFingerprintAuthAvailable()) {
                        this.f5cordova.getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                FingerprintAuth.this.mFragment = new FingerprintAuthenticationDialogFragment();
                                if (FingerprintAuth.initCipher()) {
                                    FingerprintAuth.this.mFragment.setCancelable(false);
                                    FingerprintAuth.this.mFragment.setCryptoObject(new FingerprintManager.CryptoObject(FingerprintAuth.mCipher));
                                    FragmentTransaction transaction = FingerprintAuth.this.f5cordova.getActivity().getFragmentManager().beginTransaction();
                                    transaction.add(FingerprintAuth.this.mFragment, FingerprintAuth.DIALOG_FRAGMENT_TAG);
                                    transaction.commitAllowingStateLoss();
                                } else if (!FingerprintAuth.mDisableBackup) {
                                    FingerprintAuth.this.mFragment.setCryptoObject(new FingerprintManager.CryptoObject(FingerprintAuth.mCipher));
                                    FingerprintAuth.this.mFragment.setStage(FingerprintAuthenticationDialogFragment.Stage.NEW_FINGERPRINT_ENROLLED);
                                    FragmentTransaction transaction2 = FingerprintAuth.this.f5cordova.getActivity().getFragmentManager().beginTransaction();
                                    transaction2.add(FingerprintAuth.this.mFragment, FingerprintAuth.DIALOG_FRAGMENT_TAG);
                                    transaction2.commitAllowingStateLoss();
                                } else {
                                    Log.e(FingerprintAuth.TAG, "Failed to init Cipher and backup disabled.");
                                    FingerprintAuth.mCallbackContext.error(PluginError.INIT_CIPHER_FAILED.name());
                                    FingerprintAuth.mPluginResult = new PluginResult(PluginResult.Status.ERROR);
                                    FingerprintAuth.mCallbackContext.sendPluginResult(FingerprintAuth.mPluginResult);
                                }
                            }
                        });
                        mPluginResult.setKeepCallback(true);
                    } else {
                        Log.v(TAG, "In backup");
                        if (useBackupLockScreen()) {
                            Log.v(TAG, "useBackupLockScreen: true");
                        } else {
                            Log.v(TAG, "useBackupLockScreen: false");
                        }
                        if (useBackupLockScreen()) {
                            showAuthenticationScreen();
                        } else {
                            Log.e(TAG, "Fingerprint authentication not available");
                            mPluginResult = new PluginResult(PluginResult.Status.ERROR);
                            mCallbackContext.error(PluginError.FINGERPRINT_NOT_AVAILABLE.name());
                            mCallbackContext.sendPluginResult(mPluginResult);
                        }
                    }
                    return true;
                case AVAILABILITY:
                    if (!this.f5cordova.hasPermission("android.permission.USE_FINGERPRINT")) {
                        this.f5cordova.requestPermission(this, PERMISSIONS_REQUEST_FINGERPRINT, "android.permission.USE_FINGERPRINT");
                    } else {
                        sendAvailabilityResult();
                    }
                    return true;
                case DELETE:
                    boolean ivDeleted = false;
                    boolean secretKeyDeleted = false;
                    try {
                        mKeyStore.load((KeyStore.LoadStoreParameter) null);
                        mKeyStore.deleteEntry(mClientId);
                        secretKeyDeleted = true;
                        ivDeleted = deleteIV();
                    } catch (KeyStoreException e) {
                        Log.e(TAG, "Error while deleting SecretKey.", e);
                    } catch (CertificateException e2) {
                        Log.e(TAG, "Error while deleting SecretKey.", e2);
                    } catch (NoSuchAlgorithmException e3) {
                        Log.e(TAG, "Error while deleting SecretKey.", e3);
                    } catch (IOException e4) {
                        Log.e(TAG, "Error while deleting SecretKey.", e4);
                    }
                    if (!ivDeleted || !secretKeyDeleted) {
                        Log.e(TAG, "Error while deleting Fingerprint data.");
                        mPluginResult = new PluginResult(PluginResult.Status.ERROR);
                        mCallbackContext.error(PluginError.FINGERPRINT_DATA_NOT_DELETED.name());
                    } else {
                        mPluginResult = new PluginResult(PluginResult.Status.OK);
                        mCallbackContext.success();
                    }
                    mCallbackContext.sendPluginResult(mPluginResult);
                    return true;
                case DISMISS:
                    if (this.mFragment != null) {
                        this.f5cordova.getActivity().getFragmentManager().beginTransaction().remove(this.mFragment).commit();
                        mPluginResult = new PluginResult(PluginResult.Status.OK);
                        mCallbackContext.success("Fragment dismissed");
                        mCallbackContext.sendPluginResult(mPluginResult);
                    } else {
                        setPluginResultError(PluginError.FRAGMENT_NOT_EXIST.name());
                    }
                    return true;
            }
        }
        return false;
    }

    private boolean isFingerprintAuthAvailable() throws SecurityException {
        return this.mFingerPrintManager.isHardwareDetected() && this.mFingerPrintManager.hasEnrolledFingerprints();
    }

    private void sendAvailabilityResult() {
        String errorMessage = null;
        JSONObject resultJson = new JSONObject();
        try {
            resultJson.put("isAvailable", isFingerprintAuthAvailable());
            resultJson.put("isHardwareDetected", this.mFingerPrintManager.isHardwareDetected());
            resultJson.put("hasEnrolledFingerprints", this.mFingerPrintManager.hasEnrolledFingerprints());
            mPluginResult = new PluginResult(PluginResult.Status.OK);
            mCallbackContext.success(resultJson);
            mCallbackContext.sendPluginResult(mPluginResult);
        } catch (JSONException e) {
            Log.e(TAG, "Availability Result Error: JSONException: " + e.toString());
            errorMessage = PluginError.JSON_EXCEPTION.name();
        } catch (SecurityException e2) {
            Log.e(TAG, "Availability Result Error: SecurityException: " + e2.toString());
            errorMessage = PluginError.SECURITY_EXCEPTION.name();
        }
        if (errorMessage != null) {
            Log.e(TAG, errorMessage);
            setPluginResultError(errorMessage);
        }
    }

    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) throws JSONException {
        super.onRequestPermissionResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_FINGERPRINT /*346437*/:
                if (grantResults.length <= 0 || grantResults[0] != 0) {
                    Log.e(TAG, "Fingerprint permission denied.");
                    setPluginResultError(PluginError.FINGERPRINT_PERMISSION_DENIED.name());
                    return;
                }
                sendAvailabilityResult();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public static boolean initCipher() {
        boolean initCipher = false;
        String errorMessage = "";
        try {
            SecretKey key = getSecretKey();
            if (mCipherModeCrypt) {
                mCipher.init(1, key);
                setStringPreference(mContext, mClientId + mUsername, FINGERPRINT_PREF_IV, new String(Base64.encode(mCipher.getIV(), 2)));
            } else {
                mCipher.init(2, key, new IvParameterSpec(Base64.decode(getStringPreference(mContext, mClientId + mUsername, FINGERPRINT_PREF_IV), 2)));
            }
            initCipher = true;
        } catch (Exception e) {
            errorMessage = "Failed to init Cipher: " + "Exception: " + e.toString();
        }
        if (!initCipher) {
            Log.e(TAG, errorMessage);
        }
        return initCipher;
    }

    public static boolean deleteIV() {
        return deleteStringPreference(mContext, mClientId + mUsername, FINGERPRINT_PREF_IV);
    }

    /* JADX WARNING: type inference failed for: r5v23, types: [java.security.Key] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static javax.crypto.SecretKey getSecretKey() {
        /*
            java.lang.String r2 = ""
            java.lang.String r3 = "Failed to get SecretKey from KeyStore: "
            r4 = 0
            java.security.KeyStore r5 = mKeyStore     // Catch:{ KeyStoreException -> 0x0020, CertificateException -> 0x003d, UnrecoverableKeyException -> 0x005a, IOException -> 0x0077, NoSuchAlgorithmException -> 0x0094 }
            r6 = 0
            r5.load(r6)     // Catch:{ KeyStoreException -> 0x0020, CertificateException -> 0x003d, UnrecoverableKeyException -> 0x005a, IOException -> 0x0077, NoSuchAlgorithmException -> 0x0094 }
            java.security.KeyStore r5 = mKeyStore     // Catch:{ KeyStoreException -> 0x0020, CertificateException -> 0x003d, UnrecoverableKeyException -> 0x005a, IOException -> 0x0077, NoSuchAlgorithmException -> 0x0094 }
            java.lang.String r6 = mClientId     // Catch:{ KeyStoreException -> 0x0020, CertificateException -> 0x003d, UnrecoverableKeyException -> 0x005a, IOException -> 0x0077, NoSuchAlgorithmException -> 0x0094 }
            r7 = 0
            java.security.Key r5 = r5.getKey(r6, r7)     // Catch:{ KeyStoreException -> 0x0020, CertificateException -> 0x003d, UnrecoverableKeyException -> 0x005a, IOException -> 0x0077, NoSuchAlgorithmException -> 0x0094 }
            r0 = r5
            javax.crypto.SecretKey r0 = (javax.crypto.SecretKey) r0     // Catch:{ KeyStoreException -> 0x0020, CertificateException -> 0x003d, UnrecoverableKeyException -> 0x005a, IOException -> 0x0077, NoSuchAlgorithmException -> 0x0094 }
            r4 = r0
        L_0x0018:
            if (r4 != 0) goto L_0x001f
            java.lang.String r5 = "FingerprintAuth"
            android.util.Log.e(r5, r2)
        L_0x001f:
            return r4
        L_0x0020:
            r1 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.StringBuilder r5 = r5.append(r3)
            java.lang.String r6 = "KeyStoreException: "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = r1.toString()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r2 = r5.toString()
            goto L_0x0018
        L_0x003d:
            r1 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.StringBuilder r5 = r5.append(r3)
            java.lang.String r6 = "CertificateException: "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = r1.toString()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r2 = r5.toString()
            goto L_0x0018
        L_0x005a:
            r1 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.StringBuilder r5 = r5.append(r3)
            java.lang.String r6 = "UnrecoverableKeyException: "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = r1.toString()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r2 = r5.toString()
            goto L_0x0018
        L_0x0077:
            r1 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.StringBuilder r5 = r5.append(r3)
            java.lang.String r6 = "IOException: "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = r1.toString()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r2 = r5.toString()
            goto L_0x0018
        L_0x0094:
            r1 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.StringBuilder r5 = r5.append(r3)
            java.lang.String r6 = "NoSuchAlgorithmException: "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = r1.toString()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r2 = r5.toString()
            goto L_0x0018
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cordova.plugin.android.fingerprintauth.FingerprintAuth.getSecretKey():javax.crypto.SecretKey");
    }

    public static boolean createKey() {
        String errorMessage = "";
        boolean isKeyCreated = false;
        try {
            mKeyStore.load((KeyStore.LoadStoreParameter) null);
            mKeyGenerator.init(new KeyGenParameterSpec.Builder(mClientId, 3).setBlockModes(new String[]{"CBC"}).setUserAuthenticationRequired(mUserAuthRequired).setEncryptionPaddings(new String[]{"PKCS7Padding"}).build());
            mKeyGenerator.generateKey();
            isKeyCreated = true;
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "Failed to create key: " + "NoSuchAlgorithmException: " + e.toString());
            errorMessage = PluginError.NO_SUCH_ALGORITHM_EXCEPTION.name();
        } catch (InvalidAlgorithmParameterException e2) {
            Log.e(TAG, "Failed to create key: " + "InvalidAlgorithmParameterException: " + e2.toString());
            errorMessage = PluginError.INVALID_ALGORITHM_PARAMETER_EXCEPTION.name();
        } catch (CertificateException e3) {
            Log.e(TAG, "Failed to create key: " + "CertificateException: " + e3.toString());
            errorMessage = PluginError.CERTIFICATE_EXCEPTION.name();
        } catch (IOException e4) {
            Log.e(TAG, "Failed to create key: " + "IOException: " + e4.toString());
            errorMessage = PluginError.IO_EXCEPTION.name();
        }
        if (!isKeyCreated) {
            Log.e(TAG, errorMessage);
            setPluginResultError(errorMessage);
        }
        return isKeyCreated;
    }

    public static void onAuthenticated(boolean withFingerprint, FingerprintManager.AuthenticationResult result) {
        JSONObject resultJson = new JSONObject();
        String errorMessage = "";
        boolean createdResultJson = false;
        FingerprintManager.CryptoObject cryptoObject = null;
        if (withFingerprint) {
            try {
                resultJson.put("withFingerprint", true);
                cryptoObject = result.getCryptoObject();
            } catch (BadPaddingException e) {
                Log.e(TAG, "Failed to encrypt the data with the generated key: BadPaddingException:  " + e.toString());
                errorMessage = PluginError.BAD_PADDING_EXCEPTION.name();
            } catch (IllegalBlockSizeException e2) {
                Log.e(TAG, "Failed to encrypt the data with the generated key: IllegalBlockSizeException: " + e2.toString());
                errorMessage = PluginError.ILLEGAL_BLOCK_SIZE_EXCEPTION.name();
            } catch (JSONException e3) {
                Log.e(TAG, "Failed to set resultJson key value pair: " + e3.toString());
                errorMessage = PluginError.JSON_EXCEPTION.name();
            } catch (UnsupportedEncodingException e4) {
                e4.printStackTrace();
            }
        } else {
            resultJson.put("withBackup", true);
            if (!initCipher()) {
                createKey();
            }
            if (initCipher()) {
                cryptoObject = new FingerprintManager.CryptoObject(mCipher);
            }
        }
        if (cryptoObject == null) {
            errorMessage = PluginError.INIT_CIPHER_FAILED.name();
        } else {
            if (mCipherModeCrypt) {
                resultJson.put("token", Base64.encodeToString(cryptoObject.getCipher().doFinal(mClientSecret.getBytes(WebRequest.CHARSET_UTF_8)), 2));
            } else {
                String credentialString = new String(cryptoObject.getCipher().doFinal(Base64.decode(mClientSecret, 2)), WebRequest.CHARSET_UTF_8);
                String[] credentialArray = Pattern.compile(Pattern.quote(CREDENTIAL_DELIMITER)).split(credentialString);
                if (credentialArray.length == 2) {
                    String username = credentialArray[0];
                    String str = credentialArray[1];
                    if (username.equalsIgnoreCase(mClientId + mUsername)) {
                        resultJson.put("password", credentialArray[1]);
                    }
                } else {
                    String[] credentialArray2 = credentialString.split(":");
                    if (credentialArray2.length == 2) {
                        String username2 = credentialArray2[0];
                        String str2 = credentialArray2[1];
                        if (username2.equalsIgnoreCase(mClientId + mUsername)) {
                            resultJson.put("password", credentialArray2[1]);
                        }
                    }
                }
            }
            createdResultJson = true;
        }
        if (createdResultJson) {
            mCallbackContext.success(resultJson);
            mPluginResult = new PluginResult(PluginResult.Status.OK);
        } else {
            mCallbackContext.error(errorMessage);
            mPluginResult = new PluginResult(PluginResult.Status.ERROR);
        }
        mCallbackContext.sendPluginResult(mPluginResult);
    }

    public static void onCancelled() {
        mCallbackContext.error(PluginError.FINGERPRINT_CANCELLED.name());
    }

    public static void onError(CharSequence errString) {
        mCallbackContext.error(PluginError.FINGERPRINT_ERROR.name());
        Log.e(TAG, errString.toString());
    }

    public static boolean setPluginResultError(String errorMessage) {
        mCallbackContext.error(errorMessage);
        mPluginResult = new PluginResult(PluginResult.Status.ERROR);
        return false;
    }

    public static String getStringPreference(Context context, String name, String key) {
        return context.getSharedPreferences(name, 0).getString(key, (String) null);
    }

    public static void setStringPreference(Context context, String name, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(name, 0).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static boolean deleteStringPreference(Context context, String name, String key) {
        return context.getSharedPreferences(name, 0).edit().remove(key).commit();
    }

    private boolean useBackupLockScreen() {
        if (!this.mKeyguardManager.isKeyguardSecure()) {
            return false;
        }
        return true;
    }

    private void showAuthenticationScreen() {
        Intent intent = this.mKeyguardManager.createConfirmDeviceCredentialIntent((CharSequence) null, (CharSequence) null);
        if (intent != null) {
            this.f5cordova.setActivityResultCallback(this);
            this.f5cordova.getActivity().startActivityForResult(intent, 1);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            this.f5cordova.getActivity();
            if (resultCode == -1) {
                onAuthenticated(false, (FingerprintManager.AuthenticationResult) null);
            } else {
                onCancelled();
            }
        }
    }
}
