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
		}

		if(0 <= posInicio && posInicio < posFim && posFim < s.length()){
			resp = s.substring(posInicio, posFim);
		}

		return resp;
	}

}

/*
*Classe TP04Q04ArvoreAlvinegra
*@author Rafael Schettino
*@version maio/2020
*/
public class TP04Q04ArvoreAlvinegra {
    public static void main(String[] args) throws Exception{
        MyIO.setCharset("UTF-8");
        Alvinegra alvinegra = new Alvinegra();
        for(String nomeArq = MyIO.readLine(); nomeArq.equals("FIM") == false; nomeArq = MyIO.readLine()){
			Personagem personagem = new Personagem();
			personagem.lerPersonagem(nomeArq);
			alvinegra.inserir(personagem);
        }

        long inicio = now();
        for(String nome_pesquisa = MyIO.readLine(); nome_pesquisa.equals("FIM") == false; nome_pesquisa = MyIO.readLine()){
            MyIO.print(nome_pesquisa + " ");
            boolean resp = alvinegra.pesquisar(nome_pesquisa);
			if(resp){
			    MyIO.println("SIM");
			}
			else{
			    MyIO.println("N"+((char)195)+"O");
			}//fim if
        }//fim for
        long fim = now();
		double time = (fim-inicio)/1000.0;
		FileWriter arq = new FileWriter("matrícula_alvinegra.txt");
		PrintWriter escreverArq = new PrintWriter(arq);
		escreverArq.printf("651636"+"\t"+time+"\t"+alvinegra.getComp());

		arq.close();
        
        //alvinegra.mostrarPre();
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
*No da arvore alvinegra
*@author Rafael Schettino
*/
class No{
    public boolean cor;
    public Personagem elemento;
    public No esq, dir;

    /**
    *Construtores da classe No
    */
    public No (Personagem elemento){
        this(elemento, false, null, null);
    }
    public No (Personagem elemento, boolean cor){
        this(elemento, cor, null, null);
    }
    public No (Personagem elemento, boolean cor, No esq, No dir){
        this.cor = cor;
        this.elemento = elemento.clone();
        this.esq = esq;
        this.dir = dir;
    }
}

/**
*Arvore alvinegra
*@author Rafael Schettino
*/
class Alvinegra{
    private No raiz;    //Raiz da arvore
    private int comp = 0;   //Conta o numero de comparacoes realizadas pelo programa.

    /**
    *Construtor da classe.
    */
    public Alvinegra(){
        raiz = null;
    }

    /**
     * Metodo publico iterativo para pesquisar elemento.
     * @param elemento Elemento a ser procurado.
     * @return <code>true</code> se o elemento existir,
     * <code>false</code> em caso contrario.
     */
    public boolean pesquisar(String x){
        MyIO.print("raiz ");
        return pesquisar(x, raiz);
    }

    /**
     * Metodo privado recursivo para pesquisar elemento.
     * @param elemento Elemento que sera procurado.
     * @param i No em analise.
     * @return <code>true</code> se o elemento existir, 
     * <code>false</code> em caso contrario.
     */
    private boolean pesquisar(String x, No i){
        boolean resp;
        if(i == null){
            comp++;
            resp = false;
        }else if(x.compareTo(i.elemento.getNome()) == 0){
            comp += 2;
            resp = true;
        }else if(x.compareTo(i.elemento.getNome()) < 0){
            comp += 3;
            MyIO.print("esq ");
            resp = pesquisar(x, i.esq);
        }else{
            comp+=3;
            MyIO.print("dir ");
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
			System.out.print(i.elemento.getNome() + ((i.cor) ? "(p) " : "(b) ")); //Conteudo do no.
			mostrarPre(i.esq);  //Elementos da esquerda.
			mostrarPre(i.dir);  //Elementos da direita.
		}//fim if
    }
    
    /**
    *Metodo publico iterativo para inserir elemento.
    *@param elemento Elemento a ser inserido.
    *@throws Exception Se o elemento existir.
    */
    public void inserir(Personagem elemento) throws Exception{
        //Se a arvore tiver tres ou mais elementos
        if(raiz != null && raiz.esq != null && raiz.dir != null){
            inserir(elemento, null, null, null, raiz);
    
        //Senao, se a arvore estiver vazia
        }else if(raiz == null){
            raiz = new No(elemento, false);

        //Senao, se a arvore tiver um elemento 
        }else if (raiz.esq == null && raiz.dir == null){
            if(raiz.elemento.getNome().compareTo(elemento.getNome()) > 0){
                raiz.esq = new No(elemento, true);
            }else {
                raiz.dir = new No(elemento, true);
            }

        //Senao, se a arvore tiver dois elementos (raiz e dir)
        }else if(raiz.esq == null){
            if(raiz.elemento.getNome().compareTo(elemento.getNome()) > 0){
                raiz.esq = new No(elemento);
            }else if(raiz.dir.elemento.getNome().compareTo(elemento.getNome()) > 0){
                raiz.esq = new No(raiz.elemento);
                raiz.elemento = elemento.clone();
            }else {
                raiz.esq = new No(raiz.elemento);
                raiz.elemento = raiz.dir.elemento.clone();
                raiz.dir.elemento = elemento.clone();
            }

            raiz.esq.cor = raiz.dir.cor = false;
       
        //Senao, se a arvore tiver dois elementos (raiz e esq)
        }else if(raiz.dir == null){       
            if(raiz.elemento.getNome().compareTo(elemento.getNome()) < 0){
                raiz.dir = new No(elemento);
            }else if (raiz.esq.elemento.getNome().compareTo(elemento.getNome()) < 0){
                raiz.dir = new No(raiz.elemento);
                raiz.elemento = elemento.clone();
            }else {
                raiz.dir = new No(raiz.elemento);
                raiz.elemento = raiz.esq.elemento.clone();
                raiz.esq.elemento = elemento.clone();
            }
            raiz.esq.cor = raiz.dir.cor = false;
        }else {
            throw new Exception("Erro ao inserir!");
        }

        raiz.cor = false;
    }

    private void balancear(No bisavo, No avo, No pai, No i){ 
        //Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if(pai.cor == true){   
            //4 tipos de reequilibrios e acoplamento
            if(pai.elemento.getNome().compareTo(avo.elemento.getNome()) > 0){ //rotacao a esquerda ou direita-esquerda
                if(i.elemento.getNome().compareTo(pai.elemento.getNome()) > 0){
                    avo = rotacaoEsq(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }   
            }else { //rotacao a direita ou esquerda-direita
                if(i.elemento.getNome().compareTo(pai.elemento.getNome()) < 0){
                    avo = rotacaoDir(avo);
                }else {
                    avo = rotacaoEsqDir(avo);
                }
            }
   
            if(bisavo == null){
                raiz = avo;
            }else {
                if(avo.elemento.getNome().compareTo(bisavo.elemento.getNome()) < 0){
                    bisavo.esq = avo;
                }else {
                    bisavo.dir = avo;
                }
            }
   
            //reestabelecer as cores apos a rotacao
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
        }//if(pai.cor == true)
    }

    /**
     * Metodo privado recursivo para inserir elemento.
     * @param elemento Elemento a ser inserido.
     * @param avo No em analise.
     * @param pai No em analise.
     * @param i No em analise.
     * @throws Exception Se o elemento existir.
     */
    private void inserir(Personagem elemento, No bisavo, No avo, No pai, No i) throws Exception {
        if(i == null) { 
            if(elemento.getNome().compareTo(pai.elemento.getNome()) < 0){
                i = pai.esq = new No(elemento, true);
            }else {
                i = pai.dir = new No(elemento, true);
            }
    
            if(pai.cor == true){
               balancear(bisavo, avo, pai, i);
            }    
        }else {    
            //Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
            if(i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true){
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if(i == raiz){
                    i.cor = false;
                }else if(pai.cor == true){
                    balancear(bisavo, avo, pai, i);
                }
            }//fim if

            if(elemento.getNome().compareTo(i.elemento.getNome()) < 0) {
                inserir(elemento, avo, pai, i, i.esq);
            }else if (elemento.getNome().compareTo(i.elemento.getNome()) > 0) {
                inserir(elemento, avo, pai, i, i.dir);
            }else {
                throw new Exception("Erro inserir (elemento repetido)!");
            }
        }
    }

    private No rotacaoDir(No no){
        No noDir = no.dir;
        No noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private No rotacaoEsq(No no) {
        No noDir = no.dir;
        No noDirEsq = noDir.esq;
  
        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private No rotacaoDirEsq(No no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private No rotacaoEsqDir(No no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }

    public int getComp(){
        return this.comp;
    }
}