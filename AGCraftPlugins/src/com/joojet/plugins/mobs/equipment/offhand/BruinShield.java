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

public class BruinShield extends Equipment
{
	public BruinShield ()
	{
		super (Material.SHIELD, EquipmentSlot.OFF_HAND, ChatColor.AQUA);
		this.loreColor = ChatColor.GOLD;
		this.wordsPerLine = 5;
		this.setDisplayName("Shield of the UCLA Bruin");
		this.addLoreToItemMeta("Consider this a war trophy. #beatthebruins");
		
		/* Sets up custom shield patterns */
		BlockStateMeta itemMeta = (BlockStateMeta) this.getItemMeta();
		BlockState state = itemMeta.getBlockState();
		Banner bannerState = (Banner) state;
		
		bannerState.setBaseColor(DyeColor.YELLOW);
		
		List <Pattern> pattern = new ArrayList <Pattern> ();
		pattern.add(new Pattern (DyeColor.LIGHT_GRAY, PatternType.BRICKS));
		pattern.add(new Pattern (DyeColor.LIGHT_BLUE, PatternType.STRIPE_SMALL));
		pattern.add(new Pattern (DyeColor.LIGHT_GRAY, PatternType.TRIANGLE_BOTTOM));
		pattern.add(new Pattern (DyeColor.LIGHT_GRAY, PatternType.TRIANGLES_BOTTOM));
		pattern.add(new Pattern (DyeColor.BROWN, PatternType.CURLY_BORDER));
		pattern.add(new Pattern (DyeColor.BLACK, PatternType.RHOMBUS_MIDDLE));
		pattern.add(new Pattern (DyeColor.BLACK, PatternType.RHOMBUS_MIDDLE));
		pattern.add(new Pattern (DyeColor.BROWN, PatternType.CREEPER));
		pattern.add(new Pattern (DyeColor.BROWN, PatternType.CIRCLE_MIDDLE));
		pattern.add(new Pattern (DyeColor.BROWN, PatternType.TRIANGLE_TOP));
		bannerState.setPatterns(pattern);
		bannerState.update();
		itemMeta.setBlockState(bannerState);
		itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ENCHANTS);
		itemMeta.setUnbreakable(true);
		this.setItemMeta(itemMeta);
		
		this.addAttackAttributes(2.0, 0.0);
		this.addDefenseAttributes(0.0, 2.0, 0.10);
		this.addHealthAttributes(10.0);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
	}
}
