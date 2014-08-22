package com.vijayganduri.zippr.instamap.activity;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Window;
import com.squareup.picasso.Picasso;
import com.vijayganduri.zippr.instamap.R;
import com.vijayganduri.zippr.instamap.beans.Data;
import com.vijayganduri.zippr.instamap.beans.PhotoResponse;
import com.vijayganduri.zippr.instamap.rest.InstagramRestService;

public class HomeFeedActivity extends SherlockActivity implements OnItemClickListener{

	GridView gridView;

	ImageGridAdapter adapter;
	private InstagramRestService restService;
	private FetchImages asyncFetchImages;

	private static final String TAG = HomeFeedActivity.class.getName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_home_feed);

		gridView = (GridView)findViewById(R.id.grid_view);

		restService = new InstagramRestService();
		adapter = new ImageGridAdapter(this, android.R.layout.simple_list_item_1);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(this);

		asyncFetchImages = new FetchImages();
		asyncFetchImages.execute();
	}

	private class FetchImages extends AsyncTask< Void, Void, PhotoResponse>{

		@Override
		protected PhotoResponse doInBackground(Void... params) {
			try{
				return restService.getPopularPhotos();
			}catch (Exception e) {
				return null;
			}
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			setSupportProgressBarIndeterminateVisibility(true);
		}

		@Override
		protected void onPostExecute(PhotoResponse result) {
			super.onPostExecute(result);			
			setSupportProgressBarIndeterminateVisibility(false);
			if(result == null || result.getData()==null){
				showToast("Could not get images.");
				return;
			}
			adapter.addItems(result.getData());
			adapter.notifyDataSetChanged();			
		}
	}

	private void showToast(String text){
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
		Intent intent = new Intent(this, DetailMapActivity.class);
		//intent.putExtra(AppConstants.INTENT_POS, position);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

	public class ImageGridAdapter extends ArrayAdapter<Data>{

		private Context context;

		public ImageGridAdapter(Context context, int layoutResId){
			super(context, layoutResId);
			this.context = context;
		}

		public void addItems(List<Data> data){
			Log.d(TAG, "response "+data);
			for(Data item:data){
				add(item);
			}
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			if (convertView == null) {
				convertView = getLayoutInflater().inflate(R.layout.image_thumbnail, null);
				holder = new ViewHolder();
				holder.image = (ImageView)convertView.findViewById(R.id.image_view);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder)convertView.getTag();
			}

			Data data = getItem(position);

			Picasso.with(context).load(data.getImages().getLow_resolution().getUrl())
				.into(holder.image);

			return convertView;
		}

	}

	static class ViewHolder {
		ImageView image;
	}
}