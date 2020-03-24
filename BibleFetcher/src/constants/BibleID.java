package constants;

public enum BibleID 
{
	DRA ("DRA", "179568874c45066f-01", "Douay-Rheims American 1899"),
	GNV ("GNV", "c315fa9f71d4af3a-01", "Geneva Bible"),
	KJV ("KVJ", "de4e12af7f28f599-02", "King James Version (Protestant)"),
	RV ("RV", "40072c4a5aba4022-01", "Revised Version 1885"),
	ASV ("ASV", "06125adad2d5898a-01", "The Holy Bible, American Standard Version"),
	T4T ("T4T", "0bc8836afa7427fa-01", "Translation for Translators"),
	WEB ("WEB", "9879dbb7cfe39e4d-04", "World English Bible (Protestant)");
	
	private String kBibleID;
	private String kBibleKey;
	private String kTranslationName;
	
	private BibleID (String bibleID, String bibleKey, String translationName)
	{
		this.kBibleID = bibleID;
		this.kBibleKey = bibleKey;
		this.kTranslationName = translationName;
	}
	
	public String getBibleID ()
	{
		return this.kBibleID;
	}
	
	/** Returns the key used to communicate with the API */
	public String getBibleKey ()
	{
		return this.kBibleKey;
	}
	
	public String getTranslationName ()
	{
		return this.kTranslationName;
	}
}
