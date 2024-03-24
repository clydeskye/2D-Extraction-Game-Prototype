package game;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import components.*;

import utils.AssetPool;

public class GameObject {
    private static int ID_COUNTER = 0;
    private int uid = -1;
    public String name;
    public transient Transform transform;
    private boolean doSerialization = true;
    private boolean isDead = false;
    private List<Component> components;

    public GameObject(String name) {
        this.name = name;
        this.components = new ArrayList<>();
        this.uid = ID_COUNTER++;
        // System.out.println(this + " (CREATED)");
    }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        for(Component c : components) {
            if(componentClass.isAssignableFrom(c.getClass())) {
                try {
                    return componentClass.cast(c);
                } catch(ClassCastException e) {
                    e.printStackTrace();
                    assert false : "Error: Casting component";
                }
            }
        }
        return null;
    }

    public <T extends Component> void removeComponent(Class<T> componeneClass) {
        for(int i = 0; i < components.size() ; i++) {
            Component c = components.get(i);
            if(componeneClass.isAssignableFrom(c.getClass())) {
                components.remove(i);
                return;
            }
        }
    }

    public void addComponent(Component c) {
        c.generateId();
        this.components.add(c);
        c.gameObject = this;
    }

    public void update(float dt) {
        for(int i = 0; i < components.size(); i++) {
            components.get(i).update(dt);
        }
    }

    public void editorUpdate(float dt) {
        for(int i = 0; i < components.size(); i++) {
            components.get(i).editorUpdate(dt);
        }
    }

    public void start() {
        for(int i = 0; i < components.size(); i++) {
            components.get(i).start();
        }
    }

    public static void init(int maxId) {
        ID_COUNTER = maxId;
    }

    public int getUid() {
        return this.uid;
    }

    public List<Component> getAllComponents() {
        return this.components;
    }
    
    public void setSerialize(Boolean bool) {
        this.doSerialization = bool;
    }

    public boolean doSerialization() {
        return this.doSerialization;
    }

    public void destroy() {
        this.isDead = true;
        for(int i = 0; i <components.size(); i++) {
            components.get(i).destroy();
        }
    }

    public boolean isDead() {
        return this.isDead;
    }

    public void setDead() {
        isDead = true;
    }

    public void generateUid() {
        this.uid = ID_COUNTER++;
    }

    @Override
    public String toString() {
        return "'" + getUid() + " " + name + "': [uid: '" + getUid() + "', components: '" + components.size() + "']";
    }

    public GameObject copy() {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Component.class, new ComponentDeserializer())
            .registerTypeAdapter(GameObject.class, new GameObjectDeserializer())
            .enableComplexMapKeySerialization()
            .create();

        String objAsJson = gson.toJson(this);
        GameObject obj = gson.fromJson(objAsJson, GameObject.class);
        obj.generateUid();

        for (Component c : obj.getAllComponents()) {
            c.generateId();
        }
        SpriteRenderer sprite = obj.getComponent(SpriteRenderer.class);
        if (sprite != null && sprite.getTexture() != null) {
            sprite.getSprite().setTexture(AssetPool.getTexture(sprite.getTexture().getFilepath()));
        }
        return obj;
    }

}
