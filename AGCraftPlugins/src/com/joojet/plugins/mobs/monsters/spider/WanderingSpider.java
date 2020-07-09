package com.joojet.plugins.mobs.monsters.spider;

import org.bukkit.ChatColor;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class WanderingSpider extends MobEquipment
{
	public WanderingSpider ()
	{
		this.health = 40;
		this.color = ChatColor.GOLD;
		this.name = "Wandering Spider";
		this.showName = true;
		
		this.effects.add(CustomPotionEffect.RESISTANCE.getPotionEffect());
		this.effects.add(CustomPotionEffect.SPEED.getPotionEffect());
		this.effects.add(CustomPotionEffect.STRENGTH.getPotionEffect());

		/* this.weapon = new ItemStack (Material.NETHERITE_SWORD, 1);
		ItemMeta weaponMeta = this.weapon.getItemMeta();
		weaponMeta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
		weaponMeta.addEnchant(Enchantment.FIRE_ASPECT, 3, true);
		weaponMeta.addEnchant(Enchantment.KNOCKBACK, 2, true);
		
		weaponMeta.setDisplayName(this.color + "Blade of the Wandering Spider");
		this.addLoreToItemMeta(weaponMeta, "Very potent stuff.");
		
		this.weapon.setItemMeta(weaponMeta);
		
		// Chestplate
		this.chestplate = new ItemStack (Material.NETHERITE_CHESTPLATE, 1);
		ItemMeta chestMeta = this.chestplate.getItemMeta();
		chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		chestMeta.addEnchant(Enchantment.THORNS, 4, true);
		chestMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		chestMeta.setDisplayName(this.color + "Thanos Plate");
		this.addDefenseAttributes(chestMeta, EquipmentSlot.CHEST, 15.0, 8.0, 0.2);
		this.chestplate.setItemMeta(chestMeta);
		this.addRandomDamage(this.chestplate); */
	}
}
