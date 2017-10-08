package com.whu.readshare.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	public static String TABLE_NAME_USER = "rd_user";
	public static String TABLE_NAME_CONTENT = "rd_content";
	public static String VIEW_NAME_CONTENTALL = "rd_contentall";
	
	private static String DATABASE_NAME = "readshare.db";
	private static int DATABASE_VERSION = 1;
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TABLE_NAME_USER + " (user_id integer primary key autoincrement, username text, password text)");
        db.execSQL("create table if not exists " + TABLE_NAME_CONTENT + " (content_id integer primary key autoincrement, user_id integer, title text, content text)");
        db.execSQL("CREATE VIEW if not exists " + VIEW_NAME_CONTENTALL + "  as select content_id,username,title,content from rd_user,rd_content where rd_user.[user_id] = rd_content.[user_id]");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}
}
