package org.jbltd.ffa.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jbltd.ffa.Main;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class F
{

	public static HashMap<UUID, ArrayList<NotificationType>> notifications = new HashMap<>();

	private static Main plugin;

	public F(Main main)
	{
		plugin = main;
	}

	public static String info(String head, boolean logSafe, String message)
	{

		if (!logSafe)
		{
			return ChatColor.GRAY + head + ChatColor.DARK_GRAY + "> " + ChatColor.GREEN + message;
		} else
		{
			return head + "> " + message;
		}

	}

	public static void sendNotificationMessages(Player player)
	{

		ArrayList<NotificationType> playerQueued = notifications.get(player.getUniqueId());

		for (int i = 0; i < playerQueued.size(); i++)
		{

			NotificationType nt = playerQueued.get(i);

			IChatBaseComponent component = ChatSerializer
					.a("{\"text\":\"" + ChatColor.BOLD + nt.getNotifTitle() + ChatColor.RESET + " - " + ChatColor.GREEN + nt.getDescription() + "\"}");
			PacketPlayOutChat packet = new PacketPlayOutChat(component, (byte) 2);

			((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);

			player.removeAchievement(nt.getClientAchievement());
			player.awardAchievement(nt.getClientAchievement());
			player.sendMessage(ChatColor.MAGIC + "||| " + ChatColor.BOLD + nt.getNotifTitle() + " - " + ChatColor.WHITE + nt.getDescription() + ChatColor.MAGIC + " |||");
			player.sendMessage("   \n");
			player.sendMessage("   \n");

			playerQueued.remove(nt);
			System.out.println("Removed type");

			System.out.println("Scheduling task");
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
			{

				@Override
				public void run()
				{

				}
			}, 80L);

		}

	}

}
