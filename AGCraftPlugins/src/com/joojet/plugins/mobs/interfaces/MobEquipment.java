package com.joojet.plugins.mobs.interfaces;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Base64;
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
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.PlayerHead;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public abstract class MobEquipment 
{
	/** Name of the entity */
	protected String name;
	/** Color used to recolor the entity's display names */
	protected ChatColor color;
	/** Entity's health. A value of -1 uses the entity's default health */
	protected double health;
	/** The entity's helmet */
	protected ItemStack helmet;
	/** The entity's chestplate */
	protected ItemStack chestplate;
	/** The entity's leggings */
	protected ItemStack leggings;
	/** The entity's boots */
	protected ItemStack boots;
	/** The entity's weapon */
	protected ItemStack weapon;
	/** The entity's offhand item */
	protected ItemStack offhand;
	/** If set to true, the entity will spawn with a permanent burning effect */
	protected boolean onFire;
	/** If set to true, the entity will have its nametag visible to everyone */
	protected boolean showName;
	/** A list of potion effects applied on the entity upon spawning */
	protected ArrayList <PotionEffect> effects;
	/** URL base for custom player head skins */
	protected final String urlBase = "http://textures.minecraft.net/texture/";
	/** An array storing the drop chances for each item the entity has. */
	protected float dropRates[];
	
	public MobEquipment ()
	{
		this.name = "";
		this.color = ChatColor.WHITE;
		// -1 represents default health
		this.health = -1.0;
		this.onFire = false;
		this.showName = false;
		this.effects = new ArrayList <PotionEffect> ();
		// Set up default drop rates
		this.dropRates = new float[6];
		this.setDropRates(0.03f, 0.03f, 0.03f, 0.03f, 0.01f, 0.05f);
	}
	
	/** Sets up drop rates for this entity.
	 *  Rates are stored as doubles from ranges 0.0f-1.0f, where
	 *  1.0f indicates a 100% chance of dropping
	 * 		@param helmet - Drop rate for helmet
	 * 		@param chestplate - Drop rate for chestplate
	 * 		@param leggings - Drop rate for leggings
	 * 		@param boots - Drop rate for boots
	 * 		@param weapon - Drop rate for weapons
	 * 		@param offhand - Drop rate for offhand */
	public void setDropRates (float helmet, float chestplate, float leggings, float boots, float weapon, float offhand)
	{
		this.dropRates[0] = helmet;
		this.dropRates[1] = chestplate;
		this.dropRates[2] = leggings;
		this.dropRates[3] = boots;
		this.dropRates[4] = weapon;
		this.dropRates[5] = offhand;
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
	
	/** Return the drop rates for each item in the mob's inventory */
	public float[] getDropRates ()
	{
		return this.dropRates;
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
	
	
	/** Creates a custom playerhead using a custom texture.
	 *  Code stolen from:
	 *  	https://www.spigotmc.org/threads/custom-textured-non-player-skulls.244561/#post-2448313
	 *  @param meta - the item we are adding head data to.
	 *  @param paramString - URL of the custom player head */
	public ItemMeta createHeadData (ItemStack item, PlayerHead head)
	{
		SkullMeta localSkullMeta = (SkullMeta)item.getItemMeta();

		GameProfile localGameProfile = new GameProfile(UUID.randomUUID(), null);
		StringBuilder fullURL = new StringBuilder (this.urlBase);
		fullURL.append(head.getURL());
		byte[] arrayOfByte = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", new Object[] { fullURL.toString() }).getBytes());
		localGameProfile.getProperties().put("textures", new Property("textures", new String(arrayOfByte)));
		Field localField = null;
		try
		{
			localField = localSkullMeta.getClass().getDeclaredField("profile");
			localField.setAccessible(true);
			localField.set(localSkullMeta, localGameProfile);
		}
		catch (NoSuchFieldException|IllegalArgumentException|IllegalAccessException localNoSuchFieldException)
		{
			System.out.println("error: " + localNoSuchFieldException.getMessage());
		}

		return (ItemMeta) localSkullMeta;
	}
	
	/** Adds a custom potion effect to the monster */
	public void addPotionEffect (CustomPotionEffect effect)
	{
		this.effects.add(effect.getPotionEffect());
	}
	
	/** Americanizes a name by applying the USA colors to every character in a string
	 *  in an alternating pattern */
	public String americanizeText (String str)
	{
		StringBuilder result = new StringBuilder ();
		
		int pattern = 0;
		for (char c : str.toCharArray())
		{
			switch (pattern)
			{
				case 0:
					result.append(ChatColor.RED);
					break;
				case 1:
					result.append(ChatColor.WHITE);
					break;
				default:
					result.append(ChatColor.BLUE);
					break;
			}
			result.append(c);
			++pattern;
			
			if (pattern > 2)
			{
				pattern = 0;
			}
				
		}
		return result.toString();
	}
}
