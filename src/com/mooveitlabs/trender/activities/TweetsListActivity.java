package com.mooveitlabs.trender.activities;


import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mooveitlabs.trender.R;
import com.mooveitlabs.trender.general.Configuration;
import com.mooveitlabs.trender.twitter.Trend;
import com.mooveitlabs.trender.twitter.TweetsLoader;
import com.mooveitlabs.trender.twitter.TwitterCOM;
import com.mooveitlabs.trender.twitter.TwitterResultAdapter;
import com.mooveitlabs.trender.twitter.TwitterSearchResults;

public class TweetsListActivity extends ListActivity {
	
	private String topic;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getIntent().getExtras();
		Trend t = (Trend)b.getSerializable("trendTopic");
		this.topic = t.name; //b.getString("trendTopic");
	//	Log.i("TList", "Trend topic sent: " + topic);
		this.loadTweets();
		
	}
	
	private void loadTweets() {
		Context context = getApplicationContext();
		
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, R.string.loading, duration);
		toast.show();
		new TweetsLoader().execute(this);
	
	}
	
	public String getSearchTopic(){
		return this.topic;
	}

}
