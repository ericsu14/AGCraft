package com.joojet.plugins.warp.interpreter;

import com.joojet.biblefetcher.trie.TrieNode;
import com.joojet.biblefetcher.trie.TrieUtil;
import com.joojet.plugins.warp.constants.*;

public class AccessLevelInterpreter 
{
	private TrieNode <WarpAccessLevel> accessRoot;
	
	public AccessLevelInterpreter ()
	{
		accessRoot = new TrieNode <WarpAccessLevel> (' ', null);
		this.populateTrie();
	}
	
	private void populateTrie ()
	{
		for (WarpAccessLevel level : WarpAccessLevel.values())
		{
			TrieUtil.insertWord(level.name(), level, accessRoot);
		}
	}
	
	public WarpAccessLevel searchTrie (String input)
	{
		return TrieUtil.searchTrie(input, accessRoot);
	}
}

