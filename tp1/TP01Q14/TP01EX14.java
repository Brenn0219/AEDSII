
class TP01EX14 {

    public static String isOr(String str, int posicao) {
        int orPosicao = 0, resp = 0;
        String aux = "", backupString = "";

        for(int i=0; i<posicao-2; i++) {
            backupString += str.charAt(i);
        }

        for (int i=posicao-2; i<str.length(); i++) {
            aux += str.charAt(i);
            if(str.charAt(i) == ')') {
                orPosicao = i;
                aux += str.charAt(i);
                i = str.length();
            }
        }

        for(int i=0; i<aux.length(); i++) {
            if(aux.charAt(i) == '1') {
                resp = 1;
                i = str.length();
            }
        }

        if(resp == 1) {
            backupString += '1';
        } else {
            backupString += '0';
        }

        for(int i=orPosicao+1; i<str.length(); i++) {
            backupString += str.charAt(i);
        }

        return backupString;
    }

    public static String isAnd(String str, int posicao) {
        int andPosicao = 0, resp = 1;
        String aux = "", backupString = "";

        for(int i=0; i<posicao-3; i++) {
            backupString += str.charAt(i);
        }

        for (int i=posicao-3; i<str.length(); i++) {
            aux += str.charAt(i);
            if(str.charAt(i) == ')') {
                andPosicao = i;
                aux += str.charAt(i);
                i = str.length();
            }
        }

        for(int i=0; i<aux.length(); i++) {
            if(aux.charAt(i) == '0') {
                resp = 0;
                i = str.length();
            }
        }

        if(resp == 0) {
            backupString += '0';
        } else {
            backupString += '1';
        }

        for(int i=andPosicao+1; i<str.length(); i++) {
            backupString += str.charAt(i);
        }

        return backupString;
    }

    public static String isNot(String str, int posicao) {
        int notPosicao = 0, resp = 1;
        String aux = "", backupString = "";

        for(int i=0; i<posicao-3; i++) {
            backupString += str.charAt(i);
        }

        for (int i=posicao-3; i<str.length(); i++) {
            aux += str.charAt(i);
            if(str.charAt(i) == ')') {
                notPosicao = i;
                aux += str.charAt(i);
                i = str.length();
            }
        }

        for(int i=0; i<aux.length(); i++) {
            if(aux.charAt(i) == '0') {
                resp = 0;
                i = str.length();
            }
        }

        if(resp == 0) {
            backupString += '1';
        } else {
            backupString += '0';
        }

        for(int i=notPosicao+1; i<str.length(); i++) {
            backupString += str.charAt(i);
        }

        return backupString;
    }

    public static int ultimoParentese(String str) {
        int n=0;
        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == '(') {
                n = i;
            }
        }
        return n;
    }

    public static String algebraBooleana(String str, int n) {
        int posicao = 0;
        if(str.length() == 1) {
            return str;
        } else {
            posicao = ultimoParentese(str);

            if(str.charAt(posicao-1) == 't') {
                str = isNot(str, posicao);
            } else if(str.charAt(posicao-1) == 'd') {
                str = isAnd(str, posicao);
            } else {
                str = isOr(str, posicao);
            }
            
            return algebraBooleana(str, str.length());
        }
    }

    public static String removerInicio(String str, int n) {
        String aux = "";
        for(int i=n; i<str.length(); i++) {
            aux += str.charAt(i);
        }
        return aux;
    }

    public static String distribuiVariaveis(String str, char a, char b, char c) {
        String aux = "";
        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == 'A') {
                aux += a;
            } else if (str.charAt(i) == 'B') {
                aux += b;
            } else if (str.charAt(i) == 'C') {
                aux += c;
            } else { 
                aux += str.charAt(i);
            }
        } 
        return aux;
    }

    public static String tiraOsEspacos(String str) {
        String aux = "";
        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) != ' ') {
                aux += str.charAt(i);
            }
        }
        return aux;
    }

    public static void organizaString(String str) {
        str = tiraOsEspacos(str);

        if(str.charAt(0) == '3') {
            str = distribuiVariaveis(str, str.charAt(1), str.charAt(2), str.charAt(3)); 
            str = removerInicio(str, 4);
        } else {
            str = distribuiVariaveis(str, str.charAt(1), str.charAt(2), ' ');
            str = removerInicio(str, 3);
        }

        str = algebraBooleana(str, str.length());

        MyIO.println(str);
    }

    public static boolean isFim(String str) {
        boolean resp = false;

        if(str.length() == 1 && str.charAt(0) == '0') {
            resp = true;
        } 
 
        return resp;
    }

    public static void main(String[] args) {
        String[] entradas = new String[1000];
        int cont=0;

        do {
            entradas[cont] = MyIO.readLine();
        }while(isFim(entradas[cont++]) != true);
        cont--;

        for(int i=0; i<cont; i++) {
           organizaString(entradas[i]);
        }
    }
}