package com.joojet.plugins.utility.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;

import com.joojet.plugins.agcraft.config.AbstractConfigFile;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.utility.enums.JunkClassifier;
import com.joojet.plugins.utility.interpreter.MaterialInterpreter;

public class JunkItemConfig extends AbstractConfigFile
{
	/** Material Item interpreter used to convert material strings to enums */
	protected MaterialInterpreter materialInterpreter;
	
	public JunkItemConfig ()
	{
		super ("junk-items.yaml");
		this.materialInterpreter = new MaterialInterpreter ();
	}
	
	/** Generates a new material and junk classifier mapping with the current values read by the config file */
	public HashMap <Material, JunkClassifier> generateMapping () throws RuntimeException
	{
		String currKey;
		List<String> currVal;
		HashMap <Material, JunkClassifier> mapping = new HashMap <Material, JunkClassifier> ();
		
		for (JunkClassifier classifier : JunkClassifier.values())
		{
			currKey = classifier.getKey();
			if (this.configFileValues.containsKey(currKey))
			{
				currVal = this.getValueAsList(currKey);
				Material[] materials = this.convertMaterialNames(currVal);
				for (Material material : materials)
				{
					mapping.put(material, classifier);
					AGCraftPlugin.logger.info ("Added " + material.toString() + " to the junk item classifier, " + currKey);
				}
			}
		}
		
		return mapping;
	}
	
	/** Populates the config file with default junk classifier items */
	@Override
	protected HashMap<String, Object> createConfigFileContents() 
	{
		HashMap <String, Object> items = new HashMap <String, Object> ();
		
		// Common
		this.addMaterialstoClassifier(items, JunkClassifier.COMMON, Material.ROTTEN_FLESH, Material.BONE, Material.STRING,
				Material.WHEAT_SEEDS, Material.GREEN_DYE, Material.PAPER, Material.POISONOUS_POTATO);
		// Natural Blocks
		this.addMaterialstoClassifier(items, JunkClassifier.NATURAL, Material.DIRT, Material.GRAVEL, Material.SAND, Material.CACTUS);
		// Stones
		this.addMaterialstoClassifier(items, JunkClassifier.STONE, Material.STONE, Material.COBBLESTONE, Material.DIORITE, Material.ANDESITE, Material.GRANITE);
		// Armor
		this.addMaterialstoClassifier(items, JunkClassifier.ARMOR, Material.LEATHER_BOOTS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET,
				Material.LEATHER_LEGGINGS, Material.GOLDEN_BOOTS, Material.GOLDEN_LEGGINGS, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_HELMET,
				Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET);
		// Brewing
		this.addMaterialstoClassifier(items, JunkClassifier.BREWING, Material.SPIDER_EYE);
		// Weapons (unenchanted)
		this.addMaterialstoClassifier(items, JunkClassifier.WEAPONS, Material.BOW, Material.CROSSBOW, Material.STONE_SWORD, Material.WOODEN_SWORD,
				Material.GOLDEN_SWORD);
		// Nether
		this.addMaterialstoClassifier(items, JunkClassifier.NETHER, Material.NETHERRACK);
		
		return items;
	}
	
	/** Converts a list of material names to a list of materials
	 *  @param materialNames - A list of material names as their string
	 *  @throws RuntimeException - If there exists an invalid material type listed in the config file*/
	protected Material[] convertMaterialNames (List <String> materialNames) throws RuntimeException
	{
		ArrayList <Material> materials = new ArrayList <Material> ();
		
		for (String materialStr : materialNames)
		{
			Material material = this.materialInterpreter.searchTrie(materialStr);
			if (material != null)
			{
				materials.add(material);
			}
			else
			{
				throw new RuntimeException ("Error: " + materialStr + " is not a valid material type!");
			}
		}
		
		return materials.stream().toArray(Material[]::new);
	}
	
	/** Appends a list of Material types to a junk classifier */
	protected void addMaterialstoClassifier (HashMap <String, Object> items, JunkClassifier classifier, Material... materials)
	{
		// Converts the material enums to an array of strings using their toString() method
		int materialSize = materials.length;
		String materialNames [] = new String[materialSize];
		
		for (int i = 0; i < materialSize; ++i)
		{
			materialNames[i] = materials[i].toString();
		}
		
		// Adds the classifier and materialNames entry to the hashmap
		items.put(classifier.getKey(), materialNames);
	}
}
