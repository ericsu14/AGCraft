package com.joojet.plugins.mobs.metadata;

import java.util.UUID;

import org.bukkit.NamespacedKey;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;

public class BossMetadata extends AbstractMetadata<UUID> 
{
	public static final String BOSS_TAG = "boss-tag";
	
	public BossMetadata(UUID type) 
	{
		super(BOSS_TAG, type);
	}
	
	public static NamespacedKey generateGenericNamespacedKey ()
	{
		return new NamespacedKey (AGCraftPlugin.plugin, BOSS_TAG);
	}
}
