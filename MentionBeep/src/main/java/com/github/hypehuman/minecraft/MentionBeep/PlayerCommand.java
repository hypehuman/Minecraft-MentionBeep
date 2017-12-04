package com.github.hypehuman.minecraft.MentionBeep;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class PlayerCommand implements ISubCommand {
	@Override
	public boolean execute(PluginMentionBeep plugin, CommandSender sender, List<String> args) {
        Player player = (Player) sender;
        PlayerSettings playerSettings = plugin.playerManager.getSettings(player);
        if (playerSettings == null) {
        	// player is offline or something went wrong;
        	return false;
        }
        return execute(playerSettings, args);
	}
	
	protected abstract boolean execute(PlayerSettings playerSettings, List<String> args);
}
