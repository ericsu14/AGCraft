package com.joojet.plugins.mobs.metadata;

import org.bukkit.persistence.PersistentDataHolder;

public class ExplodingFallingBlockMetadata extends AbstractMetadata<Integer>
{
	public static String tag = "EXPLODING-FALLING-BLOCK";
	
	public ExplodingFallingBlockMetadata ()
	{
		super (tag, 0);
	}
	
	/** Creates a new exploding falling block with a custom explosion power
	 *  @param power - Custom explosion power of the block's explosion once it collides with the ground */
	public ExplodingFallingBlockMetadata(Integer power) 
	{
		super(tag, power);
	}
	
	/** Gets the explosion power as an Integer from a metadata holder. Returns null
	 *  if not found. */
	public Integer getExplosionPower (PersistentDataHolder holder)
	{
		Integer result = null;
		String val = this.getStringMetadata(holder);
		if (val != null)
		{
			try
			{
				result = Integer.parseInt(val);
			}
			catch (NumberFormatException nfe)
			{
				nfe.printStackTrace();
			}
		}
		return result;
	}
}
