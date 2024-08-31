package com.example.youtubemusicmod;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.youtubemusicmod.R;
import com.example.youtubemusicmod.utils.AssetHelper;
import com.example.youtubemusicmod.utils.PythonExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LinkRefresherActivity extends AppCompatActivity {

    private TextView textView;
    private ExecutorService executorService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        /*setContentView(R.layout.link_refresher);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
        PythonExecutor executor = PythonExecutor.getInstance(this);


    }
}