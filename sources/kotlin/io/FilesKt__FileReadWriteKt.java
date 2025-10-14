package kotlin.io;

import com.adobe.phonegap.push.PushConstants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000z\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t\u001a!\u0010\n\u001a\u00020\u000b*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\rH\b\u001a!\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\rH\b\u001aB\u0010\u0010\u001a\u00020\u0001*\u00020\u000226\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00010\u0012\u001aJ\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\r26\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00010\u0012\u001a7\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00010\u0019\u001a\r\u0010\u001b\u001a\u00020\u001c*\u00020\u0002H\b\u001a\r\u0010\u001d\u001a\u00020\u001e*\u00020\u0002H\b\u001a\u0017\u0010\u001f\u001a\u00020 *\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\b\u001a\n\u0010!\u001a\u00020\u0004*\u00020\u0002\u001a\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00070#*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0014\u0010$\u001a\u00020\u0007*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0017\u0010%\u001a\u00020&*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\b\u001a?\u0010'\u001a\u0002H(\"\u0004\b\u0000\u0010(*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\u0018\u0010)\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070*\u0012\u0004\u0012\u0002H(0\u0019H\bø\u0001\u0000¢\u0006\u0002\u0010,\u001a\u0012\u0010-\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010.\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0017\u0010/\u001a\u000200*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\b\u0002\b\n\u0006\b\u0011(+0\u0001¨\u00061"}, d2 = {"appendBytes", "", "Ljava/io/File;", "array", "", "appendText", "text", "", "charset", "Ljava/nio/charset/Charset;", "bufferedReader", "Ljava/io/BufferedReader;", "bufferSize", "", "bufferedWriter", "Ljava/io/BufferedWriter;", "forEachBlock", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "buffer", "bytesRead", "blockSize", "forEachLine", "Lkotlin/Function1;", "line", "inputStream", "Ljava/io/FileInputStream;", "outputStream", "Ljava/io/FileOutputStream;", "printWriter", "Ljava/io/PrintWriter;", "readBytes", "readLines", "", "readText", "reader", "Ljava/io/InputStreamReader;", "useLines", "T", "block", "Lkotlin/sequences/Sequence;", "Requires newer compiler version to be inlined correctly.", "(Ljava/io/File;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writeBytes", "writeText", "writer", "Ljava/io/OutputStreamWriter;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 10}, xi = 1, xs = "kotlin/io/FilesKt")
/* compiled from: FileReadWrite.kt */
class FilesKt__FileReadWriteKt extends FilesKt__FilePathComponentsKt {
    @InlineOnly
    static /* bridge */ /* synthetic */ InputStreamReader reader$default(File $receiver, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new InputStreamReader(new FileInputStream($receiver), charset);
    }

