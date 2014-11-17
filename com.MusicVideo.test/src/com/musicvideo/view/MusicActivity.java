package com.musicvideo.view;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
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

	private ArrayList<MusicWrapper> mFileList;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.musiclayout);
		mFileList = new ArrayList<MusicWrapper>();
		ArrayList<Parcelable> playlist = getIntent().getExtras().getParcelableArrayList("musicurl");
		// audioFile = this.getIntent().getStringExtra(AUDIO_FILE_NAME);
		for(int i=0;i<playlist.size();i++){
			mFileList.add((MusicWrapper) playlist.get(i));
		}
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setOnPreparedListener(this);
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		
		mediaController = new MediaController(this);

		try {
			
			mediaPlayer.setDataSource(mFileList.get(0).musicPath);
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

	public void onPrepared(final MediaPlayer mediaPlayer) {
		Log.d(TAG, "onPrepared");
		mediaController.setMediaPlayer(this);
		mediaController.setAnchorView(findViewById(R.id.musiclayout));
		mediaController.setPrevNextListeners(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("music", "next");
				try {
					mediaPlayer.reset();
					mediaPlayer.setDataSource(mFileList.get(1).musicPath);
					mediaPlayer.prepare();
					mediaPlayer.start();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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