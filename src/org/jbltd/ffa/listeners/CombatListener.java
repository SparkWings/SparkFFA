package org.jbltd.ffa.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jbltd.ffa.events.CombatKillEvent;
import org.jbltd.ffa.managers.CombatManager;
import org.jbltd.ffa.util.F;
import org.jbltd.ffa.util.NotificationType;

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

		// Killstreaks
		getManager().killStreaks.put(killer.getUniqueId(), getManager().killStreaks.get(killer.getUniqueId()) + 1);

		// Deathstreaks
		getManager().deathStreaks.put(killed.getUniqueId(), getManager().deathStreaks.get(killed.getUniqueId()) + 1);

		if (getManager().deathStreaks.get(killer.getUniqueId()) >= 2)
		{
			F.notifications.get(killer.getUniqueId()).add(NotificationType.COMEBACK);
			System.out.println("Added Comeback");
		}

		getManager().deathStreaks.put(killer.getUniqueId(), 0);


		F.sendNotificationMessages(killer);

	}

}
