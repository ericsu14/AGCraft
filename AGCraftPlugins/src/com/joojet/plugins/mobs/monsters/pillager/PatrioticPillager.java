package com.joojet.plugins.mobs.monsters.pillager;

import java.util.ArrayList;

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
		this.offhand = fwTypes.getRandomFirework(16, 2);
		this.weapon = new ItemStack (Material.CROSSBOW, 1);
		ItemMeta weaponMeta = this.weapon.getItemMeta();
		weaponMeta.addEnchant(Enchantment.MULTISHOT, 1, true);
		weaponMeta.addEnchant(Enchantment.DURABILITY, 3, true);
		weaponMeta.addEnchant(Enchantment.QUICK_CHARGE, 3, true);
		weaponMeta.addEnchant(Enchantment.MENDING, 1, true);
		weaponMeta.setDisplayName(ChatColor.GOLD + "Firework Launcher");
		ArrayList<String> weaponLore = new ArrayList <String> ();
		weaponLore.add(this.americanizeText("Light up the sky!"));
		weaponMeta.setLore(weaponLore);
		this.weapon.setItemMeta(weaponMeta);
		this.setDropRates(0.0f, 0.0f, 0.0f, 0.0f, 0.25f, 0.85f);
	}
}
