
import java.util.*;
public class Dealer extends Player{
    private Deck deck;
    public Dealer() {
        super("Dealer","",100);
        deck=new Deck();

    }
    public void shuffleCards() {
        System.out.println("Dealer shuffle card");
        deck.shuffle();
    }
    public void dealCardToPlayer(Player player) {
        Card card =deck.dealCard();
        player.addCard(card);
    }

    
    public void showCardsOnHand(ArrayList<Card> cardsOnHand, boolean endGame) {

        System.out.println("Dealer");
        for (int i = 0; i < cardsOnHand.size(); i++) {
            if (i == 0){
                if(endGame){
                    System.out.println(cardsOnHand.get(i));
                }else{
                    System.out.println("<Hidden Card>");
                }
            }
            else{
                System.out.println(cardsOnHand.get(i));
            }

        }
    }
    
    
}