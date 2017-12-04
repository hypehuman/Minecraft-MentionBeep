package com.github.hypehuman.minecraft.MentionBeep;

import java.util.List;

import org.bukkit.command.CommandSender;

public interface ISubCommand {
	String[] getNames();
	boolean execute(PluginMentionBeep plugin, CommandSender sender, List<String> args);
}
