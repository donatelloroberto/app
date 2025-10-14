package kotlin.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000b\u001a!\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0002\b\u0004\u001a\u0011\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0002¢\u0006\u0002\b\u0007\u001a\u0014\u0010\b\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u001aJ\u0010\t\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001H\b¢\u0006\u0002\b\u000e\u001a\u0014\u0010\u000f\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u0002\u001a\u001e\u0010\u0011\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002\u001a\n\u0010\u0013\u001a\u00020\u0002*\u00020\u0002\u001a\u0014\u0010\u0014\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002¨\u0006\u0015"}, d2 = {"getIndentFunction", "Lkotlin/Function1;", "", "indent", "getIndentFunction$StringsKt__IndentKt", "indentWidth", "", "indentWidth$StringsKt__IndentKt", "prependIndent", "reindent", "", "resultSizeEstimate", "indentAddFunction", "indentCutFunction", "reindent$StringsKt__IndentKt", "replaceIndent", "newIndent", "replaceIndentByMargin", "marginPrefix", "trimIndent", "trimMargin", "kotlin-stdlib"}, k = 5, mv = {1, 1, 10}, xi = 1, xs = "kotlin/text/StringsKt")
/* compiled from: Indent.kt */
class StringsKt__IndentKt {
    @NotNull
    public static /* bridge */ /* synthetic */ String trimMargin$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "|";
        }
        return StringsKt.trimMargin(str, str2);
    }

    @NotNull
    public static final String trimMargin(@NotNull String $receiver, @NotNull String marginPrefix) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(marginPrefix, "marginPrefix");
        return StringsKt.replaceIndentByMargin($receiver, "", marginPrefix);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String replaceIndentByMargin$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "";
        }
        if ((i & 2) != 0) {
            str3 = "|";
        }
        return StringsKt.replaceIndentByMargin(str, str2, str3);
    }

    @NotNull
    public static final String replaceIndentByMargin(@NotNull String $receiver, @NotNull String newIndent, @NotNull String marginPrefix) {
        String str;
        String it$iv$iv$iv;
        String invoke;
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(newIndent, "newIndent");
        Intrinsics.checkParameterIsNotNull(marginPrefix, "marginPrefix");
        if (!(!StringsKt.isBlank(marginPrefix))) {
            throw new IllegalArgumentException("marginPrefix must be non-blank string.".toString());
        }
        List<String> $receiver$iv$iv = StringsKt.lines($receiver);
        int length = $receiver.length() + (newIndent.length() * $receiver$iv$iv.size());
        Function1<String, String> indentFunction$StringsKt__IndentKt = getIndentFunction$StringsKt__IndentKt(newIndent);
        int lastIndex$iv = CollectionsKt.getLastIndex($receiver$iv$iv);
        Collection destination$iv$iv$iv = new ArrayList();
        int index$iv$iv$iv$iv = 0;
        for (String line : $receiver$iv$iv) {
            int index$iv$iv$iv$iv2 = index$iv$iv$iv$iv + 1;
            int index$iv = index$iv$iv$iv$iv;
            if ((index$iv == 0 || index$iv == lastIndex$iv) && StringsKt.isBlank(line)) {
                it$iv$iv$iv = null;
            } else {
                CharSequence $receiver$iv = line;
                int firstNonWhitespaceIndex = 0;
                int length2 = $receiver$iv.length();
                while (true) {
                    if (firstNonWhitespaceIndex >= length2) {
                        firstNonWhitespaceIndex = -1;
                        break;
                    }
                    if (!CharsKt.isWhitespace($receiver$iv.charAt(firstNonWhitespaceIndex))) {
                        break;
                    }
                    firstNonWhitespaceIndex++;
                }
                if (firstNonWhitespaceIndex == -1) {
                    str = null;
                } else if (StringsKt.startsWith$default(line, marginPrefix, firstNonWhitespaceIndex, false, 4, (Object) null)) {
                    int length3 = marginPrefix.length() + firstNonWhitespaceIndex;
                    if (line == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    str = line.substring(length3);
                    Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).substring(startIndex)");
                } else {
                    str = null;
                }
                if (str == null || (invoke = indentFunction$StringsKt__IndentKt.invoke(str)) == null) {
                    it$iv$iv$iv = line;
                } else {
                    it$iv$iv$iv = invoke;
                }
            }
            if (it$iv$iv$iv != null) {
                destination$iv$iv$iv.add(it$iv$iv$iv);
            }
            index$iv$iv$iv$iv = index$iv$iv$iv$iv2;
        }
        String sb = ((StringBuilder) CollectionsKt.joinTo$default((List) destination$iv$iv$iv, new StringBuilder(length), "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 124, (Object) null)).toString();
        Intrinsics.checkExpressionValueIsNotNull(sb, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
        return sb;
    }

    @NotNull
    public static final String trimIndent(@NotNull String $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return StringsKt.replaceIndent($receiver, "");
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String replaceIndent$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "";
        }
        return StringsKt.replaceIndent(str, str2);
    }

    @NotNull
    public static final String replaceIndent(@NotNull String $receiver, @NotNull String newIndent) {
        String it$iv$iv$iv;
        String invoke;
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(newIndent, "newIndent");
        List<String> $receiver$iv$iv = StringsKt.lines($receiver);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $receiver$iv$iv) {
            if (!StringsKt.isBlank((String) element$iv$iv)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable<String> iterable = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String indentWidth$StringsKt__IndentKt : iterable) {
            destination$iv$iv2.add(Integer.valueOf(indentWidth$StringsKt__IndentKt(indentWidth$StringsKt__IndentKt)));
        }
        Integer num = (Integer) CollectionsKt.min((List) destination$iv$iv2);
        int minCommonIndent = num != null ? num.intValue() : 0;
        int length = $receiver.length() + (newIndent.length() * $receiver$iv$iv.size());
        Function1<String, String> indentFunction$StringsKt__IndentKt = getIndentFunction$StringsKt__IndentKt(newIndent);
        int lastIndex$iv = CollectionsKt.getLastIndex($receiver$iv$iv);
        Collection destination$iv$iv$iv = new ArrayList();
        int index$iv$iv$iv$iv = 0;
        for (String line : $receiver$iv$iv) {
            int index$iv$iv$iv$iv2 = index$iv$iv$iv$iv + 1;
            int index$iv = index$iv$iv$iv$iv;
            if ((index$iv == 0 || index$iv == lastIndex$iv) && StringsKt.isBlank(line)) {
                it$iv$iv$iv = null;
            } else {
                String drop = StringsKt.drop(line, minCommonIndent);
                it$iv$iv$iv = (drop == null || (invoke = indentFunction$StringsKt__IndentKt.invoke(drop)) == null) ? line : invoke;
            }
            if (it$iv$iv$iv != null) {
                destination$iv$iv$iv.add(it$iv$iv$iv);
            }
            index$iv$iv$iv$iv = index$iv$iv$iv$iv2;
        }
        String sb = ((StringBuilder) CollectionsKt.joinTo$default((List) destination$iv$iv$iv, new StringBuilder(length), "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 124, (Object) null)).toString();
        Intrinsics.checkExpressionValueIsNotNull(sb, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
        return sb;
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String prependIndent$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "    ";
        }
        return StringsKt.prependIndent(str, str2);
    }

    @NotNull
    public static final String prependIndent(@NotNull String $receiver, @NotNull String indent) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(indent, "indent");
        return SequencesKt.joinToString$default(SequencesKt.map(StringsKt.lineSequence($receiver), new StringsKt__IndentKt$prependIndent$1(indent)), "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    private static final int indentWidth$StringsKt__IndentKt(@NotNull String $receiver) {
        int it;
        CharSequence $receiver$iv = $receiver;
        int length = $receiver$iv.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                it = -1;
                break;
            }
            if (!CharsKt.isWhitespace($receiver$iv.charAt(i))) {
                it = i;
                break;
            }
            i++;
        }
        return it == -1 ? $receiver.length() : it;
    }

    private static final Function1<String, String> getIndentFunction$StringsKt__IndentKt(String indent) {
        if (indent.length() == 0) {
            return StringsKt__IndentKt$getIndentFunction$1.INSTANCE;
        }
        return new StringsKt__IndentKt$getIndentFunction$2(indent);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0051, code lost:
        r2 = r24.invoke(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.String reindent$StringsKt__IndentKt(@org.jetbrains.annotations.NotNull java.util.List<java.lang.String> r22, int r23, kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.String> r24, kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.String> r25) {
        /*
            int r21 = kotlin.collections.CollectionsKt.getLastIndex(r22)
            r12 = r22
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            java.util.Collection r14 = (java.util.Collection) r14
            r13 = r12
            r17 = 0
            java.util.Iterator r3 = r13.iterator()
        L_0x0016:
            boolean r2 = r3.hasNext()
            if (r2 == 0) goto L_0x0061
            java.lang.Object r20 = r3.next()
            int r18 = r17 + 1
            r16 = r17
            java.lang.String r20 = (java.lang.String) r20
            r15 = r16
            if (r15 == 0) goto L_0x002e
            r0 = r21
            if (r15 != r0) goto L_0x0045
        L_0x002e:
            r2 = r20
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            boolean r2 = kotlin.text.StringsKt.isBlank(r2)
            if (r2 == 0) goto L_0x0045
            r19 = 0
        L_0x003a:
            if (r19 == 0) goto L_0x0041
            r0 = r19
            r14.add(r0)
        L_0x0041:
            r17 = r18
            goto L_0x0016
        L_0x0045:
            r0 = r25
            r1 = r20
            java.lang.Object r2 = r0.invoke(r1)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 == 0) goto L_0x005e
            r0 = r24
            java.lang.Object r2 = r0.invoke(r2)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 == 0) goto L_0x005e
            r19 = r2
            goto L_0x003a
        L_0x005e:
            r19 = r20
            goto L_0x003a
        L_0x0061:
            java.util.List r14 = (java.util.List) r14
            r2 = r14
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r0 = r23
            r3.<init>(r0)
            java.lang.Appendable r3 = (java.lang.Appendable) r3
            java.lang.String r4 = "\n"
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 124(0x7c, float:1.74E-43)
            r11 = 0
            java.lang.Appendable r2 = kotlin.collections.CollectionsKt.joinTo$default(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            java.lang.StringBuilder r2 = (java.lang.StringBuilder) r2
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "mapIndexedNotNull { inde…\"\\n\")\n        .toString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__IndentKt.reindent$StringsKt__IndentKt(java.util.List, int, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1):java.lang.String");
    }
}
