package org.jbltd.ffa.killstreaks;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jbltd.ffa.events.CombatKillEvent;
import org.jbltd.ffa.managers.CombatManager;

public class GapplerKillstreak extends Killstreak
{

	public CombatManager manager;
	private static final String NAME = "Gappler";
	private static final String[] DESCRIPTION = new String[]
	{ "- For every kill you get a golden apple", "- For a 5+ killstreak you get a notch apple" };

	private static final Material DISPLAY = Material.GOLDEN_APPLE;
	
	public GapplerKillstreak(JavaPlugin plugin, CombatManager manager)
	{
		super(plugin, NAME, DESCRIPTION, DISPLAY);
		// TODO Auto-generated constructor stub
	}

	@EventHandler
	public void giveKillstreak(CombatKillEvent e)
	{
		Player p = e.getKiller();

		if (streakHolder.contains(p.getUniqueId()))
		{

			if (manager.killStreaks.get(p.getUniqueId()) <= 4 && manager.killStreaks.get(p.getUniqueId()) >= 2)
			{
				p.getInventory().addItem(new ItemStack(DISPLAY));
				return;
			} else
			{
				p.getInventory().addItem(new ItemStack(DISPLAY, 1, (short) 1));
			}

		}

	}

}
