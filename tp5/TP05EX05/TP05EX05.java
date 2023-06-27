import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.File;
import java.text.DecimalFormat;

// Classe Jogo
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

// classe Hash com Reserva
class Hash {
    private int hash, reserveHash, counter;
    private Game[] games;

    Hash() {
        hash = 21;
        reserveHash = 9;
        counter = hash;
        games = new Game[hash + reserveHash];
    }

    // Metodo para fazer o calculo do Hash
    private int hash(String name) {
        int sun = 0;
        
        for(int i = 0; i < name.length(); i++) {
            sun += name.charAt(i);
        }

        return sun % hash;
    }

    // Metodo de Inserir Elementos no Hash
    public void insert(Game game) {
        int position = hash(game.getName());

        if(games[position] == null) {
            if(position <= 20) {
                games[position] = game;
            } else {
                System.out.println("Insert Hash > 21");
            }
            
        } else {
            if(counter <= games.length - 1) {
                games[counter++] = game;
            } else {
                // System.out.println("Insert Hash Rehash > 9");
            }
        }
    }

    // Metodo de Remover Elementos no Hash
    public void remove(String name) {
        int position = hash(name);

        if(games[position].getName().equals(name)) {
            games[position] = null;

            for(int i = games.length - reserveHash; i <= counter; i++) {
                if(hash(games[i].getName()) == position) {
                    games[position] = games[i];
                    games[i] = games[counter];
                    games[counter--] = null;
                    i = counter;
                }
            }
        } else {
            for(int i = games.length - reserveHash; i <= counter; i++) {
                if(name.equals(games[i].getName())) {
                    games[i] = games[counter];
                    games[counter--] = null;
                    i = counter;
                }
            }        
        }

        if (counter < hash) {
            counter = hash;
        }
    }

    // Metodo de Pesquisar Elementos no Hash
    public boolean search(String name) {
        boolean resp = false;
        int position = hash(name);

        if(games[position].getName().equals(name)) {
            resp = true;
            System.out.println("Posicao: " + position);
        } else {
            for(int i = games.length - reserveHash; i <= counter; i++) {
                if(name.equals(games[i].getName())) {
                    System.out.println("Posicao: " + i);
                    i = counter;
                }
            }
        }

        return resp;
    }

    // Metodo de Mostrar Todos os Elementos do Hash
    public void show() {
        for(int i = 0; i < games.length; i++) {
            if(games[i] != null) {
                System.out.println(games[i].getName());
            }
        }
    }
}

// classe Main
class TP05Q05 {
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

    // Metodo de Salvar os Games na Árvore 
    public static Hash readAndSavePiubIn(Game listGames[]) throws Exception {
        String pubIn = "";
        Hash hash = new Hash();

        do {
            pubIn = sc.next();

            if(theEnd(pubIn) != true) {
                for(int i=0; i<listGames.length; i++) {
                    if(listGames[i].getAppId() == Integer.parseInt(pubIn)) {
                        hash.insert(listGames[i].clone());
                        i = listGames.length;
                    }
                }
            }
        } while(theEnd(pubIn) != true);

        return hash;
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

    // Metodo para Inserir, Remover e Pesquisar Elementos
    public static void elementManipulation(Hash hash, Game[] listGames) {
        String entry = "";

        while(theEnd(entry) != true) {
            entry = sc.nextLine();

            if(theEnd(entry) != true) {
                System.out.println("=> " + entry);
                hash.search(entry);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Game[] database = loadDatabase();
        Hash hash = readAndSavePiubIn(database);
    	elementManipulation(hash, database);
    }
}