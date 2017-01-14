package vapourdrive.genloader.utils.json;

import vapourdrive.genloader.api.generation.IGenerationCategory;
import vapourdrive.genloader.api.serializeable.IParsableBlockState;
import vapourdrive.genloader.api.serializeable.IWeightedBlockState;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonHelper
{
	public static GsonBuilder gsonBuilder;
	public static Gson gson;
	
	public GsonHelper()
	{
		gsonBuilder = new GsonBuilder();

		gsonBuilder.registerTypeHierarchyAdapter(IWeightedBlockState.class, new WeightedBlockStateSerializer());
		gsonBuilder.registerTypeHierarchyAdapter(IParsableBlockState.class, new ParsableBlockStateSerializer());
		gsonBuilder.registerTypeHierarchyAdapter(IGenerationCategory.class, new GenerationCategorySerializer());
		
		gson = gsonBuilder.setPrettyPrinting().disableHtmlEscaping().create();
	}
	
	public static GsonBuilder getBuilder()
	{
		return gsonBuilder;
	}
	
	public static Gson getAdaptedGson()
	{
		return gson;
	}
}
