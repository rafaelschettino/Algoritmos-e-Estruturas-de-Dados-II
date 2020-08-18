class TP01Q12CiframentoRecursivo {
    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    //Realiza a chamada da recursao pela primeira vez e inicializa o contador
    public static String criptografar(String s){
        return criptografar(s, 0);
    }

    /*
    *Realiza de forma recursiva o ciframento da String de entrada
    *@param s String a ser cifrada
    *@param i int utilizado como contador, para percorrer a string recebida
    *@return resp String contendo a palavra de entrada de forma cifrada
    */
    public static String criptografar(String s, int i){
        String resp = new String();
        char[] aux = new char[1000];
        if(i == s.length()){
            resp = "";
        }else{
            aux[i] = (char)(s.charAt(i)+3);      
            resp += aux[i] + criptografar(s, i+1);
        }//fim if

        return resp;
    }

    public static void main(String[] args) {
        String[] entrada = new String[1000];
        int numEntrada = 0;
        String resp;

        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(isFim(entrada[numEntrada++]) == false);
        numEntrada--; //Desconsidera a ultima linha contendo a palavra FIM

        for(int i = 0; i < numEntrada; i++){
            resp = criptografar(entrada[i]);
            MyIO.println(""+resp);
        }//fim for i
    }
}
