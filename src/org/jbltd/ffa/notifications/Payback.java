package org.jbltd.ffa.notifications;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jbltd.ffa.events.CombatKillEvent;
import org.jbltd.ffa.managers.CombatManager;
import org.jbltd.ffa.util.F;
import org.jbltd.ffa.util.Notification;
import org.jbltd.ffa.util.NotificationType;

public class Payback extends Notification
{

	public Payback(JavaPlugin plugin, CombatManager manager)
	{
		super(plugin, manager, NotificationType.PAYBACK);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void listen(CombatKillEvent e)
	{

		Player killer = e.getKiller();
		Player killed = e.getKilledPlayer();
		
		if (getManager().lastKill.get(killed.getUniqueId()) == killer.getUniqueId())
		{
			F.notifications.get(killer.getUniqueId()).add(NotificationType.PAYBACK);
			System.out.println("Added Payback");
		} else
		{

		}

		getManager().lastKill.put(killed.getUniqueId(), killer.getUniqueId());

	}

}
