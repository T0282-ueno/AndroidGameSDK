package com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.manager;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.util.HashMap;
import java.util.Map;

import javax.microedition.khronos.opengles.GL10;

public class TextureManager {
    Map<Integer, Integer> textureMap = new HashMap<>();
    Context context;

    public TextureManager(Context context) {
        this.context = context;
    }

    public boolean setTexture(GL10 gl, int imageID) {
        if (textureMap.containsKey(imageID)) {
            return false;
        }

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imageID);

        if (bitmap == null) {
            return false;
        }

        // テクスチャを生成
        int textures[] = new int[1];
        gl.glGenTextures(1, textures, 0);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

        textureMap.put(imageID, textures[0]);
        return true;
    }

    public void release() {
        textureMap = null;
    }

    public int getTextureID(int imageID) {
        return textureMap.get(imageID);
    }
}
