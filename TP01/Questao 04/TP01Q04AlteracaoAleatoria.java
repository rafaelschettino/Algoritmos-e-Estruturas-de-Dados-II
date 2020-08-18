import java.util.Random;

class TP01Q04AlteracaoAleatoria {
    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
 
    /*
     *Substitui todas as ocorrencias da letra1 na string de entrada pela letra2 e retorna a string com as alteracoes efetuadas
     *@param s String que sera aleatoriamente modificada
     *@return resp String correspondente a String de entrada apos a alteracao de suas letras
     */	    
    public static String alteracaoAleatoria(String s, Random gerador){
        String resp = "";
        char letra1 = (char)('a' + (Math.abs(gerador.nextInt()) % 26)); //Letra que sera substituida 
        char letra2 = (char)('a' + (Math.abs(gerador.nextInt()) % 26)); //Letra que ira substituir a "letra1" na String de resposta
        char[] aux = new char[1000];
  
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == letra1){
                aux[i] = letra2;
            }else{
                aux[i] = s.charAt(i);
            }//fim if   
        }//fim for i
  
        for(int j = 0; j < s.length(); j++){
            resp += aux[j];
        }//fim for j
  
        return resp;
    }
    
    public static void main(String[] args) {
        String[] entrada = new String[1000];
        int numEntrada = 0;
        String resp;
  
        Random gerador = new Random();
        gerador.setSeed(4);
        //System.out.println((char)('a' + (Math.abs(gerador.nextInt()) % 26)));
  
        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(isFim(entrada[numEntrada++]) == false);
        numEntrada--; //Deconsidera a ultima linha de entrada contendo a palavra FIM
  
        for(int i = 0; i < numEntrada; i++){
            resp = alteracaoAleatoria(entrada[i], gerador);
            MyIO.println(resp);
        }//fim for i
    }
}
