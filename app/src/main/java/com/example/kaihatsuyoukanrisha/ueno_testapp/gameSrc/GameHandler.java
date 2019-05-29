package com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc;


import android.content.Context;

import com.example.kaihatsuyoukanrisha.ueno_testapp.SampleModel;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.component.Camera;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.factory.ObjectFactory;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.gameobject.GameObjectManager;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.manager.TextureManager;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.opengl.GLRenderer;
import com.example.kaihatsuyoukanrisha.ueno_testapp.scene.SceneInterface;

import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

public class GameHandler implements Runnable {
    protected GLRenderer renderer = null;
    protected TextureManager textureManager;
    protected GameObjectManager objectManager;
    protected ObjectFactory objectFactory = null;
    protected Vector<SceneInterface> sceneVector = new Vector<>();
    protected Vector<SceneInterface> nextSceneVector = new Vector<>();

    private GL10 gl;
    private boolean whetherUpdate = true;
    private SampleModel model = new SampleModel();

    protected GameHandler() {}

    public boolean startup(Context context) {
        renderer = new GLRenderer(context, this);
        textureManager = new TextureManager(context);
        objectManager = new GameObjectManager();
        objectFactory = new ObjectFactory(objectManager);

        return true;
    }

    public boolean init() {
        changeScene();

        objectManager.initGameObjects();
        new Thread(this).start();

        return true;
    }

    public boolean uninit() {
        renderer.uninit();
        renderer = null;
        textureManager = null;
        objectManager = null;

        return true;
    }

    public void update() {
        if (!checkWhetherUpdate()) {
            return;
        }

        //更新処理
        objectManager.updateGameObjects();

        changeWhetherUpdate(false);
    }

    public void draw(GL10 gl) {
        for (int i = 0; i < 500; i++) {
            if (checkWhetherUpdate()) {
                continue;
            }

            //描画処理
            for (Camera c : Camera.getCameraList()) {
                renderer.beginbRendering(gl, c);
                objectManager.drawGameObjects();

                model.draw(gl);
            }
            break;
        }

        changeWhetherUpdate(true);
    }

    public void setGL10(GL10 gl) { this.gl = gl;}

    protected void setTexture(int id) { textureManager.setTexture(gl, id); }

    protected int getTexture(int id) { return textureManager.getTextureID(id); }

    public GLRenderer getRenderer() { return renderer; }

    @Override
    public void run() {
        while (true) {
            update();
        }
    }

    private boolean checkWhetherUpdate() {
        synchronized (this) {
            return whetherUpdate;
        }
    }

    private void changeWhetherUpdate(boolean flag) {
        synchronized (this) {
            whetherUpdate = flag;
        }
    }

    private void changeScene() {
        for (SceneInterface scene : sceneVector) {
            scene.release();
            objectManager.allDaleteGameObjects();
        }
        sceneVector.clear();
        sceneVector.addAll(nextSceneVector);

        for (SceneInterface scene : sceneVector) {
            scene.setup();
            objectManager.initGameObjects();
        }
        nextSceneVector.clear();
    }
}
