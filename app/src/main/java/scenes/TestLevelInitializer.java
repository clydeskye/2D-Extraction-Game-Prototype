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

        
        GameObject block = Prefabs.generateDemoBlock(0, 0, 0, 56);
        DebugTimer timer = new DebugTimer();
        timer.setCountdown(300);
        block.addComponent(timer);
        
        GameObject block2 = Prefabs.generateDemoBlock(16, 0, 0, 57);
        GameObject block3 = Prefabs.generateDemoBlock(32, 0, 0, 58);
        GameObject block4 = Prefabs.generateDemoBlock(48, 0, 0, 59);

        
        scene.addGameObjectToScene(block);
        scene.addGameObjectToScene(block2);
        scene.addGameObjectToScene(block3);
        scene.addGameObjectToScene(block4);

        scene.save();
    }

    @Override
    public void loadResources(Scene scene) {

        AssetPool.getTexture(AssetPool.DUNGEON_FLOOR);
        AssetPool.getTexture(AssetPool.TILES);
        AssetPool.getTexture(AssetPool.DUNGEON_FLOOR_SPRITESHEET);

        Spritesheet floorTiles = new Spritesheet(AssetPool.getTexture(AssetPool.DUNGEON_FLOOR_SPRITESHEET), 16, 16, 58);
        AssetPool.addSpritesheet(AssetPool.DUNGEON_FLOOR_SPRITESHEET, floorTiles);

    }

}
