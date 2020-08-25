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
		}//fim if

		if(0 <= posInicio && posInicio < posFim && posFim < s.length()){
			resp = s.substring(posInicio, posFim);
		}//fim if

		return resp;
	}

}

/**
*Classe TP02Q13Heapsort
*@author Rafael Schettino
*@version mar/2020
*/
public class TP02Q13Heapsort{
    public static void main(String[] args) throws Exception{
        MyIO.setCharset("UTF-8");
	Lista lista = new Lista(1000);
	for(String nomeArq = MyIO.readLine(); nomeArq.equals("FIM") == false; nomeArq = MyIO.readLine()){
		Personagem personagem = new Personagem();
		personagem.lerPersonagem(nomeArq);
		lista.inserirFim(personagem);
		//personagem.imprimir();
        }//fim for
        
        long inicio = now();
        lista.heapsort();
        //Realiza o metodo de insercao para ordenar pelo atributo nome, aqueles personagens que tem alturas iguais
        lista.insercaoPorNome();
        long fim = now();
        double time = (fim-inicio)/1000.0;
	FileWriter arq = new FileWriter("matrícula_heapsort.txt");
	PrintWriter escreverArq = new PrintWriter(arq);
	escreverArq.printf("651636"+"\t"+lista.getComp()+"\t"+lista.getMov()+"\t"+time);

        arq.close();
        lista.mostrar();
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

    public void constroi(int tamHeap){
        for(int i = tamHeap; i > 1 && (array[i].getAltura() > array[i/2].getAltura() || array[i].getAltura() == array[i/2].getAltura()); i /= 2){
            swap(i, i/2);
            comp++;
        }//fim for
    }

    public void reconstroi(int tamHeap){ 
        int i = 1, filho;
        while(i <= (tamHeap/2)){
            if(array[2*i].getAltura() > array[2*i+1].getAltura() || 2*i == tamHeap){
                filho = 2*i;
                comp++;
            }else{
                filho = 2*i + 1;
            }//fim if
        
            if(array[i].getAltura() < array[filho].getAltura()){
                swap(i, filho);
                i = filho;
                comp++;
            }else{
                i = tamHeap;
            }//fim if
        }//fim while
    }

    /**
    *Algoritmo de ordenacao Heapsort.
    */
    public void heapsort() {
        //Alterar o vetor ignorando a posicao zero
        Personagem[] tmp = new Personagem[n+1];
        for(int i = 0; i < n; i++){
            tmp[i+1] = array[i].clone();
            mov++;
        }//fim for i
        array = tmp;
       
        //Contrucao do heap
        for(int tamHeap = 2; tamHeap <= n; tamHeap++){
            constroi(tamHeap);
        }//fim for
       
        //Ordenacao propriamente dita
        int tamHeap = n;
        while(tamHeap > 1){
            swap(1, tamHeap--);
            reconstroi(tamHeap);
        }//fim while
       
        //Alterar o vetor para voltar a posicao zero
        tmp = array;
        array = new Personagem[n];
        for(int i = 0; i < n; i++){
            array[i] = tmp[i+1].clone();
            mov++;
        }//fim for i
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

            	//Caso as alturas de dois elementos sejam iguais, a ordenacao entre eles ocorre por nome
           	while((j >= 0) && (array[j].getAltura() == tmp.getAltura()) && (array[j].getNome().compareTo(tmp.getNome()) > 0)){
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
    public void mostrar(){
        for(int i = 0; i < n; i++){
		//MyIO.print("["+i+"] ");
		array[i].imprimir();
        }//fim for i
    }
}
