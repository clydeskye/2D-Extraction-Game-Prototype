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
        scene.laod("controller_test");

        // GameObject trigger = new GameObject("Trigger");
        // DebugKeyTrigger reset = new DebugKeyTrigger(KeyEvent.VK_BACK_SPACE, "controller");
        // trigger.addComponent(reset);
        // scene.addGameObjectToScene(trigger);

        
        // for (int i = -9; i < 10; i++) {
        //     scene.addGameObjectToScene(Prefabs.generateDemoBlock(i * 16f, 16f * -5f, 0, 16, BodyType.Static));
        // }
        // scene.addGameObjectToScene(Prefabs.generateDemoBlock(-9 * 16f, 16f * -4f, 0, 16, BodyType.Static));
        // scene.addGameObjectToScene(Prefabs.generateDemoBlock(9 * 16f, 16f * -4f, 0, 16, BodyType.Static));
        
        // scene.addGameObjectToScene(Prefabs.Characters.generateSoldierDemo(0, 70, 1));

        // GameObject soldier2 = Prefabs.Characters.generateSoldierDemo(120, 70, 0);
        // soldier2.transform.scale.x = -1f;
        // soldier2.removeComponent(PlayerController.class);
        // scene.addGameObjectToScene(soldier2);

        // GameObject soldier3 = Prefabs.Characters.generateSoldierDemo(-120, 70, 2);
        // soldier3.removeComponent(PlayerController.class);
        // scene.addGameObjectToScene(soldier3);


        // scene.save("controller_test");
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
        AssetPool.getTexture(Const.Img.GREEN_SOLDIER);
        AssetPool.getTexture(Const.Img.BLUE_SOLDIER);
        AssetPool.getTexture(Const.Img.RED_SOLDIER);


        // spritesheets
        AssetPool.addSpritesheet(Const.Img.DUNGEON_FLOOR_SPRITESHEET, new Spritesheet(AssetPool.getTexture(Const.Img.DUNGEON_FLOOR_SPRITESHEET), 16, 16, 59));
        AssetPool.addSpritesheet(Const.Img.KNIGHT_IDLE, new Spritesheet(AssetPool.getTexture(Const.Img.KNIGHT_IDLE), 32, 32, 4));
        AssetPool.addSpritesheet(Const.Img.ROGUE_IDLE, new Spritesheet(AssetPool.getTexture(Const.Img.ROGUE_IDLE), 32, 32, 4));
        AssetPool.addSpritesheet(Const.Img.WIZZARD_IDLE, new Spritesheet(AssetPool.getTexture(Const.Img.WIZZARD_IDLE), 32, 32, 4));
        AssetPool.addSpritesheet(Const.Img.GREEN_SOLDIER, new Spritesheet(AssetPool.getTexture(Const.Img.GREEN_SOLDIER), 48, 48, 24));
        AssetPool.addSpritesheet(Const.Img.BLUE_SOLDIER, new Spritesheet(AssetPool.getTexture(Const.Img.BLUE_SOLDIER), 48, 48, 24));
        AssetPool.addSpritesheet(Const.Img.RED_SOLDIER, new Spritesheet(AssetPool.getTexture(Const.Img.RED_SOLDIER), 48, 48, 24));


        // sounds
        AssetPool.addSound(Const.Sound.WEAPON_SWING, false);
        AssetPool.addSound(Const.Sound.EQUIP_ITEM, false);
    }

}
