package com.musicvideo.view;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
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

	private ArrayList<String> mVideoList;

	private MediaController mediacontroller;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videolayout);

		initViews();

		getIntentValue();

		setVideoEvent(mVideoList.get(0));

		// downloadVideo();

		// initController();

	}

	// private void downloadVideo() {
	// String[] stockArr = new String[mVideoList.size()];
	// stockArr = mVideoList.toArray(stockArr);
	// new DownloadVideoTask().execute(mVideoList.get(0));
	//
	// }

	private void setVideoEvent(String url) {
		// videoView.setOnPreparedListener(new OnPreparedListener() {
		//
		// @Override
		// public void onPrepared(MediaPlayer mp) {
		// mp.start();
		// }
		// });
		showDialog();

		try {
			// Start the MediaController
			mediacontroller = new MediaController(this) {

				@Override
				public void show(int timeout) {
					// TODO Auto-generated method stub
					super.show(0);
				}

//				@Override
//				public void hide() {
//					// TODO Auto-generated method stub
//					super.hide();
//				}
				

			};
			// Get the URL from String VideoURL
			Uri video = Uri.parse(url);
			videoView.setMediaController(mediacontroller);
			videoView.setVideoURI(video);

		} catch (Exception e) {
			Log.e("Error", e.getMessage());
			e.printStackTrace();
		}

		videoView.requestFocus();
		videoView.setOnPreparedListener(new OnPreparedListener() {
			// Close the progress bar and play the video
			public void onPrepared(MediaPlayer mp) {
				pDialog.dismiss();
				videoView.start();
			}
		});
		mediacontroller.setPrevNextListeners(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i(TAG, "setPrevNextListeners next ");
				showNext();

			}
		}, new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i(TAG, "setPrevNextListeners pre ");
				showPrevious();

			}
		});
		videoView.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				showNext();
				mediacontroller.show();
				
			}
		});

	}

	private void initViews() {
		videoView = (VideoView) findViewById(R.id.videolayout);
		mFileList = new ArrayList<DataWrapper>();
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

		}

	};

	private ProgressDialog pDialog;

	private void getIntentValue() {
		ArrayList<Parcelable> playlist = getIntent().getExtras()
				.getParcelableArrayList("videourl");
		mVideoList = new ArrayList<String>();

		for (int i = 0; i < playlist.size(); i++) {
			mFileList.add((DataWrapper) playlist.get(i));
		}

		for (int i = 0; i < mFileList.size(); i++) {
			mVideoList.add(mFileList.get(i).filePath);
		}

	}

	private class DownloadVideoTask extends AsyncTask<String, Void, String> {
		public int iFileSize = 0;
		public double dReadSum = 0;
		public boolean bIsDownload = false;
		private int count;

		public DownloadVideoTask() {
		}

		protected String doInBackground(String... strUrlFile) {
			URL urlFileLocation = null;
			HttpURLConnection connFile = null;
			try {
				int iUrlCount = strUrlFile.length;
				for (int i = 0; i < iUrlCount; i++) {
					urlFileLocation = new URL(strUrlFile[i]);
					connFile = (HttpURLConnection) urlFileLocation
							.openConnection();
					connFile.setDoInput(true);

					InputStream is = connFile.getInputStream();
					iFileSize = connFile.getContentLength();

					byte[] buffer = new byte[1024];
					int len1 = 0;
					dReadSum = 0;

					while ((len1 = is.read(buffer)) != -1) {
						dReadSum += len1;
						// publishProgress(dReadSum * 100 / iFileSize);

					}
					count = 0;
					Log.i("video", "fileSize " + iFileSize + " readsum "
							+ dReadSum + " " + (iFileSize == dReadSum) + " "
							+ strUrlFile[i]);
					count++;
				}

			} catch (Exception e) {
				Log.d("main", "download---" + e.toString());
			}

			return "";
		}

		protected void onPostExecute(String result) {
			Log.i("video", "FINISH!!! " + result);

			if (iFileSize == dReadSum) {
				Log.i("video", "finish");
			}

		}

	}

	private void showDialog() {
		// Create a progressbar
		pDialog = new ProgressDialog(VideoActivity.this);
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
			setVideoEvent(mVideoList.get(++mPlayPosition));
		} else {
			setVideoEvent(mVideoList.get(mPlayPosition = 0));
		}
	}

	private void showPrevious() {
		if (mPlayPosition - 1 < mFileList.size()
				&& mPlayPosition - 1 >= 0) {
			setVideoEvent(mVideoList.get(--mPlayPosition));
		} else {
			mPlayPosition = mVideoList.size() - 1;
			setVideoEvent(mVideoList.get(mPlayPosition));

		}
	}
}
