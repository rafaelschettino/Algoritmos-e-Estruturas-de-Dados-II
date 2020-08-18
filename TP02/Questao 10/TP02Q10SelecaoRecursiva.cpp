#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>

#define TAMLINHA 100
#define MAXTAM    1000
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

//Prototipos das funcoes utilizadas pelo Registro Personagem
char *substring(char* padrao, char* entrada);
long int indexOf(char* padrao, char* entrada);
Personagem lerPersonagem(Personagem personagem, char* nomeArq);
void imprimir(Personagem personagem);
Personagem clone(Personagem personagem);

//Prototipos das funcoes da estrutura Lista 
void start();
void inserirInicio(Personagem x);
void inserirFim(Personagem x);
void inserir(Personagem x, int pos);
Personagem removerInicio();
Personagem removerFim();
Personagem remover(int pos);
void selecao();
void selecaoRecursiva(int i, int j, int menor);
void swap(int i, int j);
int getComp();
int getMov();
void mostrar();

int main(){
	char nomeArq[200];
	scanf("%s", nomeArq);
	while(nomeArq[0] != 'F' && nomeArq[1] != 'I' && nomeArq[2] != 'M'){
		Personagem personagem;
        personagem = lerPersonagem(personagem, nomeArq);
		fflush(stdin);
        inserirFim(personagem);
		scanf("%s",  nomeArq);
	}//fim while
    
	clock_t comeco = clock();
    selecao();
	clock_t fim = clock();
	double total = (fim-comeco) / 1000.0;
	FILE *arq = fopen("matrículaselecaoRecursiva.txt", "w");
	fprintf(arq, "651636\t%d\t%d\t%lf", getComp(), getMov(), total);

	fclose(arq);
    mostrar();

	return 0;
}

/**
*Le o arquivo e seta os atributos do registro Personagem.
*@param string nomeArq nome do arquivo a ser lido
*@param Personagem personagem que tera seus atributos setados
*/
Personagem lerPersonagem(Personagem personagem, char* nomeArq){
	char*linha  = NULL;
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
*Lista estatica de Personagens
*@author Rafael Schettino
*@version mar/2020
*/

/*
*Atributos globais da estrutura Lista
*/
Personagem array[MAXTAM];   // Elementos da lista
int n;               // Quantidade de array.
int comp = 0;       //Conta o numero de comparacoes efetuadas pelo programa
int mov = 0;        //Conta o numero de movimentacoes efetuadas pelo programa;

/**
*Inicializacoes
*/
void start(){
   n = 0;
}

/**
*Insere um elemento na primeira posicao da lista e move os demais
*elementos para o fim da 
*@param x Personagem elemento a ser inserido.
*/
void inserirInicio(Personagem x) {
    int i;

    //validar insercao
    if(n >= MAXTAM){
        printf("Erro ao inserir!");
        exit(1);
    }//fim if 

    //levar elementos para o fim do array
    for(i = n; i > 0; i--){
        array[i] = array[i-1];
    }//fim for i

    array[0] = x;
    n++;
}


/**
*Insere um elemento na ultima posicao da 
*@param x Personagem elemento a ser inserido.
*/
void inserirFim(Personagem x) {
    //validar insercao
    if(n >= MAXTAM){
        printf("Erro ao inserir!");
        exit(1);
    }//fim if

    array[n] = x;
    n++;
}


/**
*Insere um elemento em uma posicao especifica e move os demais
*elementos para o fim da 
*@param x Personagem elemento a ser inserido.
*@param pos Posicao de insercao.
*/
void inserir(Personagem x, int pos) {
    int i;

    //validar insercao
    if(n >= MAXTAM || pos < 0 || pos > n){
        printf("Erro ao inserir!");
        exit(1);
    }//fim if

    //levar elementos para o fim do array
    for(i = n; i > pos; i--){
        array[i] = array[i-1];
    }//fim for

    array[pos] = x;
    n++;
}


/**
*Remove um elemento da primeira posicao da lista e movimenta 
*os demais elementos para o inicio da mesma.
*@return resp int elemento a ser removido.
*/
Personagem removerInicio() {
    int i;
    Personagem resp;

    //validar remocao
    if(n == 0) {
        printf("Erro ao remover!");
        exit(1);
    }//fim if

    resp = array[0];
    n--;

    for(i = 0; i < n; i++){
        array[i] = array[i+1];
    }//fim for

    return resp;
}


/**
*Remove um elemento da ultima posicao da 
*@return resp Personagem elemento a ser removido.
*/
Personagem removerFim() {
    //validar remocao
    if (n == 0) {
        printf("Erro ao remover!");
        exit(1);
    }//fim if

    return array[--n];
}


/**
*Remove um elemento de uma posicao especifica da lista e 
*movimenta os demais elementos para o inicio da mesma.
*@param pos Posicao de remocao.
*@return resp Personagem elemento a ser removido.
*/
Personagem remover(int pos){
    int i;
    Personagem resp;

    //validar remocao
    if (n == 0 || pos < 0 || pos >= n) {
        printf("Erro ao remover!");
        exit(1);
    }//fim if

    resp = array[pos];
    n--;

    for(i = pos; i < n; i++){
        array[i] = array[i+1];
    }//fim for 

    return resp;
}

/*
*Realiza a primeira chamada da recursividade
*/
void selecao(){
    selecaoRecursiva(0, 1, 0);
}

/*
*Realiza de maneira recursiva e utilizando o metodo de selecao, a ordenacao da lista.
*/
void selecaoRecursiva(int i, int j, int menor){
    if(i >= (n-1)){
   	  	comp++;
   	}else if(j == n){
   	    comp++;
   	  	swap(menor, i);
   	  	selecaoRecursiva(i+1,i+1,i+1);
   	}else if(strcmp(array[menor].nome, array[j].nome) > 0){
   	    comp++;
   	  	menor = j;
   	  	selecaoRecursiva(i, j+1, menor);
   	}else{
   	    comp++;
        selecaoRecursiva(i, j+1, menor);
   	}//fim if
}

/**
*Troca o conteudo de duas posicoes do array
*@param i int primeira posicao
*@param j int segunda posicao
*/
void swap(int i, int j){
   Personagem temp = array[i];
   array[i] = array[j];
   array[j] = temp;
   mov += 3;
}

int getComp(){
    return comp;
}

int getMov(){
    return mov;
}

/**
*Mostra os elementos da Lista.
*/
void mostrar(){
    int i;

    for(i = 0; i < n; i++){
        //printf("[%d] ", i);
        printf(" ## %s ## %d ## %g ## %s ## %s ## %s ## %s ## %s ## %s ##\n", array[i].nome, array[i].altura, array[i].peso, array[i].corDoCabelo, array[i].corDaPele, array[i].corDosOlhos, array[i].anoNascimento, array[i].genero, array[i].homeworld);
    }//fim for i
}