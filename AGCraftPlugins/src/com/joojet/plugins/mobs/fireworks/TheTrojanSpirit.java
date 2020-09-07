package com.joojet.plugins.mobs.fireworks;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

public class TheTrojanSpirit extends Firework {

	@Override
	public ItemStack generateFirework(int amount, int power) {
		ItemStack fw = new ItemStack (Material.FIREWORK_ROCKET, amount);
		this.wordsPerLine = 6;
		this.addLoreToItemMeta(fw, "Show off your school spirit with this rocket!", ChatColor.GOLD);
		FireworkMeta firework = (FireworkMeta) fw.getItemMeta();
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.fromRGB(255, 244, 229))
				.withFade(Color.fromRGB(255, 204, 0))
				.withTrail()
				.with(Type.BALL)
				.build());
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.fromRGB(255, 204, 0))
				.withFade(Color.fromRGB(153, 27, 30))
				.withTrail()
				.with(Type.BALL)
				.build());
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.fromRGB(153, 27, 30))
				.withFade(Color.fromRGB(255, 244, 229))
				.withTrail()
				.with(Type.BALL_LARGE)
				.build());
		firework.setDisplayName(ChatColor.RED + "The" + ChatColor.GOLD + " Trojan" + ChatColor.GOLD + " Spirit");
		firework.setPower(power);
		fw.setItemMeta(firework);
		
		return fw;
	}

}
