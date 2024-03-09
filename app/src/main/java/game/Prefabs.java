package game;

import org.joml.*;
import components.*;
import physics2d.components.*;
import physics2d.enums.BodyType;
import utils.AssetPool;
import utils.Const;

public class Prefabs {
    
    public static GameObject generateDemoBlock(float x, float y, float z, int spriteIndex, BodyType bodyType) {
        // Create required components

        Sprite sprite = AssetPool.getSpritesheet(Const.Img.DUNGEON_FLOOR_SPRITESHEET).getSprite(spriteIndex);
        
        SpriteRenderer spriteRenderer = new SpriteRenderer();
        spriteRenderer.setSprite(sprite);

        Box2DCollider collider = new Box2DCollider();
        collider.setHalfSize(new Vector2f(16f, 16));
        
        Rigidbody2D body = new Rigidbody2D();
        body.setBodyType(bodyType);
        body.setMass(100f);
        body.setFixedRotation(false);

        // Create GameObject & add components
        GameObject block = Window.getCurrentScene().createGameObject("Debug Block");
        block.transform.position = new Vector3f(x, y, z);
        block.transform.scale = new Vector2f(1f, 1f);
        block.addComponent(spriteRenderer);
        block.addComponent(collider);
        block.addComponent(body);

        return block;
    }

    public static GameObject generateHeroDemo(float x, float y, int type) {
        Spritesheet spritesheet;
        GameObject hero;
        
        switch (type) {
            case 0:
                spritesheet = AssetPool.getSpritesheet(Const.Img.KNIGHT_IDLE);
                hero = generateSpriteObjectDemo(spritesheet.getSprite(0), x, y, 1f, 1f);
                hero.name = "Knight";
                break;
            case 1:
                spritesheet = AssetPool.getSpritesheet(Const.Img.WIZZARD_IDLE);
                hero = generateSpriteObjectDemo(spritesheet.getSprite(0), x, y, 1f, 1f);
                hero.name = "Wizzard";
                break;
            case 2:
                spritesheet = AssetPool.getSpritesheet(Const.Img.ROGUE_IDLE);
                hero = generateSpriteObjectDemo(spritesheet.getSprite(0), x, y, 1f, 1f);
                hero.name = "Rogue";
                break;
            default:
                spritesheet = AssetPool.getSpritesheet(Const.Img.KNIGHT_IDLE);
                hero = generateSpriteObjectDemo(spritesheet.getSprite(0), x, y, 1f, 1f);
                hero.name = "Knight";
                break;
        }

        AnimatedSprite idle = new AnimatedSprite();
        float frameTime = 0.2f;
        int numFrames = 4;

        for (int i = 0; i < numFrames; i++) {
            idle.addFrame(spritesheet.getSprite(i), frameTime);
        }

        Box2DCollider collider = new Box2DCollider();
        collider.setHalfSize(new Vector2f(14f, 27f));
        collider.setOrigin(new Vector2f(8f, 5.5f));

        Rigidbody2D body = new Rigidbody2D();
        body.setBodyType(BodyType.Dynamic);
        body.setMass(50f);
        body.setFixedRotation(true);

        hero.addComponent(collider);
        hero.addComponent(body);
        hero.addComponent(idle);

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
        block.transform.scale.x = sizeX ;
        block.transform.scale.y = sizeY ;
        SpriteRenderer spriteRenderer = new SpriteRenderer();
        spriteRenderer.setSprite(sprite);
        block.addComponent(spriteRenderer);

        return block;
    }



}
