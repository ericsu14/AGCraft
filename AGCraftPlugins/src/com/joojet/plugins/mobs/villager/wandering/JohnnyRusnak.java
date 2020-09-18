package com.joojet.plugins.mobs.villager.wandering;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.offhand.TotemOfEternalFaith;
import com.joojet.plugins.mobs.equipment.offhand.TotemOfEternalStrength;
import com.joojet.plugins.mobs.equipment.offhand.TotemOfTheEternalRushee;
import com.joojet.plugins.mobs.equipment.potions.EnhancedStrengthPotion;
import com.joojet.plugins.mobs.scrolls.*;
import com.joojet.plugins.mobs.villager.VillagerEquipment;

public class JohnnyRusnak extends VillagerEquipment
{
	public JohnnyRusnak ()
	{
		super (MonsterType.JOHNNY_RUSNAK);
		this.name = "Johnny Rusnak";
		this.color = ChatColor.GOLD;
		this.addBiomes(Biome.THE_VOID);
		
		this.addMobFlags(MobFlag.SHOW_NAME, MobFlag.SPAWN_LIGHTNING);
		this.setStat(MonsterStat.HEALTH, 40.0);
		
		this.addPotionEffect(CustomPotionEffect.REGEN, CustomPotionEffect.FIRE_RESISTANCE);
		
		/** Trade 1: Golden Carrots (bundles of 16)
		 * 		- Price: 2 Emeralds
		 * 		- Max stock: 4 */
		ItemStack goldCarrot = new ItemStack (Material.GOLDEN_CARROT, 16);
		this.addRecipe(goldCarrot, Material.EMERALD, 2, 4);
		
		/** Trade 2: Enchanted Golden Apple 
		 *		- Price: 16 emeralds, max stock: 4 */
		ItemStack goldApple = new ItemStack (Material.ENCHANTED_GOLDEN_APPLE, 1);
		this.addRecipe(goldApple, Material.EMERALD, 16, 4);
		
		/** Trade 3: Netherite Ingot
		 * 		- Price: 10 Diamonds
		 * 		- Max stock: 2 */
		ItemStack netheriteIgnot = new ItemStack (Material.NETHERITE_INGOT, 1);
		this.addRecipe(netheriteIgnot, Material.DIAMOND, 10, 2);
		
		/** Trade 4: Enhanced Potion of Strength
		 * 		- Price: 2 Diamonds
		 * 		- Max stock: 2 */
		EnhancedStrengthPotion enhancedStrengthPotion = new EnhancedStrengthPotion (this.color);
		this.addRecipe(enhancedStrengthPotion, Material.DIAMOND, 2, 2);
		
		/** Totems */
		this.addRecipe(new TotemOfEternalStrength(), Material.DIAMOND, 6, 1);
		this.addRecipe(new TotemOfEternalFaith(), Material.DIAMOND, 6, 1);
		this.addRecipe(new TotemOfTheEternalRushee(), Material.DIAMOND, 6, 1);
		
		/** Boss scrolls */
		this.addRecipe(new SummonAGSpotted(), Material.EMERALD, 24, 1);
		this.addRecipe(new SummonShadowClonejoojetsu(), Material.EMERALD, 32, 1);
		this.addRecipe(new SummonBarney(), Material.EMERALD, 48 , 1);
		this.addRecipe(new SummonUCLABearTamer(), Material.DIAMOND, 16, 1);
		this.addRecipe(new SummonSkullKid(), Material.NETHERITE_INGOT, 1, 1);
		this.addRecipe(new SummonTheTerminator(), Material.NETHERITE_INGOT, 1, 1);
		this.addRecipe(new SummonDoomGuy(), Material.NETHERITE_INGOT, 2, 1);
		this.addRecipe(new SummonGiantBruinTamer(), Material.NETHERITE_INGOT, 3, 1);
		
	}
}
