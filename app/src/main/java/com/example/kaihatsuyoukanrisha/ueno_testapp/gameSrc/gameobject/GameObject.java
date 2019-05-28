package com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.gameobject;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.component.ComponentInterface;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameSrc.transform.Transform;

import java.util.ArrayList;
import java.util.List;

public class GameObject {
    public Transform transform = new Transform();
    public String name;
    public String tag;
    public int layer;
    private GameObject parent;
    private List<GameObject> children = new ArrayList<>();
    private List<ComponentInterface> componentList = new ArrayList<>();
    private GameObjectManager manager;
    private static FriendGameObjectManagerBridge managerBridge = new FriendGameObjectManagerBridge();

    //Love Friend Style
    static class FriendGameObject extends GameObject {
        //アクセスを許すクラス
        private final String[] permissionClass = {
            "GameObjectManager"
        };

        protected FriendGameObject() {
            for (String name : permissionClass) {
                if (!this.getClass().getSimpleName().startsWith(name)) {
                    throw new RuntimeException();
                }
            }
        }

        protected void init(GameObject object) {
            object.init();
        }

        protected void delete(GameObject object) {
            object.delete();
        }

        protected void update(GameObject object) {
            object.update();
        }

        protected void draw(GameObject object) {
            object.draw();
        }

        protected void setManager(GameObject object, GameObjectManager manager) {
            object.setManager(manager);
        }
    }

    //虹色に輝く夢を見つけに行こ
    static class FriendGameObjectManagerBridge extends GameObjectManager.FriendGameObjectManager {
        @Override
        protected void setParent(GameObjectManager manager, GameObject parent, GameObject child) {
            super.setParent(manager, parent, child);
        }

        @Override
        protected void addGarbage(GameObjectManager manager, GameObject object) {
            super.addGarbage(manager, object);
        }
    }

    public GameObject() {
        tag = "default";
        layer = 0x1;
    }

    private void init() {
        for (ComponentInterface c : componentList) {
            c.init();
        }
    }

    private void delete() {
        for (ComponentInterface c : componentList) {
            c.delete();
        }

        if (!children.isEmpty()) {
            for (GameObject child : children) {
                child.delete();
            }
        }

        componentList.clear();
        componentList = null;
    }

    private void update() {
        for (ComponentInterface c : componentList) {
            c.update();
        }
    }

    private void draw() {

    }

    public <T extends ComponentInterface> T getComponent() {
        T object = null;
        for (ComponentInterface c : componentList) {
            if (c.getClass() == object.getClass()) {
                return (T) c;
            }
        }

        return null;
    }

    private void setManager(GameObjectManager manager) {
        this.manager = manager;
    }

    public void addComponent(ComponentInterface component) {
        componentList.add(component);
    }

    public final GameObject getParent() { return parent; }

    public final List<GameObject> getChildren() { return children; }

    public void setParent(GameObject parent) {
        managerBridge.setParent(manager, parent, this);
    }

    public void setChild(GameObject child) {
        if (child == null) return;

        managerBridge.setParent(manager, this, parent);
    }
}
