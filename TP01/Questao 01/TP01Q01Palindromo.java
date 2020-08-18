class TP01Q01Palindromo{
	public static boolean isFim(String s){
		return(s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
	}

	/*
	 * Verifica se a String recebida corresponde a um palindromo
	 * @param s String palavra a ser verificada
	 * @return resp boolean <true> se for palindromo <false> se nao for palindromo
	 */	
	public static boolean isPalindromo(String s){
		int tam = s.length();
		int i = 0;
		int j = tam - i - 1;
		boolean resp = true;
		while(resp && i <= s.length()/2){
		   if(s.charAt(i) != s.charAt(j)){
				resp = false;
		   }//fim if
		   i++;
		   j = s.length() - i - 1;
		}//fim while
	
		return resp;
	}
	  
	public static void main(String[] args) {
		String[] entrada = new String[1000];
		int numEntrada = 0;
	
		//Leitura da entrada padrao
		do{
			entrada[numEntrada] = MyIO.readLine();
		}while(isFim(entrada[numEntrada++]) == false);
		numEntrada--; //Desconsidera a ultima linha de entrada contendo a palavra FIM
		boolean resp;
		for(int i = 0; i < numEntrada; i++){
			resp = isPalindromo(entrada[i]);
			if(resp){
				MyIO.println("SIM");
			} else {
				MyIO.println("NAO");
			}//fim if
		}//fim for i
	}	
}
