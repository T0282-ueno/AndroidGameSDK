package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.component;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.gameobject.GameObject;

abstract public class ComponentInterface {
    public GameObject object;
    public boolean isActive = true;

    public ComponentInterface(GameObject object) {
        this.object = object;
    }

    abstract public void init();
    abstract public void update();
    abstract public void delete();
}
