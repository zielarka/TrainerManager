package com.fragment;

import java.util.ArrayList;
import java.util.List;

import android.R.bool;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.common.DB;
import com.common.Dictionaries;
import com.model.Training;
import com.trainmanager.MyApplication;
import com.trainmanager.R;

public class LoaderView 
{
	public static RelativeLayout  hoursLayout;
	public static RelativeLayout  trainLayout;
	public int DaysOfWeek=0;
	public List<RelativeLayout> layouts=new ArrayList<RelativeLayout>();
	public List<Training> TrainingsMonday=new ArrayList<Training>();
	public List<RelativeLayout> layoutsHours=new ArrayList<RelativeLayout>();
	public boolean test=false;
	int i=0;
	final int margin = (int) ((int) MyApplication.Width*0.05);
	int y=0;
	int v=0;
	
	@SuppressLint("ResourceAsColor")
	public View Load(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState,int DaysOfWeekLocal,Context context, Resources Resource) {
		
		MyApplication.HeightButton=(int)(MyApplication.Height*0.04);
		DaysOfWeek=DaysOfWeekLocal;
		layouts=new ArrayList<RelativeLayout>();
		TrainingsMonday=new ArrayList<Training>();
		layoutsHours=new ArrayList<RelativeLayout>();
		View view = inflater.inflate(com.trainmanager.R.layout.activity_monday , container, false);
		hoursLayout= (RelativeLayout)view.findViewById(R.id.HoursLayout);
		trainLayout= (RelativeLayout)view.findViewById(R.id.TrainLayout);
	
		for ( int x=0;x<DB.Trainings.size();x++) 
		{
			if(DB.Trainings.get(x).DaysOfWeek[DaysOfWeek])
			{
				test=true;
				RelativeLayout linearLayoutMonday = new RelativeLayout(context);
				RelativeLayout.LayoutParams paramslinearLayoutMonday = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		
				if(v!=0)
				{
					paramslinearLayoutMonday.addRule(RelativeLayout.BELOW,999+v-1);
				}
				linearLayoutMonday.setLayoutParams(paramslinearLayoutMonday);
				linearLayoutMonday.setId(999+v);
				linearLayoutMonday.setBackground( Resource.getDrawable(R.drawable.frametraningplan));
				paramslinearLayoutMonday.setMargins(0,8,0,6);
				layouts.add(linearLayoutMonday);
				v++;
			}
		}
		RelativeLayout linearLayoutMonday = new RelativeLayout(context);
		RelativeLayout.LayoutParams paramslinearLayoutMonday = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, (int)(MyApplication.Height*MyApplication.GapHeightDown));
		linearLayoutMonday.setId(999+v);
		layouts.add(linearLayoutMonday);
		
