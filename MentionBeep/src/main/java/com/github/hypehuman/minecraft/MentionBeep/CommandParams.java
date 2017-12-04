package com.github.hypehuman.minecraft.MentionBeep;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandParams {
	public final CommandSender sender;
	public final Command cmd;
	public final String label;
	public final String[] args;
	
	public CommandParams(CommandSender aSender, Command aCmd, String aLabel, String[] aArgs) {
		sender = aSender;
		cmd = aCmd;
		label = aLabel;
		args = aArgs;
	}
}
