package com.fragment;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class AdapterWeek  extends FragmentPagerAdapter{

	List<Fragment> listFragments;
	
	public AdapterWeek(FragmentManager fm, List<Fragment> listFragments) {
		super(fm);
		this.listFragments =listFragments;
		
		
	}

	@Override
	public Fragment getItem(int position) {
		
		return listFragments.get(position);
		
	}

	@Override
	public int getCount() {
		
		return listFragments.size();
	}

}
