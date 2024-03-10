package renderer;

import java.awt.Graphics2D;

import org.joml.Vector2f;

import utils.GameUtils;

public class DebugDraw {
    
    public static void drawRect(Graphics2D g, int x, int y, int width, int height, float rotationDeg) {

        if (rotationDeg != 0f) {
            float rotation = -rotationDeg;
            int halfWidth = width / 2;
            int halfHeight = height / 2;
            int centerX = x + halfWidth;
            int centerY = y - halfHeight;
            Vector2f center = new Vector2f(centerX, centerY);

            // Vector2f bottomLeft = new Vector2f(x, y);
            // Vector2f upperLeft = GameUtils.RotatePoint(bottomLeft, new Vector2f(x, height + y), rotation);
            // Vector2f bottomRight = GameUtils.RotatePoint(bottomLeft, new Vector2f(width + x, y), rotation);
            // Vector2f upperRight = GameUtils.RotatePoint(bottomLeft, new Vector2f(width + x, height + y), rotation);

            Vector2f upperLeft = GameUtils.RotatePoint(center, new Vector2f(x, y), rotation);
            Vector2f upperRight = GameUtils.RotatePoint(center, new Vector2f(x + width, y), rotation);
            Vector2f bottomLeft = GameUtils.RotatePoint(center, new Vector2f(x, y - height), rotation);
            Vector2f bottomRight = GameUtils.RotatePoint(center, new Vector2f(width + x, y - height), rotation);

            // System.out.println(x + " | " + y);

            g.drawLine((int) bottomLeft.x, (int)  bottomLeft.y, (int)  bottomRight.x, (int)  bottomRight.y);
            g.drawLine((int) upperLeft.x, (int)  upperLeft.y, (int)  upperRight.x, (int)  upperRight.y);
            g.drawLine((int) upperLeft.x, (int)  upperLeft.y, (int)  bottomLeft.x, (int)  bottomLeft.y);
            g.drawLine((int) upperRight.x, (int)  upperRight.y, (int)  bottomRight.x, (int)  bottomRight.y);
        } else {
            g.drawRect(x, y, width, height);
        }
    }


}
