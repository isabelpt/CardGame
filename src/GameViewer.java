/**
 * Front end for game class
 * Displays instructions, each player's deck,
 * current card, and winner.
 * Special graphics for war.
 * @author isabelpt
 */

import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame {
    /** Backend **/
    private Game backend;
    /** Images **/
    private Image[] cards;
    private Image[] profileIcons;
    private Image background1;
    private Image background2;
    private Image backCard;
    /** **/
    private final Color green = new Color(69, 115, 53);
    /** Window dimensions **/
    public final int WINDOW_WIDTH = 1000;
    public final int WINDOW_HEIGHT = 800;

    /**
     * Constructor which initializes the backend,
     * the images, and sets window information.
     *
     * @param backend the game [war] backend
     */
    public GameViewer(Game backend) {
        this.backend = backend;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("War Card Game");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);

        /** Initialize images **/
        background1 = new ImageIcon("Resources/camo.jpeg").getImage();
        background2 = new ImageIcon("Resources/battlefield.jpeg").getImage();
        profileIcons = new Image[2];
        profileIcons[0] = new ImageIcon("Resources/computer.png").getImage();
        profileIcons[1] = new ImageIcon("Resources/profilepic.png").getImage();

        cards = new Image[52];
        for (int i = 1; i < 53; i++) {
            cards[i-1] = new ImageIcon("Resources/Cards/" + i + ".png").getImage();
        }
        backCard = new ImageIcon("Resources/Cards/back.png").getImage();
    }

    /**
     * Draw instructions for war to the window.
     * @param g
     */
    public void drawInstructions(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Serif", Font.PLAIN, 20));
        String[] instructions =
                {"Each player turns up a card at the same time and the player with the higher card takes both",
                        "cards and puts them, face down, on the bottom of his stack.",
                        "If the cards are the same rank, it is War.",
                        "Each player turns up one card face down and one card face up.",
                        "The player with the higher cards takes both piles (six cards).",
                        "If the turned-up cards are again the same rank,",
                        "each player places another card face down and turns another card face up.",
                        "The player with the higher card takes all 10 cards, and so on.",
                        "The game ends when one player has won all the cards."};

        int y = 310;
        for (int i = 0; i < instructions.length; i++) {
            g.drawString(instructions[i], 70, y);
            y += 25; // Line spacing
        }
    }

    /**
     * Draw user names and icons to the window
     * @param g
     */
    public void drawUsers(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Serif", Font.PLAIN, 25));
        /** Draw player names **/
        g.drawString(backend.getP2().getName(), WINDOW_WIDTH / 2 - 60, 60);
        g.drawString(backend.getP1().getName(), WINDOW_WIDTH / 2 - 60, WINDOW_HEIGHT - 20);
        /** Draw player icons [computer/player] **/
        g.drawImage(profileIcons[0], WINDOW_WIDTH / 2 - 25, 70, 50, 50, this);
        g.drawImage(profileIcons[1], WINDOW_WIDTH / 2 - 25, WINDOW_HEIGHT - 100, 50, 50, this);
    }

    /**
     * Draw cards belonging to each player
     * @param g
     */
    public void drawCards(Graphics g) {
        backend.getP1().drawHand(g, this, WINDOW_HEIGHT - 250, WINDOW_WIDTH / 2 - 70);
        backend.getP2().drawHand(g, this, 150, WINDOW_WIDTH / 2 + 20);
    }

    /**
     * Paint the window based on gameStatus
     * @param g
     */
    public void paint(Graphics g) {
        g.drawImage(background1, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);

        /** If start status, print instructions **/
        if (backend.gameStatus == backend.START_GAME) {
            drawUsers(g);
            drawCards(g);
            drawInstructions(g);
        }
        /** If war status, display special graphics **/
        else if (backend.gameStatus == backend.WAR_GAME) {
            g.drawImage(background2, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            g.setColor(green);
            g.setFont(new Font("Serif", Font.BOLD, 125));
            g.drawString("WAR", 80, 250);
            drawUsers(g);
            drawCards(g);
        }
        /** If regular status, run game as normal **/
        else if (backend.gameStatus == backend.REG_GAME) {
            drawUsers(g);
            drawCards(g);
        }
        /** If hand size status, run game as usual and show hand sizes of each player.  **/
        else if (backend.gameStatus == backend.HAND_SIZES) {
            backend.getP1().drawHandSize(g, this, 630);
            backend.getP2().drawHandSize(g, this, 200);
            drawUsers(g);
            drawCards(g);
        }
        /** When game has ended, display winner. **/
        else if (backend.gameStatus == backend.END_GAME) {
            g.setColor(Color.white);
            g.setFont(new Font("TimesRoman", Font.BOLD, 80));
            String winner = backend.getP1().hasCards() ? backend.getP1().getName() : backend.getP2().getName();
            g.drawString(winner + " wins", 200, 400);
        }
    }
}
