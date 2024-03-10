package utils;

import org.joml.Vector2f;
import org.joml.Vector4f;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

public class GameUtils {
    
    public static Vector2f RotatePoint(Vector2f origin, Vector2f point, float rotationDeg) {
        float radius = (float) Math.sqrt(Math.pow(point.x - origin.x, 2f) + Math.pow(point.y - origin.y, 2f));
        // float angle = (float) ((((float) Math.atan((point.x - origin.x) / (point.y - origin.y)) * (180f / (float) Math.PI)) + rotationDeg) * ((float) Math.PI / 180f));
        float angle = (float) Math.toRadians((float) Math.toDegrees(Math.atan((point.y - origin.y) / (point.x - origin.x))) + rotationDeg);

        float x = (float) (radius * Math.cos(angle) * -1f);
        float y = (float) (radius * Math.sin(angle));

        return new Vector2f(x, y);
    }

    public static Vector2f GetBoundSizeOfRotatedRect(float width, float height, float rotationDeg) {
        float w = width;
        float h = height;

        Vector2f bottomLeft = new Vector2f(0f, 0f);
        Vector2f upperLeft = RotatePoint(bottomLeft, new Vector2f(0f, h), rotationDeg);
        Vector2f bottomRight = RotatePoint(bottomLeft, new Vector2f(w, 0f), rotationDeg);
        Vector2f upperRight = RotatePoint(bottomLeft, new Vector2f(w, h), rotationDeg);

        float maxW = max(upperLeft.x, bottomLeft.x, upperRight.x, bottomRight.x) - min(upperLeft.x, bottomLeft.x, upperRight.x, bottomRight.x);
        float maxH = max(upperLeft.y, bottomLeft.y, upperRight.y, bottomRight.y) - min(upperLeft.y, bottomLeft.y, upperRight.y, bottomRight.y);

        return new Vector2f(maxW, maxH);
    }

    public static BufferedImage RotateImage(BufferedImage image, float rotationDeg) {
        if (rotationDeg != 0f) {
            int width = image.getWidth();
            int height = image.getHeight();
            float rotation = -rotationDeg;
    
            Vector2f bottomLeft = new Vector2f(0f, 0f);
            Vector2f upperLeft = RotatePoint(bottomLeft, new Vector2f(0f, height), rotation);
            Vector2f bottomRight = RotatePoint(bottomLeft, new Vector2f(width, 0f), rotation);
            Vector2f upperRight = RotatePoint(bottomLeft, new Vector2f(width, height), rotation);
    
            float maxX = max(upperLeft.x, bottomLeft.x, upperRight.x, bottomRight.x);
            float minY = min(upperLeft.y, bottomLeft.y, upperRight.y, bottomRight.y);

            if (rotation % 90f != 0f) {
                float minX = min(upperLeft.x, bottomLeft.x, upperRight.x, bottomRight.x);
                float maxY = max(upperLeft.y, bottomLeft.y, upperRight.y, bottomRight.y);
                width = (int) Math.floor(maxX - minX);
                height = (int) Math.floor(maxY - minY);    
            }
    
            BufferedImage temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D gTemp = temp.createGraphics();
            gTemp.translate(maxX, -minY);
            gTemp.rotate(Math.toRadians(rotation));
            gTemp.drawImage(image, null, 0, 0);
            gTemp.dispose();

            return temp;
        } else {
            return image;
        }
    }

    // public static BufferedImage RotateImageTest(BufferedImage image, float rotationDeg) {
    //     BufferedImage temp;

    //     if (rotationDeg != 0f) {
    //         int width = image.getWidth();
    //         int height = image.getHeight();
    //         float centerX = ((float) image.getWidth()) / 2f;
    //         float centerY = ((float) image.getHeight()) / 2f;

    //         BufferedImage canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    //         AffineTransform at = new AffineTransform();
    //         at.rotate(Math.toRadians(-rotationDeg), centerX, centerY);
    //         Graphics2D g = canvas.createGraphics();
    //         g.transform(at);
    //         g.drawImage(image, at, null);
    //         g.dispose();
    //         temp = canvas;

    //     } else {
    //         temp = image.getSubimage(0, 0, image.getWidth(), image.getHeight());
    //     }

    //     return temp;
    // }

    public static BufferedImage SetImageColor(BufferedImage image, Vector4f colorVec4) {
        BufferedImage temp;

        if (colorVec4.x != 1f || colorVec4.y != 1f || colorVec4.z != 1f) {
            temp = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

            Color colorTemp;
            Color finalColor;
    
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    if ((image.getRGB(j, i) >> 24) != 0x00 ) {
                        colorTemp = new Color(image.getRGB(j, i));
                        finalColor = new Color((int) ((float) colorTemp.getRed() * colorVec4.x), (int) ((float) colorTemp.getBlue() * colorVec4.y), (int) ((float) colorTemp.getGreen() * colorVec4.z));
                        temp.setRGB(j, i, finalColor.getRGB());
                    }
                }
            }
        } else {
            temp = image.getSubimage(0, 0, image.getWidth(), image.getHeight());
        }
        
        if (colorVec4.w != 1f) {
            Graphics2D gTemp = temp.createGraphics();
            gTemp.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, colorVec4.z));
            gTemp.dispose();    
        }

        return temp;
    }

    public static float max(float a, float b, float c, float d) {
        return Math.max(Math.max(a, b), Math.max(c, d));
    }

    public static float min(float a, float b, float c, float d) {
        return Math.min(Math.min(a, b), Math.min(c, d));
    }
}
