package com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.gameobject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameObjectMediator extends ObjectInterface {
    private List<GameObject> objects = new ArrayList<>();
    private List<GameObject> workObjects = new ArrayList<>();
    private List<GameObject> workChildren = new ArrayList<>();
    private List<GameObject> deleteObjects = new ArrayList<>();
    public Map<String, Integer> layer = new HashMap<String, Integer>() {
        {
            put("SYSTEM_LAYER",0x0000);
            put("DEFAULT_LAYER", 0x0001);
        }
    };

    @Override
    public boolean init() {

        return true;
    }

    @Override
    protected void update() {

    }

    @Override
    protected void draw() {

    }

    @Override
    protected void delete() {

    }

    public void initGameObjects() {
        for (GameObject object : objects) {
            object.init();

            if (!object.children.isEmpty()) {
                workChildren.addAll(object.children);
            }
        }

        initChildren();
    }

    public void updateGameObjects() {
        for (GameObject object : objects) {
            object.update();

            if (!object.children.isEmpty()) {
                workChildren.addAll(object.children);
            }
        }

        updateChildren();

        clearDeleteList();
    }

    public void drawGameObjects() {
        for (GameObject object : objects) {
            object.draw();

            if (!object.children.isEmpty()) {
                workChildren.addAll(object.children);
            }
        }

        drawChildren();
    }

    public void allDaleteGameObjects() {
        for (GameObject object : objects) {
            object.delete();

            if (!object.children.isEmpty()) {
                workChildren.addAll(object.children);
            }
        }

        deleteChildren();
    }

    public void deleteObject(GameObject object) {
        deleteObjects.add(object);
        if (!object.children.isEmpty()) {
            deleteObject(object.children);
        }
    }

    public void setChild(GameObject parent, GameObject child) {

    }

    protected void addObject(GameObject object) {
        objects.add(object);
    }

    private void deleteObject(List<GameObject> childern) {
        deleteObjects.addAll(childern);
        for (GameObject child : childern) {
            if (!child.children.isEmpty()) {
                deleteObject(child.children);
            }
        }
    }

    private void clearDeleteList() {
        for (GameObject garbage : deleteObjects) {
            garbage.delete();
        }
        deleteObjects.clear();
    }

    private void initChildren() {
        if (workChildren.isEmpty()){
            return;
        }

        workObjects.addAll(workChildren);
        workChildren.clear();

        for (GameObject child : workObjects) {
            child.init();

            if (!child.children.isEmpty()) {
                workChildren.addAll(child.children);
            }
        }

        initChildren();
    }

    private void deleteChildren() {
        if (workChildren.isEmpty()){
            return;
        }

        workObjects.addAll(workChildren);
        workChildren.clear();

        for (GameObject child : workObjects) {
            child.delete();

            if (!child.children.isEmpty()) {
                workChildren.addAll(child.children);
            }
        }

        deleteChildren();
    }

    private void updateChildren() {
        if (workChildren.isEmpty()){
            return;
        }

        workObjects.addAll(workChildren);
        workChildren.clear();

        for (GameObject child : workObjects) {
            child.update();

            if (!child.children.isEmpty()) {
                workChildren.addAll(child.children);
            }
        }

        updateChildren();
    }

    private void drawChildren() {
        if (workChildren.isEmpty()){
            return;
        }

        workObjects.addAll(workChildren);
        workChildren.clear();

        for (GameObject child : workObjects) {
            child.draw();

            if (!child.children.isEmpty()) {
                workChildren.addAll(child.children);
            }
        }

        drawChildren();
    }
}
