import java.util.Scanner;

public class Main {

	public static final boolean CHEAT = true;

	public static void main(String[] args) {
		System.out.println("Welkom bij het spelletje Zeeslag!");
		System.out.println();
		System.out.println(
				"Probeer de oorlogsbodems van je tegenstander tot zinken te brengen voor hij jouw boten te pakken heeft genomen.");
		System.out.println();

		initGame();
	}

	/**
	 * Initializes a single game.
	 */
	private static void initGame() {
		SeaBattle battle = new SeaBattle();

		Player[] players = askAmountOfPlayers();
		battle.setPlayers(players);
		battle.play();
	}

	/**
	 * Asks how many players are going to play the game. After that, it handles the
	 * creating of players.
	 * 
	 * @return the amount of players including their set variables;
	 */
	private static Player[] askAmountOfPlayers() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("Geef aantal spelers (1/2): ");
			String input = sc.nextLine();

			switch (input) {
			case "1":
				return askPlayerNames(1);
			case "2":
				return askPlayerNames(2);
			default:
				System.out.println("*** Dat is geen geldig aantal spelers! ***");
				continue;
			}
		}
	}

	/**
	 * Creates the players array by the given amount of players, then it asks every
	 * player their name and creates the player.
	 * 
	 * @param amountOfPlayers - The amount of players in a game
	 * 
	 * @return an array (Player[]) of players;
	 */
	private static Player[] askPlayerNames(int amountOfPlayers) {
		Player[] players = new Player[amountOfPlayers];

		for (int i = 0; i < amountOfPlayers; i++) {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);

			while (true) {
				System.out.print("Geef de naam van speler " + (i + 1) + ": ");

				String name = sc.nextLine();
				if (isValidName(name)) {
					players[i] = new Player(name);
					break;
				} else {
					System.out.println("*** Dat is geen geldige spelersnaam! ***");
					continue;
				}
			}

			players[i].setField(new Field());
		}

		return players;
	}

	/**
	 * Checks whether the given name is valid or not. First he trims the name, then
	 * checks it if is not empty and only contains letters and spaces.
	 * 
	 * @param name - The name of a player
	 * 
	 * @return an boolean whether the player name is valid or not
	 */
	private static boolean isValidName(String name) {
		String trimmed = name.trim();
		return (!trimmed.isEmpty() && trimmed.matches("^[a-zA-Z ]*$"));
	}
}
