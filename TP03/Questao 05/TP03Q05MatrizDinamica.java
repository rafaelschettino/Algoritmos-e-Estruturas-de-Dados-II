/**
*Celula Matriz
*@author Rafael Schettino
*@version abr/2020
*/
class CelulaMatriz{
    int elemento;
    CelulaMatriz prox, ant, inf, sup;

    /**
    *Construtores da classe CelulaMatriz 
    */
    public CelulaMatriz(){
        this(0);
    }
    public CelulaMatriz(int elemento){
        this.elemento = elemento;
        prox = ant = inf = sup = null;
    }
}

/**
*Matriz Dinamica
*@author Rafael Schettino 
*@version abr/2020 
*/
class Matriz{
    int linha, coluna;
    CelulaMatriz inicio;    //Celula inicial da Matriz Dinamica

    /*
    *Construtores da classe Matriz
    *Alocam uma matriz com this.linhas linhas e this.colunas colunas
    */
    public Matriz(){
        this(3, 3);
    }
    public Matriz(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
        int x = 0;

        CelulaMatriz i, j;
        int lin, col;

        //Criar a primeira celula
        inicio = new CelulaMatriz(x++);

        //Criar as demais (coluna-1) celulas da 1a linha
        for(j = inicio, col = 1; col < coluna; j = j.prox, col++){
            j.prox = new CelulaMatriz(x++);
            j.prox.ant = j;
        }//fim for j

        //Criar as demais (linha-1) linhas
        for(i = inicio, lin = 1; lin < linha; i = i.inf, lin++){
            i.inf = new CelulaMatriz(x++);
            i.inf.sup = i;

            for(j = i.inf, col = 1; col < coluna; j = j.prox, col++){
                j.prox = new CelulaMatriz(x++);
                j.prox.ant = j;
                j.prox.sup = j.sup.prox;
                j.sup.prox.inf = j.prox;
            }//fim for j
        }//fim for i
    }

    /**
    *Mostra os elementos da Matriz, linha por linha 
    */
    public void mostrar(){
        CelulaMatriz i;
        for(i = inicio; i != null; i = i.inf){
            for(CelulaMatriz j = i; j != null; j = j.prox){
                MyIO.print(j.elemento+" ");
            }//fim for j
            MyIO.println("");
        }//fim for i
    }

    /**
    *Mostra a diagonal principal de uma Matriz, desde que ela seja quadrada 
    */
    public void mostrarDiagonalPrincipal(){
        //Verifica se a matriz é quadrada, para que se possa ser mostrada sua diagonal principal
        if(this.linha == this.coluna && linha > 0){
            CelulaMatriz i = inicio;

            do{
                MyIO.print(i.elemento+" ");
                i = i.prox;
                if(i != null){
                    i = i.inf;
                }//fim if
            }while(i != null);
        }//fim if

        MyIO.println("");
    }

    /**
    *Mostra a diagonal secundaria de uma Matriz, desde que ela seja quadrada 
    */
    public void mostrarDiagonalSecundaria(){
        //Verifica se a matriz é quadrada, para que se possa ser mostrada sua diagonal secundaria
        if(this.linha == this.coluna && linha > 0){
            CelulaMatriz i;

            //Caminha ate o ultimo elemento da primeira linha
            for(i = inicio; i.prox != null; i = i.prox);

            do{
                MyIO.print(i.elemento+" ");
                i = i.inf;
                if(i != null){
                    i = i.ant;
                }//fim if
            }while(i != null);
        }//fim if

        MyIO.println("");
    }   

    /**
    *Realiza a soma de duas matrizes e armazena o resultado em uma terceira.
    *A soma só será realizada caso as matrizes tenham as mesmas dimensões.
    *@param Matriz b, segunda Matriz da soma.
    *@return Matriz resp, contendo uma nova matriz com a soma das outras duas. 
    */
    public Matriz soma(Matriz b){
        Matriz resp = null;
        
        //Verifica se as duas matrizes têm tamanhos iguais, para que a soma aconteça
        if(this.linha == b.linha && this.coluna == b.coluna){
            resp = new Matriz(this.linha, this.coluna);
              
            CelulaMatriz iResp = resp.inicio;
            CelulaMatriz iA    = this.inicio;
            CelulaMatriz iB    = b.inicio;
            //Caminha pelas linhas das matrizes
            for(int i = 0; i < linha; i++){
                CelulaMatriz jResp = iResp;
                CelulaMatriz jA    = iA;
                CelulaMatriz jB    = iB;
                //Caminha pelas colunas das matrizes
                for(int j = 0; j < coluna; j++){
                    jResp.elemento = jA.elemento + jB.elemento;
                    jResp = jResp.prox;
                    jA = jA.prox;
                    jB = jB.prox;
                }//fim for j
                iResp = iResp.inf;
                iA = iA.inf;
                iB = iB.inf;
            }//fim for i
        }//fim if

        return resp;
    }

