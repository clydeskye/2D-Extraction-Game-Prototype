package game;

import org.joml.*;
import components.*;
import utils.AssetPool;

public class Prefabs {
    
    public static GameObject generateDemoBlock(float x, float y, float z) {
        Sprite sprite = new Sprite();
        sprite.setTexture(AssetPool.getTexture(AssetPool.DUNGEON_FLOOR), 0, 0, 16, 16);
        
        SpriteRenderer spriteRenderer = new SpriteRenderer();
        spriteRenderer.setSprite(sprite);

        GameObject block = new GameObject("Debug Block");
        block.transform = new Transform(new Vector3f(x, y, z), new Vector2f(0.8f, 0.8f));
        block.addComponent(spriteRenderer);

        return block;
    }

}
