import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;

/**
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

	/**
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

	/**
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

	/**
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
		}

		if(0 <= posInicio && posInicio < posFim && posFim < s.length()){
			resp = s.substring(posInicio, posFim);
		}

		return resp;
	}

}

/**
*Classe TP02Q22QuicksortParcial
*@author Rafael Schettino
*@version mar/2020
*/
public class TP02Q22QuicksortParcial{
    public static void main(String[] args) throws Exception{
        MyIO.setCharset("UTF-8");
		Lista lista = new Lista(1000);
		for(String nomeArq = MyIO.readLine(); nomeArq.equals("FIM") == false; nomeArq = MyIO.readLine()){
			Personagem personagem = new Personagem();
			personagem.lerPersonagem(nomeArq);
			lista.inserirFim(personagem);
			//personagem.imprimir();
        }
        
        int k = 10;
        long inicio = now();
        lista.quicksortParcial();
        //Realiza o metodo de insercao para ordenar pelo atributo nome, aqueles personagens que tem cor de cabelo igual
        lista.insercaoPorNome();
        long fim = now();
        double time = (fim-inicio)/1000.0;
		FileWriter arq = new FileWriter("matrícula_quicksortParcial.txt");
		PrintWriter escreverArq = new PrintWriter(arq);
		escreverArq.printf("651636"+"\t"+lista.getComp()+"\t"+lista.getMov()+"\t"+time);

        arq.close();
        lista.mostrar(k);
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
*Classe Lista
*@author Rafael Schettino
*@version mar/2020
*/
class Lista{
    private Personagem[] array;
    private int n;
    private int comp = 0;   //Conta o numero de comparacoes realizadas pelo programa
    private int mov = 0;    //Conta o numero de movimentacoes realizadas pelo programa
    //private int k = 10;     //Utilizado na ordenacao parcial da lista

    /*
    *Construtor da classe.
    */
    public Lista(){
        this(6);
    }

    /**
    *Construtor da classe.
    *@param int tamanho Tamanho da lista.
    */
    public Lista(int tamanho){
        array = new Personagem[tamanho];
        n = 0;
    }

    /**
    *Insere um elemento na primeria posicao da lista e move os demais
    *elementos para o fim da lista.
    *@param x Personagem elemento a ser inserido.
    *@throws Exception se a lista estiver cheia.
    */
    public void inserirInicio(Personagem x) throws Exception{
        //validar insercao
        if(n >= array.length){
            throw new Exception("Erro ao inserir! Lista cheia");
        }//fim if

        //levar elementos para o fim do array
        for(int i = n; i > 0; i--){
            array[i] = array[i-1];
        }//fim for i

        array[0] = x;
        n++;
    }

    /**
    *Insere um elemento na ultima posicao da lista
    *@param x Personagem elemento a ser inserido
    *@throws Exception se a lista estiver cheia
    */
    public void inserirFim(Personagem x) throws Exception{
        //validar insercao
        if(n >= array.length){
            throw new Exception("Erro ao inserir! Lista cheia");
        }//fim if

        array[n] = x;
        n++;
    }

    /**
    *Insere um elemento em uma posicao especifica e move os demais
    *elementos para o fim da lista
    *@param x Personagem elemento a ser inserido
    *@param pos Posicao de insercao
    *@throws Exception se a lista estiver cheia ou a posicao invalida
    */
    public void inserir(Personagem x, int pos) throws Exception{
        //validar insercao
        if(n >= array.length || pos < 0 || pos > n){
            throw new Exception("Erro ao inserir!");
        }//fim if

        //levar elementos para o fim do array
        for(int i = n; i > pos; i--){
            array[i] = array[i-1];
        }//fim for i

        array[pos] = x;
        n++;
    }

    /**
    *Remove um elemento da primeira posicao da lista e movimenta
    *os demais elementos para o inicio da mesma
    *@return Personagem resp elemento a ser removido
    *@throws Exception se a lista estiver vazia
    */
    public Personagem removerInicio() throws Exception{
        //validar remocao
        if(n == 0){
            throw new Exception("Erro ao remover! Lista vazia");
        }//fim if

        Personagem resp = array[0];
        n--;

        for(int i = 0; i < n; i++){
            array[i] = array[i+1];
        }//fim for i

        return resp;
    }

    /**
    *Remove um elemento da ultima posicao da lista.
    *@return resp Personagem elemento a ser removido.
    *@throws Exception Se a lista estiver vazia.
    */
    public Personagem removerFim() throws Exception{
        //validar remocao
        if(n == 0){
            throw new Exception("Erro ao remover! Lista vazia");
        }//fim if

        return array[--n].clone();
    }

    /**
    *Remove um elemento de uma posicao especifica da lista e
    *movimenta os demais elementos para o inicio da mesa
    *@param pos Posicao de remocao
    *@return resp Personagem elemento a ser removido
    *@throws Exception se a lista estiver vazia ou a posicao for invalida
    */
    public Personagem remover(int pos) throws Exception{
        //validar remocao
        if(n == 0 || pos < 0 || pos >= n){
            throw new Exception("Erro ao remover!");
        }//fim if

        Personagem resp = array[pos];
        n--;

        for(int i = pos; i < n; i++){
            array[i] = array[i+1];
        }//fim for i

        return resp;
    }

    /*
    *Realiza a primeira chamada da recursao
    */
    void quicksortParcial(){
        quicksortParcial(0, n-1, 10);
    }

    /*
    *Metodo de ordenacao Quicksort PARCIAL
    */
    void quicksortParcial(int esq, int dir, int k){
        int i = esq;
        int j = dir;
        Personagem pivo = array[(esq+dir)/2].clone();
        mov++;
        while(i <= j){
            while(array[i].getCorDoCabelo().compareTo(pivo.getCorDoCabelo()) < 0){
                i++;
                comp++;
            }//fim while
            while(array[j].getCorDoCabelo().compareTo(pivo.getCorDoCabelo()) > 0){
                j--;
                comp++;
            }//fim while
            if(i <= j){
                swap(i, j);
                i++;
                j--;
                mov+=3;
            }//fim if
        }//fim while
        
        if((j-esq) >= (k-1)){
            if(esq < j){ quicksortParcial(esq, j, k); return;}
        }//fim if

        if(esq < j){
            quicksortParcial(esq, j, k);
        }//fim if

        if(i < dir){
            quicksortParcial(i, dir, k);
        }//fim if
    }

    /**
    *Troca o conteudo de duas posicoes do array
    *@param i int primeira posicao
    *@param j int segunda posicao
    */
    public void swap(int i, int j) {
        Personagem temp = array[i].clone();
        array[i] = array[j].clone();
        array[j] = temp.clone();
        mov += 3;
    }

    /**
    *Metodo de ordenacao por Insercao 
    */
    public void insercaoPorNome(){
        for(int i = 1; i < n; i++) {
			Personagem tmp = array[i].clone();
			mov++;
            int j = i - 1;

            //Caso as cores de cabelo de dois elementos sejam iguais, a ordenacao entre eles ocorre por nome
            while((j >= 0) && (array[j].getCorDoCabelo().compareTo(tmp.getCorDoCabelo()) == 0) && (array[j].getNome().compareTo(tmp.getNome()) > 0)){
				array[j + 1] = array[j].clone();
				comp++;
                j--;
            }//fim while
			array[j + 1] = tmp;
			mov++;
        }//fim for i
	}

    /*
    *Retorna o numero de comparacoes realizadas pelo programa
    */
    int getComp(){
        return comp;
    }

    /*
    *Retorna o numero de movimentacoes realizadas pelo programa
    */
    int getMov(){
        return mov;
    }

    /**
    *Mostra os elementos da lista separados por espacos
    */
    public void mostrar(int k){
        for(int i = 0; i < k; i++){
			//MyIO.print("["+i+"] ");
			array[i].imprimir();
        }//fim for i
    }
}
