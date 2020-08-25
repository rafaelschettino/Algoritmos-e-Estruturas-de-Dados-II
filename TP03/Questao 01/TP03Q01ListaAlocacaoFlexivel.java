import java.io.*;
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
*Classe TP03Q01ListaAlocacaoFlexivel
*@author Rafael Schettino
*@version abr/2020
*/
public class TP03Q01ListaAlocacaoFlexivel{
    public static void main(String[] args) throws Exception{
        MyIO.setCharset("UTF-8");
        Lista lista = new Lista();
        for(String nomeArq = MyIO.readLine(); nomeArq.equals("FIM") == false; nomeArq = MyIO.readLine()){
            Personagem personagem = new Personagem();
            personagem.lerPersonagem(nomeArq);
            lista.inserirFim(personagem);
        }//fim for

        int n = MyIO.readInt();
	for(int i = 0; i < n; i++){
		String comando = MyIO.readLine();
		Personagem personagem = new Personagem();
		String[] aux = comando.split(" ");
		if(aux.length >= 2){
			interpretar(aux, lista, personagem);
		}else{
			interpretar(aux[0], lista);
		}//fim if
	}//fim for i
	lista.mostrar();
    }

    	/**
	*Verifica os comandos do arquivo lido e chama o método para cada ação
	*@param String[] aux Array de Strings que contem a linha de comandos a serem realizados na lista
	*@param Lista lista
	*@param Personagem personagem a ser inserido ou removido.
	*/
	public static void interpretar(String[] aux, Lista lista, Personagem personagem) throws Exception{
		if(aux[0].contains("I*")){
			int pos = Integer.parseInt(aux[1]);
			personagem.lerPersonagem(aux[2]);
			lista.inserir(personagem, pos);
		}else if(aux[0].contains("II")){
			personagem.lerPersonagem(aux[1]);
			lista.inserirInicio(personagem);
		}else if(aux[0].contains("IF")){
			personagem.lerPersonagem(aux[1]);
			lista.inserirFim(personagem);
		}else if(aux[0].contains("R*")){
			int pos = Integer.parseInt(aux[1]);
			Personagem removido = lista.remover(pos);
			MyIO.println("(R) "+removido.getNome());
		}//fim if
    }
    
    	/**
	*Verifica os comandos do arquivo lido e chama o método para cada ação
	*@param String acao nesta String tem a ação a ser lida
	*@param Lista lista
	*/
	public static void interpretar(String acao, Lista lista) throws Exception{
		if(acao.contains("RI")){
			Personagem removido = lista.removerInicio();
			MyIO.println("(R) "+removido.getNome());
		}//fim if
		if(acao.contains("RF")){
			Personagem removido = lista.removerFim();
			MyIO.println("(R) "+removido.getNome());
		}//fim if
	}	
}


class Celula{
    public Personagem elemento; //Elemento inserido na celula
    public Celula prox; //Aponta a celula prox.

    /*
    *Construtor da classe.
    */
    public Celula(){}

    /**
    *Construtor da classe.
    *@param elemento Personagem inserido na celula.
    */
    public Celula(Personagem elemento){
        this.elemento = elemento.clone();
        this.prox = null;
    }
}

class Lista{
    private Celula primeiro;
    private Celula ultimo;

    /*
    *Construtor da classe que cria uma lista sem elementos(somento no cabeca)
    */
    public Lista(){
        primeiro = new Celula();
        ultimo = primeiro;
    }

    /**
    *Insere um elemento na primeira posicao da lista.
    *@param x Personagem elemento a ser inserido. 
    */
    public void inserirInicio(Personagem x){
        Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if(primeiro == ultimo){
            ultimo = tmp;
        }//fim if
        tmp = null;
    }

    /**
    *Insere um elemento na ultima posicao da lista.
    *@param x Personagem elemento a ser inserido. 
    */
    public void inserirFim(Personagem x){
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    /**
    *Remove um elemento da primeira posicao da lista.
    *@return resp Personagem elemento a ser removido.
    *@throws Exception se a lista nao contiver elementos. 
    */
    public Personagem removerInicio() throws Exception{
        if(primeiro == ultimo){
            throw new Exception("Erro ao remover! Lista vazia");
        }//fim if

        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        Personagem resp = primeiro.elemento;
        tmp.prox = null;
        tmp = null;
        return resp;
    }

    /**
    *Remove um elemento da ultima posicao da lista.
    *@return resp Personagem elemento a ser removido.
    *@throws Exception se a lista nao contiver elementos. 
    */
    public Personagem removerFim() throws Exception{
        if(primeiro == ultimo){
            throw new Exception("Erro ao remover! Lista vazia");
        }

        //Caminhar ate a penultima celula.
        Celula i;
        for(i = primeiro; i.prox != ultimo; i = i.prox);

        Personagem resp = ultimo.elemento;
        ultimo = i;
        i = ultimo.prox = null;

        return resp;
    }

    /**
    *Insere um elemento em uma posicao especifica considerando que o
    *primeiro elemento valido esta na posicao 0. 
    *@param x Personagem elemento a ser inserido.
    *@param pos int posicao de insercao.
    *@throws Exception se <code>posicao/</code>invalida.
    */
    public void inserir(Personagem x, int pos) throws Exception{
        int tamanho = tamanho();

        if(pos < 0 || pos > tamanho){
            throw new Exception("Erro ao inserir posicao (posicao " + pos + " / "+ tamanho + "invalida!");
        }else if(pos == 0){
            inserirInicio(x);
        }else if(pos == tamanho){
            inserirFim(x);
        }else{
            //Caminhar ate a posicao anterior a insercao
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);

            Celula tmp = new Celula(x);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
        }//fim if
    }

    /**
    *Remove um elemento de uma posicao especifica da lista
    *considerando que o primeiro elemento valido esta na posicao 0.
    *@param posicao Meio da remocao.
    *@return resp Personagem elemento a ser removido.
    @throws Exception se <code>posicao</code> invalida.
    */
    public Personagem remover(int pos) throws Exception{
        Personagem resp;
        int tamanho = tamanho();

        if(primeiro == ultimo){
            throw new Exception("Erro ao remover! Lista vaiza");
        }else if(pos < 0 || pos >= tamanho){
            throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
        }else if(pos == 0){
            resp = removerInicio();
        }else if(pos == tamanho - 1){
            resp = removerFim();
        }else{
            //Caminhar ate a posicao anterior a insercao
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);

            Celula tmp = i.prox;
            resp = tmp.elemento;
            i.prox = tmp.prox;
            tmp.prox = null;
            i = tmp = null;
        }//fim if

        return resp;
    }

    /*
    *Mostra os elementos da lista separados por espacos.
    */
    public void mostrar(){
        int j = 0;
        for(Celula i = primeiro.prox; i != null; i = i.prox, j++){
            MyIO.print("["+j+"] ");
            i.elemento.imprimir();
        }//fim for
    }

    /**
    *Procura um elemento e retorna se ele existe.
    *@param x elemento a pesquisar.
    *@return <code>true</code> se o elemento existir,
    *<code>false</code> em caso contrario. 
    */
    public boolean pesquisar(Personagem x){
        boolean resp = false;
        for(Celula i = primeiro.prox; i != null; i = i.prox){
            if(i.elemento == x){
                resp = true;
                i = ultimo;
            }//fim if
        }//fim for

        return resp;
    }

    /**
    *Calcula e retorna o tamanho, em numero de elementos, da lista.
    *@return resp int tamanho 
    */
    public int tamanho(){
        int tamanho = 0;
        for(Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
        return tamanho;
    }

}
