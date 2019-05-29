package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.transform;

public class Transform {
    public Vec3 pos;
    public Vec3 rot;
    public Vec3 size;

    public Transform() {
        pos = new Vec3();
        rot = new Vec3();
        size = new Vec3(1,1,1);
    }

    public Transform(Vec3 pos, Vec3 rot, Vec3 scale) {
        this.pos = pos;
        this.rot = rot;
        this.size = scale;
    }
}