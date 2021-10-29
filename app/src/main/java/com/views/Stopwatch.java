package com.views;




import com.Metronome.Metronome;
import com.trainmanager.MainActivity;
import com.trainmanager.MyApplication;
import com.trainmanager.R;
















import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

@SuppressLint({ "NewApi", "ResourceAsColor" })
public class Stopwatch extends ActionBarActivity {

	//Stoper//
	private TextView TvStoper;
	private Button Start;
	private Button Lap;
	private Button Reset;
	private Button BackMenu;
	//public static  ScrollView ScrolLaps;

	private int mLaps=1;

	//private Context mContext;
	private MetronomeAsyncTask metroTask;
	private Handler mHandler;
	boolean test=false;

	//private Thread mThreadChrono;

	boolean startStop=true;

	private long startTime = 0L;
	private Handler customHandler = new Handler();
	long timeInMilliseconds = 0L;

	long timeSwapBuff = 0L;

	long updatedTime = 0L;
	//private TextView timerValue;
	//private boolean isRunning;

	//private static Activity activity;
	private static LinearLayout LinearLayout;
	private static Context context;

	public static LinearLayout  LN_StartStopRestart;
	public static LinearLayout  LN_BackmenuScreen;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stopwatch);


		//mContext=this;

		Typeface TypeFaceOnTvStoper = Typeface.createFromAsset(getAssets(), "digital.ttf");
		final Typeface TypeFaceLapButton = Typeface.createFromAsset(getAssets(), "ERASBD.TTF");
		TvStoper=(TextView) findViewById(R.id.tvstoper);
		//ScrolLaps= (ScrollView) findViewById(R.id.laps_save);
		Start=(Button) findViewById(R.id.start);
		Lap=(Button) findViewById(R.id.lap);
		Reset=(Button) findViewById(R.id.reset);
		BackMenu=(Button) findViewById(R.id.back_menu);
		metroTask = new MetronomeAsyncTask();

		context=getApplicationContext();

		LinearLayout= (LinearLayout) findViewById(R.id.LN_et_laps);
		LN_BackmenuScreen= (LinearLayout)findViewById(R.id.Ln_BTNStoper3);

		TvStoper.setTypeface(TypeFaceOnTvStoper);

		LN_StartStopRestart= (LinearLayout)findViewById(R.id.Ln_BTNStoper2);

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
		BackMenu.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent j = new Intent(Stopwatch.this, MainActivity.class);
				startActivity (j);
				overridePendingTransition(R.anim.dol, R.anim.gora);
				finish();
			}
		});


		//////////////////button Start//////////////////////////
		Start.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {


				if(startStop)
				{


					LN_StartStopRestart.setBackground(getResources().getDrawable(R.drawable.stoperstop));
					startStop=false;


					startTime = SystemClock.uptimeMillis();
					customHandler.postDelayed(updateTimerThread, 0);
				}
				else
				{
					LN_StartStopRestart.setBackground(getResources().getDrawable(R.drawable.stoperstart));
					startStop=true;


					//					Et_laps.setText("");

					timeSwapBuff += timeInMilliseconds;
					customHandler.removeCallbacks(updateTimerThread);
					//metroTask.stop();
					metroTask = new MetronomeAsyncTask();
					//Runtime.getRuntime().gc();

				}


			}
		});



		//////////////////button Reset//////////////////////////
		Reset.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				TvStoper.setText("" + 00 + ":"
						+ String.format("%02d", 00) + ":"
						+ String.format("%03d", 00));
				startTime = SystemClock.uptimeMillis();
				timeSwapBuff=0;


			}
		});

		//////////////////button Lap Save//////////////////////////



		Lap.setOnClickListener(new View.OnClickListener() {


			@Override
			public void onClick(View v) {
				int index = LinearLayout.indexOfChild(v);				

				Button Name = new Button(context);
				Name.setLayoutParams(new LayoutParams((int)(MyApplication.Width), (int)((MyApplication.Height)*0.098)));

				Name.setText(String.valueOf(mLaps) + "      "+ String.valueOf(TvStoper.getText())+ "           X"+"\n");
				Name.setBackgroundColor(Color.rgb(0,0,0));
				Name.setTypeface(TypeFaceLapButton);
				LinearLayout.addView(Name);
				Name.setTextSize(25);
				Name.setTextColor(Color.parseColor("#946200"));
				index=mLaps++;
				//ScrolLaps.fullScroll(View.FOCUS_DOWN);

				final ScrollView scrollview = ((ScrollView) findViewById(R.id.laps_save));

				scrollview.post(new Runnable() {

					@Override
					public void run() {
						scrollview.fullScroll(ScrollView.FOCUS_DOWN);
					}
				});




				Name.setOnClickListener(new View.OnClickListener()
				{
					public void onClick(View v) 
					{

						int index = LinearLayout.indexOfChild(v);
						LinearLayout.removeViewAt(index);
						index=mLaps--;

					}
				});
			}
		});

		//activity=this;
	}



	public void updateTimerText(final String time) 
	{
		runOnUiThread(new Runnable() {

			@Override
			public void run() {

				TvStoper.setText(time);

			}
		});


	}

	private Runnable updateTimerThread = new Runnable() {



		public void run() {
			timeInMilliseconds = SystemClock.uptimeMillis() - startTime;	
			updatedTime = timeSwapBuff + timeInMilliseconds; 	
			int secs = (int) (updatedTime / 1000);   	
			int mins = secs / 60;    	
			secs = secs % 60;    	
			int milliseconds = (int) (updatedTime % 1000);    	
			TvStoper.setText("" + mins + ":"    	
					+ String.format("%02d", secs) + ":"
					+ String.format("%03d", milliseconds));
			customHandler.postDelayed(this, 0);

		}

	};




	private class MetronomeAsyncTask extends AsyncTask<Void,Void,String> {
		Metronome metronome;
		/*
		MetronomeAsyncTask() {
			mHandler = getHandler();
			metronome = new Metronome(mHandler);
		}
		public void stop() {
			metronome.stop();
			metronome = null;
		}*/

		private Handler getHandler() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			return null;
		}}

	public void onBackPressed() 
	{
		Intent j = new Intent(Stopwatch.this, MainActivity.class);
		startActivity (j);
		overridePendingTransition(R.anim.dol, R.anim.gora);
		finish();
	}
}
