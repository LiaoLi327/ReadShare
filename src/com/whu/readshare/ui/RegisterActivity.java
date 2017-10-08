package com.whu.readshare.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.whu.readshare.R;
import com.whu.readshare.database.DatabaseUtil;

public class RegisterActivity extends Activity implements OnClickListener{
	/* 定义控件 */
	private Button btn_register;// 注册按钮
	private EditText input_username;// 用户名
	private EditText input_password;// 密码
	private EditText reinput_password;// 密码
	
	private DatabaseUtil db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initView();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		db.closeDB();
		
	}
	
	private void initView(){
		input_username = (EditText) findViewById(R.id.input_username);
		input_password = (EditText) findViewById(R.id.input_password);
		reinput_password = (EditText) findViewById(R.id.reinput_password);
		btn_register = (Button) findViewById(R.id.btn_register);
		btn_register.setOnClickListener(this);
		db = DatabaseUtil.getInstance(this);
	}

	private void verifyRegister(){
		String strUsername = input_username.getText().toString();
		String strPassword = input_password.getText().toString();
		String strRePassword  = reinput_password.getText().toString();
		
		if(TextUtils.isEmpty(strUsername) && TextUtils.isEmpty(strPassword)){
			Toast.makeText(RegisterActivity.this, "请输入用户名和密码！",
					Toast.LENGTH_SHORT).show();
			return;
		}
		
		if (TextUtils.isEmpty(strUsername)) {
			Toast.makeText(RegisterActivity.this, "用户名不能为空！",
					Toast.LENGTH_SHORT).show();
			return;
		}

		if (TextUtils.isEmpty(strPassword)) {
			Toast.makeText(RegisterActivity.this, "密码不能为空！",
					Toast.LENGTH_SHORT).show();
			return;
		}
		
		if (TextUtils.isEmpty(strRePassword)) {
			Toast.makeText(RegisterActivity.this, "请重新输入密码！",
					Toast.LENGTH_SHORT).show();
			return;
		}
		
		if (!strPassword.equals(strRePassword)) {
			Toast.makeText(RegisterActivity.this, "两次输入密码不一致",
					Toast.LENGTH_SHORT).show();
			return;
		}
		
		if(db.registerQueryUser(strUsername)){
			Toast.makeText(RegisterActivity.this, "用户名已存在，请重新命名！",
					Toast.LENGTH_SHORT).show();
			return;
		}
		
		if(db.insertUser(strUsername, strPassword)){
			Toast.makeText(RegisterActivity.this, "用户注册成功",
					Toast.LENGTH_SHORT).show();
		}else {
			Toast.makeText(RegisterActivity.this, "用户注册失败",
					Toast.LENGTH_SHORT).show();
		}
		
	}
	
	@Override
	public void onClick(View view) {
		verifyRegister();
	}

}
