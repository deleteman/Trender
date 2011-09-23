package com.mooveitlabs.trender.twitter;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mooveitlabs.trender.R;

public class TwitterResultAdapter extends ArrayAdapter<TwitterSearchResult>  {
	
	private Context context;
	private ImageView avatarView;
	private TextView twittView;
	private List<TwitterSearchResult> data;

	public TwitterResultAdapter(Context context, int textViewResourceId,
			List<TwitterSearchResult> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.data = objects;
		// TODO Auto-generated constructor stub
	}
	
	public int getCount() {
		return this.data.size();
	}
	
	public TwitterSearchResult getItem(int i){
		return this.data.get(i);
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if(row == null) {
			 Log.d("getView", "Starting XML Row Inflation ... ");
			 LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			 row = inflater.inflate(R.layout.twitt_item, parent, false);
			 Log.d("getView", "Successfully completed XML Row Inflation!");
		}
		
		TwitterSearchResult sr = getItem(position);
		

		avatarView = (ImageView) row.findViewById(R.id.img_avatar);
		try {
			Thread t = new Thread(new AvatarLoader(avatarView, sr.avatar_url));
			t.start();
		} catch (MalformedURLException e1) {
			Log.e("ERROR", "URL mal formada", e1);
		} catch (Exception e) {
			Log.e("ERROR", "Error desconocido", e);
		}
	
		try {
			twittView = (TextView) row.findViewById(R.id.twitt_text);
			twittView.setText(sr.text);
			
			Log.i("INFO", "Deberia cargar el texto: " + sr.text);
			
		}catch(Exception ex) {
			Log.e("ERROR", "Error cargando el texto:" , ex);
		}
		return row;
		
	}

	
}
