# MentionBeep
Plugin for a Spigot Minecraft server

Commissioned by Zedwick

#### Functionality
If a player's name is mentioned in chat their client will play a sound. By default, any substring of the player's name (three letters or longer) will also trigger a beep, however, a player can turn that functionality off via a command. A player can also turn the whole plugin off (for them) via a command.

Mentions are case-insensitive and must fill a whole word. For example, let us take two players: Alex and Steve. Then let us consider the following messages:
- "Hi Alexander!" => nobody is notified
- "Your name is Alex!" => Alex is notified
- "My two best friends are Lex and Tev" => Alex and Steve are notified
- "I also like Lexie and Tevvie" => nobody is notified

#### Usage (if you're playing on a server that uses this plugin)
- Type /MentionBeep or /mb in-game for instructions.
- /mb toggle - turns the plugin on and off (for you only).
- /mb autonick toggle - switches between requiring your exact name and allowing shorter substrings.

#### Building/Installing (if you manage a server and want to add this plugin)
There may be easier ways, but this is how I do it:
1. Install Eclipse: <https://www.eclipse.org/downloads/>
2. Add M2Eclipse to Eclipse: <http://www.eclipse.org/m2e/>
3. Load the directory 'Minecraft-MentionBeep/MentionBeep' as a project in Eclipse.
4. In the Goals textbox, type ```package shade:shade```
5. Click the Run button
6. Once the build succeeds, copy the file 'Minecraft-MentionBeep/MentionBeep/target/MentionBeep-X.jar' (where X is the version number) to the 'Plugins' subdirectory of your Spigot server
7. Start (or re-start) your server
