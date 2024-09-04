package com.example.youtubemusicmod;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.example.youtubemusicmod.utils.AssetManager;
import com.example.youtubemusicmod.utils.PythonExecutor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PythonExecutor executor = PythonExecutor.getInstance(this);
        initializeYoutubeAPI(executor);
        Intent toHomePage = new Intent(this, HomePageActivity.class);
        startActivity(toHomePage);
        finish();
    }

    private void initializeYoutubeAPI(PythonExecutor executor){
        try{
            String oauthFileContent = AssetManager.getAssetFileContent(this, "oauth.json");
            executor.initYoutube(oauthFileContent);
            Toast.makeText(this, "Successfully initialized Youtube API", Toast.LENGTH_LONG).show();
        } catch (Exception exception){
            Toast.makeText(this, "Could not initialize Youtube API", Toast.LENGTH_LONG).show();
        }
    }

}
