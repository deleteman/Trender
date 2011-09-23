package com.mooveitlabs.trender.general;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Configuration extends ContextWrapper{
	
	static public int DEFAULT_TWEETS_PP = 15;
	static private Configuration instance;
	static private String CONF_NAME = "TrenderConf";
	
	static public Configuration getInstance(Context c) {
		if (instance == null) {
			instance = new Configuration(c);
		}
		return instance;
	}

	public Configuration(Context base) {
		super(base);
		
	}

	public void setNumberOfTweetsPerPage(int n) throws Exception {
		TweetsConfHelper tch = new TweetsConfHelper(this, "dbConf", null, 1);
		SQLiteDatabase db = tch.getWritableDatabase();
		SQLiteDatabase dbr = tch.getReadableDatabase();
		try {
			Cursor c = dbr.rawQuery("Select cant from tweetspp", null);
			boolean first_time = c.getCount() == 0;
			
			if(first_time) {
				db.execSQL("INSERT INTO tweetspp (cant) values (" + n + ")");
			} else {
				db.execSQL("UPDATE tweetspp set cant = " + n );
			}
			db.close();
			dbr.close();
		} catch(Exception e) {
			throw new Exception("Error guardando la configuración: " + e.getMessage());
		}

	}
	
	public int getNumberOfTweetsPerPage() throws Exception{
		TweetsConfHelper tch = new TweetsConfHelper(this, "dbConf", null, 1);
		SQLiteDatabase db = tch.getReadableDatabase();
		
		try {
			Cursor cursor = db.rawQuery("Select cant from tweetspp", null);
			int i;
			if(cursor.moveToFirst()) {
				i = cursor.getInt(0);
			} else {
				i = 0;
			}
			db.close();
			return i;
			
		} catch (Exception e) {
			throw new Exception("Error cargando datos de configuración: " + e.getMessage());
		}
	}
}
