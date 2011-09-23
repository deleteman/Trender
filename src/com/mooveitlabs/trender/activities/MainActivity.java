package com.mooveitlabs.trender.activities;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.mooveitlabs.trender.R;
import com.mooveitlabs.trender.general.Configuration;
import com.mooveitlabs.trender.general.ToastNotifier;
import com.mooveitlabs.trender.twitter.Trend;
import com.mooveitlabs.trender.twitter.TrendLoader;

public class MainActivity extends ListActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	

		loadTrends();
	}

	private void loadTrends() {
		new TrendLoader().execute(this);

		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, R.string.loading, duration);
		toast.show();
		try {

			final ListView lv = getListView();
			lv.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent i = new Intent(getApplicationContext(),
							TweetsListActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("trendTopic",
							(Trend) lv.getItemAtPosition(position));

					i.putExtras(bundle);
					startActivity(i);
				}

			});

		} catch (Exception ex) {
			ToastNotifier.notify(this, ex.getMessage(), false);
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.trend_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The code below needs some serious refactoring...
		try {
			switch (item.getItemId()) {
			case R.id.exit_opt:
				this.finish();
				return true;
			case R.id.refresh_opt:
				loadTrends();
				return true;
			case R.id.tweets_15:
				item.setChecked(true);
				Configuration.getInstance((Context) this)
						.setNumberOfTweetsPerPage(15);
				ToastNotifier.notify(this, getString(R.string.conf_saved), true);
				return true;
			case R.id.tweets_25:
				item.setChecked(true);
				Configuration.getInstance((Context) this)
						.setNumberOfTweetsPerPage(25);
				ToastNotifier.notify(this, getString(R.string.conf_saved), true);
				return true;
			case R.id.tweets_45:
				item.setChecked(true);
				Configuration.getInstance((Context) this)
						.setNumberOfTweetsPerPage(45);
				ToastNotifier.notify(this, getString(R.string.conf_saved), true);
				return true;
			case R.id.tweets_65:
				item.setChecked(true);
				Configuration.getInstance((Context) this)
						.setNumberOfTweetsPerPage(65);
				ToastNotifier.notify(this, getString(R.string.conf_saved), true);
				return true;
			default:
				return super.onOptionsItemSelected(item);
			}
			
		} catch (Exception e) {

			ToastNotifier.notify(this, e.getMessage(), false);
		}
		return true;

	}

}