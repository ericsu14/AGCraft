package com.joojet.plugins.mobs.fireworks;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import com.joojet.plugins.mobs.interfaces.Firework;

import net.md_5.bungee.api.ChatColor;

public class ChandelierBurst extends Firework 
{

	@Override
	public ItemStack generateFirework(int amount, int power) 
	{
		ItemStack fw = new ItemStack (Material.FIREWORK_ROCKET, amount);
		FireworkMeta firework = (FireworkMeta) fw.getItemMeta();
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.WHITE)
				.withTrail()
				.withFade(Color.fromRGB(255, 244, 229))
				.withFlicker()
				.with(Type.BURST)
				.build());
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.fromRGB(255, 244, 229))
				.withTrail()
				.withFlicker()
				.withFade(Color.fromRGB(247, 173, 74))
				.with(Type.BURST)
				.build());
		firework.setDisplayName(ChatColor.YELLOW + "Chandelier Burst");
		firework.setPower(power);
		fw.setItemMeta(firework);
		return fw;
	}

}