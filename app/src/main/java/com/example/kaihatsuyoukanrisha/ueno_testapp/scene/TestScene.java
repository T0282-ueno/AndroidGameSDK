package com.example.kaihatsuyoukanrisha.ueno_testapp.scene;

import com.example.kaihatsuyoukanrisha.ueno_testapp.R;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.GameSDK;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.GameSDKInterface;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.component.mesh.Board;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.gameobject.GameObject;

public class TestScene extends SceneInterface {

    public TestScene() {
        super("TestScene");
    }

    @Override
    public void setup() {
        GameSDKInterface sdk = GameSDK.getSDK();

        GameObject camera = sdk.createCamera();
        camera.transform.pos.z = 10.0f;
        camera.transform.pos.x = 10.0f;


        GameObject object = sdk.createEmptyGameObject();
        //object.transform.pos.z -= 3.0f;
        //object.transform.pos.x -= 0.3f;
        object.addComponent(new Board(object, R.drawable.alien_ufo));
    }

    @Override
    public void release() {

    }
}
