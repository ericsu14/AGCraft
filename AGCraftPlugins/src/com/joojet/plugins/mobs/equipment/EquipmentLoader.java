package com.joojet.plugins.mobs.equipment;

import java.util.HashMap;

import com.joojet.plugins.mobs.enums.EquipmentClassifier;
import com.joojet.plugins.mobs.equipment.boots.BootTypes;
import com.joojet.plugins.mobs.equipment.cake.CakeTypes;
import com.joojet.plugins.mobs.equipment.chest.ChestTypes;
import com.joojet.plugins.mobs.equipment.head.HeadTypes;
import com.joojet.plugins.mobs.equipment.offhand.OffhandTypes;
import com.joojet.plugins.mobs.equipment.potions.PotionTypes;
import com.joojet.plugins.mobs.equipment.weapons.WeaponTypes;
import com.joojet.plugins.mobs.interpreter.EquipmentTypeInterpreter;

/** This class ensures all custom equipments are loaded into an internal search trie, allowing
 *  every custom equipment instance to be searchable by its name */
public class EquipmentLoader 
{
	/** Search trie used to map Equipment Identifiers to Equipment instances */
	protected EquipmentTypeInterpreter interpreter;
	
	/** Stores a reference to all loaded equipment type instances */
	protected HashMap <EquipmentClassifier, EquipmentTypes> instances;
	
	public EquipmentLoader ()
	{
		this.interpreter = new EquipmentTypeInterpreter();
		this.instances = new HashMap <EquipmentClassifier, EquipmentTypes> ();
		this.loadEquipment(
			new BootTypes (this.interpreter),
			new CakeTypes (this.interpreter),
			new ChestTypes (this.interpreter),
			new HeadTypes (this.interpreter),
			new OffhandTypes (this.interpreter),
			new PotionTypes (this.interpreter),
			new WeaponTypes (this.interpreter)
		);
		
	}
	
	/** Loads in a list of equipment types and registers those instances into 
	 *  an internal hashmap. */
	public void loadEquipment (EquipmentTypes... equipmentTypes)
	{
		for (EquipmentTypes equipmentType : equipmentTypes)
		{
			this.instances.put(equipmentType.getClassifier(), equipmentType);
		}
	}
	
	/** Fetches a specific equipment type based on the passed classifier */
	public EquipmentTypes getEquipmentTypeInstance (EquipmentClassifier classifier)
	{
		return this.instances.get(classifier);
	}
	
	/** Returns the search trie containing the EquipmentType (enum) to Equipment mapping */
	public EquipmentTypeInterpreter getInterpreter ()
	{
		return interpreter;
	}
}
