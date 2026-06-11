package smartrent;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageGenerator {
    public static void main(String[] args) {
        generateImages("src/Images");
    }

    public static void generateImages(String dirPath) {
        try {
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 1. home.png
            BufferedImage home = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = home.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.WHITE);
            // Roof
            int[] xPoints = {16, 4, 28};
            int[] yPoints = {6, 16, 16};
            g.fillPolygon(xPoints, yPoints, 3);
            // Body
            g.fillRect(7, 16, 18, 11);
            // Door
            g.setColor(new Color(0,0,0,0));
            g.setComposite(AlphaComposite.Clear);
            g.fillRect(14, 20, 4, 7);
            g.dispose();
            ImageIO.write(home, "PNG", new File(dir, "home.png"));

            // 2. user.png
            BufferedImage user = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
            g = user.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.WHITE);
            // Head
            g.fillOval(11, 4, 10, 10);
            // Torso
            g.fillArc(6, 16, 20, 20, 0, 180);
            g.dispose();
            ImageIO.write(user, "PNG", new File(dir, "user.png"));

            // 3. clipboard-list.png
            BufferedImage clip = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
            g = clip.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.WHITE);
            // Board
            g.drawRoundRect(8, 6, 16, 21, 2, 2);
            // Lines
            g.drawLine(12, 13, 20, 13);
            g.drawLine(12, 17, 20, 17);
            g.drawLine(12, 21, 17, 21);
            // Clip
            g.fillRect(13, 4, 6, 3);
            g.dispose();
            ImageIO.write(clip, "PNG", new File(dir, "clipboard-list.png"));

            // 4. delete-user.png
            BufferedImage delUser = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
            g = delUser.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.WHITE);
            // Head
            g.fillOval(10, 4, 10, 10);
            // Torso
            g.fillArc(5, 16, 20, 20, 0, 180);
            // Small 'x'
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(2));
            g.drawLine(22, 18, 28, 24);
            g.drawLine(28, 18, 22, 24);
            g.dispose();
            ImageIO.write(delUser, "PNG", new File(dir, "delete-user.png"));

            System.out.println("Images generated successfully in: " + dir.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
