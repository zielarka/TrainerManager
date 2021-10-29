package com.views;

import com.common.DB;
import com.common.FileLoadSave;
import com.common.WebService;
import com.trainmanager.MainActivity;
import com.trainmanager.MyApplication;
import com.trainmanager.R;
import com.trainmanager.R.layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class DownloadPlan extends Activity {

	public static String Pin="1111";
	public static String file="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download_plan);

		Button ButtonOk = (Button) findViewById(R.id.BtnOK);
		final EditText EditTextPin = (EditText) findViewById(R.id.EditText_pin);
	

		//button Zatwierdz TRENIG
		ButtonOk.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				try 
				{	Pin=EditTextPin.getText().toString();
				Thread thread = new Thread()
				{
					@Override
					public void run()
					{
						file=WebService.DownloadPlanPIN(Pin);

					};
				};
				thread.start();
				thread.join();

				FileLoadSave.SaveFromString(file,MyApplication.Context, MyApplication.Context.getString(R.string.fileNameTrainings), false);
				DB.Reolad();
				Intent j = new Intent(DownloadPlan.this, MyTrainingPlan.class);
				startActivity (j);
				finish();
				} 
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		});

	}

	public void onBackPressed() 
	{
		Intent j = new Intent(DownloadPlan.this, MyTrainingPlan.class);
		startActivity (j);
		overridePendingTransition(R.anim.dol, R.anim.gora);
		finish();
	}
}
