import java.util.Objects;

// Isabel Prado-Tucker
// Card class
public class Card {
    // Instance Variables
    private String rank;
    private String suit;
    private int point;
    private String[] asciiArt;

    // Constructors
    public Card(String rank, String suit, int point) {
        this.rank = rank;
        this.suit = suit;
        this.point = point;
        // ASCII card inspiration from https://john.me.tz/thoughts/article.php?topic=deal&offset=3&theme=dark
        if (this.suit.equals("Hearts")) {
            String[] art = {"  __  __ ",
                            " /  \\/  \\",
                            " \\      /",
                            "  \\    / ",
                            "   \\  /  ",
                            "     V   ",
                            "         "};
            asciiArt = art;
        } else if (this.suit.equals("Clubs")) {
            String[] art = {"   ___   ",
                            "  /   \\  ",
                            " _\\   /_ ",
                            "/       \\",
                            "\\__/ \\__/",
                            "   |_|   ",
                            "         "};
            asciiArt = art;
        } else if (this.suit.equals("Spades")) {
            String[] art = {"         ",
                            "    /\\   ",
                            "   /  \\  ",
                            "  /    \\ ",
                            " (_    _)",
                            "   |__|  ",
                            "         "};
            asciiArt = art;

        } else if (this.suit.equals("Diamonds")) {
            String[] art = {"    ^    ",
                            "  /   \\  ",
                            " /     \\ ",
                            "(       )",
                            " \\     / ",
                            "  \\   /  ",
                            "    V    "};
            asciiArt = art;
        } else {
            String[] art = {"xxxxxxxxx",
                            "xxxxxxxxx",
                            "xxxxxxxxx",
                            "xxxxxxxxx",
                            "xxxxxxxxx",
                            "xxxxxxxxx",
                            "xxxxxxxxx"};
            asciiArt = art;
        }
    }

    // Methods
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String[] getAsciiArt() {
        return asciiArt;
    }

    // Build ASCII playing card with rank and suit
    public String[] makeCard() {
        String[] cardArt = new String[12];
        cardArt[0] =  " _______________";
        cardArt[1] = "/               \\";
        // Print rank, special cases for double-digit rank (10) or blank card
        if (rank.equals("10")) {
            cardArt[2] = "|  " + rank + "           |";
        } else if (rank == " ") {
            cardArt[2] = "|               |";
        } else {
            cardArt[2] = "|  " + rank + "            |";
        }
        for (int i = 0; i < asciiArt.length; i++)
        {
            cardArt[i + 3] = "|   " + asciiArt[i] + "   |";
        }
        if (rank.equals("10")) {
            cardArt[10] = "|           " + rank + "  |";
        } else {
            cardArt[10] = "|            " + rank + "  |";
        }
        cardArt[11] = "\\_______________/";
        return cardArt;
    }

    @Override
    public String toString() {
        // If blank card, return two spaces (to correctly format printTwoCards
        // Else, return rank of suit
        return rank.equals(" ") ? "  " : rank + " of " + suit;
    }
}
