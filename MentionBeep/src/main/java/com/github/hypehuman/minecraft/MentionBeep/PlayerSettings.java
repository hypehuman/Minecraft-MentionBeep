package com.github.hypehuman.minecraft.MentionBeep;

import java.util.*;
import java.util.logging.Level;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.json.simple.*;
import org.json.simple.parser.*;

public class PlayerSettings {
	public final PluginMentionBeep plugin;
	public final Player player;
	private boolean IsEnabled = true;
	private boolean AutoGenerateNicknames = true;
	
	public PlayerSettings(PluginMentionBeep aPlugin, Player aPlayer) {
		plugin = aPlugin;
		player = aPlayer;
		String n = getNameUsedByThisPlugin();
		for (int i = 0; i < n.length(); i++) {
			if (!PluginMentionBeep.allowedNameChars.contains(n.charAt(i))) {
				plugin.getLogger().log(Level.WARNING, "Player name '" + "' contains characters that I didn't think were allowed. Message parsing may fail to detect this player.");
				player.sendMessage(plugin.getDescription().getName() + ": Your name does not match my assumptions. This plugin may not behave as expected.");
				break;
			}
		}
		Load();
	}

	public boolean getIsEnabled() {
		return IsEnabled;
	}
	
	public void setIsEnabled(boolean value) {
		IsEnabled = value;
		Save();
	}
	
	public boolean getAutoGenerateNicknames() {
		return AutoGenerateNicknames;
	}
	
	public void setAutoGenerateNicknames(boolean value) {
		AutoGenerateNicknames = value;
		Save();
	}
	
	public String getNameUsedByThisPlugin() {
		return player.getName();
	}
	
	// Returns a case-insensitive set containing every substring of the player's name that has MIN_AUTO_NICK_LENGTH or more characters
	private Set<String> getMentionableNames()
	{
		CaseInsensitiveMap<String, Object> result = new CaseInsensitiveMap<String, Object>(); // the values are irrelevant
		String name = getNameUsedByThisPlugin();
		result.put(name, null);
		if (AutoGenerateNicknames)
		{
			for (int i = 0; i <= name.length() - PluginMentionBeep.MIN_AUTO_NICK_LENGTH; i++) {
				for (int j = i + PluginMentionBeep.MIN_AUTO_NICK_LENGTH; j <= name.length(); j++) {
					result.put(name.substring(i, j), null);
				}
			}
		}
		return result.keySet();
	}
	
	public void handlePossibleMentions(Collection<String> possibleMentions) {
		if (!getIsEnabled()) {
			return;
		}

		Set<String> names = getMentionableNames();
		if (!possibleMentions.parallelStream().anyMatch(pm -> names.contains(pm))) {
			return;
		}
		
		// TODO: Make these last three arguments user-configurable
		player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 1, 1);
	}
	
	public String getIsEnabledAdjective() {
		return getIsEnabled() ? "enabled" : "disabled";
	}

	private String getConfigPath() {
		return "playerSettings" + player.getUniqueId();
	}

	private static final String PluginVersionTag = "PluginVersion";
	private static final String IsEnabledTag = "IsEnabled";
	private static final String AutoGenerateNicknamesTag = "AutoGenerateNicknames";

	private void Save() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put(PluginVersionTag, plugin.getDescription().getVersion()); // just in case future versions need it
        jsonObj.put(IsEnabledTag, IsEnabled);
        jsonObj.put(AutoGenerateNicknamesTag, AutoGenerateNicknames);
        String jsonStr = jsonObj.toJSONString();
		try {
	        plugin.getConfig().set(getConfigPath(), jsonStr);
	        plugin.saveConfig();
		} catch (Exception e) {
			plugin.getLogger().log(Level.WARNING, "Failed to save settings for player " + player.getUniqueId(), e);
			player.sendMessage(plugin.getDescription().getName() + ": Failed to save settings. More details logged on server.");
		}
	}
	
	private void Load() {
		try {
			String jsonStr = plugin.getConfig().getString(getConfigPath());
			if (jsonStr == null) {
				// player is logging in for the first time
				return;
			}
			Object obj = new JSONParser().parse(jsonStr);
			JSONObject jsonObj = (JSONObject)obj;
			Object ieObj = jsonObj.getOrDefault(IsEnabledTag, null);
			if (ieObj != null)
			{
				// if a value is there, it had better be a boolean
				IsEnabled = (boolean)ieObj;
			} // if null, keep the default value
			Object agnObj = jsonObj.getOrDefault(AutoGenerateNicknamesTag, null);
			if (agnObj != null)
			{
				// if a value is there, it had better be a boolean
				AutoGenerateNicknames = (boolean)agnObj;
			} // if null, keep the default value
		} catch (Exception e) {
			plugin.getLogger().log(Level.WARNING, "Failed to load settings for player " + player.getUniqueId(), e);
			player.sendMessage(plugin.getDescription().getName() + ": Failed to load settings. More details logged on server.");
		}
	}
}
