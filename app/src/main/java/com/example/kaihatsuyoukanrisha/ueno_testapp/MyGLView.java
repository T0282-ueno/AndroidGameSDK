package com.example.kaihatsuyoukanrisha.ueno_testapp;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.manager.TextureManager;

import javax.microedition.khronos.opengles.GL10;

public class MyGLView extends GLSurfaceView {
    private GL10 gl;
    private MyRenderer renderer;
    private Context context;
    private TextureManager textureManager;

    public MyGLView(Context context){
        super(context);
        //setEGLContextClientVersion(2);
        //setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        //renderer = new MyRenderer();
        //setRenderer(renderer);
        //setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        //深度バッファ設定
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);

        //レンダー設定
        MyRenderer renderer = new MyRenderer();
        setRenderer(renderer);

        this.gl = renderer.getGL();
        textureManager = new TextureManager(context);
        textureManager.setTexture(gl, R.drawable.space_kaseijin);

        //renderer.setAxis();
    }
}
