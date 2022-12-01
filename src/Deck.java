import java.util.ArrayList;

public class Deck {
    private ArrayList cards;
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

    public void printthis() {
        for (int i = 0; i < cards.size(); i++)
        {
            System.out.println(cards.get(i).toString());
        }
    }

    public static void main(String[] args) {
        String[] r = {"A", "2", "3"};
        String[] s = {"Hearts", "Clubs"};
        int[] p = {1, 2, 3};
        Deck d = new Deck(r, s, p);
        d.printthis();
    }
}
