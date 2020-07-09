package com.joojet.plugins.rewards.interpreter;

import com.joojet.biblefetcher.trie.TrieNode;
import com.joojet.biblefetcher.trie.TrieUtil;
import com.joojet.plugins.rewards.enums.RewardType;

public class RewardTypeInterpreter 
{
	private TrieNode <RewardType> rewardRoot;
	
	public RewardTypeInterpreter ()
	{
		this.rewardRoot = new TrieNode <RewardType> (' ' , null);
		this.populateTrie();
	}
	
	/** Populates the trie with all enums in SummonTypes */
	private void populateTrie ()
	{
		for (RewardType ele : RewardType.values())
		{
			TrieUtil.insertWord(ele.toString(), ele, this.rewardRoot);
		}
	}
	
	/** Searches the trie for the search term and returns the SummonType related to
	 *  that search term. */
	public RewardType searchTrie (String input)
	{
		return TrieUtil.searchTrie(input, this.rewardRoot);
	}
}
