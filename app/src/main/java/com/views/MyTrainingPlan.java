package com.views;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.common.Dictionaries;
import com.common.WebService;
import com.fragment.AdapterWeek;
import com.fragment.Friday;
import com.fragment.Monday;
import com.fragment.Saturday;
import com.fragment.Sunday;
import com.fragment.Thursday;
import com.fragment.Tuesday;
import com.fragment.Wednesday;
import com.model.Training;
import com.trainmanager.MainActivity;
import com.trainmanager.MyApplication;
import com.trainmanager.R;
import com.trainmanager.R.id;
import com.trainmanager.R.layout;
import com.trainmanager.R.menu;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MyTrainingPlan extends ActionBarActivity implements OnPageChangeListener,OnTabChangeListener {

	ViewPager adapterWeek;
	TabHost tabHost;
	Bundle bundle;
	public int tabWidth;
	public int position;
	String file="";
	public static LinearLayout  LN_BTN_addsharedown;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		bundle=savedInstanceState;
		setContentView(R.layout.activity_my_training_plan);

		intiViewPager();

		intiViewHost();

		

		Button PlanTraning = (Button) findViewById(R.id.Plann_Traning);
		Button SharePlan = (Button) findViewById(R.id.Share_Plan);
		Button DownloadPlan = (Button) findViewById(R.id.Download_Plan);
		LN_BTN_addsharedown= (LinearLayout)findViewById(R.id.LN_BTN_PlanTranig);

		
		//final Typeface TypeFaceLapTabHost = Typeface.createFromAsset(getAssets(), "tertrexbol.otf");
		  for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
	            TextView tvTabHost = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
	            tvTabHost.setTypeface(null, Typeface.BOLD_ITALIC);
				}
		                                        
		//button ZAPLANUJ TRENIG

		
		PlanTraning.setOnTouchListener(new OnTouchListener() {

		    @Override
		    public boolean onTouch(View v, MotionEvent event) {

		        switch(event.getAction()) {

		        case MotionEvent.ACTION_DOWN:
		        	
		        	LN_BTN_addsharedown.setBackground(getResources().getDrawable(R.drawable.iconefocusadd));
		        	
		        
		        
		            break;
		        case MotionEvent.ACTION_UP:
		        	LN_BTN_addsharedown.setBackground(getResources().getDrawable(R.drawable.ikonaddsharedownload));
		        	Intent j = new Intent(MyTrainingPlan.this, PlannTraning.class);
					startActivity (j);
					overridePendingTransition(R.anim.animtransation, R.anim.animtransationend);
		         
		            break;
		        }

		        return false;
		    }

		});



		//button Udostepnij Plan Trenigowy
		SharePlan.setOnTouchListener(new OnTouchListener() {

			
			 @Override
			    public boolean onTouch(View v, MotionEvent event) {

			        switch(event.getAction()) {

			        case MotionEvent.ACTION_DOWN:
			        	
			        	LN_BTN_addsharedown.setBackground(getResources().getDrawable(R.drawable.iconefocusshare));
			        	
			        
			        
			            break;
			        case MotionEvent.ACTION_UP:
			        	LN_BTN_addsharedown.setBackground(getResources().getDrawable(R.drawable.ikonaddsharedownload));
			        	Intent j = new Intent(MyTrainingPlan.this, SharePlan.class);
						startActivity (j);
						overridePendingTransition(R.anim.animtransation, R.anim.animtransationend);
			         
			            break;
			        }

			        return false;
			    }

			});
		

		//button Pobierz Plan Trenigowy
		DownloadPlan.setOnTouchListener(new OnTouchListener() {

			
			 @Override
			    public boolean onTouch(View v, MotionEvent event) {

			        switch(event.getAction()) {

			        case MotionEvent.ACTION_DOWN:
			        	
			        	LN_BTN_addsharedown.setBackground(getResources().getDrawable(R.drawable.iconefocusdownload));
			        	
			        
			        
			            break;
			        case MotionEvent.ACTION_UP:
			        	LN_BTN_addsharedown.setBackground(getResources().getDrawable(R.drawable.ikonaddsharedownload));
			        	Intent j = new Intent(MyTrainingPlan.this, DownloadPlan.class);
						startActivity (j);
						overridePendingTransition(R.anim.animtransation, R.anim.animtransationend);
			         
			            break;
			        }

			        return false;
			    }

			});

	

	}	

	public void intiViewHost() {

		tabHost =(TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);

		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		//cal.add(Calendar.DAY_OF_WEEK, -7); 
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		String monday = format1.format(cal.getTime());
		cal.add(Calendar.DAY_OF_WEEK, 1); 
		String tuesday = format1.format(cal.getTime());
		cal.add(Calendar.DAY_OF_WEEK, 1); 
		String wednesday = format1.format(cal.getTime());
		cal.add(Calendar.DAY_OF_WEEK, 1); 
		String thursday = format1.format(cal.getTime());
		cal.add(Calendar.DAY_OF_WEEK, 1); 
		String friday = format1.format(cal.getTime());
		cal.add(Calendar.DAY_OF_WEEK, 1); 
		String saturday = format1.format(cal.getTime());
		cal.add(Calendar.DAY_OF_WEEK, 1); 
		String sunday = format1.format(cal.getTime());


		final String[] tabNames = {Dictionaries.DictionaryDayofWeek.get(0+"")+ String.format("%n")+monday,Dictionaries.DictionaryDayofWeek.get(1+"")+ String.format("%n")+tuesday,Dictionaries.DictionaryDayofWeek.get(2+"")+String.format("%n")+wednesday,Dictionaries.DictionaryDayofWeek.get(3+"")+String.format("%n")+thursday,Dictionaries.DictionaryDayofWeek.get(4+"")+String.format("%n")+friday,Dictionaries.DictionaryDayofWeek.get(5+"")+String.format("%n")+saturday,Dictionaries.DictionaryDayofWeek.get(6+"")+String.format("%n")+sunday};

		for(int i=0;i<tabNames.length;i++)
		{
			TabHost.TabSpec tabSpec;
			tabSpec = tabHost.newTabSpec(tabNames[i]);
			tabSpec.setIndicator(tabNames[i]);
			tabSpec.setContent(new FakeContent(getApplicationContext()));
			tabHost.addTab(tabSpec);
		}
		tabHost.setOnTabChangedListener(this);

		if(monday.equals(date))
		{
			adapterWeek.setCurrentItem(0);
			position=0;
		}
		if(tuesday.equals(date)) 
		{
			adapterWeek.setCurrentItem(1);
			position=1;
		}
		if(wednesday.equals(date))
		{
			adapterWeek.setCurrentItem(2);
			position=2;
		}
		if(thursday.equals(date)) 
		{
			adapterWeek.setCurrentItem(3);
			position=3;
		}
		if(friday.equals(date))
		{
			adapterWeek.setCurrentItem(4);
			position=4;
		}
		if(saturday.equals(date))
		{
			adapterWeek.setCurrentItem(5);
			position=5;
		}
		if(sunday.equals(date))
		{
			adapterWeek.setCurrentItem(6);
			position=6;
		}

		final HorizontalScrollView hScrollView = (HorizontalScrollView) findViewById(R.id.h_scroll_view);
		hScrollView.post(new Runnable() { 
			public void run() { 
				hScrollView.smoothScrollTo((MyApplication.Width/3+20)*position, 0);
			} 
		});


	}

	public class FakeContent implements TabContentFactory
	{
		Context context;
		public FakeContent (Context mcontext)
		{
			context= mcontext;
		}

		@Override
		public View createTabContent(String tag) {


			View fakeView =new View(context);
			fakeView.setMinimumHeight(0);
			fakeView.setMinimumWidth(0);
			return fakeView;
		}


	}

	private void intiViewPager() {

		adapterWeek=(ViewPager) findViewById(R.id.View_pager_PON_ND);


		List<Fragment> listFragments = new ArrayList<Fragment>();
		listFragments.add(new Monday());

		listFragments.add(new Tuesday());
		listFragments.add(new Wednesday());
		listFragments.add(new Thursday());
		listFragments.add(new Friday());
		listFragments.add(new Saturday());
		listFragments.add(new Sunday());

		AdapterWeek fragmentAdapterPON_ND = new AdapterWeek(getSupportFragmentManager(), listFragments);
		adapterWeek.setAdapter(fragmentAdapterPON_ND);
		adapterWeek.setOnPageChangeListener(this);
	}





	//TabHostListner
	@Override
	public void onTabChanged(String tabId) {
		
		int selectedItem = tabHost.getCurrentTab();
		adapterWeek.setCurrentItem(selectedItem);

		HorizontalScrollView hScrollView = (HorizontalScrollView) findViewById(R.id.h_scroll_view);
		View tabView = tabHost.getCurrentTabView();
		int scrollViewPosition = tabView.getLeft()-(hScrollView.getWidth()-tabView.getWidth())/2;
		hScrollView.smoothScrollTo(scrollViewPosition, 0);
		tabWidth=tabView.getLeft();


    
}
	//ViwPage Listner
	@Override
	public void onPageScrollStateChanged(int arg0) {


	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int selectedItem) {
		tabHost.setCurrentTab(selectedItem);

	}


	public void onBackPressed() 
	{
		Intent j = new Intent(MyTrainingPlan.this, MainActivity.class);
		startActivity (j);
		overridePendingTransition(R.anim.dol, R.anim.gora);
		finish();
	}

}

