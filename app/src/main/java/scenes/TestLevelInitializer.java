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

        // scene.addGameObjectToScene(Prefabs.generateDemoBlock(50f, 0f, 0f));
        
        GameObject block = Prefabs.generateDemoBlock(0, 0, 0);
        DebugTimer timer = new DebugTimer();
        timer.setCountdown(300);
        block.addComponent(timer);
        scene.addGameObjectToScene(block);

        // scene.addGameObjectToScene(Prefabs.generateDemoBlock(-50f, 0f, 0f));


        scene.save();
    }

    @Override
    public void loadResources(Scene scene) {

        AssetPool.getTexture(AssetPool.DUNGEON_FLOOR);

    }

}
