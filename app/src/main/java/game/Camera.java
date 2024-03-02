package game;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import org.joml.Vector2f;

public class Camera {
    
    public Vector2f position;
    private float zoom,  roation;
    private Vector2f projectionSize;
    private AffineTransform aT;
    private Rectangle2D.Float cameraBounds;

    public Camera(Vector2f position) {
        this.position = position;
        this.zoom = 1f;
        this.roation = 0f;
        projectionSize = new Vector2f(Window.Width(), Window.Height());
        aT = new AffineTransform();   
    }

    public void update() {
        // aT1.scale(zoom, zoom);
        // aT1.translate(-(pos.x - (((float) Window.Width() / zoom) / 2f)), -(pos.y - (((float) Window.Height() / zoom) / 2f)));
        // aT1.rotate(rot, pos.x, pos.y);
    }

    private void updateProjection() {
        aT.setToIdentity();

        projectionSize.x = ((float) Window.Width()) / zoom;
        projectionSize.y = ((float) Window.Height()) / zoom;

        aT.scale(zoom, zoom);
        aT.translate(-(position.x - (projectionSize.x / 2f)), -(position.y - (projectionSize.y / 2f)));
        aT.rotate(Math.toRadians(-roation), position.x, position.y);
    }

    public AffineTransform getAffineTransform() {
        updateProjection();
        return this.aT;
    }

    public Vector2f getPosition() {
        return this.position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public void setXPos(float xPosition) {
        this.position.x = xPosition;
    }

    public void setYPos(float yPosition) {
        this.position.y = yPosition;
    }

    public float getZoom() {
        return this.zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public float getRoation() {
        return this.roation;
    }

    public void setRoation(float roation) {
        this.roation = roation;
    }

    public Vector2f getProjectionSize() {
        return this.projectionSize;
    }

    public void setProjectionSize(Vector2f projectionSize) {
        this.projectionSize = projectionSize;
    }
}
