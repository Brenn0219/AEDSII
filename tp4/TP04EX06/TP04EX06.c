#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <math.h> 
#define SIZE_ARRAYLIST 75
const int SIZE_LINE = 585; 
const int SIZE_ATTRIBUTE = 385;
const int ENTRY_PUBIN = 15;

// -----------------------------------------------------------------------------------------
// Objeto de Data
typedef struct {
	int month;
	int year;
} Date;

// Metodo para retorna o mes em string
char *getMonthName(int month) {
    switch (month) {
        case 1:
            return "Jan";
            break;
        case 2:
            return "Feb";
            break;
        case 3:
            return "Mar";
            break;
        case 4:
            return "Apr";
            break;
        case 5:
            return "May";
            break;
        case 6:
            return "Jun";
            break;
        case 7:
            return "Jul";
            break;
        case 8:
            return "Aug";
            break;
        case 9:
            return "Sep";
            break;
        case 10:
            return "Oct";
            break;
        case 11:
            return "Nov";
            break;
        case 12:
            return "Dec";
            break;

        default:
            return "N/A";
            break;
    }
}

// Metodo para retornar mes em numero
int getMonthNumber(char *month) {
    if (!strcmp(month, "Jan"))
        return 1;
    else if (!strcmp(month, "Feb"))
        return 2;
    else if (!strcmp(month, "Mar"))
        return 3;
    else if (!strcmp(month, "Apr"))
        return 4;
    else if (!strcmp(month, "May"))
        return 5;
    else if (!strcmp(month, "Jun"))
        return 6;
    else if (!strcmp(month, "Jul"))
        return 7;
    else if (!strcmp(month, "Aug"))
        return 8;
    else if (!strcmp(month, "Sep"))
        return 9;
    else if (!strcmp(month, "Oct"))
        return 10;
    else if (!strcmp(month, "Nov"))
        return 11;
    else if (!strcmp(month, "Dec"))
        return 12;
    return -1;
}
// -----------------------------------------------------------------------------------------

// -----------------------------------------------------------------------------------------
// Objeto de Game
typedef struct {
	int appId, age, dlcs, avgPt, sizeLanguages, sizeGeneros;
	char *name, *owners, *website, *developers;
	float price, upvotes;
	bool windows, mac, linxs;
	Date date;
	char **languages, **generos;
} Game;

// Metodo para imprimir Horas e os Minutos corretamente
void formatHours(int seconds) {
    int hour = seconds / 60;
    int min = seconds % 60;

    if(hour == 0 && min == 0) {
        printf("null ");
    } else if (hour > 0 && min > 0) {
        printf("%dh %dm ", hour, min);
    } else {
        if(hour > 0) {
            printf("%dh ", hour);
        }
        if(min > 0) {
            printf("%dm ", min);
        }   
    }
}

// Metodo para Arredondar a Porcentagem
int formatPercentage(double conta) {
    return ((int)round(conta * 100));
}

// Metodo para remover os caracteres
void removeAll(char *string, char swapped) {
    int counter = 0, n = strlen(string);
    char aux[SIZE_ATTRIBUTE];

    for(int i = 0; i < n; i++) {
        if(string[i] != swapped) {
            aux[counter++] = string[i];
        }
    }

    aux[counter] = '\0';
    strcpy(string, aux);
}

// Metodo para retornar o total de elementos de um array (string)
int sizeArray(char *attribute) {
    int counter = 1, n = strlen(attribute);

    for(int i = 0; i < n; i++) {
        if(attribute[i] == ',') {
            counter++;
        }
    }

    return counter;
}

// Metodo para calcular a Porcentagem do Upvotes
double calculatePercentage(char *upvotes, char *notUpvotes) {
    return (atof(upvotes) / (atof(upvotes) + atof(notUpvotes)));
}

// Metodo para converter string para date
void convertDate(Game *game, char *date) {
    char month[4], year[5];
    int n = strlen(date), i = 0, counter = 0;

    for(; i < 3; i++) {
        month[counter++] = date[i];
    }
    month[counter] = '\0';

    switch (n) {
        case 12:
            i = 8;
            break;
        case 11:
            i = 7;
            break;
        case 8:
            i = 4;
            break;
    }

    counter = 0;
    for(; i < (int)strlen(date); i++) {
        year[counter++] = date[i];
    }

    game->date.month = getMonthNumber(month);
    game->date.year = atoi(year);
}

// Metodo que retorna uma string/atributo
char* separateAttributes(char* line, int *position) {
    int index = *position, counter = 0, n = strlen(line);
    char attributes[SIZE_ATTRIBUTE];
    char separator = ',';

    if(line[index] == '"') {
        separator = '"';
        index++;
    }

    if(line[index] == ' ') {
        index++;
    }

    while(index < n && line[index] != separator) {
        attributes[counter++] = line[index++];
    }

    index++;
    if(separator == '"') {
        index++;
    } 
    
    attributes[counter] = '\0';
    char *aux = malloc(sizeof(char) * (strlen(attributes) + 1));
    strcpy(aux, attributes);
    
    *position = index;

    return aux;
}

