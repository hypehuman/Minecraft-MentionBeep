package com.github.hypehuman.minecraft.MentionBeep;

import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

public class PlayerManager implements Listener {
	private PluginMentionBeep plugin;
	private HashMap<UUID, PlayerSettings> playersOnline = new HashMap<UUID, PlayerSettings>();
	
	public PlayerManager(PluginMentionBeep aPlugin) {
		plugin = aPlugin;
		for (Player player : plugin.getServer().getOnlinePlayers()) {
			onPlayerJoin(player);
		}
	}

	public PlayerSettings getSettings(Player player) {
		return playersOnline.getOrDefault(player.getUniqueId(), null);
	}
	
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
    	onPlayerJoin(event.getPlayer());
    }
    
    public void onPlayerJoin(Player player) {
    	UUID playerId = player.getUniqueId();
    	PlayerSettings settings = new PlayerSettings(plugin, player);
    	playersOnline.put(playerId, settings);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
    	playersOnline.remove(event.getPlayer().getUniqueId());
    }
    
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
    	String message = event.getMessage();
    	Collection<String> possibleMentionStrings = getPossibleMentionSubstrings(message);
    	playersOnline.values().parallelStream().forEach(ps -> ps.handlePossibleMentions(possibleMentionStrings));
    }

    // Splits the message by non-name characters and returns the substrings of name characters.
    // Each one of these substrings is possibly the name of a user.
    // Examples:
    // "Hi there, Mr. Zed!" => "Hi", "there", "Mr", "Zed"
    private static Collection<String> getPossibleMentionSubstrings(String message) {
    	Collection<String> result = new HashSet<String>();
		int nameCharIStart = -1;
		int i;
		for (i = 0; i < message.length(); i++) {
			char current = message.charAt(i);
			if (PluginMentionBeep.allowedNameChars.contains(current)) {
				if (nameCharIStart < 0) {
					nameCharIStart = i;
				}
			}
			else {
				if (nameCharIStart >= 0) {
					result.add(message.substring(nameCharIStart, i));
				}
				nameCharIStart = -1;
			}
		}
		if (nameCharIStart >= 0) {
			result.add(message.substring(nameCharIStart, i));
		}
		return result;
    }
}
