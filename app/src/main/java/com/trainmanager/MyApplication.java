package com.trainmanager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.model.Training;
import com.model.Unit;


import com.trainmanager.R.string;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings.Secure;

import com.common.DB;
import com.common.Dictionaries;

public class MyApplication extends Application 
{
	
	public static android.content.Context Context;
	public static DB DB;
	public static int GapHeight=0;
	
	public static float GapHeightDown=0.1f;
	public static Training CurrentTraining= new Training();
	public static String EmptyTarget="-";
	public static String EmptyName="-";
	public static Unit EmptyUnit= new Unit(){{Id="0";Name= "-";Amount="-";}};
	
	public static int SizeTextButtonName=18;
	public static int SizeTextButton=15;
	public static int SizeTextButtonTargetsRezults=13;
	public static int SizeTextButtonHours=15;
	public static int SizeTextOfflineNet=30;
	public static int HeightButton;
	public static int Height;
	public static int Width;
	
	@Override
    public void onCreate() {
        super.onCreate();
        
        Context = getApplicationContext();
        Dictionaries dictionaries=new Dictionaries();
        DB= new DB();       
   
    }
}
