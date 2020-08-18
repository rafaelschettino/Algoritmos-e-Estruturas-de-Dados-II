import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

class TP01Q07LeituraPaginaHTML{
	public static boolean isFim(String s){
		return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
	}

	/*
	*Abre o arquivo HTML a partir de sua URL e armazena o texto contido em sua pagina  
	*@param s String endereco da pagina web
	*@return pagina String com o conteudo da pagina HTML
	*/
	public static String leituraPagina(String s){
		String linha = "";
		String pagina = "";
		try{	
			URL url = new URL(s);
			//URLConnection conn = url.openConnection();

			InputStream is = url.openStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
	
			linha = br.readLine();
			while(linha != null){
				pagina += linha;
				linha = br.readLine();
			}//fim while
			br.close();
		} catch (MalformedURLException excecao){
			excecao.printStackTrace();
		} catch (IOException excecao){
			excecao.printStackTrace();
		}
		return pagina;
	}

	/*
	*Calcula o numero de ocorrencias de cada caractere ou string solicitado
	*@param s String correspondente ao texto da pagina web a ser analisada
	*/
	public static void getOcorrencias(String s){
		int a = 0, e = 0, i = 0, o = 0, u = o, á = 0, é = 0, í = 0, ó = 0, ú = 0, à = 0, è = 0, ì = 0, ò = 0, ù = 0,
		ã = 0, õ = 0, â = 0, ê = 0, î = 0, ô = 0, û = 0, consoante = 0, br = 0, table = 0;

		for(int j = 0; j < s.length(); j++){
			if(s.charAt(j) == '<' && s.charAt(j+1) == 't' && s.charAt(j+2) == 'a' && s.charAt(j+3) == 'b' && s.charAt(j+4)=='l' && s.charAt(j+5)=='e' && s.charAt(j+6)=='>'){
				table++;
				j += 6; //Caso seja encontrado um '<table>' o contador sera apontado para o final do padrao, fazendo com que suas consoantes e vogais nao sejam contabilizadas
			}else if((s.charAt(j) >= 'b' && s.charAt(j) <= 'd') || (s.charAt(j) >= 'f' && s.charAt(j) <= 'h') || (s.charAt(j) >= 'j' && s.charAt(j) <= 'n') || (s.charAt(j) >= 'p' && s.charAt(j) <= 't') || (s.charAt(j) >= 'v' && s.charAt(j) <= 'z')){
				consoante++;
			}else if(s.charAt(j) == '<' && s.charAt(j+1) == 'b' && s.charAt(j+2) == 'r' && s.charAt(j+3) == '>'){
				br++;
				j += 3; //Caso seja encontrado um '<br>' o contador sera apontado para o final do padrao, fazendo com que suas vogais e consoantes nao sejam contabilizadas
			}else{
				if(s.charAt(j) == 'a'){
					a++;
				} else if(s.charAt(j) == 'e'){
					e++;
				} else if(s.charAt(j) == 'i'){
					i++;
				} else if(s.charAt(j) == 'o'){
					o++;
				} else if(s.charAt(j) == 'u'){
					u++;
				} else if(s.charAt(j) == 'á'){
					á++;
				} else if(s.charAt(j) == 'é'){
 					é++;
				} else if(s.charAt(j) == 'í'){
					í++;
				} else if(s.charAt(j) == 'ó'){
					ó++;
				} else if(s.charAt(j) == 'ú'){
					ú++;
				} else if(s.charAt(j) == 'à'){
					à++;
				} else if(s.charAt(j) == 'è'){
					è++;
				} else if(s.charAt(j) == 'ì'){
					ì++;
				} else if(s.charAt(j) == 'ò'){
					ò++;
				} else if(s.charAt(j) == 'ù'){
					ù++;
				} else if(s.charAt(j) == 'ã'){
					ã++;
				} else if(s.charAt(j) == 'õ'){
					õ++;
				} else if(s.charAt(j) == 'â'){
					â++;
				} else if(s.charAt(j) == 'ê'){
					ê++;
				} else if(s.charAt(j) == 'î'){
					î++;
				} else if(s.charAt(j) == 'ô'){
					ô++;
				} else if(s.charAt(j) == 'û'){
					û++;
				}//fim if
			}//fim if
		}//fim for j

		MyIO.print("a("+a+") e("+e+") i("+i+") o("+o+") u("+u+") á("+á+") é("+é+") í("+í+") ó("+ó+") ú("+ú+") à("+à+") è("+
		è+") ì("+ì+") ò("+ò+") ù("+ù+") ã("+ã+") õ("+õ+") â("+â+") ê("+ê+") î("+î+") ô("+ô+") û("+û+") consoante("+consoante+
		") <br>("+br+") <table>("+table+") ");
	}

	public static void main(String[] args){
		MyIO.setCharset("UTF-8");
		String[] nome_pagina = new String[1000];
		String[] endereco_pagina = new String[1000];
		int numEntrada = 0;
		String pagina;
		
		//Leitura da entrada padrao
		do{
			nome_pagina[numEntrada] = MyIO.readLine();
			if(isFim(nome_pagina[numEntrada]) == false){
				endereco_pagina[numEntrada] = MyIO.readLine();
			}//fim if	
		}while(isFim(nome_pagina[numEntrada++]) == false );
		numEntrada--; //Desconsidera a ultima linha de entrada contendo a palavra FIM

		for(int i = 0; i < numEntrada; i++){
			pagina = leituraPagina(endereco_pagina[i]);
			getOcorrencias(pagina);
			MyIO.println(nome_pagina[i]);
			//MyIO.println(pagina);
		}//fim for i

	}
}

