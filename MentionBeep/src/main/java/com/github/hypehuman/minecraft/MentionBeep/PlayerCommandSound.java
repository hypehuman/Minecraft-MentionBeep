package com.github.hypehuman.minecraft.MentionBeep;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.Sound;

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
		if (args.size() == 0)
		{
			report(playerSettings);
			if (playerSettings.canCustomizeSound()) {
				String usageMessage = "To change, type '/MessageBeep sound set'.";
				if (playerSettings.soundIsCustomized()) {
					usageMessage += " Or to revert to the default, type '/MessageBeep sound reset'.";
				}
				playerSettings.player.sendMessage(usageMessage);
			}
			return;
		}
		
		if (args.size() == 1 && args.get(0).equalsIgnoreCase("reset"))
		{
			playerSettings.resetCustomSound();
			report(playerSettings);
			return;
		}
		
		if (args.size() >= 1 && args.get(0).equalsIgnoreCase("set"))
		{
			if (args.size() == 1) {
				playerSettings.player.sendMessage(new String[] {
					"Usage: /MentionBeep sound set SOUND VOLUME PITCH",
					"where SOUND is one of the following: " + Stream.of(Sound.values()).map(x -> x.name()).collect(Collectors.joining(", ")), // This will be ridiculously long. Figure out a better way inform the player.
					"where VOLUME (optional) is at least 0.0 and at most 1.0",
					"where PITCH (optional) is at least 0.5 and at most 2.0",
				});
				return;
			}
			if (args.size() <= 4) {
				SoundSettings parsed = new SoundSettings();
				if (
					parsed.setSound(args.get(1)) &&
					(args.size() <= 2 || parsed.setVolume(args.get(2))) &&
					args.size() <=3 || parsed.setPitch(args.get(3))
				) {
					report(playerSettings);
					return;
				}
			}
		}

		playerSettings.player.sendMessage("Invalid arguments. Type '/MessageBeep sound' for more info.");
	}
	
	private void report(PlayerSettings playerSettings) {
		playerSettings.playSound();
		playerSettings.player.sendMessage("You are using " + (playerSettings.soundIsCustomized() ? "the default" : "a custom") + " sound:" + playerSettings.getEffectiveSound().toString());
	}
}
