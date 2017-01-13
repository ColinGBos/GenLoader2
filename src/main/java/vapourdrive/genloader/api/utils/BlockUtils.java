package vapourdrive.genloader.api.utils;

import java.util.HashMap;
import java.util.Map.Entry;

import com.google.common.collect.UnmodifiableIterator;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import org.apache.logging.log4j.Level;

import vapourdrive.genloader.api.GenLoaderAPI;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class BlockUtils
{
	public static IBlockState createState(String block, HashMap<String, String> properties)
	{
		Block foundBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(block));
		if(foundBlock != null) {
			ImmutableList<IBlockState> states = foundBlock.getBlockState().getValidStates();
			if(!states.isEmpty()) {
				for (IBlockState state : states) {
					boolean isCorrectState = true;
					ImmutableMap<IProperty<?>, Comparable<?>> stateProps = state.getProperties();
					for (Entry<IProperty<?>, Comparable<?>> entry : stateProps.entrySet()) {
						IProperty property = entry.getKey();
						if (properties.containsKey(property.getName())) {
							if (!state.getValue(property).toString().contentEquals(properties.get(property.getName()))) {
								isCorrectState = false;
							}
						}
					}
					if (isCorrectState) {
						return state;
					}
				}
			}
			GenLoaderAPI.log.log(Level.WARN, "Block: *" + block + "* found, properties: *" + properties + "* were not found, resorting to block's default state");
			return foundBlock.getDefaultState();
		}
		GenLoaderAPI.log.log(Level.WARN, "Block: *" + block + "* was not found, resorting to stone *** This is not what you want ***");
		return Blocks.STONE.getDefaultState();

	}

	public static HashMap<String, String> generateProperties(IBlockState state)
	{
		ImmutableMap<IProperty<?>, Comparable<?>> stateProps = state.getProperties();
		UnmodifiableIterator<Entry<IProperty<?>, Comparable<?>>> propIterater = stateProps.entrySet().iterator();
		HashMap<String, String> statePropsMap = new HashMap<String, String>();
		while(propIterater.hasNext())
		{
			Entry<IProperty<?>, Comparable<?>> entry = propIterater.next();
			statePropsMap.put(entry.getKey().getName(), state.getValue(entry.getKey()).toString());
		}
		return statePropsMap;
	}
}
