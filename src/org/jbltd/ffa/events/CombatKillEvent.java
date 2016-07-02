package org.jbltd.ffa.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CombatKillEvent extends Event
{

	private static final HandlerList handlers = new HandlerList();

	private Player killer;
	private Player killed;

	public CombatKillEvent(Player killer, Player killed)
	{
		this.killer = killer;
		this.killed = killed;
	}

	@Override
	public HandlerList getHandlers()
	{
		// TODO Auto-generated method stub
		return handlers;
	}

	public static HandlerList getHandlerList()
	{
		return handlers;
	}

	public Player getKiller()
	{
		return killer;
	}

	public Player getKilledPlayer()
	{

		return killed;

	}

}
