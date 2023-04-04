/**
 * This code has been programmed by Ailakks.
 * Please, leave this note and give me credits
 * in any project in which it is used. Have a nice day!
 *
 * @author : Ailakks
 * @mailto : hola@ailakks.com
 * @created : 04/04/2023
 */

package com.ailakks.voicetranscript.audio;

import com.ailakks.voicetranscript.VoiceTranscript;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.UUID;

public class WhisperRequest {
    public static String request(byte[] totalDecode) {
        try {
            String apiEndpoint = "https://api.openai.com/v1/audio/transcriptions";
            URL url = new URL(apiEndpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "audio/mp3");
            conn.setRequestProperty("Authorization", "Bearer " + VoiceTranscript.bukkitConfiguration.getString("key.open_ai"));

            String boundary = UUID.randomUUID().toString();
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            OutputStream outputStream = conn.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true);

            writer.append("--").append(boundary).append("\r\n")
                    .append("Content-Disposition: form-data; name=\"model\"\r\n\r\n")
                    .append("whisper-1")
                    .append("\r\n")
                    .append("--")
                    .append(boundary)
                    .append("\r\n")
                    .append("Content-Disposition: form-data; name=\"file\"; filename=\"audio.mp3\"\r\n")
                    .append("Content-Type: audio/mp3\r\n\r\n");

            outputStream.write(totalDecode);

            writer.append("\r\n")
                    .append("--")
                    .append(boundary)
                    .append("--\r\n");

            writer.flush();
            writer.close();

            InputStream inputStream = conn.getInputStream();
            return new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
