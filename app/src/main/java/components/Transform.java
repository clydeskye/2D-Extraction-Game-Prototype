package components;

import org.joml.Vector2f;
import org.joml.Vector2f;

public class Transform extends Component {
    public Vector2f position;
    public float zIndex;
    public Vector2f scale;
    public float rotation = 0.0f;

    public Transform() {
        init(new Vector2f(), new Vector2f(), 0.0f);
    }

    public Transform(Vector2f position) {
        init(position, new Vector2f(), 0.0f);
    }

    public Transform(Vector2f position, Vector2f scale) {
        init(position, scale, 0.0f);

    }

    public Transform(Vector2f position, Vector2f scale, float rotation) {
        init(position, scale, rotation);

    }

    public void init(Vector2f position, Vector2f scale, float rotation) {
        this.position = position;
        this.zIndex = position.y;
        this.scale = scale;
    }

    public Transform copy() {
        return new Transform(new Vector2f(this.position), new Vector2f(this.scale));
    }

    public void copy(Transform to) {
        to.position.set(this.position);
        to.scale.set(this.scale);
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(!(o instanceof Transform)) return false;

        Transform t = (Transform)o;
        return t.position.equals(this.position) && t.scale.equals(this.scale) && t.rotation == this.rotation;
    }

    public boolean equalsRotation(Object o) {
        if(o == null) return false;
        if(!(o instanceof Transform)) return false;

        Transform t = (Transform)o;
        return t.rotation == this.rotation;
    }

    public boolean equalsScale(Object o) {
        if(o == null) return false;
        if(!(o instanceof Transform)) return false;

        Transform t = (Transform)o;
        return t.scale.equals(this.scale);
    }

    public boolean equalsZPos(Object o) {
        if(o == null) return false;
        if(!(o instanceof Transform)) return false;

        Transform t = (Transform)o;
        return t.zIndex == this.zIndex;
    }
}
