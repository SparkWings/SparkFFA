package org.jbltd.ffa.perks;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Kevlar extends Perk
{

	private static final String TITLE = "Kevlar";
	private static final String[] DESCRIPTION = new String[] { "Take 5% less damage", "Take no damage from bows" };
	private static final Material DISPLAY = Material.OBSIDIAN;
	
	public Kevlar(JavaPlugin plugin)
	{
		super(plugin, TITLE, DESCRIPTION, DISPLAY);

	}

	@EventHandler
	public void listen(EntityDamageByEntityEvent e)
	{

		if (!(e.getEntity() instanceof Player))
		{
			return;
		}

		Player player = (Player) e.getEntity();

		if (!perkHolder.contains(player.getUniqueId()))
		{
			return;
		}

		// Arrow
		if (e.getDamager() instanceof Arrow)
		{
			e.setDamage(0.0D);
			e.setCancelled(true);
		}

		// Less damage
		double damage = (e.getDamage() % 5);
		e.setDamage(e.getDamage() - damage);

	}

}
