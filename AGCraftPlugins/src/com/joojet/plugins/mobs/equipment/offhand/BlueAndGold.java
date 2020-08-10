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
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.BlockStateMeta;

import com.joojet.plugins.mobs.equipment.Equipment;

public class BlueAndGold extends Equipment 
{
	public BlueAndGold (ChatColor color)
	{
		super (Material.SHIELD, EquipmentSlot.OFF_HAND, color);
		this.wordsPerLine = 8;
		this.loreColor = ChatColor.AQUA;
		this.setDisplayName("The Blue and Gold");
		this.addLoreToItemMeta("The LORD is my strength and my shield; "
				+ "in him my heart trusts, and I am "
				+ "helped; my heart exults, and with my song "
				+ "I give thanks to him. -Psalm 28:7 ESV");
		
		/* Sets up custom shield patterns */
		BlockStateMeta itemMeta = (BlockStateMeta) this.getItemMeta();
		BlockState state = itemMeta.getBlockState();
		Banner bannerState = (Banner) state;
		
		bannerState.setBaseColor(DyeColor.BLUE);
		
		List <Pattern> pattern = new ArrayList <Pattern> ();
		pattern.add(new Pattern (DyeColor.YELLOW, PatternType.STRIPE_SMALL));
		pattern.add(new Pattern (DyeColor.BLUE, PatternType.STRIPE_BOTTOM));
		pattern.add(new Pattern (DyeColor.BLUE, PatternType.STRIPE_TOP));
		pattern.add(new Pattern (DyeColor.BLUE, PatternType.RHOMBUS_MIDDLE));
		pattern.add(new Pattern (DyeColor.YELLOW, PatternType.TRIANGLES_BOTTOM));
		pattern.add(new Pattern (DyeColor.YELLOW, PatternType.TRIANGLES_TOP));
		pattern.add(new Pattern (DyeColor.YELLOW, PatternType.STRAIGHT_CROSS));
		pattern.add(new Pattern (DyeColor.BLUE, PatternType.CURLY_BORDER));
		bannerState.setPatterns(pattern);
		bannerState.update();
		itemMeta.setBlockState(bannerState);
		itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ENCHANTS);
		itemMeta.setUnbreakable(true);
		this.setItemMeta(itemMeta);
		
		this.addAttackAttributes(3.0, 0.0);
		this.addDefenseAttributes(0.0, 4.0, 0.15);
		this.addHealthAttributes(20.0);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
	}
}
