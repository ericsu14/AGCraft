package com.joojet.plugins.mobs.interpreter;

import com.joojet.biblefetcher.trie.TrieNode;
import com.joojet.biblefetcher.trie.TrieUtil;
import com.joojet.plugins.mobs.enums.SummonTypes;

public class SummoningScrollInterpreter 
{
	private TrieNode <SummonTypes> summonRoot;
	
	public SummoningScrollInterpreter ()
	{
		this.summonRoot = new TrieNode <SummonTypes> (' ' , null);
		this.populateTrie();
	}
	
	/** Populates the trie with all enums in JunkClassifier */
	private void populateTrie ()
	{
		for (SummonTypes ele : SummonTypes.values())
		{
			TrieUtil.insertWord(ele.toString(), ele, this.summonRoot);
		}
	}
	
	/** Searches the trie for the search term and returns the JunkClassifier related to
	 *  that search term. */
	public SummonTypes searchTrie (String input)
	{
		return TrieUtil.searchTrie(input, this.summonRoot);
	}
}
