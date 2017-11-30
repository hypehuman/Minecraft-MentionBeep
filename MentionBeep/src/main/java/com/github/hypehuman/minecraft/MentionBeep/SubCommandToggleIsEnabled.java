package com.github.hypehuman.minecraft.MentionBeep;

import java.util.List;

public class SubCommandToggleIsEnabled implements ISubCommand {

	@Override
	public String[] getNames() {
		return new String[] {"toggle"};
	}

	@Override
	public boolean execute(PlayerSettings playerSettings, List<String> args) {
		if (args.size() != 0) {
			// no sub-args allowed
			return false;
		}
		
		playerSettings.setIsEnabled(!playerSettings.getIsEnabled());
		playerSettings.player.sendMessage(playerSettings.plugin.getDescription().getName() + " is now " + playerSettings.getIsEnabledAdjective());
		return true;
	}

}
