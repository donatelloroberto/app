package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Status;

final class zzce implements GameManagerClient.GameManagerInstanceResult {
    private final Status zzhf;
    private final GameManagerClient zzxf;

    zzce(Status status, GameManagerClient gameManagerClient) {
        this.zzhf = status;
        this.zzxf = gameManagerClient;
    }

    public final Status getStatus() {
        return this.zzhf;
    }

    public final GameManagerClient getGameManagerClient() {
        return this.zzxf;
    }
}
