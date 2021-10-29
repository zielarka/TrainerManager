package com.fragment;

import java.util.ArrayList;
import java.util.List;

import com.common.DB;
import com.common.Dictionaries;
import com.model.Training;
import com.model.Unit;
import com.trainmanager.MyApplication;
import com.trainmanager.R;
import com.trainmanager.R.id;
import com.trainmanager.R.layout;
import com.trainmanager.R.menu;





import com.trainmanager.R.string;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class Monday extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return new LoaderView().Load(inflater, container, savedInstanceState, 0, this.getContext(), getResources());
	}
}

