package com.example.youtubemusicmod;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.youtubemusicmod.utils.AssetHelper;
import com.example.youtubemusicmod.utils.PythonExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    private ExecutorService executorService;
    private TextView textView;
    private Button doneButton;
    private Button logButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        PythonExecutor executor = PythonExecutor.getInstance(this);
        textView = findViewById(R.id.linkView);
        doneButton = findViewById(R.id.doneButton);
        logButton = findViewById(R.id.logInButton);
        logButton.setOnClickListener(v -> {
            executor.sendRequest(this);
            updateLoginText();
        });
        doneButton.setOnClickListener(v -> {
            AssetHelper.deleteInternalFile(this, "output.txt");
            executor.sendEnter();
            if(executor.isInitialized()){
                display("Initialized Successfully");
                executor.initializeYtb(this);
            }else{
                display("Did not initialize");
            }
        });
    }

    private void display(String message){
        textView.setText(message);
    }

    private void updateLoginText(){
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                final String result;
                result = AssetHelper.getInternalFile(LoginActivity.this, "output.txt");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(result);
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }

}