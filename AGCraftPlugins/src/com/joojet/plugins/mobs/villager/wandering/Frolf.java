package com.joojet.plugins.mobs.villager.wandering;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.equipment.potions.*;
import com.joojet.plugins.mobs.scrolls.*;
import com.joojet.plugins.mobs.villager.VillagerEquipment;

public class Frolf extends VillagerEquipment
{
	public Frolf ()
	{
		this.name = "frolf";
		this.color = ChatColor.GOLD;
		this.showName = true;
		this.addBiomes(Biome.THE_VOID);
		
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
		 *		- Price: 3 diamonds, max stock: 8 */
		ItemStack goldApple = new ItemStack (Material.ENCHANTED_GOLDEN_APPLE, 1);
		this.addRecipe(goldApple, Material.DIAMOND, 3, 8);
		
		/** Trade 4: Netherite Ingot
		 * 		- Price: 12 Diamonds
		 * 		- Max stock: 1 */
		ItemStack netheriteIgnot = new ItemStack (Material.NETHERITE_INGOT, 1);
		this.addRecipe(netheriteIgnot, Material.DIAMOND, 12, 1);
		
		/** Trade 5: Potion of Haste
		 * 		- Price: 2 Diamonds
		 * 		- Max stock: 3 */
		HastePotion hastePotion = new HastePotion (this.color);
		this.addRecipe(hastePotion, Material.DIAMOND, 2, 4);
		
		/** Trade 6: Potion of Luck
		 * 		- Price: 2 Diamonds
		 * 		- Max Stock: 4 */
		LuckPotion luckPotion = new LuckPotion (this.color);
		this.addRecipe(luckPotion, Material.DIAMOND, 2, 4);
		
		/** Trade 7: Enhanced Potion of Strength
		 * 		- Price: 5 Diamonds
		 * 		- Max stock: 4 */
		EnhancedStrengthPotion enhancedStrengthPotion = new EnhancedStrengthPotion (this.color);
		this.addRecipe(enhancedStrengthPotion, Material.DIAMOND, 5, 3);
		
		/** Trade 8: Enhanced Potion of Speed 
		 * 		- Price: 5 Diamonds
		 * 		- Max stock: 3 */
		EnhancedSpeedPotion enhancedSpeedPotion = new EnhancedSpeedPotion (this.color);
		this.addRecipe(enhancedSpeedPotion, Material.DIAMOND, 5, 3);
		
		/** Trade 9: Enhanced Potion of Haste
		 *  	- Price: 5 Diamonds
		 *  	- Max Stock: 3 */
		EnhancedHastePotion enhancedHastePotion = new EnhancedHastePotion (this.color);
		this.addRecipe(enhancedHastePotion, Material.DIAMOND, 5, 3);
		
		/** Trade 10: Enhanced Potion of Luck
		 * 		- Price: 5 Diamonds
		 * 		- Max Stock: 3 */
		EnhancedLuckPotion enhancedLuckPotion = new EnhancedLuckPotion (this.color);
		this.addRecipe(enhancedLuckPotion, Material.DIAMOND, 5, 3);
		
		/** Trade 11: Summon Frosty
		 * 		- Price: 16 Emeralds
		 * 		- Max stock: 1 */
		SummoningScroll frostyScroll = new SummonFrosty();
		this.addRecipe(frostyScroll, Material.EMERALD, 16, 1);
		
		/** Trade 12: Summon Scruffy
		 * 		- Price: 32 Emeralds
		 * 		- Max stock: 1 */
		SummoningScroll scruffyScroll = new SummonScruffy ();
		this.addRecipe(scruffyScroll, Material.EMERALD, 32, 1);
		
		/** Trade 13: Summon Advanced Golem
		 * 		- Price: 12 Diamonds
		 * 		- Max stock: 1 */
		SummoningScroll advGolemScroll = new SummonAdvancedGolem ();
		this.addRecipe(advGolemScroll, Material.DIAMOND, 12, 1);
		
		/** Trade 14: Summon John Jae
		 * 		- Price: 24 Diamonds
		 * 		- Max stock: 1 */
		SummoningScroll johnJaeScroll = new SummonJohnJae ();
		this.addRecipe(johnJaeScroll, Material.DIAMOND, 24, 1);
	}
	
	
}
