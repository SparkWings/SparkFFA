package org.jbltd.ffa.util;

import org.bukkit.ChatColor;

public class F
{


	public static String info(String head, boolean logSafe, String message)
	{

		if (!logSafe)
		{
			return ChatColor.GRAY + head + ChatColor.DARK_GRAY + "> " + ChatColor.GREEN + message;
		} else
		{
			return head + "> " + message;
		}

	}


}
