package com.joojet.plugins.mobs.fireworks;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import com.joojet.plugins.mobs.interfaces.Firework;

/** Your classic red, white, and blue firework */
public class FreedomRocket extends Firework 
{
	@Override
	public ItemStack generateFirework (int amount, int power)
	{
		
		ItemStack fw = new ItemStack (Material.FIREWORK_ROCKET, amount);
		this.addLoreToItemMeta(fw, "A taste of Freedom!", ChatColor.GOLD);
		FireworkMeta firework = (FireworkMeta) fw.getItemMeta();
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.BLUE)
				.withTrail()
				.with(Type.BALL)
				.build());
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.WHITE)
				.withTrail()
				.with(Type.BALL)
				.build());
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.RED)
				.withFade(Color.WHITE)
				.withTrail()
				.with(Type.BALL_LARGE)
				.build());
		firework.setDisplayName(ChatColor.WHITE + "The" + ChatColor.RED + " Red" + ChatColor.WHITE + " White and " + ChatColor.BLUE + "Blue");
		firework.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		firework.setPower(power);
		fw.setItemMeta(firework);
		
		return fw;
	}
}
