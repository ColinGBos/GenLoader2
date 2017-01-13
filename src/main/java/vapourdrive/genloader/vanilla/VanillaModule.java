package vapourdrive.genloader.vanilla;

import java.util.ArrayList;

import net.minecraft.block.BlockStone;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import vapourdrive.genloader.api.GenLoaderAPI;
import vapourdrive.genloader.api.generation.EnumGenerationPriority;
import vapourdrive.genloader.api.generation.EnumGenerationType;
import vapourdrive.genloader.api.generation.GenerationCategory;
import vapourdrive.genloader.api.serializeable.ParsableBlockState;
import vapourdrive.genloader.api.serializeable.WeightedBlockState;

public class VanillaModule
{
	public VanillaModule()
	{
		buildVanillaGenerators();
		addValueableBlocks();
	}
	
	private void addValueableBlocks()
	{
		GenLoaderAPI.addValuableBlock(Blocks.COAL_ORE);
		GenLoaderAPI.addValuableBlock(Blocks.IRON_ORE);
		GenLoaderAPI.addValuableBlock(Blocks.GOLD_ORE);
		GenLoaderAPI.addValuableBlock(Blocks.REDSTONE_ORE);
		GenLoaderAPI.addValuableBlock(Blocks.LAPIS_ORE);
		GenLoaderAPI.addValuableBlock(Blocks.EMERALD_ORE);
		GenLoaderAPI.addValuableBlock(Blocks.DIAMOND_ORE);
		GenLoaderAPI.addValuableBlock(Blocks.QUARTZ_ORE);
		GenLoaderAPI.addValuableBlock(Blocks.CHEST);
		GenLoaderAPI.addValuableBlock(Blocks.MOSSY_COBBLESTONE);
		GenLoaderAPI.addValuableBlock(Blocks.COBBLESTONE);
		GenLoaderAPI.addValuableBlock(Blocks.MOB_SPAWNER);
	}

	private void buildVanillaGenerators()
	{
		GenerationCategory Ores = new GenerationCategory("VanillaOres", false);
		GenerationCategory Junk = new GenerationCategory("VanillaJunk", false);
		ParsableBlockState stone = new ParsableBlockState(Blocks.STONE.getDefaultState());
		ParsableBlockState netherrack = new ParsableBlockState(Blocks.NETHERRACK.getDefaultState());
		
		ArrayList<Integer> overworld = new ArrayList<Integer>();
		overworld.add(0);
		ArrayList<Integer> nether = new ArrayList<Integer>();
		nether.add(-1);
		ArrayList<Type> mountainBiomes = new ArrayList<Type>();
		mountainBiomes.add(BiomeDictionary.Type.MOUNTAIN);
		
		GenLoaderAPI.addGeneration(Ores, EnumGenerationPriority.LATER, EnumGenerationType.STANDARDVARIABLECLUSTER, 1.0f, 20, 0, 128, 17, overworld, null, null, new WeightedBlockState[]{new WeightedBlockState(10, Blocks.COAL_ORE.getDefaultState())}, stone);
		GenLoaderAPI.addGeneration(Ores, EnumGenerationPriority.LATER, EnumGenerationType.STANDARDVARIABLECLUSTER, 1.0f, 20, 0, 64, 9, overworld, null, null, new WeightedBlockState[]{new WeightedBlockState(10, Blocks.IRON_ORE.getDefaultState())}, stone);
		GenLoaderAPI.addGeneration(Ores, EnumGenerationPriority.LATE, EnumGenerationType.STANDARDVARIABLECLUSTER, 1.0f, 2, 0, 32, 9, overworld, null, null, new WeightedBlockState[]{new WeightedBlockState(10, Blocks.GOLD_ORE.getDefaultState())}, stone);
		GenLoaderAPI.addGeneration(Ores, EnumGenerationPriority.LATER, EnumGenerationType.STANDARDVARIABLECLUSTER, 1.0f, 8, 0, 16, 8, overworld, null, null, new WeightedBlockState[]{new WeightedBlockState(10, Blocks.REDSTONE_ORE.getDefaultState())}, stone);
		GenLoaderAPI.addGeneration(Ores, EnumGenerationPriority.LATE, EnumGenerationType.STANDARDVARIABLECLUSTER, 1.0f, 6, 0, 32, 1, overworld, mountainBiomes, null, new WeightedBlockState[]{new WeightedBlockState(10, Blocks.EMERALD_ORE.getDefaultState())}, stone);
		GenLoaderAPI.addGeneration(Ores, EnumGenerationPriority.LATE, EnumGenerationType.WEIGHTEDVARIABLECLUSTER, 1.0f, 1, 0, 32, 7, overworld, null, null, new WeightedBlockState[]{new WeightedBlockState(10, Blocks.LAPIS_ORE.getDefaultState())}, stone);
		GenLoaderAPI.addGeneration(Ores, EnumGenerationPriority.LATE, EnumGenerationType.STANDARDVARIABLECLUSTER, 1.0f, 1, 0, 16, 8, overworld, null, null, new WeightedBlockState[]{new WeightedBlockState(10, Blocks.DIAMOND_ORE.getDefaultState())}, stone);
		GenLoaderAPI.addGeneration(Ores, EnumGenerationPriority.LATER, EnumGenerationType.STANDARDVARIABLECLUSTER, 1.0f, 16, 0, 256, 14, nether, null, null, new WeightedBlockState[]{new WeightedBlockState(10, Blocks.QUARTZ_ORE.getDefaultState())}, netherrack);

		GenLoaderAPI.addGeneration(Junk, EnumGenerationPriority.EARLIER, EnumGenerationType.STANDARDVARIABLECLUSTER, 1.0f, 8, 0, 256, 33, overworld, null, null, new WeightedBlockState[]{new WeightedBlockState(10, Blocks.DIRT.getDefaultState())}, stone);
		GenLoaderAPI.addGeneration(Junk, EnumGenerationPriority.EARLIER, EnumGenerationType.STANDARDVARIABLECLUSTER, 1.0f, 8, 0, 256, 33, overworld, null, null, new WeightedBlockState[]{new WeightedBlockState(10, Blocks.GRAVEL.getDefaultState())}, stone);
		GenLoaderAPI.addGeneration(Junk, EnumGenerationPriority.EARLIER, EnumGenerationType.STANDARDVARIABLECLUSTER, 1.0f, 10, 0, 80, 33, overworld, null, null, new WeightedBlockState[]{new WeightedBlockState(10, Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE))}, stone);
		GenLoaderAPI.addGeneration(Junk, EnumGenerationPriority.EARLIER, EnumGenerationType.STANDARDVARIABLECLUSTER, 1.0f, 10, 0, 80, 33, overworld, null, null, new WeightedBlockState[]{new WeightedBlockState(10, Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE))}, stone);
		GenLoaderAPI.addGeneration(Junk, EnumGenerationPriority.EARLIER, EnumGenerationType.STANDARDVARIABLECLUSTER, 1.0f, 10, 0, 80, 33, overworld, null, null, new WeightedBlockState[]{new WeightedBlockState(10, Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE))}, stone);

	}
}
