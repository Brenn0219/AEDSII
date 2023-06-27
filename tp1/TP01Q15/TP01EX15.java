
class TP01EX15 {
    public static int isReal(String str, int i, int cont) {
        if(i >= str.length()) {
            return cont;
        } else {
            if(str.charAt(i) >= '0' && str.charAt(i) <= '9')  {
                cont++;
            }

            if(str.charAt(i) == '.' || str.charAt(i) == ',') {
                cont++;
            }

            return isReal(str, i+1, cont);
        }
    }

    public static boolean isReal(String str) {
        boolean resp, num;
        int p=0, v=0;

        resp = (isReal(str, 0, 0)) == str.length() ? true:false;
        num = isNumero(str);

        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == '.') {
                p++;
            }
            if(str.charAt(i) == ',') {
                v++;
            }
        }

        return((resp && (p==1 || v==1)) || num);
    }

    public static int isNumero(String str, int i, int cont) {
        if(i >= str.length()) {
            return cont;
        } else {
            if(str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                cont++;
            }
            return isNumero(str, i+1, cont);
        }
    }

    public static boolean isNumero(String str) {
        return((isNumero(str, 0, 0) == str.length() ? true:false));
    }

    public static int isConsoante(String str, int i, int cont) {
        str = str.toUpperCase();

        if(i >= str.length()) {
            return cont;
        } else {
            if(str.charAt(i) >= 'B' && str.charAt(i) <= 'Z') {
                if(str.charAt(i) != 'E' && str.charAt(i) != 'I' && str.charAt(i) != 'O' && str.charAt(i) != 'U') {
                    cont++;
                }
            }
            return isConsoante(str, i+1, cont);
        }
    }

    public static boolean isConsoante(String str) {
        return((isConsoante(str, 0, 0) == str.length() ? true:false));
    }

    public static int isVogal(String str, int i, int cont) {
        str = str.toUpperCase();

        if(i >= str.length()) {
            return cont;
        } else {
            if(str.charAt(i) == 'A' || str.charAt(i) == 'E' || str.charAt(i) == 'I' || str.charAt(i) == 'O' || str.charAt(i) == 'U') {
                cont++;
            }

            return isVogal(str, i+1, cont);
        }
    }

    public static boolean isVogal(String str) {
        return ((isVogal(str, 0, 0)) == str.length() ? true:false);
    }

    public static void lerString(String str) {
        if(isVogal(str)) {
            MyIO.print("SIM ");
        } else {
            MyIO.print("NAO ");
        }

        if(isConsoante(str)) {
            MyIO.print("SIM ");
        } else {
            MyIO.print("NAO ");
        }

        if(isNumero(str)) {
            MyIO.print("SIM ");
        } else {
            MyIO.print("NAO ");
        }

        if(isReal(str)) {
            MyIO.println("SIM ");
        } else {
            MyIO.println("NAO ");
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
        String[] entradas = new String[1000];
        int cont = 0;

        do {
            entradas[cont] = MyIO.readLine();
        }while(isFim(entradas[cont++]) != true);
        cont--;

        for(int i=0; i<cont; i++) {
            lerString(entradas[i]);
        }
    }
}