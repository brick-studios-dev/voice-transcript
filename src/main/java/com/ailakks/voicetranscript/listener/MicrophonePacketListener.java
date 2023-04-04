/**
 * This code has been programmed by Ailakks.
 * Please, leave this note and give me credits
 * in any project in which it is used. Have a nice day!
 *
 * @author : Ailakks
 * @mailto : hola@ailakks.com
 * @created : 04/04/2023
 */

package com.ailakks.voicetranscript.listener;

import com.ailakks.voicetranscript.audio.WhisperRequest;
import de.maxhenkel.voicechat.api.events.MicrophonePacketEvent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
public class MicrophonePacketListener {
    private static byte[] totalDecode;

    public static void onMicrophonePacket(MicrophonePacketEvent event) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(event.getPacket().getOpusEncodedData());
            totalDecode = byteArrayOutputStream.toByteArray();

            System.out.println(WhisperRequest.request(totalDecode));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
