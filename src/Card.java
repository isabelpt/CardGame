// Isabel Prado-Tucker
// Card class
public class Card {
    // Instance Variables
    private String rank;
    private String suit;
    private int point;
    private String[] asciiArt;
//    private static final String[][][] asciiRanks = {
//            {{"   ___"},
//                    {"  /   \\"},
//                    {" _\\   /_"},
//                    {"/       \\"},
//                    {"\\__/ \\__/"},
//                    {"   |_|"},
//                    {""}
//            }
//    };

    // Constructors
    public Card(String rank, String suit, int point) {
        this.rank = rank;
        this.suit = suit;
        this.point = point;
        if (this.suit.equals("Hearts")) {
            String[] art = {"  __  __ "," /  \\/  \\"," \\      /","  \\    / ","   \\  /  ","     V   ","         "};
            asciiArt = art;
        } else if (this.suit.equals("Clubs")) {
            String[] art = {"   ___   ", "  /   \\  ", " _\\   /_ ", "/       \\", "\\__/ \\__/", "   |_|   ", "         "};
            asciiArt = art;
        } else if (this.suit.equals("Spades")) {
            String[] art = {"         ","    /\\   ","   /  \\  ","  /    \\ "," (_    _)","   |__|  ","         "};
            asciiArt = art;

        } else if (this.suit.equals("Diamonds")) {
            String[] art = {"    ^    ", "  /   \\  ", " /     \\ ", "(       )", " \\     / ", "  \\   /  ", "    V    "};
            asciiArt = art;
        } else {
            String[] art = {"xxxxxxxxx", "xxxxxxxxx", "xxxxxxxxx", "xxxxxxxxx", "xxxxxxxxx", "xxxxxxxxx", "xxxxxxxxx"};
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

    public String[] makeCard() {
        String[] cardArt = new String[12];
        //System.out.println(" _______________");
        cardArt[0] =  " _______________";
        //System.out.println("/               \\");
        cardArt[1] = "/               \\";
        if (rank == "10") {
            //System.out.println("|  " + rank + "           |");
            cardArt[2] = "|  " + rank + "           |";
        } else {
            // System.out.println("|  " + rank + "            |");
            cardArt[2] = "|  " + rank + "            |";
        }
        for (int i = 0; i < asciiArt.length; i++)
        {
            //System.out.println("|   " + line + "   |");
            cardArt[i + 3] = "|   " + asciiArt[i] + "   |";
        }
        if (rank == "10") {
            // System.out.println("|           " + rank + "  |");
            cardArt[10] = "|           " + rank + "  |";
        } else {
            // System.out.println("|            " + rank + "  |");
            cardArt[10] = "|            " + rank + "  |";
        }
        // System.out.println("\\_______________/");
        cardArt[11] = "\\_______________/";
        return cardArt;
    }

    // Don't think this is actually necessary
    public int compare(Card other)
    {
        // 0: less than, 1: equal, 2: greater than
        if (this.point < other.point) {
            return 0;
        } else if (this.point == other.point) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
