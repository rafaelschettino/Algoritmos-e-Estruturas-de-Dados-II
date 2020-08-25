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
*Classe TP03Q07QuicksortListaDupla
*@author Rafael Schettino
*@version abr/2020
*/
public class TP03Q07QuicksortListaDupla {
    public static void main(String[] args) throws Exception{
    	MyIO.setCharset("UTF-8");
        ListaDupla lista_dupla = new ListaDupla();
        for(String nomeArq = MyIO.readLine(); nomeArq.equals("FIM") == false; nomeArq = MyIO.readLine()){
		Personagem personagem = new Personagem();
		personagem.lerPersonagem(nomeArq);
            	lista_dupla.inserirFim(personagem);
        }//fim for

	long inicio = now();
	lista_dupla.quicksort();
	//Realiza o metodo de insercao para ordenar pelo atributo nome, aqueles personagens que tem cor de cabelo igual
	lista_dupla.insercaoPorNome();
	long fim = now();
	double time = (fim-inicio)/1000.0;
	FileWriter arq = new FileWriter("matrícula_quicksort2.txt");
	PrintWriter escreverArq = new PrintWriter(arq);
	escreverArq.printf("651636"+"\t"+lista_dupla.getComp()+"\t"+lista_dupla.getMov()+"\t"+time);
		
        arq.close();        
        lista_dupla.mostrar();
    }
	
    /**
    *Retorna o timestamp atual
    *@return timestamp atual
    */
     public static long now(){
	return new Date().getTime();
     }
}

class CelulaDupla {
	public Personagem elemento;
	public CelulaDupla ant;
	public CelulaDupla prox;

	/**
	 * Construtor da classe.
	 */
	public CelulaDupla(){}

	/**
	 * Construtor da classe.
	 * @param elemento Personagem inserido na celula.
	 */
	public CelulaDupla(Personagem elemento) {
		this.elemento = elemento.clone();
		this.ant = this.prox = null;
    	}
}

class ListaDupla {
	public CelulaDupla primeiro;
	public CelulaDupla ultimo;
	private int mov = 0;	//Conta o numero de movimentacoes
	private int comp = 0;	//Conta o numero de comparacoes

	/**
	 * Construtor da classe que cria uma lista dupla sem elementos (somente no cabeca).
	 */
	public ListaDupla() {
		primeiro = new CelulaDupla();
		ultimo = primeiro;
	}

	/**
	 * Insere um elemento na primeira posicao da lista.
	 * @param x Personagem elemento a ser inserido.
	 */
	public void inserirInicio(Personagem x) {
		CelulaDupla tmp = new CelulaDupla(x);

		tmp.ant = primeiro;
		tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if(primeiro == ultimo){
			ultimo = tmp;
		}else{
			tmp.prox.ant = tmp;
		}//fim if
		tmp = null;
	}

	/**
	*Insere um elemento na ultima posicao da lista.
	*@param x Personagem elemento a ser inserido.
	*/
	public void inserirFim(Personagem x) {
		ultimo.prox = new CelulaDupla(x);
		ultimo.prox.ant = ultimo;
		ultimo = ultimo.prox;
	}

	/**
	 * Remove um elemento da primeira posicao da lista.
	 * @return resp Personagem elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Personagem removerInicio() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}//fim if

		CelulaDupla tmp = primeiro;
		primeiro = primeiro.prox;
		Personagem resp = primeiro.elemento.clone();
		tmp.prox = primeiro.ant = null;
		tmp = null;
		return resp;
	}

	/**
	 * Remove um elemento da ultima posicao da lista.
	 * @return resp Personagem elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Personagem removerFim() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}//fim if
		Personagem resp = ultimo.elemento.clone();
		ultimo = ultimo.ant;
		ultimo.prox.ant = null;
		ultimo.prox = null;
		return resp;
	}

	/**
	 * Insere um elemento em uma posicao especifica considerando que o 
	 * primeiro elemento valido esta na posicao 0.
	 * @param x Personagem elemento a ser inserido.
	 * @param pos int posicao da insercao.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public void inserir(Personagem x, int pos) throws Exception {

		int tamanho = tamanho();

		if(pos < 0 || pos > tamanho){
			throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
		} else if (pos == 0){
			inserirInicio(x);
		} else if (pos == tamanho){
			inserirFim(x);
		} else {
			// Caminhar ate a posicao anterior a insercao
			CelulaDupla i = primeiro;
			for(int j = 0; j < pos; j++, i = i.prox);

			CelulaDupla tmp = new CelulaDupla(x);
			tmp.ant = i;
			tmp.prox = i.prox;
			tmp.ant.prox = tmp.prox.ant = tmp;
			tmp = i = null;
		}
	}

	/**
	 * Remove um elemento de uma posicao especifica da lista
	 * considerando que o primeiro elemento valido esta na posicao 0.
	 * @param posicao Meio da remocao.
	 * @return resp Personagem elemento a ser removido.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public Personagem remover(int pos) throws Exception {
		Personagem resp;
		int tamanho = tamanho();

		if (primeiro == ultimo){
			throw new Exception("Erro ao remover (vazia)!");

		} else if(pos < 0 || pos >= tamanho){
			throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
		} else if (pos == 0){
			resp = removerInicio();
		} else if (pos == tamanho - 1){
			resp = removerFim();
		} else {
			// Caminhar ate a posicao anterior a insercao
			CelulaDupla i = primeiro.prox;
			for(int j = 0; j < pos; j++, i = i.prox);

			i.ant.prox = i.prox;
			i.prox.ant = i.ant;
			resp = i.elemento.clone();
			i.prox = i.ant = null;
			i = null;
		}

		return resp;
	}

	/**
	*Mostra os elementos da lista separados por espacos.
	*/
	public void mostrar() {
		for(CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
			i.elemento.imprimir();
		}//fim for
	}

