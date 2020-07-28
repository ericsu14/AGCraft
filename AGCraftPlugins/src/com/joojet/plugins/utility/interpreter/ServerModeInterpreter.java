package com.joojet.plugins.utility.interpreter;

import com.joojet.biblefetcher.trie.TrieNode;
import com.joojet.biblefetcher.trie.TrieUtil;
import com.joojet.plugins.agcraft.enums.ServerMode;

public class ServerModeInterpreter 
{
	private TrieNode <ServerMode> serverModeRoot;
	
	public ServerModeInterpreter ()
	{
		this.serverModeRoot = new TrieNode <ServerMode> (' ' , null);
		this.populateTrie();
	}
	
	/** Populates the trie with all enums in ServerMode */
	private void populateTrie ()
	{
		for (ServerMode ele : ServerMode.values())
		{
			TrieUtil.insertWord(ele.toString(), ele, this.serverModeRoot);
		}
	}
	
	/** Searches the trie for the search term and returns the ServerMode related to
	 *  that search term. */
	public ServerMode searchTrie (String input)
	{
		return TrieUtil.searchTrie(input, this.serverModeRoot);
	}
}
