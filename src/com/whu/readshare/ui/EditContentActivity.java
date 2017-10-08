package com.whu.readshare.ui;

import com.whu.readshare.R;
import com.whu.readshare.database.DatabaseUtil;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EditContentActivity extends Activity implements OnClickListener{
	
	private ImageView backBtn;
	private ImageView sendBtn;
	private EditText titleEdit;
	private EditText contentEdit;
	private DatabaseUtil db;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_content);
		initView();
	}
	
	private void initView(){
		backBtn = (ImageView) findViewById(R.id.bar_back);
		backBtn.setOnClickListener(this);
		
		sendBtn = (ImageView) findViewById(R.id.content_send);
		sendBtn.setOnClickListener(this);
		
		titleEdit = (EditText) findViewById(R.id.edit_title);
		contentEdit = (EditText) findViewById(R.id.edit_content);
		
		db = DatabaseUtil.getInstance(this);
	}

	private void sendContent(int userID,String title,String content){
		if(TextUtils.isEmpty(title) || TextUtils.isEmpty(content)){
			Toast.makeText(this, "标题和内容不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		if(db.insertContent(userID, title, content)){
			Toast.makeText(this, "文章发表成功！", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(this, "文章发表失败！", Toast.LENGTH_SHORT).show();
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
			SharedPreferences sharedPreferences = getSharedPreferences("userInfo",Activity.MODE_PRIVATE);  
			sendContent(sharedPreferences.getInt("userId", 0),titleEdit.getText().toString(),contentEdit.getText().toString());
			break;
		default:
			break;
		}
	}

}
