#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define bool short
#define true 1 
#define false 0
#define TAMLINHA 1000
#define NUMENTRADA 1000

bool isFim(char *s){
	return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

/*
*Substitui de forma recursiva as ocorrencias da letra1 
*na string de entrada pela letra2 e retorna a string com as alteracoes efetuadas
*@param s String que sera aleatoriamente modificada
*@param char letra1 e char letra2, letras sorteadas para realizarem as alteracoes
*/
char* alteracaoRecursiva(char *s, int i, char letra1, char letra2){
	if(i == strlen(s)){
		s[i] = '\0';
	}else{
		if(s[i] == letra1){
			s[i] = letra2;
			alteracaoRecursiva(s, i+1, letra1, letra2);
		}else{
			alteracaoRecursiva(s, i+1, letra1, letra2);
		}//fim if
	}//fim if

	return s;
	
}

int main(){
	srand(4);
	char entrada[NUMENTRADA][TAMLINHA];
   	int numEntrada = 0;
	
	do{
		fgets(entrada[numEntrada], TAMLINHA, stdin);
	}while(isFim(entrada[numEntrada++]) == false);
	numEntrada--;

	for(int i = 0; i < numEntrada; i++){
		char letra1 = (char)('a' +(rand() % 26));
		char letra2 = (char)('a' +(rand() % 26));
		printf("%s", alteracaoRecursiva(entrada[i], 0, letra1, letra2));
		//alteracaoRecursiva(entrada[i], 0, letra1, letra2);
	}//fim for i	
}
