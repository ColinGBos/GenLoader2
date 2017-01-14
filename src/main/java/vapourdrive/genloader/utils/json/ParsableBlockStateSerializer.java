package vapourdrive.genloader.utils.json;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;
import vapourdrive.genloader.api.serializeable.IParsableBlockState;

import com.google.gson.reflect.TypeToken;
import vapourdrive.genloader.api.serializeable.ParsableBlockState;
import vapourdrive.genloader.api.utils.BlockUtils;

public class ParsableBlockStateSerializer implements JsonSerializer<IParsableBlockState>, JsonDeserializer<IParsableBlockState>
{
	@Override
	public JsonElement serialize(IParsableBlockState src, Type typeOfSrc, JsonSerializationContext context)
	{
		JsonObject object = new JsonObject();
		object.addProperty("Block", src.getBlockName());
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		object.add("Properties", context.serialize(src.getProperties(), type));
		return object;
	}

	@Override
	public ParsableBlockState deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
	{
		JsonObject object = json.getAsJsonObject();
		String block = object.get("Block").getAsString();
		JsonObject array = object.get("Properties").getAsJsonObject();
		Type type = new TypeToken<HashMap<String, String>>(){}.getType();
		HashMap<String, String> map = context.deserialize(array, type);
		return new ParsableBlockState(BlockUtils.createState(block, map));
	}

}
