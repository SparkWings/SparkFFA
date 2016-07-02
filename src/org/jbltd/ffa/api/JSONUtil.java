package org.jbltd.ffa.api;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONUtil
{
	
	@SuppressWarnings("unchecked")
	public static JSONArray buildArray(Map<?, ?> objects)
	{

		JSONArray array = new JSONArray();

		while (objects.keySet().iterator().hasNext())
		{
			JSONObject ob = new JSONObject();
			ob.put(objects.keySet().iterator().next(), objects.values().iterator().next());
			array.add(ob);
		}

		return array;
	}

	
}
