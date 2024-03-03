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

        scene.addGameObjectToScene(Prefabs.generateDemoBlock(new Vector3f(0, 0 , 0)));
    }

    @Override
    public void loadResources(Scene scene) {

        AssetPool.getTexture(AssetPool.DUNGEON_FLOOR);

    }

}
