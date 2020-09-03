package com.joojet.plugins.mobs.equipment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.metadata.EquipmentTypeMetadata;
import com.joojet.plugins.mobs.metadata.SoulBoundMetadata;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public abstract class Equipment extends ItemStack 
{
	/** Equipment slot the item belongs to */
	protected EquipmentSlot equipmentSlot;
	/** Chatcolor used to color the equipment's text fields */
	protected ChatColor chatColor;
	/** URL base for custom player head skins */
	protected final String urlBase = "http://textures.minecraft.net/texture/";
	/** Number of words that can fit in a single line for an item's lore */
	protected int wordsPerLine;
	/** Playerhead enum used to fetch the skin of the playerhead */
	protected PlayerHead playerHead;
	/** Chat color applied to the equipment's lore. Defalut color is set to chatColor */
	protected ChatColor loreColor;
	/** Identifies the type of equipment this is */
	protected EquipmentTypes equipmentType;
	
	/** Constructs a basic item with a count of 1 */
	public Equipment (EquipmentTypes equipmentType, Material material, EquipmentSlot equipmentSlot, ChatColor chatColor)
	{
		super (material, 1);
		this.equipmentType = equipmentType;
		this.equipmentSlot = equipmentSlot;
		this.chatColor = chatColor;
		this.playerHead = null;
		this.wordsPerLine = 4;
		this.loreColor = chatColor;
		this.setEquipmentMetadata ();
	}
	
	/** Constructs a item with a specified item count */
	public Equipment (EquipmentTypes equipmentType, Material material, EquipmentSlot equipmentSlot, ChatColor chatColor, int count)
	{		
		super (material, (count > 64) ? 64 : count);
		this.equipmentType = equipmentType;
		this.equipmentSlot = equipmentSlot;
		this.chatColor = chatColor;
		this.playerHead = null;
		this.wordsPerLine = 4;
		this.loreColor = chatColor;
		this.setEquipmentMetadata ();
	}
	
	/** Constructs a basic playerhead item */
	public Equipment (EquipmentTypes equipmentType, PlayerHead head, ChatColor chatColor)
	{
		super (Material.PLAYER_HEAD, 1);
		this.equipmentType = equipmentType;
		this.playerHead = head;
		this.equipmentSlot = EquipmentSlot.HEAD;
		this.chatColor = chatColor;
		this.createHeadData(this.playerHead);
		this.wordsPerLine = 4;
		this.loreColor = chatColor;
		this.setEquipmentMetadata ();
	}
	
	/** Adds an attack speed attribute to a piece of armor or weapon */
	protected void addAttackAttributes (double attackDamage, double attackSpeed)
	{
		ItemMeta meta = this.getItemMeta();
		if (attackDamage > 0.0)
		{
			AttributeModifier attackMod = new AttributeModifier (UUID.randomUUID(), "generic.attack_damage", attackDamage, Operation.ADD_NUMBER, this.equipmentSlot);
			meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, attackMod);
		}
		
		if (attackSpeed > 0.0)
		{
			AttributeModifier attackSpeedMod = new AttributeModifier (UUID.randomUUID(), "generic.attack_speed", -attackSpeed, Operation.ADD_NUMBER, this.equipmentSlot);
			meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attackSpeedMod);
		}
		this.setItemMeta(meta);
	}
	
	/** Adds a speed attribute to a piece of armor or weapon */
	protected void addSpeedAttribute (double speed)
	{
		ItemMeta meta = this.getItemMeta();
		if (speed > 0.0)
		{
			AttributeModifier speedMod = new AttributeModifier (UUID.randomUUID(), "generic.movement_speed", speed, Operation.MULTIPLY_SCALAR_1, this.equipmentSlot);
			meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, speedMod);
		}
		this.setItemMeta(meta);
	}
	
	/** Adds defense attributes to a piece of armor or weapon
	 * 	@param meta - The item's itemmeta
	 * 	@param slot - The equipmentslot we are applying these mods into
	 * 	@param armor - Armor points the equipment carries
	 * 	@param armorToughness - Armor toughness points the equipment carries
	 * 	@param knockbackResistance - Knockback resistance points the equipment carries */
	protected void addDefenseAttributes (double armor, double armorToughness, double knockbackResistance)
	{
		ItemMeta meta = this.getItemMeta();
		if (armor > 0.0)
		{
			AttributeModifier armorMod = new AttributeModifier (UUID.randomUUID(), "generic.armor", armor, Operation.ADD_NUMBER, this.equipmentSlot);
			meta.addAttributeModifier(Attribute.GENERIC_ARMOR, armorMod);
		}
		if (armorToughness > 0.0)
		{
			AttributeModifier armorToughnessMod = new AttributeModifier (UUID.randomUUID(), "generic.armor_toughness", armorToughness, Operation.ADD_NUMBER, this.equipmentSlot);
			meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, armorToughnessMod);
		}
		if (knockbackResistance > 0.0)
		{
			AttributeModifier knockbackResistanceMod = new AttributeModifier (UUID.randomUUID(), "generic.knockback_resistance", knockbackResistance, Operation.MULTIPLY_SCALAR_1, this.equipmentSlot);
			meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, knockbackResistanceMod);
		}
		this.setItemMeta(meta);
	}
	
	/** Adds max health attributes to a piece of armor or weapon
	 * 		@param health - The amount of max health bonuses this equipment carries */
	protected void addHealthAttributes (double health)
	{
		ItemMeta meta = this.getItemMeta();
		if (health > 0.0)
		{
			AttributeModifier healthMod = new AttributeModifier (UUID.randomUUID(), "generic.max_health", health, Operation.ADD_NUMBER, this.equipmentSlot);
			meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, healthMod);
		}
		this.setItemMeta(meta);
	}
	
	/** Creates a custom playerhead using a custom texture.
	 *  Code stolen from:
	 *  	https://www.spigotmc.org/threads/custom-textured-non-player-skulls.244561/#post-2448313
	 *  @param meta - the item we are adding head data to.
	 *  @param head - Type of skin the player head is using */
	protected void createHeadData (PlayerHead head)
	{
		SkullMeta localSkullMeta = (SkullMeta) this.getItemMeta();

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

		this.setItemMeta((ItemMeta) localSkullMeta);
	}
	
	/** Americanizes a name by applying the USA colors to every character in a string
	 *  in an alternating pattern */
	protected String americanizeText (String str)
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
	
	/** Adds a new lore string into the equipment. The String will be split into multiple tokens depending on how many
	 *  words can fit in a single line.
	 * 		@param meta - ItemMeta we are adding the lore info into */
	protected void addLoreToItemMeta (String lore)
	{
		List <String> itemLore = this.getItemMeta().getLore();
		if (itemLore == null)
		{
			itemLore = new ArrayList <String> ();
		}
		
		StringBuilder str = new StringBuilder();
		str.append(this.loreColor);
		ItemMeta meta = this.getItemMeta();
		
		String[] tokens = lore.split(" ");
		
		int count = 0;
		for (String token : tokens)
		{
			str.append(token);
			str.append(" ");
			++count;
			
			if (count >= this.wordsPerLine)
			{
				itemLore.add(str.toString().trim());
				str = new StringBuilder();
				count = 0;
				str.append(this.loreColor);
			}
		}
		
		if (!str.toString().substring(2).isEmpty())
		{
			itemLore.add(str.toString().trim());
		}
		meta.setLore(itemLore);
		this.setItemMeta(meta);
	}
	
	/** Sets the display name for this equipment */
	protected void setDisplayName (String name)
	{
		ItemMeta meta = this.getItemMeta();
		meta.setDisplayName(this.chatColor + name);
		this.setItemMeta(meta);
	}
	
	/** Modifies the localized name field of this item */
	protected void setLocalizedName (String text)
	{
		ItemMeta meta = this.getItemMeta();
		meta.setLocalizedName(text);
		this.setItemMeta(meta);
	}
	
	/** Adds a list of item flags to this equipment */
	protected void addItemFlags (ItemFlag... flags)
	{
		ItemMeta meta = this.getItemMeta();
		for (ItemFlag flag : flags)
		{
			meta.addItemFlags(flag);
		}
		this.setItemMeta(meta);
	}
	
	/** Makes this equipment carry the unbreakable attribute */
	protected void makeUnbreakable ()
	{
		ItemMeta meta = this.getItemMeta();
		meta.setUnbreakable(true);
		this.setItemMeta(meta);
	}
	
	/** Allows this item to have the soulbound metadata tag, allowing this item to still remain in 
	 *  the player's inventory upon death. */
	protected void makeSoulbound ()
	{
		ItemMeta meta = this.getItemMeta();
		new SoulBoundMetadata().addStringMetadata(meta);
		this.setItemMeta(meta);
		this.addLoreToItemMeta(ChatColor.GOLD + "Soulbound");
	}
	
	/** Adds this equipment's identifier into the search tree
	 *  and adds it into its own persistent data container */
	protected void setEquipmentMetadata ()
	{
		AGCraftPlugin.equipmentInterpreter.insertWord(this.equipmentType.toString(), this);
		ItemMeta itemMeta = this.getItemMeta();
		new EquipmentTypeMetadata (this.equipmentType).addStringMetadata(itemMeta);
		this.setItemMeta(itemMeta);
	}
	
	/** Returns this equipment's equipment type identifier */
	public EquipmentTypes getEquipmentType ()
	{
		return this.equipmentType;
	}
}
