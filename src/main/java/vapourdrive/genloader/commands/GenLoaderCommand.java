package vapourdrive.genloader.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.Level;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import vapourdrive.genloader.api.GenLoaderAPI;
import vapourdrive.genloader.commands.subcommands.ClearBlocks;
import vapourdrive.genloader.commands.subcommands.CountBlocks;

import javax.annotation.Nullable;

public class GenLoaderCommand implements ICommand
{
	@Override
	public int compareTo(ICommand o)
	{
		return 0;
	}

	@Override
	public String getName()
	{
		return "genloader";
	}

	@Override
	public String getUsage(ICommandSender sender)
	{
		return "genloader <text>";
	}

	@Override
	public List<String> getAliases()
	{
		ArrayList<String> aliases = new ArrayList<String>();
		aliases.add(getName());
		return aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length > 0)
		{
			if(args[0].contains("countblocks"))
			{
				CountBlocks.processCommand(sender, args);
			}
			if(args[0].contains("clearblocks"))
			{
				ClearBlocks.processCommand(sender, args);
			}
		}
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		if (sender.getCommandSenderEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();
			if (player.capabilities.isCreativeMode)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
		ArrayList<String> argsList = new ArrayList<String>();
		if(args.length == 1)
		{
			argsList.add("countblocks");
			argsList.add("clearblocks");
		}
		if(args[0].contains("countblocks"))
		{
			argsList = CountBlocks.addTabCompletionOptions(sender, args);
			GenLoaderAPI.log.log(Level.INFO, "counblocks");
		}
		if(args[0].contains("clearblocks"))
		{
			argsList = ClearBlocks.addTabCompletionOptions(sender, args);
			GenLoaderAPI.log.log(Level.INFO, "clearblocks");
		}

		return argsList;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index)
	{
		return false;
	}

}
