package com.joojet.plugins.mobs.fireworks;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import com.joojet.plugins.mobs.interfaces.Firework;


public class CreeperRocket extends Firework
{
	@Override
	public ItemStack generateFirework (int amount, int power)
	{
		ItemStack fw = new ItemStack (Material.FIREWORK_ROCKET, amount);
		this.addLoreToItemMeta(fw, "Aww man...", ChatColor.DARK_GREEN);
		FireworkMeta firework = (FireworkMeta) fw.getItemMeta();
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.fromRGB(0, 255, 0))
				.with(Type.CREEPER)
				.flicker(true)
				.trail(true)
				.withFade(Color.fromRGB(255, 244, 229))
				.build());
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.fromRGB(255, 244, 229))
				.with(Type.CREEPER)
				.flicker(true)
				.trail(true)
				.withFade(Color.WHITE)
				.build());
		firework.setPower(power);
		firework.setDisplayName(ChatColor.GREEN + "Creeper Rocket");
		fw.setItemMeta(firework);
		
		return fw;
	}
}
