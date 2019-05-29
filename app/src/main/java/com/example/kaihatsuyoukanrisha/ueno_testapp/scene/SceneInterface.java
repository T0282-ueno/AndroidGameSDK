package com.example.kaihatsuyoukanrisha.ueno_testapp.scene;

abstract public class SceneInterface {
    String sceneName;

    public SceneInterface(String name) {
        this.sceneName = name;
    }

    abstract public void setup();
    abstract public void release();
}
