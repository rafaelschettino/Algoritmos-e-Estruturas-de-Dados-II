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
		}else{
			posFim = s.indexOf(depois, posInicio);
		}//fim if

		if(0 <= posInicio && posInicio < posFim && posFim < s.length()){
			resp = s.substring(posInicio, posFim);
		}//fim if

		return resp;
	}

}

/*
*Classe TP04Q01ArvoreBinaria
*@author Rafael Schettino
*@version maio/2020
*/
public class TP04Q01ArvoreBinaria {
    public static void main(String[] args) throws Exception{
        //MyIO.setCharset("UTF-8");
        ArvoreBinaria arvore = new ArvoreBinaria();
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
	    }else{
		MyIO.println("N"+((char)195)+"O");
	    }//fim if
	}//fim for
	    
	long fim = now();
	double time = (fim-inicio)/1000.0;
	FileWriter arq = new FileWriter("matrículaarvoreBinaria.txt");
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

/**
*No da arvore binaria
*@author Rafael Schettino 
*/
class No{
    public Personagem elemento; //Conteudo do no.
    public No esq, dir; //Filhos da esq e dir.

    /**
    *Construtor da classe.
    *@param elemento Conteudo do no. 
    */
    public No(Personagem elemento){
        this(elemento, null, null);
    }

    /**
    *Construtor da classe.
    *@param elemento Conteudo do no.
    *@param esq No da esqueda.
    *@param dir NO da direita. 
    */
    public No(Personagem elemento, No esq, No dir){
        this.elemento = elemento.clone();
        this.esq = esq;
        this.dir = dir;
    }
}

/**
*Arvore binaria de pesquisa
*@author Rafael Schettino
 */
class ArvoreBinaria{
    private No raiz;    //Raiz da arvore
    private int comp = 0;	//Conta o numero de comparacoes realizadas pelo prorgram

    /**
    *Construtor da classe
    */
    public ArvoreBinaria(){
        raiz = null;
    }

    /**
    *Metodo publico iterativo para pesquisar elemento.
    *@param x Elemento que sera procurado.
    *@return <code>true</code> se o elemento existir.
    *<code>false</code> em caso contrario.
    */
    public boolean pesquisar(String x){
        MyIO.print("raiz ");
        return pesquisar(x, raiz);
    }

    /**
    *Metodo privado recursivo para pesquisar elemento.
    *@param x Elemento que sera procurado.
    *@param i No em analise.
    *@return <code>true</code> se o elemento existir,
    *<code>false</code> em caso contrario.
    */
    private boolean pesquisar(String x, No i){
        boolean resp;
        if(i == null){
	    resp = false;
	    comp++;
        }else if(x.compareTo(i.elemento.getNome()) == 0){
	    resp = true;
	    comp+=2;
        }else if(x.compareTo(i.elemento.getNome()) < 0){
	    MyIO.print("esq ");
	    comp+=3;
            resp = pesquisar(x, i.esq);
        }else{
	    MyIO.print("dir ");
	    comp+=3;
            resp = pesquisar(x, i.dir);
        }

        return resp;
    }

    	/**
	*Metodo publico iterativo para exibir elementos.
	*/
	public void mostrarPre() {
		mostrarPre(raiz);
	}

	/**
	*Metodo privado recursivo para exibir elementos.
	*@param i No em analise.
	*/
	private void mostrarPre(No i) {
	    if (i != null) {
		System.out.println(i.elemento.getNome()); // Conteudo do no.
		mostrarPre(i.esq); // Elementos da esquerda.
		mostrarPre(i.dir); // Elementos da direita.
	    }//fim if
   	}
    
    	/**
	*Metodo publico iterativo para inserir elemento.
	*@param x Elemento a ser inserido.
	*@throws Exception Se o elemento existir.
	*/
	public void inserir(Personagem x) throws Exception {
	    raiz = inserir(x, raiz);
	}

	/**
	*Metodo privado recursivo para inserir elemento.
	*@param x Elemento a ser inserido.
	*@param i No em analise.
	*@return No em analise, alterado ou nao.
	*@throws Exception Se o elemento existir.
	*/
	private No inserir(Personagem x, No i) throws Exception {
	    if(i == null) {
            	i = new No(x);
            }else if(x.getNome().compareTo(i.elemento.getNome()) < 0) {
                i.esq = inserir(x, i.esq);
            }else if(x.getNome().compareTo(i.elemento.getNome()) > 0) {
                i.dir = inserir(x, i.dir);
            }else {
                throw new Exception("Erro ao inserir!");
            }//fim if

	    return i;
	 }

	/**
	 * Retorna o numero de comparacoes realizadas pelo programa
	 */
	public int getComp(){
	    return this.comp;
	}
}
