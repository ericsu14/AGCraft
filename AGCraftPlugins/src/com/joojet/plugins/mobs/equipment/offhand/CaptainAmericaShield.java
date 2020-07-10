package com.joojet.plugins.mobs.equipment.offhand;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.block.BlockState;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.BlockStateMeta;

import com.joojet.plugins.mobs.interfaces.Equipment;

public class CaptainAmericaShield extends Equipment
{
	public CaptainAmericaShield (ChatColor color)
	{
		super (Material.SHIELD, EquipmentSlot.OFF_HAND, color);
		
		/* Sets up custom shield patterns */
		BlockStateMeta itemMeta = (BlockStateMeta) this.getItemMeta();
		BlockState state = itemMeta.getBlockState();
		Banner bannerState = (Banner) state;
		
		bannerState.setBaseColor(DyeColor.WHITE);
		
		List <Pattern> pattern = new ArrayList <Pattern> ();
		pattern.add(new Pattern (DyeColor.RED, PatternType.STRIPE_SMALL));
		pattern.add(new Pattern (DyeColor.BLUE, PatternType.CURLY_BORDER));
		pattern.add(new Pattern (DyeColor.BLUE, PatternType.RHOMBUS_MIDDLE));
		pattern.add(new Pattern (DyeColor.BLUE, PatternType.FLOWER));
		pattern.add(new Pattern (DyeColor.WHITE, PatternType.CIRCLE_MIDDLE));
		pattern.add(new Pattern (DyeColor.BLUE, PatternType.BORDER));
		
		bannerState.setPatterns(pattern);
		bannerState.update();
		itemMeta.setBlockState(bannerState);
		this.setItemMeta(itemMeta);
		
		this.addAttackAttributes(4.0, 0.0);
		this.addDefenseAttributes(0.0, 4.0, 0.15);
		this.addHealthAttributes(20.0);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
		this.addUnsafeEnchantment(Enchantment.MENDING, 1);
		this.addLoreToItemMeta("Crafted from Adamantium, this shield makes you feel like a complete superhero!");
	}
}
