package com.whu.readshare.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whu.readshare.R;


public class FindFragment extends Fragment {
	private View view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_find, container, false);
		InitView();
		return view;
	}
	
	private void InitView(){
	}
	
}