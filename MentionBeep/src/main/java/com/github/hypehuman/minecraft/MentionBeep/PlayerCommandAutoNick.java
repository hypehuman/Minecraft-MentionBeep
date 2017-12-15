package com.github.hypehuman.minecraft.MentionBeep;

import java.util.Arrays;
import java.util.List;

public class PlayerCommandAutoNick implements PlayerCommand {
	
	@Override
	public List<String> getNames() {
		return Arrays.asList(
			"autoNick",
			"an"
		);
	}

	@Override
	public String getHelp() {
		return "Get or set whether nicknames are automatically generated, and helps more with this feature";
	}

	@Override
	public void execute(PlayerSettings playerSettings, List<String> args) {
		if (args.size() == 0) {
			report(playerSettings);
			playerSettings.player.sendMessage("To change this setting, use /" + playerSettings.plugin.getDescription().getName() + " " + getNames().get(0) + " toggle");
			return;
		}
		
		if (args.size() == 1 && args.get(0).equalsIgnoreCase("toggle")) {
			playerSettings.setAutoGenerateNicknames(!playerSettings.getAutoGenerateNicknames());
			report(playerSettings);
			return;
		}
		
		playerSettings.player.sendMessage("Invalid arguments. Type '/MessageBeep autoNick' for more info.");
	}
	
	private void report(PlayerSettings playerSettings) {
		playerSettings.player.sendMessage(playerSettings.getAutoGenerateNicknames()
			? playerSettings.plugin.getName() + ": Automatically generating nicknames. Any " + PluginMentionBeep.MIN_AUTO_NICK_LENGTH + "-character substring of your name will trigger a beep."
			: playerSettings.plugin.getName() + ": Automatic nickname generation is disabled. Only your full name will trigger a beep."
		);
	}
}
