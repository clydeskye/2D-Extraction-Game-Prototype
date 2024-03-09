package scenes;

import java.awt.event.KeyEvent;

import org.joml.Vector2f;
import org.joml.Vector3f;

import components.*;
import components.debug.*;
import game.GameObject;
import game.Prefabs;
import physics2d.components.*;
import physics2d.enums.BodyType;
import utils.AssetPool;
import utils.Const;

public class PhysTestLevelInitializer extends SceneInitializer{

    public PhysTestLevelInitializer() {

    }

    @Override
    public void init(Scene scene) {

        // scene.laod("physics");

        Transform transform = new Transform(new Vector3f(0, 0, 0));
        DebugKeyTrigger trigger = new DebugKeyTrigger(KeyEvent.VK_BACK_SPACE);
        GameObject debug = new GameObject("Debug");
        debug.addComponent(transform);
        debug.transform = transform;
        debug.addComponent(trigger);
        scene.addGameObjectToScene(debug);

        GameObject block3 = Prefabs.generateDemoBlock(-16, 0, 0, 0, BodyType.Static);
        GameObject block = Prefabs.generateDemoBlock(0, 0, 0, 1, BodyType.Static);
        GameObject block2 = Prefabs.generateDemoBlock(16, 0, 0, 1, BodyType.Static);
        GameObject block6 = Prefabs.generateDemoBlock(32, 0, 0, 2, BodyType.Static);
        scene.addGameObjectToScene(block2);
        scene.addGameObjectToScene(block3);
        scene.addGameObjectToScene(block6);
        scene.addGameObjectToScene(block);

        GameObject block4 = Prefabs.generateDemoBlock(-9, 150, 0, 57, BodyType.Dynamic);
        scene.addGameObjectToScene(block4);
        GameObject block5 = Prefabs.generateDemoBlock(7, 125, 0, 56, BodyType.Dynamic);
        scene.addGameObjectToScene(block5);
        GameObject block7 = Prefabs.generateDemoBlock(23, 175, 0, 55, BodyType.Dynamic);
        scene.addGameObjectToScene(block7);
        GameObject block8 = Prefabs.generateDemoBlock(37, 200, 0, 54, BodyType.Dynamic);
        scene.addGameObjectToScene(block8);

        GameObject hero = Prefabs.generateHeroDemo(0, 70, 0);
        scene.addGameObjectToScene(hero);

        GameObject book = scene.createGameObject("Book");
        book.transform.position = new Vector3f(32, 60, 0);
        book.transform.scale = new Vector2f(1f, 1f);
        Sprite sprite = new Sprite();
        sprite.setTexture(AssetPool.getTexture(Const.Img.BOOK));
        SpriteRenderer spr = new SpriteRenderer();
        spr.setSprite(sprite);
        book.addComponent(spr);

        Box2DCollider collider = new Box2DCollider();
        collider.setHalfSize(new Vector2f(11f, 13f));
        collider.setOrigin(new Vector2f(0f, -1.5f));

        book.addComponent(collider);

        Rigidbody2D body = new Rigidbody2D();
        body.setBodyType(BodyType.Dynamic);
        body.setMass(5f);
        body.setFixedRotation(true);
        book.addComponent(body);

        scene.addGameObjectToScene(book);
        scene.save("physics");
        trigger.trigger();
    }

    @Override
    public void loadResources(Scene scene) {

        // textures
        AssetPool.getTexture(Const.Img.DUNGEON_FLOOR);
        AssetPool.getTexture(Const.Img.TILES);
        AssetPool.getTexture(Const.Img.DUNGEON_FLOOR_SPRITESHEET);
        AssetPool.getTexture(Const.Img.KNIGHT_IDLE);
        AssetPool.getTexture(Const.Img.WIZZARD_IDLE);
        AssetPool.getTexture(Const.Img.ROGUE_IDLE);
        AssetPool.getTexture(Const.Img.BOOK);

        // spritesheets
        AssetPool.addSpritesheet(Const.Img.DUNGEON_FLOOR_SPRITESHEET, new Spritesheet(AssetPool.getTexture(Const.Img.DUNGEON_FLOOR_SPRITESHEET), 16, 16, 58));
        AssetPool.addSpritesheet(Const.Img.KNIGHT_IDLE, new Spritesheet(AssetPool.getTexture(Const.Img.KNIGHT_IDLE), 32, 32, 4));
        AssetPool.addSpritesheet(Const.Img.ROGUE_IDLE, new Spritesheet(AssetPool.getTexture(Const.Img.ROGUE_IDLE), 32, 32, 4));
        AssetPool.addSpritesheet(Const.Img.WIZZARD_IDLE, new Spritesheet(AssetPool.getTexture(Const.Img.WIZZARD_IDLE), 32, 32, 4));

        // sounds
        AssetPool.addSound(Const.Sound.WEAPON_SWING, false);
        AssetPool.addSound(Const.Sound.EQUIP_ITEM, false);
    }

}
