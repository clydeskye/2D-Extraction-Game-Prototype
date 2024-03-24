package scenes;

import java.awt.event.KeyEvent;

import components.*;
import components.debug.*;
import game.GameObject;
import game.Prefabs;
import utils.AssetPool;
import utils.Const;

public class TestLevelInitializer extends SceneInitializer{

    public TestLevelInitializer() {

    }

    @Override
    public void init(Scene scene) {


        scene.save("level");
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
        AssetPool.getTexture(Const.Img.GREEN_SOLDIER);
        AssetPool.getTexture(Const.Img.BLUE_SOLDIER);


        // spritesheets
        AssetPool.addSpritesheet(Const.Img.DUNGEON_FLOOR_SPRITESHEET, new Spritesheet(AssetPool.getTexture(Const.Img.DUNGEON_FLOOR_SPRITESHEET), 16, 16, 58));
        AssetPool.addSpritesheet(Const.Img.KNIGHT_IDLE, new Spritesheet(AssetPool.getTexture(Const.Img.KNIGHT_IDLE), 32, 32, 4));
        AssetPool.addSpritesheet(Const.Img.ROGUE_IDLE, new Spritesheet(AssetPool.getTexture(Const.Img.ROGUE_IDLE), 32, 32, 4));
        AssetPool.addSpritesheet(Const.Img.WIZZARD_IDLE, new Spritesheet(AssetPool.getTexture(Const.Img.WIZZARD_IDLE), 32, 32, 4));
        AssetPool.addSpritesheet(Const.Img.GREEN_SOLDIER, new Spritesheet(AssetPool.getTexture(Const.Img.GREEN_SOLDIER), 48, 48, 24));
        AssetPool.addSpritesheet(Const.Img.BLUE_SOLDIER, new Spritesheet(AssetPool.getTexture(Const.Img.BLUE_SOLDIER), 48, 48, 24));

        // sounds
        AssetPool.addSound(Const.Sound.WEAPON_SWING, false);
        AssetPool.addSound(Const.Sound.EQUIP_ITEM, false);
    }

}
