package com.mooveitlabs.trender.twitter;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import com.mooveitlabs.trender.R;


public class TrendLoader extends AsyncTask<ListActivity, Void, ArrayList<Trend>> {

	private ListActivity lst;
	@Override
	protected ArrayList<Trend> doInBackground(ListActivity... arg0) {
		// TODO Auto-generated method stub
		TwitterCOM tw = new TwitterCOM();
		ArrayList<Trend> arr = (ArrayList<Trend>) tw.getTrends();
		this.lst = arg0[0];
		return arr;
	}
	
	protected void onPostExecute(ArrayList<Trend> arr) {
		this.lst.setListAdapter(new ArrayAdapter<Trend>(this.lst, R.layout.trend_item, arr));
	}

}
