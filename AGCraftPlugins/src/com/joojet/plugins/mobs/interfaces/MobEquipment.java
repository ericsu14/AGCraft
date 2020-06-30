package com.joojet.plugins.mobs.interfaces;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
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
	
	/** Adds an attack speed attribute to a piece of armor or weapon */
	public void addAttackAttributes (ItemMeta meta, EquipmentSlot slot, double attackDamage, double attackSpeed)
	{
		if (attackDamage > 0.0)
		{
			AttributeModifier attackMod = new AttributeModifier (UUID.randomUUID(), "generic.attack_damage", attackDamage, Operation.ADD_NUMBER, slot);
			meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, attackMod);
		}
		
		if (attackSpeed > 0.0)
		{
			AttributeModifier attackSpeedMod = new AttributeModifier (UUID.randomUUID(), "generic.attack_speed", -attackSpeed, Operation.ADD_NUMBER, slot);
			meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attackSpeedMod);
		}
	}
	
	/** Adds a speed attribute to a piece of armor or weapon */
	public void addSpeedAttribute (ItemMeta meta, EquipmentSlot slot, double speed)
	{
		if (speed > 0.0)
		{
			AttributeModifier speedMod = new AttributeModifier (UUID.randomUUID(), "generic.movement_speed", speed, Operation.MULTIPLY_SCALAR_1, slot);
			meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, speedMod);
		}
	}
	
	/** Adds defense attributes to a piece of armor or weapon
	 * 	@param meta - The item's itemmeta
	 * 	@param slot - The equipmentslot we are applying these mods into
	 * 	@param armor - Armor points the equipment carries
	 * 	@param armorToughness - Armor toughness points the equipment carries
	 * 	@param knockbackResistance - Knockback resistance points the equipment carries */
	public void addDefenseAttributes (ItemMeta meta, EquipmentSlot slot, double armor, double armorToughness, double knockbackResistance)
	{
		if (armor > 0.0)
		{
			AttributeModifier armorMod = new AttributeModifier (UUID.randomUUID(), "generic.armor", armor, Operation.ADD_NUMBER, slot);
			meta.addAttributeModifier(Attribute.GENERIC_ARMOR, armorMod);
		}
		if (armorToughness > 0.0)
		{
			AttributeModifier armorToughnessMod = new AttributeModifier (UUID.randomUUID(), "generic.armor_toughness", armorToughness, Operation.ADD_NUMBER, slot);
			meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, armorToughnessMod);
		}
		if (knockbackResistance > 0.0)
		{
			AttributeModifier knockbackResistanceMod = new AttributeModifier (UUID.randomUUID(), "generic.knockback_resistance", knockbackResistance, Operation.MULTIPLY_SCALAR_1, slot);
			meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, knockbackResistanceMod);
		}
	}
}