		for (Training training : DB.Trainings) 
		{
			if(training.DaysOfWeek[DaysOfWeek])
			{

			//////////////////////////////////GAP START////////////////////////////////////////
			Button gap = new Button(context);//com.trainmanager.MyApplication.Context);
			RelativeLayout.LayoutParams paramsgap = new RelativeLayout.LayoutParams(MyApplication.Width, MyApplication.GapHeight);
			if(i!=0)
			{
				paramsgap.addRule(RelativeLayout.BELOW,i);
			}
			gap.getBackground().setAlpha(1);
			gap.setLayoutParams(paramsgap);
			gap.setId(1+i);
			layouts.get(y).addView(gap);	
			//////////////////////////////////GAP STOP/////////////////////////////////////////

			//////////////////////////////////Name START////////////////////////////////////////////////////

			TextView Name = new TextView(com.trainmanager.MyApplication.Context);
			RelativeLayout.LayoutParams paramsName = new RelativeLayout.LayoutParams(MyApplication.Width, LayoutParams.WRAP_CONTENT);
			paramsName.addRule(RelativeLayout.BELOW,1+i);
			Name.getLineCount();
			Name.setLayoutParams(paramsName);
			Name.setText(training.Name);	
			Name.setGravity(Gravity.CENTER | Gravity.CENTER);
			Name.setTextSize(MyApplication.SizeTextButtonName);
			Name.setTextColor(Color.rgb(243,176,75));
			Name.setTypeface(null, Typeface.BOLD);
			Name.setId(2+i);
			layouts.get(y).addView(Name);
			//////////////////////////////////Name STOP/////////////////////////////////////////////////////

			//////////////////////////////////GAP START////////////////////////////////////////
			Button gap2 = new Button(com.trainmanager.MyApplication.Context);
			RelativeLayout.LayoutParams paramsgap2 = new RelativeLayout.LayoutParams(MyApplication.Width, 2);
			gap2.getBackground().setAlpha(1);
			paramsgap2.addRule(RelativeLayout.BELOW,2+i);
			gap2.setBackgroundColor(Color.rgb(255,255,255));
			gap2.setLayoutParams(paramsgap2);
			paramsgap2.setMargins(20,0,20,0);
			gap2.setId(3+i);
			layouts.get(y).addView(gap2);
			//////////////////////////////////GAP STOP/////////////////////////////////////////

			//////////////////////////////////TYPE TRAINING START///////////////////////////////////////////

			
			TextView type = new TextView(com.trainmanager.MyApplication.Context);
			RelativeLayout.LayoutParams paramstype = new RelativeLayout.LayoutParams(MyApplication.Width,LayoutParams.WRAP_CONTENT);
			paramstype.addRule(RelativeLayout.BELOW,3+i);
			type.getLineCount();
			type.setLayoutParams(paramstype);
			type.setGravity(Gravity.CENTER | Gravity.CENTER);
			type.setText(Dictionaries.DictionaryTreninigsTypes.get(training.Type));
			type.setTextColor(Color.rgb(255,255,255));
			type.setTypeface(null, Typeface.BOLD);
			type.setId(4+i);
			layouts.get(y).addView(type);

			//////////////////////////////////TYPE TRAINING STOP////////////////////////////////////////////

			//////////////////////////////////GAP START////////////////////////////////////////
			Button gap3 = new Button(com.trainmanager.MyApplication.Context);
			RelativeLayout.LayoutParams paramsgap3 = new RelativeLayout.LayoutParams(MyApplication.Width, MyApplication.GapHeight);
			gap3.getBackground().setAlpha(1);
			paramsgap3.addRule(RelativeLayout.BELOW,4+i);
			//paramsgap3.setMargins(setmargin,0,setmargin,0);
			gap3.setLayoutParams(paramsgap3);
			gap3.setId(5+i);
			layouts.get(y).addView(gap3);
			//////////////////////////////////GAP STOP/////////////////////////////////////////

			//////////////////////////////////START TRAINING START//////////////////////////////////////////
			Button start = new Button(com.trainmanager.MyApplication.Context);
			RelativeLayout.LayoutParams paramsstart = new RelativeLayout.LayoutParams(MyApplication.Width/2-(margin*2),MyApplication.GapHeight);
			paramsstart.addRule(RelativeLayout.ALIGN_PARENT_LEFT,  RelativeLayout.TRUE);
			paramsstart.addRule(RelativeLayout.BELOW,5+i);
			start.setPadding(0, 0, 0, 0);
			start.setLayoutParams(paramsstart);
			start.setTextSize(MyApplication.SizeTextButton);
			start.getBackground().setAlpha(1);
			start.setId(6+i);
			layouts.get(y).addView(start);	

			Button stop = new Button(com.trainmanager.MyApplication.Context);
			RelativeLayout.LayoutParams paramsstop = new RelativeLayout.LayoutParams(MyApplication.Width/2-margin,MyApplication.GapHeight);
			paramsstop.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
			paramsstop.addRule(RelativeLayout.RIGHT_OF,6+i);
			paramsstop.addRule(RelativeLayout.BELOW,5+i);
			stop.setPadding(0, 0, 0, 0);
			stop.setLayoutParams(paramsstop);
			stop.setTextSize(MyApplication.SizeTextButton);
			stop.getBackground().setAlpha(1);
			stop.setId(7+i);
			layouts.get(y).addView(stop);
			//////////////////////////////////STOP TRAINING STOP////////////////////////////////////////////

			//////////////////////////////////DescriptionTrainingTitle START////////////////////////////////////////
			
			TextView DescriptionTrainingTitle = new TextView(com.trainmanager.MyApplication.Context);
			RelativeLayout.LayoutParams paramsDescriptionTrainingTitle = new RelativeLayout.LayoutParams(MyApplication.Width, LayoutParams.WRAP_CONTENT);
			paramsDescriptionTrainingTitle.addRule(RelativeLayout.BELOW,7+i);
			DescriptionTrainingTitle.getLineCount();
			DescriptionTrainingTitle.setPadding(0, 0, 0,0);
			DescriptionTrainingTitle.setLayoutParams(paramsDescriptionTrainingTitle);
			DescriptionTrainingTitle.setText("OPIS :");
			DescriptionTrainingTitle.setGravity(Gravity.CENTER | Gravity.CENTER);
			DescriptionTrainingTitle.setTextSize(MyApplication.SizeTextButton);
			DescriptionTrainingTitle.setTextColor(Color.rgb(243,176,75));
			DescriptionTrainingTitle.setTypeface(null, Typeface.BOLD);
			DescriptionTrainingTitle.setBackgroundColor(Color.rgb(16,200, 46));
			DescriptionTrainingTitle.getBackground().setAlpha(1);
			DescriptionTrainingTitle.setId(8+i);
			layouts.get(y).addView(DescriptionTrainingTitle);
			//////////////////////////////////GAP STOP/////////////////////////////////////////

			//////////////////////////////////Description Training START////////////////////////////////////
			TextView DescriptionTraining = new TextView(com.trainmanager.MyApplication.Context);
			RelativeLayout.LayoutParams paramsDescriptionTraining = new RelativeLayout.LayoutParams(MyApplication.Width, LayoutParams.WRAP_CONTENT);
			paramsDescriptionTraining.addRule(RelativeLayout.BELOW,8+i);
			DescriptionTraining.getLineCount();
			DescriptionTraining.setPadding(0, 0, 0,0);
			DescriptionTraining.setLayoutParams(paramsDescriptionTraining);
			DescriptionTraining.setText(training.DescriptionTraining);
			DescriptionTraining.setGravity(Gravity.CENTER | Gravity.CENTER);
			DescriptionTraining.setTextSize(MyApplication.SizeTextButton);
			DescriptionTraining.setTypeface(null, Typeface.BOLD);
			DescriptionTraining.setTextColor(Color.rgb(255,255,255));
			DescriptionTraining.setBackgroundColor(Color.rgb(16,200, 46));
			DescriptionTraining.getBackground().setAlpha(1);
			DescriptionTraining.setId(9+i);
			layouts.get(y).addView(DescriptionTraining);
			//////////////////////////////////Description Training STOP/////////////////////////////////////

			//////////////////////////////////GAP START////////////////////////////////////////
			Button gap5 = new Button(com.trainmanager.MyApplication.Context);
			RelativeLayout.LayoutParams paramsgap5 = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, MyApplication.GapHeight);
			gap5.getBackground().setAlpha(1);
			paramsgap5.addRule(RelativeLayout.BELOW,9+i);
			gap5.setLayoutParams(paramsgap5);
			gap5.setId(10+i);
			layouts.get(y).addView(gap5);
			//////////////////////////////////GAP STOP/////////////////////////////////////////

			//////////////////////////////////Targets Training START////////////////////////////////////////
			Button Targets = new Button(com.trainmanager.MyApplication.Context);
			RelativeLayout.LayoutParams paramsTargets = new RelativeLayout.LayoutParams(MyApplication.Width, MyApplication.HeightButton);
			paramsTargets.addRule(RelativeLayout.BELOW,10+i);
			Targets.setPadding(0, 0, 0, 0);
			Targets.setLayoutParams(paramsTargets);
			Targets.setTypeface(null, Typeface.BOLD);
			Targets.setTextColor(Color.rgb(243,176,75));
			Targets.setText("Cele Trenigowe :");
			Targets.setTextSize(MyApplication.SizeTextButton);
			Targets.getBackground().setAlpha(1);
			Targets.setGravity(Gravity.CENTER);
			Targets.setId(11+i);
			layouts.get(y).addView(Targets);
			//////////////////////////////////Targets Training STOP/////////////////////////////////////////

			//////////////////////////////////Targets Point Training START////////////////////////////////////////

			for(int j = 0; j < training.Targets.size(); j++)
			{
				TextView TargetsPoint = new TextView(com.trainmanager.MyApplication.Context);
				RelativeLayout.LayoutParams paramsTargetsPoint = new RelativeLayout.LayoutParams(MyApplication.Width, LayoutParams.WRAP_CONTENT);
				paramsTargetsPoint.addRule(RelativeLayout.BELOW,11+j+i);
				TargetsPoint.setLayoutParams(paramsTargetsPoint);
				TargetsPoint.setPadding(20, 0, 0, 10);
				TargetsPoint.setText("- "+training.Targets.get(j));
				TargetsPoint.setBackgroundColor(Color.rgb(16,200, 46));
				TargetsPoint.setTypeface(null, Typeface.BOLD);
				TargetsPoint.getBackground().setAlpha(1);
				TargetsPoint.setGravity(Gravity.LEFT);
				TargetsPoint.setTextColor(Color.rgb(255,255,255));
				TargetsPoint.setTextSize(MyApplication.SizeTextButtonTargetsRezults);
				TargetsPoint.setId(12+j+i);
				layouts.get(y).addView(TargetsPoint);

			}

			//////////////////////////////////Targets Point Training STOP/////////////////////////////////////////

			//////////////////////////////////GAP START////////////////////////////////////////
			Button gap6 = new Button(com.trainmanager.MyApplication.Context);
			RelativeLayout.LayoutParams paramsgap6 = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, MyApplication.GapHeight);
			//gap6.setBackgroundColor(Color.rgb(255,255,255));
			gap6.getBackground().setAlpha(1);
			paramsgap6.addRule(RelativeLayout.BELOW,11+training.Targets.size()+i);
			//paramsgap6.setMargins(setmargin,0,setmargin,0);
			gap6.setLayoutParams(paramsgap6);
			gap6.setId(12+training.Targets.size()+i);
			layouts.get(y).addView(gap6);
			//////////////////////////////////GAP STOP/////////////////////////////////////////


