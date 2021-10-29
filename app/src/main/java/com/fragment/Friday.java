package com.fragment;

import com.trainmanager.R;
import com.trainmanager.R.id;
import com.trainmanager.R.layout;
import com.trainmanager.R.menu;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class Friday extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return new LoaderView().Load(inflater, container, savedInstanceState, 4, this.getContext(), getResources());
	}
}






