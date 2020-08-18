class TP01Q10PalindromoRecursivo {

    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
    
    /*
    *Realiza a chamada da recursao pela primeira vez e inicializa o contador
    */
    public static boolean isPalindromo(String s){
        boolean resp =  isPalindromo(s, 0);
        return resp;
    }
    
    /*
    *Verifica de forma recursiva se a String de entrada correspone a um palindromo
    *@param s String a ser verificada
    *@param i int que sera utilizado como contador, para percorrer a String recebida
    *@return resp boolean <true> se a String for palindromo <false> caso contrario
    */
    public static boolean isPalindromo(String s, int i){
        boolean resp;
        int j = s.length() - i - 1;
        if(i > s.length()/2){
            resp = true;
        }else{
            if(s.charAt(i) != s.charAt(j)){
                resp = false;
            }else{
                resp = isPalindromo(s, i+1);
            }//fim if
        }//fim if
  
        return resp;
    }
    
    public static void main(String[] args) {
        String[] entrada = new String[1000];
        int numEntrada = 0;
        boolean resp;
  
        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(isFim(entrada[numEntrada++]) == false);
        numEntrada--;
  
        for(int i = 0; i < numEntrada; i++){
            resp = isPalindromo(entrada[i]);
            if(resp){
                MyIO.println("SIM");
            }else{
                MyIO.println("NAO");
            }//fim if
        }//fim for i
    }
}
