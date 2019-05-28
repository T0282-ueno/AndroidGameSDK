package com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc;


import android.content.Context;
import android.graphics.Point;

import com.example.kaihatsuyoukanrisha.ueno_testapp.SampleModel;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.component.Camera;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.factory.ObjectFactory;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.gameobject.GameObject;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.gameobject.GameObjectManager;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.manager.TextureManager;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.opengl.GLRenderer;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.opengl.GLView;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.transform.Transform;
import com.example.kaihatsuyoukanrisha.ueno_testapp.scene.SceneInterface;

import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

public class GameSDK implements Runnable {
    private static GameSDK sdk = null;

    private GLView glView = null;
    private GLRenderer renderer = null;
    private TextureManager textureManager;
    private GameObjectManager objectMediator;
    private ObjectFactory objectFactory = null;
    private Vector<SceneInterface> sceneVector = new Vector<>();
    private Vector<SceneInterface> nextSceneVector = new Vector<>();
    private GL10 gl;

    private boolean whetherUpdate = true;
    private SampleModel model = new SampleModel();

    private GameSDK(){}

    public boolean startup(Context context) {
        glView = new GLView(context);
        renderer = new GLRenderer();
        glView.setRenderer(renderer);
        textureManager = new TextureManager(context);
        objectMediator = new GameObjectManager();
        objectFactory = new ObjectFactory(objectMediator);

        return true;
    }

    public boolean init() {
        changeScene();

        objectMediator.initGameObjects();
        new Thread(this).start();

        return true;
    }

    public boolean uninit() {
        sdk = null;
        renderer = null;
        textureManager = null;
        objectMediator = null;

        return true;
    }

    public void update() {
        if (!checkWhetherUpdate()) {
            return;
        }

        //更新処理
        objectMediator.updateGameObjects();

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
                objectMediator.drawGameObjects();

                model.draw(gl);
            }
            break;
        }

        changeWhetherUpdate(true);
    }

    public static GameSDK getSDK() {
        if (sdk == null) {
            sdk = new GameSDK();
        }

        return sdk;
    }

    public void setGL10(GL10 gl) { this.gl = gl;}

    public void setTexture(int id) { textureManager.setTexture(gl, id); }

    public int getTexture(int id) { return textureManager.getTextureID(id); }

    public GLView getGlView() {
        return glView;
    }

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

    public final Point getDisplaySize() {
        return renderer.getDisplaySize();
    }

    public GameObject createEmptyObject() {
        return objectFactory.createEmptyObject();
    }

    public GameObject createCamera() {
        return objectFactory.createCamera();
    }

    public GameObject createCamera(Transform transform) {
        return objectFactory.createCamera(transform);
    }

    public void setScene(SceneInterface ... scenes) {
        for (SceneInterface scene : scenes) {
            nextSceneVector.add(scene);
        }
    }

    private void changeScene() {
        for (SceneInterface scene : sceneVector) {
            scene.release();
            objectMediator.allDaleteGameObjects();
        }
        sceneVector.clear();
        sceneVector.addAll(nextSceneVector);

        for (SceneInterface scene : sceneVector) {
            scene.setup();
            objectMediator.initGameObjects();
        }
        nextSceneVector.clear();
    }
}
