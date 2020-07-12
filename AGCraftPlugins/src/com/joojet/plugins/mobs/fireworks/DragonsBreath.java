package com.joojet.plugins.mobs.fireworks;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import com.joojet.plugins.mobs.interfaces.Firework;

public class DragonsBreath extends Firework
{
	@Override
	public ItemStack generateFirework(int amount, int power) 
	{
		ItemStack fw = new ItemStack (Material.FIREWORK_ROCKET, amount);
		this.addLoreToItemMeta(fw, "Light 'em up!", ChatColor.GOLD);
		FireworkMeta firework = (FireworkMeta) fw.getItemMeta();
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.ORANGE)
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
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.YELLOW)
				.withTrail()
				.withFlicker()
				.withFade(Color.fromRGB(247, 173, 74))
				.with(Type.BURST)
				.build());
		firework.setDisplayName(ChatColor.GOLD + "Dragon's Breath");
		firework.setPower(power);
		fw.setItemMeta(firework);
		return fw;
	}
}
