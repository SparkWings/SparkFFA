package org.jbltd.ffa.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.inventivetalent.rpapi.Status;
import org.jbltd.ffa.util.InventoryUtil;

import de.inventivegames.rpapi.ResourcePackStatusEvent;

@SuppressWarnings("deprecation")
public class ResourcepackListener implements Listener
{
	
	@EventHandler
	public void listen(ResourcePackStatusEvent e)
	{
		Status s = e.getStatus();
		if(s == Status.FAILED_DOWNLOAD)
		{
			e.getPlayer().kickPlayer(ChatColor.BLUE+"Game> "+ChatColor.RED+"There was a problem downloading our resourcepack... Try again?");
		}
		if(s == Status.DECLINED)
		{
			e.getPlayer().kickPlayer(ChatColor.BLUE+"Game> "+ChatColor.RED+"You must accept our resourcepack to play FFA!");
		}
		if(s == Status.SUCCESSFULLY_LOADED)
		{
			InventoryUtil.buildPerkInventory(e.getPlayer());
		}
		if(s == Status.ACCEPTED)
		{
			InventoryUtil.buildPerkInventory(e.getPlayer());
		}
	}
	
	
}
