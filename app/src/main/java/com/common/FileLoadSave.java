package com.common;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

public class FileLoadSave 
{
	public void Save(List<String> records,Context context,String filename, boolean append)
	{
	    try 
	    {
	    	String data="";
	    	OutputStreamWriter outputStreamWriter=null;
	    	if(append) outputStreamWriter= new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_APPEND));
	    	if(!append) outputStreamWriter= new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
	        for(String record: records) 
	        {
	        	data+=record+"*";
	        }
	        outputStreamWriter.write(data);
	        outputStreamWriter.close();
	    }
	    catch (Exception e) {
	        Log.e("Exception", "File write failed: " + e.toString());
	    } 
	}
	
	public List<String> Read(Context context,String filename)
	{
		List<String> records= new ArrayList<String>();
	    try 
	    {
		    FileInputStream fis = null;
		    InputStreamReader isr = null;
	
		    fis = context.openFileInput(filename);
		    isr = new InputStreamReader(fis);
		    char[] inputBuffer = new char[fis.available()];
		    isr.read(inputBuffer);
		    String data = new String(inputBuffer);
		    isr.close();
		    fis.close();
	        InputStream is = new ByteArrayInputStream(data.getBytes("UTF-8"));
	
		    BufferedReader r = new BufferedReader(new InputStreamReader(is));
		    String total = "";
		    String line;
		    while ((line = r.readLine()) != null) {
		        total+=line;
		    }
	        for(String record: total.split("\\*")) 
	        {
	        	records.add(record);
	        }
	    }
	    catch (Exception e) {
	        Log.e("Exception", "File read failed: " + e.toString());
	    } 
	         
		return records;
	}
	
	public static String ReadAsString(Context context,String filename)
	{
		String records= "";
	    try 
	    {
		    FileInputStream fis = null;
		    InputStreamReader isr = null;
	
		    fis = context.openFileInput(filename);
		    isr = new InputStreamReader(fis);
		    char[] inputBuffer = new char[fis.available()];
		    isr.read(inputBuffer);
		    String data = new String(inputBuffer);
		    isr.close();
		    fis.close();
	        InputStream is = new ByteArrayInputStream(data.getBytes("UTF-8"));
	
		    BufferedReader r = new BufferedReader(new InputStreamReader(is));
		    String total = "";
		    String line;
		    while ((line = r.readLine()) != null) {
		        total+=line;
		    }
		    records=total;
	    }
	    catch (Exception e) {
	        Log.e("Exception", "File read failed: " + e.toString());
	    } 
	         
		return records;
	}
	public static void SaveFromString(String file,Context context,String filename, boolean append)
	{
		/*List<String> records= new ArrayList<String>();
        for(String record: file.split("\\*")) 
        {
        	records.add(record);
        }*/
	    try 
	    {
	    	//String data="";
	    	OutputStreamWriter outputStreamWriter=null;
	    	if(append) outputStreamWriter= new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_APPEND));
	    	if(!append) outputStreamWriter= new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
	        /*for(String record: records) 
	        {
	        	data+=record+"*";
	        }*/
	        outputStreamWriter.write(file);
	        outputStreamWriter.close();
	    }
	    catch (Exception e) {
	        Log.e("Exception", "File write failed: " + e.toString());
	    } 
	}
}
