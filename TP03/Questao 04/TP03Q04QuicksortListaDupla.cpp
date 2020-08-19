#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <err.h>
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

/**
*Typedef Struct CelulaDupla
*/
typedef struct CelulaDupla{
    Personagem elemento;    //Elemento inserido na celula.
    struct CelulaDupla* prox;   //Aponta a celula prox.
    struct CelulaDupla* ant;    //Aponta a celula anterior.
} CelulaDupla;

//Prototipos das funcoes utilizadas pelo Registro Personagem
char *substring(char* padrao, char* entrada);
long int indexOf(char* padrao, char* entrada);
Personagem lerPersonagem(Personagem personagem, char* nomeArq);
void imprimir(Personagem personagem);
//void interpretar(char* comando);
Personagem clone(Personagem personagem);

//Prototipos das funcoes da estrutura Lista Dupla
CelulaDupla* novaCelulaDupla(Personagem elemento);
void start(Personagem x);
void inserirInicio(Personagem x);
void inserirFim(Personagem x);
void inserir(Personagem x, int pos);
Personagem removerInicio();
Personagem removerFim();
Personagem remover(int pos);
void quicksortRec();
void quicksort(int esq, int dir);
CelulaDupla* getPosElemento(int pos);
void insercaoPorNome();
int tamanho();
int getComp();
int getMov();
void mostrar();

