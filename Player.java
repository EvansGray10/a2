import java.util.*;

public class Player extends User {

    public int chips;
    protected ArrayList<Card> cardsOnHand;

    public Player(String loginName, String password, int chips) {
        super(loginName, password);
        this.chips = chips;
        this.cardsOnHand = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        this.cardsOnHand.add(card);
    }

    public void showCardsOnHand() {
        System.out.println(getLoginName());
        int sum = 0;
        for (Card card : this.cardsOnHand) {
            System.out.println(card + " ");
            sum += card.value;
        }
        System.out.println("Value: " + sum);
    }

    public int getChips() {
        return this.chips;
    }

    public void addChips(int amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Chips cannot be added.");
            return;
        }
        this.chips += amount;
    }

    public int getTotalCardValue() {
        int totalValue = 0;
        for (Card card : cardsOnHand) {
            totalValue += card.getValue();

        }
        return totalValue;
    }
    public int getcardValue(int index){
        int cardNameValue = cardsOnHand.get(index).nameRank;
        int cardSuitValue = cardsOnHand.get(index).suitRank;
        int sum = cardNameValue + cardSuitValue;
        return sum;
    }

    public void showTotalValue() {
        System.out.println("Value:" + getTotalCardValue());
    }

    public static void main(String[] args) {

        Player player = new Player("IcePeak", "password", 100);
        Deck deck = new Deck();
        deck.shuffle();
        Card card1 = deck.dealCard();
        Card card2 = deck.dealCard();
        player.addCard(card1);
        player.addCard(card2);
        player.showCardsOnHand();
        player.showTotalValue();

        System.out.println(player.getChips());
        player.addChips(20);
        System.out.println(player.getChips());
    }
}


	
	class IncorrectPasswordException extends Exception {
	    private static final long serialVersionUID = 1L;

		public IncorrectPasswordException(String string) {
	        super("Incorrect password.");
	    }
	}

