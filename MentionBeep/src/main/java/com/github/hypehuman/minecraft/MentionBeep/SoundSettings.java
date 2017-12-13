package com.github.hypehuman.minecraft.MentionBeep;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.json.simple.*;

public class SoundSettings {
	Sound sound = Sound.BLOCK_NOTE_CHIME;
	float volume = 1;
	float pitch = 1;
	
	public SoundSettings() {
	}
	
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
	
	private boolean setVolume(Object input) {
		float parsed;
		try {
			parsed = Float.valueOf((String)input);
		}
		catch (Exception ex) {
			return false;
		}
		if (parsed < 0 || parsed > 1) {
			return false;
		}
		volume = parsed;
		return true;
	}
	
	private boolean setPitch(Object input) {
		float parsed;
		try {
			parsed = Float.valueOf((String)input);
		}
		catch (Exception ex) {
			return false;
		}
		if (parsed < 0.5 || parsed > 2) {
			return false;
		}
		pitch = parsed;
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
	
	private void deserialize(JSONObject jsonObj) {
		setSound(jsonObj.getOrDefault(SoundTag, null));
		setVolume(jsonObj.getOrDefault(VolumeTag, null));
		setPitch(jsonObj.getOrDefault(PitchTag,  null));
	}
}
