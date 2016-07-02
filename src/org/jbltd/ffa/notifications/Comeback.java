package org.jbltd.ffa.notifications;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.jbltd.ffa.events.CombatKillEvent;
import org.jbltd.ffa.managers.CombatManager;
import org.jbltd.ffa.managers.NotificationManager;
import org.jbltd.ffa.util.Notification;
import org.jbltd.ffa.util.NotificationType;

public class Comeback extends Notification
{

	public Comeback(JavaPlugin plugin, CombatManager manager, NotificationManager nmanager)
	{
		super(plugin, manager, nmanager, NotificationType.COMEBACK);
		// TODO Auto-generated constructor stub
	}

	@EventHandler
	public void listen(CombatKillEvent e)
	{
		
		Player killer = e.getKiller();
		
		if (getManager().deathStreaks.get(killer.getUniqueId()) >= 2)
		{
			getNotificationManager().notifications.get(killer.getUniqueId()).add(NotificationType.COMEBACK);
			System.out.println("Added Comeback");
		}

	}

}
