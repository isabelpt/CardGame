// Isabel Prado-Tucker
// Deck class
import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;
    private int deckSize;

    public Deck(String[] ranks, String[] suits, int[] points) {
        cards = new ArrayList<Card>();
        // Create cards with all combinations of ranks and suits
        if (ranks.length == points.length) {
            deckSize = suits.length * ranks.length;
            cardsLeft = deckSize;
            // For each suit, create a card for every rank/point value
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

    // Returns true if no cards in the deck
    public boolean isEmpty() {
        return cardsLeft == 0;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    // Returns card and removes it from the deck
    public Card deal() {
        if (cards.isEmpty()) { return null; }
        cardsLeft--;
        return cards.remove(cardsLeft);

    }

    // Fisher–Yates shuffle
    public void shuffle() {
        cardsLeft = deckSize;
        for (int i = cardsLeft - 1; i > 0; i--) {
            int index = (int) (Math.random() * i);
            Card temp = cards.remove(index);
            cards.add(index, cards.remove(i - 1));
            cards.add(temp);
        }
    }

    // Splits deck equally into each player's hand and removes them from the deck
    public void splitDeck(Player p1, Player p2) {
        while (cardsLeft != 0) {
            if (cardsLeft % 2 == 0) {
                p1.addCard(deal());
            } else {
                p2.addCard(deal());
            }
        }
    }

    // Runs the t0-string method for each card
    public void printDeck() {
        for (Card card : cards) {
            System.out.println(card.toString());
        }
    }

}
