package com.amazon.device.ads;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

final class AdVideoPlayer implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
    private static String LOGTAG = AdVideoPlayer.class.getSimpleName();
    private String contentUrl_;
    private Context context_;
    private ViewGroup.LayoutParams layoutParams_ = null;
    private AdVideoPlayerListener listener_;
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
    private boolean released_ = false;
    private VideoView videoView_ = null;
    private ViewGroup viewGroup_ = null;

    public interface AdVideoPlayerListener {
        void onComplete();

        void onError();
    }

    public AdVideoPlayer(Context context) {
        this.context_ = context;
    }

    public void setPlayData(String url) {
        this.released_ = false;
        this.contentUrl_ = url;
    }

    public void setListener(AdVideoPlayerListener listener) {
        this.listener_ = listener;
    }

    public void playVideo() {
        this.logger.d("in playVideo");
        initializeVideoView();
        loadPlayerContent();
        startPlaying();
    }

    private void initializeVideoView() {
        VideoView videoView = new VideoView(this.context_);
        videoView.setOnCompletionListener(this);
        videoView.setOnErrorListener(this);
        videoView.setLayoutParams(this.layoutParams_);
        this.videoView_ = videoView;
        this.viewGroup_.addView(this.videoView_);
    }

    private void loadPlayerContent() {
        this.videoView_.setVideoURI(Uri.parse(this.contentUrl_));
    }

    public void startPlaying() {
        this.logger.d("in startPlaying");
        displayPlayerControls();
        this.videoView_.start();
    }

    private void displayPlayerControls() {
        this.logger.d("in displayPlayerControls");
        MediaController controller = new MediaController(this.context_);
        this.videoView_.setMediaController(controller);
        controller.setAnchorView(this.videoView_);
        controller.requestFocus();
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        removePlayerFromParent();
        if (this.listener_ == null) {
            return false;
        }
        this.listener_.onError();
        return false;
    }

    public void onCompletion(MediaPlayer mp) {
        releasePlayer();
        if (this.listener_ != null) {
            this.listener_.onComplete();
        }
    }

    private void removePlayerFromParent() {
        this.logger.d("in removePlayerFromParent");
        this.viewGroup_.removeView(this.videoView_);
    }

    public void releasePlayer() {
        this.logger.d("in releasePlayer");
        if (!this.released_) {
            this.released_ = true;
            this.videoView_.stopPlayback();
            removePlayerFromParent();
        }
    }

    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        this.layoutParams_ = layoutParams;
    }

    public void setViewGroup(ViewGroup viewGroup) {
        this.viewGroup_ = viewGroup;
    }
}
