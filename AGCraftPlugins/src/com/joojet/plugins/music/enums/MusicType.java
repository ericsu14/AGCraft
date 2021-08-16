package com.joojet.plugins.music.enums;

import com.joojet.plugins.music.duration.MusicDuration;

public enum MusicType {
	GORO_THEME ("boss.goro_theme", "2:31", MusicEndingType.GORO_THEME_END),
	KUZE_THEME ("boss.kuze_theme", "3:56", MusicEndingType.KUZE_THEME_END),
	DOOM_GUY ("boss.doom_guy", "1:35", MusicEndingType.DOOM_GUY_END),
	HAIKYUU ("boss.haikyuu", "4:01", MusicEndingType.HAIKYUU_END),
	BARNEY ("boss.barney", "2:30", MusicEndingType.BARNEY_END),
	SHREK ("boss.shrek", "3:20", MusicEndingType.SHREK_END),
	OUTLAW ("boss.outlaw", "3:22", MusicEndingType.OUTLAW_END),
	BLINDED_BY_LIGHT ("boss.blinded_by_light", "3:19", MusicEndingType.BLINDED_BY_LIGHT_END),
	BAKA_MITAI ("fireworks.baka_mitai", "4:48"),
	FALLING_IN_LOVE ("fireworks.falling_in_love", "3:01"),
	FLY_ME_TO_THE_MOON ("fireworks.fly_me_to_the_moon", "2:28"),
	MAPLESTORY ("fireworks.maplestory", "3:03"),
	MOONDANCE ("fireworks.moondance", "4:14"),
	FAREWELL ("fireworks.farewell", "4:27"),
	WORLD_OF_COLORS ("fireworks.world_of_colors", "3:11"),
	YOU_ARE_GOOD ("fireworks.you_are_good", "5:23")
	;
	
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
		return this.name();
	}
}
