package com.github.hypehuman.minecraft.MentionBeep;

import java.util.Arrays;
import java.util.HashSet;

import org.bukkit.plugin.java.*;

public class PluginMentionBeep extends JavaPlugin {
	public static final int MIN_AUTO_NICK_LENGTH = 3;
	public static final HashSet<Character> allowedNameChars = new HashSet<Character>(Arrays.asList(new Character[] {
		// from https://gaming.stackexchange.com/questions/21806/what-is-the-format-of-minecraft-net-account-names
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '_'
	}));
	
	public final PlayerManager playerManager;
	
	public PluginMentionBeep() {
		playerManager = new PlayerManager(this);
	}
	
    @Override
    public void onEnable() {
    	// Register the command and alias
		getCommand("MentionBeep").setExecutor(new CommandMentionBeep(this));
		getServer().getPluginManager().registerEvents(playerManager, this);
    }
    
    @Override
    public void onDisable() {
    }
}
