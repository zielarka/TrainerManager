package com.views;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Metronome.Metronome;
import com.trainmanager.MainActivity;
import com.trainmanager.R;

import java.util.Timer;
import java.util.TimerTask;

public class Tempometr extends Activity {

	private final short minBpm = 1;
	private final short maxBpm = 450;
	private Button StartStop;

	private short bpm = 100;

	private short beats = 9999;
	private short volume;
	private short initialVolume;
	private double beatSound = 2500;
	private double sound = 2000;
	private AudioManager audio;
	private MetronomeAsyncTask metroTask;

	private Button BackMenuTempo;
	private Button plusButton;
	private Button minusButton;
	private TextView currentBeat;
	private int iterator=0;
	private int oldBeat=0;

	private Handler mHandler;


	private long startTime = 0L;
	private Handler customHandler = new Handler();
	long timeInMilliseconds = 0L;

	long timeSwapBuff = 0L;
	Timer timer = new Timer();

	long updatedTime = 0L;
	private TextView timerValue;
	private boolean isRunning;


	boolean startStopclause=true;
	boolean test=false;

	public static LinearLayout  LN_StartStopTempo;
	public static LinearLayout  LN_PlusMinus;
	public static LinearLayout  LN_BackmenuScreen;
	

	private Runnable updateTimerThread = new Runnable() {



		public void run() {
			timeInMilliseconds = SystemClock.uptimeMillis() - startTime;	
			updatedTime = timeSwapBuff + timeInMilliseconds; 
			timeSwapBuff=0;
			timeInMilliseconds=0;
			int secs = (int) (updatedTime / 1000);   	
			int mins = secs / 60;    	
			secs = secs % 60;    	
			int milliseconds = (int) (updatedTime % 1000);    	
			timerValue.setText("" + mins + ":"    	
					+ String.format("%02d", secs) + ":"
					+ String.format("%03d", milliseconds));
			customHandler.postDelayed(this, 0);
		}

	};



