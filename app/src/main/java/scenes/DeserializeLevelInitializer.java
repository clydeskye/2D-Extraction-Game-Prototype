package scenes;

import utils.AssetPool;
import utils.Const;

public class DeserializeLevelInitializer extends SceneInitializer{

    public DeserializeLevelInitializer() {

    }

    @Override
    public void init(Scene scene) {
        scene.laod("level");
    }

    @Override
    public void loadResources(Scene scene) {

        AssetPool.getTexture(Const.Img.DUNGEON_FLOOR);

    }

}
