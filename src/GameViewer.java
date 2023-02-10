import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame {
    private Game backend;
    private Image[] cards;
    private Image[] profileIcons;
    private Image background;
    private Image backCard;

    public final int WINDOW_WIDTH = 1000;
    public final int WINDOW_HEIGHT = 800;
    public GameViewer(Game backend) {
        this.backend = backend;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("War Card Game");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);

        background = new ImageIcon("Resources/camo.jpeg").getImage();
        profileIcons = new Image[2];
        profileIcons[0] = new ImageIcon("Resources/computer.png").getImage();
        profileIcons[1] = new ImageIcon("Resources/computer.png").getImage();
        
        // Card should draw itself
        cards = new Image[52];
        for (int i = 1; i < 53; i++) {
            cards[i-1] = new ImageIcon("Resources/Cards/" + i + ".png").getImage();
        }
        // Create back of card image
        backCard = new ImageIcon("Resources/Cards/back.png").getImage();
        // Create stack of cards
    }

    public void paint(Graphics g) {
        // Should have three game statuses: pre, during, and end
        g.setColor(new Color(69, 115, 53));
        //g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);

        g.setColor(Color.white);
        g.setFont(new Font("Serif", Font.PLAIN, 25));
        g.drawString(backend.getP2().getName(), WINDOW_WIDTH / 2 - (60), 60);
        // Dynamic centering?
        g.drawString(backend.getP1().getName(), WINDOW_WIDTH / 2 - (60), WINDOW_HEIGHT - 20);

        g.drawImage(profileIcons[0], WINDOW_WIDTH / 2 - 25, 70, 50, 50, this);
        g.drawImage(profileIcons[1], WINDOW_WIDTH / 2 - 25, WINDOW_HEIGHT - 100, 50, 50, this);

        backend.getP1().drawHand(g, this, WINDOW_HEIGHT - 250, WINDOW_WIDTH / 2 - 70);
        backend.getP2().drawHand(g, this, 150, WINDOW_WIDTH / 2 + 20);

        // Only show when user asks
        backend.getP1().drawHandSize(g, this, 630);
        backend.getP2().drawHandSize(g, this, 200);
    }
}
