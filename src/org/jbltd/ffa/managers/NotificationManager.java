package org.jbltd.ffa.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jbltd.ffa.util.NotificationType;
import org.jbltd.ffa.util.UpdateEvent;
import org.jbltd.ffa.util.UpdateType;

public class NotificationManager implements Listener
{

	public HashMap<UUID, ArrayList<NotificationType>> notifications = new HashMap<>();

	public NotificationManager()
	{}

	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{

		notifications.put(e.getPlayer().getUniqueId(), new ArrayList<>());
	}

	@EventHandler
	public void send(UpdateEvent e)
	{

		if (e.getType() != UpdateType.SEC)
		{
			return;
		}

		for (Player player : Bukkit.getOnlinePlayers())
		{

			if (notifications.get(player.getUniqueId()).size() >= 1)
			{

				NotificationType nt = notifications.get(player.getUniqueId()).get(0);			

				for (Achievement a : Achievement.values())
				{
					player.removeAchievement(a);
				}
				player.awardAchievement(nt.getClientAchievement());

				player.sendMessage(ChatColor.MAGIC + "||| " + ChatColor.BOLD + nt.getNotifTitle() + " - " + ChatColor.WHITE + nt.getDescription() + ChatColor.MAGIC + " |||");
				
				notifications.get(player.getUniqueId()).remove(nt);
				continue;
			}
		}
	}
}
