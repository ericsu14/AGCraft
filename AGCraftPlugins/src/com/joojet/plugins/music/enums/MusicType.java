package com.joojet.plugins.music.enums;

import com.joojet.plugins.music.duration.MusicDuration;

public enum MusicType {
	GORO_THEME ("boss.goro_theme", "2:40", MusicEndingType.GORO_THEME_END),
	KUZE_THEME ("boss.kuze_theme", "3:56", MusicEndingType.KUZE_THEME_END),
	DOOM_GUY ("boss.doom_theme", "1:41", MusicEndingType.DOOM_GUY_END);
	
	/** Name of the custom sound event specified in the resource pack */
	private String namespace;
	/** Stores the duration of the song, which will be used to determine if a piece of music is still playing
	 *  at a location / player in the API. */
	private MusicDuration duration;
	/** Stores the song ending enum for this song */
	private MusicEndingType endTheme;
	
	private MusicType (String namespace, String duration)
	{
		this.namespace = namespace;
		this.duration = new MusicDuration (duration);
		this.endTheme = null;
	}
	
	private MusicType (String namespace, String duration, MusicEndingType endTheme)
	{
		this.namespace = namespace;
		this.duration = new MusicDuration (duration);
		this.endTheme = endTheme;
	}
	
	public String getNamespace ()
	{
		return this.namespace;
	}
	
	public MusicDuration duration ()
	{
		return this.duration;
	}
	
	public MusicEndingType getEndTheme ()
	{
		return this.endTheme;
	}
	
	public boolean hasEndingTheme ()
	{
		return this.endTheme != null;
	}
	
	@Override
	public String toString ()
	{
		return this.getNamespace();
	}
}
