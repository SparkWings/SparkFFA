package org.jbltd.ffa.util;

import org.bukkit.Achievement;
import org.bukkit.ChatColor;

public enum NotificationType
{

	/**
	 * Notif listener
	 */

	KINGSLAYER("Kingslayer", "Kill the #1 Ranked Player", Achievement.BUILD_SWORD), ASSIST("Assisted Kill", "Assisted someone in killing a player", Achievement.DIAMONDS_TO_YOU), PAYBACK("Payback",
			"Kill the player that previously killed you", Achievement.GET_DIAMONDS), BUZZKILL("Buzzkill", "Kill a player that was about to start a killstreak", Achievement.KILL_WITHER), COMEBACK("Comeback",
					"Get a kill after 2 or more deaths in a row.", Achievement.OVERPOWERED), TRICKSHOTTER("Trickshotter", "Kill someone with a bow from more than 30 blocks", Achievement.SNIPE_SKELETON), SURVIVOR("Survivor",
							"Kill someone while you are low.", Achievement.FLY_PIG);

	private String title;
	private String description;
	private Achievement clientA;

	NotificationType(String title, String description, Achievement clientA)
	{
		this.title = title;
		this.description = description;
		this.clientA = clientA;
	}

	public String getNotifTitle()
	{
		return ChatColor.LIGHT_PURPLE + title + ChatColor.RESET;
	}

	public String getDescription()
	{
		return description;
	}

	public Achievement getClientAchievement()
	{
		return clientA;
		
	}
	
}
