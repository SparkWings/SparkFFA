package org.jbltd.ffa.managers;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jbltd.ffa.Main;
import org.jbltd.ffa.events.CombatKillEvent;

public class CombatManager implements Listener
{

	public HashMap<UUID, List<UUID>> combatData = new HashMap<>();
	public HashMap<UUID, UUID> lastKill = new HashMap<>();
	public HashMap<UUID, Integer> killStreaks = new HashMap<>();
	public HashMap<UUID, Integer> deathStreaks = new HashMap<>();

	public Main plugin;

	public CombatManager(Main main)
	{
		this.plugin = main;
	}

	@EventHandler
	public void onJoinSetup(PlayerJoinEvent e)
	{
		deathStreaks.put(e.getPlayer().getUniqueId(), 0);
		killStreaks.put(e.getPlayer().getUniqueId(), 0);
		lastKill.put(e.getPlayer().getUniqueId(), null);
	}

	@EventHandler
	public void removeLeave(PlayerQuitEvent e)
	{

		combatData.remove(e.getPlayer().getUniqueId());
		lastKill.remove(e.getPlayer().getUniqueId());
		killStreaks.remove(e.getPlayer().getUniqueId());
		deathStreaks.remove(e.getPlayer().getUniqueId());

	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onDeath(PlayerDeathEvent e)
	{

		e.setDeathMessage(null);

		if (!(e.getEntity() instanceof Player))
		{
			return;
		}

		Player kld = (Player) e.getEntity();

		if (!(kld.getKiller() instanceof Player))
		{
			return;
		}

		Player klr = (Player) kld.getKiller();

		Bukkit.getServer().getPluginManager().callEvent(new CombatKillEvent(klr, kld));
	}

}
