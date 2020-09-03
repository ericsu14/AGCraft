package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.ShieldEquipment;

public class BruinShield extends ShieldEquipment
{
	public BruinShield ()
	{
		super (EquipmentTypes.BRUIN_SHIELD, DyeColor.YELLOW, EquipmentSlot.OFF_HAND, ChatColor.AQUA);
		this.loreColor = ChatColor.GOLD;
		this.wordsPerLine = 5;
		this.setDisplayName("Shield of the UCLA Bruin");
		this.addLoreToItemMeta("Consider this a war trophy. #beatthebruins");
		
		this.addPatterns(new Pattern (DyeColor.LIGHT_GRAY, PatternType.BRICKS),
				new Pattern (DyeColor.LIGHT_BLUE, PatternType.STRIPE_SMALL),
				new Pattern (DyeColor.LIGHT_GRAY, PatternType.TRIANGLE_BOTTOM),
				new Pattern (DyeColor.LIGHT_GRAY, PatternType.TRIANGLES_BOTTOM),
				new Pattern (DyeColor.BROWN, PatternType.CURLY_BORDER),
				new Pattern (DyeColor.BLACK, PatternType.RHOMBUS_MIDDLE),
				new Pattern (DyeColor.BLACK, PatternType.RHOMBUS_MIDDLE),
				new Pattern (DyeColor.BROWN, PatternType.CREEPER),
				new Pattern (DyeColor.BROWN, PatternType.CIRCLE_MIDDLE),
				new Pattern (DyeColor.BROWN, PatternType.TRIANGLE_TOP));
		
		this.addAttackAttributes(2.0, 0.0);
		this.addDefenseAttributes(0.0, 2.0, 0.10);
		this.addHealthAttributes(10.0);
	}
}
