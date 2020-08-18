class TP01Q15IsRecursivo {
    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    //Realiza a chamada da recursao pela primeira vez e inicializa o contador
    public static boolean somenteVogais(String s){
        return somenteVogais(s, 0);
    }

    /*
    *Verifica de forma recursiva se a String recebida e composta apenas por vogais
    *@param s String que sera verificada
    *@param i int que sera utilizado como contador, para percorrer a string
    *@return resp boolean <true> se a string for composta apenas por vogais
    *<false> caso contrario
    */
    public static boolean somenteVogais(String s, int i){
        boolean resp;
        if(i == s.length()){
            resp = true;
        }else{
            if(s.charAt(i) != 'A' && s.charAt(i) != 'a' && s.charAt(i) != 'E' && s.charAt(i) != 'e' && s.charAt(i) != 'I' && s.charAt(i) != 'i' && s.charAt(i) != 'O' && s.charAt(i) != 'o' && s.charAt(i) != 'U' && s.charAt(i) != 'u'){
                resp = false;
            }else{
                resp = somenteVogais(s, i+1);
            }//fim if
        }//fim if

        return resp;
    }
    
    //Realiza a chamada da recursao pela primeira vez e inicializa o contador
    public static boolean somenteConsoantes(String s){
        return somenteConsoantes(s, 0);
    }

    /*
    *Verifica de forma recursiva se a String recebida e composta apenas por consoantes
    *@param s String que sera verificada
    *@param i int que sera utilizado como contador, para percorrer a string
    *@return boolean resp <true> se a string for composta apenas por consoantes
    *<false> caso contrario
    */
    public static boolean somenteConsoantes(String s, int i){
        boolean resp;
        if(i == s.length()){
            resp = true;
        }else if((s.charAt(i) >= 'B' && s.charAt(i) <= 'D') || (s.charAt(i) >= 'b' && s.charAt(i) <= 'd') || (s.charAt(i) >= 'F' && s.charAt(i) <= 'H') || (s.charAt(i) >= 'f' && s.charAt(i) <= 'h') || (s.charAt(i) >= 'J' && s.charAt(i) <= 'N') || (s.charAt(i) >= 'j' && s.charAt(i) <= 'n') || (s.charAt(i) >= 'P' && s.charAt(i) <= 'T') || (s.charAt(i) >= 'p' && s.charAt(i) <= 't') || (s.charAt(i) >= 'V' && s.charAt(i) <= 'Z') || (s.charAt(i) >= 'v' && s.charAt(i) <= 'z')){
            resp = somenteConsoantes(s, i+1);
        }else{ 
            resp = false;
        }//fim if

        return resp;
    }

    //Realiza a chamada da recursao pela primeira vez e inicializa o contador
    public static boolean isNumeroInteiro(String s){
        return isNumeroInteiro(s, 0);
    }

    /*
    *Verifica de forma recursiva se a string recebida corresponde a um numero inteiro
    *@param s String que sera verificada
    *@param i int que sera utilizado como contador, para percorrer a string
    *@return boolean resp <true> se corresponder a um numero inteiro
    *<false> caso contrario
    */
    public static boolean isNumeroInteiro(String s, int i){
        boolean resp;
        if(i == s.length()){
            resp = true;
        }else{
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                resp = isNumeroInteiro(s, i+1);
            }else{
                resp = false;
            }//fim if
        }//fim if

        return resp;
    }

    /*
    *Conta o numero de virgulas ou pontos da String recebida
    *@param s String de entrada
    *@return qtd_virgula int correspondente ao numero de virgulas
    *presentes na String
    */
    public static int contaVirgula(String s){
        int qtd_virgula = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '.' || s.charAt(i) == ','){
                qtd_virgula++;
            }//fim if
        }//fim for i

        return qtd_virgula;
    }

    //Realiza a chamada da recursao pela primeira vez e inicializa o contador
    public static boolean isNumeroReal(String s){
        int qtd_virgula = contaVirgula(s);
        return isNumeroReal(s, 0, qtd_virgula);
    }

    /*
    *Verifica de forma recursiva se a string recebida corresponde a um numero real
    *@param s String que sera verificada
    *@param i int que sera utilizado como contador, para percorrer a string
    *@return boolean resp <true> se corresponder a um numero real
    *<false> caso contrario
    */
    public static boolean isNumeroReal(String s, int i, int qtd_virgula){
        boolean resp;
        if(i == s.length()){
            resp = true;
        }else{
            if(((s.charAt(i) >= '0' && s.charAt(i) <= '9') || (s.charAt(i) == ',') || (s.charAt(i) == '.')) && qtd_virgula <= 1){
                resp = isNumeroReal(s, i+1, qtd_virgula);
            }else{  
                resp = false;
            }//fim if
        }//fim if

        return resp;
    }

    public static void main(String[] args) {
        String[] entrada = new String[1000];
        int numEntrada = 0;
        boolean resp_vogais;
        boolean resp_consoantes;
        boolean resp_inteiro;
        boolean resp_real;

        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(isFim(entrada[numEntrada++]) == false);
        numEntrada--;

        for(int i = 0; i < numEntrada; i++){
            resp_vogais = somenteVogais(entrada[i]);
            if(resp_vogais){
                MyIO.print("SIM ");
            }else{
                MyIO.print("NAO ");
            }//fim if

            resp_consoantes = somenteConsoantes(entrada[i]);
            if(resp_consoantes){
                MyIO.print("SIM ");
            }else{
                MyIO.print("NAO ");
            }//fim if

            resp_inteiro = isNumeroInteiro(entrada[i]);
            if(resp_inteiro){
                MyIO.print("SIM ");
            }else{
                MyIO.print("NAO ");
            }//fim if

            resp_real = isNumeroReal(entrada[i]);
            if(resp_real){
                MyIO.print("SIM ");
            }else{
                MyIO.print("NAO ");
            }//fim if

            MyIO.println("");
        }//fim for i
    }
}