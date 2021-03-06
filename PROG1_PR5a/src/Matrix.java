
public class Matrix {
	private final LedRij[] matrix;

	public Matrix(int size) {
		matrix = new LedRij[size];

		for (int i = 0; i < matrix.length; i++) {
			matrix[i] = new LedRij(4);
		}
	}

	public void schakel(int rij, int kolom, boolean aan) {
		if (matrix.length > rij - 1 && matrix[rij - 1] != null) {
			matrix[rij - 1].schakel(kolom, aan);
		} else {
			System.out.println("De schakel is niet mogelijk op deze rij!");
		}
	}

	public void schuifInRij(int rij, boolean naarRechts) {
		if (matrix.length > rij - 1 && matrix[rij - 1] != null) {
			matrix[rij - 1].schuif(naarRechts);
		} else {
			System.out.println("De schuif is niet mogelijk op deze rij!");
		}
	}

	public void schuifInRij(int rij, int aantalPosities, boolean naarRechts) {
		if (matrix.length > rij - 1 && matrix[rij - 1] != null) {
			for (int i = 0; i <= aantalPosities; i++) {
				matrix[rij - 1].schuif(naarRechts);
			}
		} else {
			System.out.println("De schuif is niet mogelijk op deze rij!");
		}
	}

	public void maakDiagonaal() {
		LedRij rij1 = matrix[0];
		rij1.schakel(1, true);
		rij1.schakel(2, false);
		rij1.schakel(3, false);
		rij1.schakel(4, false);

		LedRij rij2 = matrix[1];
		rij2.schakel(1, false);
		rij2.schakel(2, true);
		rij2.schakel(3, false);
		rij2.schakel(4, false);

		LedRij rij3 = matrix[2];
		rij3.schakel(1, false);
		rij3.schakel(2, false);
		rij3.schakel(3, true);
		rij3.schakel(4, false);

		LedRij rij4 = matrix[3];
		rij4.schakel(1, false);
		rij4.schakel(2, false);
		rij4.schakel(3, false);
		rij4.schakel(4, true);
	}

	public void clear() {
		for (LedRij rij : matrix) {
			rij.clear();
		}
	}

	public void printMatrix() {
		for (LedRij rij : matrix) {
			System.out.println(rij.toString());
		}
	}
}
