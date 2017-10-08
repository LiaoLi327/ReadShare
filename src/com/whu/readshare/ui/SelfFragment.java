package com.whu.readshare.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whu.readshare.R;

public class SelfFragment extends Fragment implements OnClickListener{
	private View view;
	private LinearLayout edit;
	private TextView username;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_slef, container, false);
		initView();
		return view;
	}
	
	private void initView(){
		edit = (LinearLayout) view.findViewById(R.id.edit_content);
		edit.setOnClickListener(this);
		username = (TextView) view.findViewById(R.id.username);
		showUserName();
	}
	
	private void showUserName(){
		//同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象   
		SharedPreferences sharedPreferences= getActivity().getSharedPreferences("userInfo",getActivity().MODE_PRIVATE);   
		// 使用getString方法获得value，注意第2个参数是value的默认值   
		username.setText(sharedPreferences.getString("userName", "用户名"));   

	}

	@Override
	public void onClick(View view) {
		Intent intent = new Intent(getActivity(),EditContentActivity.class);
		startActivity(intent);
	}
}
