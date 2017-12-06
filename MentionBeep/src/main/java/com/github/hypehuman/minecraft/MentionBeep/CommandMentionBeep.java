package com.github.hypehuman.minecraft.MentionBeep;

import org.bukkit.command.*;
import org.bukkit.entity.*;

public class CommandMentionBeep implements CommandExecutor {
	private final PluginMentionBeep plugin;
	
	public CommandMentionBeep(PluginMentionBeep aPlugin) {
		plugin = aPlugin;
	}
	
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length > 0) {
            if (sender instanceof Player) {
            	PlayerCommandCollection.instance.onCommand(plugin, (Player)sender, cmd, label, args);
            }
            else if (sender instanceof ConsoleCommandSender) {
            	ServerCommandCollection.instance.onCommand(plugin, (ConsoleCommandSender)sender, cmd, label, args);
            }
        }
        return false;
    }
}
