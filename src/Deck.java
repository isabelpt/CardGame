// Isabel Prado-Tucker
// Deck class
import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

    public Deck(String[] ranks, String[] suits, int[] points) {
        cards = new ArrayList<Card>();
        if (ranks.length == points.length) {
            cardsLeft = suits.length * ranks.length;
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
    public boolean isEmpty() { return cardsLeft == 0; }

    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card deal() {
        if (cards.isEmpty()) { return null; }
        cardsLeft--;
        return cards.get(cardsLeft);

    }

    public void shuffle() {
        // Adjust this so the number changes based on deck size
        cardsLeft = 52;
        for (int i = cardsLeft - 1; i > 0; i--) {
            int index = (int) (Math.random() * i);
            Card temp = cards.remove(index);
            cards.add(index, cards.remove(i - 1));
            cards.add(temp);
        }
    }

    public void printDeck() {
        for (int i = 0; i < cards.size(); i++)
        {
            System.out.println(cards.get(i).toString());
        }
    }

    public static void main(String[] args) {
        String[] r = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "King", "Queen"};
        String[] s = {"Hearts", "Clubs", "Spades", "Diamonds"};
        int[] p = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        Deck d = new Deck(r, s, p);
        d.shuffle();
        d.printDeck();
    }
}
