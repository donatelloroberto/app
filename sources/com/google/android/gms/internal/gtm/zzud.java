package com.google.android.gms.internal.gtm;

final class zzud extends IllegalArgumentException {
    zzud(int i, int i2) {
        super(new StringBuilder(54).append("Unpaired surrogate at index ").append(i).append(" of ").append(i2).toString());
    }
}
