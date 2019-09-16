
public class Player {

	private String name;
	private Field field;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Field getField() {
		return field;
	}

	public void printField() {
		this.field.print();
	}
}
