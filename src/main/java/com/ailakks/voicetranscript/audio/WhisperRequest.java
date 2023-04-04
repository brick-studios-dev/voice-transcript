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
import okhttp3.*;

import java.io.*;

public class WhisperRequest {
    public static String request(byte[] totalDecode) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("model","whisper-1")
                .addPart(RequestBody.create(totalDecode))
                .build();
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/audio/transcriptions")
                .post(body)
                .addHeader("Authorization", VoiceTranscript.bukkitConfiguration.getString("key.open_ai"))
                .build();
        try {
            Response response = client.newCall(request).execute();
            response.close();
            return response.toString();
        } catch (IOException e) {
            throw new RuntimeException(new Exception());
        }
    }
}
