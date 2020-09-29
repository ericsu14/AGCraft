package com.joojet.plugins.music.enums;

import com.joojet.plugins.music.duration.MusicDuration;

public enum MusicEndingType {
	GORO_THEME_END ("boss_ending.goro_theme_end", "0:10"), 
	KUZE_THEME_END ("boss_ending.kuze_theme_end", "0:10"), 
	DOOM_GUY_END ("boss_ending.doom_guy_end", "0:03"),
	HAIKYUU_END ("boss_ending.haikyuu_end", "0:17"),
	BARNEY_END ("boss_ending.barney_end", "0:08"),
	SHREK_END ("boss_ending.shrek_end", "0:14");
	
	protected String namespace;
	
	protected MusicDuration duration;
	
	private MusicEndingType (String namespace, String duration)
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
