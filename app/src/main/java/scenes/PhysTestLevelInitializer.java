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

        scene.laod("physics");

        // scene.save("physics2");
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
        AssetPool.addSpritesheet(Const.Img.DUNGEON_FLOOR_SPRITESHEET, new Spritesheet(AssetPool.getTexture(Const.Img.DUNGEON_FLOOR_SPRITESHEET), 16, 16, 59));
        AssetPool.addSpritesheet(Const.Img.KNIGHT_IDLE, new Spritesheet(AssetPool.getTexture(Const.Img.KNIGHT_IDLE), 32, 32, 4));
        AssetPool.addSpritesheet(Const.Img.ROGUE_IDLE, new Spritesheet(AssetPool.getTexture(Const.Img.ROGUE_IDLE), 32, 32, 4));
        AssetPool.addSpritesheet(Const.Img.WIZZARD_IDLE, new Spritesheet(AssetPool.getTexture(Const.Img.WIZZARD_IDLE), 32, 32, 4));

        // sounds
        AssetPool.addSound(Const.Sound.WEAPON_SWING, false);
        AssetPool.addSound(Const.Sound.EQUIP_ITEM, false);
    }

}
