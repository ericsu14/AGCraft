package com.joojet.plugins.mobs.fireworks;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import com.joojet.plugins.mobs.interfaces.Firework;

public class PaintTheSky extends Firework
{
	@Override
	public ItemStack generateFirework(int amount, int power) 
	{
		ItemStack fw = new ItemStack (Material.FIREWORK_ROCKET, amount);
		this.addLoreToItemMeta(fw, "Add some color to the night sky!", ChatColor.AQUA);
		FireworkMeta firework = (FireworkMeta) fw.getItemMeta();
		// Red to Green
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.fromRGB(255, 0, 27))
				.withTrail()
				.withFlicker()
				.withFade(Color.fromRGB(86, 199, 108))
				.with(Type.BURST)
				.build());
		// Green to Magenta
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.fromRGB(49, 255, 104))
				.withTrail()
				.withFlicker()
				.withFade(Color.fromRGB(215, 39, 153))
				.with(Type.BURST)
				.build());
		// Blue to Red
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.fromRGB(106, 241, 255))
				.withTrail()
				.withFade(Color.fromRGB(175, 10, 52))
				.withFlicker()
				.with(Type.BURST)
				.build());
		// Yellow to Purple
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.fromRGB(255, 210, 0))
				.withTrail()
				.withFlicker()
				.withFade(Color.fromRGB(84, 64, 177))
				.with(Type.BURST)
				.build());
		// Magenta to Green
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.fromRGB(255, 102, 218))
				.withTrail()
				.withFlicker()
				.withFade(Color.fromRGB(84, 64, 177))
				.with(Type.BURST)
				.build());
		firework.setDisplayName(ChatColor.LIGHT_PURPLE + "Paint the Skies");
		firework.setPower(power);
		fw.setItemMeta(firework);
		return fw;
	}
}
