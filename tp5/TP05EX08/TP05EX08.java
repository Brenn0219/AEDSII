import java.util.*;

// Classe No
class No {
	public int element;
	public No left, right;

	No() { this(-1); }

	No(int element) {
		this.element = element;
		left = null;
		right = null;
	}
}

// Classe Arvore
class Tree {
	private No root;

	Tree() { root = null; }
	
	// Metodo de Inserir Elementos na Arvore
	public void insert(int x) throws Exception {
		root = insert(x, root);
	}

	private No insert(int x, No i) throws Exception {
		if(i == null) {
			i = new No(i);
		} else if (x < i.element) {
			i.left = insert(x, i.left);
		} else if (x > i.element) { 
			i.right = insert(x, i.right);
		} else {
			throw new Exception("Erro ao Inserir");
		}

		return i;
	}
	
	// Metodo de Remover Elementos na Arvore 
	public void remove(int x) throws Exception {
		root = remove(x, root);
	}

	private No remove(int x, No i) throws Exception {
		if(i == null) {
			throw new Exception("Erro ao Remover");
		} else if (x < i.element) {
			i.left = remove(x, i.left);
		} else if (x > i.element) {
			i.right = remove(x, i.right);
		} else if (i.right == null) {
			i = i.left;
		} else if (i.left == null) {
			i = i.right;
		} else {
			i = biggestLeft(i, i.left); 
		}

		return i;
	}
	
	private No biggestLeft(No i, No j) {	
		if(j.right == null) {
			i.element = j.element;
			j = j.left;
		} else {
			j.right = (biggestLeft(i, j.right));
		}
	}
}

class TP05EX08 {
	public void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	}
}
