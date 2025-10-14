package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.firebase.iid.zzav;
import com.google.firebase.iid.zzb;
import java.util.ArrayDeque;
import java.util.Queue;

public class FirebaseMessagingService extends zzb {
    private static final Queue<String> zzdv = new ArrayDeque(10);

    @WorkerThread
    public void onMessageReceived(RemoteMessage remoteMessage) {
    }

    @WorkerThread
    public void onDeletedMessages() {
    }

    @WorkerThread
    public void onMessageSent(String str) {
    }

    @WorkerThread
    public void onSendError(String str, Exception exc) {
    }

    @WorkerThread
    public void onNewToken(String str) {
    }

    /* access modifiers changed from: protected */
    public final Intent zzb(Intent intent) {
        return zzav.zzai().zzaj();
    }

    public final boolean zzc(Intent intent) {
        if (!"com.google.firebase.messaging.NOTIFICATION_OPEN".equals(intent.getAction())) {
            return false;
        }
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("pending_intent");
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                Log.e("FirebaseMessaging", "Notification pending intent canceled");
            }
        }
        if (MessagingAnalytics.shouldUploadMetrics(intent)) {
            MessagingAnalytics.logNotificationOpen(intent);
        }
        return true;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00c2, code lost:
        if (r1.equals("gcm") != false) goto L_0x0045;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzd(android.content.Intent r10) {
        /*
            r9 = this;
            r6 = 3
            r5 = 2
            r4 = 1
            r2 = 0
            java.lang.String r0 = r10.getAction()
            java.lang.String r1 = "com.google.android.c2dm.intent.RECEIVE"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0018
            java.lang.String r1 = "com.google.firebase.messaging.RECEIVE_DIRECT_BOOT"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0181
        L_0x0018:
            java.lang.String r0 = "google.message_id"
            java.lang.String r1 = r10.getStringExtra(r0)
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 == 0) goto L_0x0065
            r0 = 0
            com.google.android.gms.tasks.Task r0 = com.google.android.gms.tasks.Tasks.forResult(r0)
        L_0x0029:
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 == 0) goto L_0x0078
            r1 = r2
        L_0x0030:
            if (r1 != 0) goto L_0x005d
            java.lang.String r1 = "message_type"
            java.lang.String r1 = r10.getStringExtra(r1)
            if (r1 != 0) goto L_0x003c
            java.lang.String r1 = "gcm"
        L_0x003c:
            r3 = -1
            int r7 = r1.hashCode()
            switch(r7) {
                case -2062414158: goto L_0x00c5;
                case 102161: goto L_0x00bc;
                case 814694033: goto L_0x00db;
                case 814800675: goto L_0x00d0;
                default: goto L_0x0044;
            }
        L_0x0044:
            r2 = r3
        L_0x0045:
            switch(r2) {
                case 0: goto L_0x00e6;
                case 1: goto L_0x0123;
                case 2: goto L_0x0128;
                case 3: goto L_0x0133;
                default: goto L_0x0048;
            }
        L_0x0048:
            java.lang.String r2 = "FirebaseMessaging"
            java.lang.String r3 = "Received message with unknown type: "
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r4 = r1.length()
            if (r4 == 0) goto L_0x0151
            java.lang.String r1 = r3.concat(r1)
        L_0x005a:
            android.util.Log.w(r2, r1)
        L_0x005d:
            r2 = 1
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ ExecutionException -> 0x01c8, InterruptedException -> 0x0158, TimeoutException -> 0x01ca }
            com.google.android.gms.tasks.Tasks.await(r0, r2, r1)     // Catch:{ ExecutionException -> 0x01c8, InterruptedException -> 0x0158, TimeoutException -> 0x01ca }
        L_0x0064:
            return
        L_0x0065:
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r3 = "google.message_id"
            r0.putString(r3, r1)
            com.google.firebase.iid.zzab r3 = com.google.firebase.iid.zzab.zzc(r9)
            com.google.android.gms.tasks.Task r0 = r3.zza(r5, r0)
            goto L_0x0029
        L_0x0078:
            java.util.Queue<java.lang.String> r3 = zzdv
            boolean r3 = r3.contains(r1)
            if (r3 == 0) goto L_0x00a5
            java.lang.String r3 = "FirebaseMessaging"
            boolean r3 = android.util.Log.isLoggable(r3, r6)
            if (r3 == 0) goto L_0x009d
            java.lang.String r3 = "FirebaseMessaging"
            java.lang.String r7 = "Received duplicate message: "
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r8 = r1.length()
            if (r8 == 0) goto L_0x009f
            java.lang.String r1 = r7.concat(r1)
        L_0x009a:
            android.util.Log.d(r3, r1)
        L_0x009d:
            r1 = r4
            goto L_0x0030
        L_0x009f:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r7)
            goto L_0x009a
        L_0x00a5:
            java.util.Queue<java.lang.String> r3 = zzdv
            int r3 = r3.size()
            r7 = 10
            if (r3 < r7) goto L_0x00b4
            java.util.Queue<java.lang.String> r3 = zzdv
            r3.remove()
        L_0x00b4:
            java.util.Queue<java.lang.String> r3 = zzdv
            r3.add(r1)
            r1 = r2
            goto L_0x0030
        L_0x00bc:
            java.lang.String r4 = "gcm"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x0044
            goto L_0x0045
        L_0x00c5:
            java.lang.String r2 = "deleted_messages"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0044
            r2 = r4
            goto L_0x0045
        L_0x00d0:
            java.lang.String r2 = "send_event"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0044
            r2 = r5
            goto L_0x0045
        L_0x00db:
            java.lang.String r2 = "send_error"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0044
            r2 = r6
            goto L_0x0045
        L_0x00e6:
            boolean r1 = com.google.firebase.messaging.MessagingAnalytics.shouldUploadMetrics(r10)
            if (r1 == 0) goto L_0x00ef
            com.google.firebase.messaging.MessagingAnalytics.logNotificationReceived(r10)
        L_0x00ef:
            android.os.Bundle r1 = r10.getExtras()
            if (r1 != 0) goto L_0x00fa
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
        L_0x00fa:
            java.lang.String r2 = "android.support.content.wakelockid"
            r1.remove(r2)
            boolean r2 = com.google.android.gms.internal.firebase_messaging.zzac.zzj(r1)
            if (r2 == 0) goto L_0x0119
            com.google.firebase.messaging.zza r2 = new com.google.firebase.messaging.zza
            r2.<init>(r9, r1)
            boolean r2 = r2.zzar()
            if (r2 != 0) goto L_0x005d
            boolean r2 = com.google.firebase.messaging.MessagingAnalytics.shouldUploadMetrics(r10)
            if (r2 == 0) goto L_0x0119
            com.google.firebase.messaging.MessagingAnalytics.logNotificationForeground(r10)
        L_0x0119:
            com.google.firebase.messaging.RemoteMessage r2 = new com.google.firebase.messaging.RemoteMessage
            r2.<init>(r1)
            r9.onMessageReceived(r2)
            goto L_0x005d
        L_0x0123:
            r9.onDeletedMessages()
            goto L_0x005d
        L_0x0128:
            java.lang.String r1 = "google.message_id"
            java.lang.String r1 = r10.getStringExtra(r1)
            r9.onMessageSent(r1)
            goto L_0x005d
        L_0x0133:
            java.lang.String r1 = "google.message_id"
            java.lang.String r1 = r10.getStringExtra(r1)
            if (r1 != 0) goto L_0x0141
            java.lang.String r1 = "message_id"
            java.lang.String r1 = r10.getStringExtra(r1)
        L_0x0141:
            com.google.firebase.messaging.SendException r2 = new com.google.firebase.messaging.SendException
            java.lang.String r3 = "error"
            java.lang.String r3 = r10.getStringExtra(r3)
            r2.<init>(r3)
            r9.onSendError(r1, r2)
            goto L_0x005d
        L_0x0151:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r3)
            goto L_0x005a
        L_0x0158:
            r0 = move-exception
        L_0x0159:
            java.lang.String r1 = "FirebaseMessaging"
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r2 = java.lang.String.valueOf(r0)
            int r2 = r2.length()
            int r2 = r2 + 20
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Message ack failed: "
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            android.util.Log.w(r1, r0)
            goto L_0x0064
        L_0x0181:
            java.lang.String r1 = "com.google.firebase.messaging.NOTIFICATION_DISMISS"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0194
            boolean r0 = com.google.firebase.messaging.MessagingAnalytics.shouldUploadMetrics(r10)
            if (r0 == 0) goto L_0x0064
            com.google.firebase.messaging.MessagingAnalytics.logNotificationDismiss(r10)
            goto L_0x0064
        L_0x0194:
            java.lang.String r1 = "com.google.firebase.messaging.NEW_TOKEN"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x01a7
            java.lang.String r0 = "token"
            java.lang.String r0 = r10.getStringExtra(r0)
            r9.onNewToken(r0)
            goto L_0x0064
        L_0x01a7:
            java.lang.String r1 = "FirebaseMessaging"
            java.lang.String r2 = "Unknown intent action: "
            java.lang.String r0 = r10.getAction()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r3 = r0.length()
            if (r3 == 0) goto L_0x01c2
            java.lang.String r0 = r2.concat(r0)
        L_0x01bd:
            android.util.Log.d(r1, r0)
            goto L_0x0064
        L_0x01c2:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r2)
            goto L_0x01bd
        L_0x01c8:
            r0 = move-exception
            goto L_0x0159
        L_0x01ca:
            r0 = move-exception
            goto L_0x0159
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.FirebaseMessagingService.zzd(android.content.Intent):void");
    }
}
