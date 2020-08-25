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
*Classe TP04Q05HashDiretaReserva
*@author Rafael Schettino
*@version maio/2020
*/
public class TP04Q05HashDiretaReserva {
    public static void main(String[] args) throws Exception{
        MyIO.setCharset("UTF-8");
        Hash tabela = new Hash(21,9);	//Cria uma hash com tamanho de tabela 21 e area de reserva de tamanho 9.
        for(String nomeArq = MyIO.readLine(); nomeArq.equals("FIM") == false; nomeArq = MyIO.readLine()){
		Personagem personagem = new Personagem();
		personagem.lerPersonagem(nomeArq);
		tabela.inserir(personagem);
        }//fim for

	long inicio = now();
        for(String nome_pesquisa = MyIO.readLine(); nome_pesquisa.equals("FIM") == false; nome_pesquisa = MyIO.readLine()){
            	MyIO.print(nome_pesquisa + " ");
            	boolean resp = tabela.pesquisar(nome_pesquisa);
		if(resp){
		    MyIO.println("SIM");
		}
		else{
		    MyIO.println("N"+((char)195)+"O");
		}//fim if
	}//fim for
	long fim = now();
	double time = (fim-inicio)/1000.0;
	FileWriter arq = new FileWriter("matrícula_hashReserva.txt");
	PrintWriter escreverArq = new PrintWriter(arq);
	escreverArq.printf("651636"+"\t"+time+"\t"+tabela.getComp());

	arq.close();
    }
	
	/**
    	*Retorna o timestamp atual
    	*@return timestamp atual
    	*/
	public static long now(){
		return new Date().getTime();
	}
}

class Hash{
    Personagem tabela[];
    int m1, m2, m, reserva;
    int comp = 0;	//Conta o numero de comparacoes realizadas pelo programa.

    public Hash (int m1, int m2){
        this.m1 = m1;
        this.m2 =  m2;
        this.m = m1 + m2;
        this.tabela = new Personagem[this.m];
        for(int i = 0; i < m; i++){
           tabela[i] = null;
        }//fim for i
        reserva = 0;
    }

    public int h(Personagem elemento){
        return elemento.getAltura() % 21;
    }

    public boolean inserir (Personagem elemento){
        boolean resp = false;
   
        if(elemento != null){   
            int pos = h(elemento);
   
            if(tabela[pos] == null){
                tabela[pos] = elemento;
                resp = true;   
            }else if (reserva < m2){
                tabela[m1 + reserva] = elemento;
                reserva++;
                resp = true;
            }
        }//fim if

        return resp;
    }

    public boolean pesquisar(String x){
        boolean resp = false;
		
	//Procura o elemento na tabela 
        for(int i = 0; i < m1 && resp == false; i++){
		if(tabela[i] != null && x.compareTo(tabela[i].getNome()) == 0){
			resp = true;
		}//fim if
		comp++;
	}//fim for i

	//Caso o elemento nao tenha sida encontrado na tabela, realiza a pesquisa na area de reserva
	if(resp == false){
        	for(int i = 0; resp == false && i < reserva; i++){
           		if(x.compareTo(tabela[m1+i].getNome()) == 0){
            	    		resp = true;
			}//fim if
			comp++;
		}//fim for i
	}//fim if
				
        return resp;
    }
	
	/**
	*Retorna o numero de comparacoes realizadas por esse programa
	*/
	public int getComp(){
		return this.comp;
	}
}
