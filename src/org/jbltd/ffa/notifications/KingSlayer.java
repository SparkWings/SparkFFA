package org.jbltd.ffa.notifications;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.jbltd.ffa.events.CombatKillEvent;
import org.jbltd.ffa.managers.CombatManager;
import org.jbltd.ffa.util.F;
import org.jbltd.ffa.util.Notification;
import org.jbltd.ffa.util.NotificationType;

public class KingSlayer extends Notification
{

	public KingSlayer(JavaPlugin plugin, CombatManager manager)
	{
		super(plugin, manager, NotificationType.KINGSLAYER);
		// TODO Auto-generated constructor stub
	}

	@EventHandler
	public void listen(CombatKillEvent e)
	{
		List<UUID> order = new ArrayList<>();

		Player killed = e.getKilledPlayer();
		Player killer = e.getKiller();
		
		this.getManager().killStreaks.forEach((key, value) -> order.add(key));

		Collections.sort(order);

		if (order.isEmpty())
		{
			return;
		}

		if (order.get(0) != null)
		{

			Player uno = Bukkit.getPlayer(order.get(0));
			if (killed == uno)
			{
				F.notifications.get(killer.getUniqueId()).add(NotificationType.KINGSLAYER);
				System.out.println("Added king");
			}
		}
		
	}

}
