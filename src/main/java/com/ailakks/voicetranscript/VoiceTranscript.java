package com.ailakks.voicetranscript;

import com.ailakks.voicetranscript.configuration.BukkitConfiguration;
import com.ailakks.voicetranscript.configuration.BukkitConfigurationManager;
import com.ailakks.voicetranscript.listener.MicrophonePacketListener;
import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.MicrophonePacketEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class VoiceTranscript extends JavaPlugin implements VoicechatPlugin {

    public static VoiceTranscript instance;

    public static final BukkitConfigurationManager bukkitConfigurationManager = new BukkitConfigurationManager();
    public static final BukkitConfiguration bukkitConfiguration = bukkitConfigurationManager.getConfig("config.yml");

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * @return the ID of this plugin - Has to be unique
     */
    @Override
    public String getPluginId() {
        return "voice-transcript";
    }

    /**
     * Called after loading the plugin.
     *
     * @param api
     */
    @Override
    public void initialize(VoicechatApi api) {

    }

    /**
     * Register your events here - Only here!
     *
     * @param registration the event registration object, used to register events
     */
    @Override
    public void registerEvents(EventRegistration registration) {
        registration.registerEvent(MicrophonePacketEvent.class, MicrophonePacketListener::onMicrophonePacket);
    }
}
