import java.util.*;

public class Deck {
	private ArrayList<Card> cards;
	public Deck() {
		cards=new ArrayList<Card>();

		String[] suits = {"Heart","Diamond","Spade","Club"};
		int[] suitRank = {3, 1, 4, 2};

		for(int i = 0; i < suits.length; i++){
			String suit = suits[i];
			int suitRankInt = suitRank[i];
			Card card = new Card(suit, suitRankInt, "Ace",1, 1);
			cards.add(card);
			for(int n=2;n<=10;n++) {
				Card acard=new Card(suit, suitRankInt, n+"", n, n);
				cards.add(acard);
			}
			Card jack = new Card(suit, suitRankInt, "Jack", 10, 10);
			Card queen = new Card(suit, suitRankInt, "Queen", 10, 10);
			Card king = new Card(suit, suitRankInt, "King", 10, 10);
			cards.add(jack);
			cards.add(queen);
			cards.add(king);
		}
	}
		
		
		public void shuffle() {
			Random random=new Random();
			for(int i=0;i<1000;i++) {
				int indexA=random.nextInt(cards.size());
				int indexB=random.nextInt(cards.size());
				Card cardA=cards.get(indexA);
				Card cardB=cards.get(indexB);
				cards.set(indexA,cardB);
				cards.set(indexB, cardA);
				}
			}
		
		public void showCards() {
		for(Card card:cards) {
			System.out.println(card);
		}
		}
		public Card dealCard() {
			return cards.remove(0);
		}
		public void appendCard(Card card) {
			cards.add(card);
		}
		public void appendCard(ArrayList<Card>cards) {
			for (Card card:cards) {
				appendCard(card);
			}
		}
		
	
	public static void main(String[] args) {

		
//		Deck deck = new Deck();
//		deck.showCards();
			
	}

}
