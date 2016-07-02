package org.jbltd.ffa.notifications;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jbltd.ffa.events.CombatKillEvent;
import org.jbltd.ffa.managers.CombatManager;
import org.jbltd.ffa.util.F;
import org.jbltd.ffa.util.Notification;
import org.jbltd.ffa.util.NotificationType;

public class Assist extends Notification
{

	public Assist(JavaPlugin plugin, CombatManager manager)
	{
		super(plugin, manager, NotificationType.ASSIST);
		// TODO Auto-generated constructor stub
	}

	@EventHandler
	public void addCombatee(EntityDamageByEntityEvent e)
	{

		if (!(e.getEntity() instanceof Player))
		{
			return;
		}

		Player attacked = (Player) e.getEntity();

		if (!(e.getDamager() instanceof Player))
		{
			return;
		}

		Player attacker = (Player) e.getDamager();

		if (!getManager().combatData.containsKey(attacked.getUniqueId()))
		{
			getManager().combatData.put(attacked.getUniqueId(), Arrays.asList(attacker.getUniqueId()));
		} else
		{
			List<UUID> dat = getManager().combatData.get(attacked.getUniqueId());

			if (dat.contains(attacker.getUniqueId()))
			{
				return;
			}

			dat.add(attacker.getUniqueId());

		}

	}
	
	@EventHandler
	public void listen(CombatKillEvent e)
	{
		Player killer = e.getKiller();
		Player killed = e.getKilledPlayer();
		
		int i = 0;
		
		for (UUID u : getManager().combatData.get(killed.getUniqueId()))
		{
			Player k = Bukkit.getPlayer(u);
			if (k != killer)
			{
				F.notifications.get(killer.getUniqueId()).add(NotificationType.ASSIST);
			}

			i++;
			continue;
		}

		getManager().combatData.remove(killed.getUniqueId());

		Bukkit.broadcastMessage(F.info("Death", false, killed.getName() + " was killed by " + killer.getName() + ((i - 1) < 1 ? "" : (" + " + (i - 1)))));

		
	}

}
