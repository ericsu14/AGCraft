package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.EquipmentType;

public class ArrowOfDoom extends TippedArrow {

	public ArrowOfDoom(ChatColor color) 
	{
		super(EquipmentType.ARROW_OF_DOOM, color);
	}

	@Override
	protected void addPotionData() 
	{
		this.setColor(Color.fromRGB(75, 83, 32));
		this.addPotionEffect(PotionEffectType.WEAKNESS, 140, 1);
		this.addPotionEffect(PotionEffectType.WITHER, 140, 3);
		this.addPotionEffect(PotionEffectType.BLINDNESS, 140, 0);
		this.addLoreToItemMeta("Custom-tipped arrows specifically made for slaying demons in the Nether. And you.");
	}

}
