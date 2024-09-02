package com.example.youtubemusicmod.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.youtubemusicmod.R;
import com.example.youtubemusicmod.models.Song;

import java.util.List;

public class ListenAgainAdapter extends RecyclerView.Adapter<ListenAgainAdapter.ViewHolder> {

    private List<Song> songList;
    private Context context;

    public ListenAgainAdapter(List<Song> songList, Context context){
        this.songList = songList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_song, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.songTitle.setText(song.getTitle());
        //TODO Get Also the album art
        loadImageFromURL(holder.albumArt, song.getThumbnails().get(0).getUrl());
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    private void loadImageFromURL(ImageView imageView, String url){
        Glide.with(context)
                .load(url)
                //.placeholder() OPTIONAL LOADING SCREEN
                //.error() OPTIONAL ERROR SCREEN
                .into(imageView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView albumArt;
        TextView songTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            albumArt = itemView.findViewById(R.id.album_art);
            songTitle = itemView.findViewById(R.id.song_title);
        }
    }
}


