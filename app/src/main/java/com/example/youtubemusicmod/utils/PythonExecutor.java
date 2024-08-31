package com.example.youtubemusicmod.utils;

import android.content.Context;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PythonExecutor {
    private static final String OAUTH_JSON_PATH = "/oauth.json";
    private static final String OAUTH_JSON_NAME = "oauth.json";
    private String SCRIPT_NAME = "my_script";
    private static PythonExecutor INSTANCE;
    private final Python pythonInstance;

    private PythonExecutor(Context currentActivity){
        this.pythonInstance = init(currentActivity);
    }

    public static PythonExecutor getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new PythonExecutor(context);
        }
        return INSTANCE;
    }

    public void setScript(String scriptName){
        SCRIPT_NAME = scriptName;
    }

    private Python init(Context currentActivity){
        if(!Python.isStarted()){
            Python.start(new AndroidPlatform(currentActivity));
        }
        return Python.getInstance();
    }

    public void sendEnter(){
        pythonInstance.getModule("sender").callAttr("send_input_to_script", "\n");
    }

    public void initializeYtb(Context context){
        pythonInstance.getModule("my_script").callAttr("initialize", context);
    }

    public boolean isInitialized(){
        return pythonInstance.getModule("my_script").callAttr("is_initialized").toJava(Boolean.class);
    }

    public void sendRequest(Context context){
        pythonInstance.getModule("my_script").callAttr("setup_oauth", context);
    }

}
