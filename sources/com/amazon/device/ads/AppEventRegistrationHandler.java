package com.amazon.device.ads;

import com.amazon.device.ads.FileOutputHandler;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

class AppEventRegistrationHandler {
    protected static final String APP_EVENTS_FILE = "AppEventsJsonFile";
    protected static final long APP_EVENTS_FILE_MAX_SIZE = 1048576;
    protected static final String APP_EVENT_NAME_KEY = "evtName";
    protected static final String APP_EVENT_TIMESTAMP_KEY = "ts";
    protected static final String INSTALL_REFERRER_EVENT_NAME = "INSTALL_REFERRER";
    private static final String LOGTAG = AppEventRegistrationHandler.class.getSimpleName();
    protected static AppEventRegistrationHandler instance = new AppEventRegistrationHandler(MobileAdsInfoStore.getInstance(), new DefaultFileHandlerFactory());
    protected final Object appEventsFileLock;
    protected final Set<String> eventsSent;
    private final FileHandlerFactory fileHandlerFactory;
    private FileInputHandler fileInputHandler;
    private FileOutputHandler fileOutputHandler;
    /* access modifiers changed from: private */
    public final MobileAdsInfoStore infoStore;
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
    protected final Set<String> newEventsToSave;

    protected AppEventRegistrationHandler(MobileAdsInfoStore infoStore2, FileHandlerFactory fileHandlerFactory2) {
        this.infoStore = infoStore2;
        this.fileHandlerFactory = fileHandlerFactory2;
        this.appEventsFileLock = new Object();
        this.newEventsToSave = Collections.synchronizedSet(new HashSet());
        this.eventsSent = Collections.synchronizedSet(new HashSet());
    }

    public static AppEventRegistrationHandler getInstance() {
        return instance;
    }

    public void addEventToAppEventsCacheFile(final AppEvent appEvent) {
        ThreadUtils.scheduleRunnable(new Runnable() {
            public void run() {
                AppEventRegistrationHandler.this.appendAppEventToFile(appEvent);
                if (appEvent.getEventName().equals(AppEventRegistrationHandler.INSTALL_REFERRER_EVENT_NAME) && AppEventRegistrationHandler.this.infoStore.getRegistrationInfo().isRegisteredWithSIS()) {
                    AppEventRegistrationHandler.this.infoStore.getSISRegistration().registerEvents();
                }
            }
        });
    }

    private boolean createFileOutputHandlerIfNeeded() {
        if (this.fileOutputHandler == null) {
            File fileDir = this.infoStore.getFilesDir();
            if (fileDir == null) {
                this.logger.e("No files directory has been set.");
                return false;
            }
            this.fileOutputHandler = this.fileHandlerFactory.createFileOutputHandler(fileDir, APP_EVENTS_FILE);
        }
        if (this.fileOutputHandler != null) {
            return true;
        }
        return false;
    }

