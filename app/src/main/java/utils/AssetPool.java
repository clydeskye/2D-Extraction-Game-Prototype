package utils;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import javax.imageio.ImageIO;

import components.Spritesheet;
import game.Sound;
// import renderer.Shader;
import renderer.Texture;

import java.util.HashMap;
import java.util.Map;

// import components.Spritesheet;

import java.io.File;

public class AssetPool {

    private static Map<String, Texture> textures = new HashMap<>();
    private static Map<String, Spritesheet> spritesheets = new HashMap<>();
    private static Map<String, Sound> sounds = new HashMap<>();

    public static void clear() {
        textures.clear();
        spritesheets.clear();
        sounds.clear();
    }

    public class Utils {
        public static BufferedImage GetSpriteAtlas(String filename) {
            BufferedImage img = null;
    
            // InputStream is = AssetPool.class.getResourceAsStream(filename);
            File file = new File(filename);
            try {
                img = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // try {
                // 	is.close();
                // } catch(IOException e) {
                // 	e.printStackTrace();
                // }
            }
            return img;
        }

        public static void GetAllAssets(String sourceFolder) {

        }

    }


    public static Texture getTexture(String resourceName) {
        File file = new File(resourceName);
        if(AssetPool.textures.containsKey(file.getAbsolutePath())) {
            return AssetPool.textures.get(file.getAbsolutePath());
        } else {
            Texture texture = new Texture();
            texture.setFilepath(resourceName);
            AssetPool.textures.put(file.getAbsolutePath(), texture);
            return texture;
        }
    }

    public static void addSpritesheet(String resourceName, Spritesheet spritesheet) {
        File file = new File(resourceName);
        if(!AssetPool.spritesheets.containsKey(file.getAbsolutePath())) {
            AssetPool.spritesheets.put(file.getAbsolutePath(), spritesheet);
        }
    }

    public static Spritesheet getSpritesheet(String resourceName) {
        File file = new File(resourceName);
        if(!AssetPool.spritesheets.containsKey(file.getAbsolutePath())) {
            assert false : "Error: Tried to acces '" + resourceName + "' and it has not been added tp asset pool.";
        }
        return AssetPool.spritesheets.getOrDefault(file.getAbsolutePath(), null);
    }

    public static Collection<Sound> getAllSounds() {
        return sounds.values();
    }

    public static Sound getSound(String soundFile) {
        File file = new File(soundFile);
        if (sounds.containsKey(file.getAbsolutePath())) {
            return sounds.get(file.getAbsolutePath());
        } else {
            assert false : "Sound file not added '" + soundFile + "'";
        }
        return null;
    }

    public static Sound addSound(String soundFile, boolean loops) {
        File file = new File(soundFile);
        if (sounds.containsKey(file.getAbsolutePath())) {
            return sounds.get(file.getAbsolutePath());
        } else {
            Sound sound = new Sound(file.getAbsolutePath(), loops);
            AssetPool.sounds.put(file.getAbsolutePath(), sound);
            return sound;
        }
    }
}


