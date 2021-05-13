package com.joojet.plugins.mobs.villager.wandering;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.offhand.TotemOfEternalFaith;
import com.joojet.plugins.mobs.equipment.offhand.TotemOfEternalStrength;
import com.joojet.plugins.mobs.equipment.offhand.TotemOfTheEternalRushee;
import com.joojet.plugins.mobs.equipment.potions.EnhancedStrengthPotion;
import com.joojet.plugins.mobs.equipment.weapons.ShotBow;
import com.joojet.plugins.mobs.equipment.weapons.SpiritualTravesty;
import com.joojet.plugins.mobs.interfaces.CustomSkillUser;
import com.joojet.plugins.mobs.scrolls.*;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.attack.ThundagaSkill;
import com.joojet.plugins.mobs.skills.utility.TeleportSkill;
import com.joojet.plugins.mobs.villager.VillagerEquipment;

public class JohnnyRusnak extends VillagerEquipment implements CustomSkillUser
{
	public JohnnyRusnak ()
	{
		super (MonsterType.JOHNNY_RUSNAK);
		this.name = "Johnny Rusnak";
		this.color = ChatColor.GOLD;
		
		this.addMobFlags(MobFlag.SHOW_NAME, MobFlag.SPAWN_LIGHTNING);
		this.setStat(MonsterStat.HEALTH, 40.0);
		
		this.addPotionEffect(CustomPotionEffect.REGEN, CustomPotionEffect.FIRE_RESISTANCE);

		/** Normal trades */
		this.addRecipe(new ItemStack (Material.GOLDEN_CARROT, 16), Material.EMERALD, 2, 4);
		this.addRecipe(new ItemStack (Material.ENCHANTED_GOLDEN_APPLE, 1), Material.EMERALD, 16, 4);
		this.addRecipe(new ItemStack (Material.EMERALD, 16), Material.DIAMOND, 4, 4);
		this.addRecipe(new ItemStack (Material.NETHERITE_INGOT, 1), Material.DIAMOND, 10, 2);
		this.addRecipe(new EnhancedStrengthPotion (this.color), Material.DIAMOND, 2, 2);
		
		/** Rare weapons */
		this.addRecipe(new ShotBow (ChatColor.GOLD), Material.DIAMOND, 5, 1);
		this.addRecipe(new ItemStack (Material.TRIDENT), Material.DIAMOND, 7, 1);
		this.addRecipe(new SpiritualTravesty(ChatColor.GOLD), Material.NETHERITE_INGOT, 3, 1);
		
		/** Totems */
		this.addRecipe(new TotemOfEternalStrength(), Material.DIAMOND, 6, 1);
		this.addRecipe(new TotemOfEternalFaith(), Material.DIAMOND, 6, 1);
		this.addRecipe(new TotemOfTheEternalRushee(), Material.DIAMOND, 6, 1);
		
		/** Boss scrolls */
		this.addRecipe(new SummonAGSpotted(), Material.EMERALD, 24, 1);
		this.addRecipe(new SummonShadowClonejoojetsu(), Material.EMERALD, 32, 1);
		this.addRecipe(new SummonBarney(), Material.EMERALD, 48 , 1);
		this.addRecipe(new SummonSoulObliterator (), Material.EMERALD, 48, 1);
		this.addRecipe(new SummonSkullKid(), Material.NETHERITE_INGOT, 1, 1);
		this.addRecipe(new SummonTheTerminator(), Material.NETHERITE_INGOT, 1, 1);
		this.addRecipe(new SummonHellWalker (), Material.NETHERITE_INGOT, 1, 1);
		this.addRecipe(new SummonDoomGuy(), Material.NETHERITE_INGOT, 3, 1);
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) {
		skills.add(new TeleportSkill (64, 30, Integer.MAX_VALUE, 2));
		skills.add(new ThundagaSkill (20, 14, Integer.MAX_VALUE, 12, 3.0F, 6, 20, 0.85));
	}
}
