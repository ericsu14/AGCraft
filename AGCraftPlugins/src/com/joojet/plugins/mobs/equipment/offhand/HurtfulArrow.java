package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.enums.EquipmentTypes;

public class HurtfulArrow extends TippedArrow 
{
	public HurtfulArrow (ChatColor color)
	{
		super (EquipmentTypes.HURTFUL_ARROW, color);
	}
	
	public HurtfulArrow (ChatColor color, int count)
	{
		super (EquipmentTypes.HURTFUL_ARROW, color, count);
	}
	
	@Override
	public void addPotionData ()
	{
		this.setColor(Color.BLACK);
		this.addPotionEffect(PotionEffectType.HARM, 1, 0);
		this.setDisplayName("Hurtful Arrow");
		this.addLoreToItemMeta("Inflicts great pain to your enemies");
	}
}
