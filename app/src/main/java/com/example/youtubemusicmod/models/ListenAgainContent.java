package com.example.youtubemusicmod.models;

import java.util.ArrayList;

public class ListenAgainContent {

    private String title;
    private String videoId;
    private String playlistId;
    private ArrayList<Thumbnail> thumbnails;
    private ArrayList<Artist> artists;

    public ListenAgainContent(){

    }

    public ListenAgainContent(String title, String videoId, String playlistId, ArrayList<Thumbnail> thumbnails, ArrayList<Artist> artists) {
        this.title = title;
        this.videoId = videoId;
        this.playlistId = playlistId;
        this.thumbnails = thumbnails;
        this.artists = artists;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public ArrayList<Thumbnail> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(ArrayList<Thumbnail> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }
}
