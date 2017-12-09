package com.github.hypehuman.minecraft.MentionBeep;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.json.simple.*;

public abstract class SoundSettings {
	Sound sound;
	float volume;
	float pitch;
	
	public SoundSettings(JSONObject jsonObj) {
		deserialize(jsonObj);
	}
	
	public void play(Player player) {
		player.playSound(player.getLocation(), sound, volume, pitch);
	}
	
	private boolean setSound(Object input) {
		Sound parsed;
		try {
			parsed = Sound.valueOf((String)input);
		}
		catch (Exception ex) {
			return false;
		}
		sound = parsed;
		return true;
	}

	private static final String SoundTag = "Sound";
	private static final String VolumeTag = "Volume";
	private static final String PitchTag = "Pitch";
	
	public JSONObject serialize() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put(SoundTag, sound.name());
        jsonObj.put(VolumeTag, volume);
        jsonObj.put(PitchTag, pitch);
        return jsonObj;
	}
	
	private JSONObject deserialize(JSONObject jsonObj) {
		setSound(jsonObj.getOrDefault(SoundTag, null));
		
	}
}
