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
	private ArrayList<DataWrapper> mMusicList;
	private ArrayList<DataWrapper> mVideoList;
	private ArrayList<String> mMusicSourceAryList;
	private ArrayList<String> mVideoSourceAryList;
	private Button photoButton;
	private ArrayList<DataWrapper> mPhotoList;
	private ArrayList<String> mPhotoSourceAryList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();

		addMusicData();

		addVideoData();

		addPhotoData();

		setListener();

		setMusicData(mMusicSourceAryList);

		setVideoData(mVideoSourceAryList);

		setPhotoData(mPhotoSourceAryList);
	}

	private void setMusicData(ArrayList<String> musicSourcePath) {
		// mFileList.addAll("http://127.0.0.1:50080/dav/home/Music/A-Lin/%E6%88%91%E5%80%91%E6%9C%83%E6%9B%B4%E5%A5%BD%E7%9A%84/01%20Intro.m4a?session=6278047c683f80b9c8b2bc44f8a6bc43746231c9&login=user");
		// mFileList.add("http://127.0.0.1:50080/dav/home/Music/Avril%20Lavigne%20(%E8%89%BE%E8%96%87%E5%85%92)_Avril%20Lavigne%20feat_/Let%20Me%20Go/01%20Let%20Me%20Go.m4a?session=6278047c683f80b9c8b2bc44f8a6bc43746231c9&login=user");
		for (int i = 0; i < musicSourcePath.size(); i++) {
			DataWrapper wrapper = new DataWrapper();
			wrapper.filePath = musicSourcePath.get(i);
			mMusicList.add(wrapper);
		}
	}

	private void setVideoData(ArrayList<String> videoSourcePath) {
		// mFileList.addAll("http://127.0.0.1:50080/dav/home/Music/A-Lin/%E6%88%91%E5%80%91%E6%9C%83%E6%9B%B4%E5%A5%BD%E7%9A%84/01%20Intro.m4a?session=6278047c683f80b9c8b2bc44f8a6bc43746231c9&login=user");
		// mFileList.add("http://127.0.0.1:50080/dav/home/Music/Avril%20Lavigne%20(%E8%89%BE%E8%96%87%E5%85%92)_Avril%20Lavigne%20feat_/Let%20Me%20Go/01%20Let%20Me%20Go.m4a?session=6278047c683f80b9c8b2bc44f8a6bc43746231c9&login=user");
		for (int i = 0; i < videoSourcePath.size(); i++) {
			DataWrapper wrapper = new DataWrapper();
			wrapper.filePath = videoSourcePath.get(i);
			mVideoList.add(wrapper);
		}
	}

	private void setPhotoData(ArrayList<String> photoSourcePath) {
		// mFileList.addAll("http://127.0.0.1:50080/dav/home/Music/A-Lin/%E6%88%91%E5%80%91%E6%9C%83%E6%9B%B4%E5%A5%BD%E7%9A%84/01%20Intro.m4a?session=6278047c683f80b9c8b2bc44f8a6bc43746231c9&login=user");
		// mFileList.add("http://127.0.0.1:50080/dav/home/Music/Avril%20Lavigne%20(%E8%89%BE%E8%96%87%E5%85%92)_Avril%20Lavigne%20feat_/Let%20Me%20Go/01%20Let%20Me%20Go.m4a?session=6278047c683f80b9c8b2bc44f8a6bc43746231c9&login=user");

		for (int i = 0; i < photoSourcePath.size(); i++) {
			DataWrapper wrapper = new DataWrapper();
			wrapper.filePath = photoSourcePath.get(i);
			mPhotoList.add(wrapper);
		}
	}

	private void initViews() {
		musicButton = (Button) findViewById(R.id.music);
		videoButton = (Button) findViewById(R.id.video);
		photoButton = (Button) findViewById(R.id.photo);
		mMusicList = new ArrayList<DataWrapper>();
		mVideoList = new ArrayList<DataWrapper>();
		mPhotoList = new ArrayList<DataWrapper>();
		mMusicSourceAryList = new ArrayList<String>();
		mVideoSourceAryList = new ArrayList<String>();
		mPhotoSourceAryList = new ArrayList<String>();

	}

	private void addMusicData() {
		mMusicSourceAryList
				.add("http://192.168.2.152/dav/home/Music/%E4%BF%A1%E6%A8%82%E5%9C%98/%E6%8C%91%E4%BF%A1/01%20%E5%BE%9E%E4%BB%8A%E4%BB%A5%E5%BE%8C.m4a?session=9c32b9b41d76be25e91a4507e55f5deea5ce06e2&login=user");
		mMusicSourceAryList
				.add("http://192.168.2.152/dav/home/Music/%E4%BF%A1%E6%A8%82%E5%9C%98/%E6%8C%91%E4%BF%A1/01%20%E5%BE%9E%E4%BB%8A%E4%BB%A5%E5%BE%8C.m4a?session=9c32b9b41d76be25e91a4507e55f5deea5ce06e2&login=user");
		mMusicSourceAryList
				.add("http://192.168.2.152/dav/home/Music/%E4%BF%A1%E6%A8%82%E5%9C%98/%E6%8C%91%E4%BF%A1/01%20%E5%BE%9E%E4%BB%8A%E4%BB%A5%E5%BE%8C.m4a?session=9c32b9b41d76be25e91a4507e55f5deea5ce06e2&login=user");

	}

	private void addVideoData() {
		mVideoSourceAryList
				.add("http://192.168.2.152/dav/home/Videos/a00006.mp4?session=f0f9f8216c6c543e82674da685cbfa5cb0b54af0&login=user");
		mVideoSourceAryList
				.add("http://192.168.2.152/dav/home/Videos/a00006.mp4?session=f0f9f8216c6c543e82674da685cbfa5cb0b54af0&login=user");
		mVideoSourceAryList
				.add("http://192.168.2.152/dav/home/Videos/a00006.mp4?session=f0f9f8216c6c543e82674da685cbfa5cb0b54af0&login=user");

	}

	private void addPhotoData() {
		

		mPhotoSourceAryList
				.add("http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg");
		mPhotoSourceAryList
				.add("http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg");
		mPhotoSourceAryList
				.add("http://img.my.csdn.net/uploads/201407/26/1406383291_8239.jpg");

		mPhotoSourceAryList
		.add("http://img.my.csdn.net/uploads/201407/26/1406383275_3977.jpg");
		mPhotoSourceAryList
		.add("http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg");
		mPhotoSourceAryList
		.add("http://img.my.csdn.net/uploads/201407/26/1406383264_3954.jpg");
		mPhotoSourceAryList
		.add("http://img.my.csdn.net/uploads/201407/26/1406383264_4787.jpg");
		mPhotoSourceAryList
		.add("http://img.my.csdn.net/uploads/201407/26/1406383264_8243.jpg");
		mPhotoSourceAryList
		.add("http://img.my.csdn.net/uploads/201407/26/1406383248_3693.jpg");
				

	}

	private void setListener() {
		musicButton.setOnClickListener(musicOnClickListener);
		videoButton.setOnClickListener(videoOnClickListener);
		photoButton.setOnClickListener(photoOnClickListener);
	}

	OnClickListener musicOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putParcelableArrayList("musicurl",
					(ArrayList<? extends Parcelable>) mMusicList);

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
			bundle.putParcelableArrayList("videourl",
					(ArrayList<? extends Parcelable>) mVideoList);

			intent.putExtras(bundle);
			intent.setClass(getApplicationContext(), VideoActivity.class);
			startActivity(intent);
		}
	};

	OnClickListener photoOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putParcelableArrayList("photourl",
					(ArrayList<? extends Parcelable>) mPhotoList);

			intent.putExtras(bundle);
			intent.setClass(getApplicationContext(), PhotoActivity.class);
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
