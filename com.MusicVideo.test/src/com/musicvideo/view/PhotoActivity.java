package com.musicvideo.view;

import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.com.musicvideo.test.R;

public class PhotoActivity extends Activity {

	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private ViewFlipper mViewFlipper;
	private AnimationListener mAnimationListener;
	private Context mContext;

	@SuppressWarnings("deprecation")
	private final GestureDetector detector = new GestureDetector(
			new SwipeGestureDetector());
	private Button stopButton;
	private Button playButton;
	protected boolean playFlag;
	private ImageView photoView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photolayout);
		mContext = this;
		initViews();
		mViewFlipper = (ViewFlipper) this.findViewById(R.id.view_flipper);
		mViewFlipper.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(final View view, final MotionEvent event) {
				detector.onTouchEvent(event);
				return true;
			}
		});
		
		setImageViews(R.drawable.test1);

		playButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// sets auto flipping
				playFlag = !playFlag;
				mViewFlipper.setAutoStart(true);
				mViewFlipper.setFlipInterval(2000);
				mViewFlipper.startFlipping();
				if (playFlag) {
					playButton
							.setBackgroundResource(android.R.drawable.ic_media_pause);
				} else {
					mViewFlipper.stopFlipping();
					playButton
							.setBackgroundResource(android.R.drawable.ic_media_play);
				}
			}

		});

		// animation listener
		mAnimationListener = new Animation.AnimationListener() {
			public void onAnimationStart(Animation animation) {
				// animation started event
			}

			public void onAnimationRepeat(Animation animation) {
			}

			public void onAnimationEnd(Animation animation) {
				// TODO animation stopped event
			}
		};
	}

	private void setImageViews(int resource) {
		new DownloadImageTask(photoView).execute("http://icons.iconseeker.com/png/fullsize/blend/location-http-4.png");
	}

	private void initViews() {
		playButton = (Button) findViewById(R.id.play);
		photoView = (ImageView)findViewById(R.id.imageview);

	}

	class SwipeGestureDetector extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			try {
				// right to left swipe
				if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(
							mContext, R.anim.left_in));
					mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(
							mContext, R.anim.left_out));
					// controlling animation
					mViewFlipper.getInAnimation().setAnimationListener(
							mAnimationListener);
					mViewFlipper.showNext();
					setImageViews(R.drawable.test2);
					return true;
				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(
							mContext, R.anim.right_in));
					mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(
							mContext, R.anim.right_out));
					// controlling animation
					mViewFlipper.getInAnimation().setAnimationListener(
							mAnimationListener);
					mViewFlipper.showPrevious();
					setImageViews(R.drawable.test1);
					return true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return false;
		}
	}
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
		    this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
		    String urldisplay = urls[0];
		    Bitmap mIcon11 = null;
		    try {
		        InputStream in = new java.net.URL(urldisplay).openStream();
		        mIcon11 = BitmapFactory.decodeStream(in);
		    } catch (Exception e) {
		        Log.e("Error", e.getMessage());
		        e.printStackTrace();
		    }
		    return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
		    bmImage.setImageBitmap(result);
		}
	}
}
