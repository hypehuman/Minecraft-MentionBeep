package com.github.hypehuman.minecraft.MentionBeep;

import java.util.Arrays;
import java.util.List;

public class PlayerCommandSound implements PlayerCommand {

	@Override
	public List<String> getNames() {
		return Arrays.asList(
			"sound",
			"s"
		);
	}

	@Override
	public String getHelp() {
		return "Get or set what sound is played when you are mentioned, and helps more with this feature";
	}

	@Override
	public void execute(PlayerSettings playerSettings, List<String> args) {
		boolean report = args.size() == 0;
		boolean reset = args.size() == 1 && args.get(0).equalsIgnoreCase("toggle");
		boolean set = args.get(0).equalsIgnoreCase("set");

	}
}
