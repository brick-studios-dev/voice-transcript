/**
 * This code has been programmed by Ailakks.
 * Please, leave this note and give me credits
 * in any project in which it is used. Have a nice day!
 *
 * @author : Ailakks
 * @mailto : hola@ailakks.com
 * @created : 04/04/2023
 */

package com.ailakks.voicetranscript;

import com.ailakks.voicetranscript.listener.MicrophonePacketListener;
import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.MicrophonePacketEvent;

public class VoiceChatPlugin implements VoicechatPlugin {
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
