
public class Square {

	// CONSTANTS
	public static final char MISSED = '~';
	public static final char WATER = '~';
	public static final char HIT = '*';
	public static final char SUNK = 'V';
	public static final char DOT = '.';

	// VARIABLES
	private Ship ship;
	private boolean isHit;

	public Square() {
		this.ship = null;
		this.isHit = false;
	}

	public Square(Ship ship) {
		this.ship = ship;
	}

	public Ship getShip() {
		return ship;
	}

	public boolean hasShip() {
		return ship != null;
	}

	public boolean isHit() {
		return isHit;
	}

	/**
	 * Changes the state of the squares' hit. If the square has a ship, increase the
	 * ships' shot count.
	 */
	public void setIsHit() {
		if (this.hasShip())
			ship.addShotCount();

		this.isHit = true;
	}
}
