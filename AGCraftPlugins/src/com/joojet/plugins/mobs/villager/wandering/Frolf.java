package com.joojet.plugins.mobs.villager.wandering;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.interfaces.SummoningScroll;
import com.joojet.plugins.mobs.interfaces.VillagerEquipment;
import com.joojet.plugins.mobs.scrolls.*;

public class Frolf extends VillagerEquipment
{
	public Frolf ()
	{
		this.name = "frolf";
		this.color = ChatColor.GOLD;
		this.showName = true;
		
		/** Trade 1: Golden Carrots (bundles of 16)
		 * 		- Price: 3 Emeralds
		 * 		- Max stock: 4 */
		ItemStack goldCarrot = new ItemStack (Material.GOLDEN_CARROT, 16);
		this.addRecipe(goldCarrot, Material.EMERALD, 3, 4);
		
		/** Trade 2: Sponges (bundles of 4)
		 * 		- Price: 12 Emeralds
		 * 		- Max stock: 2 */
		ItemStack sponges = new ItemStack (Material.SPONGE, 4);
		this.addRecipe(sponges, Material.EMERALD, 12, 2);
		
		/** Trade 3: Enchanted Golden Apple 
		 *		- Price: 3 diamonds, max stock: 3 */
		ItemStack goldApple = new ItemStack (Material.ENCHANTED_GOLDEN_APPLE, 1);
		this.addRecipe(goldApple, Material.DIAMOND, 3, 3);
		
		/** Trade 4: Netherite Ingot
		 * 		- Price: 12 Diamonds
		 * 		- Max stock: 1 */
		ItemStack netheriteIgnot = new ItemStack (Material.NETHERITE_INGOT, 1);
		this.addRecipe(netheriteIgnot, Material.DIAMOND, 12, 1);
		
		/** Trade 5: Enhanced Potion of Strength
		 * 		- Price: 2 Diamonds
		 * 		- Max stock: 3 */
		ItemStack enhancedStrengthPotion = new ItemStack (Material.POTION, 1);
		PotionMeta strPotMeta = (PotionMeta) enhancedStrengthPotion.getItemMeta();
		strPotMeta.setColor(Color.MAROON);
		strPotMeta.addCustomEffect(new PotionEffect (PotionEffectType.INCREASE_DAMAGE, 9600, 1), false);
		strPotMeta.setDisplayName(this.color + "Enhanced Potion of Strength");
		enhancedStrengthPotion.setItemMeta(strPotMeta);
		this.addRecipe(enhancedStrengthPotion, Material.DIAMOND, 3, 1);
		
		/** Trade 6: Enhanced Potion of Speed 
		 * 		- Price: 2 Diamonds
		 * 		- Max stock: 3 */
		ItemStack enhancedSpeedPotion = new ItemStack (Material.POTION, 1);
		PotionMeta speedPotMeta = (PotionMeta) enhancedSpeedPotion.getItemMeta();
		speedPotMeta.setColor(Color.fromRGB(137, 207, 240));
		speedPotMeta.addCustomEffect(new PotionEffect (PotionEffectType.SPEED, 9600, 1), false);
		speedPotMeta.setDisplayName(this.color + "Enhanced Potion of Speed");
		enhancedSpeedPotion.setItemMeta(speedPotMeta);
		this.addRecipe(enhancedSpeedPotion, Material.DIAMOND, 3, 1);
		
		/** Trade 7: Potion of Haste
		 * 		- Price: 2 Diamonds
		 * 		- Max stock: 2 */
		ItemStack hastePotion = new ItemStack (Material.POTION, 1);
		PotionMeta hastePotMeta = (PotionMeta) hastePotion.getItemMeta();
		hastePotMeta.setColor (Color.YELLOW);
		hastePotMeta.addCustomEffect(new PotionEffect (PotionEffectType.FAST_DIGGING, 9600, 0), false);
		hastePotMeta.setDisplayName(this.color + "Potion of Haste");
		hastePotion.setItemMeta(hastePotMeta);
		this.addRecipe(hastePotion, Material.DIAMOND, 2, 2);
		
		/** Trade 8: Summon Frosty
		 * 		- Price: 16 Emeralds
		 * 		- Max stock: 1 */
		SummoningScroll frostyScroll = new SummonFrosty();
		this.addRecipe(frostyScroll, Material.EMERALD, 16, 1);
		
		/** Trade 9: Summon Scruffy
		 * 		- Price: 32 Emeralds
		 * 		- Max stock: 1 */
		SummoningScroll scruffyScroll = new SummonScruffy ();
		this.addRecipe(scruffyScroll, Material.EMERALD, 32, 1);
		
		/** Trade 10: Summon Advanced Golem
		 * 		- Price: 12 Diamonds
		 * 		- Max stock: 1 */
		SummoningScroll advGolemScroll = new SummonAdvancedGolem ();
		this.addRecipe(advGolemScroll, Material.DIAMOND, 12, 1);
		
		/** Trade 11: Summon John Jae
		 * 		- Price: 24 Diamonds
		 * 		- Max stock: 1 */
		SummoningScroll johnJaeScroll = new SummonJohnJae ();
		this.addRecipe(johnJaeScroll, Material.DIAMOND, 24, 1);
		
	}
	
	
}
