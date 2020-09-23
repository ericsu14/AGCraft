package com.joojet.plugins.mobs.equipment;

import com.joojet.plugins.mobs.enums.EquipmentClassifier;
import com.joojet.plugins.mobs.interpreter.EquipmentTypeInterpreter;

public abstract class EquipmentTypes 
{
	/** Classifier used to specify the type of equipment handled by this class */
	protected EquipmentClassifier classifier;
	/** A reference to the passed equipment type interpreter, which defines a mapping between
	 *  an Equipment's Equipment Type identifier to the Equipment instance itself */
	protected EquipmentTypeInterpreter equipmentTypeInterpreter;
	
	/** Creates a new instance 
	 * 	@param classifier - The classifier used to specify the type of equipment being handled
	 *  @param equipmentTypeInterpreter - A reference to an EquipmentTypeInteperter containing mappings for all custom
	 *         equipment defined in this plugin. */
	public EquipmentTypes (EquipmentClassifier classifier, EquipmentTypeInterpreter equipmentTypeInterpreter)
	{
		this.equipmentTypeInterpreter = equipmentTypeInterpreter;
	}
	
	/** Registers a list of Equipment instances and registers its mapping into the passed interpreter */
	public void registerEquipments (Equipment... equipments)
	{
		for (Equipment equipment : equipments)
		{
			this.equipmentTypeInterpreter.insertWord(equipment.toString(), equipment);
		}
	}
	
	/** Returns the classifier for this equipmenttypes instance */
	public EquipmentClassifier getClassifier ()
	{
		return this.classifier;
	}
}
