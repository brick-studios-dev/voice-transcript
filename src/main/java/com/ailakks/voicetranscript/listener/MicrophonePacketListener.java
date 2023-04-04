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

import com.ailakks.voicetranscript.VoiceTranscript;
import de.maxhenkel.voicechat.api.events.MicrophonePacketEvent;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MicrophonePacketListener {
    private static byte[] totalDecode;

    public static void onMicrophonePacket(MicrophonePacketEvent event) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(event.getPacket().getOpusEncodedData());
            totalDecode = byteArrayOutputStream.toByteArray();

            String apiEndpoint = "https://api.openai.com/v1/audio/transcriptions";
            URL url = new URL(apiEndpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "audio/mp3");

            FormDataMultiPart formData = new FormDataMultiPart();
            FormDataBodyPart filePart = new FormDataBodyPart(
                    FormDataContentDisposition.name("file").fileName("audio.mp3").build(),
                    new ByteArrayInputStream(totalDecode), MediaType.APPLICATION_OCTET_STREAM_TYPE);
            formData.field("model", "whisper-1");
            formData.bodyPart(filePart);

            Client client = ClientBuilder.newBuilder()
                    .register(MultiPartFeature.class)
                    .build();

            Entity<FormDataMultiPart> requestEntity = Entity.entity(formData, MediaType.MULTIPART_FORM_DATA_TYPE);
            requestEntity.getEntity().getHeaders().add("Authorization", "Bearer " + VoiceTranscript.bukkitConfiguration.getString("key.open_ai"));

            Response response = client.target(apiEndpoint)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(requestEntity);

            InputStream inputStream = response.readEntity(InputStream.class);
            String stringResponse = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();

            Logger.getGlobal().log(Level.INFO, stringResponse);

            inputStream.close();
            client.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
