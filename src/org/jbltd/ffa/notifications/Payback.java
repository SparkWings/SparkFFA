package org.jbltd.ffa.notifications;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.jbltd.ffa.events.CombatKillEvent;
import org.jbltd.ffa.managers.CombatManager;
import org.jbltd.ffa.managers.NotificationManager;
import org.jbltd.ffa.util.Notification;
import org.jbltd.ffa.util.NotificationType;

public class Payback extends Notification
{

	public Payback(JavaPlugin plugin, CombatManager manager, NotificationManager nmanager)
	{
		super(plugin, manager, nmanager, NotificationType.PAYBACK);
		// TODO Auto-generated constructor stub
	}

	@EventHandler
	public void listen(CombatKillEvent e)
	{

		Player killer = e.getKiller();
		Player killed = e.getKilledPlayer();
		
		if (getManager().lastKill.get(killed.getUniqueId()) == killer.getUniqueId())
		{
			getNotificationManager().notifications.get(killer.getUniqueId()).add(NotificationType.PAYBACK);
			System.out.println("Added Payback");
		} else
		{

		}

		getManager().lastKill.put(killed.getUniqueId(), killer.getUniqueId());

	}

}
