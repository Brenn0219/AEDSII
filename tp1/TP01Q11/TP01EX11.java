
class TP01EX11{

    public static boolean testePalindromo(String str, int i, int n, boolean resp) {
        if(i >= n) {
            return resp;
        } else {
            if(str.charAt(i) != str.charAt(n - i - 1)) {
                resp = false;
            }
            return testePalindromo(str, i+1, n, resp);
        }
    }

    public static boolean testePalindromo(String str) {
        return testePalindromo(str, 0, str.length(), true);
    }

    public static boolean isFim(String str)  {
        return(str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M');
    }

    public static void main(String[] args) {
        String[] entradas = new String[1000];
        int cont = 0;

        do {
            entradas[cont] = MyIO.readLine();
        } while(isFim(entradas[cont++]) == false);
        cont--;

        for (int i = 0; i < cont; i++) {
            if(testePalindromo(entradas[i])) {
                MyIO.println("SIM");
            } else {
                MyIO.println("NAO");
            }
        }
    }
}