	/**
	 * Mostra os elementos da lista de forma invertida 
	 * e separados por espacos.
	 */
	public void mostrarInverso() {
		System.out.print("[ ");
		for (CelulaDupla i = ultimo; i != primeiro; i = i.ant){
			System.out.print(i.elemento + " ");
		}//fim for
		System.out.println("] "); // Termina de mostrar.
	}

    /**
    *Realiza a primeira chamada da recursao
    */
    public void quicksort(){
	quicksort(0, tamanho()-1);
    }

    /**
    *Realiza a ordenacao da Lista dupla utilizando o metodo Quicksort
    */
    public void quicksort(int esq, int dir){
		int i = esq;
		int j = dir;
		CelulaDupla CelulaPivo = getPosElemento((esq+dir)/2);
		String pivo = CelulaPivo.elemento.getCorDoCabelo();
		mov++;

		while(i <= j){
			while(getPosElemento(i).elemento.getCorDoCabelo().compareTo(pivo) < 0){
				i++;
				comp++;
			}//fim while
			while(getPosElemento(j).elemento.getCorDoCabelo().compareTo(pivo) > 0){
				j--;
				comp++;
			}//fim while
			if(i <= j){
				//Swap dos elementos
				CelulaDupla tmp = new CelulaDupla();
				tmp.elemento = getPosElemento(i).elemento.clone();
				getPosElemento(i).elemento = getPosElemento(j).elemento.clone();
				getPosElemento(j).elemento = tmp.elemento.clone();
				tmp = null;
				i++;
				j--;
				mov+=3;
			}//fim if
		}//fim while

		if(esq < j) quicksort(esq, j);
		if(dir > i) quicksort(i, dir);

	}

	/**
	*Metodo de ordenacao por Insercao 
	*Ordena por nome, aqueles elementos que possuem cor de cabelo igual
	*/
	public void insercaoPorNome(){
		for(CelulaDupla i = primeiro.prox.prox; i != null; i = i.prox){
			CelulaDupla tmp = new CelulaDupla();
			tmp.elemento = i.elemento.clone();
			CelulaDupla j = i.ant;
			mov++;

			//Caso as cores do cabelo de dois elementos sejam iguais, a ordenacao entre eles ocorre por nome
			while((j != primeiro) && (j.elemento.getCorDoCabelo().compareTo(tmp.elemento.getCorDoCabelo()) == 0) && (j.elemento.getNome().compareTo(tmp.elemento.getNome())) > 0){
				j.prox.elemento = j.elemento.clone();
				j = j.ant;
				comp+=2;
				mov+=2;
			}//fim while
			j.prox.elemento = tmp.elemento.clone();
			mov++;
		}//fim for i
	}

	/**
	*Procura e retorna a Celula que se encontra em determinada posicao da lista
	*@param int pos que servira para a busca da CelulaDupla
	*@return CelulaDupla retorno, que é a Celula que se encontra na posicao recebida
	*/
	public CelulaDupla getPosElemento(int pos){
		CelulaDupla retorno; 
		CelulaDupla i = primeiro.prox;
		for(int j = 0; j < pos; j++, i = i.prox);
		retorno = i;

		return retorno;
	}

	/**
	*Retorna o numero de comparacoes realizadas pelo programa
	*/
	public int getComp(){
		return comp;
	}

	/*
	*Retorna o numero de movimentacoes realizadas pelo programa
	*/
	public int getMov(){
		return mov;
	}

	/**
	* Calcula e retorna o tamanho, em numero de elementos, da lista.
	* @return resp int tamanho
	*/
	public int tamanho() {
		int tamanho = 0; 
		for(CelulaDupla i = primeiro; i != ultimo; i = i.prox, tamanho++);
		return tamanho;
        }
}
