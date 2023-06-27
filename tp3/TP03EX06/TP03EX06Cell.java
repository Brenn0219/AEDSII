import java.util.*;
import java.io.*;
import java.text.*;

class Game {
    // Atributos Privados
    private int appId, age, dlcs, avgPt;
    private double price, upvotes;
    private boolean windows, mac, linux;
    private String name, owners, website, developes;
    private String[] languages, genres; 
    private Date releaseDate;

    // Construtor sem Parametro
    public Game() {
        appId = 0;
        name = "";
        releaseDate = null;
        owners = "";
        age = 0;
        price = 0;
        dlcs = 0;
        languages = null;
        website = "";
        windows = false;
        mac = false;
        linux = false;
        upvotes = 0.0;
        avgPt = 0;
        developes = "";
        genres = null;
    }

    // Construtor com Parametro
    public Game(int appId, String name, Date releaseDate, String owners, int age, double price, int dlcs, String[] languages,  String website, boolean windows, boolean mac, boolean linux, Double upvotes, int avgPt, String developes,String[] genres) {
        setAppId(appId);
        setName(name);
        setReleaseDate(releaseDate);
        setOwners(owners);
        setAge(age);
        setPrice(price);
        setDlcs(dlcs);
        setLanguages(languages);
        setWebsite(website);
        setWindows(windows);
        setMac(mac);
        setLinux(linux); 
        setUpvotes(upvotes);
        setAvgPt(avgPt);
        setDevelopes(developes);
        setGenres(genres);
    }

    // Sets
    public void setAppId(int appId) { this.appId = appId;}
    public void setName(String name) { this.name = name; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate;}
    public void setOwners(String owners) { this.owners = owners; }
    public void setAge(int age) { this.age = age; }
    public void setPrice(double price) { this.price = price; }
    public void setDlcs(int dlcs) { this.dlcs = dlcs; }
    public void setLanguages(String[] languages) { this.languages = languages; }
    public void setWebsite(String website) { this.website = website; }
    public void setWindows(boolean windows) {this.windows = windows; }
    public void setMac(boolean mac) { this.mac = mac; }
    public void setLinux(boolean linux) { this.linux = linux; }
    public void setUpvotes(Double upvotes) { this.upvotes = upvotes; }
    public void setAvgPt(int avgPt) { this.avgPt = avgPt; }
    public void setDevelopes(String developes) { this.developes = developes; }
    public void setGenres(String[] strings) { this.genres = strings; }

    // Gets
    public int getAppId() { return appId; }
    public String getName() { return name; }
    public Date getReleaseDate() { return releaseDate; }
    public String getOwners() { return owners; }
    public int getAge() { return age; }
    public double getPrice() { return price; }
    public int getDlcs() { return dlcs; }
    public String[] getLanguages() { return languages; }
    public String getWebsite() { return website; }
    public boolean getWindows() { return windows; }
    public boolean getMac() { return mac; }
    public boolean getLinux() { return linux; }
    public Double getUpvotes() { return upvotes; }
    public int getAvgPt() { return avgPt; }
    public String getDevelopes() { return developes; }
    public String[] getGenres() { return genres; }

    // Metodo para formatar o array
    private String formatArray(String[] array) {
        String printArray = "";

        if(array !=  null) {
            printArray += "["; 
            for(int i=0; i < array.length; i++) {
                int n = array[i].indexOf("-");
                if(n != -1) {
                    String aux = "";
                    int t = array[i].length();
                    for(int j=0; j < n; j++) {
                        aux += array[i].charAt(j);
                    }
                    aux += " - ";
                    for(int j = n+1; j < t; j++) {
                        aux += array[i].charAt(j);
                    }  
                    array[i] = aux;
                }

                if(i == array.length - 1) {
                    printArray += array[i];
                } else {
                    printArray += array[i] + ", ";
                }
            }
            printArray += "]";
        } else {
            printArray = "null";
        }
        
        return printArray;
    }