    @InlineOnly
    private static final InputStreamReader reader(@NotNull File $receiver, Charset charset) {
        return new InputStreamReader(new FileInputStream($receiver), charset);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ BufferedReader bufferedReader$default(File $receiver, Charset charset, int bufferSize, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i & 2) != 0) {
            bufferSize = 8192;
        }
        Reader inputStreamReader = new InputStreamReader(new FileInputStream($receiver), charset);
        return inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, bufferSize);
    }

    @InlineOnly
    private static final BufferedReader bufferedReader(@NotNull File $receiver, Charset charset, int bufferSize) {
        Reader inputStreamReader = new InputStreamReader(new FileInputStream($receiver), charset);
        return inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, bufferSize);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ OutputStreamWriter writer$default(File $receiver, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new OutputStreamWriter(new FileOutputStream($receiver), charset);
    }

    @InlineOnly
    private static final OutputStreamWriter writer(@NotNull File $receiver, Charset charset) {
        return new OutputStreamWriter(new FileOutputStream($receiver), charset);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ BufferedWriter bufferedWriter$default(File $receiver, Charset charset, int bufferSize, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i & 2) != 0) {
            bufferSize = 8192;
        }
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream($receiver), charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, bufferSize);
    }

    @InlineOnly
    private static final BufferedWriter bufferedWriter(@NotNull File $receiver, Charset charset, int bufferSize) {
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream($receiver), charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, bufferSize);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ PrintWriter printWriter$default(File $receiver, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream($receiver), charset);
        return new PrintWriter(outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192));
    }

    @InlineOnly
    private static final PrintWriter printWriter(@NotNull File $receiver, Charset charset) {
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream($receiver), charset);
        return new PrintWriter(outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x004f, code lost:
        kotlin.io.CloseableKt.closeFinally(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0052, code lost:
        throw r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x004e, code lost:
        r11 = move-exception;
     */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final byte[] readBytes(@org.jetbrains.annotations.NotNull java.io.File r14) {
        /*
            java.lang.String r9 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r14, r9)
            java.io.FileInputStream r9 = new java.io.FileInputStream
            r9.<init>(r14)
            java.io.Closeable r9 = (java.io.Closeable) r9
            r10 = 0
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            r0 = r9
            java.io.FileInputStream r0 = (java.io.FileInputStream) r0     // Catch:{ Throwable -> 0x004c }
            r2 = r0
            r3 = 0
            long r4 = r14.length()     // Catch:{ Throwable -> 0x004c }
            r11 = 2147483647(0x7fffffff, float:NaN)
            long r12 = (long) r11     // Catch:{ Throwable -> 0x004c }
            int r11 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r11 <= 0) goto L_0x0053
            java.lang.OutOfMemoryError r11 = new java.lang.OutOfMemoryError     // Catch:{ Throwable -> 0x004c }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x004c }
            r12.<init>()     // Catch:{ Throwable -> 0x004c }
            java.lang.String r13 = "File "
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ Throwable -> 0x004c }
            java.lang.StringBuilder r12 = r12.append(r14)     // Catch:{ Throwable -> 0x004c }
            java.lang.String r13 = " is too big ("
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ Throwable -> 0x004c }
            java.lang.StringBuilder r12 = r12.append(r4)     // Catch:{ Throwable -> 0x004c }
            java.lang.String r13 = " bytes) to fit in memory."
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ Throwable -> 0x004c }
            java.lang.String r12 = r12.toString()     // Catch:{ Throwable -> 0x004c }
            r11.<init>(r12)     // Catch:{ Throwable -> 0x004c }
            java.lang.Throwable r11 = (java.lang.Throwable) r11     // Catch:{ Throwable -> 0x004c }
            throw r11     // Catch:{ Throwable -> 0x004c }
        L_0x004c:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x004e }
        L_0x004e:
            r11 = move-exception
            kotlin.io.CloseableKt.closeFinally(r9, r10)
            throw r11
        L_0x0053:
            int r7 = (int) r4
            byte[] r8 = new byte[r7]     // Catch:{ Throwable -> 0x004c }
        L_0x0057:
            if (r7 <= 0) goto L_0x005f
            int r6 = r2.read(r8, r3, r7)     // Catch:{ Throwable -> 0x004c }
            if (r6 >= 0) goto L_0x0066
        L_0x005f:
            if (r7 != 0) goto L_0x0069
        L_0x0061:
            kotlin.io.CloseableKt.closeFinally(r9, r10)
            return r8
        L_0x0066:
            int r7 = r7 - r6
            int r3 = r3 + r6
            goto L_0x0057
        L_0x0069:
            byte[] r8 = java.util.Arrays.copyOf(r8, r3)     // Catch:{ Throwable -> 0x004c }
            java.lang.String r11 = "java.util.Arrays.copyOf(this, newSize)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r11)     // Catch:{ Throwable -> 0x004c }
            goto L_0x0061
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.readBytes(java.io.File):byte[]");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0024, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0025, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void writeBytes(@org.jetbrains.annotations.NotNull java.io.File r5, @org.jetbrains.annotations.NotNull byte[] r6) {
        /*
            java.lang.String r2 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r2)
            java.lang.String r2 = "array"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r2)
            java.io.FileOutputStream r2 = new java.io.FileOutputStream
            r2.<init>(r5)
            java.io.Closeable r2 = (java.io.Closeable) r2
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            r0 = r2
            java.io.FileOutputStream r0 = (java.io.FileOutputStream) r0     // Catch:{ Throwable -> 0x0022 }
            r1 = r0
            r1.write(r6)     // Catch:{ Throwable -> 0x0022 }
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x0022 }
            kotlin.io.CloseableKt.closeFinally(r2, r3)
            return
        L_0x0022:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0024 }
        L_0x0024:
            r4 = move-exception
            kotlin.io.CloseableKt.closeFinally(r2, r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.writeBytes(java.io.File, byte[]):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0029, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0025, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0026, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void appendBytes(@org.jetbrains.annotations.NotNull java.io.File r5, @org.jetbrains.annotations.NotNull byte[] r6) {
        /*
            java.lang.String r2 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r2)
            java.lang.String r2 = "array"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r2)
            java.io.FileOutputStream r2 = new java.io.FileOutputStream
            r3 = 1
            r2.<init>(r5, r3)
            java.io.Closeable r2 = (java.io.Closeable) r2
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            r0 = r2
            java.io.FileOutputStream r0 = (java.io.FileOutputStream) r0     // Catch:{ Throwable -> 0x0023 }
            r1 = r0
            r1.write(r6)     // Catch:{ Throwable -> 0x0023 }
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x0023 }
            kotlin.io.CloseableKt.closeFinally(r2, r3)
            return
        L_0x0023:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0025 }
        L_0x0025:
            r4 = move-exception
            kotlin.io.CloseableKt.closeFinally(r2, r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.appendBytes(java.io.File, byte[]):void");
    }

    @NotNull
    public static final String readText(@NotNull File $receiver, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        return new String(FilesKt.readBytes($receiver), charset);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String readText$default(File file, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return FilesKt.readText(file, charset);
    }

    public static final void writeText(@NotNull File $receiver, @NotNull String text, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, PushConstants.STYLE_TEXT);
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        byte[] bytes = text.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        FilesKt.writeBytes($receiver, bytes);
    }

    public static /* bridge */ /* synthetic */ void writeText$default(File file, String str, Charset charset, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        FilesKt.writeText(file, str, charset);
    }

    public static final void appendText(@NotNull File $receiver, @NotNull String text, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, PushConstants.STYLE_TEXT);
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        byte[] bytes = text.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        FilesKt.appendBytes($receiver, bytes);
    }

    public static /* bridge */ /* synthetic */ void appendText$default(File file, String str, Charset charset, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        FilesKt.appendText(file, str, charset);
    }

    public static final void forEachBlock(@NotNull File $receiver, @NotNull Function2<? super byte[], ? super Integer, Unit> action) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(action, "action");
        FilesKt.forEachBlock($receiver, 4096, action);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0038, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0039, code lost:
        kotlin.io.CloseableKt.closeFinally(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003c, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void forEachBlock(@org.jetbrains.annotations.NotNull java.io.File r7, int r8, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super byte[], ? super java.lang.Integer, kotlin.Unit> r9) {
        /*
            java.lang.String r4 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r4)
            java.lang.String r4 = "action"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r4)
            r4 = 512(0x200, float:7.175E-43)
            int r4 = kotlin.ranges.RangesKt.coerceAtLeast((int) r8, (int) r4)
            byte[] r1 = new byte[r4]
            java.io.FileInputStream r4 = new java.io.FileInputStream
            r4.<init>(r7)
            java.io.Closeable r4 = (java.io.Closeable) r4
            r5 = 0
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            r0 = r4
            java.io.FileInputStream r0 = (java.io.FileInputStream) r0     // Catch:{ Throwable -> 0x0036 }
            r2 = r0
        L_0x0021:
            int r3 = r2.read(r1)     // Catch:{ Throwable -> 0x0036 }
            if (r3 > 0) goto L_0x002e
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x0036 }
            kotlin.io.CloseableKt.closeFinally(r4, r5)
            return
        L_0x002e:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)     // Catch:{ Throwable -> 0x0036 }
            r9.invoke(r1, r6)     // Catch:{ Throwable -> 0x0036 }
            goto L_0x0021
        L_0x0036:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0038 }
        L_0x0038:
            r6 = move-exception
            kotlin.io.CloseableKt.closeFinally(r4, r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.forEachBlock(java.io.File, int, kotlin.jvm.functions.Function2):void");
    }

    public static /* bridge */ /* synthetic */ void forEachLine$default(File file, Charset charset, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        FilesKt.forEachLine(file, charset, function1);
    }

    public static final void forEachLine(@NotNull File $receiver, @NotNull Charset charset, @NotNull Function1<? super String, Unit> action) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        Intrinsics.checkParameterIsNotNull(action, "action");
        TextStreamsKt.forEachLine(new BufferedReader(new InputStreamReader(new FileInputStream($receiver), charset)), action);
    }

    @InlineOnly
    private static final FileInputStream inputStream(@NotNull File $receiver) {
        return new FileInputStream($receiver);
    }

    @InlineOnly
    private static final FileOutputStream outputStream(@NotNull File $receiver) {
        return new FileOutputStream($receiver);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ List readLines$default(File file, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return FilesKt.readLines(file, charset);
    }

    @NotNull
    public static final List<String> readLines(@NotNull File $receiver, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        ArrayList result = new ArrayList();
        FilesKt.forEachLine($receiver, charset, new FilesKt__FileReadWriteKt$readLines$1(result));
        return result;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005d, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005e, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0065, code lost:
        if (kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0) != false) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0067, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006a, code lost:
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006d, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006e, code lost:
        if (r3 == null) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0070, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r2.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* bridge */ /* synthetic */ java.lang.Object useLines$default(java.io.File r7, java.nio.charset.Charset r8, kotlin.jvm.functions.Function1 r9, int r10, java.lang.Object r11) {
        /*
            r5 = 0
            r6 = 1
            r2 = r10 & 1
            if (r2 == 0) goto L_0x0008
            java.nio.charset.Charset r8 = kotlin.text.Charsets.UTF_8
        L_0x0008:
            java.lang.String r2 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r2)
            java.lang.String r2 = "charset"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r2)
            java.lang.String r2 = "block"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r2)
            r4 = 8192(0x2000, float:1.14794E-41)
            java.io.FileInputStream r2 = new java.io.FileInputStream
            r2.<init>(r7)
            java.io.InputStream r2 = (java.io.InputStream) r2
            java.io.InputStreamReader r3 = new java.io.InputStreamReader
            r3.<init>(r2, r8)
            r2 = r3
            java.io.Reader r2 = (java.io.Reader) r2
            boolean r3 = r2 instanceof java.io.BufferedReader
            if (r3 == 0) goto L_0x0050
            java.io.BufferedReader r2 = (java.io.BufferedReader) r2
        L_0x002e:
            java.io.Closeable r2 = (java.io.Closeable) r2
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            r0 = r2
            java.io.BufferedReader r0 = (java.io.BufferedReader) r0     // Catch:{ Throwable -> 0x005b }
            r1 = r0
            kotlin.sequences.Sequence r4 = kotlin.io.TextStreamsKt.lineSequence(r1)     // Catch:{ Throwable -> 0x005b }
            java.lang.Object r4 = r9.invoke(r4)     // Catch:{ Throwable -> 0x005b }
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            boolean r5 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r6, r6, r5)
            if (r5 == 0) goto L_0x0057
            kotlin.io.CloseableKt.closeFinally(r2, r3)
        L_0x004c:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            return r4
        L_0x0050:
            java.io.BufferedReader r3 = new java.io.BufferedReader
            r3.<init>(r2, r4)
            r2 = r3
            goto L_0x002e
        L_0x0057:
            r2.close()
            goto L_0x004c
        L_0x005b:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x005d }
        L_0x005d:
            r4 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            boolean r5 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r6, r6, r5)
            if (r5 == 0) goto L_0x006e
            kotlin.io.CloseableKt.closeFinally(r2, r3)
        L_0x006a:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            throw r4
        L_0x006e:
            if (r3 != 0) goto L_0x0074
            r2.close()
            goto L_0x006a
        L_0x0074:
            r2.close()     // Catch:{ Throwable -> 0x0079 }
            goto L_0x006a
        L_0x0079:
            r2 = move-exception
            goto L_0x006a
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.useLines$default(java.io.File, java.nio.charset.Charset, kotlin.jvm.functions.Function1, int, java.lang.Object):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0057, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0058, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005f, code lost:
        if (kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0) != false) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0061, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0064, code lost:
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0067, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0068, code lost:
        if (r3 == null) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006a, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r2.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> T useLines(@org.jetbrains.annotations.NotNull java.io.File r7, @org.jetbrains.annotations.NotNull java.nio.charset.Charset r8, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super kotlin.sequences.Sequence<java.lang.String>, ? extends T> r9) {
        /*
            r5 = 0
            r6 = 1
            java.lang.String r2 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r2)
            java.lang.String r2 = "charset"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r2)
            java.lang.String r2 = "block"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r2)
            r4 = 8192(0x2000, float:1.14794E-41)
            java.io.FileInputStream r2 = new java.io.FileInputStream
            r2.<init>(r7)
            java.io.InputStream r2 = (java.io.InputStream) r2
            java.io.InputStreamReader r3 = new java.io.InputStreamReader
            r3.<init>(r2, r8)
            r2 = r3
            java.io.Reader r2 = (java.io.Reader) r2
            boolean r3 = r2 instanceof java.io.BufferedReader
            if (r3 == 0) goto L_0x004a
            java.io.BufferedReader r2 = (java.io.BufferedReader) r2
        L_0x0028:
            java.io.Closeable r2 = (java.io.Closeable) r2
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            r0 = r2
            java.io.BufferedReader r0 = (java.io.BufferedReader) r0     // Catch:{ Throwable -> 0x0055 }
            r1 = r0
            kotlin.sequences.Sequence r4 = kotlin.io.TextStreamsKt.lineSequence(r1)     // Catch:{ Throwable -> 0x0055 }
            java.lang.Object r4 = r9.invoke(r4)     // Catch:{ Throwable -> 0x0055 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            boolean r5 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r6, r6, r5)
            if (r5 == 0) goto L_0x0051
            kotlin.io.CloseableKt.closeFinally(r2, r3)
        L_0x0046:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            return r4
        L_0x004a:
            java.io.BufferedReader r3 = new java.io.BufferedReader
            r3.<init>(r2, r4)
            r2 = r3
            goto L_0x0028
        L_0x0051:
            r2.close()
            goto L_0x0046
        L_0x0055:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0057 }
        L_0x0057:
            r4 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r6)
            boolean r5 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r6, r6, r5)
            if (r5 == 0) goto L_0x0068
            kotlin.io.CloseableKt.closeFinally(r2, r3)
        L_0x0064:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r6)
            throw r4
        L_0x0068:
            if (r3 != 0) goto L_0x006e
            r2.close()
            goto L_0x0064
        L_0x006e:
            r2.close()     // Catch:{ Throwable -> 0x0073 }
            goto L_0x0064
        L_0x0073:
            r2 = move-exception
            goto L_0x0064
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.useLines(java.io.File, java.nio.charset.Charset, kotlin.jvm.functions.Function1):java.lang.Object");
    }
}
