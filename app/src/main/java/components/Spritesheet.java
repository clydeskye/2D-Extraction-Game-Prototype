package components;

import renderer.Texture;
import java.util.*;

public class Spritesheet {
    
    private Texture texture;
    private List<Sprite> sprites;

    public Spritesheet(Texture texture, int spriteWidth, int spriteHeight, int numSpirtes) {
        init(texture, spriteWidth, spriteHeight, numSpirtes, 0, 0);
    }

    public Spritesheet(Texture texture, int spriteWidth, int spriteHeight, int numSpirtes, int horizontalSpace, int verticalSpace) {
        init(texture, spriteWidth, spriteHeight, numSpirtes, horizontalSpace, verticalSpace);
    }

    private void init(Texture texture, int spriteWidth, int spriteHeight, int numSpirtes, int horizontalSpace, int verticalSpace) {
        this.texture = texture;
        this.sprites = new ArrayList<>();

        double texWidth = texture.getWidth();
        double texHeight = texture.getHeight();
        double width = spriteWidth + Math.abs(horizontalSpace);
        double height = spriteHeight + Math.abs(verticalSpace);
        int rows = (int) ((verticalSpace >= 0) ? Math.floor(texHeight / height) : Math.ceil(texHeight / height));
        int columns = (int) ((horizontalSpace >= 0) ? Math.floor(texWidth / width) : Math.ceil(texWidth / width));
        int i = 0;

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                if (i < numSpirtes) {
                    Sprite sprite = new Sprite();
                    sprite.setTexture(
                        this.texture, 
                        (x * (int) width) + ((horizontalSpace <= 0) ? 0 : horizontalSpace), 
                        (y * (int) height) + ((verticalSpace <= 0) ? 0 : verticalSpace), 
                        spriteWidth, 
                        spriteHeight
                    );
                    this.sprites.add(sprite);
                }
                i++;
            }
        }
    }

    public Sprite getSprite(int index) {
        return this.sprites.get(index % size());
    }

    public List<Sprite> getSpriteList() {
        return this.sprites;
    }

    public int size() {
        return this.sprites.size();
    }
}
