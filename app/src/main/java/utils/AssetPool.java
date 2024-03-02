package utils;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import javax.imageio.ImageIO;
// import renderer.Shader;
import renderer.Texture;

import java.util.HashMap;
import java.util.Map;

// import components.Spritesheet;

import java.io.File;

public class AssetPool {

    public static final String SPRITES_PARENT_FOLDER = "app/assets/sprites/";
	public static final String GROUND_SPRITESHEET = "terrain/ground/Tiles.png";
	public static final String DUNGEON_FLOOR = "terrain/ground/Dungeon_floor.png";

    // private static Map<String, Shader> shaders = new HashMap<>();
    private static Map<String, Texture> textures = new HashMap<>();
    // private static Map<String, Spritesheet> spritesheets = new HashMap<>();

    public static BufferedImage GetSpriteAtlas(String filename) {
		BufferedImage img = null;

		// InputStream is = AssetPool.class.getResourceAsStream(filename);
        File file = new File(SPRITES_PARENT_FOLDER + filename);
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

    // public static Shader getShader(String resourceName) {
    //     File file = new File(resourceName);
        
    //     // System.out.println("PATH: " + file.getAbsolutePath());
        
    //     if(AssetPool.shaders.containsKey(file.getAbsolutePath())) {
    //         return AssetPool.shaders.get(file.getAbsolutePath());
    //     } else {
    //         Shader shader = new Shader(resourceName);
    //         shader.compile();
    //         AssetPool.shaders.put(file.getAbsolutePath(), shader);
    //         return shader;
    //     }
    // }

    public static Texture getTexture(String resourceName) {
        File file = new File(SPRITES_PARENT_FOLDER + resourceName);
        if(AssetPool.textures.containsKey(file.getAbsolutePath())) {
            return AssetPool.textures.get(file.getAbsolutePath());
        } else {
            Texture texture = new Texture();
            texture.setFilepath(SPRITES_PARENT_FOLDER + resourceName);
            AssetPool.textures.put(file.getAbsolutePath(), texture);
            return texture;
        }
    }

    // public static void addSpritesheet(String resourceName, Spritesheet spritesheet) {
    //     File file = new File(resourceName);
    //     if(!AssetPool.spritesheets.containsKey(file.getAbsolutePath())) {
    //         AssetPool.spritesheets.put(file.getAbsolutePath(), spritesheet);
    //     }
    // }

    // public static Spritesheet getSpritesheet(String resourceName) {
    //     File file = new File(resourceName);
    //     if(!AssetPool.spritesheets.containsKey(file.getAbsolutePath())) {
    //         assert false : "Error: Tried to acces '" + resourceName + "' and it has not been added tp asset pool.";
    //     }
    //     return AssetPool.spritesheets.getOrDefault(file.getAbsolutePath(), null);
    // }
}


