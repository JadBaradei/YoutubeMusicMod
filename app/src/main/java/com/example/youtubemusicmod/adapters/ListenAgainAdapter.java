package com.example.youtubemusicmod.adapters;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.youtubemusicmod.R;
import com.example.youtubemusicmod.fragments.PlayerBottomSheetFragment;
import com.example.youtubemusicmod.models.Song;
import com.example.youtubemusicmod.utils.MediaPlayerSingleton;
import com.example.youtubemusicmod.utils.PythonExecutor;

import java.util.List;

public class ListenAgainAdapter extends RecyclerView.Adapter<ListenAgainAdapter.ViewHolder> {

    private final List<Song> songList;
    private final Context context;
    private final PythonExecutor executor;
    private final FragmentManager supportFragmentManager;

    public ListenAgainAdapter(List<Song> songList, Context context, FragmentManager supportFragmentManager) {
        this.songList = songList;
        this.context = context;
        executor = PythonExecutor.getInstance(context);
        this.supportFragmentManager = supportFragmentManager;
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
        String title = song.getTitle();
        String thumbNail = song.getThumbnails().get(0).getUrl();
        String videoId = song.getVideoId();
        holder.songTitle.setText(title);
        loadImageFromURL(holder.albumArt, thumbNail);
        holder.itemView.setOnClickListener(v -> launchSong(videoId));
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

    private void launchSong(String videoId) {
        showPlayerBottomSheet(supportFragmentManager);
        Thread playSongThread = new Thread(() -> playSong(videoId));
        playSongThread.start();
    }

    private void playSong(String videoId) {
        String streamUrl = videoIdToStreamUrl(videoId);
        MediaPlayerSingleton.getInstance().playSong(streamUrl);
        new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(context, "Playing song", Toast.LENGTH_SHORT).show());
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

    private void showPlayerBottomSheet(FragmentManager supportFragmentManager) {
        Log.d("BottomSheet", "Showing bottom sheet");
        PlayerBottomSheetFragment bottomSheetFragment = new PlayerBottomSheetFragment();
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.getTag());
    }
    
}


