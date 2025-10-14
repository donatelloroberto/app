package acidhax.cordova.chromecast;

public abstract class ChromecastSessionCallback {
    /* access modifiers changed from: package-private */
    public abstract void onError(String str);

    /* access modifiers changed from: package-private */
    public abstract void onSuccess(Object obj);

    public void onSuccess() {
        onSuccess((Object) null);
    }
}
