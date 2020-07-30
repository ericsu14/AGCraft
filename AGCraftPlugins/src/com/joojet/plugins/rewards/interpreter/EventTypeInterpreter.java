package com.joojet.plugins.rewards.interpreter;

import com.joojet.biblefetcher.trie.TrieNode;
import com.joojet.biblefetcher.trie.TrieUtil;
import com.joojet.plugins.rewards.enums.MinigameType;

public class EventTypeInterpreter 
{
	private TrieNode <MinigameType> eventRoot;
	
	public EventTypeInterpreter ()
	{
		this.eventRoot = new TrieNode <MinigameType> (' ' , null);
		this.populateTrie();
	}
	
	/** Populates the trie with all enums in SummonTypes */
	private void populateTrie ()
	{
		for (MinigameType ele : MinigameType.values())
		{
			TrieUtil.insertWord(ele.toString(), ele, this.eventRoot);
		}
	}
	
	/** Searches the trie for the search term and returns the SummonType related to
	 *  that search term. */
	public MinigameType searchTrie (String input)
	{
		return TrieUtil.searchTrie(input, this.eventRoot);
	}
}
