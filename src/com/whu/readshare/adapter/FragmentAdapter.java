package com.whu.readshare.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import java.util.List;

import com.whu.readshare.R;
import com.whu.readshare.ui.BBSFragment;
import com.whu.readshare.ui.FindFragment;
import com.whu.readshare.ui.SelfFragment;

public class FragmentAdapter extends FragmentPagerAdapter implements
		ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {

	TextView title;
	List<Fragment> fragments;
	ViewPager viewPager;
	FragmentTabHost fraTabHost;
	LayoutInflater layoutInflater;

	private Class fragmentArray[] = { BBSFragment.class, FindFragment.class,
			SelfFragment.class };
	private int imageViewArray[] = { R.drawable.select_bbs,
			R.drawable.select_find, R.drawable.select_self };
	private String textViewArray[] = { "文章", "推荐", "我的" };

	// 写构造方法，方便赋值调用
	public FragmentAdapter(FragmentManager fm, LayoutInflater layoutInflater,TextView title,
			List<Fragment> fragments, ViewPager viewPager,
			FragmentTabHost fraTabHost) {
		super(fm);
		this.layoutInflater = layoutInflater;
		this.title = title;
		this.fragments = fragments;
		this.viewPager = viewPager;
		this.fraTabHost = fraTabHost;

		/* 实现OnPageChangeListener接口,目的是监听Tab选项卡的变化，然后通知ViewPager适配器切换界面 */
		/* 简单来说,是为了让ViewPager滑动的时候能够带着底部菜单联动 */
		viewPager.setOnPageChangeListener(this);// 设置页面切换时的监听器,最新android中此api过期需要使用addOnPageChangeListener

		/* 实现setOnTabChangedListener接口,目的是为监听界面切换），然后实现TabHost里面图片文字的选中状态切换 */
		/* 简单来说,是为了当点击下面菜单时,上面的ViewPager能滑动到对应的Fragment */
		fraTabHost.setOnTabChangedListener(this);

		initTabHost();
	}

	private void initTabHost() {
		int count = textViewArray.length;

		/* 新建Tabspec选项卡并设置Tab菜单栏的内容和绑定对应的Fragment */
		for (int i = 0; i < count; i++) {
			// 给每个Tab按钮设置标签、图标和文字
			TabHost.TabSpec tabSpec = fraTabHost.newTabSpec(textViewArray[i])
					.setIndicator(getTabItemView(i));
			// 将Tab按钮添加进Tab选项卡中，并绑定Fragment
			fraTabHost.addTab(tabSpec, fragmentArray[i], null);
			fraTabHost.setTag(i);
			fraTabHost.getTabWidget().getChildAt(i)
					.setBackgroundResource(R.drawable.selector_tab_background);// 设置Tab被选中的时候颜色改变
		}
	}

	private View getTabItemView(int i) {
		// 将xml布局转换为view对象
		View view = layoutInflater.inflate(R.layout.bottom_menu_item, null);
		// 利用view对象，找到布局中的组件,并设置内容，然后返回视图
		ImageView mImageView = (ImageView) view
				.findViewById(R.id.tab_imageview);
		TextView mTextView = (TextView) view.findViewById(R.id.tab_textview);
		mImageView.setBackgroundResource(imageViewArray[i]);
		mTextView.setText(textViewArray[i]);
		return view;
	}

	//显示标题
	private void showTtile(int index){
		switch (index) {
		case 0:
			title.setText(R.string.rb_bbs);
			break;
		case 1:
			title.setText(R.string.rb_find);
			break;
		case 2:
			title.setText(R.string.rb_my);
			break;

		default:
			break;
		}
	}

	// 根据Item的位置返回对应位置的Fragment，绑定item和Fragment
	@Override
	public Fragment getItem(int arg0) {
		return fragments.get(arg0);
	}

	// 设置Item的数量
	@Override
	public int getCount() {
		return fragments.size();
	}

	// Tab改变的时候调用
	@Override
	public void onTabChanged(String arg0) {
		int position = fraTabHost.getCurrentTab();
		showTtile(position);
		viewPager.setCurrentItem(position);// 把选中的Tab的位置赋给适配器，让它控制页面切换

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	// arg0是表示你当前选中的页面位置Postion，这事件是在你页面跳转完毕的时候调用的。
	@Override
	public void onPageSelected(int arg0) {
		TabWidget widget = fraTabHost.getTabWidget();
		int oldFocusability = widget.getDescendantFocusability();
		widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);// 设置View覆盖子类控件而直接获得焦点
		fraTabHost.setCurrentTab(arg0);// 根据位置Postion设置当前的Tab
		widget.setDescendantFocusability(oldFocusability);// 设置取消分割线

	}

}
