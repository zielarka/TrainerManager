package com.model;

import java.util.ArrayList;
import java.util.List;

public class Training 
{
	public int Id=0;
	public String Name;	//nazwa trenigu
	public boolean[] DaysOfWeek= new boolean[7]; //true tak jest w poniedzia³ek np.
	public int TimeStartTrainingHour;//"00:00:00" format jak TimeSpan,budzik na trening
	public int TimeStartTrainingMinute;//"00:00:00" format jak TimeSpan,budzik na trening
	public int TimeEndTrainingHour; // "00:00:00" dziennik treningowy
	public int TimeEndTrainingMinute; // "00:00:00" dziennik treningowy
	public String Type;  //woda; bieg; si³ownia;
	public String DescriptionTraining; // opis trenigu;
	public List<String> Targets=new ArrayList<String>();	//cele trenigowe
	public List<Unit> Results=new ArrayList<Unit>();	 //dziennik wynik trenigu
	public String Date;//data realizacji treningu
	
	
	public Training()
	{
		DaysOfWeek[0]=true;
	}
}
