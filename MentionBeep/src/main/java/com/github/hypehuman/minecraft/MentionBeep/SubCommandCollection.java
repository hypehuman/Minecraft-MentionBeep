package com.github.hypehuman.minecraft.MentionBeep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class SubCommandCollection<TSender extends CommandSender, TSubCommand extends SubCommand> {

	private final TSubCommand[] subCommands;
	private final Map<String, TSubCommand> byName = new CaseInsensitiveMap<String, TSubCommand>();
	
    public SubCommandCollection(TSubCommand[] aSubCommands) {
    	subCommands = aSubCommands;
    	for (TSubCommand sc : subCommands) {
    		for (String name : sc.getNames()) {
    			byName.put(name, sc);
    		}
    	}
	}

	public void onCommand(PluginMentionBeep plugin, TSender sender, Command cmd, String label, String[] args) {
		if (args.length != 0) {
	    	String subCommandName = args[0];
	    	List<String> remainingArgs = Arrays.stream(args).skip(1).collect(Collectors.toList());
	    	TSubCommand subCommand = byName.getOrDefault(subCommandName, null);
	    	if (subCommand != null) {
	        	execute(subCommand, plugin, sender, remainingArgs);
	        	return;
	    	}
		}

    	ArrayList<String> helpList = getHelp(plugin, sender, cmd);
    	sender.sendMessage(helpList.toArray(new String[helpList.size()]));
	}
	
	abstract void execute(TSubCommand subCommand, PluginMentionBeep plugin, TSender sender, List<String> args);
	
	ArrayList<String> getHelp(PluginMentionBeep plugin, TSender sender, Command cmd) {
		StringJoiner baseCmdJoiner = new StringJoiner("", " /", "");
		baseCmdJoiner.add(cmd.getName());
		cmd.getAliases().stream().forEach(baseCmdJoiner::add);
		ArrayList<String> result = new ArrayList<String>();
		result.add("Aliases (all commands are case-insensitive):" + baseCmdJoiner.toString());
		result.add("    " + getBaseCommandHelp(plugin, sender));
		for (TSubCommand sc : subCommands) {
			StringJoiner subCmdJoiner = new StringJoiner("", "/" + cmd.getName() + " ", "");
			Arrays.stream(sc.getNames()).forEach(subCmdJoiner::add);
			result.add(subCmdJoiner.toString());
			result.add("    " + sc.getHelp());
		}
		return result;
	}

	abstract String getBaseCommandHelp(PluginMentionBeep plugin, TSender sender);
}
