package scenes;

import org.joml.*;
import components.*;
import game.GameObject;
import game.Prefabs;
import utils.AssetPool;

public class TestLevelInitializer extends SceneInitializer{


    public TestLevelInitializer() {

    }

    @Override
    public void init(Scene scene) {

        
        // GameObject block = Prefabs.generateDemoBlock(0, 0, 0, 56);
        // DebugTimer timer = new DebugTimer();
        // timer.setCountdown(300);
        // block.addComponent(timer);
        
        // GameObject block2 = Prefabs.generateDemoBlock(16, 0, 0, 57);
        // GameObject block3 = Prefabs.generateDemoBlock(32, 0, 0, 58);
        // GameObject block4 = Prefabs.generateDemoBlock(48, 0, 0, 59);
        // scene.addGameObjectToScene(block);
        // scene.addGameObjectToScene(block2);
        // scene.addGameObjectToScene(block3);
        // scene.addGameObjectToScene(block4);

        GameObject hero = Prefabs.generateHeroDemo(-40, 0, 0);
        GameObject hero1 = Prefabs.generateHeroDemo(0, 0, 1);
        GameObject hero2 = Prefabs.generateHeroDemo(40, 0, 2);

        scene.addGameObjectToScene(hero);
        scene.addGameObjectToScene(hero1);
        scene.addGameObjectToScene(hero2);

        scene.save();
    }

    @Override
    public void loadResources(Scene scene) {

        AssetPool.getTexture(AssetPool.DUNGEON_FLOOR);
        AssetPool.getTexture(AssetPool.TILES);
        AssetPool.getTexture(AssetPool.DUNGEON_FLOOR_SPRITESHEET);
        AssetPool.getTexture(AssetPool.KNIGHT_IDLE);
        AssetPool.getTexture(AssetPool.WIZZARD_IDLE);
        AssetPool.getTexture(AssetPool.ROGUE_IDLE);

        Spritesheet floorTiles = new Spritesheet(AssetPool.getTexture(AssetPool.DUNGEON_FLOOR_SPRITESHEET), 16, 16, 58);
        AssetPool.addSpritesheet(AssetPool.DUNGEON_FLOOR_SPRITESHEET, floorTiles);
        Spritesheet knight_Idle = new Spritesheet(AssetPool.getTexture(AssetPool.KNIGHT_IDLE), 32, 32, 4);
        AssetPool.addSpritesheet(AssetPool.KNIGHT_IDLE, knight_Idle);
        Spritesheet rogue_Idle = new Spritesheet(AssetPool.getTexture(AssetPool.ROGUE_IDLE), 32, 32, 4);
        AssetPool.addSpritesheet(AssetPool.ROGUE_IDLE, rogue_Idle);
        Spritesheet wizzard_Idle = new Spritesheet(AssetPool.getTexture(AssetPool.WIZZARD_IDLE), 32, 32, 4);
        AssetPool.addSpritesheet(AssetPool.WIZZARD_IDLE, wizzard_Idle);

    }

}
