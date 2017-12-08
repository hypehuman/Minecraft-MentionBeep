package com.github.hypehuman.minecraft.MentionBeep;

import java.util.Arrays;
import java.util.List;

public class PlayerCommandToggleIsEnabled implements PlayerCommand {

	@Override
	public List<String> getNames() {
		return Arrays.asList(
			"toggle",
			"t"
		);
	}

	@Override
	public String getHelp() {
		return "enable or disable the plugin";
	}

	@Override
	public void execute(PlayerSettings playerSettings, List<String> args) {
		if (args.size() != 0) {
			playerSettings.player.sendMessage("Invalid arguments. Type '/MessageBeep' for more info.");
			return;
		}
		
		playerSettings.setIsEnabled(!playerSettings.getIsEnabled());
		playerSettings.player.sendMessage(playerSettings.plugin.getDescription().getName() + " is now " + playerSettings.getIsEnabledAdjective());
	}
}
