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

        DebugSoundPlayer soundPlayer = new DebugSoundPlayer(AssetPool.getSound(Const.Sound.WEAPON_SWING), KeyEvent.VK_P);
        DebugSoundPlayer soundPlayer2 = new DebugSoundPlayer(AssetPool.getSound(Const.Sound.EQUIP_ITEM), KeyEvent.VK_O);

        GameObject hero = Prefabs.generateHeroDemo(-40, 0, 0);
        hero.addComponent(soundPlayer);
        hero.addComponent(soundPlayer2);

        GameObject hero1 = Prefabs.generateHeroDemo(0, 0, 1);
        GameObject hero2 = Prefabs.generateHeroDemo(40, 0, 2);

        scene.addGameObjectToScene(hero);
        scene.addGameObjectToScene(hero1);
        scene.addGameObjectToScene(hero2);

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
