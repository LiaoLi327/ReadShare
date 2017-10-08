package com.whu.readshare.ui;

import com.whu.readshare.R;
import com.whu.readshare.database.DatabaseUtil;

import android.app.Activity;
import android.app.Instrumentation;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ContentDetailsActivity extends Activity implements OnClickListener{
	
	private ImageView backBtn;
	private ImageView sendBtn;
	private TextView title;
	private TextView userName;
	private TextView content;
	private DatabaseUtil db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content_details);
		init();
	}

	private void init(){
		db = DatabaseUtil.getInstance(this);
		
		Bundle bundle = getIntent().getExtras();
		String contentID = bundle.getString("content_id"); 
		
		Cursor cursor = db.getContent(contentID);
		
		backBtn = (ImageView) findViewById(R.id.bar_back);
		backBtn.setOnClickListener(this);
		
		sendBtn = (ImageView) findViewById(R.id.content_send);
		sendBtn.setOnClickListener(this);
		
		title = (TextView) findViewById(R.id.title);
		userName = (TextView) findViewById(R.id.username);
		content = (TextView) findViewById(R.id.content);
		
		while (cursor.moveToNext()) {
			userName.setText(cursor.getString(1));
			title.setText(cursor.getString(2));
			content.setText(cursor.getString(3));
		}
	}
	
	@Override
	public void onClick(View view) {
        switch (view.getId()) {
		case R.id.bar_back:
			new Thread() { 
				public void run() { 
					try { 
						Instrumentation inst = new Instrumentation(); 
						inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK); 
						} catch (Exception e) { 
							Log.e("Exception when sendKeyDownUpSync", e.toString()); 
							} 
					} 
				}.start();
			break;
		case R.id.content_send:
			
			break;
		default:
			break;
		}		
	}

}
