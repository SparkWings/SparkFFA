package org.jbltd.ffa.killstreaks;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jbltd.ffa.events.CombatKillEvent;
import org.jbltd.ffa.managers.CombatManager;

public class SupplyDrop extends Killstreak
{

	public CombatManager manager;
	private HashSet<ItemStack> _toRemove = new HashSet<>();
	private static final String TITLE = "Supply Drop";
	private static final String[] DESCRIPTION = new String[]
	{ "After 3 kills receive a supply drop with", "3 useful items" };
	private static final Material DISPLAY = Material.TRAPPED_CHEST;

	public SupplyDrop(JavaPlugin plugin, CombatManager manager)
	{
		super(plugin, TITLE, DESCRIPTION, DISPLAY);

		this.manager = manager;
	}

	@EventHandler
	public void handleKillstreak(CombatKillEvent e)
	{

		Player killer = e.getKiller();

		if (streakHolder.contains(killer.getUniqueId()))
		{

			int k = manager.killStreaks.get(killer.getUniqueId());

			if (k >= 3)
			{
				ItemStack streakItem = new ItemStack(DISPLAY);
				ItemMeta sm = streakItem.getItemMeta();

				sm.setDisplayName(ChatColor.GOLD + "Supply Drop: Drop To Activate");
				streakItem.setItemMeta(sm);

				killer.getInventory().addItem(streakItem);

			}

		}

	}

	@EventHandler
	public void useStreak(PlayerDropItemEvent e)
	{


		Player player = e.getPlayer();

		if (e.getItemDrop().getItemStack().getType() != Material.TRAPPED_CHEST)
		{
			return;
		}
		
		e.getItemDrop().remove();
		
		if (streakHolder.contains(player.getUniqueId()))
		{

			ItemStack chest = new ItemStack(Material.CHEST);

			player.getWorld().dropItemNaturally(player.getLocation(), chest).setPickupDelay(Integer.MAX_VALUE);
			_toRemove.add(chest);
			Location loc = player.getLocation().clone();

			Bukkit.getScheduler().scheduleSyncDelayedTask(getPlugin(), new Runnable()
			{

				@Override
				public void run()
				{

					for (ItemStack i : _toRemove)
					{
						_toRemove.remove(i);
						i.setType(Material.AIR);
						Firework firework = player.getWorld().spawn(loc.subtract(0, 1, 0), Firework.class);
						FireworkMeta data = (FireworkMeta) firework.getFireworkMeta();
						data.addEffects(FireworkEffect.builder().withColor(Color.YELLOW).with(Type.BALL).build());
						data.setPower(0);
						firework.setFireworkMeta(data);

						Firework firework2 = player.getWorld().spawn(loc.subtract(0, 1, 0), Firework.class);
						FireworkMeta data2 = (FireworkMeta) firework2.getFireworkMeta();
						data2.addEffects(FireworkEffect.builder().withColor(Color.RED).with(Type.BALL).build());
						data2.setPower(0);
						firework2.setFireworkMeta(data);

						Firework firework3 = player.getWorld().spawn(loc.subtract(0, 1, 0), Firework.class);
						FireworkMeta data3 = (FireworkMeta) firework3.getFireworkMeta();
						data3.addEffects(FireworkEffect.builder().withColor(Color.GREEN).with(Type.BALL).build());
						data3.setPower(0);

						firework3.setFireworkMeta(data);
					}
				}

			}, 60L);

		}

	}

}
