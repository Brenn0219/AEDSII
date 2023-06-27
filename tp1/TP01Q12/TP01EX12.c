#include <string.h>
#include <stdio.h>
#include <stdlib.h>

int testePalindromo(char* str, int i, int n, int resp) {
	if(i >= n) {
		return resp;
	} else {
		if(str[i] != str[n - i - 1]) {
			resp = 0;
		}

		return testePalindromo(str, i+1, n, resp); 
	}
}

int isFim(char* str) {
	int resp = 0;
	if(str[0] = 'F' && str[1] == 'I' && str[2] == 'M') {
		resp = 1;	
	}
	return resp;
}

int main(void) {
	char entradas[1000];
	
	do{
		fgets(entradas, 1000, stdin);

		if(entradas[strlen(entradas-1) == '\n']) {
			entradas[strlen(entradas)-1] = 0;
		}
		
		if(testePalindromo(entradas, 0 , strlen(entradas), 1) == 1) {
			printf("SIM\n");
		} else {
			printf("NAO\n");
		}

	}while(isFim(entradas) != 1);
	puts(entradas);

	return 0;
}
