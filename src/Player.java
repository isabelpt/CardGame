import java.util.ArrayList;

// Isabel Prado-Tucker
// Player class
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

    public void addCard(Card newCard) {
        hand.add(newCard);
    }

    public void addCards(ArrayList<Card> newCards) {
        for (Card c : newCards) {
            hand.add(c);
        }
    }
    public void tradeCard(Card c, Player other) {
        hand.remove(hand.indexOf(c));
        other.addCard(c);
    }

    public Card getTopCard() {
        return hand.remove(0);
    }

    public boolean hasCards() {
        return hand.isEmpty();
    }

    @Override
    public String toString() {
        return name + " has " + points + " points\n" + name + "'s cards: " + hand;
    }
}