	// have in mind that: http://stackoverflow.com/questions/11407943/this-handler-class-should-be-static-or-leaks-might-occur-incominghandler
	// in this case we should be fine as no delayed messages are queued
	private Handler getHandler() {
		return new Handler() {
			@Override
			public void  handleMessage(Message msg) {
				String message = (String)msg.obj;




				currentBeat.setText(message);
			}
		};
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {    	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tempometr);
		metroTask = new MetronomeAsyncTask();

		StartStop=(Button) findViewById(R.id.startstop);


		timerValue = (TextView) findViewById(R.id.timerValue);
		Typeface TypeFaceOnStoperMetronome = Typeface.createFromAsset(getAssets(), "digital.ttf");

		timerValue.setTypeface(TypeFaceOnStoperMetronome);
		timerValue.setGravity(Gravity.CENTER_VERTICAL );
		timerValue.setGravity(Gravity.CENTER_HORIZONTAL );

		Typeface TypeFaceOnMetronomeBeats = Typeface.createFromAsset(getAssets(), "CollegiateInsideFLF.ttf");
		TextView bpmText = (TextView) findViewById(R.id.bps);
		bpmText.setText(""+bpm);
		bpmText.setTypeface(TypeFaceOnMetronomeBeats);


		plusButton = (Button) findViewById(R.id.plus);
		plusButton.setOnLongClickListener(plusListener);

		minusButton = (Button) findViewById(R.id.minus);
		minusButton.setOnLongClickListener(minusListener);

		currentBeat = (TextView) findViewById(R.id.currentBeat);
		currentBeat.setTypeface(TypeFaceOnMetronomeBeats);


		LN_StartStopTempo= (LinearLayout)findViewById(R.id.Ln_BTNTempo3);
		LN_PlusMinus= (LinearLayout)findViewById(R.id.Ln_BTNTempo2);
		LN_BackmenuScreen= (LinearLayout)findViewById(R.id.Ln_BTNTempor4);

		audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		initialVolume = (short) audio.getStreamVolume(AudioManager.STREAM_MUSIC);
		volume = initialVolume;

		BackMenuTempo = (Button) findViewById(R.id.back_menu_tempo);

		//SeekBar volumebar = (SeekBar) findViewById(R.id.volumebar);
		//volumebar.setMax(audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
		//volumebar.setProgress(volume);
		//volumebar.setOnSeekBarChangeListener(volumeListener);
		
		/////////////////////////////////CheckBox-activty sceen//////////////////
        final Button  Buttonactivtyscreen = (Button) this.findViewById(R.id.Button_activty_screen);

        Buttonactivtyscreen.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

        if(test)
		{

          	
        	Toast.makeText(getApplicationContext(), "Wygaszanie ekranu zosta³o wy³¹czone!",Toast.LENGTH_SHORT).show();
        	getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); 
        	LN_BackmenuScreen.setBackground(getResources().getDrawable(R.drawable.backmenuon));
			test=false;

		}
		else
		{
		 	Toast.makeText(getApplicationContext(), "Wygaszanie ekranu zosta³o w³¹czone!",Toast.LENGTH_SHORT).show();
        	getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); 
        	LN_BackmenuScreen.setBackground(getResources().getDrawable(R.drawable.backmenuoff));
			test=true;


		}


	}
});
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); 
		LN_BackmenuScreen.setBackground(getResources().getDrawable(R.drawable.backmenuon));

		//////////////////button Back menu in bottom display//////////////////////////
		BackMenuTempo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent k = new Intent(Tempometr.this, MainActivity.class);
				startActivity (k);
				metroTask.stop();
				metroTask = new MetronomeAsyncTask();
				Runtime.getRuntime().gc();
				audio.setStreamVolume(AudioManager.STREAM_MUSIC, initialVolume, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
				Intent j = new Intent(Tempometr.this, MainActivity.class);
				startActivity (j);
				overridePendingTransition(R.anim.dol, R.anim.gora);  
				finish();    
			}
		});

		////////////////////ButtonStartStop///////////////////
		StartStop.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				EnableNotification();

				if(startStopclause) 

				{
					LN_StartStopTempo.setBackground(getResources().getDrawable(R.drawable.stoptempo6));
					metroTask = new MetronomeAsyncTask();
					Runtime.getRuntime().gc();
					startTime = SystemClock.uptimeMillis();
					customHandler.postDelayed(updateTimerThread, -1000);
					startStopclause=false;
					metroTask.execute();


					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							try {
									/*if(oldBeat==Metronome.currentBeat)
									{
										if(iterator!=0)
										{
											LN_StartStopTempo.setBackground(getResources().getDrawable(R.drawable.stoptempo));
											iterator=0;
										}
										return;
									}
									if(oldBeat!=Metronome.currentBeat)
									{
										if(iterator!=1)
										{
											LN_StartStopTempo.setBackground(getResources().getDrawable(R.drawable.stoptempo1));
											iterator=1;
										}
										oldBeat=Metronome.currentBeat;
									}
								iterator++;
								if(iterator%100==0 
										|| iterator%100==1
											|| iterator%100==2
													|| iterator%100==3
															|| iterator%100==4
																	|| iterator%100==5
																			|| iterator%100==6)
								{
									if(iterator%7==0)
									{
										LN_StartStopTempo.setBackground(getResources().getDrawable(R.drawable.stoptempo));
									}
									if(iterator%7==1)
									{
										LN_StartStopTempo.setBackground(getResources().getDrawable(R.drawable.stoptempo1));
									}
									if(iterator%7==2)
									{
										LN_StartStopTempo.setBackground(getResources().getDrawable(R.drawable.stoptempo2));
									}
									if(iterator%7==3)
									{
										LN_StartStopTempo.setBackground(getResources().getDrawable(R.drawable.stoptempo3));
									}
									if(iterator%7==4)
									{
										LN_StartStopTempo.setBackground(getResources().getDrawable(R.drawable.stoptempo4));
									}
									if(iterator%7==5)
									{
										LN_StartStopTempo.setBackground(getResources().getDrawable(R.drawable.stoptempo5));
									}
									if(iterator%7==6)
									{
										LN_StartStopTempo.setBackground(getResources().getDrawable(R.drawable.stoptempo6));
									}
								}
								idbutton=v.getId();(882);
								dicionary<idbutton,index> diionary
								DB.Trainings.remove(DB.Trainings[diionary[idbutton]]);
								DB.Reolad();*/
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}, 100,100);

				} 


				else {


					LN_StartStopTempo.setBackground(getResources().getDrawable(R.drawable.playtempo));
					timeSwapBuff += timeInMilliseconds;
					customHandler.removeCallbacks(updateTimerThread);

					startStopclause=true;
					metroTask.stop();
					metroTask = new MetronomeAsyncTask();
					Runtime.getRuntime().gc();

				}
			}

		});

		plusButton.setOnTouchListener(new OnTouchListener() {

		    @Override
		    public boolean onTouch(View v, MotionEvent event) {

		        switch(event.getAction()) {

		        case MotionEvent.ACTION_DOWN:
		        	
		        	LN_PlusMinus.setBackground(getResources().getDrawable(R.drawable.tempoup));
		        	
		        
		        
		            break;
		        case MotionEvent.ACTION_UP:
		        	LN_PlusMinus.setBackground(getResources().getDrawable(R.drawable.plusminustempo));
		    
		         
		            break;
		        }

		        return false;
		    }

		});

		minusButton.setOnTouchListener(new OnTouchListener() {

		    @Override
		    public boolean onTouch(View v, MotionEvent event) {

		        switch(event.getAction()) {

		        case MotionEvent.ACTION_DOWN:
		        	
		        	LN_PlusMinus.setBackground(getResources().getDrawable(R.drawable.tempodown));
		        	
		        
		        
		            break;
		        case MotionEvent.ACTION_UP:
		        	LN_PlusMinus.setBackground(getResources().getDrawable(R.drawable.plusminustempo));
		    
		         
		            break;
		        }

		        return false;
		    }

		});

		
	}
	
	
	private void maxBpmGuard() {
		if(bpm >= maxBpm) {
			plusButton.setEnabled(false);
			plusButton.setPressed(false);
		} else if(!minusButton.isEnabled() && bpm>minBpm) {
			minusButton.setEnabled(true);
		}    	
	}

	public void onPlusClick(View view) {

		bpm++;
		TextView bpmText = (TextView) findViewById(R.id.bps);
		bpmText.setText(""+bpm);
		metroTask.setBpm(bpm);
		maxBpmGuard();
		iterator++;
		//LN_PlusMinus.setBackground(getResources().getDrawable(R.drawable.tempoup));
	}

	private OnLongClickListener plusListener = new OnLongClickListener() {

		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			bpm+=20;
			if(bpm >= maxBpm)
				bpm = maxBpm;
			TextView bpmText = (TextView) findViewById(R.id.bps);
			bpmText.setText(""+bpm);
			metroTask.setBpm(bpm);
			maxBpmGuard();
			return true;
		}

	};

	private void minBpmGuard() {
		if(bpm <= minBpm) {
			minusButton.setEnabled(false);
			minusButton.setPressed(false);
		} else if(!plusButton.isEnabled() && bpm<maxBpm) {
			plusButton.setEnabled(true);
		}    	
	}

	public void onMinusClick(View view) {
		bpm--;
		TextView bpmText = (TextView) findViewById(R.id.bps);
		bpmText.setText(""+bpm);
		metroTask.setBpm(bpm);
		minBpmGuard();
	}

	private OnLongClickListener minusListener = new OnLongClickListener() {

		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			bpm-=20;
			if(bpm <= minBpm)
				bpm = minBpm;
			TextView bpmText = (TextView) findViewById(R.id.bps);
			bpmText.setText(""+bpm);
			metroTask.setBpm(bpm);
			minBpmGuard();
			return true;
		}

	};
