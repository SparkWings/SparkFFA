package org.jbltd.ffa.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jbltd.ffa.api.JSONUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

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

	public static JSONArray buildJSON(String key, Player player)
	{

		try
		{
			
			
			
			JSONArray array = JSONUtil.buildArray(getPlayerStats(player));

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse(array.toJSONString());
			String fin = gson.toJson(je);

			System.out.println(fin);

			return array;

		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;

	}

	public static String generateAPIKey() throws Exception
	{
		if (!DB.checkConnection())
		{
			DB.openConnection();
		}

		String key = UUID.randomUUID().toString();

		Statement s = DB.getConnection().createStatement();
		s.executeUpdate("INSERT INTO `APIKEYS` (`Key`,`Calls`) VALUES ('" + key + "',0);");

		return key;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{

		JSONArray array = new JSONArray();
		JSONObject o = new JSONObject();
		o.put("UUID", UUID.randomUUID());
		JSONObject o2 = new JSONObject();
		o2.put("Kills", "1253");
		JSONObject o3 = new JSONObject();
		o3.put("Deaths", "12");

		array.addAll(Arrays.asList(o, o2, o3));

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(array.toJSONString());
		String fin = gson.toJson(je);

		System.out.println(fin);

	}

}
