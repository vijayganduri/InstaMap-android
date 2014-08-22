package com.vijayganduri.zippr.instamap.activity;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;
import com.vijayganduri.zippr.instamap.R;

public class DetailMapActivity extends SherlockActivity{

    private static final String TAG = DetailMapActivity.class.getName();
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_feed);	
	}

}