package kotlin.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a&\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\b¢\u0006\u0002\u0010\u0005\u001a&\u0010\u0006\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00072\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\b¢\u0006\u0002\u0010\b\u001a&\u0010\t\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\b¢\u0006\u0002\u0010\u0005¨\u0006\n"}, d2 = {"read", "T", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "action", "Lkotlin/Function0;", "(Ljava/util/concurrent/locks/ReentrantReadWriteLock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withLock", "Ljava/util/concurrent/locks/Lock;", "(Ljava/util/concurrent/locks/Lock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "write", "kotlin-stdlib"}, k = 2, mv = {1, 1, 10})
@JvmName(name = "LocksKt")
/* compiled from: Locks.kt */
public final class LocksKt {
    @InlineOnly
    private static final <T> T withLock(@NotNull Lock $receiver, Function0<? extends T> action) {
        $receiver.lock();
        try {
            return action.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            $receiver.unlock();
            InlineMarker.finallyEnd(1);
        }
    }

    @InlineOnly
    private static final <T> T read(@NotNull ReentrantReadWriteLock $receiver, Function0<? extends T> action) {
        ReentrantReadWriteLock.ReadLock rl = $receiver.readLock();
        rl.lock();
        try {
            return action.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            rl.unlock();
            InlineMarker.finallyEnd(1);
        }
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    @kotlin.internal.InlineOnly
    private static final <T> T write(@org.jetbrains.annotations.NotNull java.util.concurrent.locks.ReentrantReadWriteLock r7, kotlin.jvm.functions.Function0<? extends T> r8) {
        /*
            r6 = 1
            r4 = 0
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r2 = r7.readLock()
            int r5 = r7.getWriteHoldCount()
            if (r5 != 0) goto L_0x0019
            int r1 = r7.getReadHoldCount()
        L_0x0010:
            r0 = r4
        L_0x0011:
            if (r0 >= r1) goto L_0x001b
            r2.unlock()
            int r0 = r0 + 1
            goto L_0x0011
        L_0x0019:
            r1 = r4
            goto L_0x0010
        L_0x001b:
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r3 = r7.writeLock()
            r3.lock()
            java.lang.Object r5 = r8.invoke()     // Catch:{ all -> 0x003a }
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            r0 = r4
        L_0x002b:
            if (r0 >= r1) goto L_0x0033
            r2.lock()
            int r0 = r0 + 1
            goto L_0x002b
        L_0x0033:
            r3.unlock()
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            return r5
        L_0x003a:
            r5 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            r0 = r4
        L_0x003f:
            if (r0 >= r1) goto L_0x0047
            r2.lock()
            int r0 = r0 + 1
            goto L_0x003f
        L_0x0047:
            r3.unlock()
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.concurrent.LocksKt.write(java.util.concurrent.locks.ReentrantReadWriteLock, kotlin.jvm.functions.Function0):java.lang.Object");
    }
}