			//////////////////////////////////Results Training START////////////////////////////////////////
			Button Results = new Button(com.trainmanager.MyApplication.Context);
			RelativeLayout.LayoutParams paramsResults = new RelativeLayout.LayoutParams(MyApplication.Width, MyApplication.HeightButton);
			paramsResults.addRule(RelativeLayout.BELOW,12+training.Targets.size()+i);
			Results.setPadding(0, 0, 0, 0);
			Results.setLayoutParams(paramsResults);
			Results.setText("Wyniki Trenigowe :");
			Results.setTypeface(null, Typeface.BOLD);
			Results.setTextColor(Color.rgb(243,176,75));
			Results.setTextSize(MyApplication.SizeTextButton);
			//Results.setBackgroundColor(Color.rgb(16,200, 46));
			Results.getBackground().setAlpha(1);
			Results.setGravity(Gravity.CENTER);
			Results.setId(13+training.Targets.size()+i);
			layouts.get(y).addView(Results);
			//////////////////////////////////Results Training STOP/////////////////////////////////////////

			
			//////////////////////////////////Results Point Training START////////////////////////////////////////
			for(int k = 0; k < training.Results.size(); k++)
			{
				TextView ResultsPoint = new TextView(com.trainmanager.MyApplication.Context);
				RelativeLayout.LayoutParams paramsResultsPoint = new RelativeLayout.LayoutParams(MyApplication.Width, LayoutParams.WRAP_CONTENT);
				paramsResultsPoint.addRule(RelativeLayout.BELOW,13+training.Targets.size()+k+i);
				ResultsPoint.setPadding(20, 0, 0, 10);
				ResultsPoint.setLayoutParams(paramsResultsPoint);
				ResultsPoint.setText("- "+training.Results.get(k).Amount+" "+training.Results.get(k).Name);
				ResultsPoint.setBackgroundColor(Color.rgb(16,200, 36));
				ResultsPoint.getBackground().setAlpha(1);
				ResultsPoint.setGravity(Gravity.LEFT);
				ResultsPoint.setTypeface(null, Typeface.BOLD);
				ResultsPoint.setTextColor(Color.rgb(255,255,255));
				ResultsPoint.setTextSize(MyApplication.SizeTextButtonTargetsRezults);
				ResultsPoint.setId(14+training.Targets.size()+k+i);
				layouts.get(y).addView(ResultsPoint);
				
			}
			//////////////////////////////////Results Point Training STOP/////////////////////////////////////////

