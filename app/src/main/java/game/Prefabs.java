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

    public static GameObject generateHeroDemo(float x, float y, int type) {
        Spritesheet spritesheet;
        GameObject hero;
        
        switch (type) {
            case 0:
                spritesheet = AssetPool.getSpritesheet(AssetPool.KNIGHT_IDLE);
                hero = generateSpriteObjectDemo(spritesheet.getSprite(0), x, y, 1f, 1f);
                hero.name = "Knight";
                break;
            case 1:
                spritesheet = AssetPool.getSpritesheet(AssetPool.WIZZARD_IDLE);
                hero = generateSpriteObjectDemo(spritesheet.getSprite(0), x, y, 1f, 1f);
                hero.name = "Wizzard";
                break;
            case 2:
                spritesheet = AssetPool.getSpritesheet(AssetPool.ROGUE_IDLE);
                hero = generateSpriteObjectDemo(spritesheet.getSprite(0), x, y, 1f, 1f);
                hero.name = "Rogue";
                break;
            default:
                spritesheet = AssetPool.getSpritesheet(AssetPool.KNIGHT_IDLE);
                hero = generateSpriteObjectDemo(spritesheet.getSprite(0), x, y, 1f, 1f);
                hero.name = "Knight";
                break;
        }

        AnimationState idle = new AnimationState();
        idle.title = "Idle";
        idle.setLoop(true);
        float frameTime = 0.23f;
        int numFrames = 4;

        for (int i = 0; i < numFrames; i++) {
            idle.addFrame(spritesheet.getSprite(i), frameTime);
        }

        StateMachine stateMachine = new StateMachine();
        stateMachine.addState(idle);
        stateMachine.setDefaultState(idle);
        hero.addComponent(stateMachine);

        return hero;
    }

    public static GameObject generateSpriteObjectDemo(Sprite sprite, float x, float y, float sizeX, float sizeY) {
        GameObject block = Window.getCurrentScene().createGameObject("Generated_Object");
        block.transform.scale.x = sizeX;
        block.transform.scale.y = sizeY;
        block.transform.position.x = x;
        block.transform.position.y = y;
        SpriteRenderer spriteRenderer = new SpriteRenderer();
        spriteRenderer.setSprite(sprite);
        block.addComponent(spriteRenderer);

        return block;
    }

    public static GameObject generateSpriteObject(Sprite sprite, float sizeX, float sizeY) {
        GameObject block = Window.getCurrentScene().createGameObject("Generated_Object");
        block.transform.scale.x = sizeX;
        block.transform.scale.y = sizeY;
        SpriteRenderer spriteRenderer = new SpriteRenderer();
        spriteRenderer.setSprite(sprite);
        block.addComponent(spriteRenderer);

        return block;
    }



}
