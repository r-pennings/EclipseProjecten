import java.util.*;

public class Field {

	// CONSTANTS
	public static final char[] LETTERS = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J' };
	public static final int SIZE = 10;

	// VARIABLES
	private HashMap<String, Square> playfield;
	private Ship[] ships;
	private int sunkenShips;

	public Field() {
		initField();

		initShips();
	}

	/**
	 * Prints the board of the current player. If game is in cheat mode, it prints
	 * an extra field with all the ships shown.
	 */
	public void print() {
		printBoard(false);

		if (Main.CHEAT)
			printBoard(true);
	}

	/**
	 * Returns a square from the field by an given coordinate.
	 * 
	 * @param coordinate - The questioned coordinate
	 * 
	 * @return a square from the playfield
	 */
	public Square getSquareByCoordinate(String coordinate) {
		return playfield.get(coordinate);
	}

	/**
	 * Increases the amount of sunken ships.
	 */
	public void addSunkenShip() {
		sunkenShips++;
	}

	/**
	 * Returns an boolean if the game is finished or not. When game is in cheat
	 * mode, one ship is enough for a game to be finished.
	 * 
	 * @return boolean whether the game has finished or not
	 */
	public boolean gameIsFinished() {
		if (Main.CHEAT)
			return sunkenShips == 1;

		return sunkenShips == ships.length;
	}

	/**
	 * Initializes the field. Creates the field and adds an empty square to every
	 * coordination.
	 */
	private void initField() {
		playfield = new HashMap<String, Square>();
		sunkenShips = 0;

		for (char x = 'A'; x <= Field.LETTERS[Field.SIZE - 1]; x++) {
			for (int y = 1; y <= Field.SIZE; y++) {
				putInField(x, y);
			}
		}
	}

	/**
	 * Initializes the ships. Creates the array and adds them to the field.
	 */
	private void initShips() {
		this.ships = new Ship[ShipType.values().length];

		int i = 0;
		for (ShipType type : ShipType.values()) {
			this.ships[i] = new Ship(type);
			i++;
		}

		for (Ship ship : ships)
			placeShip(ship);
	}

	/**
	 * Prints the board. Starts at maximum height (because of descending order).
	 * 
	 * @param isCheatMode - Variable from the Main class, if game is in cheat mode
	 */
	private void printBoard(boolean isCheatMode) {
		for (int y = Field.SIZE; y >= 1; y--) {
			String boardRow = "";

			if (y < 10)
				boardRow += " ";

			boardRow += (y + "| ");

			for (char x = 'A'; x <= Field.LETTERS[Field.SIZE - 1]; x++) {
				Square square = getFromField(x, y);

				Ship ship = square.getShip();
				char icon;
				if (isCheatMode) {
					if (square.hasShip()) {
						icon = ship.getIcon();
					} else {
						icon = Square.WATER;
					}
				} else {
					if (square.hasShip()) {
						if (ship.hasSunk()) {
							icon = ship.getIcon();
						} else if (square.isHit()) {
							icon = Square.HIT;
						} else {
							icon = Square.DOT;
						}
					} else {
						if (square.isHit()) {
							icon = Square.MISSED;
						} else {
							icon = Square.DOT;
						}
					}
				}

				boardRow += icon;
				boardRow += " ";
			}

			System.out.println(boardRow);
		}

		System.out.println("  +--------------------");
		System.out.println("    A B C D E F G H I J");
		System.out.println();
	}

	/**
	 * Adds the ship to the field by a random character, number and direction
	 * 
	 * @param ship - The ship the for loop is trying to place
	 */
	private void placeShip(Ship ship) {
		Random rand = new Random();
		char randomStartChar = Field.LETTERS[rand.nextInt(Field.SIZE - ship.getLength())];
		int randomStartNr = rand.nextInt(Field.SIZE - ship.getLength()) + 1;
		boolean isVertical = rand.nextBoolean();

		// Check whether the new ship can be placed vertical or not
		if (isValidCoordinate(ship, randomStartChar, randomStartNr, isVertical)) {
			addShipToField(ship, randomStartChar, randomStartNr, isVertical);
		} else {
			if (isValidCoordinate(ship, randomStartChar, randomStartNr, !isVertical)) {
				addShipToField(ship, randomStartChar, randomStartNr, !isVertical);
			} else {
				placeShip(ship);
			}
		}
	}

	/**
	 * Adds an ship to the field. Loop through the length of the ship and adds a
	 * part of the ship to the given coordinate.
	 * 
	 * @param ship       - The ship that is going to be placed
	 * @param startChar  - The beginning character of the ship placement
	 * @param startNr    - The beginning number of the ship placement
	 * @param isVertical - The ship is vertical or horizontal
	 */
	private void addShipToField(Ship ship, char startChar, int startNr, boolean isVertical) {
		for (int i = 0; i < ship.getLength(); i++) {
			putInField(startChar, startNr, ship);

			if (isVertical) {
				startNr++;
			} else {
				startChar++;
			}
		}
	}

	/**
	 * Checks whether the questioned coordinate is valid or not. Loops through every
	 * coordinate. If coordinate not possible, return false.
	 * 
	 * @param ship       - The ship that is going to be placed
	 * @param startChar  - The beginning character of the ship placement
	 * @param startNr    - The beginning number of the ship placement
	 * @param isVertical - The ship is vertical or horizontal
	 * 
	 * @return boolean isValidCoordinate
	 */
	private boolean isValidCoordinate(Ship ship, char startChar, int startNr, boolean isVertical) {
		for (int i = 0; i < ship.getLength(); i++) {
			String coordinate = Character.toString(startChar) + startNr;
			if (!isSquareFree(coordinate)) {
				return false;
			}

			if (isVertical) {
				startNr++;
			} else {
				startChar++;
			}
		}

		return true;
	}

	private boolean isSquareFree(String coordinate) {
		return playfield.get(coordinate).getShip() == null;
	}

	private void putInField(char x, int y) {
		playfield.put(Character.toString(x) + y, new Square());
	}

	private void putInField(char x, int y, Ship ship) {
		playfield.put(Character.toString(x) + y, new Square(ship));
	}

	private Square getFromField(char x, int y) {
		return playfield.get(Character.toString(x) + y);
	}
}
