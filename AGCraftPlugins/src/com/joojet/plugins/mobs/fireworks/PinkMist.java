package com.joojet.plugins.mobs.fireworks;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import com.joojet.plugins.mobs.interfaces.Firework;

import net.md_5.bungee.api.ChatColor;

public class PinkMist extends Firework
{
	@Override
	public ItemStack generateFirework(int amount, int power) 
	{
		ItemStack fw = new ItemStack (Material.FIREWORK_ROCKET, amount);
		FireworkMeta firework = (FireworkMeta) fw.getItemMeta();
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.WHITE)
				.withTrail()
				.withFlicker()
				.with(Type.BALL)
				.build());
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.fromRGB(255, 51, 255))
				.withTrail()
				.withFade(Color.WHITE)
				.withFlicker()
				.with(Type.BALL_LARGE)
				.build());
		firework.setDisplayName(ChatColor.LIGHT_PURPLE + "Pink Mist");
		firework.setPower(power);
		fw.setItemMeta(firework);
		return fw;
	}
}