    // Metodo para imprimir Horas e os Minutos corretamente
    private String formatHours(int seconds) {
        int hour = seconds / 60;
        int min = seconds % 60;
        String hours = "";

        if(hour == 0 && min == 0) {
            hours = "null";
        } else if (hour > 0 && min > 0) {
            hours = hour + "h " + min + "m";
        } else {
            if(hour > 0) {
                hours = hour + "h";
            }
            if(min > 0) {
                hours = min + "m";
            }   
        }

        return hours;
    }

    // Metodo para Arredondar a Porcentagem
    private String formatPercentage(Double conta) {
        return ((int)Math.round(conta * 100) + "%");
    }

    // Metodo para imprimir Web se existir se nao, imprimir null
    private String formatWeb(String web) {
        if(web.equals("")) {
            return "null";
        } else {
            return web;
        }
    }

    // Metodo para formatar casas Decimais em Price
    private String formatPrice(double price) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(price);
    }

    // Metodo para calcular a Porcentagem do Upvotes
    private double calculatePercentage(String upvotes, String notUpvotes) {
        return (Double.parseDouble(upvotes) / (Double.parseDouble(upvotes) + Double.parseDouble(notUpvotes)));
    }

    // Metodo para transformar Date em String 
    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM/yyyy", Locale.US);
        return sdf.format(date);
    }

    // Metodo para transformar String em Date 
    private Date convertToDate(String strData) throws ParseException {
        Date date = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
            date = sdf.parse(strData);
        } catch(ParseException e) {
            SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy", Locale.US);
            date = sdf.parse(strData);
        }

        return date;
    }

    // Metodo para Separar os Atributos e colocar em uma Array
    private String[] separateAttributes(String line) {
        String[] arrayString = new String[17];
        String helperString = "";
        int counter = 0;

        for(int i=0; i<line.length(); i++) {
            if(i == line.length()-1) {
                helperString += line.charAt(i);
                arrayString[counter++] = helperString;
                helperString = "";
            } else {
                if(line.charAt(i) == '"') {
                    int pos = 0 ;
                    for(int j=i+1; j<line.length(); j++) {
                        if(line.charAt(j) != '"') {
                            helperString += line.charAt(j);
                        } else {
                            arrayString[counter++] = helperString;
                            helperString = "";
                            pos = j;
                            j = line.length();
                        }
                    }
                    i = pos+1;
                } else {
                    if(i < line.length()) {
                        if(line.charAt(i) != ',') {
                            helperString += line.charAt(i);
                        } else {
                            arrayString[counter++] = helperString;
                            helperString = "";
                        }
                    } 
                }
            }
        }

        return arrayString;
    }

    // Metodo de ler/criar um Jogo
    public void toRead(String line) throws ParseException {
        // String[] arrysAtributos =  line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        String[] arrysAtributos = separateAttributes(line);
        
        setAppId(Integer.parseInt(arrysAtributos[0]));
        setName(arrysAtributos[1]);
        setReleaseDate(convertToDate(arrysAtributos[2])); 
        setOwners(arrysAtributos[3]);
        setAge(Integer.parseInt(arrysAtributos[4]));
        setPrice(Double.parseDouble(arrysAtributos[5]));
        setDlcs(Integer.parseInt(arrysAtributos[6]));
        setLanguages(arrysAtributos[7].replaceAll("'", "").replaceAll("\"", "").replace('[', ' ').replace(']', ' ').replaceAll(" ", "").split(","));
        setWebsite(arrysAtributos[8]);
        setWindows(Boolean.parseBoolean(arrysAtributos[9]));
        setMac(Boolean.parseBoolean(arrysAtributos[10]));
        setLinux(Boolean.parseBoolean(arrysAtributos[11]));
        setUpvotes(calculatePercentage(arrysAtributos[12], arrysAtributos[13]));
        setAvgPt(Integer.parseInt(arrysAtributos[14]));
        setDevelopes(arrysAtributos[15].replaceAll("\"", ""));
        setGenres((arrysAtributos[16] != null) ? arrysAtributos[16].replaceAll("\"", "").split(","):null);
    }

    // Metodo de Imprimir
    public void show() {
        System.out.println(getAppId() + " " + getName() + " " + formatDate(getReleaseDate()) + " " + getOwners() + " " + getAge() + " " + formatPrice(getPrice()) + " " + getDlcs() + " " + formatArray(getLanguages()) + " " + formatWeb(getWebsite()) + " " + getWindows() + " " + getMac() + " " + getLinux() + " " + formatPercentage(getUpvotes()) + " " + formatHours(getAvgPt()) + " " + getDevelopes() + " " + formatArray(getGenres()));
    }

    // Metodo de clonar 
    public Game clone() {
        Game cloneGames = new Game();

        cloneGames.name = this.name;
        cloneGames.owners = this.owners;
        cloneGames.website = this.website;
        cloneGames.developes = this.developes;
        cloneGames.languages = this.languages;
        cloneGames.genres = this.genres;
        cloneGames.releaseDate = this.releaseDate;
        cloneGames.appId = this.appId;
        cloneGames.age = this.age;
        cloneGames.dlcs = this.dlcs;
        cloneGames.avgPt = this.avgPt;
        cloneGames.price = this.price;
        cloneGames.upvotes = this.upvotes;
        cloneGames.windows = this.windows;
        cloneGames.mac = this.mac;
        cloneGames.linux = this.linux;

        return cloneGames;
    }
}

