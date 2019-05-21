package com.example.kaihatsuyoukanrisha.ueno_testapp.scene;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.GameSDK;

abstract public class SceneInterface {
    GameSDK sdk;
    String sceneName;

    public SceneInterface(String name) {
        sdk = GameSDK.getSDK();
        this.sceneName = name;
    }

    abstract public void setup();
    abstract public void release();
}
