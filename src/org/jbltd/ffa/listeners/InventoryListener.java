package org.jbltd.ffa.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jbltd.ffa.killstreaks.Killstreak;
import org.jbltd.ffa.perks.Perk;
import org.jbltd.ffa.util.InventoryUtil;

public class InventoryListener implements Listener
{

	@EventHandler
	public void handle(InventoryClickEvent e)
	{
		if (!(e.getClickedInventory().getName().equalsIgnoreCase(ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Perks")))
		{
			return;
		}

		e.setCancelled(true);

		ItemStack i = e.getCurrentItem();
		
		int count = 0;
		
		for (Perk p : Perk.allPerks)
		{

			if (p.getDisplayMaterial() == i.getType())
			{

				if (!p.perkHolder.contains(e.getWhoClicked().getUniqueId()))
				{
					p.perkHolder.add(e.getWhoClicked().getUniqueId());

					e.getWhoClicked().openInventory(InventoryUtil.buildPerkInventory((Player) e.getWhoClicked()));
					count++;
					
					if(count >= 3)
					{
						InventoryUtil.buildStreakInventory((Player) e.getWhoClicked());
					}
					
					
				} else
				{
					continue;
				}
			} else
				continue;

		}

	}
	
	
	@EventHandler
	public void handleStreaks(InventoryClickEvent e)
	{
		
		if (!(e.getClickedInventory().getName().equalsIgnoreCase(ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Killstreaks")))
		{
			return;
		}

		e.setCancelled(true);

		ItemStack i = e.getCurrentItem();
		
		int count = 0;
		
		for (Killstreak p : Killstreak.allStreaks)
		{

			if (p.getDisplayMaterial() == i.getType())
			{

				if (!p.streakHolder.contains(e.getWhoClicked().getUniqueId()))
				{
					p.streakHolder.add(e.getWhoClicked().getUniqueId());

					e.getWhoClicked().openInventory(InventoryUtil.buildPerkInventory((Player) e.getWhoClicked()));
					count++;
					
					if(count >= 3)
					{
						e.getWhoClicked().closeInventory();
					}
					
					
				} else
				{
					continue;
				}
			} else
				continue;

		}

		
	}

}
