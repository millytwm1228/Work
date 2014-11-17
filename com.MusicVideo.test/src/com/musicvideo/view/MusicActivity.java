package com.musicvideo.view;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.MediaController;

import com.example.com.musicvideo.test.R;

public class MusicActivity extends Activity implements OnPreparedListener,
		MediaController.MediaPlayerControl {
	private static final String TAG = "AudioPlayer";

	public static final String AUDIO_FILE_NAME = "audioFileName";

	private MediaPlayer mediaPlayer;
	private MediaController mediaController;
	private String audioFile;

	private Handler handler = new Handler();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.musiclayout);

		// audioFile = this.getIntent().getStringExtra(AUDIO_FILE_NAME);

		mediaPlayer = new MediaPlayer();
		mediaPlayer.reset();
		mediaPlayer.setOnPreparedListener(this);

		mediaController = new MediaController(this);

		try {
//			File file = new File("http://127.0.0.1:50080/dav/home/Music/A-Lin/%E6%88%91%E5%80%91%E6%9C%83%E6%9B%B4%E5%A5%BD%E7%9A%84/01%20Intro.m4a?session=6278047c683f80b9c8b2bc44f8a6bc43746231c9&login=user"); 
//			FileInputStream fis = new FileInputStream(file); 
//			mediaPlayer.setDataSource(fis.getFD()); 
			mediaPlayer.setDataSource("http://127.0.0.1:50080/dav/home/Music/Avril%20Lavigne%20(%E8%89%BE%E8%96%87%E5%85%92)_Avril%20Lavigne%20feat_/Let%20Me%20Go/01%20Let%20Me%20Go.m4a?session=6278047c683f80b9c8b2bc44f8a6bc43746231c9&login=user");
//			AssetFileDescriptor descriptor = getAssets().openFd("test.mp3");
//			mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
			// mediaPlayer.setDataSource(audioFile);
			mediaPlayer.prepareAsync();
			mediaPlayer.start();
		} catch (IOException e) {
			Log.e(TAG, "Could not open file " + audioFile + " for playback.", e);
		}

	}

	@Override
	protected void onStop() {
		super.onStop();
		mediaController.hide();
		mediaPlayer.stop();
		mediaPlayer.release();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// the MediaController will hide after 3 seconds - tap the screen to
		// make it appear again
		mediaController.show(0);
		return false;
	}

	// --MediaPlayerControl
	// methods----------------------------------------------------
	public void start() {
		mediaPlayer.start();
	}

	public void pause() {
		mediaPlayer.pause();
	}

	public int getDuration() {
		return mediaPlayer.getDuration();
	}

	public int getCurrentPosition() {
		return mediaPlayer.getCurrentPosition();
	}

	public void seekTo(int i) {
		mediaPlayer.seekTo(i);
	}

	public boolean isPlaying() {
		return mediaPlayer.isPlaying();
	}

	public int getBufferPercentage() {
		return 0;
	}

	public boolean canPause() {
		return true;
	}

	public boolean canSeekBackward() {
		return true;
	}

	public boolean canSeekForward() {
		return true;
	}

	// --------------------------------------------------------------------------------

	public void onPrepared(MediaPlayer mediaPlayer) {
		Log.d(TAG, "onPrepared");
		mediaController.setMediaPlayer(this);
		mediaController.setAnchorView(findViewById(R.id.musiclayout));
		mediaController.setPrevNextListeners(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("music", "next");
				
			}
		}, new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("music", "pre");

				
			}
		});
		handler.post(new Runnable() {
			public void run() {
				mediaController.setEnabled(true);
				mediaController.show(0);
			}
		});
	}

	@Override
	public int getAudioSessionId() {
		// TODO Auto-generated method stub
		return 0;
	}
}