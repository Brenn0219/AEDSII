import java.util.*;

// Classe No
class No {
    public char letter;
    public No left, right;

    No(char letter) {
        this.letter = letter;
        left = null;
        right = null;
    } 
}

class Tree {
    private No  root;

    Tree() { root = null; }

    // Metodo de Inserir um Elemento na Arvore
    public void insert(char x) throws Exception {
        root = insert(x, root);
    }

    private No insert(char x, No i) throws Exception {
        if(i == null) {
            i = new No(x);
        } else if (x < i.letter) {
            i.left = insert(x, i.left);
        } else if (x > i.letter) {
            i.right = insert(x, i.right);
        } else {
            throw new Exception("Erro ao Inserir");
        }
        
        return i;
    }

    // Metodo de Remover
    public void remove(char x) throws Exception {
        root = remove(x, root);
    }

    private No remove(char x, No i) throws Exception {
        if(i == null) {
            throw new Exception("Erro ao Remover");
        } else if (x < i.letter) {
            i.left = remove(x, i.left);
        } else if (x > i.letter) {
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
            i.letter = j.letter;
            j = j.left;
        } else {
            j.right = biggestLeft(i, j.right);
        }
        
        return j;
    }

    // Metodo de Pesquisar um Elemento na Arvore
    public boolean search(char x) {
        return search(x, root);
    }

    private boolean search(char x, No i) {
        boolean resp = false;

        if(i == null) {
            resp = false;
        } else if (x < i.letter) {
            resp = search(x, i.left);
        } else if (x > i.letter) {
            resp = search(x, i.right);
        } else {
            resp = true;
        }

        return resp;
    }

    // Mostrar - Caminhamento Central
    public void showCentral() {
        showCentral(root);
        System.out.println();
    }

    private void showCentral(No i) {
        if(i != null) {
            showCentral(i.left);
            System.out.print(i.letter + " ");
            showCentral(i.right);
        }
    }

    // Mostrar - Caminhamento Pre-Fixado
    public void showPre() {
        showPre(root);
        System.out.println();
    }

    private void showPre(No i) {
        if(i != null) {
            System.out.print(i.letter + " ");
            showPre(i.left);
            showPre(i.right);
        }
    }

    // Mostrar - Caminhamento Pos-Fixado
    public void showPos() {
        showPos(root);
        System.out.println();
    }

    private void showPos(No i) {
        if(i != null) {
            showPos(i.left);
            showPos(i.right);
            System.out.print(i.letter + " ");
        }
    }
}

class TP05Q09 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Tree tree = new Tree();
        String entry;

        while(sc.hasNext()) {
            entry = sc.next();

            if(entry.equals("I")) {
                tree.insert(sc.next().charAt(0));
            } else if (entry.equals("INFIXA")) {
                tree.showCentral();
            } else if (entry.equals("PREFIXA")) {
                tree.showPre();
            } else if (entry.equals("POSFIXA")) {
                tree.showPos();
            } else if (entry.equals("P")) {
                char x = sc.next().charAt(0);
                
                if(tree.search(x)) {
                    System.out.println(x + " existe");
                } else {
                    System.out.println(x + " nao existe");
                }
            }
        }

        sc.close();
    }
}