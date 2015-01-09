package com.yogeshn.hellomoon;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by yogesh on 18/08/2014.
 */
public class HelloMoonFragment extends Fragment {

    private AudioPlayer mPlayer = new AudioPlayer();
    private Button mPlayButton;
    private Button mStopButton;
    private Button mPauseButton; // challenge 1: Created a pause button.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hello_moon, parent, false);

        mPlayButton = (Button) v.findViewById(R.id.hellomoon_playButton);
        mStopButton = (Button) v.findViewById(R.id.hellomoon_stopButton);
        mPauseButton = (Button) v.findViewById(R.id.hellomoon_pauseButton); // challenge 1: get ref to pause button.

        mPlayButton.setOnClickListener(new PlayButtonListener());
        mStopButton.setOnClickListener(new StopButtonListener());
        mPauseButton.setOnClickListener(new PauseButtonListener());

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlayer.stop();
    }

    class PlayButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            mPlayer.play(getActivity());
        }
    }

    class StopButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            mPlayer.stop();
        }
    }

    class PauseButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            mPlayer.pause();
        }
    }
}
