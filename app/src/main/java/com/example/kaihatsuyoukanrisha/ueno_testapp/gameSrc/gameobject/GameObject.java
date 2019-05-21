package com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.gameobject;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.component.ComponentInterface;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.transform.Transform;

import java.util.ArrayList;
import java.util.List;

public class GameObject extends ObjectInterface {
    public Transform transform = new Transform();
    public GameObject parent;
    public List<GameObject> children = new ArrayList<>();
    public String name;
    public String tag;
    public int layer;
    private GameObjectMediator mediator = null;
    private List<ComponentInterface> componentList = new ArrayList<>();

    public GameObject(GameObjectMediator mediator) {
        this.mediator = mediator;
        tag = "default";
        mediator.addObject(this);
    }

    public GameObject(GameObjectMediator mediator, String tag) {
        this.mediator = mediator;
        this.tag = tag;
    }

    public GameObject(GameObjectMediator mediator, String tag, int layer) {
        this.mediator = mediator;
        this.tag = tag;
        this.layer = layer;
    }

    public boolean init() {
        for (ComponentInterface c : componentList) {
            c.init();
        }

        return true;
    }

    public void delete() {
        for (ComponentInterface c : componentList) {
            c.delete();
        }
        mediator = null;
        componentList = null;
    }

    public void update() {
        for (ComponentInterface c : componentList) {
            c.update();
        }
    }

    public void draw() {

    }

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

}