//METODO PRINCIPAL ==================================================================================================
int main(){
	char nomeArq[200];
	scanf("%s", nomeArq);
    int i = 0;
	while(nomeArq[0] != 'F' && nomeArq[1] != 'I' && nomeArq[2] != 'M'){
		Personagem personagem;
		personagem = lerPersonagem(personagem, nomeArq);
		fflush(stdin);
        //Caso seja a primeira insercao da lista, chamamos o metodo start() para setar as celulas primeiro e ultimo
        if(i == 0){
            start(personagem);
        }//fim if   
        inserirFim(personagem);
        i++;
		scanf("%s",  nomeArq);
	}//fim while

	clock_t inicio = clock();
    quicksortRec();
	//Realiza o metodo de insercao para ordenar pelo atributo nome, aqueles personagens que tem cor de cabelo igual
    insercaoPorNome();
	clock_t fim = clock();
	double total = (fim-inicio) / 1000.0;
	FILE *arq = fopen("matrícula_quicksort2.txt", "w");
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
*Lista Dupla
*@author Rafael Schettino
*@version abr/2020
*/

//TIPO CELULA DUPLA ===================================================
CelulaDupla* novaCelulaDupla(Personagem elemento){
    CelulaDupla* nova = (CelulaDupla*) malloc(sizeof(CelulaDupla));
    nova->elemento = clone(elemento);
    nova->ant = nova->prox = NULL;
    return nova;
}

//LISTA DUPLA PROPRIAMENTE DITA =========================================
CelulaDupla* primeiro;
CelulaDupla* ultimo;
int comp;       //Conta o numero de comparacoes efetuadas pelo programa
int mov;        //Conta o numero de movimentacoes efetuadas pelo programa;

/**
*Cria uma lista dupla sem elementos (somente no cabeca).
*/
void start(Personagem x){
    primeiro = novaCelulaDupla(x);
    ultimo = primeiro;
	comp = 0;
	mov = 0;
}

/**
*Insere um elemento na primeira posicao da lista.
*@param x Personagem elemento a ser inserido
*/
void inserirInicio(Personagem x){
    CelulaDupla* tmp = novaCelulaDupla(x);

    tmp->ant = primeiro;
    tmp->prox = primeiro->prox;
    primeiro->prox = tmp;
    if(primeiro == ultimo){
        ultimo = tmp;
    }else{
        tmp->prox->ant = tmp;
    }//fim if
    tmp = NULL;
}

/**
*Insere um elemento na ultima posica da lista.
*@param x Personagem elemento a ser inserido.
*/
void inserirFim(Personagem x){
    ultimo->prox = novaCelulaDupla(x);
    ultimo->prox->ant = ultimo;
    ultimo = ultimo->prox;
}

/**
*Remove um elemento da primeira posicao da lista.
*@return resp Personagem elemento a ser inserido
*/
Personagem removerInicio(){
    if(primeiro == ultimo){
        errx(1, "Erro ao remover (vazia)!");
    }//fim if

    CelulaDupla* tmp = primeiro;
    primeiro = primeiro->prox;
    Personagem resp = primeiro->elemento;
    tmp->prox = primeiro->ant = NULL;
    free(tmp);
    tmp = NULL;
    return resp;
}

/**
*Remoe um elemento da ultima posicao da lista.
*@return resp Personagem a ser removido.
*/
Personagem removerFim(){
    if(primeiro == ultimo){
        errx(1, "Erro ao remover (vazia)!");
    }//fim if

    Personagem resp = ultimo->elemento;
    ultimo = ultimo->ant;
    ultimo->prox->ant = NULL;
    free(ultimo->prox);
    ultimo->prox = NULL;
    return resp;
}

/**
*Calcula o tamanho, em numero de elementos, da lista.
*@return resp int tamanho.
*/
int tamanho(){
    int tamanho = 0;
    CelulaDupla* i;
    for(i = primeiro; i != ultimo; i = i->prox, tamanho++);
    return tamanho;
}

/**
*Insere um elemento em uma posicao especifica considerando que o 
*primeiro elemento valido esta na posicao 0.
*@param x Personagem elemento a ser inserido.
*@param pos int posicao da insercao.
*@throws Exception Se <code>posicao</code> invalida.
*/
void inserir(Personagem x, int pos) {
    int tam = tamanho();

    if(pos < 0 || pos > tam){
        errx(1, "Erro ao remover (posicao %d/%d invalida!", pos, tam);
    }else if (pos == 0){
        inserirInicio(x);
    }else if (pos == tam){
        inserirFim(x);
    }else {
        //Caminhar ate a posicao anterior a insercao
        CelulaDupla* i = primeiro;
        int j;
        for(j = 0; j < pos; j++, i = i->prox);

        CelulaDupla* tmp = novaCelulaDupla(x);
        tmp->ant = i;
        tmp->prox = i->prox;
        tmp->ant->prox = tmp->prox->ant = tmp;
        tmp = i = NULL;
    }//fim if
}

/**
*Remove um elemento de uma posicao especifica da lista
*considerando que o primeiro elemento valido esta na posicao 0.
*@param posicao Meio da remocao.
*@return resp Personagem elemento a ser removido.
*@throws Exception Se <code>posicao</code> invalida.
*/
Personagem remover(int pos){
    Personagem resp;
    int tam = tamanho();

    if(primeiro == ultimo){
        errx(1, "Erro ao remover (vazia)!");
    }else if(pos < 0 || pos >= tam){
        errx(1, "Erro ao remover(posicao %d/%d invalida!", pos, tam);
    }else if(pos == 0){
        resp = removerInicio();
    }else if(pos == tam-1){
        resp = removerFim();
    }else{
        //Caminhar ate a posicao anrerior a remocao
        CelulaDupla* i = primeiro->prox;
        int j;
        for(j = 0; j < pos; j++, i = i->prox);

        i->ant->prox = i->prox;
        i->prox->ant = i->ant;
        resp = i->elemento;
        i->prox = i->ant = NULL;
        free(i);
        i = NULL;
    }//fim if

    return resp;
}

/*
*Realiza a primeira chamada da recursao
*/
void quicksortRec(){
    quicksort(0, tamanho()-1);
}

/*
*Realiza a ordenacao da Lista Dupla utilizando o metodo Quicksort.
*/
void quicksort(int esq, int dir){
    int i = esq;
    int j = dir;
    CelulaDupla* CelulaPivo = getPosElemento((dir+esq)/2);
    char* pivo = (char*)malloc(50*(sizeof(char)));
    strcpy(pivo, CelulaPivo->elemento.corDoCabelo);
	mov++;
    
    while(i <= j){
        while(strcmp(getPosElemento(i)->elemento.corDoCabelo, pivo) < 0){
            i++;
			comp++;
        }//fim while

        while(strcmp(getPosElemento(j)->elemento.corDoCabelo, pivo) > 0){
            j--;
			comp++;
        }//fim while

        if(i <= j){
            //Swap dos elementos
            Personagem tmp = getPosElemento(i)->elemento;
            getPosElemento(i)->elemento = getPosElemento(j)->elemento;
            getPosElemento(j)->elemento = tmp;
            i++;
            j--;
			mov += 3;
        }//fim if
    }//fim while

    free(pivo);
    if(esq < j) quicksort(esq, j);
    if(dir > i) quicksort(i, dir);
}

/**
*Metodo de ordenacao por Insercao 
*Ordena por nome, aqueles elementos que possuem cor de cabelo igual
*/
void insercaoPorNome(){
    for(CelulaDupla* i = primeiro->prox->prox; i != NULL; i = i->prox){
		Personagem tmp = i->elemento;
		CelulaDupla* j = i->ant;
		mov++;

		//Caso as cores do cabelo de dois elementos sejam iguais, a ordenacao entre eles ocorre por nome
		while((j != primeiro) && (strcmp(j->elemento.corDoCabelo, tmp.corDoCabelo) == 0) && (strcmp(j->elemento.nome, tmp.nome) > 0)){
			j->prox->elemento = j->elemento;
			j = j->ant;
			comp++;
		}//fim while
	    j->prox->elemento = clone(tmp);
		mov++;
	}//fim for i
}

/**
*Procura e retorna a Celula que se encontra em determinada posicao da lista
*@param int pos que servira para a busca da CelulaDupla
*@return CelulaDupla retorno, que é a Celula que se encontra na posicao recebida
*/
CelulaDupla* getPosElemento(int pos){
	CelulaDupla* retorno; 
	CelulaDupla* i = primeiro->prox;
	for(int j = 0; j < pos; j++, i = i->prox);
	retorno = i;

	return retorno;
}

/**
*Retorna o numero de comparacoes realizadas pelo programa
*/
int getComp(){
	return comp;
}

/**
*Retorna o numero de movimentacoes realizadas pelo programa
*/
int getMov(){
	return mov;
}

/*
*Mostra os elementos da lista dupla.
*/
void mostrar(){
    CelulaDupla* i;
    for(i = primeiro->prox; i != NULL; i = i->prox){
        printf(" ## %s ## %d ## %g ## %s ## %s ## %s ## %s ## %s ## %s ##\n", i->elemento.nome, i->elemento.altura, i->elemento.peso, i->elemento.corDoCabelo, i->elemento.corDaPele, i->elemento.corDosOlhos, i->elemento.anoNascimento, i->elemento.genero, i->elemento.homeworld);
    }//fim for i
}
