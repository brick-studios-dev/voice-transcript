/**
 * This code has been programmed by Ailakks.
 * Please, leave this note and give me credits
 * in any project in which it is used. Have a nice day!
 *
 * @author : Ailakks
 * @mailto : hola@ailakks.com
 */

package com.ailakks.voicetranscript.configuration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ailakks.voicetranscript.VoiceTranscript;
import org.bukkit.configuration.InvalidConfigurationException;

public class BukkitConfigurationManager {

    private final Map<String, BukkitConfiguration> configurationMap;

    public BukkitConfigurationManager() {
        this.configurationMap = new HashMap<>();
    }

    public BukkitConfiguration getConfig(String name) {
        BukkitConfiguration configuration = configurationMap.get(name);

        if (configuration != null) {
            return configuration;
        }

        File configurationFile = new File(VoiceTranscript.instance.getDataFolder(), name);
        if (!configurationFile.exists()) {
            configurationFile.getParentFile().mkdirs();
            VoiceTranscript.instance.saveResource(name, false);
        }

        configuration = new BukkitConfiguration(configurationFile);
        try {
            configuration.load();
            this.configurationMap.put(name, configuration);
        } catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
        }

        return configuration;
    }
}