package com.musicvideo.view;

import java.util.ArrayList;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.View.OnClickListener;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.com.musicvideo.test.R;

public class VideoActivity extends Activity{

	private VideoView videoView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videolayout);
		getIntentValues();
		initViews();
		initController();
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
			}
			
		};

		videoView.setMediaController(mediaController);
		
	}


	private void initViews() {
		videoView = (VideoView)findViewById(R.id.videolayout);
		
	}


	private void getIntentValues() {
		Bundle bundle = getIntent().getExtras();
		ArrayList<Parcelable> playList = null;
		if (bundle != null)
			playList = bundle.getParcelableArrayList("videourl");
		
		playVideo(playList);
	}


	private void playVideo(ArrayList<Parcelable> playList) {
		String[] videoPlay = (String[]) playList.toArray();
		videoView.setVideoPath(videoPlay[0]);
		
	}
}
