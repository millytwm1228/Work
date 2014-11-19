package com.musicvideo.view;

import java.util.ArrayList;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.com.musicvideo.test.R;

public class VideoActivity extends Activity {

	protected static final String TAG = VideoActivity.class.getName();

	private VideoView videoView;

	private ArrayList<DataWrapper> mFileList;

	protected int mPlayPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videolayout);

		initViews();

		getIntentValue();

		initController();

		playVideo(0);

		setVideoEvent();
	}

	private void setVideoEvent() {
		videoView.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				mp.start();
			}
		});

	}

	private void initController() {
		final MediaController mediaController = new MediaController(this) {

			@Override
			public void setMediaPlayer(MediaPlayerControl player) {
				super.setMediaPlayer(player);

			}

			@Override
			public void show(int timeout) {
				super.show(0);
			}

			@Override
			public void hide() {
				// TODO Auto-generated method stub
				super.hide();
			}

			@Override
			public boolean dispatchKeyEvent(KeyEvent event) {

				// TODO Auto-generated method stub
				if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
					finish();
				}

				return super.dispatchKeyEvent(event);
			}

			@Override
			public void setPrevNextListeners(OnClickListener next,
					OnClickListener prev) {
				// TODO Auto-generated method stub
				super.setPrevNextListeners(next, prev);
				Log.i(TAG, "setPrevNextListeners " + next + " " + prev);

			}

		};

		mediaController.setPrevNextListeners(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i(TAG, "setPrevNextListeners next ");
				if (++mPlayPosition < mFileList.size()) {
					playVideo(mPlayPosition);
				}

			}
		}, new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i(TAG, "setPrevNextListeners pre ");
				if (--mPlayPosition < mFileList.size()) {
					playVideo(mPlayPosition);
				}
			}
		});
		videoView.setMediaController(mediaController);
	}

	private void initViews() {
		videoView = (VideoView) findViewById(R.id.videolayout);
		mFileList = new ArrayList<DataWrapper>();
	}

	private void getIntentValue() {
		ArrayList<Parcelable> playlist = getIntent().getExtras()
				.getParcelableArrayList("videourl");

		for (int i = 0; i < playlist.size(); i++) {
			mFileList.add((DataWrapper) playlist.get(i));
		}
	}

	private void playVideo(int position) {
		videoView.setVideoPath(mFileList.get(position).filePath);

	}
}
