package com.whu.readshare.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.whu.readshare.R;
import com.whu.readshare.database.DatabaseUtil;

public class LoginActivity extends Activity implements OnClickListener{
	
	private String TAG = "LoginActivity";// log标志

	/* 定义控件 */
	private Button btn_login;// 登录按钮
	private EditText username;// 用户名
	private EditText password;// 密码
	private TextView txt_register;//用戶注冊

	private DatabaseUtil db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		isHyalineStatusBar();
		setContentView(R.layout.activity_login);
		initView();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		db.closeDB();
	}
	
	// 让状态栏和导航栏变成透明
	private void isHyalineStatusBar() {
		
		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			// 透明状态栏
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 透明导航栏
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
	}
	
	//初始化控件
	private void initView(){
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		btn_login = (Button) findViewById(R.id.btn_login);
		txt_register = (TextView) findViewById(R.id.txt_register);
		//綁定點擊事件
		btn_login.setOnClickListener(this);
		txt_register.setOnClickListener(this);
		
		db = DatabaseUtil.getInstance(this);
	}

	private void verifyLogin(){
		String strUsertname = username.getText().toString();
		String strPassword = password.getText().toString();
		
		if(TextUtils.isEmpty(strUsertname) && TextUtils.isEmpty(strPassword)){
			Toast.makeText(LoginActivity.this, "请输入用户名和密码！",
					Toast.LENGTH_SHORT).show();
			return;
		}
		
		if (TextUtils.isEmpty(strUsertname)) {
			Toast.makeText(LoginActivity.this, "用户名不能为空！",
					Toast.LENGTH_SHORT).show();
			return;
		}

		if (TextUtils.isEmpty(strPassword)) {
			Toast.makeText(LoginActivity.this, "密码不能为空！",
					Toast.LENGTH_SHORT).show();
			return;
		}
		
		if(db.loginQueryUser(strUsertname, strPassword)){
			Toast.makeText(LoginActivity.this, "登录成功",
					Toast.LENGTH_SHORT).show();
			saveUser(strUsertname, strPassword);
			Intent intent = new Intent(LoginActivity.this,MainActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(LoginActivity.this, "用户名或密码错误",
					Toast.LENGTH_SHORT).show();
		}
		
	}
	
	private void saveUser(String userName,String password){
		//实例化SharedPreferences对象（第一步）   
		SharedPreferences mySharedPreferences= getSharedPreferences("userInfo",   
		Activity.MODE_PRIVATE);   
		//实例化SharedPreferences.Editor对象（第二步）   
		SharedPreferences.Editor editor = mySharedPreferences.edit();   
	
		Cursor cursor = db.queryUser(userName, password);
		while (cursor.moveToNext()) {
			int userID = cursor.getColumnIndex("user_id");
			
			//用putString的方法保存数据   
			editor.putInt("userId", cursor.getInt(userID));
			editor.putString("userName", userName);   
			editor.putString("password", password);   
			//提交当前数据   
			editor.commit();   
		}
	}
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_login:
			verifyLogin();
			break;
		case R.id.txt_register :
			Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
			startActivity(intent);
		default:
			break;
		}
	}
	
	
}
  