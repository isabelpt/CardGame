// Isabel Prado-Tucker
// Player class
import java.awt.*;
import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private String name;
    private int points;
    /** Top card being displayed **/
    private Card topCard;

    public Player(String name) {
        this.name = name;
        points = 0;
        hand = new ArrayList<Card>();
        topCard = new Card("", "", 0);
    }

    public Player(ArrayList<Card> hand, String name) {
        this.hand = hand;
        this.name = name;
        points = 0;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }


    public void addPoints(int points) {
        this.points += points;
    }

    // Adds card to hand
    public void addCard(Card newCard) {
        hand.add(newCard);
    }

    // Adds multiple cards to hand
    // Not necessary to add copy of card because already removed from deck and other player's hand
    public void addCards(ArrayList<Card> newCards) {
        for (Card c : newCards) {
            hand.add(c);
        }
    }

    // Prints the to-string method for each card in hand
    public void printHand() {
        for (Card card : hand) {
            System.out.println(card.toString());
        }
    }

    // Reshuffles hand using Fisher–Yates shuffle
    public void reshuffle() {
        for (int i = handSize() - 1; i > 0; i--) {
            int index = (int) (Math.random() * i);
            Card temp = hand.remove(index);
            hand.add(index, hand.remove(i - 1));
            hand.add(temp);
        }
    }

    /**
     * Draws player's hand.
     * Shows stack of card belonging to player and the top card being compared.
     * @param g
     * @param window
     * @param startY y-value for cards in deck
     * @param startX x-value for top card being displayed
     */
    public void drawHand(Graphics g, GameViewer window, int startY, int startX) {
        int x = window.WINDOW_WIDTH / 2 - 50;
        int y = startY;
        for (int i = 0; i < hand.size() - 1; i++) {
            hand.get(i).drawCard(g, window, x, y, false);
            x += 2;
        }
        topCard.drawCard(g, window, startX, window.WINDOW_HEIGHT / 2 - 115 / 2 + 10, true);
    }

    /**
     * Draw the number of cards a player has to the window
     * @param g
     * @param window
     * @param y top-left y-value for displaying card #
     */
    public void drawHandSize(Graphics g, GameViewer window, int y) {
        g.setColor(Color.white);
        g.setFont(new Font("Serif", Font.PLAIN, 15));
        g.drawString("Cards in hand: " + handSize(), 700, y);
    }

    public int handSize() {
        return hand.size();
    }

    public Card getTopCard() {
            topCard = hand.remove(0);
            return topCard;
    }

    // Returns true if player still has cards in hand
    public boolean hasCards() {
        return !(hand.isEmpty());
    }

    @Override
    public String toString() {
        return name + " has " + points + " points\n" + name + "'s cards: " + hand;
    }
}
