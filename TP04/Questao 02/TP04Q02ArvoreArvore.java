import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;

/*
*Classe Personagem
*@author Rafael Schettino
*@version mar/2020
*/
class Personagem{
	private String nome;
	private int altura;
	private double peso;
	private String corDoCabelo;
	private String corDaPele;
	private String corDosOlhos;
	private String anoNascimento;
	private String genero;
	private String homeworld;

	/*
	*Construtor da classe
	*/
	public Personagem(){
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

	/*
	*Construtor da classe
	*@param nome, altura, peso, corDoCabelo, corDaPele, corDosOlhos, anoNascimento, genero, homeworld
	*/
	public Personagem(String nome, int altura, double peso, String corDoCabelo, String corDaPele, String corDosOlhos, String anoNascimento, String genero, String homeworld){
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

	public void setNome(String nome){
		this.nome = nome;
	}
	public void setAltura(int altura){
		this.altura = altura;
	}
	public void setPeso(double peso){
		this.peso = peso;
	}
	public void setCorDoCabelo(String corDoCabelo){
		this.corDoCabelo = corDoCabelo;
	}
	public void setCorDaPele(String corDaPele){
		this.corDaPele = corDaPele;
	}
	public void setCorDosOlhos(String corDosOlhos){
		this.corDosOlhos = corDosOlhos;
	}
	public void setAnoNascimento(String anoNascimento){
		this.anoNascimento = anoNascimento;
	}
	public void setGenero(String genero){
		this.genero = genero;
	}
	public void setHomeWorld(String homeworld){
		this.homeworld = homeworld;
	}

	public String getNome(){
		return this.nome;
	}
	public int getAltura(){
		return this.altura;
	}
	public double getPeso(){
		return this.peso;
	}
	public String getCorDoCabelo(){
		return this.corDoCabelo;
	}
	public String getCorDaPele(){
		return this.corDaPele;
	}
	public String getCorDosOlhos(){
		return this.corDosOlhos;
	}
	public String getAnoNascimento(){
		return this.anoNascimento;
	}
	public String getGenero(){
		return this.genero;
	}
	public String getHomeWorld(){
		return this.homeworld;
	}

	/*
	*Copia os atributos da classe para um objeto.
	*/
	public Personagem clone(){
		Personagem resp = new Personagem();
		resp.nome = this.nome;
		resp.altura = this.altura;
		resp.peso = this.peso;
		resp.corDoCabelo = this.corDoCabelo;
		resp.corDaPele = this.corDaPele;
		resp.corDosOlhos = this.corDosOlhos;
		resp.anoNascimento = this.anoNascimento;
		resp.genero = this.genero;
		resp.homeworld = this.homeworld;
		return resp;
	}

	/*
	*Imprime os atributos da classe Personagem
	*/
	public void imprimir(){
		MyIO.println(" ## " + this.nome + " ## " + 
					this.altura + " ## " + 
					String.valueOf(this.peso).replace(".0", "") + " ## " + 
					this.corDoCabelo + " ## " +
					this.corDaPele + " ## " + 
					this.corDosOlhos + " ## " +
					this.anoNascimento + " ## " +
					this.genero + " ## " +
					this.homeworld + " ## ");
	}

	/*
	*Le o arquivo e seta os atributos da classe.
    	*@param String nomeArq nome do arquivo a ser lido
    	*/
	public void lerPersonagem(String nomeArq) throws Exception{
		FileReader file = new FileReader(nomeArq);
        	BufferedReader br = new BufferedReader(file);
		String linha = br.readLine();
		
		//Definir atributo nome
		setNome(getSubstringEntre(linha, "'name': '", "', 'height"));

		//Definir atributo altura
		String aux_altura = getSubstringEntre(linha, "height': '", "', 'mass");
		if(!aux_altura.equals("unknown")){
			setAltura(Integer.parseInt(aux_altura));
		}//fim if

		//Definir atributo peso
		String aux_peso = (getSubstringEntre(linha, "mass': '", "', 'hair_color"));
		if(aux_peso.contains(",")){
			aux_peso = aux_peso.replace(",", "");
		}//fim if
		
		if(!aux_peso.equals("unknown")){
			setPeso(Double.parseDouble(aux_peso));
		}//fim if

		//Definir atributo cor do cabelo
		setCorDoCabelo(getSubstringEntre(linha, "hair_color': '", "', 'skin_color"));

		//Definir atributo cor da pele
		setCorDaPele(getSubstringEntre(linha, "skin_color': '", "', 'eye_color"));

		//Definir atributo cor dos olhos
		setCorDosOlhos(getSubstringEntre(linha, "eye_color': '", "', 'birth_year"));

		//Definir atributo ano de nascimento
		setAnoNascimento(getSubstringEntre(linha, "birth_year': '", "', 'gender"));

		//Definir atributo genero
		setGenero(getSubstringEntre(linha, "gender': '", "', 'homeworld"));

		//Definir atributo homeworld
		setHomeWorld(getSubstringEntre(linha, "homeworld': '", "', 'films"));

		//MyIO.println(linha);
	}

	/*
	*Pega uma string entre duas
	*/
	public String getSubstringEntre(String s, String antes, String depois){
		String resp = "";
		int posInicio , posFim;

		posInicio = s.indexOf(antes) + antes.length();

		if(antes.compareTo(depois) != 0){
			posFim = s.indexOf(depois);
		} else {
			posFim = s.indexOf(depois, posInicio);
		}//fim if

		if(0 <= posInicio && posInicio < posFim && posFim < s.length()){
			resp = s.substring(posInicio, posFim);
		}//fim if

		return resp;
	}

}

/*
*Classe TP04Q02ArvoreArvore
*@author Rafael Schettino
*@version maio/2020
*/
public class TP04Q02ArvoreArvore {
    public static void main(String[] args) throws Exception{
		MyIO.setCharset("UTF-8");
		ArvoreArvore arvore = new ArvoreArvore();
		for(String nomeArq = MyIO.readLine(); nomeArq.equals("FIM") == false; nomeArq = MyIO.readLine()){
			Personagem personagem = new Personagem();
			personagem.lerPersonagem(nomeArq);
			arvore.inserir(personagem);
		}//fim for
		
		long inicio = now();
		for(String nome_pesquisa = MyIO.readLine(); nome_pesquisa.equals("FIM") == false; nome_pesquisa = MyIO.readLine()){
            		MyIO.print(nome_pesquisa + " ");
            		boolean resp = arvore.pesquisar(nome_pesquisa);
			if(resp){
			    MyIO.println("SIM");
			}
			else{
			    MyIO.println("N"+((char)195)+"O");
			}//fim if
		}//fim for
		long fim = now();
		double time = (fim-inicio)/1000.0;
		FileWriter arq = new FileWriter("matrículaarvoreArvore.txt");
		PrintWriter escreverArq = new PrintWriter(arq);
		escreverArq.printf("651636"+"\t"+time+"\t"+arvore.getComp());

		arq.close();

		//arvore.mostrarPre(); 
	}
	
	/**
    	*Retorna o timestamp atual
    	*@return timestamp atual
    	*/
	public static long now(){
		return new Date().getTime();
	}
}

class No {
    	public int elemento;   //Conteudo do no.
	public No esq;  //No da esquerda.
	public No dir;  //No da direita.
    	public No2 outro;
	
    	/**
	*Construtor da classe.
	*@param elemento Conteudo do no.
	*/
	No(int elemento) {
		this.elemento = elemento;
		this.esq = this.dir = null;
        this.outro = null;
	}

	/**
	*Construtor da classe.
	*@param elemento Conteudo do no.
	*@param esq No da esquerda.
	*@param dir No da direita.
	*/
	No(int elemento, No esq, No dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
        this.outro = null;
	}
}

class No2{
    	public Personagem elemento; //Conteudo do no.
	public No2 esq; //No da esquerda.
    	public No2 dir; //No da direita.
    
    	/**
	*Construtor da classe.
	*@param elemento Conteudo do no.
	*/
	No2(Personagem elemento){
		this.elemento = elemento.clone();
		this.esq = this.dir = null;
    }
    
    	/**
	*Construtor da classe.
	*@param elemento Conteudo do no.
	*@param esq No2 da esquerda.
	*@param dir No2 da direita.
	*/
	No2(Personagem elemento, No2 esq, No2 dir) {
		this.elemento = elemento.clone();
		this.esq = esq;
		this.dir = dir;
	}
}

/**
*Arvore de arvore
*@author Rafael Schettino
*/
class ArvoreArvore{
	private No raiz;    //Raiz da arvore.
	private int comp = 0;	//Conta o numero de comparacoes realizadas pelo programa

    /**
    *Construtor da classe.
    */
    public ArvoreArvore() throws Exception{
		raiz = null;
		inserirInteiro(7);
		inserirInteiro(3);
		inserirInteiro(11);
		inserirInteiro(1);
		inserirInteiro(5);
		inserirInteiro(9);
		inserirInteiro(12);
		inserirInteiro(0);
		inserirInteiro(2);
		inserirInteiro(4);
		inserirInteiro(6);
		inserirInteiro(8);
		inserirInteiro(10);
		inserirInteiro(13);
		inserirInteiro(14);
	}
	
	/**
	*Metodo publico para inserir inteiro na primeira arvore
	*@param int x Inteiro a ser inserido.
	@throws Exception se o inteiro existir.
	*/
	public void inserirInteiro(int x) throws Exception{
		raiz = inserirInteiro(x, raiz);
	}

	/**
	*Metodo privado recursivo para inserir inteiro na primeira arvore.
	*@param x Inteiro a ser inserido.
	*@param i No em analise.
	*@return No em analise, alterado ou nao.
	*@throws Exception Se o inteiro existir.
	*/
	private No inserirInteiro(int x, No i) throws Exception{
		if(i == null){
			i = new No(x);
		}else if(x < i.elemento){
			i.esq = inserirInteiro(x, i.esq);
		}else if(x > i.elemento){
			i.dir = inserirInteiro(x, i.dir);
		}else{
			throw new Exception("Erro ao inserir!");
		}

		return i;
	}

	/**
	*Metodo publico para inserir Personagem
	*@param Personagem x Elemento a ser inserido.
	*@throws Exception se o inteiro existir.
	*/
	public void inserir(Personagem x) throws Exception{
		inserir(x, raiz);
	}

	/**
	*Metodo privado recursivo para inserir Personagem.
	*@param Personagem x Elemento a ser inserido.
	*@param i No em analise.
	*@return No em analise, alterado ou nao.
	*@throws Exception Se o inteiro existir.
	*/
	private No inserir(Personagem x, No i) throws Exception{
		if(i == null){
			throw new Exception("Erro ao inserir!");
		}else if(i.elemento > (x.getAltura() % 15)){
			i.esq = inserir(x, i.esq);
		}else if(i.elemento < (x.getAltura() % 15)){
			i.dir = inserir(x, i.dir);
		}else{
			i.outro = inserirSegundaArvore(x, i.outro);
		}

		return i;
	}

	/**
	*Metodo privado recursivo para inserir Personagem na segunda arvore.
	*@param x Elemento a ser inserido.
	*@param no a ser analisado.
	*@return No2 em analise, alterado ou nao.
	*@throws Exception se o elemento existir.
	*/
	private No2 inserirSegundaArvore(Personagem x, No2 no) throws Exception{
		if(no == null){
			no = new No2(x);
		}else if(no.elemento.getNome().compareTo(x.getNome()) > 0){
			no.esq = inserirSegundaArvore(x, no.esq);
		}else if(no.elemento.getNome().compareTo(x.getNome()) < 0){
			no.dir = inserirSegundaArvore(x, no.dir);
		}else{
			throw new Exception("Erro a inserir!");
		}

		return no;
	}

	/**
    	*Metodo publico iterativo para pesquisar elemento.
    	*@param String x Nome do elemento que sera procurado.
    	*@return <code>true</code> se o elemento existir.
    	*<code>false</code> em caso contrario.
    	*/
	public boolean pesquisar(String x){
		MyIO.print("raiz ");
		return pesquisar(x, raiz);
	}

	/**
	*Metodo privado recursivo para pesquisar elemento.
	*Pesquisa em cada Segunda Arvore de cada no.
	*@param String x Nome do elemento que sera procurado.
	*@param i No em analise.
	*@return <code>true</code> se o elemento existir,
	*<code>false</code> em caso contrario.
    	*/
	private boolean pesquisar(String x, No i){
		boolean resp;
		if(i != null){
			resp = pesquisarSegundaArvore(x, i.outro); 
			if(resp == false){
				MyIO.print("esq ");
				comp++;
				resp = pesquisar(x, i.esq);
			}//fim if
			if(resp == false){
				MyIO.print("dir ");
				comp++;
				resp = pesquisar(x, i.dir);
			}//fim if
		}else{
			resp = false;
		}

		return resp;
	}

	/**
	 * Realiza a pesquisa de Personagem na 
	 * Arvore de Personagens de cada no da primeira arvore.
	 */
	private boolean pesquisarSegundaArvore(String x, No2 no){
		boolean resp;
		if(no != null){
			if(x.compareTo(no.elemento.getNome()) == 0){
				resp = true;
				comp++;
			}else{
				MyIO.print("ESQ ");
				resp = pesquisarSegundaArvore(x, no.esq);
			}
			if(resp == false){
				MyIO.print("DIR ");
				resp = pesquisarSegundaArvore(x, no.dir);
			}
		}else{
			resp = false;
		}

		return resp;
	}

	public void mostrarPre(){
		mostrarPre(raiz);
	}

	private void mostrarPre(No i){
		if(i != null){
			MyIO.print(i.elemento + " ");
			mostrarPre(i.esq);
			mostrarPre(i.dir);
		}//fim if
	}

	/**
	 * Retorna o numero de comparacoes realizadas pelo programa
	 */
	public int getComp(){
		return this.comp;
	}

}
