package physics2d.components;

import components.Component;
import org.joml.Vector2f;

public class CircleCollider extends Component {
    private float radius = 1f;
    protected Vector2f offset = new Vector2f();

    public float getRadius() {
        return radius;
    }

    public Vector2f getOffset() {
        return this.offset;
    }

    public void setOffset(Vector2f newOffset) { this.offset.set(newOffset); }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}