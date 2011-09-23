package com.mooveitlabs.trender.general;

import android.content.Context;
import android.widget.Toast;

public class ToastNotifier {

	static public void notify(Context context, String txt, boolean short_duration) {
		
		int duration = (short_duration)?Toast.LENGTH_SHORT:Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, txt, duration);
		toast.show();

	}
}
