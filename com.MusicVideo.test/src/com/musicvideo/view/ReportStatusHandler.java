package com.musicvideo.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ReportStatusHandler extends Handler
{
	public static final String tag = "TestHandler2";
	private VideoActivity parentActivity = null; 
	
	public ReportStatusHandler(VideoActivity inParentActivity)
	{
		parentActivity = inParentActivity;
	}

	@Override
	public void handleMessage(Message msg) 
	{
//		String pm = Utils.getStringFromABundle(msg.getData());
				
//		Log.d(tag,pm);
		Utils.logThreadSignature();
	}

	
}
