// Isabel Prado-Tucker
// Deck class
import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;
    private int deckSize;

    public Deck(String[] ranks, String[] suits, int[] points) {
        cards = new ArrayList<Card>();
        if (ranks.length == points.length) {
            deckSize = suits.length * ranks.length;
            cardsLeft = deckSize;
            for (String suit : suits) {
                for (int i = 0; i < points.length; i++)
                {
                    Card c = new Card(ranks[i], suit, points[i]);
                    cards.add(c);
                }
            }
        }
        else {
            return;
        }
    }

    // Is it better to make one line functions on a single line or with line breaks
    public boolean isEmpty() {
        return cardsLeft == 0;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card deal() {
        if (cards.isEmpty()) { return null; }
        cardsLeft--;
        return cards.get(cardsLeft);

    }

    public void shuffle() {
        cardsLeft = deckSize;
        for (int i = cardsLeft - 1; i > 0; i--) {
            int index = (int) (Math.random() * i);
            Card temp = cards.remove(index);
            cards.add(index, cards.remove(i - 1));
            cards.add(temp);
        }
    }

    public void splitDeck(Player p1, Player p2) {
        int halfDeck = deckSize / 2;
        for (int i = 0 ; i < halfDeck; i++) {
            p1.addCard(cards.get(i));
        }
        for (int i = halfDeck; i < deckSize; i++) {
            p2.addCard(cards.get(i));
        }
        cardsLeft = 0;
    }

    public void printDeck() {
        for (int i = 0; i < cards.size(); i++)
        {
            System.out.println(cards.get(i).toString());
        }
    }

}
