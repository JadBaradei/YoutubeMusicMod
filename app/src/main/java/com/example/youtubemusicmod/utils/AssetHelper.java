package com.example.youtubemusicmod.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class AssetHelper {

    public static File createInternalFile(Context context, String fileName, String fileContent) {
        File file = new File(context.getFilesDir(), fileName);

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(fileContent.getBytes());
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getInternalFile(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        if (file.exists()) {
            return getFileContent(file);
        } else {
            return null;
        }
    }

    public static boolean deleteInternalFile(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        return file.delete();
    }

    private static String getFileContent(File file) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return content.toString();
    }

    public static String getInternalFilePath(Context context ,String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        if (file.exists()) {
            return file.getAbsolutePath();
        } else {
            return null;
        }
    }

}
