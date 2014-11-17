package com.example.intent;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private static final String LINK_PACKAGE_NAME = "jp.naver.line.android";
	private TextView textView;
	private TextView MapTextView;
	private TextView calendarTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		setListener();

	}

	private void setListener() {
		textView.setOnClickListener(onClickListener);
		MapTextView.setOnClickListener(mapClickListener);
		calendarTextView.setOnClickListener(calendarListener);
	}
	
	private View.OnClickListener calendarListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
//			Intent calendarIntent = new Intent(Intent.ACTION_INSERT, Events.CONTENT_URI);
//			Calendar beginTime = Calendar.getInstance().set(2012, 0, 19, 7, 30);
//			Calendar endTime = Calendar.getInstance().set(2012, 0, 19, 10, 30);
//			calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
//			calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
//			calendarIntent.putExtra(Events.TITLE, "Ninja class");
//			calendarIntent.putExtra(Events.EVENT_LOCATION, "Secret dojo");
		}
	};

	private View.OnClickListener onClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent LaunchIntent = getPackageManager()
					.getLaunchIntentForPackage(LINK_PACKAGE_NAME);
			startActivity(LaunchIntent);

		}

	};
	
	private View.OnClickListener mapClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// Map point based on address
			Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
			// Or map point based on latitude/longitude
			// Uri location = Uri.parse("geo:37.422219,-122.08364?z=14"); // z param is zoom level
			Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
			startActivity(mapIntent);
			
		}
	};

	private void initViews() {
		textView = (TextView) findViewById(R.id.textView);
		MapTextView = (TextView) findViewById(R.id.map);
		calendarTextView = (TextView)findViewById(R.id.calendar);

	}

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
