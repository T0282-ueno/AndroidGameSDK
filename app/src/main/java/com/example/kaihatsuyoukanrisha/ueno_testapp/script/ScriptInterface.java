package com.example.kaihatsuyoukanrisha.ueno_testapp.script;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.component.ComponentInterface;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.gameobject.GameObject;

public abstract class ScriptInterface extends ComponentInterface {
    public ScriptInterface(GameObject object) {
        super(object);
    }

    public void init() {}

    public void update() {}

    public void delete() {}

    

}
