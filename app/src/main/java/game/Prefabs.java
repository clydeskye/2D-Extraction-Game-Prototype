package game;

import org.joml.*;
import components.*;
import utils.AssetPool;

public class Prefabs {
    
    public static GameObject generateDemoBlock(float x, float y, float z, int spriteIndex) {
        // Create required components

        Sprite sprite = AssetPool.getSpritesheet(AssetPool.DUNGEON_FLOOR_SPRITESHEET).getSprite(spriteIndex);
        
        SpriteRenderer spriteRenderer = new SpriteRenderer();
        spriteRenderer.setSprite(sprite);
        
        // Create GameObject & add components
        GameObject block = Window.getCurrentScene().createGameObject("Debug Block");
        block.transform.position = new Vector3f(x, y, z);
        block.transform.scale = new Vector2f(0.8f, 0.8f);
        block.addComponent(spriteRenderer);

        return block;
    }

}
