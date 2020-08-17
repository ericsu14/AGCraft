package com.joojet.plugins.mobs.equipment.weapons;

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

public class LeftCrashSymbol extends Equipment
{
	public LeftCrashSymbol (ChatColor color)
	{
		super (Material.SHIELD, EquipmentSlot.HAND, color);
		this.loreColor = ChatColor.GOLD;
		this.wordsPerLine = 5;
		this.setDisplayName("Left Crash Symbol");
		this.addLoreToItemMeta("Bash your enemies with this crash symbol!");
		
		/* Sets up custom shield patterns */
		BlockStateMeta itemMeta = (BlockStateMeta) this.getItemMeta();
		BlockState state = itemMeta.getBlockState();
		Banner bannerState = (Banner) state;
		
		bannerState.setBaseColor(DyeColor.YELLOW);
		
		List <Pattern> pattern = new ArrayList <Pattern> ();
		pattern.add(new Pattern (DyeColor.BLACK, PatternType.FLOWER));
		pattern.add(new Pattern (DyeColor.YELLOW, PatternType.STRIPE_BOTTOM));
		pattern.add(new Pattern (DyeColor.BLACK, PatternType.STRIPE_DOWNRIGHT));
		pattern.add(new Pattern (DyeColor.YELLOW, PatternType.STRIPE_TOP));
		pattern.add(new Pattern (DyeColor.YELLOW, PatternType.CURLY_BORDER));
		pattern.add(new Pattern (DyeColor.YELLOW, PatternType.STRIPE_LEFT));
		pattern.add(new Pattern (DyeColor.YELLOW, PatternType.STRIPE_RIGHT));
		
		bannerState.setPatterns(pattern);
		bannerState.update();
		itemMeta.setBlockState(bannerState);
		itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		this.setItemMeta(itemMeta);
		
		this.addAttackAttributes(6.0, 0.0);
		this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
		this.addUnsafeEnchantment(Enchantment.MENDING, 1);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		this.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 4);
	}
}
