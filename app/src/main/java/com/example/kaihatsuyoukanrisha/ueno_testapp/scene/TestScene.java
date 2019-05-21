package com.example.kaihatsuyoukanrisha.ueno_testapp.scene;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.transform.Transform;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.transform.Vec3;

public class TestScene extends SceneInterface {

    public TestScene() {
        super("TestScene");
    }

    @Override
    public void setup() {
        sdk.createCamera(new Transform(new Vec3(0, 0, 1.0f), new Vec3(0,0,0), new Vec3(1,1,1)));
    }

    @Override
    public void release() {

    }
}
