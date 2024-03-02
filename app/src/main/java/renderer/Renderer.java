package renderer;

import game.*;
import components.*;
import utils.*;
import javax.imageio.ImageIO;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Renderer {

    private final Vector4f DEFAULT_COLOR_VEC = new Vector4f(1f, 1f, 1f, 1f);

    private List<SpriteRenderer> spriteRenderers = new ArrayList<>();
    private BufferedImage currentFrame;

    public Renderer() {
        currentFrame = new BufferedImage((int) Window.Width(), (int) Window.Height(), BufferedImage.TYPE_INT_ARGB);
    }

    private void updateFrame() {
        currentFrame = new BufferedImage((int) Window.Width(), (int) Window.Height(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = currentFrame.createGraphics();
        g2d.transform(Window.getCurrentScene().camera().getAffineTransform());
        
        for (SpriteRenderer spr : spriteRenderers) {
            BufferedImage imageTemp;
            Vector3f position = spr.gameObject.transform.position;
            Vector2f scale = spr.gameObject.transform.scale;

            if (spr.isDirty()) {
                imageTemp = spr.getDefaultSpriteImg();

                if (spr.isRotated()) {
                    imageTemp = GameUtils.RotateImage(imageTemp, spr.gameObject.transform.rotation);
                }

                spr.setLastSpriteImg(imageTemp);
                spr.unflagDirty();
            } else {
                imageTemp = spr.getLastSpriteImg();
            }

            if (spr.getColorVec().equals(DEFAULT_COLOR_VEC)) {
                imageTemp = GameUtils.SetImageColor(imageTemp, spr.getColorVec());
            }

            g2d.drawImage(imageTemp, (int) (position.x * Window.Scale()), (int) (position.y * Window.Scale()), (int) (imageTemp.getWidth() * scale.x * Window.Scale()), (int) (imageTemp.getHeight() * scale.y * Window.Scale()), null);
        }

        g2d.dispose();
    }

    public void render(Graphics2D g) {
        if (!spriteRenderers.isEmpty()) {
            updateFrame();
        }
        g.drawImage(currentFrame, 0, 0, Window.Width(), Window.Height(), null);
        
        // g.setColor(Color.RED);
        // g.drawRect(Window.Width() / 2, Window.Height() / 2, 10, 10);
        // g.drawRect(0, 0 / 2, 10, 10);
        // g.drawRect(Window.Width() / 4, Window.Height() / 4, 10, 10);
        // g.dispose();
    }

    
    public void destroyGameObject(GameObject go) {
       
    }

    public void add(GameObject go) {
        
    }

    public void screenshotCurrentFrame() {
        try {
            ImageIO.write(currentFrame, "PNG", new File("app/saves", "screenshot.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // //images
    // BufferedImage imgTmp, image, rotatedImage, combinedImage;
    
    // // Settings Translation
    // Vector2f position = new Vector2f(160f, 90f);
    // Vector2f scale = new Vector2f(1f, 1f);
    // Vector4f cVec = new Vector4f(1f, 1f, 1f, 1f);
    // float roation = 0f;
    
    // private void initTest() {
    //     imgTmp = AssetPool.GetSpriteAtlas(AssetPool.GROUND_SPRITESHEET).getSubimage(0, 0, 16, 16);
    //     image = imgTmp;
    
    
    //     if(cVec.x != 1f || cVec.y != 1f || cVec.z != 1f || cVec.w != 1f)  {
    //         image = GameUtils.SetImageColor(imgTmp, cVec);
    //     }
    
    //     if (roation != 0.0f) {
    //         image = GameUtils.RotateImage(imgTmp, roation);
    //     }
    
    
    
    //     float zoom = 1f;
    //     Vector2f pos = new Vector2f(160f * Window.Scale(), 90f * Window.Scale());
    
    //     float rot = (float) Math.toRadians(0f);
    
    //     AffineTransform aT1 = new AffineTransform();
    
    
    //     combinedImage = new BufferedImage((int) Window.Width(), (int) Window.Height(), BufferedImage.TYPE_INT_ARGB);
    
    
    //     Graphics2D g2 = combinedImage.createGraphics();
    
    //     g2.transform(aT1);
    //     g2.drawImage(image, (int) (position.x * Window.Scale()), (int) (position.y * Window.Scale()), (int) (image.getWidth() * scale.x * Window.Scale()), (int) (image.getHeight() * scale.y * Window.Scale()), null);
    //     g2.dispose();
    //     //
    
    //     // Save final image
    //     try {
    // 		ImageIO.write(combinedImage, "PNG", new File("app/saves", "combined.png"));
    // 	} catch (IOException e) {
    // 		// TODO Auto-generated catch block
    // 		e.printStackTrace();
    // 	}
    
    // }
}
