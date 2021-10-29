package com.views;

import com.common.DB;
import com.common.FileLoadSave;
import com.common.WebService;
import com.trainmanager.MyApplication;
import com.trainmanager.R;
import com.trainmanager.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SharePlan extends Activity {

	public static String pin="1111";
	public TextView TextViewPin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share_plan);
		TextViewPin = (TextView) findViewById(R.id.textviewPin);
		DB.Save();
		try 
		{		
			Thread thread = new Thread()
			{
				@Override
				public void run()
				{
					pin=WebService.SharePlan(DB.IdPhone, FileLoadSave.ReadAsString(MyApplication.Context, MyApplication.Context.getString(R.string.fileNameTrainings)));
					TextViewPin.setTextColor(Color.rgb(243,176,75));
					
				};
			};
			thread.start();
			thread.join();
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		TextViewPin.setText(pin);
		
		if (pin.equals("")) 
		{
			TextViewPin.setText("Brak po³¹cznia z internetem");
			TextViewPin.setTextSize(MyApplication.SizeTextOfflineNet);
			TextViewPin.setTextColor(Color.rgb(220,56,75));
			TextViewPin.setPaintFlags(TextViewPin.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
		}
	}
	
	public void onBackPressed() 
	{
		Intent j = new Intent(SharePlan.this, MyTrainingPlan.class);
		startActivity (j);
		overridePendingTransition(R.anim.dol, R.anim.gora);
		finish();
	}

}
