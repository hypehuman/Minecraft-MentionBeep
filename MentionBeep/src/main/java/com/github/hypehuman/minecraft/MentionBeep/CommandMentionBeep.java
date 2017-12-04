package com.github.hypehuman.minecraft.MentionBeep;

import java.util.*;
import org.apache.commons.collections4.map.*;
import java.util.stream.*;

import org.bukkit.Server;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class CommandMentionBeep implements CommandExecutor {
	private static Map<String, PlayerCommand> playerCommands;
	private static Map<String, ServerCommand> serverCommands;
	private final PluginMentionBeep plugin;
	
	public CommandMentionBeep(PluginMentionBeep aPlugin) {
		plugin = aPlugin;
		if (playerCommands == null) {
			playerCommands = initPlayerCommands();
			serverCommands = initServerCommands();
		}
	}
	
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length > 0) {
        	String subCommandName = args[0];
        	List<String> remainingArgs = Arrays.stream(args).skip(1).collect(Collectors.toList());

            if (sender instanceof Player) {
            	PlayerSettings playerSettings = new PlayerSettings(plugin, (Player)sender);
                if (playerCommands.containsKey(subCommandName)) {
                	ISubCommand subCommand = playerCommands.getOrDefault(subCommandName, null);
                	if (subCommand != null && subCommand.execute(plugin, sender, remainingArgs)) {
                		return true;
                	}
                }
                else {
                	sender.sendMessage(
                		plugin.getDescription().getName() + " is " + playerSettings.getIsEnabledAdjective()
        			);
                	sendHelp(sender, playerCommands.values());
                }
            }
            else if (sender instanceof Server) {
            	Server srv = (Server)sender;
            	sender.sendMessage("You are a server!");
            	return true;
            }
            
        }
    }

    private static Map<String, PlayerCommand> initPlayerCommands() {
    	PlayerCommand[] scs = {
    		new PlayerCommandToggleIsEnabled(),
    		new PlayerCommandAutoNick(),
		};
    	Map<String, PlayerCommand> result = new CaseInsensitiveMap<String, PlayerCommand>();
    	for (PlayerCommand sc : scs) {
    		for (String name : sc.getNames()) {
    			result.put(name, sc);
    		}
    	}
    	return result;
    }
    
    public void sendHelp(CommandSender sender, Collection<? extends ISubCommand> commands)
    {
		sender.sendMessage(new String[] {
    		plugin.getDescription().getName() + " is " + playerSettings.getIsEnabledAdjective(),
    		"    Aliases (case-insensitive): /MentionBeep /mb",
    		"    /mb toggle: enable or disable the plugin",
    		"    /mb autoNick: return whether or not nicknames are automatically generated, and gives more info on this feature",
    		"    /mb autoNick toggle: turns auto nickname generation on and off",
    	});
    }
}