/*
	private OnSeekBarChangeListener volumeListener = new OnSeekBarChangeListener() {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub
			volume = (short) progress;
			audio.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
		}   	

	};
*/
	public void onBackPressed() {
		metroTask.stop();
		metroTask = new MetronomeAsyncTask();
		Runtime.getRuntime().gc();
		audio.setStreamVolume(AudioManager.STREAM_MUSIC, initialVolume, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
		Intent j = new Intent(Tempometr.this, MainActivity.class);
		startActivity (j);
		overridePendingTransition(R.anim.dol, R.anim.gora);
		finish();    
	}

	private class MetronomeAsyncTask extends AsyncTask<Void,Void,String> {
		Metronome metronome;

		MetronomeAsyncTask() {
			mHandler = getHandler();
			metronome = new Metronome(mHandler);

		}





		protected String doInBackground(Void... params) {
			metronome.setBeat(beats);

			metronome.setBpm(bpm);
			metronome.setBeatSound(beatSound);
			//metronome.setSound(sound);

			metronome.play();

			return null;


		}

		public void stop() {
			metronome.stop();
			metronome = null;
		}

		public void setBpm(short bpm) {
			metronome.setBpm(bpm);
			metronome.calcSilence();
		}

		public void setBeat(short beat) {
			if(metronome != null)
				metronome.setBeat(beat);
		}



	}


	private void EnableNotification()
	{

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getBaseContext())
				.setSmallIcon(R.drawable.ic_launcher) // notification icon
				.setContentTitle(getString(R.string.TitleTempometr)) // title for notification
				//.setContentText(String.format("%02d", Parameters.HourStartAlarmSpecificTime) + ":" + String.format("%02d", Parameters.MinuteStartAlarmSpecificTime) + " - " + String.format("%02d", Parameters.HourStopAlarmSpecificTime) + ":" + String.format("%02d", Parameters.MinuteStopAlarmSpecificTime)) // message for notification
				.setAutoCancel(false); // clear notification after click
		Intent intent = new Intent(getBaseContext(), Tempometr.class);
		PendingIntent pi = PendingIntent.getActivity(getBaseContext(), 0, intent, 0);
		mBuilder.setContentIntent(pi);
		NotificationManager mNotificationManager =
				(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(0, mBuilder.build());
	}



}



