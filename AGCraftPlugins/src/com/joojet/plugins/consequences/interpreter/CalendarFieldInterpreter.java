package com.joojet.plugins.consequences.interpreter;

import com.joojet.biblefetcher.trie.TrieNode;
import com.joojet.biblefetcher.trie.TrieUtil;
import com.joojet.plugins.consequences.enums.CalendarField;

public class CalendarFieldInterpreter 
{
	private TrieNode <CalendarField> calendarRoot;
	
	public CalendarFieldInterpreter ()
	{
		this.calendarRoot = new TrieNode <CalendarField> (' ' , null);
		this.populateTrie();
	}
	
	/** Populates the trie with all enums in CalendarFields */
	private void populateTrie ()
	{
		for (CalendarField ele : CalendarField.values())
		{
			TrieUtil.insertWord(ele.toString(), ele, this.calendarRoot);
		}
	}
	
	/** Searches the trie for the search term and returns the CalendarField related to
	 *  that search term. */
	public CalendarField searchTrie (String input)
	{
		return TrieUtil.searchTrie(input, this.calendarRoot);
	}
}
