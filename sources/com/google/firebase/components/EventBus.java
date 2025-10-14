package com.google.firebase.components;

import android.support.annotation.GuardedBy;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-common@@16.1.0 */
class EventBus implements Subscriber, Publisher {
    private final Executor defaultExecutor;
    @GuardedBy("this")
    private final Map<Class<?>, ConcurrentHashMap<EventHandler<Object>, Executor>> handlerMap = new HashMap();
    @GuardedBy("this")
    private Queue<Event<?>> pendingEvents = new ArrayDeque();

    EventBus(Executor defaultExecutor2) {
        this.defaultExecutor = defaultExecutor2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        if (r3.hasNext() == false) goto L_0x000e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        r1 = r3.next();
        r1.getValue().execute(com.google.firebase.components.EventBus$$Lambda$1.lambdaFactory$(r1, r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0010, code lost:
        r3 = getHandlers(r6).iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void publish(com.google.firebase.events.Event<?> r6) {
        /*
            r5 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r6)
            monitor-enter(r5)
            java.util.Queue<com.google.firebase.events.Event<?>> r2 = r5.pendingEvents     // Catch:{ all -> 0x0033 }
            if (r2 == 0) goto L_0x000f
            java.util.Queue<com.google.firebase.events.Event<?>> r2 = r5.pendingEvents     // Catch:{ all -> 0x0033 }
            r2.add(r6)     // Catch:{ all -> 0x0033 }
            monitor-exit(r5)     // Catch:{ all -> 0x0033 }
        L_0x000e:
            return
        L_0x000f:
            monitor-exit(r5)     // Catch:{ all -> 0x0033 }
            java.util.Set r2 = r5.getHandlers(r6)
            java.util.Iterator r3 = r2.iterator()
        L_0x0018:
            boolean r2 = r3.hasNext()
            if (r2 == 0) goto L_0x000e
            java.lang.Object r1 = r3.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            r0 = r6
            java.lang.Object r2 = r1.getValue()
            java.util.concurrent.Executor r2 = (java.util.concurrent.Executor) r2
            java.lang.Runnable r4 = com.google.firebase.components.EventBus$$Lambda$1.lambdaFactory$(r1, r0)
            r2.execute(r4)
            goto L_0x0018
        L_0x0033:
            r2 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0033 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.components.EventBus.publish(com.google.firebase.events.Event):void");
    }

    private synchronized Set<Map.Entry<EventHandler<Object>, Executor>> getHandlers(Event<?> event) {
        Map<EventHandler<Object>, Executor> handlers;
        handlers = this.handlerMap.get(event.getType());
        return handlers == null ? Collections.emptySet() : handlers.entrySet();
    }

    public synchronized <T> void subscribe(Class<T> type, Executor executor, EventHandler<? super T> handler) {
        Preconditions.checkNotNull(type);
        Preconditions.checkNotNull(handler);
        Preconditions.checkNotNull(executor);
        if (!this.handlerMap.containsKey(type)) {
            this.handlerMap.put(type, new ConcurrentHashMap());
        }
        this.handlerMap.get(type).put(handler, executor);
    }

    public <T> void subscribe(Class<T> type, EventHandler<? super T> handler) {
        subscribe(type, this.defaultExecutor, handler);
    }

    public synchronized <T> void unsubscribe(Class<T> type, EventHandler<? super T> handler) {
        Preconditions.checkNotNull(type);
        Preconditions.checkNotNull(handler);
        if (this.handlerMap.containsKey(type)) {
            ConcurrentHashMap<EventHandler<Object>, Executor> handlers = this.handlerMap.get(type);
            handlers.remove(handler);
            if (handlers.isEmpty()) {
                this.handlerMap.remove(type);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void enablePublishingAndFlushPending() {
        Queue<Event<?>> pending = null;
        synchronized (this) {
            if (this.pendingEvents != null) {
                pending = this.pendingEvents;
                this.pendingEvents = null;
            }
        }
        if (pending != null) {
            for (Event<?> event : pending) {
                publish(event);
            }
        }
    }
}
