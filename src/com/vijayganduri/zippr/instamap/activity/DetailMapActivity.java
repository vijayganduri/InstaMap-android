package com.vijayganduri.zippr.instamap.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.vijayganduri.zippr.instamap.AppConstants;
import com.vijayganduri.zippr.instamap.R;
import com.vijayganduri.zippr.instamap.beans.Data;

public class DetailMapActivity extends SherlockFragmentActivity{

	private static final String TAG = DetailMapActivity.class.getName();

	private GoogleMap gMap;
	private Data data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_info);

		ActionBar actionbar = getSupportActionBar();
		actionbar.setHomeButtonEnabled(true);
		actionbar.setDisplayHomeAsUpEnabled(true);

		if(getIntent()==null ||  getIntent().getExtras()==null){
			return;
		}

		Bundle bundle = getIntent().getExtras();
		data = (Data) bundle.getSerializable(AppConstants.INTENT_DATA);

		gMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_view)).getMap();
		setupMarker();

	}
	
	private void setupMarker(){
		if(data.getLocation()==null){
			showToast("No location available for the photo");
			return;
		}

		LatLng markerPos = new LatLng(data.getLocation().getLatitude(), data.getLocation().getLongitude());
		MarkerOptions markerOptions = new MarkerOptions().position(markerPos)
				.title(data.getCaption().getText());
		gMap.addMarker(markerOptions);
		
		Picasso.with(this).load(data.getImages().getThumbnail().getUrl()).into(target);

		moveToPosition(markerPos);
	}
	
	private void drawMarkerWithImage(BitmapDescriptor bitmapDescriptor){
		gMap.clear();
		LatLng markerPos = new LatLng(data.getLocation().getLatitude(), data.getLocation().getLongitude());
		MarkerOptions markerOptions = new MarkerOptions().position(markerPos)
				.title(data.getCaption().getText())
				.icon(bitmapDescriptor);
		gMap.addMarker(markerOptions);
	}

	private Target target = new Target() {
		@Override
		public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
			drawMarkerWithImage(BitmapDescriptorFactory.fromBitmap(bitmap));		
		}

		@Override
		public void onBitmapFailed(Drawable arg0) {

		}

		@Override
		public void onPrepareLoad(Drawable arg0) {

		}
	};

	private void moveToPosition(LatLng latLng){
		gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
		gMap.animateCamera(CameraUpdateFactory.zoomIn());
		gMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
	}

	private void showToast(String text){
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putSerializable(AppConstants.INTENT_DATA, data);
		super.onSaveInstanceState(outState);

	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		data =  (Data) savedInstanceState.getSerializable(AppConstants.INTENT_DATA);
	}

}