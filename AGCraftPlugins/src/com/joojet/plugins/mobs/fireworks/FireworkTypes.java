package com.joojet.plugins.mobs.fireworks;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.inventory.ItemStack;

public class FireworkTypes 
{
	/** List of preset fireworks available to distribute */
	private ArrayList <Firework> fireworks;
	/** RNG used by this class  */
	private Random rand;
	
	public FireworkTypes ()
	{
		this.fireworks = new ArrayList <Firework> ();
		rand = new Random();
		this.addFirework(new FreedomRocket());
		this.addFirework(new FreedomStar());
		this.addFirework(new ChandelierBurst());
		this.addFirework(new ChandelierFalling());
		this.addFirework(new PinkMist());
		this.addFirework(new CreeperRocket());
		this.addFirework(new MagicalFirework());
		this.addFirework(new DragonsBreath());
		this.addFirework(new PaintTheSky());
		this.addFirework(new WorldofColors());
		this.addFirework(new FightOn());
		this.addFirework(new USCCreeper());
		this.addFirework(new TheSpiritOfTroy());
		this.addFirework(new TheTrojanSpirit());
	}
	
	public void addFirework (Firework firework)
	{
		this.fireworks.add(firework);
	}
	
	public ItemStack getRandomFirework (int amount, int power)
	{
		return this.fireworks.get(rand.nextInt(this.fireworks.size())).generateFirework(amount, power);
	}
}
