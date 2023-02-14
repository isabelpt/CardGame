// Isabel Prado-Tucker
// Game class
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private GameViewer window;
    private Player p1;
    private Player p2;
    private Deck deck;
    /** Game statuses to determine window views **/
    public int gameStatus;
    public final int START_GAME = 0;
    public final int HAND_SIZES = 1;
    public final int REG_GAME = 2;
    public final int WAR_GAME = 3;
    public final int END_GAME = 4;

    // Color values from https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\033[0;33m";
    public Game(Player p1, Player p2, Deck deck) {
        this.p1 = p1;
        this.p2 = p2;
        this.deck = deck;
        this.deck.shuffle();
        this.deck.splitDeck(p1, p2);

        gameStatus = START_GAME;

        window = new GameViewer(this);
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public boolean isWar() {
        return p1.getHand().get(0).getPoint() == p2.getHand().get(0).getPoint() ? true : false;
    }
    // Prints instructions for how to play war
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

    // Prints player names to establish format of the game board
    public void printNames() {
        String output = "————————————————————————————————————————————————————-\n";
        // Calculate spaces based on 24 total characters
        int spaces1 = 24 - p1.getName().length();
        int spaces2 = 24 - p2.getName().length();
        output += "| " + p1.getName();
        // Add spaces
        for (int i = 0; i < spaces1; i++) {
            output += " ";
        }
        output += "| " + p2.getName();
        for (int i = 0; i < spaces2; i++) {
            output += " ";
        }
        output += "|";
        System.out.println(output);
    }

    // Print cards from one round, based on makeCard method from Card class
    public void printTwoCards(Card c1, Card c2, int winner) {
        String[][] art = {c1.makeCard(), c2.makeCard()};
        String output = "————————————————————————————————————————————————————-\n";
        output += "| " + c1.toString();
        // Calculate spaces based on 24 total characters
        int spaces1 = 24 - c1.toString().length();
        // Add spaces
        for (int i = 0; i < spaces1; i++) {
            output += " ";
        }
        output += "| " + c2.toString();
        int spaces2 = 24 - c2.toString().length();
        for (int i = 0; i < spaces2; i++) {
            output += " ";
        }
        output += "|\n";
        // Based on winner of round, make winning card green
        if (winner == 1) {
            output += "|    " + ANSI_GREEN + art[0][0] + ANSI_RESET + "     |    " + art[1][0] + "     |\n";
            for (int i = 1; i < art[0].length; i++) {
                output += "|    " + ANSI_GREEN + art[0][i] + ANSI_RESET + "    |    " + art[1][i] + "    |\n";
            }
        } else if (winner == 2){
            output += "|    " + art[0][0] + "     |    "+ ANSI_GREEN + art[1][0] + ANSI_RESET + "     |\n";
            for (int i = 1; i < art[0].length; i++) {
                output += "|    " + art[0][i] + "    |    "+ ANSI_GREEN + art[1][i] + ANSI_RESET + "    |\n";
            }
        } else {
            output += "|    " + art[0][0] + "     |    " + art[1][0] + "     |\n";
            for (int i = 1; i < art[0].length; i++) {
                output += "|    " + art[0][i] + "    |    " + art[1][i] +  "    |\n";
            }
        }

        System.out.print(output);
    }

    // Print cards left from each player
    public void printHandSizes() {
        String output = "————————————————————————————————————————————————————-\n" + "| " + p1.getName() + ": " + p1.handSize();
        // Calculate spaces based on 22 total characters
        int spaces = 22 - p1.getName().length() - String.valueOf(p1.handSize()).length();
        // Add spaces
        for (int i = 0; i < spaces; i++) {
            output += " ";
        }
        output += "| " + p2.getName() + ": " + p2.handSize();
        spaces = 22 - p2.getName().length() - String.valueOf(p2.handSize()).length();
        for (int i = 0; i < spaces; i++) {
            output += " ";
        }
        output += "|";
        System.out.println(output);
    }

    // Print war ascii text in green and yellow
    public void printWar() {
        // 'WAR!' ascii text from textkool.com
        String[] message = {"██     ██  █████  ██████  ██ ",
                            "██     ██ ██   ██ ██   ██ ██ ",
                            "██  █  ██ ███████ ██████  ██ ",
                            "██ ███ ██ ██   ██ ██   ██    ",
                            " ███ ███  ██   ██ ██   ██ ██ ",
                            "                             "};
        System.out.println("|                                                   |");
        for (int i = 0; i < message.length; i++) {
            int spaces = (52 - message[i].length()) / 2;
            System.out.print("|");
            for (int j = 0; j < spaces; j++) {
                System.out.print(" ");
            }
            // Alternate green and yellow colors
            if (i % 2 == 0) {
                System.out.print(ANSI_GREEN);
            } else {
                System.out.print(ANSI_YELLOW);
            }
            System.out.print(message[i]+ ANSI_RESET);
            for (int j = 0; j < spaces; j++) {
                System.out.print(" ");
            }
            System.out.println("|");
        }
        System.out.println("————————————————————————————————————————————————————-");
    }

    // Run war, dealing cards from player's hands in sets of four until no longer a tie
    public void war(ArrayList<Card> warCards1, ArrayList<Card> warCards2) {
        Card blank = new Card(" ", "", 0);
        // Get four cards from each player, assuming they have cards, and print the blank cards
        for (int i = 1; i < 5; i++) {
            if (p1.hasCards()) {
                warCards1.add(p1.getTopCard());
            }
            if (p2.hasCards()) {
                warCards2.add(p2.getTopCard());
            }
            if (i < 4) {
                printTwoCards(blank, blank, 0);
            }
        }

        for (Card c : warCards1) {
            c.setVisible(true);
        }

        for (Card c : warCards2) {
            c.setVisible(true);
        }

        // Find winner and give all war cards to winner's hand, unless tie in which case start new round of war
        if (warCards1.get(warCards1.size() - 1).getPoint() > warCards2.get(warCards2.size() - 1).getPoint()) {
            p1.addCards(warCards1);
            p1.addCards(warCards2);
            printTwoCards(warCards1.get(warCards1.size() - 1), warCards2.get(warCards2.size() - 1), 1);
        } else if (warCards1.get(warCards1.size() - 1).getPoint() < warCards2.get(warCards2.size() - 1).getPoint()) {
            p2.addCards(warCards1);
            p2.addCards(warCards2);
            printTwoCards(warCards1.get(warCards1.size() - 1), warCards2.get(warCards2.size() - 1), 2);
        } else {
            printTwoCards(warCards1.get(warCards1.size() - 1), warCards2.get(warCards2.size() - 1), 0);
            war(warCards1, warCards2);
        }
    }

    // Play game and return winning player
    public Player playGame() {
        window.repaint();
        Scanner s = new Scanner(System.in);
        printInstructions();
        printNames();
        // Until a player has zero cards
        while (p1.hasCards() && p2.hasCards()) {
            System.out.println("————————————————————————————————————————————————————-");
            System.out.print("Press enter to play, 'hand' for hand sizes, or 'reshuffle': ");
            String input = s.nextLine();
           // window.repaint();
            if (input.equals("hand")) {
                gameStatus = HAND_SIZES;
                window.repaint();
                printHandSizes();
            } else if (input.equals("reshuffle")) {
                p1.reshuffle();
                System.out.println("————————————————————————————————————————————————————-");
                System.out.println("Your deck is now reshuffled ");
            } else {
                gameStatus = REG_GAME;
                Card c1 = p1.getTopCard();
                Card c2 = p2.getTopCard();
                c1.setVisible(true);
                c2.setVisible(true);
                window.repaint();
                // If not same number they both go to one player
                // If same number enter WAR
                if (c1.getPoint() > c2.getPoint()) {
                    p1.addCard(c1);
                    p1.addCard(c2);
                    printTwoCards(c1, c2, 1);
                } else if (c1.getPoint() < c2.getPoint()) {
                    p2.addCard(c2);
                    p2.addCard(c1);
                    printTwoCards(c1, c2, 2);
                } else {
                    // Run war
                    // Impliment some kind of sleep thing where it puts 3 face down
                    // And then does the top one
                    gameStatus = WAR_GAME;
                    printTwoCards(c1, c2, 0);
                    System.out.println("————————————————————————————————————————————————————-");
                    printWar();
                    System.out.print("Press enter to proceed: ");
                    s.nextLine();
                    ArrayList<Card> warCardsP1 = new ArrayList<Card>();
                    warCardsP1.add(c1);
                    ArrayList<Card> warCardsP2 = new ArrayList<Card>();
                    warCardsP2.add(c2);
                    war(warCardsP1, warCardsP2);
                    window.repaint();
                }
                }

            }

        gameStatus = END_GAME;
        // Winner who still has cards is printed and returned
        if (p1.hasCards())
        {
            System.out.println(p1.getName() + " wins");
            return p1;
        } else {
            System.out.println(p2.getName() + " wins");
            return p2;
        }
    }

    public static void main(String[] args) {
        // To-Do: remove single letter variables
        Scanner s = new Scanner(System.in);
        System.out.print("Name: ");
        String name = s.nextLine();
        Player p1 = new Player(name);
        Player p2 = new Player("Computer");
        // Full Deck
        String[] r1 = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] s1 = {"Hearts", "Clubs", "Spades", "Diamonds"};
        int[] points1 = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        // Abridged deck
        String[] r2 = {"2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] s2 = {"Hearts", "Clubs"};
        int[] points2 = {2, 3, 4, 5, 6, 7, 8, 9, 10};
        Deck d = new Deck(r1, s1, points1);
        Game g = new Game(p1, p2, d);
        g.playGame();
    }
}
