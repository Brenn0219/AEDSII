import java.util.*;

class Celulle {
	public int element;
	public Celulle next, previous, up, down;

	Celulle() { this(-1); }

	Celulle(int element) {
		this.element = element;
		next = previous = up = down = null;
	}

	Celulle(int element, Celulle next, Celulle previous, Celulle up, Celulle down) {
		this.element = element;
		this.next = next;
		this.previous = previous;
		this.up = up;
		this.down = down;
	}	
}

class Matriz {
	public static Scanner sc = new Scanner(System.in);
	private Celulle start;
	private int line, column;

	Matriz(int line, int column) {
		// Valores para saber o tamanho da Matriz
		this.line = line;
		this.column = column;

		// Sentinela - "Cabeca"
		start = new Celulle();
		start.down = new Celulle();

		// Primeira Posicao da Linha e da Coluna 
		Celulle timelineBuilder = start.down, lineBiulder;
		
		// Criando as Colunas da primeira Linha
		for(int i = 0; i < column - 1; i++) {
			timelineBuilder.next = new Celulle();
			timelineBuilder.next.previous = timelineBuilder;
			timelineBuilder = timelineBuilder.next;
		}
		
		for(int i = 0; i < line - 1; i++) {
			
			for(; timelineBuilder.previous != null; timelineBuilder = timelineBuilder.previous);

			// Criando a Próxima Linha
			timelineBuilder.down = new Celulle();
			lineBiulder = timelineBuilder.down;
			lineBiulder.up = timelineBuilder;

			// Construindo as colunas da Linha
			for(int j = 0; j < column - 1; j++) {
				lineBiulder.next = new Celulle();
				lineBiulder.next.previous = lineBiulder;

				lineBiulder = lineBiulder.next;
				timelineBuilder = timelineBuilder.next;

				lineBiulder.up = timelineBuilder;
				timelineBuilder.down = lineBiulder;
			}

			// Apontando para a Ultima posicao daquela linha
			timelineBuilder = lineBiulder;
		}
	}

	public Celulle getFirstPosition() {
		return start.down;
	}

	public void multiplicationOfMatrices(Matriz m) {
		int multiplication = 0;
		Celulle internalMatrix = start.down, externalMatrix = m.getFirstPosition();

		for(int i = 0; i < line; i++) {
			Celulle firstPositionInternal = internalMatrix;
			Celulle firstPositionExternal = externalMatrix;

			for(int j = 0; j < column; j++) {
				for(int k = 0; k < column; k++) {
					multiplication += internalMatrix.element * externalMatrix.element;
					internalMatrix = internalMatrix.next;
					externalMatrix = externalMatrix.down;
				}

				System.out.print(multiplication + " ");
				multiplication = 0;

				externalMatrix = firstPositionExternal.next; 
				internalMatrix = firstPositionInternal;
			}

			multiplication = 0;

			internalMatrix = firstPositionInternal.down;
			externalMatrix = firstPositionExternal;

			System.out.println();
		}
	}

	public void sumOfMatrices(Matriz m) {
		int sum = 0;
		Celulle internalMatrix = start.down, externalMatrix = m.getFirstPosition();

		for(int i = 0; i < line; i++) {
			Celulle firstPositionInternal = internalMatrix;
			Celulle firstPositionExternal = externalMatrix;

			for(int j = 0; j < column; j++) {
				sum = internalMatrix.element + externalMatrix.element;
				System.out.print(sum + " ");
				internalMatrix = internalMatrix.next;
				externalMatrix = externalMatrix.next;
			}
			System.out.println();

			internalMatrix = firstPositionInternal.down;
			externalMatrix = firstPositionExternal.down;
		}
	}

	public void secondaryDiagonal() {
		Celulle i = start.down;
		
		for(; i.next != null; i = i.next);

		for(; i != null; i = i.down) {
			System.out.print(i.element + " ");
			
			if(i.previous != null) { i = i.previous; }
		}
		System.out.println();
	}

	public void leadingDiagonal() {
		for(Celulle i = start.down; i != null; i = i.down) {
			System.out.print(i.element + " ");
			
			if(i.next != null) { i = i.next; }
		}
		System.out.println();
	}

	public void insertElements() {
		Celulle cellInsert = start.down;

		for(int i = 0; i < line; i++) {
			Celulle firstPosition = cellInsert;

			for(int j = 0; j < column; j++) {
				cellInsert.element = sc.nextInt();
				cellInsert = cellInsert.next;
			}

			cellInsert = firstPosition.down;
		}
	}

	public void show() {
		Celulle cellShow = start.down;

		for(int i = 0; i < line; i++) {
			Celulle firstPosition = cellShow;

			for(int j = 0; j < column; j++) {
				System.out.print(cellShow.element + " ");
				cellShow = cellShow.next;
			}
			System.out.println();

			cellShow = firstPosition.down;
		}	
	}

	public static void main(String[] args) {
		int testCases, line, column;
		Matriz m1, m2;
		
		testCases = Integer.parseInt(sc.nextLine());
		
		for(int i = 0; i < testCases; i++) {
			line = Integer.parseInt(sc.next());
			column = Integer.parseInt(sc.next());
			m1 = new Matriz(line, column);
			m1.insertElements();
			m1.leadingDiagonal();
			m1.secondaryDiagonal();
			
			line = Integer.parseInt(sc.next());
			column = Integer.parseInt(sc.next());
			m2 = new Matriz(line, column);
			m2.insertElements();

			m1.sumOfMatrices(m2);
			m1.multiplicationOfMatrices(m2);
		}
	}
}
