package com.musicvideo.view;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.example.com.musicvideo.test.R;

public class MainActivity extends ActionBarActivity {

	private Button musicButton;
	private Button videoButton;
	private ArrayList<MusicWrapper> mFileList;
	private ArrayList<String> mMusicSourceAryList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		setListener();
		
		setData(mMusicSourceAryList);
	}

	private void setData(ArrayList<String> musicSourcePath) {
		// mFileList.addAll("http://127.0.0.1:50080/dav/home/Music/A-Lin/%E6%88%91%E5%80%91%E6%9C%83%E6%9B%B4%E5%A5%BD%E7%9A%84/01%20Intro.m4a?session=6278047c683f80b9c8b2bc44f8a6bc43746231c9&login=user");
		// mFileList.add("http://127.0.0.1:50080/dav/home/Music/Avril%20Lavigne%20(%E8%89%BE%E8%96%87%E5%85%92)_Avril%20Lavigne%20feat_/Let%20Me%20Go/01%20Let%20Me%20Go.m4a?session=6278047c683f80b9c8b2bc44f8a6bc43746231c9&login=user");
		for (int i = 0; i < musicSourcePath.size(); i++) {
			MusicWrapper wrapper = new MusicWrapper();
			wrapper.musicPath = musicSourcePath.get(i);
			mFileList.add(wrapper);
		}
	}

	private void initViews() {
		musicButton = (Button) findViewById(R.id.music);
		videoButton = (Button) findViewById(R.id.video);
		mFileList = new ArrayList<MusicWrapper>();
		mMusicSourceAryList = new ArrayList<String>();
		mMusicSourceAryList.add("http://programmerguru.com/android-tutorial/wp-content/uploads/2013/04/hosannatelugu.mp3");
		mMusicSourceAryList.add("http://programmerguru.com/android-tutorial/wp-content/uploads/2013/04/hosannatelugu.mp3");
	}

	private void setListener() {
		musicButton.setOnClickListener(musicOnClickListener);
		videoButton.setOnClickListener(videoOnClickListener);
	}

	OnClickListener musicOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putParcelableArrayList("musicurl",
					(ArrayList<? extends Parcelable>) mFileList);
			
			intent.putExtras(bundle);
			intent.setClass(getApplicationContext(), MusicActivity.class);
			startActivity(intent);
		}
	};

	OnClickListener videoOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			// bundle.putParcelableArrayList("videourl",
			// (ArrayList<? extends Parcelable>) mFileList);

			intent.putExtras(bundle);
			intent.setClass(getApplicationContext(), MusicActivity.class);
			startActivity(intent);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
