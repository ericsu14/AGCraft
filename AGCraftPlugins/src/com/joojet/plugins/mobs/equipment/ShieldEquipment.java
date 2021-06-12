package com.joojet.plugins.mobs.equipment;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.block.BlockState;
import org.bukkit.block.banner.Pattern;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.enums.EquipmentType;

public abstract class ShieldEquipment extends Equipment 
{
	/** Creates a new instance of a custom Shield Equipment
	 *  @param equipmentType - Type of equipment this is
	 *  @param baseColor - DyeColor applied on this shield as its base color
	 *  @param equipmentSlot - Equipment Slot in which this shield's attributes are applied to
	 *  @param chatColor - Chat color applied to this Equipment's name and lore */
	public ShieldEquipment(EquipmentType equipmentType, DyeColor baseColor, EquipmentSlot equipmentSlot, ChatColor chatColor) 
	{
		super(equipmentType, Material.SHIELD, equipmentSlot, chatColor);
		this.setBaseColor(baseColor);
	}
	
	/** Sets base color for this custom shield
	 * @param color - Base color applied onto this shield */
	protected void setBaseColor (DyeColor color)
	{
		new BukkitRunnable () 
		{
			@Override
			public void run() 
			{
				BlockStateMeta itemMeta = (BlockStateMeta) getItemMeta();
				if (itemMeta.hasBlockState())
				{
					BlockState state = itemMeta.getBlockState();
					Banner bannerState = (Banner) state;
					bannerState.setBaseColor (color);
					bannerState.update();
					itemMeta.setBlockState(bannerState);
					itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
					setItemMeta(itemMeta);
				}
				else
				{
					AGCraftPlugin.plugin.getLogger().warning("Unable to set base color for " + this.toString());
				}
				
			}
			
		}.runTaskLater(AGCraftPlugin.plugin, 20);
	}
	
	/** Adds a list of custom banner patterns to the shield */
	protected void addPatterns (Pattern... patterns)
	{
		new BukkitRunnable () 
		{
			@Override
			public void run() 
			{
				BlockStateMeta itemMeta = (BlockStateMeta) getItemMeta();
				if (itemMeta.hasBlockState())
				{
					BlockState state = itemMeta.getBlockState();
					Banner bannerState = (Banner) state;
					List <Pattern> customPatterns = new ArrayList <Pattern> ();
					for (Pattern pattern : patterns)
					{
						customPatterns.add(pattern);
					}
					bannerState.setPatterns(customPatterns);
					bannerState.update();
					itemMeta.setBlockState(bannerState);
					setItemMeta(itemMeta);
				}
				else
				{
					AGCraftPlugin.plugin.getLogger().warning("Unable to add pattern for " + this.toString());
				}
				
			}
			
		}.runTaskLater(AGCraftPlugin.plugin, 20);
	}
	
	/* private void attachBannerState (BlockStateMeta bannerMeta)
	{
		if (!bannerMeta.hasBlockState())
		{
			ItemStack banner = new ItemStack (Material.WHITE_BANNER, 1);
			BlockState bannerState = ((BlockStateMeta) banner.getItemMeta()).getBlockState();
			bannerMeta.setBlockState(bannerState);
		}
	} */
}
