package org.jbltd.ffa.killstreaks;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jbltd.ffa.events.CombatKillEvent;
import org.jbltd.ffa.listeners.CombatListener;
import org.jbltd.ffa.managers.CombatManager;

public class GapplerKillstreak extends Killstreak
{

	public CombatManager manager;
	
	public GapplerKillstreak(JavaPlugin plugin, CombatManager manager)
	{
		super(plugin, "Gappler", new String[] {
				"- For every kill you get a golden apple",
				"- For a 5+ killstreak you get a notch apple"}
		, Material.GOLDEN_APPLE);
		// TODO Auto-generated constructor stub
	}
 
	@EventHandler
	public void giveKillstreak(CombatKillEvent e)
	{
		Player p = e.getKiller();
		
		if(streakHolder.contains(p.getUniqueId()))
		{
			
			if(manager.killStreaks.get(p.getUniqueId()) <= 4 && manager.killStreaks.get(p.getUniqueId()) >= 2)
			{
				p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));
				return;
			}
			else
			{
				p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1));
			}
			
		}
	
	
	}
	
}
