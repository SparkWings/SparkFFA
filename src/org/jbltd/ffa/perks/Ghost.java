package org.jbltd.ffa.perks;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jbltd.ffa.util.GhostFactory;

public class Ghost extends Perk implements Runnable
{

	private GhostFactory factory;

	private static final String TITLE = "Ghost";
	private static final String[] DESCRIPTION = new String[] { "25% visible to other players" };
	private static final Material DISPLAY = Material.BARRIER;
	
	public Ghost(JavaPlugin plugin)
	{
		super(plugin, TITLE, DESCRIPTION, DISPLAY);

		factory = new GhostFactory(plugin);

	}

	@Override
	public void run()
	{

		for (UUID u : perkHolder)
		{
			Player p = Bukkit.getPlayer(u);
			if (!factory.hasPlayer(p))
			{
				factory.addPlayer(p);
			}
			continue;
		}

	}

}
