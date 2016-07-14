package org.jbltd.ffa.util;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InventoryOpenCommand implements CommandExecutor
{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{

		if (!(sender instanceof Player))
			return true;

		if (cmd.getName().equalsIgnoreCase("perk"))
		{
			Player player = (Player) sender;

			player.openInventory(InventoryUtil.buildPerkInventory((Player) sender));
		}

		return false;
	}

}
