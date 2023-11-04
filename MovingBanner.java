
package movingbanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovingBanner extends JFrame {
    private BannerPanel bannerPanel;

    public MovingBanner() {
        setTitle("Moving Banner Example");
        setSize(800, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bannerPanel = new BannerPanel();
        add(bannerPanel);

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bannerPanel.moveText(); // Update banner content position
                bannerPanel.repaint(); // Redraw the panel
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MovingBanner movingBanner = new MovingBanner();
            movingBanner.setVisible(true);
        });
    }

    // Custom JPanel for drawing the banner content
    class BannerPanel extends JPanel {
        private final String bannerText = "THIS BANNER IS MOVING";
        private int xPosition = 0;

        public void moveText() {
            xPosition += 1; // Adjust the position according to your desired speed
            if (xPosition > getWidth()) {
                xPosition = -100; // Reset when it goes out of the frame
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.drawString(bannerText, xPosition, getHeight() / 2);
        }
    }
}




