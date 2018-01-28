import java.util.Scanner;

public class Battleship {
	
	public static Scanner sc = new Scanner(System.in);
	
	/*Instance variables that affects how the games is set. Includes ship number,
	 *grenade number and the size of the grid.
	 */
	
	private static int numShips = 6;
	private static int numGrenades = 4;
	private static int gridSize = 8;
	
	private String grid [][] = new String[gridSize][gridSize]; 
	public String visibleGrid [][] = new String[gridSize][gridSize];
	
	/* Checks for remaining ships left for both parties
	 * H = Human
	 * C = Computer
	 */

	private int shipsLeftH = numShips;
	private int shipsLeftC = numShips;
	
	/*These boolean variables are meant to be check if one the
	 * parties has to skip a turn. More details in the Driver
	 */
	
	public boolean skipTurnH = false;
	public boolean skipTurnC = false;
	
	/* this method initializes two grids.
	 * The first one is Grid which contains the location of all ships 
	 * and grenades of both parties. 
	 * Then, there is visibleGrid, which is the grid where both party
	 * can see after each turn. It includes the locations that have been hit and
	 * also those that are missed
	 */
	
	public void Init() {
		
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				grid[i][j] = "_";
				visibleGrid[i][j] = "_";
			}
		}
	}
	
	/* As the grid of the game is composed of strings, this method checks 
	 * the first character of said string to evaluate the type. For example,
	 * the String found in the grid are "sHuman" or "GComputer"
	 */
	
	public String CheckType(String s) {
		
		String type = null;
		
		if (s.charAt(0) == 'g' || s.charAt(0) == 'G')
			type = "grenade";
		if (s.charAt(0) == 's' || s.charAt(0) == 'S')
			type = "ship";
		if (s.charAt(0) == '_')
			type = "nothing";
		if (s.charAt(0) == '*')
			type = "miss";
		
		return type;
		
	}
	
	/* Similarly to the previous method, this one checks the second character
	 * to indicate the owner
	 */
	
	public String CheckOwner(String s) {
		
		String owner = null;
		
		if (s.charAt(1) == 'H')
			owner = "human";
		if (s.charAt(1) == 'C')
			owner = "computer";
		
		return owner;
		
	}

	/*A method that convert characters to number for the grid
	 * Note that number is initialized to 0. This will be useful as it indicates
	 * when the coordinates are out of the grid.
	 */
	
	public static int CharToNum(char c) {
		
		int number = 0; 
		
		if (c == 'a'|| c == 'A')
			number = 1 ;
		if (c == 'b'|| c == 'B')
			number = 2;
		if (c == 'c'|| c == 'C')
			number = 3;
		if (c == 'd'|| c == 'D')
			number = 4;
		if (c == 'e'|| c == 'E')
			number = 5;
		if (c == 'f'|| c == 'F')
			number = 6;
		if (c == 'g'|| c == 'G')
			number = 7;
		if (c == 'h'|| c == 'H')
			number = 8;
	
		return number;
				
	}
	/* This is a similar method from the previous one. It converts numbers to characters
	 */
	
	public static char NumToChar(int n) {
		
		char c = 0;
		
		if (n == 1) 
			c = 'A';
		if (n == 2) 
			c = 'B';
		if (n == 3) 
			c = 'C';		
		if (n == 4) 
			c = 'D';
		if (n == 5) 
			c = 'E';
		if (n == 6) 
			c = 'F';
		if (n == 7) 
			c = 'G';
		if (n == 8)
			c = 'H';
	
		return c;
				
	}
	
	/* This method simply prints Grid
	 */
	
	public void PrintGrid(){
		
		System.out.print("\t\t");
		
		for (int i = 0 ; i < gridSize; i++) {
			System.out.print("\n");
			for (int j = 0;  j < gridSize; j++)
				System.out.print(grid[j][i].charAt(0)+"  ");	
		}		
	}
	
	/* This method prints VisibleGrid
	 */
	
	public void PrintVisGrid(){
		
		System.out.print("\t\t");
		
		for (int i = 0 ; i < gridSize; i++) {
			System.out.print("\n");
			for (int j = 0;  j < gridSize; j++)
				System.out.print(visibleGrid[j][i].charAt(0)+"  ");
		}		
		System.out.print("\n\n");
	}

	/* The following two methods are initializing methods that allows for
	 * the ships and grenades to be placed by both parties. It converts the coordinates into
	 * the appropriate format and checks if they are within the grid.
	 */
	
	public void SetHuman(){
		 
		for (int i = 1; i <= numShips; i++) {
			
			System.out.print("Enter the coordinate for ship #"+i+": ");
			String position = sc.next();
			  
			char c = position.charAt(0);
			int column = CharToNum(c);
			int row = position.charAt(1)-48;
			
			//If column is 0, then there was no permutation of the variable from the method CharToNum
			//This indicates that the character did not respect the grid limitation.
			
			if (column == 0 || row > 8 || row < 1)	{	
				System.out.println("Sorry, the coordinates are outside the grid. Try again.");
				i = i-1;
				continue;		
			}
			//If the grid that is checked do not contain nothing, then a String has already been placed.
			if (!(CheckType(grid[column-1][row-1]) == "nothing")) {
				System.out.println("Sorry, the coordinates are already in use. Try again.");
				i = i-1;
				continue;
			}
			//If all is good, a string is placed into the coordinate. In this case, it is a human ship.
			else
				grid[column-1][row-1] = "sHuman";
		}	
		
		// For the grenade part, it is almost identical as the previous lines of code.
		for (int j = 1; j <= numGrenades; j++) {
			
			System.out.print("Enter the coordinates for grenade #"+j+": ");
			String position = sc.next();
			  
			char c = position.charAt(0);
			int column = CharToNum(c);
			int row = position.charAt(1)-48;
			
			if (column == 0 || row > 8 || row < 1)	{	
				System.out.println("Sorry, these coordinates are outside the grid. Try again.");
				j = j-1;
				continue;
			}	
			if (!(CheckType(grid[column-1][row-1]) == "nothing")) {
				System.out.println("Sorry, these coordinates are already in use. Try again.");
				j = j-1;
				continue;
			}
			else
				grid[column-1][row-1] = "gHuman";
		}	
		
	}
		
	public void SetComputer(){
			 
			for (int i = 1; i <= numShips; i++) {
				
				int column = (int) (Math.random() * 8 + 1);
				int row    = (int) (Math.random() * 8 + 1);
			
				if (!(CheckType(grid[column-1][row-1]) == "nothing")){
					i = i-1;
					continue;
				}
				else
					grid[column-1][row-1] = "SComputer";

			}	
			
			for (int j = 1; j <= numGrenades; j++) {
				
				int column = (int) (Math.random() * 8 + 1);
				int row    = (int) (Math.random() * 8 + 1);
				
				if (!(CheckType(grid[column-1][row-1]) == "nothing")) {
					j = j-1;
					continue;
				}
				else
					grid[column-1][row-1] = "GComputer";

			}	
			System.out.print("The computer has placed its ships and grenades at random. Let’s play.");
	}
		
	/* The following two method is the "meat" of the game.
	 * Just like before, it prompts the human player to shoot a rocket 
	 * at a certain coordinate. And given where the rocket lands, there is several outcomes.
	 * Same thing goes for the computer's turn method.
	 */
	
	public void HumanTurn() {
		
		for (int i = 0; i < 1; i++) {
			
			System.out.print("\nPosition of your rocket: ");
			String callH = sc.next();
			
			char c = callH.charAt(0);
			int column = CharToNum(c);
			int row = callH.charAt(1)-48;
			
			if (column == 0 || row > 8 || row < 1)	{	
				System.out.println("Sorry, these coordinates are outside the grid. Try again.");
				i = i-1;
				continue;
			}
		
			column--;
			row--;
			
			//Checks with most priority if the position has already been called. If you call
			// a position that has been called, you wasted a turn.
			
			if (CheckType(visibleGrid[column][row]) == "grenade" || 
					CheckType(visibleGrid[column][row]) == "ship" ||
					CheckType(visibleGrid[column][row]) == "miss")
					
						System.out.println("Position already called.");
			
			//Or else, it proceeds the see what has been hit. For most of the functions,
			// The program checks the type and owner of the called position. Depending on the situation,
			//different scenarios can occur.
			
			else {
				
				if (CheckType(grid[column][row]) == "ship" && CheckOwner(grid[column][row]) == "computer") {
					System.out.println("Ship hit");
					shipsLeftC--;
					visibleGrid[column][row] = "S";
				}
				if (CheckType(grid[column][row]) == "ship" && CheckOwner(grid[column][row]) == "human") {
					System.out.println("You've just hit your own ship.");
					shipsLeftH--;
					visibleGrid[column][row] = "s";
				}
				//In case of a grenade being hit (regardless of the owner), it changes the instance variable
				//skipTurnH or skipTurnC to true depending on who hit it. This boolean variable is useful in the main
				//drive to check if one player has to skip a turn
				if (CheckType(grid[column][row]) == "grenade" && CheckOwner(grid[column][row]) == "computer") {
					System.out.println("BOOM! You've just hit a grenade. Skip your next turn.");
					visibleGrid[column][row] = "G";
					skipTurnH = true;
				}
				if (CheckType(grid[column][row]) == "grenade" && CheckOwner(grid[column][row]) == "human") {
					System.out.println("BOOM! You've just hit your own grenade. Skip your next turn.");
					visibleGrid[column][row] = "g";
					skipTurnH = true;
				}
				if (CheckType(grid[column][row]) == "nothing") {
					System.out.println("Nothing.");
					visibleGrid[column][row] = "*";
				}
			}
		}
		//The visible grid is printed at each turn
		PrintVisGrid();	
	}
	
	//The method for the computer is very similar to the one for the human.
	//It is more "bare bones" as it always outputs an appropriate coordinate

	public void CompTurn() {
		
		int column = (int) (Math.random() * 8 + 1);
		int row    = (int) (Math.random() * 8 + 1);
		char c = NumToChar(column);
		
		System.out.print("Position of my rocket: "+c+row+"\n");
		
		column--;
		row--;
		
		if (CheckType(visibleGrid[column][row]) == "grenade" || 
				CheckType(visibleGrid[column][row]) == "ship" ||
				CheckType(visibleGrid[column][row]) == "miss")
				
					System.out.println("Position already called.");
		
		else {
		
			
			if (CheckType(grid[column][row]) == "ship" && CheckOwner(grid[column][row]) == "computer") {
				System.out.println("I've just hit my own ship...");
				shipsLeftC--;
				visibleGrid[column][row] = "S";
			}
			if (CheckType(grid[column][row]) == "ship" && CheckOwner(grid[column][row]) == "human") {
				System.out.println("Ship hit");
				shipsLeftH--;
				visibleGrid[column][row] = "s";
			}
			if (CheckType(grid[column][row]) == "grenade" && CheckOwner(grid[column][row]) == "computer") {
				System.out.println("BOOM! I've hit my own grenade. My next turn will be skipped");
				visibleGrid[column][row] = "G";
				skipTurnC = true;
			}
			if (CheckType(grid[column][row]) == "grenade" && CheckOwner(grid[column][row]) == "human") {
				System.out.println("BOOM! Grenade hit. My next turn will be skipped");
				visibleGrid[column][row] = "g";
				skipTurnC = true;
			}
			if (CheckType(grid[column][row]) == "nothing") {
				System.out.println("Nothing.");
				visibleGrid[column][row] = "*";
			}
		}	
		PrintVisGrid();	
	}
	
	/* This methods check if all the ships have been sinked,
	 * If so, it return the boolean variable GameOver as true which 
	 * stops immediately the game in the main driver.
	 */

	public boolean GameOver() {
		
		boolean gameOver = false;
		
		if (shipsLeftC == 0 || shipsLeftH == 0)
			gameOver = true;
		
		return gameOver;
	}
	
	/*This method checks who has won and prints a declaration of victory
	 */

	public void DeclareWinner() {
		
		if (shipsLeftC == 0)
				System.out.println("You Win! I have been vanquished!");
			
		if (shipsLeftH == 0)
				System.out.println("I Win! Better luck next time!");
	}


}
	