			//////////////////////////////////GAP START////////////////////////////////////////
			Button gap7 = new Button(com.trainmanager.MyApplication.Context);
			RelativeLayout.LayoutParams paramsgap7 = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, MyApplication.GapHeight);
			gap7.getBackground().setAlpha(1);
			paramsgap7.addRule(RelativeLayout.BELOW,13+training.Targets.size()+training.Results.size()+i);
			gap7.setLayoutParams(paramsgap7);
			gap7.setId(14+training.Targets.size()+training.Results.size()+i);
			layouts.get(y).addView(gap7);
			//////////////////////////////////GAP STOP/////////////////////////////////////////


			//}
			i=14+training.Targets.size()+training.Results.size()+i;
			y++;
			}
		}

		for (RelativeLayout layout : layouts) 
		{
			trainLayout.addView(layout);
		}
		
		for (int z=0;z< layouts.size()-1;z++) 
		{
			linearLayoutMonday  = new RelativeLayout(context);
			paramslinearLayoutMonday = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
			
			if(z!=0)
			{
				paramslinearLayoutMonday.addRule(RelativeLayout.BELOW,888+z-1);
			}
			linearLayoutMonday.setLayoutParams(paramslinearLayoutMonday);
			linearLayoutMonday.setId(888+z);
			linearLayoutMonday.setBackground(Resource.getDrawable(R.drawable.framehours));	
			paramslinearLayoutMonday.setMargins(0,6,0,6);
			hoursLayout.addView(linearLayoutMonday);
			layoutsHours.add(linearLayoutMonday);
		}

		if(test)
		{
		   final RelativeLayout headerLayout = (RelativeLayout)(view.findViewById(999+v-1));
		    ViewTreeObserver observer = headerLayout .getViewTreeObserver();
		            observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

		        @SuppressLint("ResourceAsColor")
				@Override
		        public void onGlobalLayout() {
		        
		    		for (Training tran : DB.Trainings) 
		    		{
		    			if(tran.DaysOfWeek[DaysOfWeek])TrainingsMonday.add(tran);
		    		}
		            int z=0;

		    		for (RelativeLayout layout : layoutsHours) 
		    		{
		    			try {
			    			if(z==0)
			    			{
			    				layout.getLayoutParams().height = layouts.get(z).getHeight();
			    			}
			    			
			    			else 
			    			{
			    			layout.getLayoutParams().height = layouts.get(z).getHeight();
			    			}

							Button hours = new Button(com.trainmanager.MyApplication.Context);
							RelativeLayout.LayoutParams paramshours = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,layout.getLayoutParams().height-10);
							hours.setLayoutParams(paramshours);
							Training train= TrainingsMonday.get(z);					
							hours.setText(String.format("%02d",train.TimeStartTrainingHour)+":"+String.format("%02d",train.TimeStartTrainingMinute) +"\n\n - \n\n"+ 
									String.format("%02d",train.TimeEndTrainingHour)+":"+String.format("%02d",train.TimeEndTrainingMinute));
							hours.getBackground().setAlpha(1);
							hours.setTextColor(Color.rgb(255,255, 255));
							hours.setTypeface(null, Typeface.BOLD);
							hours.setId(555+z);
							hours.setTextSize(MyApplication.SizeTextButtonHours);
							hours.setLayoutParams(paramshours);
							layout.addView(hours);
							
			    			z++;
						} 
		    			catch (Exception e)
		    			{
							z=0;
						}
		    		}
		    		
		        headerLayout .getViewTreeObserver().removeGlobalOnLayoutListener(this);
		    }
		});
			}
		
		return view;
	}
}
