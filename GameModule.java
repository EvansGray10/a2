import java.text.BreakIterator;
import java.util.*;
public class GameModule {
	private Dealer dealer;
	private Player player;
	private int pot = 0;
	
	public GameModule() {
		dealer= new Dealer();
		player=new Player("IcePeak","password",100);
		
	}
	public void dealcard() {
		dealer.dealCardToPlayer(player);
		dealer.dealCardToPlayer(dealer);
	}


	public void comparison(int roundnumber, int indicator){
		int playerCard = 0;
		int dealerCard = 0;
		if (indicator == 1){
			playerCard = this.player.cardsOnHand.get(roundnumber).nameRank;
			dealerCard = this.dealer.cardsOnHand.get(roundnumber).nameRank;
		}else{
			playerCard = this.player.cardsOnHand.get(roundnumber).suitRank;
			dealerCard = this.dealer.cardsOnHand.get(roundnumber).suitRank;
		}
		Scanner kb = new Scanner(System.in);
		if (playerCard > dealerCard){
			if (roundnumber == 1 ){
				System.out.println("Player call, state bet:");
			int betAmount = kb.nextInt();
			int playerTotalChips = player.getChips();
			int dealerTotalChips = dealer.getChips();
			player.chips = playerTotalChips - betAmount;
			dealer.chips = dealerTotalChips - betAmount;
			System.out.println(player.getLoginName()+"You are left with " + player.chips + " chips");
			pot = pot + betAmount * 2;
			System.out.println("Bet on table:"+ pot);
			}
			else{
				
				System.out.println("Do you want to [C]all or [Q]uit?: ");
				String answer = kb.next();
				while(!(answer.equalsIgnoreCase("C") || answer.equalsIgnoreCase("Q"))){
					System.out.println("Do you want to [C]all or [Q]uit?: ");
					answer = kb.next();
				}
				if(answer.equalsIgnoreCase("C")){
					System.out.println("Player call, state bet: ");
					int betAmount = kb.nextInt();
					int playerTotalChips = player.getChips();
					int dealerTotalChips = dealer.getChips();
					player.chips = playerTotalChips - betAmount;
					dealer.chips = dealerTotalChips - betAmount;
					System.out.println("You are left with " + player.chips + " chips");
					pot = pot + betAmount * 2;
					System.out.println("Bet on table:" + pot);
				
				}
				else if (answer.equalsIgnoreCase("Q")){
					endGame();
				}
			}
		}
		else if(dealerCard>playerCard){
			
			if(roundnumber == 1){
				System.out.println("Dealer calls, state bet: 20");
				int betAmount = 20;
				int playerTotalChips = player.getChips();
				int dealerTotalChips = dealer.getChips();
				player.chips = playerTotalChips - betAmount;
				dealer.chips = dealerTotalChips - betAmount;
				pot = pot + betAmount * 2;
				System.out.println("bet on table:" + pot);
			}
			else{
				System.out.println("Dealer calls, state bet: 20");
				int betAmount = 20;
				int playerTotalChips = player.getChips();
				int dealerTotalChips = dealer.getChips();
				dealer.chips = dealerTotalChips - betAmount;
				System.out.println("Do you want to follow? [Y/N]");
				String answer = kb.next();
				
				while(!(answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N"))){
					System.out.println("Do you want to follow? [Y/N]");
					answer = kb.next();
				}
				
				if (answer.equalsIgnoreCase("Y")) {
					player.chips = playerTotalChips - betAmount;
					pot = pot + betAmount * 2;
					System.out.println(player.getLoginName() + ", You are left with " + player.chips + " chips");
					System.out.println("bet on table:" + pot);

				} 
				else if (answer.equalsIgnoreCase("N")){
					endGame();
				}
			}
		}
		else if (playerCard == dealerCard) {
			comparison(roundnumber, 2);
		}
	
	}

	
	public void run() {
		Scanner kb = new Scanner(System.in);
		System.out.println("HighSum Game");
		System.out.println("================================================================================");
		System.out.println("Enter Login Name> ");
		String login = kb.next();
		System.out.println("Enter Password> ");
		String pw = kb.next();
		
		try {
			player.checkLoginName(login);
		} catch (User.IncorrectLoginNameException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		try {
			player.checkPassword(pw);
		} catch (User.IncorrectPasswordException e) {
			System.out.println(e.getLocalizedMessage());
			System.exit(0);
		}
		
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("HighSum Game");
		System.out.println("================================================================================");
		System.out.println("IcePeak, You have 100 chips");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Game starts - Dealer shuffles deck.");
		dealer.shuffleCards();
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Dealer dealing cards - ROUND 1");
		System.out.println("--------------------------------------------------------------------------------");

		dealcard();
		dealcard();
		dealer.showCardsOnHand(dealer.cardsOnHand, false);
		player.showCardsOnHand();
		comparison(1,1);
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Dealer dealing cards - ROUND 2");
		System.out.println("--------------------------------------------------------------------------------");

		dealcard();
		dealer.showCardsOnHand(dealer.cardsOnHand, false);
		player.showCardsOnHand();
		comparison(2,1);
		System.out.println("--------------------------------------------------------------------------------");

		System.out.println("Dealer dealing cards - ROUND 3");
		System.out.println("--------------------------------------------------------------------------------");

		dealcard();
		dealer.showCardsOnHand(dealer.cardsOnHand, false);
		player.showCardsOnHand();
		comparison(3,1);
		System.out.println("--------------------------------------------------------------------------------");

		System.out.println("Dealer dealing cards - ROUND 4");
		System.out.println("--------------------------------------------------------------------------------");

		dealcard();
		dealer.showCardsOnHand(dealer.cardsOnHand, false);
		player.showCardsOnHand();
		comparison(4,1);
		System.out.println("--------------------------------------------------------------------------------");

		System.out.println("Game End - Dealer reveal hidden cards");
		System.out.println("--------------------------------------------------------------------------------");

		endGame();


	}
	public void endGame(){
		int playerTotal = player.getTotalCardValue();
		int dealerTotal = dealer.getTotalCardValue();
		dealer.showCardsOnHand(dealer.cardsOnHand, true);
		System.out.println("value: "+ dealerTotal);
		player.showCardsOnHand();
	
		if(playerTotal > dealerTotal){
			System.out.println(player.getLoginName()+" Wins");
			int playerTotalChips = pot + player.chips;
			System.out.println(player.getLoginName()+", You have " + playerTotalChips + " chips.");
		}
		else if(dealerTotal > playerTotal){
			System.out.println(dealer.getLoginName() + " wins");
			int dealerTotalChips = pot + dealer.chips;
			System.out.println("Dealer have " + dealerTotalChips + " chips.");
			System.out.println(player.getLoginName()+", You have " + player.chips + " chips.");

			
		}
		else if(playerTotal == dealerTotal){
			System.out.println("it's a draw.");
			System.out.println(player.getLoginName()+", You have " + player.chips + " chips.");

		}
		nextGame();
	}
	public void nextGame(){
		Scanner kb = new Scanner(System.in);
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Next Game? (Y/N) > ");
		String ans = kb.next();
		if (ans.equalsIgnoreCase("Y")) {
			GameModule app = new GameModule();
			app.run();
		} else if (ans.equalsIgnoreCase("N")) {
			System.out.println("Bye Bye! The game ends here!");
		} else {
			System.out.println("Invalid! please provide valid one.");
				nextGame();
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameModule app= new GameModule();
		app.run();
	}

}