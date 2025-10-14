package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.SequenceBuilder;
import kotlin.coroutines.experimental.jvm.internal.CoroutineImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "T", "Lkotlin/coroutines/experimental/SequenceBuilder;", "", "invoke", "(Lkotlin/coroutines/experimental/SequenceBuilder;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 10})
/* compiled from: SlidingWindow.kt */
final class SlidingWindowKt$windowedIterator$1 extends CoroutineImpl implements Function2<SequenceBuilder<? super List<? extends T>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Iterator $iterator;
    final /* synthetic */ boolean $partialWindows;
    final /* synthetic */ boolean $reuseBuffer;
    final /* synthetic */ int $size;
    final /* synthetic */ int $step;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    private SequenceBuilder p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SlidingWindowKt$windowedIterator$1(int i, int i2, Iterator it, boolean z, boolean z2, Continuation continuation) {
        super(2, continuation);
        this.$step = i;
        this.$size = i2;
        this.$iterator = it;
        this.$reuseBuffer = z;
        this.$partialWindows = z2;
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull SequenceBuilder<? super List<? extends T>> $receiver, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        SlidingWindowKt$windowedIterator$1 slidingWindowKt$windowedIterator$1 = new SlidingWindowKt$windowedIterator$1(this.$step, this.$size, this.$iterator, this.$reuseBuffer, this.$partialWindows, continuation);
        slidingWindowKt$windowedIterator$1.p$ = $receiver;
        return slidingWindowKt$windowedIterator$1;
    }

    @Nullable
    public final Object invoke(@NotNull SequenceBuilder<? super List<? extends T>> $receiver, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        return ((SlidingWindowKt$windowedIterator$1) create($receiver, continuation)).doResume(Unit.INSTANCE, (Throwable) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:?, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002e, code lost:
        if (r4.hasNext() == false) goto L_0x0080;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0030, code lost:
        r1 = r4.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0034, code lost:
        if (r3 <= 0) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0036, code lost:
        r3 = r3 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0039, code lost:
        r0.add(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0042, code lost:
        if (r0.size() != r11.$size) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0044, code lost:
        r11.L$0 = r5;
        r11.I$0 = r2;
        r11.L$1 = r0;
        r11.I$1 = r3;
        r11.L$2 = r1;
        r11.L$3 = r4;
        r11.label = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0056, code lost:
        if (r5.yield(r0, r11) != r8) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0071, code lost:
        if (r11.$reuseBuffer == false) goto L_0x0078;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0073, code lost:
        r0.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0076, code lost:
        r3 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0078, code lost:
        r0 = new java.util.ArrayList(r11.$size);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0087, code lost:
        if (r0.isEmpty() != false) goto L_0x00a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0089, code lost:
        if (r9 == false) goto L_0x0186;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x008d, code lost:
        if (r11.$partialWindows != false) goto L_0x0097;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0095, code lost:
        if (r0.size() != r11.$size) goto L_0x0186;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0097, code lost:
        r11.I$0 = r2;
        r11.L$0 = r0;
        r11.I$1 = r3;
        r11.label = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a4, code lost:
        if (r5.yield(r0, r11) != r8) goto L_0x0186;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a8, code lost:
        r9 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00c3, code lost:
        if (r6.hasNext() == false) goto L_0x0118;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00c5, code lost:
        r1 = r6.next();
        r0.add(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00d0, code lost:
        if (r0.isFull() == false) goto L_0x018a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00d4, code lost:
        if (r11.$reuseBuffer == false) goto L_0x00ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d6, code lost:
        r4 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00d9, code lost:
        r11.L$0 = r7;
        r11.I$0 = r2;
        r11.L$1 = r0;
        r11.L$2 = r1;
        r11.L$3 = r6;
        r11.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ea, code lost:
        if (r7.yield(r4, r11) != r8) goto L_0x010e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00ef, code lost:
        r4 = new java.util.ArrayList(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x010e, code lost:
        r4 = r6;
        r5 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0110, code lost:
        r0.removeFirst(r11.$step);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0115, code lost:
        r6 = r4;
        r7 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x011a, code lost:
        if (r11.$partialWindows == false) goto L_0x0186;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x011c, code lost:
        r6 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0123, code lost:
        if (r0.size() <= r11.$step) goto L_0x015f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0127, code lost:
        if (r11.$reuseBuffer == false) goto L_0x013e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0129, code lost:
        r4 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x012c, code lost:
        r11.L$0 = r6;
        r11.I$0 = r2;
        r11.L$1 = r0;
        r11.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0139, code lost:
        if (r6.yield(r4, r11) != r8) goto L_0x0157;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x013e, code lost:
        r4 = new java.util.ArrayList(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0157, code lost:
        r4 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0158, code lost:
        r0.removeFirst(r11.$step);
        r6 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0166, code lost:
        if (r0.isEmpty() != false) goto L_0x017b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0168, code lost:
        r4 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0169, code lost:
        if (r4 == false) goto L_0x0186;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x016b, code lost:
        r11.I$0 = r2;
        r11.L$0 = r0;
        r11.label = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0176, code lost:
        if (r6.yield(r0, r11) != r8) goto L_0x0186;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x017b, code lost:
        r4 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x018a, code lost:
        r4 = r6;
        r5 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:?, code lost:
        return r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:?, code lost:
        return r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:?, code lost:
        return r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:?, code lost:
        return r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:?, code lost:
        return r8;
     */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object doResume(@org.jetbrains.annotations.Nullable java.lang.Object r12, @org.jetbrains.annotations.Nullable java.lang.Throwable r13) {
        /*
            r11 = this;
            r10 = 0
            r9 = 1
            java.lang.Object r8 = kotlin.coroutines.experimental.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r11.label
            switch(r4) {
                case 0: goto L_0x0013;
                case 1: goto L_0x005a;
                case 2: goto L_0x00aa;
                case 3: goto L_0x00fb;
                case 4: goto L_0x014a;
                case 5: goto L_0x017d;
                default: goto L_0x000b;
            }
        L_0x000b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0013:
            if (r13 == 0) goto L_0x0016
            throw r13
        L_0x0016:
            kotlin.coroutines.experimental.SequenceBuilder r5 = r11.p$
            int r4 = r11.$step
            int r6 = r11.$size
            int r2 = r4 - r6
            if (r2 < 0) goto L_0x00b5
            java.util.ArrayList r0 = new java.util.ArrayList
            int r4 = r11.$size
            r0.<init>(r4)
            r3 = 0
            java.util.Iterator r4 = r11.$iterator
        L_0x002a:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x0080
            java.lang.Object r1 = r4.next()
            if (r3 <= 0) goto L_0x0039
            int r3 = r3 + -1
            goto L_0x002a
        L_0x0039:
            r0.add(r1)
            int r6 = r0.size()
            int r7 = r11.$size
            if (r6 != r7) goto L_0x002a
            r11.L$0 = r5
            r11.I$0 = r2
            r11.L$1 = r0
            r11.I$1 = r3
            r11.L$2 = r1
            r11.L$3 = r4
            r11.label = r9
            java.lang.Object r6 = r5.yield(r0, r11)
            if (r6 != r8) goto L_0x006f
            r4 = r8
        L_0x0059:
            return r4
        L_0x005a:
            java.lang.Object r4 = r11.L$3
            java.util.Iterator r4 = (java.util.Iterator) r4
            java.lang.Object r1 = r11.L$2
            int r3 = r11.I$1
            java.lang.Object r0 = r11.L$1
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            int r2 = r11.I$0
            java.lang.Object r5 = r11.L$0
            kotlin.coroutines.experimental.SequenceBuilder r5 = (kotlin.coroutines.experimental.SequenceBuilder) r5
            if (r13 == 0) goto L_0x006f
            throw r13
        L_0x006f:
            boolean r6 = r11.$reuseBuffer
            if (r6 == 0) goto L_0x0078
            r0.clear()
        L_0x0076:
            r3 = r2
            goto L_0x002a
        L_0x0078:
            java.util.ArrayList r0 = new java.util.ArrayList
            int r6 = r11.$size
            r0.<init>(r6)
            goto L_0x0076
        L_0x0080:
            r4 = r0
            java.util.Collection r4 = (java.util.Collection) r4
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L_0x00a8
        L_0x0089:
            if (r9 == 0) goto L_0x0186
            boolean r4 = r11.$partialWindows
            if (r4 != 0) goto L_0x0097
            int r4 = r0.size()
            int r6 = r11.$size
            if (r4 != r6) goto L_0x0186
        L_0x0097:
            r11.I$0 = r2
            r11.L$0 = r0
            r11.I$1 = r3
            r4 = 2
            r11.label = r4
            java.lang.Object r4 = r5.yield(r0, r11)
            if (r4 != r8) goto L_0x0186
            r4 = r8
            goto L_0x0059
        L_0x00a8:
            r9 = r10
            goto L_0x0089
        L_0x00aa:
            int r3 = r11.I$1
            java.lang.Object r0 = r11.L$0
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            int r2 = r11.I$0
            if (r13 == 0) goto L_0x0186
            throw r13
        L_0x00b5:
            kotlin.collections.RingBuffer r0 = new kotlin.collections.RingBuffer
            int r4 = r11.$size
            r0.<init>(r4)
            java.util.Iterator r6 = r11.$iterator
            r7 = r5
        L_0x00bf:
            boolean r4 = r6.hasNext()
            if (r4 == 0) goto L_0x0118
            java.lang.Object r1 = r6.next()
            r0.add(r1)
            boolean r4 = r0.isFull()
            if (r4 == 0) goto L_0x018a
            boolean r4 = r11.$reuseBuffer
            if (r4 == 0) goto L_0x00ef
            r4 = r0
            java.util.List r4 = (java.util.List) r4
        L_0x00d9:
            r11.L$0 = r7
            r11.I$0 = r2
            r11.L$1 = r0
            r11.L$2 = r1
            r11.L$3 = r6
            r5 = 3
            r11.label = r5
            java.lang.Object r4 = r7.yield(r4, r11)
            if (r4 != r8) goto L_0x010e
            r4 = r8
            goto L_0x0059
        L_0x00ef:
            java.util.ArrayList r5 = new java.util.ArrayList
            r4 = r0
            java.util.Collection r4 = (java.util.Collection) r4
            r5.<init>(r4)
            r4 = r5
            java.util.List r4 = (java.util.List) r4
            goto L_0x00d9
        L_0x00fb:
            java.lang.Object r4 = r11.L$3
            java.util.Iterator r4 = (java.util.Iterator) r4
            java.lang.Object r1 = r11.L$2
            java.lang.Object r0 = r11.L$1
            kotlin.collections.RingBuffer r0 = (kotlin.collections.RingBuffer) r0
            int r2 = r11.I$0
            java.lang.Object r5 = r11.L$0
            kotlin.coroutines.experimental.SequenceBuilder r5 = (kotlin.coroutines.experimental.SequenceBuilder) r5
            if (r13 == 0) goto L_0x0110
            throw r13
        L_0x010e:
            r4 = r6
            r5 = r7
        L_0x0110:
            int r6 = r11.$step
            r0.removeFirst(r6)
        L_0x0115:
            r6 = r4
            r7 = r5
            goto L_0x00bf
        L_0x0118:
            boolean r4 = r11.$partialWindows
            if (r4 == 0) goto L_0x0186
            r6 = r7
        L_0x011d:
            int r4 = r0.size()
            int r5 = r11.$step
            if (r4 <= r5) goto L_0x015f
            boolean r4 = r11.$reuseBuffer
            if (r4 == 0) goto L_0x013e
            r4 = r0
            java.util.List r4 = (java.util.List) r4
        L_0x012c:
            r11.L$0 = r6
            r11.I$0 = r2
            r11.L$1 = r0
            r5 = 4
            r11.label = r5
            java.lang.Object r4 = r6.yield(r4, r11)
            if (r4 != r8) goto L_0x0157
            r4 = r8
            goto L_0x0059
        L_0x013e:
            java.util.ArrayList r5 = new java.util.ArrayList
            r4 = r0
            java.util.Collection r4 = (java.util.Collection) r4
            r5.<init>(r4)
            r4 = r5
            java.util.List r4 = (java.util.List) r4
            goto L_0x012c
        L_0x014a:
            java.lang.Object r0 = r11.L$1
            kotlin.collections.RingBuffer r0 = (kotlin.collections.RingBuffer) r0
            int r2 = r11.I$0
            java.lang.Object r4 = r11.L$0
            kotlin.coroutines.experimental.SequenceBuilder r4 = (kotlin.coroutines.experimental.SequenceBuilder) r4
            if (r13 == 0) goto L_0x0158
            throw r13
        L_0x0157:
            r4 = r6
        L_0x0158:
            int r5 = r11.$step
            r0.removeFirst(r5)
            r6 = r4
            goto L_0x011d
        L_0x015f:
            r4 = r0
            java.util.Collection r4 = (java.util.Collection) r4
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L_0x017b
            r4 = r9
        L_0x0169:
            if (r4 == 0) goto L_0x0186
            r11.I$0 = r2
            r11.L$0 = r0
            r4 = 5
            r11.label = r4
            java.lang.Object r4 = r6.yield(r0, r11)
            if (r4 != r8) goto L_0x0186
            r4 = r8
            goto L_0x0059
        L_0x017b:
            r4 = r10
            goto L_0x0169
        L_0x017d:
            java.lang.Object r0 = r11.L$0
            kotlin.collections.RingBuffer r0 = (kotlin.collections.RingBuffer) r0
            int r2 = r11.I$0
            if (r13 == 0) goto L_0x0186
            throw r13
        L_0x0186:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            goto L_0x0059
        L_0x018a:
            r4 = r6
            r5 = r7
            goto L_0x0115
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.SlidingWindowKt$windowedIterator$1.doResume(java.lang.Object, java.lang.Throwable):java.lang.Object");
    }
}
