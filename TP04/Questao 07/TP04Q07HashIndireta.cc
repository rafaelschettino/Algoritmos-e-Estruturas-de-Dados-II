#include <cstring>
#include <err.h>
#include <iostream>
#include <time.h>

using namespace std;

/**
*Class Personagem
*@author Rafael Schettino
*@version maio/2020
*/
class Personagem{
    private:
        string nome;
	    int altura;
	    double peso;
	    string corDoCabelo;
	    string corDaPele;
	    string corDosOlhos;
	    string anoNascimento;
	    string genero;
	    string homeworld;

    public:
        Personagem();
        Personagem(string, int, double, string, string, string, string, string, string);
        void setNome(string);
        void setAltura(int);
        void setPeso(double);
        void setCorDoCabelo(string);
        void setCorDaPele(string);
        void setCorDosOlhos(string);
        void setAnoNascimento(string);
        void setGenero(string);
        void setHomeWorld(string);
        string getNome();
        int getAltura();
        double getPeso();
        string getCorDoCabelo();
        string getCorDaPele();
        string getCorDoOlhos();
        string getAnoNascimento();
        string getGenero();
        string getHomeWorld();
        void lerPersonagem(char* nomeArq);
        void imprimir();
};

/**
*Construtor da classe
*/
Personagem::Personagem(){
    setNome("");
	setAltura(0);
	setPeso(0);
	setCorDoCabelo("");
	setCorDaPele("");
	setCorDosOlhos("");
	setAnoNascimento("");
	setGenero("");
	setHomeWorld("");
}

/**
*Construtor da classe
*@param atributos nome, altura, peso, corDoCabelo, corDaPele, corDosOlhos, anoNascimento, genero, homeworld
*/
Personagem::Personagem(string nome, int altura, double peso, string corDoCabelo, string corDaPele, string corDosOlhos, string anoNascimento, string genero, string homeworld){
    setNome(nome);
    setAltura(altura);
    setPeso(peso);
    setCorDoCabelo(corDoCabelo);
    setCorDaPele(corDaPele);
    setCorDosOlhos(corDosOlhos);
    setAnoNascimento(anoNascimento);
    setGenero(genero);
    setHomeWorld(homeworld);
}

void Personagem::setNome(string nome){
    this->nome = nome;
}
void Personagem::setAltura(int altura){
    this->altura = altura;
}
void Personagem::setPeso(double peso){
    this->peso = peso;
}
void Personagem::setCorDoCabelo(string corDoCabelo){
    this->corDoCabelo = corDoCabelo;
}
void Personagem::setCorDaPele(string corDaPele){
    this->corDaPele = corDaPele;
}
void Personagem::setCorDosOlhos(string corDosOlhos){
    this->corDosOlhos = corDosOlhos;
}
void Personagem::setAnoNascimento(string anoNascimento){
    this->anoNascimento = anoNascimento;
}
void Personagem::setGenero(string genero){
    this->genero = genero;
}
void Personagem::setHomeWorld(string homeworld){
    this->homeworld = homeworld;
}

string Personagem::getNome(){
    return this->nome;
}

int Personagem::getAltura(){
    return this->altura;
}
double Personagem::getPeso(){
    return this->peso;
}
string Personagem::getCorDoCabelo(){
    return this->corDoCabelo;
}
string Personagem::getCorDaPele(){
    return this->corDaPele;
}
string Personagem::getCorDoOlhos(){
    return this->corDosOlhos;
}
string Personagem::getAnoNascimento(){
    return this->anoNascimento;
}
string Personagem::getGenero(){
    return this->genero;
}
string Personagem::getHomeWorld(){
    return this->homeworld;
}

void Personagem::imprimir(){
    cout << " ## " << this->nome;
    cout << " ## " << this->altura;
    printf(" ## %g", this->peso);
    cout << " ## " << this->corDoCabelo;
    cout << " ## " << this->corDaPele;
    cout << " ## " << this->corDosOlhos;
    cout << " ## " << this->anoNascimento;
    cout << " ## " << this->genero;
    cout << " ## " << this->homeworld;
    cout << " ## \n";
}

