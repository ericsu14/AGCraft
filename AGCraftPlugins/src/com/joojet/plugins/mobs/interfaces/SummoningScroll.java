package com.joojet.plugins.mobs.interfaces;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import org.bukkit.ChatColor;

public abstract class SummoningScroll extends ItemStack 
{
	/** The mob to be summoned */
	protected MobEquipment mob;
	/** The mob's entity type */
	protected EntityType type;
	/** The mob's name */
	protected String name;
	
	public SummoningScroll (MobEquipment mob, EntityType type)
	{
		super (Material.PAPER, 1);
		ItemMeta scrollMeta = this.getItemMeta();
		scrollMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		scrollMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		scrollMeta.setDisplayName(ChatColor.GOLD + "Summon " + mob.getChatColor() + mob.getName());
		ArrayList <String> scrollLore = new ArrayList <String> ();
		scrollLore.add(ChatColor.DARK_PURPLE + "Right click to summon " + mob.getChatColor() + mob.getName());
		scrollLore.add(ChatColor.GOLD + "Ticket No. " + UUID.randomUUID());
		scrollMeta.setLore(scrollLore);
		scrollMeta.setLocalizedName(mob.getName());
		this.setItemMeta(scrollMeta);
		
		this.mob = mob;
		this.type = type;
		this.name = mob.getName();
	}
	
	/** Returns the mob to be summoned */
	public MobEquipment getMob ()
	{
		return this.mob;
	}
	
	/** Return the monster's entity type */
	public EntityType getMobType ()
	{
		return this.type;
	}
	
	/** Return the monster's name */
	public String getName ()
	{
		return this.name;
	}
	
	/** Marks the scroll as "used" */
	public static ItemMeta markUsed (ItemMeta itemMeta)
	{
		// Marks the summoning scroll as used
		itemMeta.removeEnchant(Enchantment.DURABILITY);
		itemMeta.setDisplayName(ChatColor.GRAY + "Withered Summing Scroll");
		ArrayList <String> lore = new ArrayList <String> ();
		lore.add(ChatColor.GRAY + "You cannot use this scroll anymore.");
		lore.add(ChatColor.GRAY + "Best to throw it away or use it as paper.");
		itemMeta.setLore(lore);
		itemMeta.setLocalizedName("");
		
		return itemMeta;
	}
}
