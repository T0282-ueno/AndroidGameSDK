package com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.factory;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.component.Camera;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.gameobject.GameObject;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.gameobject.GameObjectMediator;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.transform.Transform;

public class ObjectFactory {
    private GameObjectMediator mediator;

    public ObjectFactory(GameObjectMediator mediator) {
        this.mediator = mediator;
    }

    public GameObject createEmptyObject() {
        GameObject object = new GameObject(mediator);
        object.name = "empty";

        return object;
    }

    public GameObject createCamera() {
        GameObject object = new GameObject(mediator);
        object.name = "camera";
        object.addComponent(new Camera(object));

        return object;
    }

    public GameObject createCamera(Transform transform) {
        GameObject object = createCamera();
        object.transform = transform;

        return object;
    }
}