package com.joojet.plugins.mobs.fireworks;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;


public class FreedomStar extends Firework
{
	@Override
	public ItemStack generateFirework (int amount, int power)
	{
		ItemStack fw = new ItemStack (Material.FIREWORK_ROCKET, amount);
		this.addLoreToItemMeta(fw, "Light up the skies with this patriotic rocket!", ChatColor.GOLD);
		FireworkMeta firework = (FireworkMeta) fw.getItemMeta();
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.BLUE)
				.withTrail()
				.with(Type.STAR)
				.build());
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.WHITE)
				.withTrail()
				.with(Type.STAR)
				.build());
		firework.addEffect(FireworkEffect.builder()
				.withColor(Color.RED)
				.withFade(Color.fromRGB(246, 205, 139))
				.withTrail()
				.with(Type.STAR)
				.build());
		firework.setDisplayName(ChatColor.WHITE + "The" + ChatColor.RED + " Stars" + ChatColor.WHITE + " and" + ChatColor.BLUE + " Stripes");
		firework.setPower(power);
		fw.setItemMeta(firework);
		return fw;
	}
}
