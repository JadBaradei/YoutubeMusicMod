package com.example.youtubemusicmod;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubemusicmod.adapters.ListenAgainAdapter;
import com.example.youtubemusicmod.fragments.PlayerBottomSheetFragment;
import com.example.youtubemusicmod.models.Song;
import com.example.youtubemusicmod.utils.PythonExecutor;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class HomePageActivity extends AppCompatActivity {
    List<Song> listenAgainSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        RecyclerView listenAgainRecyclerView = findViewById(R.id.listen_again_recycler_view);
        PythonExecutor executor = PythonExecutor.getInstance(this);
        try {
            listenAgainSongs = executor.getListenAgain();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if (listenAgainSongs != null) {
            ListenAgainAdapter listenAgainAdapter = new ListenAgainAdapter(listenAgainSongs, this, getSupportFragmentManager());
            listenAgainRecyclerView.setAdapter(listenAgainAdapter);
            listenAgainRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.HORIZONTAL, false));
        }
    }

}