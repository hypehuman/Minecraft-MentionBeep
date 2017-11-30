package com.github.hypehuman.minecraft.MentionBeep;

import java.util.*;
import org.apache.commons.collections4.map.*;
import java.util.stream.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class CommandMentionBeep implements CommandExecutor {
	private static Map<String, ISubCommand> subCommands;
	private final PluginMentionBeep plugin;
	
	public CommandMentionBeep(PluginMentionBeep aPlugin) {
		plugin = aPlugin;
		if (subCommands == null) {
			subCommands = initSubCommands();
		}
	}
	
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
        	return false;
        }

        Player player = (Player) sender;
        PlayerSettings playerSettings = plugin.playerManager.getSettings(player);
        if (playerSettings == null) {
        	// player is offline or something went wrong;
        	return false;
        }
        
        if (args.length > 0) {
        	String subCommandName = args[0];
        	List<String> remainingArgs = Arrays.stream(args).skip(1).collect(Collectors.toList());
            if (subCommands.containsKey(subCommandName)) {
            	ISubCommand subCommand = subCommands.getOrDefault(subCommandName, null);
            	if (subCommand != null && subCommand.execute(playerSettings, remainingArgs)) {
            		return true;
            	}
            }
        }
        
        return sendHelp(playerSettings);
    }

    private static Map<String, ISubCommand> initSubCommands() {
    	ISubCommand[] scs = {
    		new SubCommandToggleIsEnabled(),
    		new SubCommandAutoNick(),
		};
    	Map<String, ISubCommand> result = new CaseInsensitiveMap<String, ISubCommand>();
    	for (ISubCommand sc : scs) {
    		for (String name : sc.getNames()) {
    			result.put(name, sc);
    		}
    	}
    	return result;
    }
    
    public boolean sendHelp(PlayerSettings playerSettings)
    {
    	playerSettings.player.sendMessage(new String[] {
    		plugin.getDescription().getName() + " is " + playerSettings.getIsEnabledAdjective(),
    		"    Aliases (case-insensitive): /MentionBeep /mb",
    		"    /mb toggle: enable or disable the plugin",
    		"    /mb autoNick: return whether or not nicknames are automatically generated, and gives more info on this feature",
    		"    /mb autoNick toggle: turns auto nickname generation on and off",
    	});
    	return true;
    }
}
