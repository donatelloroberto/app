package com.amazon.device.ads;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.amazon.device.ads.AdActivity;
import com.amazon.device.ads.AdVideoPlayer;
import com.google.android.gms.common.internal.ImagesContract;

class VideoActionHandler implements AdActivity.IAdActivityAdapter {
    /* access modifiers changed from: private */
    public Activity activity;
    private RelativeLayout layout;
    private AdVideoPlayer player;

    VideoActionHandler() {
    }

    public void setActivity(Activity activity2) {
        this.activity = activity2;
    }

    public void onCreate() {
        Bundle data = this.activity.getIntent().getExtras();
        this.layout = new RelativeLayout(this.activity);
        this.layout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.activity.setContentView(this.layout);
        initPlayer(data);
        this.player.playVideo();
    }

    private void setPlayerListener(AdVideoPlayer player2) {
        player2.setListener(new AdVideoPlayer.AdVideoPlayerListener() {
            public void onError() {
                VideoActionHandler.this.activity.finish();
            }

            public void onComplete() {
                VideoActionHandler.this.activity.finish();
            }
        });
    }

    private void initPlayer(Bundle data) {
        this.player = new AdVideoPlayer(this.activity);
        this.player.setPlayData(data.getString(ImagesContract.URL));
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-1, -1);
        lp.addRule(13);
        this.player.setLayoutParams(lp);
        this.player.setViewGroup(this.layout);
        setPlayerListener(this.player);
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onStop() {
        this.player.releasePlayer();
        this.player = null;
        this.activity.finish();
    }

    public void preOnCreate() {
        this.activity.requestWindowFeature(1);
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public boolean onBackPressed() {
        return false;
    }
}
