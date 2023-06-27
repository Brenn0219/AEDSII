
class TP01EX03 {

    public static void criptografia(String str) {
        String texto = "";
        int n = 0;
        char c;

        for(int i=0; i<str.length(); i++) {
            n = (int)str.charAt(i) + 3;
            c = (char)n;
            texto += c;
        }

        MyIO.println(texto);
    }

    public static boolean isFim(String str) {
        boolean resp = false;

        if(str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M') {
            resp = true;
        }

        return resp;
    }

    public static void main(String[] args) {
       String[] entradas = new String[1000];
       int contador = 0;

       do {
            entradas[contador] = MyIO.readLine();
       } while(isFim(entradas[contador++]) == false);
       contador--;

       for(int i=0; i<contador; i++) {
            criptografia(entradas[i]);
       }
    }
}