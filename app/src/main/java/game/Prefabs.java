package game;

import org.joml.*;
import components.*;
import physics2d.components.*;
import physics2d.enums.BodyType;
import static physics2d.enums.BodyType.*;
import utils.AssetPool;
import utils.Const;

public class Prefabs {

    public class Characters {

        public static GameObject generateSoldierDemo(float x, float y, int type) {
            GameObject soldier;
            Spritesheet spritesheet;

            switch (type) {
                case 0:
                    spritesheet = AssetPool.getSpritesheet(Const.Img.GREEN_SOLDIER);
                    soldier = generateSpriteObjectDemo(spritesheet.getSprite(0), x, y, 1f, 1f);
                    soldier.name = "Green Soldier";
                    break;
                case 1:
                    spritesheet = AssetPool.getSpritesheet(Const.Img.BLUE_SOLDIER);
                    soldier = generateSpriteObjectDemo(spritesheet.getSprite(0), x, y, 1f, 1f);
                    soldier.name = "Blue Soldier";
                    break;
                case 2:
                    spritesheet = AssetPool.getSpritesheet(Const.Img.RED_SOLDIER);
                    soldier = generateSpriteObjectDemo(spritesheet.getSprite(0), x, y, 1f, 1f);
                    soldier.name = "Red Soldier";
                    break;
                default:
                    spritesheet = AssetPool.getSpritesheet(Const.Img.GREEN_SOLDIER);
                    soldier = generateSpriteObjectDemo(spritesheet.getSprite(0), x, y, 1f, 1f);
                    soldier.name = "Green Soldier";
                    break;
            }

            // animation states
            AnimationState idle = new AnimationState();
            idle.title = "Idle";
            idle.setFrames(spritesheet.getSpecificSprites(0, 5), 0.2f);
            idle.setLoop(true);

            AnimationState run = new AnimationState();
            run.title = "Run";
            run.setFrames(spritesheet.getSpecificSprites(10, 6), 0.1f);
            run.setLoop(true);

            AnimationState jump = new AnimationState();
            jump.title = "Jump";
            jump.setFrames(spritesheet.getSpecificSprites(8, 2), 0.1f);
            jump.setLoop(false);

            AnimationState crouch = new AnimationState();
            crouch.title = "Crouch";
            crouch.setFrames(spritesheet.getSpecificSprites(5,3), 0.1f);
            crouch.setLoop(false);

            AnimationState death = new AnimationState();
            death.title = "Death";
            death.setFrames(spritesheet.getSpecificSprites(16,8), 0.1f);
            death.setLoop(false);

            StateMachine stateMachine = new StateMachine();
            stateMachine.addState(run);
            stateMachine.addState(idle);
            stateMachine.addState(jump);
            stateMachine.addState(death);
            stateMachine.addState(crouch);
            
            // animation state triggers
            
            stateMachine.setDefaultState(idle.title);
            stateMachine.addState(run.title, jump.title, "jump");
            stateMachine.addState(idle.title, jump.title, "jump");
            stateMachine.addState(idle.title, crouch.title, "crouch");            
            stateMachine.addState(idle.title, run.title, "startRunning");
            stateMachine.addState(crouch.title, idle.title, "stopCrouching");
            stateMachine.addState(jump.title, idle.title, "stopJumping");
            stateMachine.addState(run.title, idle.title, "stopRunning");

            stateMachine.addState(run.title, death.title, "die");
            stateMachine.addState(idle.title, death.title, "die");
            stateMachine.addState(jump.title, death.title, "die");
            stateMachine.addState(crouch.title, death.title, "die");

            // 10 x 32
            PillboxCollider collider = new PillboxCollider();
            collider.setWidth(10f);
            collider.setHeight(58f);
            collider.offset = new Vector2f(0f, 1f);

            Rigidbody2D body = new Rigidbody2D();
            body.setBodyType(Dynamic);
            body.setContinuousCollision(false);
            body.setFixedRotation(true);
            body.setMass(50.0f);

            PlayerController playerController = new PlayerController();

            soldier.transform.zIndex = 1f;
            soldier.addComponent(stateMachine);
            soldier.addComponent(collider);
            soldier.addComponent(body);
            soldier.addComponent(playerController);

            return soldier;
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
            collider.setOffset(new Vector2f(0f, -2.5f));
    
            Rigidbody2D body = new Rigidbody2D();
            body.setBodyType(BodyType.Dynamic);
            body.setMass(80f);
            body.setFixedRotation(true);
    
            hero.addComponent(collider);
            hero.addComponent(body);
            hero.addComponent(idle);
    
            return hero;
        }
    }
    
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
        block.transform.position = new Vector2f(x, y);
        block.transform.zIndex = z;
        block.transform.scale = new Vector2f(1f, 1f);
        block.addComponent(spriteRenderer);
        block.addComponent(collider);
        block.addComponent(body);

        return block;
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
