package com.whu.readshare.adapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.whu.readshare.R;

public class BBSListAdapter extends BaseAdapter {
	private Context context;
	private List<HashMap<String, Object>> data = new LinkedList<HashMap<String, Object>>();
	private int listItem = 0;

	public BBSListAdapter() {

	}

	public BBSListAdapter(Context context, List<HashMap<String, Object>> data, int listItem) {
		this.context = context;
		this.data = data;
		this.listItem = listItem;
	}

	public Context getContext() {
		return this.context;
	}

	@Override
	public int getCount() {
		int count = 0;
		if (data != null) {
			count = data.size();
		}
		return count;
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(getContext()).inflate(listItem,
					null);
			holder.title = (TextView) convertView
					.findViewById(R.id.title);
			holder.content = (TextView) convertView
					.findViewById(R.id.content);
			holder.username = (TextView) convertView
					.findViewById(R.id.username);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.title.setText(data.get(position).get("title").toString());
		holder.content.setText(data.get(position).get("content").toString());
		holder.username.setText(data.get(position).get("username").toString());

		return convertView;
	}

	private class ViewHolder {
		TextView title;
		TextView content;
		TextView username;

	}
}
