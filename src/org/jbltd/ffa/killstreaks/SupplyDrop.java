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
	private static final String[] DESCRIPTION = new String[] { "After 3 kills receive a supply drop with", "3 useful items" };
	private static final Material DISPLAY = Material.TRAPPED_CHEST;

	public SupplyDrop(JavaPlugin plugin, CombatManager manager)
	{
		super(plugin, TITLE, DESCRIPTION, DISPLAY);
		// TODO Auto-generated constructor stub
	}

	@EventHandler
	public void handleKillstreak(CombatKillEvent e)
	{

		Player killer = e.getKiller();

		if (streakHolder.contains(killer.getUniqueId()))
		{

			int k = manager.killStreaks.get(killer.getUniqueId());

			int calc = k / 3;

			if (calc == Math.round(calc))
			{
				ItemStack streakItem = new ItemStack(DISPLAY);
				ItemMeta sm = streakItem.getItemMeta();

				sm.setDisplayName(ChatColor.GOLD + "Supply Drop: Click To Activate");
				streakItem.setItemMeta(sm);

				killer.getInventory().addItem(streakItem);

			}

		}

	}

	@EventHandler
	public void useStreak(PlayerInteractEvent e)
	{

		Player player = e.getPlayer();

		if (streakHolder.contains(player.getUniqueId()))
		{
			ItemStack streakItem = new ItemStack(DISPLAY);
			ItemMeta sm = streakItem.getItemMeta();

			sm.setDisplayName(ChatColor.GOLD + "Supply Drop: Click To Activate");
			streakItem.setItemMeta(sm);

			ItemStack chest = new ItemStack(Material.CHEST);

			if (player.getInventory().contains(streakItem))
			{
				player.getInventory().remove(streakItem);
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
							Firework firework = player.getWorld().spawn(loc, Firework.class);
							FireworkMeta data = (FireworkMeta) firework.getFireworkMeta();
							data.addEffects(FireworkEffect.builder().withColor(Color.YELLOW).with(Type.BALL).build());
							data.setPower(1);
							firework.setFireworkMeta(data);
						}
					}

				}, 60L);

			}

		}

	}

}
