package com.joojet.biblefetcher.constants;

public enum BookID 
{
	GEN (50, "GEN", "genesis"),
	EXO (40, "EXO", "exodus"),
	LEV (27, "LEV", "leviticus"),
	NUM (36, "NUM", "numbers"),
	DEU (34, "DEU", "deuteronomy"),
	JOS (24, "JOS", "joshua"),
	JDG (21, "JDG", "judges"),
	RUT (4, "RUT", "ruth"),
	SA1 (31, "1SA", "samuel", 1),
	SA2 (24, "2SA", "samuel", 2),
	KI1 (22, "1KI", "king", 1),
	KI2 (25, "2KI", "king", 2),
	CH1 (29, "1CH", "chronicles", 1),
	CH2 (36, "2CH", "chronicles", 2),
	EZR (10, "EZR", "ezra"),
	NEH (13, "NEH", "nehemia"),
	EST (10, "EST", "esther"),
	JOB (42, "JOB", "job"),
	PSA (150, "PSA", "psalms"),
	PRO (31, "PRO", "proverbs"),
	ECC (12, "ECC", "ecclesiastes"),
	SNG (8, "SNG", "song of solomon"),
	ISA (66, "ISA", "isaish"),
	JER (52, "JER", "jeremiah"),
	LAM (5, "LAM", "lamentations"),
	EZK (48, "EZK", "ezekiel"),
	DAN (12, "DAN", "daniel"),
	HAB (3, "HAB", "habakkuk"),
	ZEP (3, "ZEP", "zephaniah"),
	MAL (4, "MAL", "malachi"),
	HOS (14, "HOS", "hosea"),
	JOL (3, "JOL", "joel"),
	AMO (9, "AMO", "amos"),
	OBA (1, "OBA", "obadiah", 0),
	JON (4, "JON", "jonah"),
	MIC (7, "MIC", "micah"),
	NAM (3, "NAM", "nahum"),
	HAG (2, "HAG", "haggai"),
	ZEC (14, "ZEC", "zechariah"),
	MAT (28, "MAT", "matthew"),
	MRK (16, "MRK", "mark"),
	LUK (24, "LUK", "luke"),
	JHN (21, "JHN", "john"),
	ACT (28, "ACT", "act"),
	ROM (16, "ROM", "romans"),
	CO1 (16, "1CO", "corinthians", 1),
	CO2 (13, "2CO", "corinthians", 2),
	GAL (6, "GAL", "galatians"),
	EPH (6, "EPH", "ephesians"),
	PHP (4, "PHP", "philippians"),
	COL (4, "COL", "colossians"),
	TH1 (5, "1TH", "thessalonians", 1),
	TH2 (3, "2TH", "thessalonians", 2),
	TI1 (6, "1TI", "timothy", 1),
	TI2 (4, "2TI", "timothy", 2),
	TIT (3, "TIT", "titus"),
	PHM (1, "PHM", "philemon", 0),
	HEB (13, "HEB", "hebrews"),
	JAS (5, "JAS", "james"),
	PE1 (5, "1PE", "peter", 1),
	PE2 (3, "2PE", "peter", 2),
	JN1 (5, "1JN", "john", 1),
	JN2 (1, "2JN", "john", 2),
	JN3 (1, "3JN", "john", 3),
	JUD (1, "JUD", "jude", 0),
	REV (22, "REV", "revelation")
 	;
	
	private String kBookID;
	private String kBookTitle;
	private int kNumChapters;
	private int kChapter;
	
	private BookID (int numChapters, String bookID, String bookTitle)
	{
		this.kNumChapters = numChapters;
		this.kBookID = bookID;
		this.kBookTitle = bookTitle;
		this.kChapter = 0;
	}
	
	private BookID (int numChapters, String bookID, String bookTitle, int chapter)
	{
		this.kNumChapters = numChapters;
		this.kBookID = bookID;
		this.kBookTitle = bookTitle;
		this.kChapter = chapter;
	}
	
	private BookID (int numChapters, String bookID, String bookTitle, int chapter, boolean singleChapter)
	{
		this.kNumChapters = numChapters;
		this.kBookID = bookID;
		this.kBookTitle = bookTitle;
		this.kChapter = chapter;
	}
	
	/** Returns the title of the book without any added chapter information. */
	public String getTitle ()
	{
		return this.kBookTitle;
	}
	
	/** Returns a formatted version of the book title with proper capitalization and chapter metadata. */
	public String getFormattedTitle ()
	{
		StringBuilder result = new StringBuilder ();
		if (this.kChapter != 0)
		{
			result.append(this.kChapter).append(" ");
		}
		
		result.append(Character.toUpperCase(this.kBookTitle.charAt(0)));
		result.append(this.kBookTitle.substring(1));
		
		return result.toString();
	}
	
	/** Returns the book title without any spaces */
	public String getFormattedTitleWithoutSpace ()
	{
		return this.getFormattedTitle().replaceAll("([\\s])", "");
	}
	
	/** Returns the BookID used to communicate with the WEB API */
	public String getID ()
	{
		return this.kBookID;
	}
	
	/** Returns the book's chapter number if it comes in parts, such as John1 or John2. */
	public int getChapter ()
	{
		return this.kChapter;
	}
	
	/** Returns true if this book only contains one chapter */
	public boolean isSingleChapter ()
	{
		return this.kNumChapters == 1;
	}
	
	/** Returns the total number of chapters in this book */
	public int getNumChapters ()
	{
		return this.kNumChapters;
	}
	
	/** Returns the book's formatted title as a string */
	@Override
	public String toString ()
	{
		return this.getFormattedTitle();
	}
}