// Metodo para formatar os arrays
char** formartArray(char *line, int *n) {
    int j = 0; 

    removeAll(line, '\'');
    removeAll(line, '[');
    removeAll(line, ']');
    
    *n = sizeArray(line); 
    char **array = malloc(sizeof(char*) * (*n));

    for(int i = 0; i < *n; i++) {
        array[i] = separateAttributes(line, &j);
    }

    if(*n == 1) {
        int n = strlen(array[0]);
        char aux[n];
        strcpy(aux, array[0]);

        for(int i = 0; i < n; i++) {
            if(aux[i] == '\n' || aux[i] == '\r') {
                aux[i] = '\0';
            }
        }

        strcpy(array[0], aux);
    }

    return array;
}

// Metodo de ler/criar um jogo
void toRead(Game *game, char *line) {
	int i = 0;
	char *attributes;

	// appId
    attributes = separateAttributes(line, &i);
    game->appId = atoi(attributes);
    free(attributes);

    // name 
    game->name = separateAttributes(line, &i);

    // date
    attributes = separateAttributes(line, &i);
    convertDate(game, attributes);
    free(attributes);

    // owners
    game->owners = separateAttributes(line, &i);

    // age
    attributes = separateAttributes(line, &i);
    game->age = atoi(attributes);
    free(attributes);

    // price
    attributes = separateAttributes(line, &i);
    game->price = atof(attributes);
    free(attributes);

    // dlcs
    attributes = separateAttributes(line, &i);
    game->dlcs = atof(attributes);
    free(attributes);

    // languages
    attributes = separateAttributes(line, &i);
    game->sizeLanguages = 0;
    game->languages = formartArray(attributes, &game->sizeLanguages);
    free(attributes);

    // website
    game->website = separateAttributes(line, &i);

    // windows
    attributes = separateAttributes(line, &i);
    game->windows = (strcmp(attributes, "True") ? false:true);
    free(attributes);

    // linuxs
    attributes = separateAttributes(line, &i);
    game->linxs = (strcmp(attributes, "True") ? false:true);
    free(attributes);

    // mac
    attributes = separateAttributes(line, &i);
    game->mac = (strcmp(attributes, "True") ? false:true);
    free(attributes);

    // upvotes
    char *upvotes = separateAttributes(line, &i);
    char *notupvotes = separateAttributes(line, &i);
    game->upvotes = calculatePercentage(upvotes, notupvotes);
    free(upvotes);
    free(notupvotes);

    // avgPt
    attributes = separateAttributes(line, &i);
    game->avgPt = atoi(attributes); 
    free(attributes);

    // developers
    game->developers = separateAttributes(line, &i);

    // generos
    attributes = separateAttributes(line, &i);
    game->sizeGeneros = 0;
    if(strlen(attributes)) {
        game->generos = formartArray(attributes, &game->sizeGeneros);
    }
    free(attributes); 
}

// Metodo de imprimir
void show(Game *game) {
    printf("%d %s %s/%d %s %d %.02f %d [", game->appId, game->name, getMonthName(game->date.month), game->date.year, game->owners, game->age, game->price, game->dlcs);
    for(int i = 0; i < game->sizeLanguages; i++) {
        if(i == game->sizeLanguages-1) {
            printf("%s] ", game->languages[i]);
        } else {
            printf("%s, ", game->languages[i]);
        }
    }
    printf("%s %s %s %s %d%% ", (strlen(game->website) ? game->website : "null"), (game->windows ? "true":"false"), (game->mac ? "true":"false"), (game->linxs ? "true":"false"), formatPercentage(game->upvotes));
    formatHours(game->avgPt);
    printf("%s ", game->developers);
    if(game->sizeGeneros > 0) {
        printf("[");
        for(int i = 0; i < game->sizeGeneros; i++) {
            if(i == game->sizeGeneros-1) {
                printf("%s]\n", game->generos[i]);
            } else {
                printf("%s, ", game->generos[i]);
            }
        }
    } else {
        printf("null\n");
    }
}

