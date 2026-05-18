package Windows.JPanels;

import javax.swing.*;
import java.awt.*;

/**
 * A custom JPanel that scales and draws a background image across its entire area.
 */
public class BackgroundPanel extends JPanel {
    private final Image backgroundImage;

    /**
     * Constructs a BackgroundPanel with the specified image.
     * Sets the panel to be non-opaque so the image shows through properly.
     *
     * @param backgroundImage The image to be drawn as the background.
     */
    public BackgroundPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
        setOpaque(false);
    }

    /**
     * Paints the component, drawing the background image scaled to the
     * width and height of the panel.
     *
     * @param g The Graphics context to draw on.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
