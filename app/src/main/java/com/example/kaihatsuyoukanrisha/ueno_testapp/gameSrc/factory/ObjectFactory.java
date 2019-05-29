package com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.factory;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.component.Camera;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.gameobject.GameObject;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.gameobject.GameObjectManager;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.transform.Transform;

public class ObjectFactory {
    private GameObjectManager manager;

    public ObjectFactory(GameObjectManager manager) {
        this.manager = manager;
    }

    public GameObject createEmptyObject() {
        GameObject object = new GameObject();
        object.name = "empty";
        manager.addObject(object);

        return object;
    }

    public GameObject createCamera() {
        GameObject object = new GameObject();
        object.name = "camera";
        object.addComponent(new Camera(object));
        manager.addObject(object);

        return object;
    }

    public GameObject createCamera(Transform transform) {
        GameObject object = createCamera();
        object.transform = transform;
        manager.addObject(object);

        return object;
    }
}
