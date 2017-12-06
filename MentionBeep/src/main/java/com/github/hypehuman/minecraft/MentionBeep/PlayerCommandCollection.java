package com.github.hypehuman.minecraft.MentionBeep;

import java.util.List;

import org.bukkit.entity.Player;

public class PlayerCommandCollection extends SubCommandCollection<Player, PlayerCommand> {
	
	public static final PlayerCommandCollection instance = new PlayerCommandCollection(new PlayerCommand[] {
		new PlayerCommandToggleIsEnabled(),
		new PlayerCommandAutoNick(),
	});
	
	public PlayerCommandCollection(PlayerCommand[] subCommands) {
		super(subCommands);
	}

	@Override
	void execute(PlayerCommand subCommand, PluginMentionBeep plugin, Player sender, List<String> args) {
        PlayerSettings playerSettings = plugin.playerManager.getSettings(sender);
        subCommand.execute(playerSettings, args);
	}
	
	@Override
	String getBaseCommandHelp(PluginMentionBeep plugin, Player sender) {
        PlayerSettings playerSettings = plugin.playerManager.getSettings(sender);
        return plugin.getDescription().getName() + " is " + playerSettings.getIsEnabledAdjective();
	}
}