// Metodo para clonar jogo
Game* clone(Game *game) {
    Game *cloned = malloc(sizeof(Game));

    // appId
    cloned->appId = game->appId;
    
    // name
    strcpy(cloned->name, game->name);

    // date
    cloned->date.month = game->date.month;
    cloned->date.year = game->date.year;
    
    // owners
    strcpy(cloned->owners, game->owners);
    
    // age
    cloned->age = game->age;
    
    // price
    cloned->price = game->price;
    
    // dlcs
    cloned->dlcs = game->dlcs;
    
    // languages
    cloned->sizeLanguages = game->sizeLanguages;
    for(int i = 0; i < cloned->sizeLanguages; i++) {
        strcpy(cloned->languages[i], game->languages[i]);
    }

    // website
    strcpy(cloned->website, game->website);
    
    // windows
    cloned->windows = game->windows;
    
    // linxs
    cloned->linxs = game->linxs;
    
    // mac
    cloned->mac = game->mac;
    
    // upvotes
    cloned->upvotes = game->upvotes;
    
    // abgPt
    cloned->avgPt = game->avgPt;
    
    // developers
    strcpy(cloned->developers, game->developers);
    
    // generos
    cloned->sizeGeneros = game->sizeGeneros;
    for(int i = 0; i < cloned->sizeGeneros; i++) {
        strcpy(cloned->generos[i], game->generos[i]);
    }

    return cloned;
}

// Metodo para liberar o game
void freeGame(Game *game) {
    free(game->name);
    free(game->owners);
    for(int i = 0; i < game->sizeLanguages; i++) {
        free(game->languages[i]);
    }
    free(game->languages);
    free(game->website);
    free(game->developers);
    if(game->sizeGeneros > 0) {
        for(int i = 0; i < game->sizeGeneros; i++) {
            free(game->generos[i]);
        }
        free(game->generos);
    }
    free(game);
}
// -----------------------------------------------------------------------------------------

// -----------------------------------------------------------------------------------------
typedef struct Cell {
    Game game;
    struct Cell *next, *previous;
} Cell;

// Metodo para criar uma nova Cell
Cell* newCell(Game game) {
    Cell *tmp = malloc(sizeof(Cell));
    tmp->game = game;
    tmp->next = tmp->previous = NULL;

    return tmp;
}

// -----------------------------------------------------------------------------------------

// -----------------------------------------------------------------------------------------
Cell *first, *last;

// Metodo para inicializar as variaveis - Construtor
void start() {
    first = last = NULL;
}

// Metodo inserir no comeco o Game no inicio do arrayList
void insertStart(Game game) {
    if(first == NULL) {
        first = newCell(game);
        last = first;
    } else {
        Cell *tmp = newCell(game);
        
        tmp->next = first->next;
        tmp->next->previous = tmp;
        tmp->previous = first;
        first->next = tmp;
    }
}

// Metodo para inserir o Game no fim do arrayList
void insertEnd(Game game) {
    if(first == NULL) {
        first = newCell(game);
        last = first;
    } else {
        last->next = newCell(game);
        last->next->previous = last;
        last = last->next;
    }
}

// Metodo para remover o game no inicio do arrayList
Game removeStart() {
    Game gameRemoved = first->game;
    first = first->next;
    first->previous = NULL;

    free(first->previous->previous);
    free(first->previous->next);
    free(first->previous);

    return gameRemoved;
}

// Metodo para remover o Game no fim do arrayList
Game removeEnd() {
    Game gameRemoved = last->game;
    last = last->previous;
    last->next = NULL;

    free(last->next->next);
    free(last->next->previous);
    free(last->next);

    return gameRemoved; 
}

// Metodo para mostrar os jogos do arrayList
void showArrayList() {
    int counter = 0;
    for(Cell *i = first; i != NULL; i = i->next) {
        show(&i->game);
    }
}
// -----------------------------------------------------------------------------------------

// Metodo que pesquisa o jogo no arquivo de jogos 
Game searchGameDatabase(int id) {
    char *line = (char *) malloc(sizeof(char) * SIZE_LINE);
    FILE *database = fopen("tmp/games.csv", "r");
    if(database == NULL) { 
        database = fopen("/tmp/games.csv", "r"); 
    }

    while(fgets(line, SIZE_LINE, database) != NULL) {
        Game game;
        toRead(&game, line);

        if(id == game.appId) {
            return game;
            break;
        } 
    }

    fclose(database);
    free(line);
}

// Metodo para verificar o fim do arquivo pubIn
bool theEnd(char *entry) {
    return (entry[0] == 'F' && entry[1] == 'I' && entry[2] == 'M');
}

int main() {
    FILE *pubIn = fopen("pub.in", "r");
    char *entry = (char *) malloc(sizeof(char) * ENTRY_PUBIN);
    int n, counter = 0;
    
    start();
    
    do {
        fgets(entry, ENTRY_PUBIN, pubIn);

        if(!theEnd(entry)) {
            insertEnd(searchGameDatabase(atoi(entry)));
        }

    } while (!theEnd(entry));

    showArrayList();

    fclose(pubIn);
    free(entry);
    return 0;
}