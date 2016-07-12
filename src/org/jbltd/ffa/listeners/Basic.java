package org.jbltd.ffa.listeners;

import org.bukkit.Achievement;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jbltd.ffa.util.DatabaseManager;
import org.jbltd.ffa.util.InventoryUtil;

import de.inventivegames.rpapi.ResourcePackAPI;

@SuppressWarnings("deprecation")
public class Basic implements Listener
{


	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		
		ResourcePackAPI.setResourcepack(e.getPlayer(), "http://download857.mediafire.com/4k109akhamng/2cy63bbfx5h263i/pack.zip");
		
		for(Achievement a : Achievement.values())
		{
			e.getPlayer().awardAchievement(a);
		}

		try
		{
			if(!DatabaseManager.hasAccount(e.getPlayer()))
			{
				DatabaseManager.createAccount(e.getPlayer());
			}
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "]" + " " + e.getPlayer().getName());

		InventoryUtil.equip(e.getPlayer());

	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e)
	{

		e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "]" + " " + e.getPlayer().getName());

	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e)
	{

		e.setFormat(ChatColor.YELLOW + e.getPlayer().getName() + ChatColor.GRAY + ": " + ChatColor.WHITE + e.getMessage());

	}

	@EventHandler
	public void handleMobs(CreatureSpawnEvent e)
	{
		if (!(e.getSpawnReason() != SpawnReason.CUSTOM))
		{
			e.setCancelled(true);
		}
	}

}
