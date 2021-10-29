package com.trainmanager;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.views.MyTrainingPlan;
import com.views.Stopwatch;
import com.views.Tempometr;

@SuppressLint("InlinedApi")
public class MainActivity extends Activity {


	private static Context context;

	public static LinearLayout  LN_FullMenu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);


		
		
		context = getApplicationContext();
		
		/*
		/////////////////////////////////// Permissions START! //////////////////////////////////////////////////////////

		requestPermission();
		///  granting permission  ////
		if(!checkPermission())
		{
			requestPermission();
		}

		//Intent intent = new Intent(getApplicationContext(), MainMenu.class);
		//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		//startActivity(intent);
		//this.finish();

	}

	///////////////////////   permission for marshmallow  ///////////////////

	@SuppressLint("InlinedApi")
	private boolean checkPermission()
	{
		int result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
		int result2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
		int result3 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
		int result4 = ContextCompat.checkSelfPermission(this, Manifest.permission.SYSTEM_ALERT_WINDOW);
		int result6   = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_BOOT_COMPLETED);
		int result7   = ContextCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK);
		int result8   = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE);
		int result9   = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
		int result10   = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
		int result11   = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

		if (result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED &&
				result3 == PackageManager.PERMISSION_GRANTED && result4 == PackageManager.PERMISSION_GRANTED &&       
				result4 == PackageManager.PERMISSION_GRANTED &&       
				result6 == PackageManager.PERMISSION_GRANTED && result7 == PackageManager.PERMISSION_GRANTED &&
				result8 == PackageManager.PERMISSION_GRANTED && result9 == PackageManager.PERMISSION_GRANTED &&
				result10 == PackageManager.PERMISSION_GRANTED && result11 == PackageManager.PERMISSION_GRANTED
				&& result11 == PackageManager.PERMISSION_GRANTED){

			return true;

		} else {

			//Toast.makeText(this,"You don't have permission to use further features",Toast.LENGTH_LONG).show();
			return false;

		}
	}

	@SuppressWarnings("deprecation")
	private void requestPermission(){

		if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET) &&
				ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) &&
				ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)  &&
				ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SYSTEM_ALERT_WINDOW) &&
				ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_BOOT_COMPLETED) &&
				ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WAKE_LOCK) &&
				ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_NETWORK_STATE) &&
				ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) &&
				ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) &&
				ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE) 
				){

			Toast.makeText(this,"Application needs permission to use your camera, calls, storage and location.",Toast.LENGTH_LONG).show();

		} else {

			ActivityCompat.requestPermissions(this,new String[]{
					Manifest.permission.INTERNET,
					Manifest.permission.WRITE_EXTERNAL_STORAGE,
					Manifest.permission.READ_EXTERNAL_STORAGE,
					Manifest.permission.SYSTEM_ALERT_WINDOW,
					Manifest.permission.RECEIVE_BOOT_COMPLETED,
					Manifest.permission.WAKE_LOCK,
					Manifest.permission.ACCESS_NETWORK_STATE,
					Manifest.permission.CAMERA,
					Manifest.permission.WRITE_EXTERNAL_STORAGE,
					Manifest.permission.READ_PHONE_STATE,
			},1);
		}
*/
		/////////////////////////////////////////////// Permissions END ! ////////////////////////////////////////////////








		Display display = getWindowManager().getDefaultDisplay(); 
		MyApplication.Width = display.getWidth();

		MyApplication.Height = display.getHeight();

		Button MyTrainingPlan = (Button) findViewById(R.id.MyTrainingPlan);
		Button stoper = (Button) findViewById(R.id.Stoper);
		//Button MyRecords = (Button) findViewById(R.id.MyRecords);
		Button Tempometr = (Button) findViewById(R.id.Tempometr);
		Button exit = (Button) findViewById(R.id.exit);
		LN_FullMenu= (LinearLayout)findViewById(R.id.Ln_BTN_full);

		//button MOJ PLAN TRENIGOWY

		MyTrainingPlan.setOnTouchListener(new OnTouchListener() {

		    @Override
		    public boolean onTouch(View v, MotionEvent event) {

		        switch(event.getAction()) {

		        case MotionEvent.ACTION_DOWN:
		        	
		        	LN_FullMenu.setBackground(getResources().getDrawable(R.drawable.menutraningonclick));
		        	
		        
		        
		            break;
		        case MotionEvent.ACTION_UP:
		        	LN_FullMenu.setBackground(getResources().getDrawable(R.drawable.menu));
		        	Intent j = new Intent(MainActivity.this, MyTrainingPlan.class);
					startActivity (j);
					overridePendingTransition(R.anim.animtransation, R.anim.animtransationend);
		         
		            break;
		        }

		        return false;
		    }

		});



		//button STOPER
	
		stoper.setOnTouchListener(new OnTouchListener() {

		    @Override
		    public boolean onTouch(View v, MotionEvent event) {

		        switch(event.getAction()) {

		        case MotionEvent.ACTION_DOWN:
		        	
		        	LN_FullMenu.setBackground(getResources().getDrawable(R.drawable.menustoperonclick));
		        	
		        
		        
		            break;
		        case MotionEvent.ACTION_UP:
		        	LN_FullMenu.setBackground(getResources().getDrawable(R.drawable.menu));
		    		Intent j = new Intent(MainActivity.this, Stopwatch.class);
					startActivity (j);
					overridePendingTransition(R.anim.animtransation, R.anim.animtransationend);
		         
		            break;
		        }

		        return false;
		    }

		});

		//button TEMPOMETR

		Tempometr.setOnTouchListener(new OnTouchListener() {

		    @Override
		    public boolean onTouch(View v, MotionEvent event) {

		        switch(event.getAction()) {

		        case MotionEvent.ACTION_DOWN:
		        	
		        	LN_FullMenu.setBackground(getResources().getDrawable(R.drawable.menutempoonclick));
		        	
		        
		        
		            break;
		        case MotionEvent.ACTION_UP:
		        	LN_FullMenu.setBackground(getResources().getDrawable(R.drawable.menu));
		    		Intent j = new Intent(MainActivity.this, Tempometr.class);
					startActivity (j);
					overridePendingTransition(R.anim.animtransation, R.anim.animtransationend);
		         
		            break;
		        }

		        return false;
		    }

		});

		//button Exit

		exit.setOnTouchListener(new OnTouchListener() {

		    @Override
		    public boolean onTouch(View v, MotionEvent event) {

		        switch(event.getAction()) {

		        case MotionEvent.ACTION_DOWN:
		        	
		        	LN_FullMenu.setBackground(getResources().getDrawable(R.drawable.menufinishonclick));
		        	
		        
		        
		            break;
		        case MotionEvent.ACTION_UP:
		        	LN_FullMenu.setBackground(getResources().getDrawable(R.drawable.menu));
		        	finish();
					overridePendingTransition(R.anim.animtransation, R.anim.animtransationend);
		         
		            break;
		        }

		        return false;
		    }
		    
			private void finish() {
				//Intent j = new Intent(MainActivity.this, MainActivity.class);
				//startActivity (j);
				moveTaskToBack(true);
			}

		});
		//button Moje Rekordy
/*
		MyRecords.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent j = new Intent(MainActivity.this, MyRecordMenu.class);
				startActivity (j);
				overridePendingTransition(R.anim.animtransation, R.anim.animtransationend);

			}
		});
*/
	}
	public void onBackPressed() 
	{
	
		finish();
		///Intent j = new Intent(MainActivity.this, MainActivity.class);
		//startActivity (j);
		moveTaskToBack(true);
	}




}