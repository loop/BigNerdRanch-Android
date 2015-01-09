package com.yogeshn.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

/**
 * Created by yogesh on 18/08/2014.
 */
public class AudioPlayer {

    private MediaPlayer mPlayer;
    private int mCurrentPosition = -1;
    private Uri resourceUri = Uri.parse("android.resource://" + "com.yogeshn.hellomoon/raw/apollo_17_stroll");

    public void stop() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void play(Context c) {
        stop();

        mPlayer = MediaPlayer.create(c, R.raw.one_small_step);

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stop();
            }
        });
        if(mCurrentPosition > -1) {
            mPlayer.seekTo(mCurrentPosition);
        }
        mPlayer.start();
    }

    public void pause() {
        if (mPlayer != null) {
            mCurrentPosition = mPlayer.getCurrentPosition();
            mPlayer.pause();
        }
    }
}
