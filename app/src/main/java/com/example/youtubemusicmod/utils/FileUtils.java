package com.example.youtubemusicmod.utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

    // Method to copy a file from assets to internal storage
    public static void copyFileToInternalStorage(Context context, String filename) {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            // Open the file from assets
            inputStream = context.getAssets().open(filename);

            // Create the file in internal storage
            File file = new File(context.getFilesDir(), filename);
            outputStream = new FileOutputStream(file);

            // Buffer for reading file
            byte[] buffer = new byte[1024];
            int length;

            // Read the file from assets and write it to internal storage
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions as needed
        } finally {
            // Close streams
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace(); // Handle exceptions as needed
            }
        }
    }
}
