package com.ailakks.voicetranscript;

import com.ailakks.voicetranscript.configuration.BukkitConfiguration;
import com.ailakks.voicetranscript.configuration.BukkitConfigurationManager;
import de.maxhenkel.voicechat.api.BukkitVoicechatPlugin;
import de.maxhenkel.voicechat.api.BukkitVoicechatService;
import org.bukkit.plugin.java.JavaPlugin;

@BukkitVoicechatPlugin
public final class VoiceTranscript extends JavaPlugin {

    public static VoiceTranscript instance;

    public static BukkitConfigurationManager bukkitConfigurationManager;
    public static BukkitConfiguration bukkitConfiguration;

    @Override
    public void onEnable() {
        instance = this;

        bukkitConfigurationManager = new BukkitConfigurationManager();
        bukkitConfiguration = bukkitConfigurationManager.getConfig("config.yml");

        BukkitVoicechatService bukkitVoicechatService = getServer().getServicesManager().load(BukkitVoicechatService.class);

        if (bukkitVoicechatService != null) {
            bukkitVoicechatService.registerPlugin(new VoiceChatPlugin());
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
