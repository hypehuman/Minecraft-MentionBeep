package com.github.hypehuman.minecraft.MentionBeep;

import java.util.List;

public interface PlayerCommand extends SubCommand {
	void execute(PlayerSettings playerSettings, List<String> args);
}
