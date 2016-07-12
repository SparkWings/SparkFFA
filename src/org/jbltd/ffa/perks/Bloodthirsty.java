package org.jbltd.ffa.perks;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jbltd.ffa.events.CombatKillEvent;

public class Bloodthirsty extends Perk
{

	private static final String TITLE = "Bloodthirsty";
	private static final String[] DESCRIPTION = new String[] { "Receive regeneration for 2 seconds after a kill" };
	private static final Material DISPLAY = Material.POTION;
	
	public Bloodthirsty(JavaPlugin plugin)
	{
		super(plugin, TITLE, DESCRIPTION, DISPLAY);
		// TODO Auto-generated constructor stub
	}

	@EventHandler
	public void perk(CombatKillEvent e)
	{

		Player killer = e.getKiller();

		if (perkHolder.contains(killer.getUniqueId()))
		{
			killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 0));
			killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 10F, 2F);
		}

	}

}
