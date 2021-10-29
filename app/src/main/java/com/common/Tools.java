package com.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.model.Unit;
import android.content.Context;
import android.widget.Toast;

public class Tools 
{
	public static int tryParseInt(String value) {  
	     try {  
	    	 return Integer.parseInt(value);  
	      } catch (NumberFormatException e) {  
	         return 0;  
	      }  
	}

	public static boolean[] getDaysOfWeek(String data) {
       
		boolean[] records= new boolean[7];
		int i=0;
		for(String value: data.split("\\|")) 
       {
		     try 
		     {  
		    	 records[i]= Boolean.parseBoolean(value);  
		     } 
		     catch (NumberFormatException e) {  
		    	 records[i]=false; 
		      } 
       	i++;
       }

		return records;
	}

	public static List<Unit> getResults(String data) {
		List<Unit> records= new ArrayList<Unit>();
		List<String> temp=Arrays.asList(data.split("\\|"));
		for(int i=0;i<temp.size()-2;i+=3) 
       {
			Unit unit=new Unit();
			unit.Id=temp.get(i);
			unit.Name=temp.get(i+1);
			unit.Amount=temp.get(i+2);
			records.add(unit);
       }

		return records;
	}

	public static String getDataDaysOfWeek(boolean[] daysOfWeek) 
	{
		String result="";
		
		for (boolean days : daysOfWeek)
		{
			result+=days+"|";
		}
		result=result.subSequence(0, result.length()-1)+"";
		
		return result;
	}

	public static String getDataTargets(List<String> targets)
	{
		String result="";
		
		for (String target : targets)
		{
			result+=target+"|";
		}
		result=result.subSequence(0, result.length()-1)+"";
		
		return result;
	}

	public static String getDataResults(List<Unit> results) {
		String result="";
		for (Unit unit : results) 
		{
			result+=unit.Id+"|"+unit.Name+"|"+unit.Amount+"|";
		}
		result=result.subSequence(0, result.length()-1)+"";
		return result;
	}
	
	public static void WriteMsg(String message, Context context)
	{
		 Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}
}
