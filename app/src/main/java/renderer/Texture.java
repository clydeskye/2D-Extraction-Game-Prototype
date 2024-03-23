package renderer;

import java.awt.image.BufferedImage;
import utils.AssetPool;

public class Texture {

	private String filepath;
	private int width, height; //Pixel Unit
	private transient BufferedImage imgTexture;
	
	public Texture() {
		this.filepath = "Generated";
		this.imgTexture = null;
		this.width = -1;
		this.height = -1;
	}
	
	public Texture(String filepath) {
		setFilepath(filepath);
	}
	
	public void setFilepath(String filepath) {
		this.filepath = filepath;
		this.imgTexture = AssetPool.Utils.GetSpriteAtlas(filepath);
		this.width = imgTexture.getWidth();
		this.height = imgTexture.getHeight();
	}

	public String getFilepath() {
		return filepath;
	}

	public BufferedImage getImgTexture() {
		if (imgTexture == null) {
			this.imgTexture = AssetPool.Utils.GetSpriteAtlas(filepath);
			this.width = imgTexture.getWidth();
			this.height = imgTexture.getHeight();	
		}
		return imgTexture;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