    /**
    *Realiza a multiplicacao de duas matrizes e armazena o resultado em uma terceira.
    *Para que uma multiplicacao de matrizes aconteça, o numero de colunas da primeira,
    *deve ser igual ao numero de linhas da segunda.
    *@param Matriz b, segunda Matriz da multiplicacao.
    *@return Matriz resp, contendo a matriz resultante da multiplicacao.
    */
    public Matriz multiplicacao(Matriz b){
        Matriz resp = null;
        //Verifica a condicao para que possa ocorrer a multiplicacao.
        if(this.coluna == b.linha){
            resp = new Matriz(this.linha, b.coluna);
            int aux = 0;
            for(int i = 0; i < this.coluna; i++){
                for(int j = 0; j < this.linha; j++){
                    aux = 0;
                    for(int k = 0; k < this.linha; k++){
                        aux += (buscar(k, i) * b.buscar(j, k));
                    }//fim for k
                    resp.insereElemento(i, j, aux);
                }//fim for j
            }//fim for i
        }//fim if

        return resp;
    }

    /**
    *Procura e retorna a Celula de um determinado elemento da Matriz,
    *tendo recebido linha e coluna correspondentes.
    */
    public CelulaMatriz posicao(int pi, int pj){
        CelulaMatriz pos = inicio;
        for(int i = 0; i < pi; i++, pos = pos.prox);
        for(int j = 0; j < pj; j++, pos = pos.inf);
        return pos;
    }

    /**
    *Busca e retorna um determinado elemento de determinada posicao da Matriz 
    */
    public int buscar(int pi, int pj){
        CelulaMatriz tmp = posicao(pi, pj);
        return tmp.elemento;
    }

    /**
    *Insere o elemento recebido na Matriz Dinamica
    *@param int linha onde sera inserido o elemento
    *@param int coluna onde sera inserido o elemento
    *@param int elemento a ser inserido
    */
    public void insereElemento(int linha, int coluna, int elemento){
        CelulaMatriz aux = this.inicio;

        //Percorre as linhas da matriz
        for(int i = 0; i < linha; i++){
            aux = aux.inf;
        }//fim for i

        //Percorre as colunas da matriz
        for(int j = 0; j < coluna; j++){
            aux = aux.prox;
        }//fim for j

        aux.elemento = elemento;
        aux = null;
    }
}

public class TP03Q05MatrizDinamica{
    public static void main(String[] args) throws Exception{
        int n;  //Numero de casos da entrada padrao
        int l;  //Numero de linhas da primeira matriz
        int c;  //Numero de colunas da segunda matriz
        int l2; //Numero de linhas da segunda matriz
        int c2; //Numero de colunas da segunda matriz
        
        n = MyIO.readInt();
        for(int k = 0; k < n; k++){
                l = MyIO.readInt();
                c = MyIO.readInt();
                Matriz a = new Matriz(l, c);
                int elemento;
                //Insere os elementos na primeira matriz
                for(int i = 0; i < l; i++){
                    for(int j = 0; j < c; j++){
                        elemento = MyIO.readInt();
                        a.insereElemento(i, j, elemento);
                    }//fim for j
                }//fim for i

                l2 = MyIO.readInt();
                c2 = MyIO.readInt();
                Matriz b = new Matriz(l2, c2);
                int elemento2;
                //Insere os elementos na segunda matriz
                for(int i = 0; i < l2; i++){
                    for(int j = 0; j < c2; j++){
                        elemento2 = MyIO.readInt();
                        b.insereElemento(i, j, elemento2);
                    }//fim for j
                }//fim for i

            
                Matriz soma = a.soma(b);
                Matriz multiplicacao = a.multiplicacao(b);

                a.mostrarDiagonalPrincipal();
                a.mostrarDiagonalSecundaria();
                soma.mostrar();
                multiplicacao.mostrar();
        }//fim for k
    }
}
