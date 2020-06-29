package com.joojet.plugins.mobs.interfaces;

import java.util.ArrayList;
import java.util.Random;

public abstract class MonsterTypes 
{
	private ArrayList <MobEquipment> equipmentList;
	
	public MonsterTypes ()
	{
		this.equipmentList = new ArrayList <MobEquipment> ();
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
