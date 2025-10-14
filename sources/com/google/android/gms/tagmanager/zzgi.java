package com.google.android.gms.tagmanager;

final class zzgi extends Number implements Comparable<zzgi> {
    private double zzall;
    private long zzalm;
    private boolean zzaln = false;

    private zzgi(double d) {
        this.zzall = d;
    }

    private zzgi(long j) {
        this.zzalm = j;
    }

    public static zzgi zza(Double d) {
        return new zzgi(d.doubleValue());
    }

    public static zzgi zzm(long j) {
        return new zzgi(j);
    }

    public static zzgi zzbo(String str) throws NumberFormatException {
        try {
            return new zzgi(Long.parseLong(str));
        } catch (NumberFormatException e) {
            try {
                return new zzgi(Double.parseDouble(str));
            } catch (NumberFormatException e2) {
                throw new NumberFormatException(String.valueOf(str).concat(" is not a valid TypedNumber"));
            }
        }
    }

    public final String toString() {
        return this.zzaln ? Long.toString(this.zzalm) : Double.toString(this.zzall);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof zzgi) && compareTo((zzgi) obj) == 0;
    }

    public final int hashCode() {
        return new Long(longValue()).hashCode();
    }

    /* renamed from: zza */
    public final int compareTo(zzgi zzgi) {
        if (!this.zzaln || !zzgi.zzaln) {
            return Double.compare(doubleValue(), zzgi.doubleValue());
        }
        return new Long(this.zzalm).compareTo(Long.valueOf(zzgi.zzalm));
    }

    public final boolean zzju() {
        return !this.zzaln;
    }

    public final boolean zzjv() {
        return this.zzaln;
    }

    public final double doubleValue() {
        return this.zzaln ? (double) this.zzalm : this.zzall;
    }

    public final float floatValue() {
        return (float) doubleValue();
    }

    public final long longValue() {
        return this.zzaln ? this.zzalm : (long) this.zzall;
    }

    public final int intValue() {
        return (int) longValue();
    }

    public final short shortValue() {
        return (short) ((int) longValue());
    }

    public final byte byteValue() {
        return (byte) ((int) longValue());
    }
}
