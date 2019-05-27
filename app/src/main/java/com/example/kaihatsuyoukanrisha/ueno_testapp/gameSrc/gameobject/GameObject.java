package com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.gameobject;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.component.ComponentInterface;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.transform.Transform;

import java.util.ArrayList;
import java.util.List;

public class GameObject {
    public Transform transform = new Transform();
    protected GameObject parent;
    protected List<GameObject> children = new ArrayList<>();
    public String name;
    public String tag;
    public int layer;
    protected List<ComponentInterface> componentList = new ArrayList<>();

    public GameObject() {
        tag = "default";
        layer = 0x1;
    }

    protected void init() {
        for (ComponentInterface c : componentList) {
            c.init();
        }
    }

    protected void delete() {
        for (ComponentInterface c : componentList) {
            c.delete();
        }

        if (!children.isEmpty()) {
            for (GameObject child : children) {
                child.delete();
            }
        }

        componentList.clear();
        componentList = null;
    }

    protected void update() {
        for (ComponentInterface c : componentList) {
            c.update();
        }
    }

    protected void draw() {

    }

    public List<GameObject> getChildren() { return children; }

    public <T extends ComponentInterface> T getComponent() {
        T object = null;
        for (ComponentInterface c : componentList) {
            if (c.getClass() == object.getClass()) {
                return (T) c;
            }
        }

        return null;
    }

    public void addComponent(ComponentInterface component) {
        componentList.add(component);
    }

    protected void text() {}
}
