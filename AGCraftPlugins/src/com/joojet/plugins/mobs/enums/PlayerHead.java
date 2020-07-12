package com.joojet.plugins.mobs.enums;

public enum PlayerHead {
	CAMERA_HEAD ("3db83586542934f8c3231a5284f2489b87678478454fca69359447569f157d14"),
	PHARAOH ("9c4de46b35bfb79418e96633f4a1a9bb5547c8ead6bb84d81b2de3b1cd1bec"),
	UNCLE_SAMS("a608dae3323fb49c685a0ddca98851dc95d69f00b977dfa6b2fedb74e74ef1a3"),
	AMERICAN_MONSTER("d2edf9fb20ee77c082df790914d9c89d7ae8ddcfbb5be0d016bea14548a52a17"),
	MAJORAS_MASK("25bb52db233dbe7fc0bf3afb9be46fa25269d60c1da7a40821e674945c"),
	SHREK ("4868dd50bcb73c47c4aaee75c7eb3c7097885d4a9dd34a57c8ca48de3b76598a");
	
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
