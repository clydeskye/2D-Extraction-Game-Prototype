package scenes;

import utils.AssetPool;

public class DeserializeLevelInitializer extends SceneInitializer{

    public DeserializeLevelInitializer() {

    }

    @Override
    public void init(Scene scene) {
        scene.laod();
    }

    @Override
    public void loadResources(Scene scene) {

        AssetPool.getTexture(AssetPool.DUNGEON_FLOOR);

    }

}
