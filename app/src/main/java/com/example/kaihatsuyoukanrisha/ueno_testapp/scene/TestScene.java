package com.example.kaihatsuyoukanrisha.ueno_testapp.scene;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.GameSDK;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.GameSDKInterface;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.gameobject.GameObject;

public class TestScene extends SceneInterface {

    public TestScene() {
        super("TestScene");
    }

    @Override
    public void setup() {
        GameSDKInterface sdk = GameSDK.getSDK();

        GameObject camera = sdk.createCamera();
        camera.transform.pos.z -= 3.0f;
    }

    @Override
    public void release() {

    }
}
