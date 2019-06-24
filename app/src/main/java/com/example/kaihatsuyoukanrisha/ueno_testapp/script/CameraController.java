package com.example.kaihatsuyoukanrisha.ueno_testapp.script;

import android.graphics.PointF;
import android.view.MotionEvent;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.component.ScriptInterface;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.gameobject.GameObject;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.input.AndroidInput;

public class CameraController extends ScriptInterface {
    public CameraController(GameObject object) {
        super(object);
    }

    public void init() {
        int i = 0;
    }

    public void update() {
        if (AndroidInput.hasMove()) {
            PointF move = AndroidInput.getAmountMovement(AndroidInput.TOUCH.ONE);
            object.transform.pos.x += move.x * 0.01f;
            object.transform.pos.y -= move.y * 0.01f;
        }
    }
}
