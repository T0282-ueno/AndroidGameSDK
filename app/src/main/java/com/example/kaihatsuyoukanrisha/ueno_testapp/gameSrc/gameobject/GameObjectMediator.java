package com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.gameobject;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class GameObjectMediator extends GameObject {
    private List<GameObject> objectList = new ArrayList<>();
    private Queue<GameObject> workObject = new ArrayDeque<>();
    private Queue<GameObject> garbageObject = new ArrayDeque<>();
    public Map<String, Integer> layer = new HashMap<String, Integer>() {
        {
            put("SYSTEM_LAYER",0x0000);
            put("DEFAULT_LAYER", 0x0001);
        }
    };

    public void initGameObjects() {
        for (GameObject object : objectList) {
            workObject.add(object);

            while (!workObject.isEmpty()) {
                GameObject work = workObject.poll();
                work.init();

                workObject.addAll(work.getChildren());
            }
        }
    }

    public void updateGameObjects() {
        for (GameObject object : objectList) {
            workObject.add(object);

            while (!workObject.isEmpty()) {
                GameObject work = workObject.poll();
                work.update();

                workObject.addAll(work.getChildren());
            }
        }
    }

    public void drawGameObjects() {
        for (GameObject object : objectList) {
            workObject.add(object);

            while (!workObject.isEmpty()) {
                GameObject work = workObject.poll();
                work.draw();

                workObject.addAll(work.getChildren());
            }
        }
    }

    public void allDaleteGameObjects() {
        for (GameObject object : objectList) {
            workObject.add(object);

            while (!workObject.isEmpty()) {
                GameObject work = workObject.poll();
                work.delete();

                workObject.addAll(work.getChildren());
            }
        }

        objectList.clear();
    }

    public void addGarbageObject(GameObject object) {
        garbageObject.add(object);
    }

    private void cleanGarbageObject() {
        for (GameObject object : garbageObject) {
            object.delete();
        }
        garbageObject.clear();
    }

    public void setChild(GameObject parent, GameObject child) {

    }

    protected void addObject(GameObject object) {
        objectList.add(object);
    }
}
