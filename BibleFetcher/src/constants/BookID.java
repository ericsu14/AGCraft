package constants;

public enum BookID 
{
	GEN ("GEN", "genesis"),
	EXO ("EXO", "exodus"),
	LEV ("LEV", "leviticus"),
	NUM ("NUM", "numbers"),
	DEU ("DEU", "deuteronomy"),
	JOS ("JOS", "joshua"),
	JDG ("JDG", "judges"),
	RUT ("RUT", "ruth"),
	SA1 ("1SA", "samuel", 1),
	SA2 ("2SA", "samuel", 2),
	KI1 ("1KI", "king", 1),
	KI2 ("2KI", "king", 2),
	CH1 ("1CH", "chronicles", 1),
	CH2 ("2CH", "chronicles", 2),
	EZR ("EZR", "ezra"),
	NEH ("NEH", "nehemia"),
	EST ("EST", "esther"),
	JOB ("JOB", "job"),
	PSA ("PSA", "psalms"),
	PRO ("PRO", "proverbs"),
	ECC ("ECC", "ecclesiastes"),
	SNG ("SNG", "song of solomon"),
	ISA ("ISA", "isaish"),
	JER ("JER", "jeremiah"),
	LAM ("LAM", "lamentations"),
	EZK ("EZK", "ezekiel"),
	DAN ("DAN", "daniel"),
	HAB ("HAB", "habakkuk"),
	ZEP ("ZEP", "zephaniah"),
	MAL ("MAL", "malachi"),
	HOS ("HOS", "hosea"),
	JOL ("JOL", "joel"),
	AMO ("AMO", "amos"),
	OBA ("OBA", "obadiah"),
	JON ("JON", "jonah"),
	MIC ("MIC", "micah"),
	NAM ("NAM", "nahum"),
	HAG ("HAG", "haggai"),
	ZEC ("ZEC", "zechariah"),
	MAT ("MAT", "matthew"),
	MRK ("MRK", "mark"),
	LUK ("LUK", "luke"),
	JHN ("JHN", "john"),
	ACT ("ACT", "act"),
	ROM ("ROM", "romans"),
	CO1 ("1CO", "corinthians", 1),
	CO2 ("2CO", "corinthians", 2),
	GAL ("GAL", "galatians"),
	EPH ("EPH", "ephesians"),
	PHP ("PHP", "philippians"),
	COL ("COL", "colossians"),
	TH1 ("1TH", "thessalonians", 1),
	TH2 ("2TH", "thessalonians", 2),
	TI1 ("1TH", "timothy", 1),
	TI2 ("1TH", "timothy", 2),
	TIT ("TIT", "titus"),
	PHN ("PHN", "philemon"),
	HEB ("HEB", "hebrews"),
	JAS ("JAS", "james"),
	PE1 ("1PE", "peter", 1),
	PE2 ("2PE", "peter", 2),
	JN1 ("1JN", "john", 1),
	JN2 ("2JN", "john", 2),
	JN3 ("3JN", "john", 3),
	JUD ("JUD", "jude"),
	REV ("REV", "revelation")
 	;
	
	private String kBookID;
	private String kBookTitle;
	private int kChapter;
	
	private BookID (String bookID, String bookTitle)
	{
		this.kBookID = bookID;
		this.kBookTitle = bookTitle;
		this.kChapter = 0;
	}
	
	private BookID (String bookID, String bookTitle, int chapter)
	{
		this.kBookID = bookID;
		this.kBookTitle = bookTitle;
		this.kChapter = chapter;
	}
	
	public String getTitle ()
	{
		return this.kBookTitle;
	}
	
	/** Returns the name with the first character capitalized */
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
	
	public String getID ()
	{
		return this.kBookID;
	}
	
	public int getChapter ()
	{
		return this.kChapter;
	}
}

