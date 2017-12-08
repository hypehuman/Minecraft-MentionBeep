package com.github.hypehuman.minecraft.MentionBeep;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.json.simple.*;
import org.json.simple.parser.*;

public abstract class SoundSettings {
	Sound sound;
	float volume;
	float pitch;
	
	public SoundSettings(JSONObj jsonObj) {
		deserialize(jsonObj);
	}
	
	public void play(Player player) {
		player.playSound(player.getLocation(), sound, volume, pitch);
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
		setSound()
	}
	
	abstract void save() {
		
	}
}
