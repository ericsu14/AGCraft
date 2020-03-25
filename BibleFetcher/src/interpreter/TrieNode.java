package interpreter;

public class TrieNode <T> 
{
	// Defines the max. amount of children this node could store
	public final int kChildSize = 255;
	// Amount of entities linked to this node
	private int count;
	// Stores the identification info this entry is linked to
	private T linkedID;
	// Stores the node's children
	private TrieNode<T> children[];
	// Amount of active children this node carries
	private int size;
	// The node's current character
	private char letter;
	
	@SuppressWarnings("unchecked")
	public TrieNode (char letter, T linkedID)
	{
		this.letter = letter;
		this.linkedID = linkedID;
		this.size = 0;
		this.count = 0;
		this.children = (TrieNode<T>[]) new TrieNode [this.kChildSize];
	}
	
	public void addChild (char letter, T linkedID)
	{
		this.children[letter] = new TrieNode <T> (letter, linkedID);
		++this.size;
	}
	
	public TrieNode <T> next (char letter)
	{
		return this.children[letter];
	}
	
	public int size ()
	{
		return this.size;
	}
	
	public T getLinkedID ()
	{
		return this.linkedID;
	}
	
	public char getLetter ()
	{
		return this.letter;
	}
	
	public int getCount ()
	{
		return this.count;
	}
	
	public void incrementCount ()
	{
		++this.count;
	}
	
	
}
