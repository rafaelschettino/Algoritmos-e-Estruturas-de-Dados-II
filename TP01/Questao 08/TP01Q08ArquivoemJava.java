import java.io.*;

class TP01Q08ArquivoemJava{
	public static void main(String[] args){
		int n = MyIO.readInt(); //Quantidade de valores que devem ser lidos
		double valor_real;
		int valor_int;

		try{
			RandomAccessFile arq = new RandomAccessFile("valores.txt", "rw");
			//Preenche o arquivo com n valores reais lidos
			for(int i = 0; i < n; i++){
				arq.writeDouble(MyIO.readDouble());
			}//fim for i
			
			for(int i = 0; i < n; i++){
				arq.seek(arq.length() - (8*(i+1)));
				valor_real = arq.readDouble();
				valor_int  = (int)valor_real;

				if(valor_real == valor_int){
					MyIO.println(valor_int);
				}else{
					MyIO.println(valor_real);
				}//fim if
			}//fim for i
			
		} catch(Exception e){
			//throw new Exception("Erro ao manusear arquivo!");
		}
	}
}
