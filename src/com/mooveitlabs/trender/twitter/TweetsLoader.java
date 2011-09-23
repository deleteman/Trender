package com.mooveitlabs.trender.twitter;

import com.mooveitlabs.trender.R;
import com.mooveitlabs.trender.activities.TweetsListActivity;
import com.mooveitlabs.trender.general.Configuration;
import com.mooveitlabs.trender.general.ToastNotifier;

import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class TweetsLoader extends AsyncTask<TweetsListActivity, Void, TwitterSearchResults> {

	private TweetsListActivity lst;
	@Override
	protected TwitterSearchResults doInBackground(TweetsListActivity... params) {
		// TODO Auto-generated method stub
		TwitterCOM tc = new TwitterCOM();
		lst = params[0];
		TwitterSearchResults results ;
		try {
			int rpp = Configuration.getInstance((Context)this.lst).getNumberOfTweetsPerPage();
			if(rpp == 0) {
				rpp = Configuration.DEFAULT_TWEETS_PP;
			}
			results =  tc.search(this.lst.getSearchTopic(), rpp);	
		//	
		} catch(Exception ex) {
			Log.e("TrendResults","Error al obtener los datos", ex);
			ToastNotifier.notify(this.lst, ex.getMessage(), false);
			results = tc.search(this.lst.getSearchTopic(), Configuration.DEFAULT_TWEETS_PP);
		}
		
	
		return results;
	
	//	return null;
	}
	
	protected void onPostExecute(TwitterSearchResults sr) {
		if (sr != null) {
			this.lst.setListAdapter(new  TwitterResultAdapter(this.lst, R.layout.twitt_item, sr.results));
		}
	}

}
