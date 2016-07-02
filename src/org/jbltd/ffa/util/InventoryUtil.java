package org.jbltd.ffa.util;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jbltd.ffa.killstreaks.Killstreak;
import org.jbltd.ffa.perks.Perk;

import net.md_5.bungee.api.ChatColor;

public class InventoryUtil
{

	public static void equip(Player player)
	{

		player.getInventory().clear();

		ItemStack helm = new ItemStack(Material.DIAMOND_HELMET);
		ItemStack chest = new ItemStack(Material.DIAMOND_HELMET);
		ItemStack legs = new ItemStack(Material.DIAMOND_HELMET);
		ItemStack boots = new ItemStack(Material.DIAMOND_HELMET);

		player.getInventory().setHelmet(helm);
		player.getInventory().setChestplate(chest);
		player.getInventory().setLeggings(legs);
		player.getInventory().setBoots(boots);

		player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD), new ItemStack(Material.FISHING_ROD), new ItemStack(Material.COOKED_BEEF, 64));

	}

	public static Inventory buildPerkInventory(Player player)
	{

		Inventory inv = Bukkit.getServer().createInventory(null, 54, ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Perks");

		int i = 0;

		for (Perk p : Perk.allPerks)
		{

			ItemStack m = new ItemStack(p.getDisplayMaterial());
			ItemMeta im = m.getItemMeta();
			im.setDisplayName(p.getTitle());
			im.setLore(Arrays.asList(p.getDescription()));

			if (p.perkHolder.contains(player.getUniqueId()))
			{
				im.addEnchant(Enchantment.DURABILITY, 1, false);
			}

			m.setItemMeta(im);

			inv.setItem(i++, m);
		}

		return inv;
	}

	
	public static Inventory buildStreakInventory(Player player)
	{

		Inventory inv = Bukkit.getServer().createInventory(null, 54, ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Killstreaks");

		int i = 0;

		for (Killstreak p : Killstreak.allStreaks)
		{

			ItemStack m = new ItemStack(p.getDisplayMaterial());
			ItemMeta im = m.getItemMeta();
			im.setDisplayName(p.getTitle());
			im.setLore(Arrays.asList(p.getDescription()));

			if (p.streakHolder.contains(player.getUniqueId()))
			{
				im.addEnchant(Enchantment.DURABILITY, 1, false);
			}

			m.setItemMeta(im);

			inv.setItem(i++, m);
		}

		return inv;
	}

	
}
