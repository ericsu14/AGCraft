package com.joojet.biblefetcher.main;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.dsp.onsets.PercussionOnsetDetector;
import be.tarsos.dsp.onsets.PrintOnsetHandler;

public class TestAudioPercussionMic 
{
	
	private static String OS = null;
	public static String getOsName()
	{
		if(OS == null)
			OS = System.getProperty("os.name");
	    return OS;
	}
	
	public static boolean isWindows()
	{
	   return getOsName().startsWith("Windows");
	}
	
	public static String toLocalString(Object info)
	{
		if(!isWindows())
			return info.toString();
		String defaultEncoding = Charset.defaultCharset().toString();
		try
		{
			return new String(info.toString().getBytes("windows-1252"), defaultEncoding);
		}
		catch(UnsupportedEncodingException ex)
		{
			return info.toString();
		}
	}
	
	public static void main (String [] args) throws LineUnavailableException
	{
		float sampleRate = 44100;
		int bufferSize = 1024;
		int overlap = 0;
		
		// Selects a specific mixer
		int index = 0;
		int selectedMixerIndex = 3;
		for (Mixer.Info mixer : AudioSystem.getMixerInfo())
		{
			System.out.println (index + ":" + toLocalString (mixer));
			++index;
		}
		
		Mixer.Info selectedMixer = AudioSystem.getMixerInfo()[selectedMixerIndex];
		System.out.println ("Selected mixer: " + toLocalString (selectedMixer));
		
		// Opens a line wavelet
		final Mixer mixer = AudioSystem.getMixer(selectedMixer);
		final AudioFormat format = new AudioFormat (sampleRate, 16, 1, true, false);
		final DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, format);
		TargetDataLine line;
		
		line = (TargetDataLine) mixer.getLine(dataLineInfo);
		final int numberOfSamples = bufferSize;
		line.open (format, numberOfSamples);
		line.start();
		
		final AudioInputStream stream = new AudioInputStream (line);
		JVMAudioInputStream inputStream = new JVMAudioInputStream (stream);
		
		// Creates a new dispatcher
		AudioDispatcher dispatcher = new AudioDispatcher (inputStream, bufferSize, overlap);
		
		// Add a processor, handle percussion event
		dispatcher.addAudioProcessor(new PercussionOnsetDetector (sampleRate, bufferSize, overlap, new PrintOnsetHandler ()));
		
		// Run the dispatcher on a new thread
		dispatcher.run();
	}
}
