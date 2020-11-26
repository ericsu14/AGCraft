package com.joojet.plugins.mobs.monsters.ghast.UHC;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;

public class UHCGhast extends MobEquipment
{
	/** In UHC, ghasts have a 50% percent chance of dropping a golden ignot */
	public UHCGhast ()
	{
		super (MonsterType.UHC_GHAST);
		this.addBiomes(Biome.THE_VOID);
		this.weapon = new ItemStack (Material.GOLD_INGOT, 1);
		this.setDropRates(0.00f, 0.00f, 0.00f, 0.00f, 1.00f, 0.00f);
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) {
		// TODO Auto-generated method stub
		
	}
}
