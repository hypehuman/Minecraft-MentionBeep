package com.github.hypehuman.minecraft.MentionBeep;

import java.util.List;

public interface ISubCommand {
	String[] getNames();
	boolean execute(PlayerSettings playerSettings, List<String> args);
}
