package org.jbltd.ffa.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jbltd.ffa.events.CombatKillEvent;
import org.jbltd.ffa.managers.CombatManager;
import org.jbltd.ffa.util.DatabaseManager;

public class CombatListener implements Listener
{

	private CombatManager manager;

	public CombatListener(CombatManager manager)
	{
		this.manager = manager;
	}

	public CombatManager getManager()
	{
		return manager;
	}

	@EventHandler
	public void onDeath(CombatKillEvent e)
	{

		Player killer = e.getKiller();
		Player killed = e.getKilledPlayer();

		getManager().killStreaks.put(killer.getUniqueId(), getManager().killStreaks.get(killer.getUniqueId()) + 1);

		DatabaseManager.incrementPlayerKills(killer);
		DatabaseManager.incrementPlayerDeaths(killed);
		
		getManager().deathStreaks.put(killed.getUniqueId(), getManager().deathStreaks.get(killed.getUniqueId()) + 1);

		getManager().deathStreaks.put(killer.getUniqueId(), 0);


	}

}
