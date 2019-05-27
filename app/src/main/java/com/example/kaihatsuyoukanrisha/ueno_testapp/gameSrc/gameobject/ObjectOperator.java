package com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.gameobject;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.component.ComponentInterface;

import java.util.List;

public class ObjectOperator extends GameObject {

    public void init() {
        for (ComponentInterface c : componentList) {
            c.init();
        }
    }

    public void delete() {
        for (ComponentInterface c : componentList) {
            c.delete();
        }
        componentList.clear();
        componentList = null;
    }

    public void update() {
        for (ComponentInterface c : componentList) {
            c.update();
        }
    }

    public void draw() {

    }

    public List<ObjectOperator> getChildren() { return children; }
}