/**
*Le o arquivo e seta os atributos do registro Personagem.
*@param string nomeArq nome do arquivo a ser lido
*/
void Personagem::lerPersonagem(char* nomeArq){
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
	setNome(nome_aux);
	free(nome_aux);

	//Definir atributo altura
	linha = strstr(linha, "height': '");
	char* altura_aux = (char*)malloc(10*(sizeof(char)));
	for(i = 10; linha[i] != '\''; i++){
		altura_aux[i-10] = linha[i];
	}//fim for i
	altura_aux[i-10] = '\0';
	if(strcmp(altura_aux, "unknown") == 0){
		setAltura(0);
	}else{
		setAltura(atoi(altura_aux));
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
		setPeso(0);
	}else if(strcmp(peso_aux, "1,358") == 0){
        setPeso(1358);
    }else{
		setPeso(atof(peso_aux));
	}//fim if
	free(peso_aux);

	//Definir atributo cor do cabelo
	linha = strstr(linha, "color': '");
	char* corDoCabelo_aux = (char*)malloc(50*(sizeof(char)));
	for(i = 9; linha[i] != '\''; i++){
		corDoCabelo_aux[i-9] = linha[i];
	}//fim for i
	corDoCabelo_aux[i-9] = '\0';
	//strcpy(personagem.corDoCabelo, corDoCabelo_aux);
    setCorDoCabelo(corDoCabelo_aux);
	free(corDoCabelo_aux);

	//Definir atributo cor da pele
	linha = strstr(linha, "skin_color': '");
	char* corDaPele_aux = (char*)malloc(50*(sizeof(char)));
	for(i =14; linha[i] != '\''; i++){
		corDaPele_aux[i-14] = linha[i];
	}//fim for i
	corDaPele_aux[i-14] = '\0';
	//strcpy(personagem.corDaPele, corDaPele_aux);
    setCorDaPele(corDaPele_aux);
	free(corDaPele_aux);

	//Definir atribuot cor dos olhos
	linha = strstr(linha, "eye_color': '");
	char* corDosOlhos_aux = (char*)malloc(30*(sizeof(char)));
	for(i = 13; linha[i] != '\''; i++){
		corDosOlhos_aux[i-13] = linha[i];
	}//fim for i
	corDosOlhos_aux[i-13] = '\0';
	//strcpy(personagem.corDosOlhos, corDosOlhos_aux);
    setCorDosOlhos(corDosOlhos_aux);
	free(corDosOlhos_aux);

	//Definir atributo ano de nascimento
	linha = strstr(linha, "birth_year': '");
	char* anoNascimento_aux = (char*)malloc(30*(sizeof(char)));
	for(i = 14; linha[i] != '\''; i++){
		anoNascimento_aux[i-14] = linha[i];
	}//fim for i
	anoNascimento_aux[i-14] = '\0';
	//strcpy(personagem.anoNascimento, anoNascimento_aux);
    setAnoNascimento(anoNascimento_aux);
	free(anoNascimento_aux);

	//Definir atributo genero
	linha = strstr(linha, "gender': '");
	char* genero_aux = (char*)malloc(20*(sizeof(char)));
	for(i = 10; linha[i] != '\''; i++){
		genero_aux[i-10] = linha[i];
	}//fim for i
	genero_aux[i-10] = '\0';
	//strcpy(personagem.genero, genero_aux);
    setGenero(genero_aux);
	free(genero_aux);

	//Definir atributo homeworld
	linha = strstr(linha, "homeworld': '");
	char* homeworld_aux = (char*)malloc(30*(sizeof(char)));
	for(i =  13; linha[i] != '\''; i++){
		homeworld_aux[i-13] = linha[i];
	}//fim for i
	homeworld_aux[i-13] = '\0';
	//strcpy(personagem.homeworld, homeworld_aux);
    setHomeWorld(homeworld_aux);
	free(homeworld_aux);

	fclose(arq);
    
}

/**
*Classe Celula da Lista Dinamica
*/
class Celula{
    public:
        Personagem* elemento;
        Celula* prox;
        Celula(){
            this->elemento = new Personagem();
            this->prox = NULL;
        }
        Celula(Personagem* & elemento){
            this->elemento = elemento;
            this->prox = NULL;
        }
};

