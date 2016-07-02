package org.jbltd.ffa.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
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
		for (Perk p : Perk.allPerks)
		{

			if (p.getDisplayMaterial() == i.getType())
			{

				if (!p.perkHolder.contains(e.getWhoClicked().getUniqueId()))
				{
					p.perkHolder.add(e.getWhoClicked().getUniqueId());

					e.getWhoClicked().openInventory(InventoryUtil.buildPerkInventory((Player) e.getWhoClicked()));
				} else
				{
					continue;
				}
			} else
				continue;

		}

	}

}