class Cellule {
    public Game game;
    public Cellule next, previous;

    public Cellule() {
        game = new Game();
        next = null;
    }

    public Cellule(Game game) {
        this.game = game.clone();
        next = null;
        previous = null;
    }
}

class ArrayList {
    private Cellule first, last;

    // Construtor sem parametro
    public ArrayList() {   
        first = new Cellule();
        last = first;
    }

    // Insere um Game na primeira posicao 
    public void insertStart(Game game) throws Exception {
        Cellule temporaryCell =  new Cellule(game);
        temporaryCell.next = first.next;
        first.next = temporaryCell;

        if(first == last) { last = temporaryCell; }
        else { temporaryCell.next.previous = temporaryCell; }

        temporaryCell = null;
    }

    // Insere um Game na ultima posicao 
    public void insertEnd(Game game) throws IOException, ParseException {
        last.next = new Cellule(game);
        last.next.previous = last;
        last = last.next;
    }

    // Insere um Game em uma posicao especifica 
    public void insert(Game game, int position) throws Exception {
        int size = flexibleListSize();

        if(position < 0 || position > flexibleListSize()) {
            throw new Exception("Erro");
        } else if (position == 0) { insertStart(game); }
        else if (position == size) { insertEnd(game);}
        else {
            Cellule i = first;
            
            for(int j = 0; j<position; j++) { i = i.next;}

            Cellule tampCellule = new Cellule(game);
            tampCellule.next = i.next;
            tampCellule.previous = i;
            i.next = tampCellule;

            tampCellule = i = null;
        }
    }

    // Metodo que Retorna o Tamanho da Lista Flexivel
    public int flexibleListSize() {
        int size = 0;
        for(Cellule i = first.next; i != null; i = i.next) { size++; }
        return size;
    }

    // Remove um Game da primeira posicao 
    public Game removeStart() throws Exception {
        if(first == last) { throw new Exception("Erro"); }

        Cellule temporaryCell = first;
        first = first.next;
        Game gameRemoved = first.game;
        temporaryCell = temporaryCell.next = null;

        System.out.println("(R) " + gameRemoved.getName());
        return gameRemoved;
    }

    // Remove um Game da ultima posicao
    public Game removeEnd() throws Exception {
        if(first == last) { throw new Exception("Erro"); }
        
       Game gameRemoved = last.game;
       last = last.previous;
       last.next.previous = last.next = null;

        System.out.println("(R) " + gameRemoved.getName());
        return gameRemoved;
    }

