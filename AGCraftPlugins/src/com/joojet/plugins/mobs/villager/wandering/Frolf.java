package com.joojet.plugins.mobs.villager.wandering;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.potions.*;
import com.joojet.plugins.mobs.interfaces.CustomSkillUser;
import com.joojet.plugins.mobs.scrolls.*;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.utility.TeleportSkill;
import com.joojet.plugins.mobs.villager.VillagerEquipment;

public class Frolf extends VillagerEquipment implements CustomSkillUser
{
	public Frolf ()
	{
		super (MonsterType.FROLF);
		this.name = "frolf";
		this.color = ChatColor.GOLD;
		this.addMobFlags(MobFlag.SHOW_NAME);
		
		/** Trade 1: Golden Carrots (bundles of 16)
		 * 		- Price: 3 Emeralds
		 * 		- Max stock: 8 */
		ItemStack goldCarrot = new ItemStack (Material.GOLDEN_CARROT, 16);
		this.addRecipe(goldCarrot, Material.EMERALD, 3, 8);
		
		/** Trade 2: Sponges (bundles of 4)
		 * 		- Price: 12 Emeralds
		 * 		- Max stock: 2 */
		ItemStack sponges = new ItemStack (Material.SPONGE, 4);
		this.addRecipe(sponges, Material.EMERALD, 12, 2);
		
		/** Trade 3: Enchanted Golden Apple 
		 *		- Price: 24 emeralds, max stock: 8 */
		ItemStack goldApple = new ItemStack (Material.ENCHANTED_GOLDEN_APPLE, 1);
		this.addRecipe(goldApple, Material.EMERALD, 24, 8);
		
		/** Trade 3.5: 16 Emeralds
		 *      - Price: 4 diamonds, max stock: 4 */
		this.addRecipe(new ItemStack (Material.EMERALD, 16), Material.DIAMOND, 4, 4);
		
		/** Trade 4: Netherite Ingot
		 * 		- Price: 12 Diamonds
		 * 		- Max stock: 1 */
		ItemStack netheriteIgnot = new ItemStack (Material.NETHERITE_INGOT, 1);
		this.addRecipe(netheriteIgnot, Material.DIAMOND, 12, 1);
		
		/** Trade 5: Potion of Haste
		 * 		- Price: 2 Diamonds
		 * 		- Max stock: 3 */
		HastePotion hastePotion = new HastePotion (this.color);
		this.addRecipe(hastePotion, Material.EMERALD, 16, 4);
		
		/** Trade 6: Potion of Luck
		 * 		- Price: 2 Diamonds
		 * 		- Max Stock: 4 */
		LuckPotion luckPotion = new LuckPotion (this.color);
		this.addRecipe(luckPotion, Material.EMERALD, 16, 4);
		
		/** Trade 7: Enhanced Potion of Strength
		 * 		- Price: 4 Diamonds
		 * 		- Max stock: 3 */
		EnhancedStrengthPotion enhancedStrengthPotion = new EnhancedStrengthPotion (this.color);
		this.addRecipe(enhancedStrengthPotion, Material.DIAMOND, 4, 3);
		
		/** Trade 8: Enhanced Potion of Speed 
		 * 		- Price: 4 Diamonds
		 * 		- Max stock: 3 */
		EnhancedSpeedPotion enhancedSpeedPotion = new EnhancedSpeedPotion (this.color);
		this.addRecipe(enhancedSpeedPotion, Material.DIAMOND, 4, 3);
		
		/** Trade 9: Enhanced Potion of Luck
		 * 		- Price: 5 Diamonds
		 * 		- Max Stock: 3 */
		EnhancedLuckPotion enhancedLuckPotion = new EnhancedLuckPotion (this.color);
		this.addRecipe(enhancedLuckPotion, Material.DIAMOND, 4, 3);
		
		/** Trade 10: Enhanced Potion of Haste
		 *  	- Price: 5 Diamonds
		 *  	- Max Stock: 3 */
		EnhancedHastePotion enhancedHastePotion = new EnhancedHastePotion (this.color);
		this.addRecipe(enhancedHastePotion, Material.DIAMOND, 5, 3);
		
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
		
		/** Trade 13: Summon Snowball
		 * 		- Price: 64 Emeralds
		 * 		- Max stock: 1 */
		SummoningScroll snowballScroll = new SummonSnowball();
		this.addRecipe(snowballScroll, Material.EMERALD, 64, 1);
		
		/** Trade 14: Summon Advanced Golem
		 * 		- Price: 8 Diamonds
		 * 		- Max stock: 1 */
		SummoningScroll advGolemScroll = new SummonAdvancedGolem ();
		this.addRecipe(advGolemScroll, Material.DIAMOND, 8, 1);
		
		/** Trade 15: Summon John Jae
		 * 		- Price: 16 Diamonds
		 * 		- Max stock: 1 */
		SummoningScroll johnJaeScroll = new SummonJohnJae ();
		this.addRecipe(johnJaeScroll, Material.DIAMOND, 16, 1);
		
		/** Trade 16: Summon Cookie
		 * 		- Price: 24 Diamonds
		 * 		- Max stock: 1 */
		SummoningScroll cookieScroll = new SummonCookie ();
		this.addRecipe(cookieScroll, Material.DIAMOND, 24, 1);
		
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) {
		skills.add(new TeleportSkill (64, 30, Integer.MAX_VALUE, 2));
	}
	
	
}
