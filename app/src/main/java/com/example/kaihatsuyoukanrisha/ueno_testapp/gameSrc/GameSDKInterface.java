package com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc;

import android.content.Context;
import android.graphics.Point;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.gameobject.GameObject;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.opengl.GLRenderer;
import com.example.kaihatsuyoukanrisha.ueno_testapp.scene.SceneInterface;

public interface GameSDKInterface {
    public boolean startUp(Context context);
    public GameObject createGameObject();
    public GameObject createCamera();
    public void setParent(GameObject parent, GameObject child);
    public void setScene(SceneInterface... scenes);
    public Point getDisplaySize();
    public GLRenderer getRenderer();
}
