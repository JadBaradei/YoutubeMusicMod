package com.example.youtubemusicmod.utils;

import android.content.Context;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

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
        try{
            return initializeYoutubePythonAPI(currentActivity);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private Python initializeYoutubePythonAPI(Context currentActivity) throws Exception {
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(currentActivity));
        }
        try {
            FileUtils.copyFileToInternalStorage(currentActivity, OAUTH_JSON_NAME);
            String filePath = currentActivity.getFilesDir() + OAUTH_JSON_PATH;
            Python pythonInstance = Python.getInstance();
            PyObject module = pythonInstance.getModule(SCRIPT_NAME);
            module.callAttr("initialize", filePath);
            return pythonInstance;
        } catch (Exception e) {
            throw new Exception("An error occurred while initializing python API");
        }
    }

    public void sendEnter(){
        pythonInstance.getModule("sender").callAttr("send_input_to_script", "\n");
    }

    public Object sendRequest(){
        PyObject pyObject = pythonInstance.getModule("my_script").callAttr("get_oauth");
        return pyObject.toJava(List.class);
    }

}