/**
*Lista dinamica
*@author Rafael Schettino
*@version maio/2020
*/
class Lista{
    private:
        Celula* primeiro;
        Celula* ultimo;
        int comp = 0;	//Conta o numero de comparacoes realizadas pelo programa.
    public:
        Lista(){
            primeiro = new Celula();
            ultimo = primeiro;
        }
        void inserirInicio(Personagem* &);
        bool pesquisar(string);
        int getComp();
};

/**
*Insere um elemento na primeira posicao da lista 
*@param x Personagem elemento a ser inserido
*/
void Lista::inserirInicio(Personagem* &x){
    Celula* tmp = new Celula(x);
    tmp->prox = primeiro->prox;
    primeiro->prox = tmp;
    if(primeiro == ultimo){
        ultimo = tmp;
    }//fim if

    tmp = NULL;
}

/**
*Procura um elemento e retorna se ele existe.
*@param x Elemento a ser pesquisado.
*@return <code>true</code> se o elemento existir,
*<code>false</code> em caso contrario.
*/
bool Lista::pesquisar(string x){
    bool resp = false;
    for(Celula* i = primeiro->prox; i != NULL; i = i->prox){
        if(x.compare(i->elemento->getNome()) == 0){
            resp = true;
            i = ultimo;
        }//fim if
        comp++;
    }//fim for i

    return resp;
}

/**
*Retorna o numero de comparacoes realizadas por essa lista
*/
int Lista::getComp(){
    return this->comp;
}

/**
*Classe Hash Indireto Lista
*@author Rafael Schettino
*@version maio/2020
*/
class HashIndiretoLista{
    public:
        Lista* tabela[25];
        int tamanho;
        /**
        *Constutor da classe
        *@param int tamanho da tabela
        */
        HashIndiretoLista(int tamanho){
            this->tamanho = tamanho;
            //tabela = new Lista[tamanho];
            for(int i = 0; i < tamanho; i++){
                tabela[i] = new Lista();
            }//fim for i
        }
        int h(Personagem* &);
        bool pesquisar(string);
        void inserirInicio(Personagem* &);
        int getComp();
};

int HashIndiretoLista::h(Personagem* &elemento){
    return elemento->getAltura() % tamanho;
}

bool HashIndiretoLista::pesquisar(string x){
    int i = 0;
    bool resp = false;
    while(resp == false && i < tamanho){
        resp = tabela[i]->pesquisar(x);
        i++;
    }//fim while

    return resp;
}

void HashIndiretoLista::inserirInicio(Personagem* &elemento){
    int pos = h(elemento);
    tabela[pos]->inserirInicio(elemento);
}

/**
*Retorna o numero de comparacoes realizadas pelo programa
*/
int HashIndiretoLista::getComp(){
    int i = 0;
    int comp = 0;
    while(i < tamanho){
        comp += tabela[i]->getComp();
        i++;
    }
    return comp;
}

//METODO PRINCIPAL =================================
int main(){
    HashIndiretoLista* tabela = new HashIndiretoLista(25);
    string nome_pesquisa;
    char nomeArq[200];
    scanf("%s", nomeArq);
    while(nomeArq[0] != 'F' && nomeArq[1] != 'I' && nomeArq[2] != 'M'){
		Personagem* personagem = new Personagem();
		personagem->lerPersonagem(nomeArq);
		fflush(stdin);
		tabela->inserirInicio(personagem);
        scanf("%s",  nomeArq);
	}//fim while

    clock_t comeco = clock();
	cin.ignore();
    getline(cin, nome_pesquisa);
    while(nome_pesquisa.compare("FIM") != 0){
        cout << nome_pesquisa << " ";
        bool resp = tabela->pesquisar(nome_pesquisa);
		if(resp){
			cout << "SIM";
		}else{
			cout << "NÃO";
		}//fim if
		cout << "\n";
    	getline(cin, nome_pesquisa);
    }//fim while
    clock_t fim = clock();
	double total = (fim-comeco) / 1000.0;
	FILE *arq = fopen("matrícula_hashIndireta.txt", "w");
    fprintf(arq, "651636\t%lf\t%d", total, tabela->getComp());

    fclose(arq);
}