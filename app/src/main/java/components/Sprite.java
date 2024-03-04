package components;

import org.joml.Vector2f;
import java.awt.image.BufferedImage;
import renderer.Texture;

public class Sprite {

    private Texture texture = null;
    private int width, height, x, y;
    private transient BufferedImage lastSpriteImg;

    public Texture getTexture() {
        return this.texture;
    }

    public void setTexture(Texture texture) {
        setTexture(texture, 0, 0, texture.getWidth(), texture.getHeight());
    }

    public void setTexture(Texture texture, int x, int y, int width, int height) {
        this.texture = texture;
        this.width = width;
		this.height = height;
		this.x = x;
        this.y = y;
    }

    public BufferedImage getSpriteImg() {
        return this.texture.getImgTexture().getSubimage(y, x, width, height);
    }

    public void setLastSpriteImg(BufferedImage image) {
        this.lastSpriteImg = image;
    }

    public BufferedImage getLastSpriteImg() {
        return this.lastSpriteImg;
    }

    public float getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Vector2f getSize() {
        return new Vector2f(this.width, this.height);
    }
}
