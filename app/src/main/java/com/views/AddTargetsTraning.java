package com.views;

import java.util.ArrayList;
import java.util.List;

import com.model.Unit;
import com.trainmanager.MainActivity;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class AddTargetsTraning extends Activity {
	private TextView Ed_TargetsWrite;
	private static Context context;
	private static Activity activity;
	private static LinearLayout LinearLayout;
	private static int  gap=0;
	public static ScrollView  ScrollDescriptionTargets;

	public static ArrayList<String> Targets=new ArrayList <String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_targets_traning);
		LinearLayout= (LinearLayout) findViewById(R.id.LinearLayoutTargets);
		Button TargetsTranig = (Button) findViewById(R.id.AddTargetsTraning);
		Ed_TargetsWrite=(TextView) findViewById(R.id.AddTargetsTraningEditext);
		context=getApplicationContext();
		Button SaveResultsTraning = (Button) findViewById(R.id.SaveTraninigTargets);
		Targets=new ArrayList<String>();
		ScrollDescriptionTargets=(ScrollView)findViewById(R.id.ScrollView_Targets);

		//button SaveResultsTraning
		SaveResultsTraning.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				MyApplication.CurrentTraining.Targets=new ArrayList<String>();
				for (int i=0; i<Targets.size();i++) 
				{
					MyApplication.CurrentTraining.Targets.add(Targets.get(i));
				}	
				Toast.makeText(context, "Cele trenigowe zosta³y zapisane!",Toast.LENGTH_LONG).show();

				//Intent j = new Intent(AddTargetsTraning.this, PlannTraning.class);
				//startActivity (j);
				finish();
			}

		});

		for (int i=0; i<MyApplication.CurrentTraining.Targets.size();i++) 
		{
			addButton(MyApplication.CurrentTraining.Targets.get(i)+ "\n");
			Targets.add(MyApplication.CurrentTraining.Targets.get(i));
		}
		//button dodaj nowy trening
		TargetsTranig.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				addButton("");
				final ScrollView scrollview = (ScrollView) findViewById(R.id.ScrollView_Targets);

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
		});


		activity=this;
	}

	public void addButton(String text) {

		Button btnTag = new Button(context);
		btnTag.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		if((text==""))
		{
			Targets.add("-  "+String.valueOf(Ed_TargetsWrite.getText()));
			btnTag.setText("-  "+String.valueOf(Ed_TargetsWrite.getText())+"										X"+"\n");
			ScrollDescriptionTargets.fullScroll(View.FOCUS_DOWN);
		}
		else {
			btnTag.setText(text);
		}


		//btnTag.setBackgroundColor(Color.rgb(25,165, 111));
		btnTag.getBackground().setAlpha(1);
		btnTag.setTypeface(null, Typeface.BOLD);
		btnTag.setTextSize(18);
		btnTag.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v) 
			{
				try
				{
					int index = LinearLayout.indexOfChild(v);
					LinearLayout.removeViewAt(index);
					
					Targets.remove(index);
				
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
		//Intent j = new Intent(AddTargetsTraning.this, PlannTraning.class);
		//startActivity (j);
		overridePendingTransition(R.anim.dol, R.anim.gora);
		finish();
	}

}
