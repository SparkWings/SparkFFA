package org.jbltd.ffa.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jbltd.ffa.events.CombatKillEvent;
import org.jbltd.ffa.managers.CombatManager;
import org.jbltd.ffa.managers.NotificationManager;

public abstract class Notification implements Listener
{
	
	private JavaPlugin plugin;
	private NotificationType type;
	private CombatManager manager;
	private NotificationManager nmanager;

	public static List<Notification> AllNotifications = new ArrayList<>();
	
	public Notification(JavaPlugin plugin, CombatManager manager, NotificationManager nmanager, NotificationType type)
	{
		this.plugin = plugin;
		this.type = type;
		this.manager = manager;
		this.nmanager = nmanager;
		
		getPlugin().getServer().getPluginManager().registerEvents(this, getPlugin());
		AllNotifications.add(this);
	}
	
	public JavaPlugin getPlugin()
	{
		return plugin;
	}
	
	public NotificationType getNotificationType()
	{
		return type;
	}
	
	public CombatManager getManager()
	{
		return manager;
	}
	
	public NotificationManager getNotificationManager()
	{
		return nmanager;
	}
	
	@EventHandler
	public abstract void listen(CombatKillEvent e);
	
}