    private boolean createFileInputHandlerIfNeeded() {
        if (this.fileInputHandler == null) {
            File fileDir = this.infoStore.getFilesDir();
            if (fileDir == null) {
                this.logger.e("No files directory has been set.");
                return false;
            }
            this.fileInputHandler = this.fileHandlerFactory.createFileInputHandler(fileDir, APP_EVENTS_FILE);
        }
        if (this.fileInputHandler != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void appendAppEventToFile(AppEvent appEvent) {
        if (!createFileOutputHandlerIfNeeded()) {
            this.logger.e("Error creating file output handler.");
            return;
        }
        JSONObject jsonEvent = new JSONObject();
        try {
            jsonEvent.put(APP_EVENT_NAME_KEY, appEvent.getEventName());
            jsonEvent.put("ts", appEvent.getTimestamp());
            for (Map.Entry<String, String> prop : appEvent.getPropertyEntries()) {
                jsonEvent.put(prop.getKey(), prop.getValue());
            }
            this.newEventsToSave.add(jsonEvent.toString());
            synchronized (this.appEventsFileLock) {
                String toAppend = jsonEvent.toString() + "\n";
                if (this.fileOutputHandler.getFileLength() + ((long) toAppend.length()) > 1048576) {
                    this.logger.w("Couldn't write the application event %s to the cache file. Maximum size limit reached.", appEvent.toString());
                    return;
                }
                if (this.fileOutputHandler.open(FileOutputHandler.WriteMethod.APPEND)) {
                    try {
                        this.fileOutputHandler.write(toAppend);
                        this.logger.d("Added the application event %s to the cache file.", appEvent.toString());
                    } catch (IOException e) {
                        this.logger.w("Couldn't write the application event %s to the file.", appEvent.toString());
                    }
                }
                this.fileOutputHandler.close();
            }
        } catch (JSONException e2) {
            this.logger.w("Internal error while persisting the application event %s.", appEvent.toString());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r7.fileInputHandler.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0068, code lost:
        if (r0.length() <= 0) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONArray getAppEventsJSONArray() {
        /*
            r7 = this;
            r3 = 0
            boolean r4 = r7.createFileInputHandlerIfNeeded()
            if (r4 != 0) goto L_0x0010
            com.amazon.device.ads.MobileAdsLogger r4 = r7.logger
            java.lang.String r5 = "Error creating file input handler."
            r4.e(r5)
            r0 = r3
        L_0x000f:
            return r0
        L_0x0010:
            java.lang.Object r4 = r7.appEventsFileLock
            monitor-enter(r4)
            com.amazon.device.ads.FileInputHandler r5 = r7.fileInputHandler     // Catch:{ all -> 0x005c }
            boolean r5 = r5.doesFileExist()     // Catch:{ all -> 0x005c }
            if (r5 != 0) goto L_0x001e
            monitor-exit(r4)     // Catch:{ all -> 0x005c }
            r0 = r3
            goto L_0x000f
        L_0x001e:
            com.amazon.device.ads.FileInputHandler r5 = r7.fileInputHandler     // Catch:{ all -> 0x005c }
            boolean r5 = r5.open()     // Catch:{ all -> 0x005c }
            if (r5 != 0) goto L_0x0030
            com.amazon.device.ads.MobileAdsLogger r5 = r7.logger     // Catch:{ all -> 0x005c }
            java.lang.String r6 = "App Events File could not be opened."
            r5.e(r6)     // Catch:{ all -> 0x005c }
            monitor-exit(r4)     // Catch:{ all -> 0x005c }
            r0 = r3
            goto L_0x000f
        L_0x0030:
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ all -> 0x005c }
            r0.<init>()     // Catch:{ all -> 0x005c }
            r2 = 0
        L_0x0036:
            com.amazon.device.ads.FileInputHandler r5 = r7.fileInputHandler     // Catch:{ all -> 0x005c }
            java.lang.String r2 = r5.readLine()     // Catch:{ all -> 0x005c }
            if (r2 == 0) goto L_0x005f
            org.json.JSONObject r1 = com.amazon.device.ads.JSONUtils.getJSONObjectFromString(r2)     // Catch:{ all -> 0x005c }
            if (r1 != 0) goto L_0x004f
            r7.onAppEventsRegistered()     // Catch:{ all -> 0x005c }
            com.amazon.device.ads.FileInputHandler r5 = r7.fileInputHandler     // Catch:{ all -> 0x005c }
            r5.close()     // Catch:{ all -> 0x005c }
            monitor-exit(r4)     // Catch:{ all -> 0x005c }
            r0 = r3
            goto L_0x000f
        L_0x004f:
            r0.put(r1)     // Catch:{ all -> 0x005c }
            java.util.Set<java.lang.String> r5 = r7.eventsSent     // Catch:{ all -> 0x005c }
            java.lang.String r6 = r1.toString()     // Catch:{ all -> 0x005c }
            r5.add(r6)     // Catch:{ all -> 0x005c }
            goto L_0x0036
        L_0x005c:
            r3 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x005c }
            throw r3
        L_0x005f:
            com.amazon.device.ads.FileInputHandler r5 = r7.fileInputHandler     // Catch:{ all -> 0x005c }
            r5.close()     // Catch:{ all -> 0x005c }
            int r5 = r0.length()     // Catch:{ all -> 0x005c }
            if (r5 <= 0) goto L_0x006c
            monitor-exit(r4)     // Catch:{ all -> 0x005c }
            goto L_0x000f
        L_0x006c:
            monitor-exit(r4)     // Catch:{ all -> 0x005c }
            r0 = r3
            goto L_0x000f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.device.ads.AppEventRegistrationHandler.getAppEventsJSONArray():org.json.JSONArray");
    }

    public void onAppEventsRegistered() {
        if (!createFileOutputHandlerIfNeeded()) {
            this.logger.e("Error creating file output handler.");
            return;
        }
        synchronized (this.appEventsFileLock) {
            this.newEventsToSave.removeAll(this.eventsSent);
            if (!this.newEventsToSave.isEmpty()) {
                StringBuilder toWrite = new StringBuilder();
                synchronized (this.newEventsToSave) {
                    for (String newEvent : this.newEventsToSave) {
                        toWrite.append(newEvent).append("\n");
                    }
                }
                if (this.fileOutputHandler.open(FileOutputHandler.WriteMethod.APPEND)) {
                    try {
                        this.fileOutputHandler.write(toWrite.toString());
                        this.newEventsToSave.clear();
                        this.eventsSent.clear();
                    } catch (IOException e) {
                        this.logger.w("Couldn't write the application event(s) to the file.");
                    }
                }
                this.fileOutputHandler.close();
            } else {
                this.infoStore.getApplicationContext().deleteFile(APP_EVENTS_FILE);
                this.eventsSent.clear();
            }
        }
    }
}
