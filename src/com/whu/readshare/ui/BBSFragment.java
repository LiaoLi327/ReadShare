package com.whu.readshare.ui;

import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.whu.readshare.R;
import com.whu.readshare.adapter.BBSListAdapter;
import com.whu.readshare.database.DatabaseUtil;

public class BBSFragment extends Fragment implements OnItemClickListener{
	private View view;
	private List<HashMap<String, Object>> bbsBeanList;
	private BBSListAdapter bbsListAdapter;
	private DatabaseUtil db;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_bbs, container, false);
		initData();
		initView();
		return view;
	}
	
	private void initView(){
		ListView bbsListView = (ListView) view.findViewById(R.id.bbs_listview);
		bbsListAdapter = new BBSListAdapter(view.getContext(),bbsBeanList,R.layout.bbs_listview_item);
		bbsListView.setAdapter(bbsListAdapter);
		bbsListView.setOnItemClickListener(this);
	}
	
	private void initData(){
		db = DatabaseUtil.getInstance(getActivity());
		bbsBeanList = db.getAllContent();
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Bundle bundle = new Bundle();  
        bundle.putString("content_id", bbsBeanList.get(position).get("content_id").toString());  
        Intent intent = new Intent();  
        intent.putExtras(bundle);  
        intent.setClass(getActivity(), ContentDetailsActivity.class);  
        startActivity(intent);  
	}
}
