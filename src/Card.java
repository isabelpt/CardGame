// Isabel Prado-Tucker
// Card class
public class Card {
    // Instance Variables
    private String rank;
    private String suit;
    private int point;

    // Constructors
    public Card(String rank, String suit, int point) {
        this.rank = rank;
        this.suit = suit;
        this.point = point;
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

    public void printCard() {
        String[] clubs = {"      ___      ", "     /   \\     ", "    _\\   /_    ", "   /       \\   ", "   \\__/ \\__/   ", "      |_|      "};
        System.out.println(" _______________");
        System.out.println("/               \\");
        System.out.println("|  " + rank + "            |");
        for (String line: clubs) {
            System.out.println("|" + line + "|");
        }
        System.out.println("|            " + rank + "  |");
        System.out.println("\\_______________/");
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
