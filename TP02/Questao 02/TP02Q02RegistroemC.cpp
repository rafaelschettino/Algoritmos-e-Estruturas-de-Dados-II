#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

typedef struct Personagem{
	char nome[50];
	int altura;
	double peso;
	char corDoCabelo[50];
	char corDaPele[50];
	char corDosOlhos[50];
	char anoNascimento[50];
	char genero[50];
	char homeworld[50];
}Personagem;

char *substring(char* padrao, char* entrada);
long int indexOf(char* padrao, char* entrada);
Personagem lerPersonagem(Personagem personagem, char* nomeArq);
void imprimir(Personagem personagem);

int main(){
	Personagem personagem[1000];
	char nomeArq[200];
	scanf("%s", nomeArq);
	int i = 0;
	while(nomeArq[0] != 'F' && nomeArq[1] != 'I' && nomeArq[2] != 'M'){
		personagem[i] = lerPersonagem(personagem[i], nomeArq);
		fflush(stdin);
		//imprimir(personagem[i]);
		printf(" ## %s ## %d ## %g ## %s ## %s ## %s ## %s ## %s ## %s ##\n", personagem[i].nome, personagem[i].altura, personagem[i].peso, personagem[i].corDoCabelo, personagem[i].corDaPele, personagem[i].corDosOlhos, personagem[i].anoNascimento, personagem[i].genero, personagem[i].homeworld);
		scanf("%s",  nomeArq);
		i++;
	}//fim while

	return 0;
}

/**
*Le o arquivo e seta os atributos do registro Personagem.
*@param string nomeArq nome do arquivo a ser lido
*@param Personagem personagem que tera seus atributos setados
*/
Personagem lerPersonagem(Personagem personagem, char* nomeArq){
	char* linha = NULL;
	FILE *arq = fopen(nomeArq, "rb");
	size_t len = 0;

	if(arq == NULL){
		printf("Error printed by perror");
	}else{
		getline(&linha, &len, arq);
		while(!feof(arq)){
			getline(&linha, &len, arq);
		}//fim while
	}//fim if
	int i;

	//Definir o aributo nome
	linha = strstr(linha, "name': '");
	char* nome_aux = (char*)malloc(50*sizeof(char));
	for(i = 8; linha[i] != '\''; i++){
		nome_aux[i-8] = linha[i];
	}//fim for i
	nome_aux[i-8] = '\0';
	strcpy(personagem.nome, nome_aux);
	free(nome_aux);

	//Definir atributo altura
	linha = strstr(linha, "height': '");
	char* altura_aux = (char*)malloc(10*(sizeof(char)));
	for(i = 10; linha[i] != '\''; i++){
		altura_aux[i-10] = linha[i];
	}//fim for i
	altura_aux[i-10] = '\0';
	if(strcmp(altura_aux, "unknown") == 0){
		personagem.altura = 0;
	}else{
		personagem.altura = atoi(altura_aux);
	}//fim if
	free(altura_aux);

	//Definir atributo peso
	linha = strstr(linha, "mass': '");
	char* peso_aux = (char*)malloc(10*(sizeof(char)));
	for(i = 8; linha[i] != '\''; i++){
		peso_aux[i-8] = linha[i];
	}//fim for i
	peso_aux[i-8] = '\0';
	if(strcmp(peso_aux, "unknown") == 0){
		personagem.peso = 0;
	}else if(strcmp(peso_aux, "1,358") == 0){
        personagem.peso = 1358;
    }else{
		personagem.peso = atof(peso_aux);
	}//fim if
	free(peso_aux);

	//Definir atributo cor do cabelo
	linha = strstr(linha, "color': '");
	char* corDoCabelo_aux = (char*)malloc(50*(sizeof(char)));
	for(i = 9; linha[i] != '\''; i++){
		corDoCabelo_aux[i-9] = linha[i];
	}//fim for i
	corDoCabelo_aux[i-9] = '\0';
	strcpy(personagem.corDoCabelo, corDoCabelo_aux);
	free(corDoCabelo_aux);

	//Definir atributo cor da pele
	linha = strstr(linha, "skin_color': '");
	char* corDaPele_aux = (char*)malloc(50*(sizeof(char)));
	for(i =14; linha[i] != '\''; i++){
		corDaPele_aux[i-14] = linha[i];
	}//fim for i
	corDaPele_aux[i-14] = '\0';
	strcpy(personagem.corDaPele, corDaPele_aux);
	free(corDaPele_aux);

	//Definir atribuot cor dos olhos
	linha = strstr(linha, "eye_color': '");
	char* corDosOlhos_aux = (char*)malloc(30*(sizeof(char)));
	for(i = 13; linha[i] != '\''; i++){
		corDosOlhos_aux[i-13] = linha[i];
	}//fim for i
	corDosOlhos_aux[i-13] = '\0';
	strcpy(personagem.corDosOlhos, corDosOlhos_aux);
	free(corDosOlhos_aux);

	//Definir atributo ano de nascimento
	linha = strstr(linha, "birth_year': '");
	char* anoNascimento_aux = (char*)malloc(30*(sizeof(char)));
	for(i = 14; linha[i] != '\''; i++){
		anoNascimento_aux[i-14] = linha[i];
	}//fim for i
	anoNascimento_aux[i-14] = '\0';
	strcpy(personagem.anoNascimento, anoNascimento_aux);
	free(anoNascimento_aux);

	//Definir atributo genero
	linha = strstr(linha, "gender': '");
	char* genero_aux = (char*)malloc(20*(sizeof(char)));
	for(i = 10; linha[i] != '\''; i++){
		genero_aux[i-10] = linha[i];
	}//fim for i
	genero_aux[i-10] = '\0';
	strcpy(personagem.genero, genero_aux);
	free(genero_aux);

	//Definir atributo homeworld
	linha = strstr(linha, "homeworld': '");
	char* homeworld_aux = (char*)malloc(30*(sizeof(char)));
	for(i =  13; linha[i] != '\''; i++){
		homeworld_aux[i-13] = linha[i];
	}//fim for i
	homeworld_aux[i-13] = '\0';
	strcpy(personagem.homeworld, homeworld_aux);
	free(homeworld_aux);

	fclose(arq);
	return personagem;
    
}

char *substring(char* padrao, char * entrada){
	char *pointer = strstr(entrada, padrao);
	return strdup(pointer);
}//fim substring

long int indexOf(char* padrao, char *entrada){
	char *pointer = strstr(entrada, padrao);
	return pointer - entrada;
}//fim indexOf

void imprimir(Personagem personagem){
	printf(" ## %s ## %d ## %lf ## %s ## %s ## %s ## %s ## %s ## %s ##\n", personagem.nome, personagem.altura, personagem.peso, personagem.corDoCabelo, personagem.corDaPele, personagem.corDosOlhos, personagem.anoNascimento, personagem.genero, personagem.homeworld);
}
