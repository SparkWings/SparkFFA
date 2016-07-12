package org.jbltd.ffa.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class DatabaseManager
{

	private JavaPlugin plugin;
	public static MySQL DB;

	public DatabaseManager(JavaPlugin plugin)
	{
		this.plugin = plugin;

	}

	public void setupDB() throws SQLException, ClassNotFoundException
	{
		DB = new MySQL(plugin, "127.0.0.1", "3306", "testdb", "root", "");
		DB.openConnection();

		Statement a = DB.getConnection().createStatement();
		a.executeUpdate("CREATE TABLE IF NOT EXISTS `FFA` (`id` INT NOT NULL AUTO_INCREMENT, `UUID` MEDIUMTEXT, `Kills` INT, `Deaths` INT, PRIMARY KEY (`id`));");
		a.executeUpdate("CREATE TABLE IF NOT EXISTS `APIKEYS` (`id` INT NOT NULL AUTO_INCREMENT, `Key` MEDIUMTEXT, `Calls` INT, PRIMARY KEY (`id`));");

	}

	public static boolean hasAccount(Player player) throws Exception
	{

		if (!DB.checkConnection())
		{
			DB.openConnection();
		}

		Statement s = DB.getConnection().createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM `FFA` WHERE `UUID`='" + player.getUniqueId().toString() + "';");

		if (!rs.next())
		{
			return false;
		} else
		{
			return true;
		}

	}

	public static void createAccount(Player player) throws Exception
	{

		if (!DB.checkConnection())
		{
			DB.openConnection();
		}

		String name = player.getName();
		String uuid = player.getUniqueId().toString();

		Statement s = DB.getConnection().createStatement();
		s.executeUpdate("INSERT INTO `FFA` (`UUID`,`Kills`,`Deaths`) VALUES ('" + uuid + "',0,0);");

	}

	public static UUID getPlayerUUID(Player player)
	{

		return player.getUniqueId();

	}

	public static int getPlayerKills(Player player) throws Exception
	{

		if (!hasAccount(player))
		{
			throw new IllegalArgumentException("Player does not have an account in the database!");

		}

		if (!DB.checkConnection())
		{
			DB.openConnection();
		}

		Statement s = DB.getConnection().createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM `FFA` WHERE `UUID`='" + player.getUniqueId().toString() + "';");

		if (!rs.next())
		{
			return rs.getInt("Kills");
		} else
		{
			return 0;
		}

	}

	public static void incrementPlayerKills(Player player)
	{
		try
		{
			if (!DB.checkConnection())
			{
				DB.openConnection();
			}

			String uuid = player.getUniqueId().toString();

			Statement s = DB.getConnection().createStatement();

			s.executeUpdate("UPDATE `FFA` SET `Kills`='" + (getPlayerKills(player) + 1) + "' WHERE `UUID`='" + uuid + "';");
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void incrementPlayerDeaths(Player player)
	{
		try
		{
			if (!DB.checkConnection())
			{
				DB.openConnection();
			}

			String uuid = player.getUniqueId().toString();

			Statement s = DB.getConnection().createStatement();

			s.executeUpdate("UPDATE `FFA` SET `Deaths`='" + (getPlayerDeaths(player) + 1) + "' WHERE `UUID`='" + uuid + "';");
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int getPlayerDeaths(Player player) throws Exception
	{

		if (!hasAccount(player))
		{
			throw new IllegalArgumentException("Player does not have an account in the database!");

		}

		if (!DB.checkConnection())
		{
			DB.openConnection();
		}

		Statement s = DB.getConnection().createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM `FFA` WHERE `UUID`='" + player.getUniqueId().toString() + "';");

		if (!rs.next())
		{
			return rs.getInt("Deaths");
		} else
		{
			return 0;
		}

	}

	public static Map<?, ?> getPlayerStats(Player player) throws Exception
	{
		HashMap<String, Object> playerStats = new HashMap<>();

		playerStats.put("UUID", getPlayerUUID(player));
		playerStats.put("Kills", getPlayerKills(player));
		playerStats.put("Deaths", getPlayerDeaths(player));

		return playerStats;
	}



}
