package com.example.youtubemusicmod.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.youtubemusicmod.R;
import com.example.youtubemusicmod.models.Song;
import com.example.youtubemusicmod.utils.PythonExecutor;

import java.io.IOException;
import java.util.List;

public class ListenAgainAdapter extends RecyclerView.Adapter<ListenAgainAdapter.ViewHolder> {

    private final List<Song> songList;
    private final Context context;
    private final PythonExecutor executor;

    public ListenAgainAdapter(List<Song> songList, Context context) {
        this.songList = songList;
        this.context = context;
        executor = PythonExecutor.getInstance(context);
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
        String songTitle = song.getTitle();
        String thumbNail = song.getThumbnails().get(0).getUrl();
        holder.songTitle.setText(songTitle);
        loadImageFromURL(holder.albumArt, thumbNail);
        holder.itemView.setOnClickListener(v -> playSong(song.getVideoId()));
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    private void loadImageFromURL(ImageView imageView, String url) {
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.loading_spinner)
                .error(R.drawable.ic_launcher_foreground)
                .into(imageView);
    }

    private void playSong(String videoId) {
        String streamUrl = videoIdToStreamUrl(videoId);
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(streamUrl);
            mediaPlayer.prepare();
            mediaPlayer.start();
            Toast.makeText(context, "Playing song", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Failed to play song", Toast.LENGTH_SHORT).show();
        }
    }

    private String videoIdToStreamUrl(String videoId) {
        return executor.getStreamUrl(videoId);
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


