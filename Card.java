
public class Card {
	    private String suit;
		public int suitRank;
		private String name;
		public int nameRank;
	    public int value;

	    public Card(String suit, int suitRank, String name, int nameRank, int value) {
	        this.suit = suit;
			this.suitRank = suitRank;
			this.name = name;
			this.nameRank = nameRank;
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }

	    @Override
	    public String toString() {
	        return "<" + suit + " " + name + ">";
	    }

	    // public static void main(String[] args) {
	    //     // Card card = new Card("Heart", "Ace", 1);
	    //     // System.out.println(card);
	    // }
	}

