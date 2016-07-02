package org.jbltd.ffa.notifications;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.jbltd.ffa.events.CombatKillEvent;
import org.jbltd.ffa.managers.CombatManager;
import org.jbltd.ffa.util.F;
import org.jbltd.ffa.util.Notification;
import org.jbltd.ffa.util.NotificationType;

public class Trickshotter extends Notification
{

	public Trickshotter(JavaPlugin plugin, CombatManager manager)
	{
		super(plugin, manager, NotificationType.TRICKSHOTTER);
		// TODO Auto-generated constructor stub
	}

	@EventHandler
	public void listen(CombatKillEvent e)
	{
		
		Player killer = e.getKiller();
		
		if (killer.getItemInHand().getType() == Material.BOW)
		{

			F.notifications.get(killer.getUniqueId()).add(NotificationType.TRICKSHOTTER);

		}
		
		
	}

}
