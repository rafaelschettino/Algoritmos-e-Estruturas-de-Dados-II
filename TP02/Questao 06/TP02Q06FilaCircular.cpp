#include <stdlib.h>
#include <stdio.h>
#include <string.h>

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

//Prototipos das funcoes utilizadas pelo Registro Personagem
char *substring(char* padrao, char* entrada);
long int indexOf(char* padrao, char* entrada);
Personagem lerPersonagem(Personagem personagem, char* nomeArq);
void imprimir(Personagem personagem);
void interpretar(char* comando);
Personagem clone(Personagem personagem);

//Prototipos das funcoes da estrutura Fila Circular
void start();
void inserir(Personagem x);
Personagem remover();
int calculaMedia();
int arredondar(double x);
void mostrar();
bool isVazia();

int main(){
	char nomeArq[200];
	scanf("%s", nomeArq);
	int i = 0;
	while(nomeArq[0] != 'F' && nomeArq[1] != 'I' && nomeArq[2] != 'M'){
		Personagem personagem;
		personagem = lerPersonagem(personagem, nomeArq);
		fflush(stdin);
		inserir(personagem);
		int media = calculaMedia(); //Calcula a media das alturas dos componentes da Fila a cada insercao
        printf("%d\n", media);
        scanf("%s",  nomeArq);
		i++;
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
		int media = calculaMedia();
		printf("%d\n", media);
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
*Fila circular de Personagens
*@author Rafael Schettino
*@version mar/2020
*/

/*
*Atributos globais da estrutura Fila Circular
*/
Personagem array[MAXTAM+1];   // Elementos da fila
int primeiro;          // Remove do indice "primeiro".
int ultimo;             // Insere no indice "ultimo".
int n = 0; 				//Controla a quantidade de elementos existentes na fila

/**
*Inicializacoes
*/
void start(){
    primeiro = ultimo = 0;
}

/**
*Insere um elemento na ultima posicao da 
*@param x Personagem elemento a ser inserido.
*Se a fila estiver cheia, antes de inserir um novo elemento, a fila realiza uma remocao
*/
void inserir(Personagem x) {
    //validar insercao
    if(((ultimo + 1) % MAXTAM) == primeiro) {
        //Caso a fila esteja cheia, ao inves de acusar erro, realizamos uma remocao
        Personagem removido = remover();
    }//fim if

    array[ultimo] = x;
    ultimo = (ultimo + 1) % MAXTAM;
    n++;
}


/**
*Remove um elemento da primeira posicao da fila e movimenta 
*os demais elementos para o primeiro da mesma.
*@return resp Personagem elemento a ser removido.
*@Se a fila estiver vazia.
*/
Personagem remover() {
    //validar remocao
    if(primeiro == ultimo) {
        printf("Erro ao remover!");
        exit(1);
    }//fim if

    Personagem resp = array[primeiro];
    primeiro = (primeiro + 1) % MAXTAM;
    n--;
    return resp;
}

/**
*Calcula a media das alturas dos componentes da Fila Circular
*@return int resp contendo a media arredondada das alturas
*/
int calculaMedia(){
	double soma = 0;
	double media;
	int resp;
	int i;
	for(i = primeiro;  i != ultimo; i = ((i + 1) % MAXTAM)){
		soma += array[i].altura;
	}//fim for i
	media = (soma/n);
	resp = arredondar(media);

	return resp;
}

/**
*Metodo que arredonda o valor da variavel recebida
*@return o numero recebido apos o arredondamento
*/
int arredondar(double x){
    return (x >= 0) ? (int)(x + 0.5) : (int)(x - 0.5);
}

/**
*Mostra os elementos presentes na FIla Circular
*/
void mostrar(){
   int i;
   	for(i = primeiro; i != ultimo; i = ((i + 1) % MAXTAM)) {
        printf("[%d] ", i);
        printf(" ## %s ## %d ## %g ## %s ## %s ## %s ## %s ## %s ## %s ##\n",array[i].nome, array[i].altura, array[i].peso, array[i].corDoCabelo, array[i].corDaPele, array[i].corDosOlhos, array[i].anoNascimento, array[i].genero, array[i].homeworld);
    }//fim for i
}

/**
*Retorna um bool indicando se a fila esta vazia
*@return bool indicando se a fila esta vazia
*/
bool isVazia() {
    return (primeiro == ultimo); 
}


