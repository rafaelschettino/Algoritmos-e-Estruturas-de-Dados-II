#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define bool short
#define true 1
#define false 0
#define NUMENTRADA 1000
#define TAMLINHA 1000

bool isFim(char *s){
    return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

/*
 *Verifica se a String recebida corresponde a um palindromo
 *@param s String a ser verificada
 *@return resp bool <true> se for palindromo <false> se nao for
 */
bool isPalindromo(char *s){
    int tam = (int)strlen(s);
    int i = 0;
    int j = tam - i - 2;
    bool resp = true;
    while(resp && i <= (int)(strlen(s)/2)){
        if(s[i] != s[j]){
            resp = false;
        }//fim if
        i++;
        j = tam - i - 2;
    }//fim while

    return resp;
}

int main() {
    char entrada[NUMENTRADA][TAMLINHA];
    int numEntrada = 0;
    bool resp;
    //Leitura da entrada padrao
    do{
        fgets(entrada[numEntrada], TAMLINHA, stdin);
    }while(isFim(entrada[numEntrada++]) == false);
    numEntrada--;

    for(int i = 0; i < numEntrada; i++){
        resp = isPalindromo(entrada[i]);
        if(resp == true){
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }//fim if
    }//fim for i

    return 0;
}

