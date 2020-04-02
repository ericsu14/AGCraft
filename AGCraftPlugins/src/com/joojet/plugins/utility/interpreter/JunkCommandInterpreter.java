package com.joojet.plugins.utility.interpreter;

import com.joojet.biblefetcher.trie.TrieNode;
import com.joojet.biblefetcher.trie.TrieUtil;
import com.joojet.plugins.utility.enums.JunkClassifier;

public class JunkCommandInterpreter 
{
	private TrieNode <JunkClassifier> junkRoot;
	
	public JunkCommandInterpreter ()
	{
		this.junkRoot = new TrieNode <JunkClassifier> (' ' , null);
		this.populateJunkClassifierTrie();
	}
	
	/** Populates the trie with all enums in JunkClassifier */
	private void populateJunkClassifierTrie ()
	{
		for (JunkClassifier ele : JunkClassifier.values())
		{
			TrieUtil.insertWord(ele.toString(), ele, this.junkRoot);
		}
	}
	
	/** Searches the trie for the search term and returns the JunkClassifier related to
	 *  that search term. */
	public JunkClassifier searchJunkClassifierTrie (String input)
	{
		return TrieUtil.searchTrie(input, this.junkRoot);
	}
	
}