    // Remove um Game de uma posicao especifica 
    public Game remove(int position) throws Exception {
        int size = flexibleListSize();
        Game gameRemoved =  new Game();

        if(first == last) {
            throw new Exception("Erro");
        } else if (position < 0 || position >=  size) { 
            throw new Exception("Erro");
        } else if (position == 0) { removeStart(); }
        else if (position == size) { removeEnd();}
        else {
            Cellule i = first;
            
            for(int j = 0; j<position; j++) { i = i.next;}

            Cellule tampCellule = i.next; 
            gameRemoved = tampCellule.game;
            i.next = tampCellule.next;
            tampCellule.next.previous = i;

            tampCellule = i = null;
        }
        System.out.println("(R) " + gameRemoved.getName());
        return gameRemoved;
    }

    public void sort() {
        sort(first, last);
    }

    private void sort(Cellule left, Cellule right) {
        Cellule i = left, j = right;
        Date pivot = choosePivotDate(left, right);

        while(i == j || i == j.next) {
            while(i.game.getReleaseDate().before(pivot)) { i = i.next; }
            while(j.game.getReleaseDate().after(pivot)) { j = j.previous; }

            if(i == j || i == j.next) {
                swap(i, j);
    
                i = i.next;
                j = j.previous;
            }
            
            if(left != j) { sort(left, j); }
            if(i != right) { sort(i, right); }
        }
        
    }

    private Date choosePivotDate(Cellule left, Cellule right) {
        int position = flexibleListSize() / 2;
        Cellule temporaryCell = first.next;

        for(int i = 0; i < position; i++, temporaryCell = temporaryCell.next);

        return temporaryCell.game.getReleaseDate();
    }

    private void swap(Cellule i, Cellule j) {
        Game tmp = i.game;
        i.game = j.game;
        j.game = tmp;
    }

    // Metodo para Mostrar os Games
    public void show() {
        int counter = 0;
        for(Cellule i = first.next; i != null; i = i.next) {
            System.out.print("[" + counter++ + "] ");
            i.game.show();
        }
    }
}

class TP04EX04 {
    // Variavel publica para se obter a Entrada dos Dados
    public static Scanner sc = new Scanner(System.in);

    // Metodo para ler o fim do while
    public static boolean theEnd(String pubIn) {
        return (pubIn.length() == 3 
            && pubIn.charAt(0) == 'F'
            && pubIn.charAt(1) == 'I'
            && pubIn.charAt(2) == 'M'
        );
    }

    // Metodo para Ler da base de dados
    public static Game[] loadDatabase() throws Exception {
        Scanner sc;
        Game game = new Game();
        Game[] database = new Game[4403];
        int counter = 0;

        try {
            sc = new Scanner (new File("./tmp/games.csv"));
        } catch (Exception e) {
            sc = new Scanner (new File("/tmp/games.csv"));
        }
        
        while(sc.hasNext()) {
            game.toRead(sc.nextLine());
            database[counter++] = game.clone();
        }
        sc.close();
        
        return database;
    }

    // Metodo de Salvar os Games no Lista Flexivel de PubIn
    public static ArrayList readAndSavePiubIn(Game database[]) throws IOException, ParseException {
        String pubIn = "";
        ArrayList listGames = new ArrayList();

        do {
            pubIn = sc.next();

            if(theEnd(pubIn) != true) {
                for(int i=0; i<database.length; i++) {
                    if(database[i].getAppId() == Integer.parseInt(pubIn)) {
                        listGames.insertEnd(database[i].clone());
                        i = database.length;
                    }
                }
            }
        } while(theEnd(pubIn) != true);

        return listGames;
    }

    // Pesquisa no Array de Games através do id o Game específico e Retorna o Game naquela posicao 
    public static Game searchGameInGameList(Game[] listGames, int id) {
        Game game =  new Game();

        for(int i=0; i<listGames.length; i++) {
            if(id == listGames[i].getAppId()) {
                game = listGames[i].clone();
                i = listGames.length;
            }
        }

        return game;
    }

    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Game[] database = loadDatabase();
        ArrayList pubInGames = readAndSavePiubIn(database);
        pubInGames.sort();
    }   
}