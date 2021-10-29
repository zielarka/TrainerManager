package com.views;


import java.util.ArrayList;
import java.util.List;

import com.common.Dictionaries;
import com.model.Training;
import com.model.Unit;
import com.trainmanager.MyApplication;
import com.trainmanager.R;
import com.trainmanager.R.id;
import com.trainmanager.R.layout;
import com.trainmanager.R.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class AddResultsTraning extends Activity {

	private TextView Ed_ResultsName;
	private TextView Ed_Unit;
	private static Context context;
	private static Activity activity;
	private static LinearLayout LinearLayout;
	private static int  gap=0;
	public static Spinner spinnerUnit;
	public static String ValueSpinnerUnit;

	public static List<Unit> Results=new ArrayList<Unit>();
	public static ScrollView  ScrollDescriptionResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_results_traning);
		LinearLayout= (LinearLayout) findViewById(R.id.LinearLayoutResults);
		Button ResultsTranig = (Button) findViewById(R.id.AddResultsTraningBTN);
		Ed_ResultsName=(TextView) findViewById(R.id.NameTraningEditText);
		Ed_Unit=(TextView) findViewById(R.id.AmountEditText);
		spinnerUnit = (Spinner)findViewById(R.id.spinnerTypeOfUnit);
		context=getApplicationContext();

		ScrollDescriptionResult=(ScrollView)findViewById(R.id.ScrollView_result);
		
		
		Button SaveResultsTraning = (Button) findViewById(R.id.SaveTraninigResults);

		//button SaveResultsTraning
		SaveResultsTraning.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				MyApplication.CurrentTraining.Results=new ArrayList<Unit>();
				for (int i=0; i<Results.size();i++) 
				{
					MyApplication.CurrentTraining.Results.add(Results.get(i));
				}	
				Toast.makeText(context, "Rezultaty trenigowe zosta³y zapisane!",Toast.LENGTH_LONG).show();
				//Intent j = new Intent(AddResultsTraning.this, PlannTraning.class);
				//startActivity (j);
				finish();
				
			}

		});








		//spinner typ jednostki
		Results=new ArrayList<Unit>();
		for (int i=0; i<MyApplication.CurrentTraining.Results.size();i++) 
		{
			addButton("-  "+MyApplication.CurrentTraining.Results.get(i).Name+" "+MyApplication.CurrentTraining.Results.get(i).Amount+ "\n");
			Results.add(MyApplication.CurrentTraining.Results.get(i));
		}
		String[] items = new String[Dictionaries.DictionaryUnit.size()];
		for(int i=0;i<Dictionaries.DictionaryUnit.size();i++)
		{
			items[i]=Dictionaries.DictionaryUnit.get(i+"");
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, items);
		spinnerUnit.setAdapter(adapter);

		spinnerUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) 
			{
				String value;
				value=spinnerUnit.getSelectedItem().toString();
				ValueSpinnerUnit=value;

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

		});


		ResultsTranig.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					addButton("");
					final ScrollView scrollview = (ScrollView) findViewById(R.id.ScrollView_result);
					scrollview.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
					    @Override
					    public void onGlobalLayout() {
					        scrollview.post(new Runnable() {
					            public void run() {
					                scrollview.fullScroll(View.FOCUS_DOWN);
					            }
					        });
					    }
					});
					        
					    
				
				}
				catch (Exception e)
				{
					int i=0;
				}
			}
		});



		activity=this;
	}
	public void addButton(String text) {


		Button btnTag = new Button(context);
		btnTag.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		try {




			if((text==""))
			{
				Unit unit= new Unit();
				unit.Name=Ed_ResultsName.getText()+" ";
				unit.Amount=String.valueOf(Ed_Unit.getText())+" "+String.valueOf(ValueSpinnerUnit);
				Results.add(unit);
				btnTag.setText("-  "+String.valueOf(Ed_ResultsName.getText())+" "+  String.valueOf(Ed_Unit.getText())+" "+String.valueOf(ValueSpinnerUnit)+"										X"+ "\n");
				ScrollDescriptionResult.fullScroll(View.FOCUS_DOWN);
			}
			else {
				btnTag.setText(text);
			}
			//btnTag.setBackgroundColor(Color.rgb(25,165, 111));
			btnTag.getBackground().setAlpha(1);
			btnTag.setTypeface(null, Typeface.BOLD);
			btnTag.setTextSize(18);

		} 

		catch (Exception e) {

		}

		btnTag.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v) 
			{
				try
				{
					int index = LinearLayout.indexOfChild(v);
					LinearLayout.removeViewAt(index);
				
					Results.remove(index);
				
				}
				catch (Exception e)
				{
					//Et_TargetsRead.append("-  "+String.valueOf(Ed_TargetsWrite.getText())+ "\n");
				}
			}
		});
		LinearLayout.addView(btnTag);
		/*Button btnTag1 = new Button(context);
		btnTag1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,170));
		btnTag1.getBackground().setAlpha(1);
		btnTag1.setId(999);
		if(gap!=0)
		{
			int index = LinearLayout.indexOfChild((Button) findViewById(999));
			if(index>0)
			{
				LinearLayout.removeViewAt(index);
			}
		}
		
		LinearLayout.addView(btnTag1);
		gap=999;
		*/

	}
	public void onBackPressed() 
	{
		//Intent j = new Intent(AddResultsTraning.this, PlannTraning.class);
		//startActivity (j);
		finish();
	}
}