package com.example.youtubemusicmod.utils;

import android.content.Context;

import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.youtubemusicmod.models.Song;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

public class PythonExecutor {
    private static PythonExecutor INSTANCE;
    private final Python pythonInstance;

    private PythonExecutor(Context currentActivity) {
        this.pythonInstance = init(currentActivity);
    }

    private Python init(Context currentActivity) {
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(currentActivity));
        }
        return Python.getInstance();
    }

    public static PythonExecutor getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new PythonExecutor(context);
        }
        return INSTANCE;
    }

    public void initYoutube(String oauthFileContent) {
        pythonInstance.getModule("main").callAttr("yt_init", oauthFileContent);
    }

    public List<Song> getListenAgain() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String listenAgainString = pythonInstance.getModule("main").callAttr("get_listen_again").toJava(String.class);
        Song[] youtubeSongs = objectMapper.readValue(listenAgainString, Song[].class);
        return Arrays.asList(youtubeSongs);
    }

    public String getStreamUrl(String videoId) {
        return pythonInstance.getModule("main").callAttr("get_stream_url", videoId).toJava(String.class);
    }

}
