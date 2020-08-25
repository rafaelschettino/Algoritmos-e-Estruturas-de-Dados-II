class TP01Q06Is{
    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
    
    /*
    *Verifica se a String recebida e composta apenas por vogais
    *@param s String que sera verificada
    *@return resp boolean <true> se for composta apenas por vogais
    *<false> caso contrario
    */
    public static boolean somenteVogais(String s){
        boolean resp = true;
        int i = 0;
        while(resp && i < s.length()){
            if(s.charAt(i) != 'A' && s.charAt(i) != 'a' && s.charAt(i) != 'E' && s.charAt(i) != 'e' && s.charAt(i) != 'I' && s.charAt(i) != 'i' && s.charAt(i) != 'O' && s.charAt(i) != 'o' && s.charAt(i) != 'U' && s.charAt(i) != 'u'){
                resp = false;
            }else{  
                resp = true;
            }//fim if
            i++;
        }//fim while

        return resp;
    }  
    
    /*
    *Verifica se a String recebida e composta apenas por consoantes
    *@param s String que sera verificada
    *@return boolean resp <true> se for composta apenas por consoantes
    *<false> caso contrario
    */
    public static boolean somenteConsoantes(String s){
        boolean resp = true;
        int i = 0;
        while(resp && i < s.length()){
            if((s.charAt(i) >= 'B' && s.charAt(i) <= 'D') || (s.charAt(i) >= 'b' && s.charAt(i) <= 'd') || (s.charAt(i) >= 'F' && s.charAt(i) <= 'H') || (s.charAt(i) >= 'f' && s.charAt(i) <= 'h') || (s.charAt(i) >= 'J' && s.charAt(i) <= 'N') || (s.charAt(i) >= 'j' && s.charAt(i) <= 'n') || (s.charAt(i) >= 'P' && s.charAt(i) <= 'T') || (s.charAt(i) >= 'p' && s.charAt(i) <= 't') || (s.charAt(i) >= 'V' && s.charAt(i) <= 'Z') || (s.charAt(i) >= 'v' && s.charAt(i) <= 'z')){
                resp = true;
            } else{
                resp = false;
            }//fim if
            i++;
        }//fim while
  
        return resp;
    }
  
    /*
    *Verifica se a String recebida corresponde a um numero inteiro
    *@param s String que sera verificada
    *@return boolean resp <true> se corresponder a um numero inteiro
    *<false> caso contrario
    */
    public static boolean isNumeroInteiro(String s){
      boolean resp = true;
      int i = 0;
      while(resp && i < s.length()){
          if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
              resp = true;
          }else{
              resp = false;
          }//fim if
          i++;
      }//fim while
  
      return resp;
    }

    /*
    *Verifica se a String recebida corresponde a um numero real
    *@param s String a ser verificada
    *@return boolean resp <true> se corresponder a um numero real
    *<false> caso contrario
    */  
    public static boolean isNumeroReal(String s){
        boolean resp = true;
        int i = 0;
        int qtd_virgulas = 0;
        for(int j = 0; j < s.length(); j++){
            if(s.charAt(j) == ',' || s.charAt(j) == '.'){
                qtd_virgulas++;
            }//fim if
        }//fim for j
  
        while(resp && i < s.length()){
            if(((s.charAt(i) >= '0' && s.charAt(i) <= '9') || (s.charAt(i) == ',') || (s.charAt(i) == '.')) && qtd_virgulas <= 1){
                resp = true;
            }else{
                resp = false;
            }//fim if
            i++;
        }//fim while
  
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
            }else {
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
            } else{
                MyIO.print("NAO ");
            }//fim if
  
            MyIO.println("");
        }//fim for i
    }
}
