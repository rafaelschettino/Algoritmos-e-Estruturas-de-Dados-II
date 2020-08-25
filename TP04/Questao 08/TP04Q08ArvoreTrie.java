import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.security.PrivateKey;
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
*Classe TP04Q08ArvoreTrie
*@author Rafael Schettino
*@version maio/2020
*/
public class TP04Q08ArvoreTrie {
    public static void main(String[] args) throws Exception{
	MyIO.setCharset("UTF-8");
	ArvoreTrie trie = new ArvoreTrie();	//Primeira Arvore Trie
        ArvoreTrie trie2 = new ArvoreTrie();	//Segunda Arvore Trie
        ArvoreTrie resultante = new ArvoreTrie();    //Arvore resultante apos o merge das duas primeiras

        //Le os personagens e insere o nome de cada um na primeira arvore
        for(String nomeArq = MyIO.readLine(); nomeArq.equals("FIM") == false; nomeArq = MyIO.readLine()){
		Personagem personagem = new Personagem();
		personagem.lerPersonagem(nomeArq);
		trie.inserir(personagem.getNome());
        }//fim for

        //Le os personagens e insere o nome de cada um na segunda arvore
        for(String nomeArq = MyIO.readLine(); nomeArq.equals("FIM") == false; nomeArq = MyIO.readLine()){
        	Personagem personagem = new Personagem();
            	personagem.lerPersonagem(nomeArq);
            	trie2.inserir(personagem.getNome());
        }//fim for

	//Realiza a uniao das duas arvores em uma unica arvore resultante
	resultante.merge(trie);
	resultante.merge(trie2);
        //merge.mostrar();		
		
	long inicio = now();
	//Realiza a pesquisa de elementos na arvore resultante apos o merge
        for(String nome_pesquisa = MyIO.readLine(); nome_pesquisa.equals("FIM") == false; nome_pesquisa = MyIO.readLine()){
            	MyIO.print(nome_pesquisa + " ");
            	boolean resp = resultante.pesquisar(nome_pesquisa);
		if(resp){
		    MyIO.println("SIM");
		}else{
		    MyIO.println("N"+((char)195)+"O");
		}//fim if
	}//fim for  
		
	long fim = now();
	double time = (fim-inicio)/1000.0;
	FileWriter arq = new FileWriter("matrícula_arvoreTrie.txt");
	PrintWriter escreverArq = new PrintWriter(arq);
	escreverArq.printf("651636"+"\t"+time+"\t"+resultante.getComp());

	arq.close();

        //trie.mostrar();
        //trie2.mostrar();
    }
	
	/**
    	*Retorna o timestamp atual
    	*@return timestamp atual
    	*/
	public static long now(){
		return new Date().getTime();
	}
}

/**
*Classe No da Arvore Trie
*/
class No {
    public char elemento;
    public int tamanho = 255;
    public No[] prox;
    public boolean folha;
	
    /**
    *Construtor da classe
    */
    public No (){
        this(' ');
    }
 
    /**
    *Construtor da classe 
    *@param char elemento conteudo do no 
    */
    public No(char elemento){
        this.elemento = elemento;
 
        prox = new No[tamanho];
 
        for(int i = 0; i < tamanho; i++){
            prox[i] = null;
        }//fim for i
 
        folha = false;
    }
 
    public static int hash (char x){
        return (int)x;
    }
 }

/**
*Classe Arvore Trie
*@author Rafael Schettino
*@version maio/2020
*/
class ArvoreTrie {
	private No raiz;
	private int comp = 0;	//Conta o numero de comparacoes realizas pelo programa
 
	/**
	*Construtor da classe 
	*/
    	public ArvoreTrie(){
        	raiz = new No();
    	}
 
	/**
	*Metodo publico para inserir elementos na Arvore Trie
	*@param s String a ser inserida.
	*/
    	public void inserir(String s) throws Exception {
        	inserir(s, raiz, 0);
	}
	
	public void inserirMerge(String s) throws Exception{
		inserir(s, raiz, 1);
	}
	
	/**
	*Metodo privado para inserir elementos na Arvore Trie
	*/
    	private void inserir(String s, No no, int i) throws Exception {
        	//System.out.print("\nEM NO(" + no.elemento + ") (" + i + ")");
        	if(no.prox[s.charAt(i)] == null){
  	    		//System.out.print("--> criando filho(" + s.charAt(i) + ")");
      			no.prox[s.charAt(i)] = new No(s.charAt(i)); 
       			if(i == s.length() - 1){
            			//System.out.print("(folha)");
            			no.prox[s.charAt(i)].folha = true;
        		}else{
            			inserir(s, no.prox[s.charAt(i)], i + 1);
        		}//fim if 
       		}else if (no.prox[s.charAt(i)].folha == false && i < s.length() - 1){
        		inserir(s, no.prox[s.charAt(i)], i + 1); 
       		}else {
        		throw new Exception("Erro ao inserir!");
       		}//fim if 
    }
 
	/**
	*Metodo publico para pesquisar elementos na Arvore
	*/
    	public boolean pesquisar(String s) throws Exception {
      		return pesquisar(s, raiz, 0);
    	}
 
	/**
	*Metodo privado para pesquisar o nome elementos na arvore
	*/
    	private boolean pesquisar(String s, No no, int i) throws Exception {
    		boolean resp;
 
       		if(no.prox[s.charAt(i)] == null){
			resp = false;
			comp++;
       		}else if(i == s.length() - 1){
			resp = (no.prox[s.charAt(i)].folha == true);
			comp+=2;
       		}else if(i < s.length() - 1 ){
			resp = pesquisar(s, no.prox[s.charAt(i)], i + 1);
			comp+=3;
       		}else{
        		throw new Exception("Erro ao pesquisar!");
       		}
 
       		return resp;
       }
 
 
	/**
	*Metodo publico para realizar o Merge das duas arvores
	*@param arv Arvore a ser inserida na arvore resultante
	*/
    	public void merge(ArvoreTrie arv) throws Exception{
        	merge("", arv.raiz);
    	}
 
	/**
	*Metodo privado para realizar o Merge das duas arvores
	*O metodo de mostrar da Arvore Trie foi adaptado,
	*ao inves de mostrar na tela as palavras, as insere em uma nova arvore.
	*@param No no raiz da arvore que tera suas palavras inseridas.
	*/
    	private void merge(String s, No no) throws Exception{
        	if(no.folha == true){
            		//System.out.println("Palavra: " + (s + no.elemento));
            		inserirMerge(s + no.elemento);
        	}else {
            		for(int i = 0; i < no.prox.length; i++){
                		if(no.prox[i] != null){
                    			//System.out.println("ESTOU EM (" + no.elemento + ") E VOU PARA (" + no.prox[i].elemento + ")");
                			merge(s + no.elemento, no.prox[i]);
                		}//fim if
            		}//fim for i
        	}//fim if
    }

    public void mostrar() throws Exception{
        mostrar("", raiz);
    }
 
    public void mostrar(String s, No no) throws Exception{
        if(no.folha == true){
            System.out.println("Palavra:" + (s + no.elemento));
            //inserir(s+ no.elemento);
        }else {
            for(int i = 0; i < no.prox.length; i++){
                if(no.prox[i] != null){
                    //System.out.println("ESTOU EM (" + no.elemento + ") E VOU PARA (" + no.prox[i].elemento + ")");
                    mostrar(s + no.elemento, no.prox[i]);
                }//fim if
            }//fim for i
        }//fim if
	}
	
	/**
    	*Retorna o numero de comparacoes realizadas pelo programa
    	*/
	public int getComp(){
		return this.comp;
	}
    
}
 
