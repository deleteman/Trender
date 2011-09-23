package com.mooveitlabs.trender.general;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class TweetsConfHelper extends SQLiteOpenHelper {

	private String SQL_CREATE = "CREATE TABLE tweetspp (cant integer)";
	
	
	
	public TweetsConfHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE);	

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS tweetspp");
		db.execSQL(SQL_CREATE);

	}

}
