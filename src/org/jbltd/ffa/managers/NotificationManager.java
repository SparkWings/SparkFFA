package org.jbltd.ffa.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jbltd.ffa.util.NotificationType;
import org.jbltd.ffa.util.UpdateEvent;
import org.jbltd.ffa.util.UpdateType;

public class NotificationManager implements Listener
{


	public HashMap<UUID, ArrayList<NotificationType>> notifications = new HashMap<>();

	public NotificationManager()
	{}

	@EventHandler
	public void send(UpdateEvent e)
	{

		if (e.getType() != UpdateType.SEC)
		{
			return;
		}

		for (Player player : Bukkit.getOnlinePlayers())
		{

			ArrayList<NotificationType> playerQueued = notifications.get(player.getUniqueId());

			Iterator<NotificationType> o = playerQueued.iterator();

			while (o.hasNext())
			{
				NotificationType nt = o.next();

				player.removeAchievement(nt.getClientAchievement());
				player.awardAchievement(nt.getClientAchievement());
				player.sendMessage(ChatColor.MAGIC + "||| " + ChatColor.BOLD + nt.getNotifTitle() + " - " + ChatColor.WHITE + nt.getDescription() + ChatColor.MAGIC + " |||");
				player.sendMessage("   \n");
				player.sendMessage("   \n");

				playerQueued.remove(nt);
				continue;
			}
		}
	}
}
