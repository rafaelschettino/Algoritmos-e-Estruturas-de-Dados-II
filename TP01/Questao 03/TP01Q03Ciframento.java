class TP01Q03Ciframento{
    
    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
	
    /*
     * Realiza o ciframento da String de entrada
     *@param s String a ser cifrada
     *@return resp String que contem a String de entrada de forma cifrada
     */
    public static String criptografar(String s){
        int tam = s.length();
        String resp = "";
      
        char[] letrasCifradas = new char[1000];
        for(int j = 0; j < tam; j++){
            letrasCifradas[j] = (char)(s.charAt(j)+3);
        }//fim for j 

        for(int i = 0; i < tam; i++){
            resp += letrasCifradas[i];
        }//fim for i

        return resp;
    }
  
    public static void main(String[] args){
        String[] entrada = new String[1000];
        int numEntrada = 0;
        String resp;
      
        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(isFim(entrada[numEntrada++]) == false);
        numEntrada--;
      
        for(int i = 0; i < numEntrada; i++){
            resp = criptografar(entrada[i]);
            MyIO.println(""+resp);
        }//fim for i
    }

}
