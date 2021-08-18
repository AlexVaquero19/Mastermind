package listaespera;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

public class ListaEspera {
    static ArrayList<Pacientes> lista = new ArrayList<Pacientes>();
    static ArrayList<Pacientes> historico = new ArrayList<Pacientes>();
    static Scanner leer = new Scanner(System.in);
    static Identificador ident = new Identificador();
    
    public static void menu(){
        //Mostramos el Menú y Pedimos una Opción hasta que se introduzca un 0
        int opcion;
        do{
            System.out.print("+------------------------------------------------------------------+"
                            +"\n***Elige una Opcion(1-3)***                                        |\n"
                            +"1.  | Generar Identificador                                        |\n"
                            +"2.  | Llamar al siguiente Paciente                                 |\n"
                            +"3.  | Llamar a un Determinado Paciente                             |\n"
                            +"4.  | Mostrar la Lista de Espera                                   |\n"
                            +"5.  | Mostrar los Ultimos 5 Pacientes                              |\n"
                            +"6.  | Marcar Paciente como Atendido                                |\n"
                            +"7.  | Mostrar Histórico de los Pacientes                           |\n"
                            +"8.  | Mostrar las Estadisticas                                     |\n"
                            +"0.  |                         SALIR                                |\n"
                            +"+------------------------------------------------------------------+\n");

            opcion = leer.nextInt();//Pedimos que Opción del Menú Queremos
            //Mediante el Switch llamamos a cada Método
            switch(opcion){
                case 1: generadorPaciente();break;
                case 2: llamarSiguienteP();break;
                case 3: llamarPaciente(opcion);break;
                case 4: mostrarLista();break;
                case 5: ultimosPacientes(); break;
                case 6: llamarPaciente(opcion); break;
                case 7: mostrarHistorico();break;
                case 8: estadisticas();break;
            }
        }while(opcion != 0 || opcion > 10);
    }
    
    public static void generadorPaciente(){
        int n = (int) (Math.random()*20);
        Pacientes p = new Pacientes();

        p.identificador = existe(ident.id());
        p.estado = estadoP(0);
        p.horaLlegada = LocalTime.now();

        lista.add(p);
        historico.add(p);
            
        System.out.println("El Identificador se ha creado correctamente");
    }
    public static String existe(String id){
        boolean existe = false;
        String nId = id;
        
        for(int i=0; i < lista.size() && existe; i++){
            if(id == lista.get(i).identificador){
                existe = true;
                nId = ident.id();
            }else{
                existe = false;
            }
        }
        return nId;
    }
    public static String estadoP(int n){
        String[] estados = {"Espera","Llamado","Atendido"};
        
        return estados[n];
    }
    
    public static void llamarSiguienteP(){
        boolean cierto = false;
        for(int i=0; i < lista.size() && !cierto; i++){
            if(lista.get(i).estado == "Espera"){
                lista.get(i).estado = estadoP(1);
                lista.get(i).horaLlamada = LocalTime.now();
                cierto = true;
            }
        }
    }
    
    public static void llamarPaciente(int opcion){
        leer.nextLine();
        System.out.println("Identificador del Paciente");
        String id = leer.nextLine();
        int n = -1;
        boolean existe = false;
        
        for(int i=0; i < lista.size() && !existe; i++){
            if(id.equals(lista.get(i).identificador)){
                n = i;
                existe = true;
            }
        }
        
        if(existe && opcion == 3){
            lista.get(n).estado = estadoP(1);
            lista.get(n).horaLlamada = LocalTime.now();
            historico = (ArrayList<Pacientes>) lista.clone();

        }else if(existe && opcion == 6){
            lista.get(n).estado = estadoP(2);
            lista.get(n).horaAtendido = LocalTime.now();

        }else{
            System.out.println("El paciente no se encuentra");
        }
    }
    
    public static void mostrarLista(){
        DateTimeFormatter isoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        
        for(int i=0; i < lista.size(); i++){
            System.out.println(lista.get(i).identificador+"\n"+lista.get(i).horaLlegada.format(isoHora));
        }
    }
    
    //FALTA Método mostrar los Últimos 5 pacientes atendidos
    public static void ultimosPacientes(){
        int cont = 0; 
        for(int i = lista.size(); cont < 5; cont++){
            if(lista.get(i).estado == "Atendido"){
                System.out.println(lista.get(i));
            }
        }
    }
    
    public static void mostrarHistorico(){
        historico = (ArrayList<Pacientes>) lista.clone();
        boolean seguir = false;
        
        for(int i=0; i < historico.size(); i++){
            if(historico.get(i).horaLlamada != null && historico.get(i).horaAtendido != null){
                System.out.println(historico.get(i));
                seguir = true;
            }else{
                seguir = false;
            }
        }
    }
    
    public static void estadisticas(){
        int tam = historico.size();
        
        System.out.println("Numero Total de Pacientes: "+tam);
        for(int i=0; i < historico.size(); i++){
            int h = (int) historico.get(i).horaLlegada.until(historico.get(i).horaAtendido, ChronoUnit.HOURS);
            int m = (int) historico.get(i).horaLlegada.until(historico.get(i).horaAtendido, ChronoUnit.MINUTES);
            int s = (int) historico.get(i).horaLlegada.until(historico.get(i).horaAtendido, ChronoUnit.SECONDS);
            System.out.println("Paciente: "+(i+1)+" ha tardado "+h+" horas "+m+" minutos "+s+" segundos");
        }
    }
}