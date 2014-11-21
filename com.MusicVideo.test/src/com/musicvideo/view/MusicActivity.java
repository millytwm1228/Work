package com.musicvideo.view;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
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

	private MediaPlayer mediaPlayer;

	private MediaController mediaController;

	private Handler handler = new Handler();

	private ArrayList<DataWrapper> mFileList;

	private int mPlayPosition;

	private ProgressDialog pDialog;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.musiclayout);

		initViews();

		getIntentValue();

		initMediaplayer(mFileList.get(0).filePath);

		setMediaListener();

	}

	private void initMediaplayer(String path) {
		showDialog();
		mediaPlayer = new MediaPlayer();
		mediaPlayer.reset();
		mediaPlayer.setOnPreparedListener(this);
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

		mediaController = new MediaController(this);

		try {
			// if(mFileList.size() > 0)
			mediaPlayer.setDataSource(path);
			// mPlayPosition++;
			// play songs (assets)
			// AssetFileDescriptor descriptor = getAssets().openFd("test.mp3");
			// mediaPlayer.setDataSource(descriptor.getFileDescriptor(),
			// descriptor.getStartOffset(), descriptor.getLength());
			mediaPlayer.prepareAsync();
//			mediaPlayer.start();
		} catch (IOException e) {
		}
	}
	
	

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mediaPlayer.release();
	}

	private void initViews() {
		mFileList = new ArrayList<DataWrapper>();
	}

	private void getIntentValue() {
		ArrayList<Parcelable> playlist = getIntent().getExtras()
				.getParcelableArrayList("musicurl");

		for (int i = 0; i < playlist.size(); i++) {
			mFileList.add((DataWrapper) playlist.get(i));
		}
	}

	private void setMediaListener() {
		mediaPlayer
				.setOnBufferingUpdateListener(new OnBufferingUpdateListener() {

					@Override
					public void onBufferingUpdate(MediaPlayer mp, int percent) {
						Log.i(TAG, "onBufferingUpdate " + percent);

					}
				});
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				Log.i(TAG, "OnCompletionListener ");
				showNext();

			}
		});
		mediaPlayer.setOnErrorListener(new OnErrorListener() {

			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				Log.i(TAG, "onError " + what + " extra " + extra);
				return false;
			}
		});
		mediaPlayer.setOnInfoListener(new OnInfoListener() {

			@Override
			public boolean onInfo(MediaPlayer mp, int what, int extra) {
				Log.i(TAG, "onInfo " + what + " extra " + extra);
				return false;
			}
		});

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
		if (pDialog != null)
			pDialog.dismiss();
		mediaController.setMediaPlayer(this);
		mediaController.setAnchorView(findViewById(R.id.musiclayout));
		mediaController.setPrevNextListeners(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i("music", "next");
				showNext();

			}
		}, new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i("music", "pre");
				showPrevious();

			}
		});
		handler.post(new Runnable() {
			public void run() {
				mediaController.setEnabled(true);
				mediaController.show(0);
			}
		});
		mediaPlayer.start();

	}

	@Override
	public int getAudioSessionId() {
		// TODO Auto-generated method stub
		return 0;
	}

	// private void playSongs(final MediaPlayer mediaPlayer , int position) {
	// try {
	// mediaPlayer.reset();
	// mediaPlayer.setDataSource(mFileList.get(position).filePath);
	// mediaPlayer.prepare();
	// mediaPlayer.start();
	// } catch (IllegalArgumentException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (SecurityException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IllegalStateException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	private void showDialog() {
		// Create a progressbar
		pDialog = new ProgressDialog(MusicActivity.this);
		// Set progressbar title
		pDialog.setTitle("Please wait...");
		// Set progressbar message
		pDialog.setMessage("Buffering...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(false);
		// Show progressbar
		pDialog.show();
	}

	private void showNext() {
		if (mPlayPosition + 1 < mFileList.size()) {
			// playSongs(mediaPlayer, mPlayPosition);
			initMediaplayer(mFileList.get(++mPlayPosition).filePath);
		} else {
			initMediaplayer(mFileList.get(mPlayPosition = 0).filePath);

		}
	}

	private void showPrevious() {
		if (mPlayPosition - 1 < mFileList.size()) {
			// playSongs(mediaPlayer, mPlayPosition);
			initMediaplayer(mFileList.get(--mPlayPosition).filePath);
		} else {
			initMediaplayer(mFileList.get(mPlayPosition = mFileList
					.size() - 1).filePath);

		}
	}

}