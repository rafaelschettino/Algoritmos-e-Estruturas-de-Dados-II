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
*No da arvore binaria AVL
*/
class No {
   public:
        Personagem elemento;    //Conteudo do no.
	    No *esq, *dir;  //Filhos da esq e dir.
        int nivel;  //Numero de niveis abaixo do no
        No(Personagem* &);
        void setNivel();
        int getNivel(No*);
};

/**
*Construtor da classe.
*@param elemento Conteudo do no.
 */
No::No(Personagem* &elemento) {
   this->elemento = *elemento;
   this->esq = NULL;
   this->dir = NULL;
   this->nivel = 1;
}

void No::setNivel(){
    this->nivel = 1 + std::max(getNivel(esq), getNivel(dir));
}

int No::getNivel(No* no){
    return (no == NULL) ? 0 : no->nivel;
}

class AVL{
   private:
      No* raiz; // Raiz da arvore.
	  int comp = 0;	//Conta o numero de comparacoes realizadas pelo programa.
	   bool pesquisar(string, No*);
	   void mostrarCentral(No*);
	   void mostrarPre(No*);
	   void mostrarPos(No*);
	   void inserir(Personagem* &, No* &);
	   No* balancear(No* &);
	   No* rotacionarDir(No* &);
	   No* rotacionarEsq(No* &);

   public:
      AVL();
       //int getAltura();  
	   bool pesquisar(string);
	   void mostrarPre();
	   void inserir(Personagem* &);
	   int getComp();
};

/**
*Construtor da classe
*/
AVL::AVL(){
    raiz = NULL;
}

/**
*Metodo publico iterativo para pesquisar elemento.
*@param x Elemento que sera procurado.
*@return <code>true</code> se o elemento existir,
*<code>false</code> em caso contrario.
*/
bool AVL::pesquisar(string x) {
	cout << "raiz ";
    return pesquisar(x, raiz);
}

/**
 * Metodo privado recursivo para pesquisar elemento.
 * @param x Elemento que sera procurado.
 * @param i No em analise.
 * @return <code>true</code> se o elemento existir,
 * <code>false</code> em caso contrario.
 */
bool AVL::pesquisar(string x, No* i) {
    bool resp;
    if(i == NULL){
        resp = false;
		comp++;
    }else if(x.compare(i->elemento.getNome()) == 0){
        resp = true;
		comp+=2;
    }else if(x.compare(i->elemento.getNome()) < 0){
		cout << "esq ";
		comp+=3;
        resp = pesquisar(x, i->esq);
    }else {
		cout << "dir ";
		comp+=3;
        resp = pesquisar(x, i->dir);
    }

    return resp;
}     

/**
*Metodo publico iterativo para exibir elementos.
*/
void AVL::mostrarPre() {
   	mostrarPre(raiz);
}

/**
*Metodo privado recursivo para exibir elementos.
*@param i No em analise.
*/
void AVL::mostrarPre(No* i) {
	if(i != NULL) {
    	cout << i->elemento.getNome() << "\n"; // Conteudo do no.
    	mostrarPre(i->esq); // Elementos da esquerda.
    	mostrarPre(i->dir); // Elementos da direita.
   	}//fim if
}

/**
 * Metodo publico iterativo para inserir elemento.
 * @param x Elemento a ser inserido.
 */
void AVL::inserir(Personagem* &x) {
	inserir(x, raiz);
}

/**
*Metodo privado recursivo para inserir elemento.
*@param x Elemento a ser inserido.
*@param i No em analise.
*/
void AVL::inserir(Personagem* &x, No* &i){
   	if(i == NULL){
    	i = new No(x);
   	}else if (x->getNome().compare(i->elemento.getNome()) < 0){
    	inserir(x, i->esq);
   	}else if (x->getNome().compare(i->elemento.getNome()) > 0) {
    	inserir(x, i->dir);
   	}else {
        errx(1, "Erro ao inserir!");
   	}

	i = balancear(i);
}

No* AVL::balancear(No* &i){
	if(i != NULL){
		int fator = i->getNivel(i->dir) - i->getNivel(i->esq);

		//Se o balanceada
		if(abs(fator) <= 1){
			i->setNivel();

		//Se desbalanceada para a direita
		}else if(fator == 2){
			int fatorFilhoDir = i->getNivel(i->dir->dir) - i->getNivel(i->dir->esq);

			//Se o filho a direita tambem estiver desbalanceado
			if(fatorFilhoDir == -1){
				i->dir = rotacionarDir(i->dir);
			}//fim if
			i = rotacionarEsq(i);

		//Se desbalanceada para a esquerda
		}else if(fator == -2){
			int fatorFilhoEsq = i->getNivel(i->esq->dir) - i->getNivel(i->esq->esq);

			//Se o filho a esquerda tambem estiver desbalanceado
			if(fatorFilhoEsq == 1){
				i->esq = rotacionarEsq(i->esq);
			}
			i = rotacionarDir(i);
		}else{
			errx(1, "Erro fator de balanceamento invalido");
		}
	}

	return i;	
}

No* AVL::rotacionarDir(No* &i){
	No* noEsq = i->esq;
	No* noEsqDir = noEsq->dir;

    noEsq->dir = i;
    i->esq = noEsqDir;

    i->setNivel();
    noEsq->setNivel();

	return noEsq;
}

No* AVL::rotacionarEsq(No* &i){
	No* noDir = i->dir;
    No* noDirEsq = noDir->esq;

    noDir->esq = i;
    i->dir = noDirEsq;

    i->setNivel();
    noDir->setNivel();

	return noDir;
}

int AVL::getComp(){
	return this->comp;
}


//METODO PRINCIPAL ====================================
int main(){
	AVL* arvore = new AVL();
	string nome_pesquisa;
	char nomeArq[200];
	scanf("%s", nomeArq);
	while(nomeArq[0] != 'F' && nomeArq[1] != 'I' && nomeArq[2] != 'M'){
		Personagem* personagem = new Personagem();
		personagem->lerPersonagem(nomeArq);
		fflush(stdin);
		arvore->inserir(personagem);
        scanf("%s",  nomeArq);
	}//fim while

	clock_t comeco = clock();
	cin.ignore();
    getline(cin, nome_pesquisa);
	while(nome_pesquisa.compare("FIM") != 0){
		cout << nome_pesquisa << " ";
		bool resp = arvore->pesquisar(nome_pesquisa);
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
	FILE *arq = fopen("matrícula_avl.txt", "w");
	fprintf(arq, "651636\t%lf\t%d", total, arvore->getComp());

	fclose(arq);

	//arvore->mostrarPre();

    return 1;
}