package components;

import java.awt.image.BufferedImage;

import org.joml.Vector4f;
import renderer.Texture;

public class SpriteRenderer extends Component {

    private Sprite sprite;
    private Vector4f color = new Vector4f(1, 1, 1, 1);
    private transient Transform transform;
    private boolean isDirty = true;
    private transient boolean rotationChanged = false, scaleChanged = false;

    @Override
    public void start() {
        this.transform = gameObject.transform.copy();
    }

    @Override
    public void update(float dt) {
        if(!this.transform.equals(this.gameObject.transform)) {
            isDirty = true;
            if (!this.transform.equalsRotation(this.gameObject.transform)) {
                rotationChanged = true;
            }
            // if (!this.transform.equalsScale(this.gameObject.transform)) {
            //     scaleChanged = true;
            // }
            this.gameObject.transform.copy(this.transform);
        }
    }

    public Texture getTexture() {
        return this.sprite.getTexture();
    }

    public void setLastSpriteImg(BufferedImage image) {
        this.sprite.setLastSpriteImg(image);
    }

    public BufferedImage getLastSpriteImg() {
        return this.sprite.getLastSpriteImg();
    }

    public BufferedImage getDefaultSpriteImg() {
        return this.sprite.getSpriteImg();
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setColor(Vector4f color) {
        this.color = color;
    }

    public Vector4f getColorVec() {
        return this.color;
    }

    

    public boolean isDirty() {
        return this.isDirty;
    }

    public boolean isScaled() {
        return this.scaleChanged;
    }

    public boolean isRotated() {
        return this.rotationChanged;
    }

    public void unflagDirty() {
        this.isDirty = false;
        this.rotationChanged = false;
        this.scaleChanged = false;
    }
}
