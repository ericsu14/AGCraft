package com.joojet.plugins.mobs.monsters.pillager;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.joojet.plugins.mobs.fireworks.FireworkTypes;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

import net.md_5.bungee.api.ChatColor;
public class PatrioticPillager extends MobEquipment
{
	private FireworkTypes fwTypes;
	public PatrioticPillager ()
	{
		fwTypes = new FireworkTypes();
		this.name = this.americanizeText("Patriotic Pillager");
		this.health = 16;
		this.offhand = fwTypes.getRandomFirework(8, 1);
		this.weapon = new ItemStack (Material.CROSSBOW, 1);
		ItemMeta weaponMeta = this.weapon.getItemMeta();
		weaponMeta.addEnchant(Enchantment.MULTISHOT, 1, true);
		weaponMeta.setDisplayName(ChatColor.BLUE + "Firework Launcher");
		this.weapon.setItemMeta(weaponMeta);
	}
}
