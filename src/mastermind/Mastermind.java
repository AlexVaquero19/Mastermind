package mastermind;

import java.util.Scanner;
public class Mastermind {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        
        int continuar = 1;
        
        while(continuar == 1){
            System.out.println("Benvenido al Juego MasterMind");
            System.out.println("Elige el Nivel de Dificultad: 1->Fácil | 2->Medio | 3->Dificil");
            
            int dificultad = leer.nextInt();
            int[] numeros = {0,1,2,3,4,5,6,7,8,9};
            int[] codigo = new int[3]; 
            
            switch(dificultad){
                case 1: codigo = new int[3];break;
                case 2: codigo = new int[4];break;
                case 3: codigo = new int[5];break;
            }
            
            int[] repetidos = new int[10];
            int n = (int) (Math.random()*10);
            repetidos[n] = 1;
            codigo[0] = numeros[n];
            
            for(int i=1; i < codigo.length; i++){
                n = (int) (Math.random()*10);
                while(repetidos[n] == 1){
                    repetidos[n] = 1;
                    n = (int) (Math.random()*10);              
                }
                repetidos[n] = 1;
                codigo[i] = numeros[n];
            }
           
            int intentos = 0, aciertos = 0, coincidencias = 0;

            while(aciertos != codigo.length){
                aciertos = 0;
                coincidencias = 0;
                System.out.println("Prueba un Código");
                int prueba = leer.nextInt();

                int[] comprobar = new int[codigo.length];
                for(int i=comprobar.length-1; i >= 0; i--){
                    comprobar[i] = prueba % 10;
                    prueba /= 10;
                }
                
                intentos++;
                
                for(int i=0; i < codigo.length; i++){
                    if(comprobar[i] == codigo[i]){
                        aciertos++;
                    }else{
                        boolean repe = false;
                        for(int k=i; k < codigo.length && !repe; k++){
                            for(int j=0; j < codigo.length && !repe; j++){
                                if(comprobar[j] == codigo[i]){
                                    coincidencias++;
                                    repe = true;
                                }
                            }
                        }
                    }
                }
                
                for(int i=0; i < codigo.length; i++){
                    System.out.print(codigo[i]);
                }
                
                
                System.out.println("Ha habido "+aciertos+" aciertos y "+coincidencias+" coincidencias");
            }
            System.out.println("Felicidades has acertado el código en: "+intentos+" intentos");
            System.out.println("¿Quieres Volver a Jugar? (1->SI | 0->NO)");
            continuar = leer.nextInt();
            if(continuar == 0){
                System.out.println("Hasta Pronto");
            }
        }
    }   
}