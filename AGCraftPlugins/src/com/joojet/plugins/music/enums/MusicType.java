package com.joojet.plugins.music.enums;

import com.joojet.plugins.music.duration.MusicDuration;

public enum MusicType {
	GORO_THEME ("boss.goro_theme", "2:40"),
	KUZE_THEME ("boss.kuze_theme", "3:56"),
	DOOM_GUY ("boss.doom_theme", "1:41");
	
	/** Name of the custom sound event specified in the resource pack */
	private String namespace;
	/** Stores the duration of the song, which will be used to determine if a piece of music is still playing
	 *  at a location / player in the API. */
	private MusicDuration duration;
	
	private MusicType (String namespace, String duration)
	{
		this.namespace = namespace;
		this.duration = new MusicDuration (duration);
	}
	
	public String getNamespace ()
	{
		return this.namespace;
	}
	
	public MusicDuration duration ()
	{
		return this.duration;
	}
	
	@Override
	public String toString ()
	{
		return this.getNamespace();
	}
}
