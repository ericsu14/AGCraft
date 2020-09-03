package com.joojet.plugins.mobs.scrolls;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;
import com.joojet.plugins.mobs.monsters.MobEquipment;

import org.bukkit.ChatColor;

public abstract class SummoningScroll extends Equipment 
{
	/** The mob to be summoned */
	protected MobEquipment mob;
	/** The mob's entity type */
	protected EntityType type;
	/** The mob's name */
	protected String name;
	/** String that marks a scroll as used */
	protected final static String witheredString = "withered";
	
	public SummoningScroll (MobEquipment mob, EntityType type)
	{
		super (EquipmentTypes.SUMMONING_SCROLL, Material.PAPER, EquipmentSlot.HAND, ChatColor.GOLD);
		ItemMeta scrollMeta = this.getItemMeta();
		scrollMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		scrollMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		scrollMeta.setDisplayName(ChatColor.GOLD + "Summon " + mob.getChatColor() + mob.getName());
		ArrayList <String> scrollLore = new ArrayList <String> ();
		scrollLore.add(ChatColor.DARK_PURPLE + "Right click to summon " + mob.getChatColor() + mob.getName());
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
		itemMeta.setDisplayName(ChatColor.GRAY + "Withered Summoning Scroll");
		ArrayList <String> lore = new ArrayList <String> ();
		lore.add(ChatColor.GRAY + "You cannot use this scroll anymore.");
		lore.add(ChatColor.GRAY + "Best to throw it away or use it as paper.");
		lore.add(ChatColor.GRAY + "Use /clearjunk to remove from inventory.");
		itemMeta.setLore(lore);
		itemMeta.setLocalizedName(witheredString);
		
		return itemMeta;
	}
	
	/** Returns true if the item is a scroll */
	public static boolean isSummoningScroll (ItemStack item)
	{
		ItemMeta itemMeta = item.getItemMeta();
		return (item.getType().equals(Material.PAPER)
			&& itemMeta.hasLocalizedName()
			&& itemMeta.hasEnchants());
	}
	
	/** Returns true if the item is a withered scroll */
	public static boolean isWitheredScroll (ItemStack item)
	{
		ItemMeta itemMeta = item.getItemMeta();
		return (item.getType().equals(Material.PAPER)
				&& itemMeta.hasLocalizedName()
				&& itemMeta.getLocalizedName().equals(witheredString)
				&& !itemMeta.hasEnchants());
	}
}
