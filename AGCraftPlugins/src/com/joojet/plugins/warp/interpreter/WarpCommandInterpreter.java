package com.joojet.plugins.warp.interpreter;

import com.joojet.biblefetcher.trie.TrieNode;
import com.joojet.biblefetcher.trie.TrieUtil;
import com.joojet.plugins.warp.constants.WarpType;

public class WarpCommandInterpreter 
{
	private TrieNode <WarpType> warpTypeRoot;
	
	public WarpCommandInterpreter ()
	{
		warpTypeRoot = new TrieNode <WarpType> (' ', null);
		this.populateWarpTypeTrie();
	}
	
	private void populateWarpTypeTrie ()
	{
		for (WarpType type : WarpType.values())
		{
			TrieUtil.insertWord(type.name(), type, this.warpTypeRoot);
		}
	}
	
	public WarpType searchWarpTypeTrie (String input)
	{
		return TrieUtil.searchTrie(input, this.warpTypeRoot);
	}
}
