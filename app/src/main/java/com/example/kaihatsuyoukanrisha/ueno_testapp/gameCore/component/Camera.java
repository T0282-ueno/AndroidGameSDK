package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.component;

import android.graphics.Point;
import android.opengl.Matrix;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.GameSDK;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.gameobject.GameObject;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.transform.Rect;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.transform.Rectf;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.transform.Vec3;

import static java.lang.StrictMath.abs;

public class Camera extends Component {
    private static final float FOVY = 90.0f;
    private static final float NEAR = 0.01f;
    private static final float FAR = 1000.0f;

    private float[] projectionMatrix;
    private float[] viewMatrix;
    private Rect viewport;
    private Rectf frustumRect = new Rectf(0,0,0,0);
    private float aspect;
    private float near;
    private float far;

    public float fovy;

    public Camera(GameObject object) {
        super(object);

        fovy = FOVY;
        near = NEAR;
        far = FAR;
        projectionMatrix = new float[16];
        viewMatrix = new float[16];
        Matrix.setIdentityM(viewMatrix, 0);
        Matrix.setIdentityM(projectionMatrix, 0);

        Point displaySize = GameSDK.getSDK().getDisplaySize();
        setViewport(new Rect(0, displaySize.x, displaySize.y, 0));
        GameSDK.getSDK().setCamera(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        Vec3 pos = object.transform.getWorldPos();
        Vec3 at = object.transform.getAxisZ().addition(pos);
        Vec3 up = object.transform.getAxisY();
        Matrix.setLookAtM(viewMatrix, 0,
                pos.x, pos.y, pos.z,
                at.x, at.y, at.z,
                up.x, up.y, up.z);
    }

    @Override
    public void delete() {
        projectionMatrix = null;
        viewMatrix = null;
        viewport = null;
        GameSDK.getSDK().removeCamera(this);
    }

    public float[] getViewMatrix() {
        return viewMatrix;
    }

    public float[] getProjectionMatrix() {
        return projectionMatrix;
    }

    public final Rect getViewport() {
        return viewport;
    }

    public void setViewport(final Rect rect) {
        viewport = rect;
        Point size = getViewPortSize();
        aspect = (float)size.x / size.y;

        Matrix.perspectiveM(projectionMatrix, 0, fovy, aspect, near, far);
    }

    public Point getViewPortSize() {
        return new Point(abs(viewport.right - viewport.left), abs(viewport.top - viewport.bottom));
    }

    public void setFovy(final float fovy) {
        this.fovy = fovy;
        Matrix.perspectiveM(projectionMatrix, 0, fovy, aspect, near, far);
    }
}
