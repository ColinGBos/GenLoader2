package vapourdrive.genloader.utils.json;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;
import vapourdrive.genloader.api.serializeable.IWeightedBlockState;

import com.google.gson.reflect.TypeToken;
import vapourdrive.genloader.api.serializeable.WeightedBlockState;
import vapourdrive.genloader.api.utils.BlockUtils;

public class WeightedBlockStateSerializer implements JsonSerializer<IWeightedBlockState>, JsonDeserializer<IWeightedBlockState>
{
	GsonBuilder builder = new GsonBuilder();
	Gson gson = builder.create();

	@Override
	public JsonElement serialize(IWeightedBlockState src, Type typeOfSrc, JsonSerializationContext context)
	{
		JsonObject object = new JsonObject();
		object.addProperty("Weight", src.getWeight());
		object.addProperty("Block", src.getBlockName());
		Type type = new TypeToken<Map<String, String>>()
		{
		}.getType();
		object.add("Properties", gson.toJsonTree(src.getProperties(), type));
		return object;
	}

	@Override
	public WeightedBlockState deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
	{
		JsonObject object = json.getAsJsonObject();
		int Weight = object.get("Weight").getAsInt();
		String block = object.get("Block").getAsString();
		JsonObject array = object.get("Properties").getAsJsonObject();
		Type type = new TypeToken<HashMap<String, String>>(){}.getType();
		HashMap<String, String> map = gson.fromJson(array, type);
		return new WeightedBlockState(Weight, BlockUtils.createState(block, map));
	}
}
