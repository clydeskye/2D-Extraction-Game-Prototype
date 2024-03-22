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
    private float renderScale;
    private List<SpriteRenderer> spriteRenderers = new ArrayList<>();
    private List<Box2DCollider> colliders = new ArrayList<>();
    public boolean debug = false;

    private BufferedImage currentFrame;

    public Renderer() {
        currentFrame = new BufferedImage((int) Window.Width(), (int) Window.Height(), BufferedImage.TYPE_INT_ARGB);
        renderScale = Window.Scale() * Const.O_SCALE;
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
    
                    if (spr.isZPosChanged()) {
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
            for (int i = 0; i < spriteRenderers.size(); i++) {
                SpriteRenderer spr = spriteRenderers.get(i);
                BufferedImage imageTemp = spr.getLastSpriteImg();
                Vector2f position = spr.gameObject.transform.position;
                float zIndex = spr.gameObject.transform.zIndex;
                Vector2f scale = spr.gameObject.transform.scale;
                float rotation = spr.gameObject.transform.rotation;
                
                float newX = Math.round(position.x), newY = Math.round(position.y), newZ = Math.round(zIndex);

                int width = (int) (imageTemp.getWidth() * scale.x * renderScale);
                int height = (int) (imageTemp.getHeight() * scale.y * renderScale);
                int x = (int) ((newX - (float) (imageTemp.getWidth() / 2f)) * renderScale);
                int y = (int) -((newZ + newY + (float) (imageTemp.getHeight() / 2f)) * renderScale);

                if (!spr.getColorVec().equals(DEFAULT_COLOR_VEC)) {
                    imageTemp = GameUtils.SetImageColor(imageTemp, spr.getColorVec());
                }
                g2d.drawImage(imageTemp, x, y, width, height, null);

                if (debug) {
                    Box2DCollider collider;
                    if ((collider = colliders.get(i)) != null) {
                        Vector2f origin = collider.getOrigin();
                        Vector2f size = collider.getHalfSize();
    
                        int halfWidth = (int) ((size.x / 2f) * scale.x * renderScale);
                        int halfHeight = (int) ((size.y / 2f) * scale.y * renderScale);
                        int fullWidth = (int) (size.x * scale.x * renderScale);
                        int fullHeight = (int) (size.y * scale.y * renderScale);
                        int offsetX = (int) (origin.x * scale.x * renderScale);
                        int offsetY = (int) (origin.y * scale.y * renderScale);
                        int centerX = x + (width / 2);
                        int centerY = y + (height / 2);
                        int finalX = centerX - halfWidth - offsetX;
                        int finalY = centerY - halfHeight - offsetY;
                        
                        g2d.setColor(Color.red);
                        DebugDraw.drawRect(g2d, finalX, finalY, fullWidth, fullHeight, rotation);
                    }
                }

            }

            g2d.dispose();
        }
    }

    public void render(Graphics2D g) {
        updateFrame();

        g.drawImage(currentFrame, 0, 0, Window.Width(), Window.Height(), null);

        g.setColor(Color.RED);
        g.drawRect(Window.Width() / 2, Window.Height() / 2, 10, 10);
    }

    private void sortSprites() {
        if (!debug) {
            Collections.sort(spriteRenderers);
        }
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
        Box2DCollider collider = go.getComponent(Box2DCollider.class);
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
