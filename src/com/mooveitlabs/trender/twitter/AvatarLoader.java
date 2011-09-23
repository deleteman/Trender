package com.mooveitlabs.trender.twitter;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

public class AvatarLoader implements Runnable {
	
	private ImageView avatar;
	private URL avatar_url;
	private Bitmap bmp_avatar;
	
	public AvatarLoader(ImageView view, String url) throws MalformedURLException {
		this.avatar = view;
		this.avatar_url = new URL(url);
	}

	@Override
	public void run() {
		
		try {
			this.bmp_avatar = BitmapFactory.decodeStream((InputStream) this.avatar_url.getContent());
			handler.sendEmptyMessage(0);
			
		} catch (IOException e) {
			Log.e("ERROR", "No se cargó la imagen", e);
		}
	}
	
	  private Handler handler = new Handler() {
          @Override
          public void handleMessage(Message msg) {
        	  avatar.setImageBitmap(bmp_avatar);

          }
  };

}
