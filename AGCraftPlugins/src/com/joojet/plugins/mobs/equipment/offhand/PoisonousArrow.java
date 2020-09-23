package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.EquipmentType;

public class PoisonousArrow extends TippedArrow {

	public PoisonousArrow(ChatColor color) 
	{
		super(EquipmentType.POISONOUS_ARROW, color);
	}

	@Override
	protected void addPotionData() 
	{
		this.loreColor = ChatColor.GOLD;
		this.setDisplayName("Poisonous Arrow");
		this.addPotionEffect(PotionEffectType.POISON, 140, 1);
		this.addPotionEffect(PotionEffectType.HUNGER, 280, 1);
		this.addLoreToItemMeta("A highly poisonous arrow that also gives you food poisoning...");
		this.setColor(Color.GREEN);
	}

}
