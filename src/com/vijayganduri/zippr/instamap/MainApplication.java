package com.vijayganduri.zippr.instamap;

import android.app.Application;

import com.bugsense.trace.BugSenseHandler;
import com.vijayganduri.zippr.instamap.rest.InstagramRestService;

public class MainApplication extends Application{

	private InstagramRestService restService;
	private static final String TAG = MainApplication.class.getName();
	
	@Override
	public void onCreate() {		
		super.onCreate();
		BugSenseHandler.initAndStartSession(this, AppConstants.BUGSENSE_APPLICATION_KEY);
	}
	
	public InstagramRestService getRestService(){
		if(restService==null){
			restService = new InstagramRestService();
		}
		return restService;
	}

}
