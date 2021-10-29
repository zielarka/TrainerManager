package com.common;

import java.util.HashMap;
import java.util.Map;

import com.trainmanager.MyApplication;
import com.trainmanager.R;

public class Dictionaries 
{
	public static String FileNameTrainings;
	public static Map<String, String> DictionaryTreninigsTypes = new HashMap<String, String>();
	public static Map<String, String> DictionaryDayofWeek = new HashMap<String, String>();
	public static Map<String, String> DictionaryUnit = new HashMap<String, String>();
	
	public Dictionaries()
	{
		FileNameTrainings= MyApplication.Context.getString(R.string.fileNameTrainings);
		DictionaryTreninigsTypes.put("0",MyApplication.Context.getString(R.string.treningType1));
		DictionaryTreninigsTypes.put("1",MyApplication.Context.getString(R.string.treningType2));
		DictionaryTreninigsTypes.put("2",MyApplication.Context.getString(R.string.treningType3));
		DictionaryTreninigsTypes.put("3",MyApplication.Context.getString(R.string.treningType4));
		DictionaryTreninigsTypes.put("4",MyApplication.Context.getString(R.string.treningType5));
		DictionaryTreninigsTypes.put("5",MyApplication.Context.getString(R.string.treningType6));
		DictionaryTreninigsTypes.put("6",MyApplication.Context.getString(R.string.treningType7));
		DictionaryTreninigsTypes.put("7",MyApplication.Context.getString(R.string.treningType8));
		DictionaryTreninigsTypes.put("8",MyApplication.Context.getString(R.string.treningType9));
		
		DictionaryDayofWeek.put("0",MyApplication.Context.getString(R.string.Monday));
		DictionaryDayofWeek.put("1",MyApplication.Context.getString(R.string.Tuesday));
		DictionaryDayofWeek.put("2",MyApplication.Context.getString(R.string.Wednesday));
		DictionaryDayofWeek.put("3",MyApplication.Context.getString(R.string.Thursday));
		DictionaryDayofWeek.put("4",MyApplication.Context.getString(R.string.Friday));
		DictionaryDayofWeek.put("5",MyApplication.Context.getString(R.string.Saturady));
		DictionaryDayofWeek.put("6",MyApplication.Context.getString(R.string.Sunday));
		
		DictionaryUnit.put("0",MyApplication.Context.getString(R.string.km));
		DictionaryUnit.put("1",MyApplication.Context.getString(R.string.kg));
		DictionaryUnit.put("2",MyApplication.Context.getString(R.string.ton));
		DictionaryUnit.put("3",MyApplication.Context.getString(R.string.m));
		DictionaryUnit.put("4",MyApplication.Context.getString(R.string.speed));
		DictionaryUnit.put("5",MyApplication.Context.getString(R.string.pulse));
		DictionaryUnit.put("6",MyApplication.Context.getString(R.string.minutes));
		DictionaryUnit.put("7",MyApplication.Context.getString(R.string.hours));
	}
}
