package com.example.youtubemusicmod.utils;

import android.content.Context;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.youtubemusicmod.models.ListenAgainContent;
import com.example.youtubemusicmod.models.ListenAgainContentList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class PythonExecutor {
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

    private Python init(Context currentActivity){
        if(!Python.isStarted()){
            Python.start(new AndroidPlatform(currentActivity));
        }
        return Python.getInstance();
    }

    public PyObject startMainInstance(){
        return pythonInstance.getModule("main");
    }

    public void initYoutube(PyObject main, String oauthFileContent){
        PyObject pyObject = main.callAttr("yt_init", oauthFileContent);
    }

    public void getHome(PyObject main){
        main.callAttr("get_home");
    }

    public Map<String, Object> getListenAgain() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List listenAgainString = pythonInstance.getModule("main").callAttr("get_listen_again").toJava(List.class);
        //ListenAgainContentList listenAgainContent = objectMapper.readValue(listenAgainString, ListenAgainContentList.class);
        return null;
    }

}
