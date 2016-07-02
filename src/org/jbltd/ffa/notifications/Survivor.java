package org.jbltd.ffa.notifications;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.jbltd.ffa.events.CombatKillEvent;
import org.jbltd.ffa.managers.CombatManager;
import org.jbltd.ffa.managers.NotificationManager;
import org.jbltd.ffa.util.Notification;
import org.jbltd.ffa.util.NotificationType;

public class Survivor extends Notification
{

	public Survivor(JavaPlugin plugin, CombatManager manager, NotificationManager nmanager)
	{
		super(plugin, manager, nmanager, NotificationType.SURVIVOR);
	}

	@EventHandler
	public void listen(CombatKillEvent e)
	{
		
		Player killer = e.getKiller();	
		
		// Survivor
		if (killer.getHealth() <= 6.0D)
		{
			getNotificationManager().notifications.get(killer.getUniqueId()).add(NotificationType.SURVIVOR);
			System.out.println("Added Survivor");
		}

	}

}
