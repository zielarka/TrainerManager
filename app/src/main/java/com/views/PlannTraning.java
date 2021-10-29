package com.views;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class PlannTraning extends Activity {
	Button timeStart;
	Button timeEnd;
	static final int DIALOG_ID=0;
	int hour_x;
	int minute_x;

	int startHour=0;
	int startMinute=0;
	int endHour=0;
	int endMinute=0;

	public static Button spinnerDayWeek;
	public static TextView tv;
	public static Spinner spinnerTypeTraning;
	public static TextView LabelStartTrening;
	public static TextView LabelEndTrening;
	public static EditText NameTraning;
	public static EditText DescriptionTraining;
	boolean IsStartTime=true;
	private static Context context;

	public static ScrollView  ScrollDescriptionTraining;
	boolean cilckedittext=true;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plann_traning);

		TimeStartTraning();
		TimeEndTraning();
		LabelStartTrening = (TextView) findViewById(R.id.LabelStartTraningTextViewDynamic);
		LabelEndTrening = (TextView) findViewById(R.id.LabelEndTraningTextViewDynamic);
		NameTraning = (EditText) findViewById(R.id.NameTraning);
		ScrollDescriptionTraining=(ScrollView)findViewById(R.id.SC_ScrollDescriptionTraining);
		DescriptionTraining = (EditText) findViewById(R.id.DescriptionTraning);
		ScrollDescriptionTraining.fullScroll(View.FOCUS_BACKWARD);
		Button TargetsTraning = (Button) findViewById(R.id.TargetsTraning);
		Button ResultTraning = (Button) findViewById(R.id.ResultTraning);
		final Button SaveTraning = (Button) findViewById(R.id.SaveTraninig);
	
		
		
		context=getApplicationContext();

		//button Save New Traning
		SaveTraning.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {


				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String currentDateandTime = sdf.format(new Date());
				MyApplication.CurrentTraining.Date=currentDateandTime;
				//MyApplication.CurrentTraining.DaysOfWeek=new boolean[7];
				//MyApplication.CurrentTraining.DaysOfWeek[0]=true;
				MyApplication.CurrentTraining.DescriptionTraining=DescriptionTraining.getText().toString();
				//training.Id=1;
				MyApplication.CurrentTraining.Name= NameTraning.getText().toString();
				if(MyApplication.CurrentTraining.Name.equals("")) MyApplication.CurrentTraining.Name=MyApplication.EmptyName;
				MyApplication.CurrentTraining.TimeEndTrainingHour=endHour;
				MyApplication.CurrentTraining.TimeEndTrainingMinute=endMinute;
				MyApplication.CurrentTraining.TimeStartTrainingHour=startHour;
				MyApplication.CurrentTraining.TimeStartTrainingMinute=startMinute;
				if(MyApplication.CurrentTraining.Targets.size()==0)MyApplication.CurrentTraining.Targets.add(MyApplication.EmptyTarget);
				if(MyApplication.CurrentTraining.Results.size()==0)MyApplication.CurrentTraining.Results.add(MyApplication.EmptyUnit);
				//training.Type=1+"";
				DB.Trainings.add(DB.DeepCopy(MyApplication.CurrentTraining));
				DB.Save();
				Toast.makeText(context, "Plan Trenigowy zosta³ zapisany!",Toast.LENGTH_LONG).show();
				Intent j = new Intent(PlannTraning.this, MyTrainingPlan.class);
				startActivity (j);
				finish();
				

				
			
								
				
			}

		});
		
	
		//button Targets Traning
		TargetsTraning.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				

				Intent j = new Intent(PlannTraning.this, AddTargetsTraning.class);
				startActivity (j);
				//finish();


			}
		});

		//button Result Traning
		ResultTraning.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent j = new Intent(PlannTraning.this, AddResultsTraning.class);
				startActivity (j);
				//finish();


			}
		});


		// spinner Typ Trenigu
		spinnerTypeTraning = (Spinner)findViewById(R.id.spinnerTypeTraning);
		{
			String[] items = new String[Dictionaries.DictionaryTreninigsTypes.size()];
			for(int i=0;i<Dictionaries.DictionaryTreninigsTypes.size();i++)
			{
				items[i]=Dictionaries.DictionaryTreninigsTypes.get(i+"");
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
			spinnerTypeTraning.setAdapter(adapter);	

		}
		spinnerTypeTraning.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) 
			{
				MyApplication.CurrentTraining.Type=position+"";
				((TextView) spinnerTypeTraning.getChildAt(0)).setGravity(Gravity.CENTER);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

		});

		tv = (TextView) findViewById(R.id.tv_textViewDays);
		spinnerDayWeek = (Button)findViewById(R.id.DayWeekSpinner);
		spinnerDayWeek.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) { 

				AlertDialog.Builder builderDays = new AlertDialog.Builder(PlannTraning.this);
				String[] items = new String[Dictionaries.DictionaryDayofWeek.size()];


				for(int i=0;i<Dictionaries.DictionaryDayofWeek.size();i++)
				{
					items[i]=Dictionaries.DictionaryDayofWeek.get(i+"");
				}



				final List<String> DayList = Arrays.asList(items);

				builderDays.setMultiChoiceItems(items, MyApplication.CurrentTraining.DaysOfWeek, new DialogInterface.OnMultiChoiceClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which, boolean isChecked) 
					{
						MyApplication.CurrentTraining.DaysOfWeek[which] = isChecked;
					}
				});

				builderDays.setCancelable(false);

				builderDays.setTitle("Wybierz dni tygodnia?");

				builderDays.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String selected="";

						for (int i = 0; i<MyApplication.CurrentTraining.DaysOfWeek.length; i++)
						{
							boolean checkedDay = MyApplication.CurrentTraining.DaysOfWeek[i];
							if (checkedDay) 
							{
								selected+=DayList.get(i)+",";
							}
						}
						if(!selected.equals(""))
						{
							spinnerDayWeek.setText(selected.substring(0, selected.length()-1));
						}
					}
				});

				builderDays.setNeutralButton("Anuluj", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) 
					{
					}
				});

				AlertDialog dialog = builderDays.create();
				dialog.show();
			}
		});



	}

	public void TimeStartTraning () 
	{
		Button timeStart = (Button) findViewById(R.id.timeBtnStart); 

		timeStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				IsStartTime=true;
				showDialog(DIALOG_ID);
				


			}
		});

	}

	public void TimeEndTraning () 
	{
		Button timeEnd = (Button) findViewById(R.id.timeBtnEnd);

		timeEnd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				IsStartTime=false;	
				showDialog(DIALOG_ID);

			}
		});

	}

	@Override
	protected Dialog onCreateDialog(int id)
	{
		if(id ==DIALOG_ID)
			return new TimePickerDialog(PlannTraning.this,R.style.MyDialogTheme, kTimePickerListner, hour_x,minute_x, true);
		
		

		return null;

	}

	protected TimePickerDialog.OnTimeSetListener kTimePickerListner = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			hour_x=hourOfDay;
			minute_x=minute;

			if(IsStartTime==true)
			{
				LabelStartTrening.setText(String.format("%02d",hour_x)+":"+String.format("%02d",minute_x));
				startHour=hour_x;
				startMinute=minute_x;
			}
			else
			{
				LabelEndTrening.setText(String.format("%02d",hour_x)+":"+String.format("%02d",minute_x));
				endHour=hour_x;
				endMinute=minute_x;
			}


		}
	};





	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

	}
	
	public void onBackPressed() 
	{
		Intent j = new Intent(PlannTraning.this, MyTrainingPlan.class);
		startActivity (j);
		overridePendingTransition(R.anim.dol, R.anim.gora);
		finish();
	}

}


