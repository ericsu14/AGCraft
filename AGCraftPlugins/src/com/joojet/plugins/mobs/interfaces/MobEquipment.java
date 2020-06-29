package com.joojet.plugins.mobs.interfaces;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.potion.PotionEffect;

public abstract class MobEquipment 
{
	protected String name;
	protected ChatColor color;
	protected double health;
	protected ItemStack helmet;
	protected ItemStack chestplate;
	protected ItemStack leggings;
	protected ItemStack boots;
	protected ItemStack weapon;
	protected ItemStack offhand;
	protected boolean onFire;
	protected boolean showName;
	protected ArrayList <PotionEffect> effects;
	
	public MobEquipment ()
	{
		this.name = "";
		this.color = ChatColor.WHITE;
		// -1 represents default health
		this.health = -1.0;
		this.onFire = false;
		this.showName = false;
		this.effects = new ArrayList <PotionEffect> ();
	}
	
	/** Returns all of the mob's equipment in the form of an array */
	public ItemStack [] getEquipment ()
	{
		ItemStack itemList[] = new ItemStack [6];
		
		itemList[0] = this.helmet;
		itemList[1] = this.chestplate;
		itemList[2] = this.leggings;
		itemList[3] = this.boots;
		itemList[4] = this.weapon;
		itemList[5] = this.offhand;
		
		return itemList;
	}
	
	public ItemStack getHelmet ()
	{
		return this.helmet;
	}
	
	public ItemStack getChestplate ()
	{
		return this.chestplate;
	}
	
	public ItemStack getLeggings ()
	{
		return this.leggings;
	}
	
	public ItemStack getBoots ()
	{
		return this.boots;
	}
	
	public ItemStack getWeapon ()
	{
		return this.weapon;
	}
	
	public ItemStack getOffhand ()
	{
		return this.offhand;
	}
	
	public String getName ()
	{
		return this.name;
	}
	
	public ChatColor getChatColor ()
	{
		return this.color;
	}
	
	public double getHealth ()
	{
		return this.health;
	}
	
	public ArrayList <PotionEffect> getEffects ()
	{
		return this.effects;
	}
	
	public boolean onFire ()
	{
		return this.onFire;
	}
	
	public boolean showName ()
	{
		return this.showName;
	}
	
	public void addRandomDamage (ItemStack item)
	{
		Damageable dmg = (Damageable) item.getItemMeta();
		int max = (int) item.getType().getMaxDurability() - 10;
		int min = (int) (max * 0.1);
		Random rand = new Random ();
		
		dmg.setDamage(rand.nextInt(max - min) + min);
	}
}
