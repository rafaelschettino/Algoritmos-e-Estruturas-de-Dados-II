#include <stdio.h>
#include <stdlib.h>

int main(){
	int n; //Quantidade de valores que devem ser lidos
	scanf("%d", &n);

	double valor_real;
	FILE* arq = fopen("valores.txt", "w+");

	int i = 0;
	//Preenche o arquivo com os n valores reais lidos
	while(i < n){
		scanf("%lf", &valor_real);
		fwrite(&valor_real, sizeof(double), 1, arq);
		i++;
	}//fim while

	//Realiza a leitura do arquivo de tras para frente, enquanto imprime na tela os valores lidos 
	for(int j = 0; j < n; j++){
		fseek(arq, (8*n)-(sizeof(double)*(1+j)), SEEK_SET);
    	fread(&valor_real, sizeof(double), 1, arq);
    	printf("%g\n", valor_real);
    	valor_real = 0;
	}//fim for j

	fclose(arq);
	
	return 0;
}

