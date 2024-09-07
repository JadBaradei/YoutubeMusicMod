package com.example.youtubemusicmod.utils;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MediaPlayerSingleton {

    private static MediaPlayerSingleton instance;
    private MediaPlayer mediaPlayer;
    private boolean isPrepared;
    private ExecutorService executorService;

    private MediaPlayerSingleton() {
        mediaPlayer = new MediaPlayer();
        isPrepared = false;
        executorService = Executors.newSingleThreadExecutor();
    }

    public static synchronized MediaPlayerSingleton getInstance() {
        if (instance == null) {
            instance = new MediaPlayerSingleton();
        }
        return instance;
    }

    public void playSong(String streamUrl) {
            if (isPrepared) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                isPrepared = false;
            }
            executorService.submit(() -> {
                try{
                    mediaPlayer.setDataSource(streamUrl);
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(mp -> {
                        isPrepared = true;
                        mp.start();
                        Log.d("MediaPlayerSingleton", "Song started playing");
                    });
                    mediaPlayer.setOnErrorListener((mp, what, extra) -> {
                        Log.e("MediaPlayerSingleton", "Error occurred while playing song");
                        return true;
                    });
                } catch (IOException e) {
                    Log.e("MediaPlayerSingleton", "Failed to play song", e);
                }
            });
    }

    public void stopSong() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    public void releasePlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            instance = null;
        }
    }
}
