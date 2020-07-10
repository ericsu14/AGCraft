package com.joojet.plugins.mobs.fireworks;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import com.joojet.plugins.mobs.interfaces.Firework;

public class ChandelierFalling extends Firework 
{
	@Override
	public ItemStack generateFirework(int amount, int power) 
	{
		ItemStack fw = new ItemStack (Material.FIREWORK_ROCKET, amount);
		this.addLoreToItemMeta(fw, "Can't ever have a firework show without a classic!", ChatColor.YELLOW);
		FireworkMeta firework = (FireworkMeta) fw.getItemMeta();
		firework.addEffect(FireworkEffect.builder()
				.with(Type.BALL)
				.withColor(Color.WHITE)
				.withTrail()
				.withFade(Color.fromRGB(255, 244, 229))
				.build());
		firework.addEffect(FireworkEffect.builder()
				.with(Type.BALL_LARGE)
				.withColor(Color.fromRGB(255, 244, 229))
				.withTrail()
				.withFlicker()
				.withFade(Color.fromRGB(247, 173, 74))
				.build());
		firework.setDisplayName(ChatColor.YELLOW + "Falling Chandelier");
		firework.setPower(power);
		firework.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		fw.setItemMeta(firework);
		return fw;
	}
}
