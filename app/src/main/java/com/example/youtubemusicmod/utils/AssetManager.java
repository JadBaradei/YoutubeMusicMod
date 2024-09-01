package com.example.youtubemusicmod.utils;
import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class AssetManager {
    public static String getAssetFileContent(Context context, String filename) {
        StringBuilder text = new StringBuilder();
        try {
            InputStream inputStream = context.getAssets().open(filename);
            int size;
            byte[] buffer = new byte[1024];
            while ((size = inputStream.read(buffer)) != -1) {
                text.append(new String(buffer, 0, size));
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }
}
