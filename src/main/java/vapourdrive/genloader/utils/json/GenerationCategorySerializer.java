package vapourdrive.genloader.utils.json;

import java.lang.reflect.Type;

import com.google.gson.*;
import vapourdrive.genloader.api.generation.GenerationCategory;
import vapourdrive.genloader.api.generation.IGenerationCategory;

public class GenerationCategorySerializer implements JsonSerializer<IGenerationCategory>, JsonDeserializer<IGenerationCategory>
{

	@Override
	public JsonElement serialize(IGenerationCategory src, Type typeOfSrc, JsonSerializationContext context)
	{
		JsonObject object = new JsonObject();
		object.addProperty("name", src.getCategoryName());
		object.addProperty("defaultEnabled", src.getIsDefaultEnabled());
		return object;
	}

	@Override
	public IGenerationCategory deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
	{
		JsonObject object = json.getAsJsonObject();
		return new GenerationCategory(object.get("name").getAsString(), object.get("defaultEnabled").getAsBoolean());
	}

}
