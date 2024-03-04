package renderer;

import game.*;
import components.*;
import utils.*;
import java.util.*;
import javax.imageio.ImageIO;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

public class Renderer {

    private final Vector4f DEFAULT_COLOR_VEC = new Vector4f(1f, 1f, 1f, 1f);
    private List<SpriteRenderer> spriteRenderers = new ArrayList<>();
    private BufferedImage currentFrame;

    public Renderer() {
        currentFrame = new BufferedImage((int) Window.Width(), (int) Window.Height(), BufferedImage.TYPE_INT_ARGB);
    }

    private void updateFrame() {
        currentFrame = new BufferedImage((int) Window.Width(), (int) Window.Height(), BufferedImage.TYPE_INT_ARGB);
        
        if (!spriteRenderers.isEmpty()) {
            boolean isSortSprites = false;
            Graphics2D g2d = currentFrame.createGraphics();
            g2d.transform(Window.getCurrentScene().camera().getAffineTransform());
            
            // check & update dirty sprites
            for (SpriteRenderer spr : spriteRenderers) {
                if (spr.isDirty()) {
                    BufferedImage imageTemp = spr.getDefaultSpriteImg();
    
                    if (spr.isRotated()) {
                        imageTemp = GameUtils.RotateImage(imageTemp, spr.gameObject.transform.rotation);
                    }
    
                    if (spr.isYPosChanged()) {
                        isSortSprites = true;
                    }
    
                    // System.out.println((imageTemp != null) + " " + imageTemp.getWidth() + " " + imageTemp.getHeight());
                    spr.setLastSpriteImg(imageTemp);
                    spr.unflagDirty();
                }
            }
    
            // sort order of sprites based on y-axis
            if (isSortSprites) {
                sortSprites();
            }
            
            // create the image frame to be rendered
            for (SpriteRenderer spr : spriteRenderers) {
                BufferedImage imageTemp = spr.getLastSpriteImg();
                Vector3f position = spr.gameObject.transform.position;
                Vector2f scale = spr.gameObject.transform.scale;
    
                int width = (int) (imageTemp.getWidth() * scale.x * Window.Scale());
                int height = (int) (imageTemp.getHeight() * scale.y * Window.Scale());
                int x = (int) (position.x * Window.Scale());
                int y = (int) -((position.z * Window.Scale()) + (position.y * Window.Scale()) + height);
    
                if (!spr.getColorVec().equals(DEFAULT_COLOR_VEC)) {
                    imageTemp = GameUtils.SetImageColor(imageTemp, spr.getColorVec());
                }
                g2d.drawImage(imageTemp, x, y, width, height, null);
            }
     
            g2d.dispose();
        }
    }

    public void render(Graphics2D g) {
        updateFrame();

        g.drawImage(currentFrame, 0, 0, Window.Width(), Window.Height(), null);

        g.setColor(Color.RED);
        g.drawRect(Window.Width() / 2, Window.Height() / 2, 10, 10);
        g.drawRect(0, 0 / 2, 10, 10);
        g.drawRect(Window.Width() / 4, Window.Height() / 4, 10, 10);
        // g.dispose();
    }

    private void sortSprites() {
        Collections.sort(spriteRenderers);
    }

    public void add(GameObject go) {
        SpriteRenderer spr = go.getComponent(SpriteRenderer.class);
        if(spr != null) {
            spriteRenderers.add(spr);
            sortSprites();
        }
    }

    public void destroyGameObject(GameObject go) {
        if(go.getComponent(SpriteRenderer.class) == null) return;
        SpriteRenderer spr = go.getComponent(SpriteRenderer.class);
        for (int i = 0; i < spriteRenderers.size(); i++) {
            if (spriteRenderers.get(i) == spr) {
                spriteRenderers.remove(i);
                sortSprites();

                
                return;
            }
        }
    }

    public void screenshotCurrentFrame() {
        try {
            ImageIO.write(currentFrame, "PNG", new File("app/saves", "screenshot.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void printSprites() {
        System.out.println("==================");
        for (SpriteRenderer spprrr : spriteRenderers) {
            System.out.println(spprrr);
        }
    }

    // private void add(SpriteRenderer sprite) {
    //     boolean added = false;
    //     for(RenderBatch batch : batches) {
    //         if (batch.hasRoom() && batch.getZIndex() == sprite.gameObject.transform.zIndex) {
    //             Texture tex = sprite.getTexture();
    //             if(tex == null || (batch.hasTexture(tex) || batch.hasTextureRoom())) {
    //                 batch.addSprite(sprite);
    //                 added = true;
    //                 break;
    //             }
    //         }
    //     }

    //     if(!added) {
    //         RenderBatch newBatch = new RenderBatch(MAX_BATCH_SIZE, sprite.gameObject.transform.zIndex, this);
    //         newBatch.start();
    //         batches.add(newBatch);
    //         newBatch.addSprite(sprite);
    //         Collections.sort(batches);
    //     }
    // }
    
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
