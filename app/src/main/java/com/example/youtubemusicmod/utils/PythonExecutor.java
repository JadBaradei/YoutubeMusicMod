package com.example.youtubemusicmod.utils;

import android.content.Context;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.util.concurrent.ExecutionException;

public class PythonExecutor {
    private static final String OAUTH_JSON_PATH = "/oauth.json";
    private static final String OAUTH_JSON_NAME = "oauth.json";
    private final String SCRIPT_NAME;
    private final Python pythonInstance;

    public PythonExecutor(Context currentActivity, String scriptName){
        this.SCRIPT_NAME = scriptName;
        this.pythonInstance = init(currentActivity);
    }

    private Python init(Context currentActivity){
        try{
            return initializeYoutubePythonAPI(currentActivity, SCRIPT_NAME);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private Python initializeYoutubePythonAPI(Context currentActivity, String scriptName) throws Exception {
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(currentActivity));
        }
        try {
            FileUtils.copyFileToInternalStorage(currentActivity, OAUTH_JSON_NAME);
            String filePath = currentActivity.getFilesDir() + OAUTH_JSON_PATH;
            Python pythonInstance = Python.getInstance();
            pythonInstance.getModule(scriptName).callAttr("initialize", filePath);
            return pythonInstance;
        } catch (Exception e) {
            throw new Exception("An error occurred while initializing python API");
        }
    }

    public String songSearch(String name) {
        PyObject pyObject = pythonInstance.getModule(SCRIPT_NAME).callAttr("get_song", name);
        return pyObject.toJava(String.class);
    }
}
