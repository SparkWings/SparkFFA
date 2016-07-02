package org.jbltd.ffa.killstreaks;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Killstreak implements Listener
{

	private JavaPlugin plugin;
	private String title;
	private String[] description;
	private Material display;

	public static List<Killstreak> allStreaks = new ArrayList<>();

	public List<UUID> streakHolder = new ArrayList<UUID>();

	public Killstreak(JavaPlugin plugin, String title, String[] description, Material display)
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
	
}
