package com.joojet.biblefetcher.main;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.onsets.OnsetHandler;
import be.tarsos.dsp.onsets.PercussionOnsetDetector;

public class TestAudioPercussionSound 
{
	
	public static void main (String [] args) throws UnsupportedAudioFileException, IOException
	{
		String file = "./music/New York.wav";
		AudioFormat format = AudioSystem.getAudioInputStream(new File (file)).getFormat();
		AudioDispatcher dispatcher = AudioDispatcherFactory.fromFile(new File (file), 2048, 0);
		
		dispatcher.addAudioProcessor(new PercussionOnsetDetector (format.getSampleRate(), 2048, new OnsetHandler () {
			int i = 0;
			@Override
			public void handleOnset (double timestamp, double o)
			{
				System.out.println (i++ + "\t" + timestamp + "\t" + o);
			}
		}, 44, 4));
		System.out.println (dispatcher.toString());
		dispatcher.run();
	}
}
