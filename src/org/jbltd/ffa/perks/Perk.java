package org.jbltd.ffa.perks;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Perk implements Listener
{

	private JavaPlugin plugin;
	private String title;
	private String[] description;
	private Material display;

	public static List<Perk> allPerks = new ArrayList<>();

	public List<UUID> perkHolder = new ArrayList<UUID>();

	public Perk(JavaPlugin plugin, String title, String[] description, Material display)
	{
		this.plugin = plugin;
		this.title = title;
		this.description = description;
		this.display = display;

		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);

	}

	public JavaPlugin getPlugin()
	{
		return plugin;
	}

	public String getTitle()
	{
		return title;

	}

	public String[] getDescription()
	{
		return description;
	}

	public Material getDisplayMaterial()
	{
		return display;
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e)
	{

		if (perkHolder.contains(e.getPlayer().getUniqueId()))
		{
			perkHolder.remove(e.getPlayer().getUniqueId());

		}

	}

}
