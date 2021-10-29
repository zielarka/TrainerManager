package com.Metronome;


	import java.util.ArrayList;
import java.util.List;







import com.trainmanager.R;

import android.content.ContextWrapper;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;

	public class Metronome {
		
		private static double bpm;
		private int beat;
		private int noteValue;
		private int silence;
		private static boolean first;

		private static double beatSound;
		private static double sound;
		private final int tick = 1000; // samples of tick
		
		public static boolean play = true;
		
		private static AudioGenerator audioGenerator = new AudioGenerator(8000);
		private static Handler mHandler;
		private static double[] soundTickArray;
		private static double[] soundTockArray;
		private static double[] silenceSoundArray;
		private Message msg;
		public static int currentBeat = 1;
		
		public Metronome(Handler handler) {
			audioGenerator.createPlayer();
			this.mHandler = handler;
		}
		
		public void calcSilence() {
			silence = (int) (((60/bpm)*8000)-tick);		
			soundTickArray = new double[this.tick];	
			soundTockArray = new double[this.tick];
			silenceSoundArray = new double[this.silence];
			msg = new Message();
			msg.obj = ""+currentBeat;
			double[] tick = audioGenerator.getSineWave(this.tick, 8000, beatSound);
			double[] tock = audioGenerator.getSineWave(this.tick, 8000, beatSound);
			for(int i=0;i<this.tick;i++) {
				soundTickArray[i] = tick[i];
				soundTockArray[i] = tock[i];
			}
			for(int i=0;i<silence;i++)
				silenceSoundArray[i] = 0;
		}
		
		public void play() {
			calcSilence();
			play=true;
			currentBeat=1;
			if(!first)
			{
				Thread thread = new Thread()
				{
					@Override
					public void run()
					{
						while(true)
						{
							try 
							{
								if(play)
								{
									msg = new Message();
									msg.obj = ""+currentBeat;
									if(currentBeat == 1)
										audioGenerator.writeSound(soundTockArray);
									else
										audioGenerator.writeSound(soundTickArray);				
									if(bpm <= 120)
										mHandler.sendMessage(msg);
									audioGenerator.writeSound(silenceSoundArray);
									if(bpm > 120)
										mHandler.sendMessage(msg);
									currentBeat++;
									if(currentBeat > beat)
										currentBeat = 1;
								}
								else
								{
									try {
										sleep(500);
									} catch (InterruptedException e) 
									{
									}
								}
							} 
							catch (Exception e) 
							{
							}
						}
					};
				};
				thread.start();
				first=true;
			}
			//while(play)
			//{

			//}
		}
		
		public void stop() {
			play = false;
			audioGenerator.destroyAudioTrack();
		}

		public double getBpm() {
			return bpm;
		}

		public void setBpm(int bpm) {
			this.bpm = bpm;
		}

		public int getNoteValue() {
			return noteValue;
		}

		public void setNoteValue(int bpmetre) {
			this.noteValue = bpmetre;
		}

		public int getBeat() {
			return beat;
		}

		public void setBeat(int beat) {
			this.beat = beat;
		}

		public double getBeatSound() {
			return beatSound;
		}

		public void setBeatSound(double sound1) {
			this.beatSound = sound1;
		}

		public double getSound() {
			return sound;
		}

		public void setSound(double sound2) {
			this.sound = sound2;
		}

	}
