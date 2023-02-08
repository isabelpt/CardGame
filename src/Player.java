// Isabel Prado-Tucker
// Player class
import java.awt.*;
import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private String name;
    private int points;

    public Player(String name) {
        this.name = name;
        points = 0;
        hand = new ArrayList<Card>();
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

    public void drawHand(Graphics g, GameViewer window) {
        hand.get(0).drawCard(g, window);
    }

    public int handSize() {
        return hand.size();
    }

    public Card getTopCard() {
            return hand.remove(0);
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
