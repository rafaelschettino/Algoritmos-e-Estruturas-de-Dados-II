import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.lang.Math;

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
		}

		if(0 <= posInicio && posInicio < posFim && posFim < s.length()){
			resp = s.substring(posInicio, posFim);
		}

		return resp;
	}

}

/*
*Classe TP03Q03FilaAlocacaoFlexivel
*@author Rafael Schettino
*@version abr/2020
*/
public class TP03Q03FilaAlocacaoFlexivel {
    public static void main(String[] args) throws Exception{
        MyIO.setCharset("UTF-8");
        Fila fila = new Fila();
        for(String nomeArq = MyIO.readLine(); nomeArq.equals("FIM") == false; nomeArq = MyIO.readLine()){
			Personagem personagem = new Personagem();
			personagem.lerPersonagem(nomeArq);
            fila.inserir(personagem);
            fila.calculaMedia();
        }

        int n = MyIO.readInt(); //Quantidade de registros a serem inseridos/removidos
        
        for(int i = 0; i < n; i++){
            String comando = MyIO.readLine();
            Personagem personagem = new Personagem();
            String[] aux = comando.split(" ");
            if(aux.length == 2){
                personagem.lerPersonagem(aux[1]);
                fila.inserir(personagem);
                fila.calculaMedia();
            }else if(aux.length == 1){
                personagem = fila.remover();
                MyIO.println("(R) "+personagem.getNome());
            }//fim if
        }//fim for i

        fila.mostrar();
    }
}

class Celula{
    public Personagem elemento;
    public Celula prox;

    /**
    *Construtor da classe
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

class Fila{
    private Celula primeiro;
    private Celula ultimo;
    private int p, u;   //Utilizados para gerenciar a quantidade maxima de elementos na fila.
    private int n = 0;  //Controla a quantidade de elementos na fila e auxilia no calculo da media. 

    /**
    *Construtor da classe que cria uma fila sem elementos(somente no cabeca)
    */
    public Fila(){
        primeiro = new Celula();
        ultimo = primeiro;
        p = u = 0;
        n = 0;
    } 

    /**
    *Insere um elemento na fila(politica FIFO)
    *@param x Personagem elemento a inserir. 
    */
    public void inserir(Personagem x) throws Exception{
        //Validar insercao
        if(((u + 1) % 6) == p){
            //Caso a fila esteja cheia, ao inves de acusar erro, realizamos uma remocao
            Personagem removido = remover();
        }//fim if

        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
        u = (u+1) % 6;
        n++;
    }

    /**
    *Remove elemento da fila(politica FIFO).
    *@return Elemento removido.
    *@throws Exception se a fila nao tiver elementos. 
    */
    public Personagem remover() throws Exception{
        if(primeiro == ultimo){
            throw new Exception("Erro ao remover! Fila vazia");
        }//fim if

        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        Personagem resp = primeiro.elemento.clone();
        tmp.prox = null;
        tmp = null;
        p = (p+1) % 6;
        n--;

        return resp;
    }

    /**
    *Calcula a media das alturas dos componentes da fila
    *@return int resp contendo a media arredondada das alturas 
    */
    public void calculaMedia(){
        double soma = 0;
        double media;
        for(Celula i = primeiro.prox; i != null; i = i.prox){
            soma += i.elemento.getAltura();
        }//fim for 
        media = (soma/n);
        
        MyIO.println(String.valueOf(Math.round(media)).replace(".0", ""));     
    }

    /**
    *Mostra os elementos da fila 
    */
    public void mostrar(){
        for(Celula i = primeiro.prox; i != null; i = i.prox){
            i.elemento.imprimir();
        }//fim for
    }
}