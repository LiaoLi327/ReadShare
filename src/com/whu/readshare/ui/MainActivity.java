package com.whu.readshare.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.whu.readshare.R;
import com.whu.readshare.adapter.FragmentAdapter;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.TextView;


public class MainActivity extends FragmentActivity {

	private TextView title;
	private FragmentTabHost mTabHost;
	private LayoutInflater layoutInflater;
	private ViewPager vp;
	private List<Fragment> fragments = new ArrayList<Fragment>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initPage();
	}

	// 控件初始化控件
	private void initView() {
		title = (TextView) findViewById(R.id.title);
		
		vp = (ViewPager) findViewById(R.id.content_pager);
		layoutInflater = LayoutInflater.from(this);// 加载布局管理器

		/* 实例化FragmentTabHost对象并进行绑定 */
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);// 绑定tahost
		mTabHost.setup(this, getSupportFragmentManager(), R.id.content_pager);// 绑定viewpager
	}

	/* 初始化Fragment */
	private void initPage() {
		BBSFragment BBSFragment = new BBSFragment();
		FindFragment findFragment = new FindFragment();
		SelfFragment selfFragment = new SelfFragment();

		fragments.add(BBSFragment);
		fragments.add(findFragment);
		fragments.add(selfFragment);

		// 绑定Fragment适配器
		vp.setAdapter(new FragmentAdapter(getSupportFragmentManager(), layoutInflater,title,fragments,vp,mTabHost));
		mTabHost.getTabWidget().setDividerDrawable(null);
	}
}
