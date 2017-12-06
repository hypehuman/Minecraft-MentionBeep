package com.github.hypehuman.minecraft.MentionBeep;

import java.util.List;

import org.bukkit.command.ConsoleCommandSender;

public class ServerCommandCollection extends SubCommandCollection<ConsoleCommandSender, ServerCommand> {
	
	public static final ServerCommandCollection instance = new ServerCommandCollection(new ServerCommand[] {
	});

	public ServerCommandCollection(ServerCommand[] subCommands) {
		super(subCommands);
	}

	@Override
	void execute(ServerCommand subCommand, PluginMentionBeep plugin, ConsoleCommandSender sender, List<String> args) {
		subCommand.execute(plugin, sender, args);
	}

	@Override
	String getBaseCommandHelp(PluginMentionBeep plugin, ConsoleCommandSender sender) {
		return "";
	}
}
