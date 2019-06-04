package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.core;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.GameSDK;

abstract public class SceneInterface {
    String sceneName;
    protected GameSDKInterface sdk;

    public SceneInterface(String name) {
        this.sceneName = name;
        sdk = GameSDK.getSDK();
    }

    abstract public void setup();
    abstract public void release();
}
