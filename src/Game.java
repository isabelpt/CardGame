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
    }

    public void printInstructions() {
        System.out.println("Card game: War");

        // Instructions from bicycle-cards.com
        System.out.println("Each player turns up a card at the same time and the player with the higher card takes both"
                + "cards and puts them, face down, on the bottom of his stack.");
        System.out.println("If the cards are the same rank, it is War");
        System.out.println("Each player turns up one card face down and one card face up.");
        System.out.println(" The player with the higher cards takes both piles (six cards).");
        System.out.println("If the turned-up cards are again the same rank, each player places another card face down"
                + " and turns another card face up.");
        System.out.println("The player with the higher card takes all 10 cards, and so on.");
        System.out.println("The game ends when one player has won all the cards.");
    }

    public void playGame() {
        printInstructions();
        // This method should contain the logic to run your game. For example, if
        // your game were BlackJack, the player would continuously draw cards from the deck until
        // they reached 21, forfeit, or hit a number higher than 21
    }

    public static void main(String[] args) {

    }
}
