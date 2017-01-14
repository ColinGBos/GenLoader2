package vapourdrive.genloader.utils.json;

import java.lang.reflect.Type;

import com.google.gson.*;
import vapourdrive.genloader.api.generation.Generation;
import vapourdrive.genloader.api.generation.IGeneration;

public class GenerationSerializer implements JsonSerializer<IGeneration>, JsonDeserializer<IGeneration>
{
	@Override
	public JsonElement serialize(IGeneration src, Type typeOfSrc, JsonSerializationContext context)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGeneration deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
	{
		// TODO Auto-generated method stub
		return null;
	}

}
