package org.jbltd.ffa.perks;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BruteStrength extends Perk
{

	private static final String TITLE = "Brute Strength";
	private static final String[] DESCRIPTION = new String[] { "Deal 15% more damage" };
	private static final Material DISPLAY = Material.DIAMOND_AXE;
	
	public BruteStrength(JavaPlugin plugin)
	{
		super(plugin, TITLE, DESCRIPTION, DISPLAY);
		// TODO Auto-generated constructor stub
	}

	@EventHandler
	public void listen(EntityDamageByEntityEvent e)
	{

		if (!(e.getEntity() instanceof Player))
			return;

		if (!(e.getDamager() instanceof Player))
			return;

		Player damager = (Player) e.getDamager();

		if (!perkHolder.contains(damager.getUniqueId()))
		{
			double dmg = e.getDamage() % 15;

			e.setDamage(e.getDamage() + dmg);
		}

	}

}
