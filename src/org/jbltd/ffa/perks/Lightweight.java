package org.jbltd.ffa.perks;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Lightweight extends Perk implements Runnable
{

	public Lightweight(JavaPlugin plugin)
	{
		super(plugin, "Lightweight", new String[]
		{ "Move 7% faster", "Take no fall damage" }, Material.FEATHER);
	}

	@Override
	public void run()
	{

		for (UUID u : perkHolder)
		{
			Player p = Bukkit.getPlayer(u);

			if (!p.getActivePotionEffects().isEmpty())
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
			}

		}

	}

	@EventHandler
	public void fallDamage(EntityDamageEvent e)
	{

		if (!(e.getEntity() instanceof Player))
			return;

		if (!perkHolder.contains(e.getEntity().getUniqueId()))
		{
			return;
		} else
		{

			if (e.getCause() == DamageCause.FALL)
			{
				e.setDamage(0.0D);
				e.setCancelled(true);
			}

		}

	}

}
