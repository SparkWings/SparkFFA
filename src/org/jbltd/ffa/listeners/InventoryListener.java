package org.jbltd.ffa.listeners;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.jbltd.ffa.killstreaks.Killstreak;
import org.jbltd.ffa.perks.Perk;
import org.jbltd.ffa.util.InventoryUtil;

public class InventoryListener implements Listener
{

	private HashMap<UUID, Integer> perkCount = new HashMap<>();
	private HashMap<UUID, Integer> ksCount = new HashMap<>();

	@EventHandler
	public void handleJoin(PlayerJoinEvent e)
	{
		perkCount.put(e.getPlayer().getUniqueId(), 0);
		ksCount.put(e.getPlayer().getUniqueId(), 0);
	}

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
					perkCount.put(e.getWhoClicked().getUniqueId(), perkCount.get(e.getWhoClicked().getUniqueId()) + 1);

					System.out.println(perkCount.get(e.getWhoClicked().getUniqueId()));

					if (perkCount.get(e.getWhoClicked().getUniqueId()) >= 3)
					{
						e.getWhoClicked().openInventory(InventoryUtil.buildStreakInventory((Player) e.getWhoClicked()));
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


		for (Killstreak p : Killstreak.allStreaks)
		{

			if (p.getDisplayMaterial() == i.getType())
			{

				if (!p.streakHolder.contains(e.getWhoClicked().getUniqueId()))
				{
					p.streakHolder.add(e.getWhoClicked().getUniqueId());

					e.getWhoClicked().openInventory(InventoryUtil.buildPerkInventory((Player) e.getWhoClicked()));
					ksCount.put(e.getWhoClicked().getUniqueId(), ksCount.get(e.getWhoClicked().getUniqueId()) + 1);

					if (ksCount.get(e.getWhoClicked().getUniqueId()) >= 3)
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
