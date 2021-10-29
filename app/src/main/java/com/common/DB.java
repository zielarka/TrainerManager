package com.common;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.model.Training;
import com.model.Unit;
import com.trainmanager.MyApplication;
import com.trainmanager.R;

import android.R.bool;
import android.provider.Settings.Secure;

import com.common.FileLoadSave;
import com.common.Tools;

public class DB 
{
	public static String IdPhone="";
	public static List<Training> Trainings=new ArrayList<Training>();
	public static List<Training> TrainingsHistory=new ArrayList<Training>();
	public static FileLoadSave FileLoadSave= new FileLoadSave();
	
	public DB()
	{
		IdPhone=Secure.getString(MyApplication.Context.getContentResolver(),Secure.ANDROID_ID); 
		Trainings=LoadTrainings();
	}
	
	public static void Reolad() 
	{
		List<String> data=FileLoadSave.Read(MyApplication.Context, MyApplication.Context.getString(R.string.fileNameTrainings));
		List<Training> taining=new ArrayList<Training>();
		try {
			for (int i = 0; i < data.size()-11; i+=12)
			{
				Training train=new Training();
				
				train.Id=Tools.tryParseInt(data.get(i));
				train.DaysOfWeek=Tools.getDaysOfWeek(data.get(i+1));
				train.TimeStartTrainingHour=Tools.tryParseInt(data.get(i+2));
				train.TimeStartTrainingMinute=Tools.tryParseInt(data.get(i+3));
				train.TimeEndTrainingHour=Tools.tryParseInt(data.get(i+4));
				train.TimeEndTrainingMinute=Tools.tryParseInt(data.get(i+5));
				train.DescriptionTraining=data.get(i+6);
				train.Targets=Arrays.asList(data.get(i+7).split("\\|"));
				train.Results=Tools.getResults(data.get(i+8));
				train.Date=data.get(i+9);
				train.Type=data.get(i+10);
				train.Name=data.get(i+11);
				taining.add(train);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		
		Trainings=taining;
	}
	
	private List<Training> LoadTrainings() 
	{
		List<String> data=FileLoadSave.Read(MyApplication.Context, MyApplication.Context.getString(R.string.fileNameTrainings));
		List<Training> taining=new ArrayList<Training>();
		try {
			for (int i = 0; i < data.size()-11; i+=12)
			{
				Training train=new Training();
				
				train.Id=Tools.tryParseInt(data.get(i));
				train.DaysOfWeek=Tools.getDaysOfWeek(data.get(i+1));
				train.TimeStartTrainingHour=Tools.tryParseInt(data.get(i+2));
				train.TimeStartTrainingMinute=Tools.tryParseInt(data.get(i+3));
				train.TimeEndTrainingHour=Tools.tryParseInt(data.get(i+4));
				train.TimeEndTrainingMinute=Tools.tryParseInt(data.get(i+5));
				train.DescriptionTraining=data.get(i+6);
				train.Targets=Arrays.asList(data.get(i+7).split("\\|"));
				train.Results=Tools.getResults(data.get(i+8));
				train.Date=data.get(i+9);
				train.Type=data.get(i+10);
				train.Name=data.get(i+11);
				taining.add(train);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return taining;
	}

	public static void Save() 
	{
		List<String> records=new ArrayList<String>();
		
		for (Training train : Trainings) 
		{
			records.add(train.Id+"");
			records.add(Tools.getDataDaysOfWeek(train.DaysOfWeek));
			records.add(train.TimeStartTrainingHour+"");
			records.add(train.TimeStartTrainingMinute+"");
			records.add(train.TimeEndTrainingHour+"");
			records.add(train.TimeEndTrainingMinute+"");
			records.add(train.DescriptionTraining+"");
			records.add(Tools.getDataTargets(train.Targets));
			records.add(Tools.getDataResults(train.Results));
			records.add(train.Date+"");
			records.add(train.Type+"");
			records.add(train.Name+"");
		}
		
		FileLoadSave.Save(records, MyApplication.Context, MyApplication.Context.getString(R.string.fileNameTrainings), false);
	}
	
	public static Training DeepCopy(Training train) 
	{
		Training training =new Training();
		training.Date=train.Date;
		training.DaysOfWeek= new boolean[7];
		int i=0;
		for (final boolean day : train.DaysOfWeek) 
		{
			training.DaysOfWeek[i]=day;
			i++;
		}
		training.DescriptionTraining=train.DescriptionTraining;
		training.Id=train.Id;
		training.Name=train.Name;
		for (final Unit unit : train.Results) 
		{
			training.Results.add(new Unit(){{Id=unit.Id;Amount=unit.Amount;Name=unit.Name;}});
		}
		for (final String target : train.Targets) 
		{
			training.Targets.add(target);
		}
		training.TimeEndTrainingHour=train.TimeEndTrainingHour;
		training.TimeEndTrainingMinute=train.TimeEndTrainingMinute;
		training.TimeStartTrainingHour=train.TimeStartTrainingHour;
		training.TimeStartTrainingMinute=train.TimeStartTrainingMinute;
		training.Type=train.Type;
		return training;
	}
}
