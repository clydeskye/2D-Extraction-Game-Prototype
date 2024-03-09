package renderer;

import game.*;
import physics2d.components.Box2DCollider;
import physics2d.components.Collider;
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
    private List<Collider> colliders = new ArrayList<>();


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
                if (spr.getLastSpriteImg() == null) {
                    spr.setLastSpriteImg(spr.getDefaultSpriteImg());
                }

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
    
                int width = (int) (imageTemp.getWidth() * scale.x * Window.Scale() * Const.O_SCALE);
                int height = (int) (imageTemp.getHeight() * scale.y * Window.Scale() * Const.O_SCALE);
                int x = (int) Math.round(position.x * Window.Scale() * Const.O_SCALE);
                int y = (int) -(Math.round((position.z * Window.Scale() * Const.O_SCALE) + (position.y * Window.Scale() * Const.O_SCALE) + height));
    
                if (!spr.getColorVec().equals(DEFAULT_COLOR_VEC)) {
                    imageTemp = GameUtils.SetImageColor(imageTemp, spr.getColorVec());
                }
                g2d.drawImage(imageTemp, x, y, width, height, null);

                // g2d.setColor(Color.red);
                // g2d.drawRect(x, y, width, height);
            }

            // for (int i = 0; i < spriteRenderers.size(); i++) {
            //     SpriteRenderer spr = spriteRenderers.get(i);
            //     BufferedImage imageTemp = spr.getLastSpriteImg();
            //     Vector3f position = spr.gameObject.transform.position;
            //     Vector2f scale = spr.gameObject.transform.scale;
    
            //     int width = (int) (imageTemp.getWidth() * scale.x * Window.Scale() * Const.O_SCALE);
            //     int height = (int) (imageTemp.getHeight() * scale.y * Window.Scale() * Const.O_SCALE);
            //     int x = (int) (position.x * Window.Scale() * Const.O_SCALE);
            //     int y = (int) -((position.z * Window.Scale() * Const.O_SCALE) + (position.y * Window.Scale() * Const.O_SCALE) + height);
    
            //     if (!spr.getColorVec().equals(DEFAULT_COLOR_VEC)) {
            //         imageTemp = GameUtils.SetImageColor(imageTemp, spr.getColorVec());
            //     }
            //     g2d.drawImage(imageTemp, x, y, width, height, null);

            //     // Box2DCollider collider;
            //     // if ((collider = (Box2DCollider) colliders.get(i)) != null) {
            //     //     int xC = (int) ((position.x * scale.x * Window.Scale() * Const.O_SCALE) + collider.getOrigin().x);
            //     //     int yC = (int) -((position.y + collider.getOrigin().y) * scale.x * Window.Scale() * Const.O_SCALE);

            //     //     // System.out.println(collider.getOrigin().x);

            //     //     g2d.setColor(Color.red);
            //     //     g2d.drawRect(xC, yC - height, width, height);
            //     // }
            // }
     
            g2d.dispose();
        }
    }

    public void render(Graphics2D g) {
        updateFrame();

        g.drawImage(currentFrame, 0, 0, Window.Width(), Window.Height(), null);

        // g.setColor(Color.RED);
        // g.drawRect(Window.Width() / 2, Window.Height() / 2, 10, 10);
        // g.drawRect(0, 0 / 2, 10, 10);
        // g.drawRect(Window.Width() / 4, Window.Height() / 4, 10, 10);
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
        addCollider(go);
    }

    private void addCollider(GameObject go) {
        Box2DCollider collider = (Box2DCollider) go.getComponent(Collider.class);
        if(collider != null) {
            colliders.add(collider);
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
}
