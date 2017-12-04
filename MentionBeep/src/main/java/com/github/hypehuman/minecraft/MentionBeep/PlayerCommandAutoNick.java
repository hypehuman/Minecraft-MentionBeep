package com.github.hypehuman.minecraft.MentionBeep;

import java.util.List;

public class PlayerCommandAutoNick extends PlayerCommand {
	@Override
	public String[] getNames() {
		return new String[]{
			"autoNick",
			"a",
		};
	}

	@Override
	public boolean execute(PlayerSettings playerSettings, List<String> args) {
		boolean report = args.size() == 0;
		boolean toggle = args.size() == 1 && args.get(0).equalsIgnoreCase("toggle");
		if (report || toggle) {
			if (toggle) {
				playerSettings.setAutoGenerateNicknames(!playerSettings.getAutoGenerateNicknames());
			}
			playerSettings.player.sendMessage(playerSettings.getAutoGenerateNicknames()
				? playerSettings.plugin.getName() + ": Automatically generating nicknames. Any " + PluginMentionBeep.MIN_AUTO_NICK_LENGTH + "-character substring of your name will trigger a beep."
				: playerSettings.plugin.getName() + ": Automatic nickname generation is disabled. Only your full name will trigger a beep."
			);
			if (report) {
				playerSettings.player.sendMessage("To change this setting, use /" + playerSettings.plugin.getDescription().getName() + " " + getNames()[0] + " toggle");
			}
			return true;
		}
		
		return false;
	}
}
