package com.joojet.plugins.mobs.enums;

import java.util.ArrayList;
import java.util.Random;

import com.joojet.plugins.mobs.interfaces.MobEquipment;
import com.joojet.plugins.mobs.monsters.zombie.*;

public class ZombieTypes 
{
	private ArrayList <MobEquipment> equipmentList;
	
	public ZombieTypes ()
	{
		equipmentList = new ArrayList <MobEquipment> ();
		this.addEquipment(new UncommonZombie(), 4);
		this.addEquipment(new BadassZombie(), 2);
		this.addEquipment(new UltimateBadassZombie(), 1);
	}
	
	public void addEquipment (MobEquipment equipment, int weight)
	{
		for (int i = 0; i < weight; ++i)
		{
			equipmentList.add(equipment);
		}
	}
	
	public MobEquipment getRandomEquipment ()
	{
		int n = equipmentList.size();
		Random rand = new Random ();
		return equipmentList.get(rand.nextInt(n));
	}
}
