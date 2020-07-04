package com.joojet.plugins.mobs.monsters.skeleton;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.fireworks.FireworkTypes;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class PatrioticSkeleton extends MobEquipment
{
	private FireworkTypes fwTypes;
	public PatrioticSkeleton ()
	{
		this.fwTypes = new FireworkTypes ();
		this.name = this.americanizeText("Patriotic Skeleton");
		this.color = ChatColor.WHITE;
		this.health = 6.0;
		
		this.addPotionEffect(CustomPotionEffect.STRENGTH);
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		this.setDropRates(0.05f, 0.03f, 0.03f, 0.03f, 0.05f, 0.75f);
		
		this.helmet = new ItemStack (Material.PLAYER_HEAD);
		ItemMeta helmetMeta = this.createHeadData(this.helmet, PlayerHead.UNCLE_SAMS);
		this.addDefenseAttributes(helmetMeta, EquipmentSlot.HEAD, 3.0, 1.5, 0.05);
		helmetMeta.setDisplayName(this.americanizeText(ChatColor.WHITE + "Patriotic Hat"));
		ArrayList <String> helmetLore = new ArrayList <String> ();
		helmetLore.add(ChatColor.RED + "Wear to be blinded");
		helmetLore.add(ChatColor.WHITE + "with patriotism,");
		helmetLore.add(ChatColor.BLUE + "... but its okay because");
		helmetLore.add(ChatColor.RED + "this is America!");
		helmetMeta.setLore(helmetLore);
		helmetMeta.setDisplayName(this.americanizeText("USA"));
		this.helmet.setItemMeta(helmetMeta);
		
		// Red jacket
		this.chestplate = new ItemStack (Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chestMeta = (LeatherArmorMeta) this.chestplate.getItemMeta();
		chestMeta.setColor(Color.RED);
		chestMeta.setDisplayName(ChatColor.RED + "Patriotic Red Jacket");
		ArrayList <String> chestLore = new ArrayList <String> ();
		chestLore.add(ChatColor.YELLOW + "jooj loves to wear this!");
		chestMeta.setLore(chestLore);
		this.addDefenseAttributes(chestMeta, EquipmentSlot.CHEST, 8.0, 3.0, 0.15);
		this.chestplate.setItemMeta(chestMeta);
		
		this.leggings = new ItemStack (Material.LEATHER_LEGGINGS);
		LeatherArmorMeta legMeta = (LeatherArmorMeta) this.leggings.getItemMeta();
		legMeta.setColor(Color.WHITE);
		legMeta.setDisplayName(ChatColor.WHITE + "Patriotic White Pants");
		ArrayList <String> legLore = new ArrayList <String> ();
		legLore.add(ChatColor.YELLOW + "Whiter than Collin!");
		legMeta.setLore(legLore);
		this.addDefenseAttributes(legMeta, EquipmentSlot.LEGS, 6.0, 2.0, 0.10);
		this.leggings.setItemMeta(legMeta);
		
		this.boots = new ItemStack (Material.LEATHER_BOOTS);
		LeatherArmorMeta bootMeta = (LeatherArmorMeta) this.boots.getItemMeta();
		bootMeta.setColor(Color.BLUE);
		bootMeta.setDisplayName(ChatColor.BLUE + "Patriotic Blue Boots");
		ArrayList <String> bootLore = new ArrayList <String> ();
		bootLore.add(this.americanizeText("God bless America!"));
		bootMeta.setLore(bootLore);
		this.addDefenseAttributes(bootMeta, EquipmentSlot.FEET, 3.0, 1.5, 0.05);
		this.boots.setItemMeta(bootMeta);
		
		this.offhand = fwTypes.getRandomFirework(8, 2);
	}
}
