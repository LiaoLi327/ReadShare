package com.whu.readshare.database;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseUtil {

	private static SQLiteDatabase db;
	private static DatabaseUtil dbUtil;
	private DatabaseHelper dbHelper;
	
	private DatabaseUtil(Context context){
		db = getOpenedDB(context);
	}
	
	public static DatabaseUtil getInstance(Context context){
		if(dbUtil == null){
			dbUtil = new DatabaseUtil(context.getApplicationContext());
		}
		return dbUtil;
	}
	
    /**
     * 获取打开的本地数据库
     *
     * @param context：Context
     * @return SQLiteDatabase
     */
    public synchronized SQLiteDatabase getOpenedDB(Context context) {
        if (db == null) {
        	dbHelper = new DatabaseHelper(context.getApplicationContext());
            db = dbHelper.getWritableDatabase();
        }
        return db;
    }

    /**
     * 关闭本地数据库
     */
    public void closeDB() {
        if (db != null) {
            db.close();
            db = null;
        }
    }
	
	public boolean insertUser(String userName, String password) {
		
		if(db == null){
			db = dbHelper.getWritableDatabase();
		}
		
		ContentValues values = new ContentValues();
		values.put("username", userName);
		values.put("password", password);
		
		long id = db.insert(DatabaseHelper.TABLE_NAME_USER, null, values);
		
		if(id > 0){
			return true;
		}
		
		return false;
	}

	public boolean loginQueryUser(String userName, String password) {
		
		if(db == null){
			db = dbHelper.getWritableDatabase();
		}
		
		Cursor cursor = db.query(DatabaseHelper.TABLE_NAME_USER, null, " username = ? and password = ?", new String[]{userName,password}, null, null, null);
		
		if(cursor.getCount() > 0){
			return true;
		}
		
		return false;
	}
	
	public boolean registerQueryUser(String userName){
		if(db == null){
			db = dbHelper.getWritableDatabase();
		}
		
		Cursor cursor = db.query(DatabaseHelper.TABLE_NAME_USER, null, " username = ?", new String[]{userName}, null, null, null);
		
		if(cursor.getCount() > 0){
			return true;
		}
		
		return false;
	}
	
	public Cursor queryUser(String userName, String password){
		if(db == null){
			db = dbHelper.getWritableDatabase();
		}
		
		Cursor cursor = db.query(DatabaseHelper.TABLE_NAME_USER, null, " username = ? and password = ?", new String[]{userName,password}, null, null, null);
		
		return cursor;
	}
	
	public boolean insertContent(int userID,String title,String content){
		
		if(db == null){
			db = dbHelper.getWritableDatabase();
		}
		
		ContentValues values = new ContentValues();
		values.put("user_id", userID);
		values.put("title", title);
		values.put("content", content);
		
		long id = db.insert(DatabaseHelper.TABLE_NAME_CONTENT, null, values);
		
		if(id > 0){
			return true;
		}
		
		return false;
	}
	
    // 查询所有数据  
    public ArrayList<HashMap<String, Object>> getAllContent() {  
        Cursor c = db.query(DatabaseHelper.VIEW_NAME_CONTENTALL, null, null, null, null, null, null);
        int columnsSize = c.getColumnCount();  
        ArrayList<HashMap<String, Object>>  listData = new ArrayList<HashMap<String, Object>>();  
        // 获取表的内容  
        while (c.moveToNext()) {  
            HashMap<String, Object> map = new HashMap<String, Object>();  
            for (int i = 0; i < columnsSize; i++) {  
                map.put("content_id", c.getString(0));  
                map.put("username", c.getString(1));  
                map.put("title", c.getString(2));  
                map.put("content", c.getString(3));  
            }  
            listData.add(map);  
        }  
        
        return listData;
    } 
    
    // 查询所有数据  
    public Cursor getContent(String contentID) {  
    	
        Cursor c = db.query(DatabaseHelper.VIEW_NAME_CONTENTALL, null, " content_id = ? ", new String[]{contentID}, null, null, null);
        
        return c;
    } 

}
