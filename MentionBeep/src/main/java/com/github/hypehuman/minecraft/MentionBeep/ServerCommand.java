package com.github.hypehuman.minecraft.MentionBeep;

import java.util.List;

import org.bukkit.command.ConsoleCommandSender;

public abstract class ServerCommand implements SubCommand {

	public abstract void execute(PluginMentionBeep plugin, ConsoleCommandSender sender, List<String> args);
}
