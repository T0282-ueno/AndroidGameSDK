package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore;

import android.content.Context;
import android.graphics.Point;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.gameobject.GameObject;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.transform.Transform;
import com.example.kaihatsuyoukanrisha.ueno_testapp.scene.SceneInterface;

public class GameSDK extends GameHandler implements GameSDKInterface {
    private static GameSDK sdk = null;

    private GameSDK() {
    }

    public static GameSDKInterface getSDK() {
        if (sdk == null) {
            sdk = new GameSDK();
        }

        return sdk;
    }

    @Override
    public boolean startUp(Context context) {
        return super.startup(context);
    }

    public void setScene(SceneInterface ... scenes) {
        for (SceneInterface scene : scenes) {
            nextSceneVector.add(scene);
        }
    }

    public final Point getDisplaySize() {
        return renderer.getDisplaySize();
    }

    public GameObject createEmptyGameObject() {
        return objectFactory.createEmptyObject();
    }

    public GameObject createCamera() {
        return objectFactory.createCamera();
    }

    public GameObject createCamera(Transform transform) {
        return objectFactory.createCamera(transform);
    }

    public void setParent(GameObject parent, GameObject child) {
        objectManager.setParent(parent, child);

    }
}
