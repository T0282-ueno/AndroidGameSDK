package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.component.mesh;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.component.ComponentInterface;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.gameobject.GameObject;
abstract public class MeshInterface extends ComponentInterface {
    public MeshInterface(GameObject object) {
        super(object);
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    abstract public void draw();
}
