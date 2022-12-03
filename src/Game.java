import java.util.ArrayList;

// Isabel Prado-Tucker
// Game class
public class Game {
    private Player p1;
    private Player p2;
    private Deck deck;

    public Game(Player p1, Player p2, Deck deck) {
        this.p1 = p1;
        this.p2 = p2;
        this.deck = deck;
        // Deal the deck and give to players hand
//        String[] r = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "King", "Queen", "Ace"};
//        String[] s = {"Hearts", "Clubs", "Spades", "Diamonds"};
//        int[] p = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
//        Deck d = new Deck(r, s, p);
        this.deck.shuffle();
        this.deck.splitDeck(p1, p2);
    }

    public void printInstructions() {
        System.out.println("Card game: War");

        // Instructions from bicycle-cards.com
        System.out.println("Each player turns up a card at the same time and the player with the higher card takes both"
                + "cards and puts them, face down, on the bottom of his stack.");
        System.out.println("If the cards are the same rank, it is War");
        System.out.println("Each player turns up one card face down and one card face up.");
        System.out.println("The player with the higher cards takes both piles (six cards).");
        System.out.println("If the turned-up cards are again the same rank, each player places another card face down"
                + " and turns another card face up.");
        System.out.println("The player with the higher card takes all 10 cards, and so on.");
        System.out.println("The game ends when one player has won all the cards.");
    }

    public void war(ArrayList<Card> warCards1, ArrayList<Card> warCards2) {
        for (int i = 1; i < 5; i++) {
            warCards1.add(p1.getTopCard());
            warCards2.add(p2.getTopCard());
        }

        if (warCards1.get(warCards1.size() - 1).getPoint() > warCards2.get(warCards2.size() - 1).getPoint()) {
            p1.addCards(warCards1);
            p1.addCards(warCards2);
        } else if (warCards1.get(warCards1.size() - 1).getPoint() < warCards2.get(warCards2.size() - 1).getPoint()) {
            p2.addCards(warCards1);
            p2.addCards(warCards2);
        } else {
            war(warCards1, warCards2);
        }
    }
    public void playGame() {
        printInstructions();
        // Until a player has zero cards
        while (p1.hasCards() && p2.hasCards()) {
            // Each player deals top card from their hand
            Card c1 = p1.getTopCard();
            Card c2 = p2.getTopCard();
            // If not same number they both go to one player
            // If same number enter WAR
            if (c1.getPoint() > c2.getPoint()) {
                p1.addCard(c1);
                p1.addCard(c2);
            } else if (c1.getPoint() > c2.getPoint()) {
                p2.addCard(c1);
                p2.addCard(c2);
            } else {
                ArrayList<Card> warCardsP1 = new ArrayList<Card>();
                warCardsP1.add(c1);
                ArrayList<Card> warCardsP2 = new ArrayList<Card>();
                warCardsP2.add(c2);
                war(warCardsP1, warCardsP2);
            }
            // Each player deals four more cards
            // Values of top card compared and one player gets all 10 cards
        }
        // Winner who still has cards is posted (need method to check for empty hand)
        // P2 always wins at the moment
        if (p1.hasCards())
        {
            System.out.println("P1 wins");
        } else {
            System.out.println("P2 wins");
        }
    }

    public static void main(String[] args) {
        Player p1 = new Player("Isabel");
        Player p2 = new Player("Joel");
        String[] r = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "King", "Queen"};
        String[] s = {"Hearts", "Clubs", "Spades", "Diamonds"};
        int[] p = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        Deck d = new Deck(r, s, p);
        Game g = new Game(p1, p2, d);
        g.playGame();
    }
}
