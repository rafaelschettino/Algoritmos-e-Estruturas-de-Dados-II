#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <err.h>

#define TAMLINHA 100
#define MAXTAM    6
#define bool      short
#define true      1
#define false     0

/**
*Typedef Struct Personagem
*@author Rafael Schettino
*@version mar/2020
*/
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

/**
*Typedef Struct Celula
*/
typedef struct Celula{
    Personagem elemento;    //Elemento inserido na celula.
    struct Celula* prox;    //Aponta a celula prox.
}Celula;

//Prototipos das funcoes utilizadas pelo Registro Personagem
char *substring(char* padrao, char* entrada);
long int indexOf(char* padrao, char* entrada);
Personagem lerPersonagem(Personagem personagem, char* nomeArq);
void imprimir(Personagem personagem);
void interpretar(char* comando);
Personagem clone(Personagem personagem);

//Prototipos das funcoes da estrutura Pilha Dinamica
Celula* novaCelula(Personagem elemento);
void start();
void inserir(Personagem x);
Personagem remover();
int tamanho();
void mostrar();
void mostrarInsercao(Celula* i, int j);

//METODO PRINCIPAL====================================================================
int main(){
    	char nomeArq[200];
	scanf("%s", nomeArq);
	while(nomeArq[0] != 'F' && nomeArq[1] != 'I' && nomeArq[2] != 'M'){
		Personagem personagem;
		personagem = lerPersonagem(personagem, nomeArq);
		fflush(stdin);
		inserir(personagem);
        scanf("%s",  nomeArq);
	}//fim while

    	int num; //Quantidade de registros a serem inseridos/removidos
    	scanf("%d", &num);
    	char comando[100];

    	for(int j = 0; j < num; j++){
        	fflush(stdin);
        	scanf(" %[^\n]s", comando);
        	interpretar(comando);
    	}//fim for j

    	mostrar();

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

/**
*Verifica os comandos a serem realizados para o arquivo lido e chama o metodo para cada acao
*@param char* comando String que contem a linha de comandos a serem realizados na lista
*/
void interpretar(char* comando){
    if(comando[0] == 'I'){
        char aux[30];
        int i;
        for(i = 2; i < strlen(comando); i++){
            aux[i-2] = comando[i];
        }//fim for i
        aux[i-2] = '\0';
        Personagem inserido = lerPersonagem(inserido, aux);
        inserir(inserido);
    }else if(comando[0] == 'R'){
        Personagem removido = remover();
        printf("(R) %s\n", removido.nome);
    }//fim if
}

/**
*Copia os atributos de um Personagem para um novo personagem
*@param Personagem personagem que tera seus atributos copiados 
*@return Personagem resp
*/
Personagem clone(Personagem personagem){
	Personagem resp;
	strcpy(resp.nome, personagem.nome);
	resp.altura = personagem.altura;
	resp.peso = personagem.peso;
	strcpy(resp.corDoCabelo, personagem.corDoCabelo);
	strcpy(resp.corDaPele, personagem.corDaPele);
	strcpy(resp.corDosOlhos, personagem.corDosOlhos);
	strcpy(resp.anoNascimento, personagem.anoNascimento);
	strcpy(resp.genero, personagem.genero);
	strcpy(resp.homeworld, personagem.homeworld);

	return resp;
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


/**
*Pilha dinamica
*@author Rafael Schettino
*@version abr/2020
*/

//TIPO CELULA================================================
Celula* novaCelula(Personagem elemento){
    Celula* nova = (Celula*) malloc(sizeof(Celula));
    nova->elemento = clone(elemento);
    nova->prox = NULL;
    return nova;
}

//PILHA PROPRIAMENTE DITA======================================
Celula* topo;   //Sem celula cabeca

/**
*Cria uma pilha sem elementos.
*/
void start(){
    topo = NULL;
}

/**
*Insere elemento na pilha (politica FILO).
*@param x Personagem elemento a inserir.
*/
void inserir(Personagem x){
    Celula* tmp = novaCelula(x);
    tmp->prox = topo;
    topo = tmp;
    tmp = NULL;
}

/**
*Remove elemento da pilha (politica FILO)
*@return Elemento removido.
*/
Personagem remover(){
    if(topo == NULL){
        errx(1, "Erro ao remover!");
    }//fim if

    Personagem resp = topo->elemento;
    Celula* tmp = topo;
    topo = topo->prox;
    tmp->prox = NULL;
    free(tmp);
    tmp = NULL;
    return resp;
}

/**
*Calcula e retorna o tamanho, em numero de elementos, da pilha.
*@return resp int tamanho
*/
int tamanho(){
    int tamanho = 0;
    Celula* i;
    for(i = topo; i != NULL; i = i->prox, tamanho++);
    return tamanho;
}

/*
*Realiza a primeira chamada da funcao
*/
void mostrar(){
    mostrarInsercao(topo, tamanho()-1);
}

/**
*Mostra os elementos da Pilha na ordem em que os elementos foram inseridos 
*/
void mostrarInsercao(Celula* i, int j){
    if(i != NULL){
        mostrarInsercao(i->prox, j-1);
        printf("[%d] ", j);
        printf(" ## %s ## %d ## %g ## %s ## %s ## %s ## %s ## %s ## %s ##\n", i->elemento.nome, i->elemento.altura, i->elemento.peso, i->elemento.corDoCabelo, i->elemento.corDaPele, i->elemento.corDosOlhos, i->elemento.anoNascimento, i->elemento.genero, i->elemento.homeworld);
    }//fim if
}
