
class TP01EX13 {

	public static String lerString(String str) {
		return lerString(str, 0, "");
	}

	public static String lerString(String str, int i, String aux) {
		int n = 0;

		if(i >= str.length()) {
			return aux;
		} else {
			n = str.charAt(i) + 3;
			aux += (char)n;
			return lerString(str, i+1, aux);
		}

	}

	public static boolean isFim(String str) {
		boolean resp = false;
		
		if(str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M') {
			resp = true;	
		}

		return resp;
	}

	public static void main(String[] args) {
		String[] entradas = new String [1000];
		int cont = 0;

		do {
			entradas[cont] = MyIO.readLine();
		}while(isFim(entradas[cont++]) != true);
		cont--;

		for(int i=0; i<cont; i++) {
			MyIO.println(lerString(entradas[i]));
		}
	}
}
