package com.joojet.plugins.mobs.enums;

public enum PlayerHead {
	CAMERA_HEAD ("3db83586542934f8c3231a5284f2489b87678478454fca69359447569f157d14"),
	PHARAOH ("9c4de46b35bfb79418e96633f4a1a9bb5547c8ead6bb84d81b2de3b1cd1bec");
	
	// URL code of playerhead stored in minecraft's skin server
	private String url;
	
	private PlayerHead (String url)
	{
		this.url = url;
	}
	
	public String getURL ()
	{
		return this.url;
	}
}
