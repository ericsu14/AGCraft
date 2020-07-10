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

public class MagicalFirework extends Firework
{
	@Override
	public ItemStack generateFirework (int amount, int power)
	{
		ItemStack fw = new ItemStack (Material.FIREWORK_ROCKET, amount);
		this.addLoreToItemMeta(fw, "For the blue and gold!", ChatColor.GOLD);
		FireworkMeta firework = (FireworkMeta) fw.getItemMeta();
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.YELLOW)
				.withFade(Color.fromRGB(226, 134, 47))
				.withTrail()
				.withFlicker()
				.with(Type.BALL_LARGE)
				.build());
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.WHITE)
				.withFade(Color.fromBGR(246, 238, 255))
				.withTrail()
				.withFlicker()
				.with(Type.BALL_LARGE)
				.build());
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.fromRGB(255, 244, 229))
				.withFade(Color.BLUE)
				.withTrail()
				.withFlicker()
				.with(Type.BALL_LARGE)
				.build());
		firework.setDisplayName(ChatColor.LIGHT_PURPLE + "Magical Firework");
		firework.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		firework.setPower(power);
		fw.setItemMeta(firework);
		return fw;
	}
}
