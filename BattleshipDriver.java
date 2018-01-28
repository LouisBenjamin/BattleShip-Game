	

public class BattleshipDriver {

	public static void main(String[] args) {
	
		/*An object is created from the Battleship class, allowing multiple games to
		 *run separately or simultaneously
		 */
		Battleship game1 = new Battleship();
		
		//Lines 15-18 initializes the whole game
		game1.Init();
		
		game1.SetHuman();
		game1.SetComputer();
		System.out.print("\n\n");
		
		/*A do-while loop is used to simulate the turn-based game.
		 *Once GameOver = True (One of the player has sunk all of the enemy ships,the 
		 *game stops. It is checked after each turn to prevent the losing player to play an
		 *extra turn. If skipTurn(C or H) becomes true, then someone has hit a grenade.
		 *Hence, it skips the if loop and in consequence, skips one's turn.
		 */
		
		do {
		
			if (!(game1.GameOver()) && !(game1.skipTurnH))
				game1.HumanTurn();
			else 
				game1.skipTurnH = false;
			
			
			if (!(game1.GameOver()) && !(game1.skipTurnC))
				game1.CompTurn();
			else 
				game1.skipTurnC = false;
		
		} while (!(game1.GameOver()));
		
		//The game has ended, the winner has been declared, tears have been shed and
		//the real grid is printed.
		game1.DeclareWinner();
		game1.PrintGrid();
	}	
}
