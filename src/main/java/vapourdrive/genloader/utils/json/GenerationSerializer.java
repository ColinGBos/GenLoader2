package vapourdrive.genloader.utils.json;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import vapourdrive.genloader.api.generation.*;
import vapourdrive.genloader.api.serializeable.IWeightedBlockState;
import vapourdrive.genloader.api.serializeable.ParsableBlockState;

public class GenerationSerializer implements JsonSerializer<IGeneration>, JsonDeserializer<IGeneration>
{
	@Override
	public JsonElement serialize(IGeneration src, Type typeOfSrc, JsonSerializationContext context)
	{
		JsonObject object = new JsonObject();
		object.add("category", context.serialize(src.getOwner()));
		object.addProperty("generationPriority", src.getGeneratorPriority().toString());
		object.addProperty("generationType", src.getGeneratorType().toString());
		object.addProperty("chance", src.getChance());
		object.addProperty("frequency", src.getFrequency());
		object.addProperty("minY", src.getMinY());
		object.addProperty("maxY", src.getMaxY());
		object.addProperty("size", src.getSize());
		object.add("dimensions", context.serialize(src.getDimensions()));
//		object.add("biomeTypes", context.serialize(src.getBiomeTypes()));
		object.add("biomeNames", context.serialize(src.getBiomeNames()));
		object.add("weightedBlocks", context.serialize(src.getWeightedBlocks()));
//		object.add("blockToReplace", context.serialize(src.getBlockToReplace()));

		return object;
	}

	@Override
	public IGeneration deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
	{
		JsonObject object = json.getAsJsonObject();

		Type IGenCatType = new TypeToken<IGenerationCategory>(){}.getType();
		GenerationCategory category = context.deserialize(object.get("category"), IGenCatType);
		Type EnumGenPriorityType = new TypeToken<EnumGenerationPriority>(){}.getType();
		EnumGenerationPriority priority = context.deserialize(object.get("generationPriority"), EnumGenPriorityType);
		Type EnumGenTypeType = new TypeToken<EnumGenerationType>(){}.getType();
		EnumGenerationType type = context.deserialize(object.get("generationType"), EnumGenTypeType);
		float chance = object.get("chance").getAsFloat();
		int frequency = object.get("frequency").getAsInt();
		int minY = object.get("minY").getAsInt();
		int maxY = object.get("maxY").getAsInt();
		int size = object.get("size").getAsInt();
		Type DimensionArrayListType = new TypeToken<ArrayList<Integer>>(){}.getType();
		ArrayList<Integer> dimensions = context.deserialize(object.get("dimensions"), DimensionArrayListType);
		Type BiomeTypeArrayListType = new TypeToken<ArrayList<BiomeDictionary.Type>>(){}.getType();
		ArrayList<BiomeDictionary.Type> biomeTypes = context.deserialize(object.get("biomeTypes"), BiomeTypeArrayListType);
		Type BiomeNameArrayListType = new TypeToken<ArrayList<String>>(){}.getType();
		ArrayList<String> biomeNames = context.deserialize(object.get("biomeNames"), BiomeNameArrayListType);
		Type IWeightedBlockStateType = new TypeToken<IWeightedBlockState[]>(){}.getType();
		IWeightedBlockState[] weightedBlockStates = context.deserialize(object.get("weightedBlocks"), IWeightedBlockStateType);

		Type IBlockStateType = new TypeToken<IBlockState>(){}.getType();
		IBlockState blockState = context.deserialize(object.get("blockToReplace"), IBlockStateType);

		//IBlockState blockState = Blocks.STONE.getDefaultState();
		IGeneration generation = new Generation(category, priority, type, chance, frequency, minY, maxY, size, dimensions, biomeTypes, biomeNames, weightedBlockStates, new ParsableBlockState(blockState));
		return generation;
	}

}
