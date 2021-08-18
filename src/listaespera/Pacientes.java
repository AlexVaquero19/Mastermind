package listaespera;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Pacientes {
    public String identificador;
    public String estado;
    public LocalTime horaLlegada;
    public LocalTime horaLlamada;
    public LocalTime horaAtendido;
    
    @Override
    public String toString(){
        DateTimeFormatter isoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        
        String resul = "Identificador: "+identificador+"\nEstado: "+estado
                       +"\nHora de Llamada: "+horaLlegada.format(isoHora)+"\nHora de Llegada: "+horaLlamada.format(isoHora)
                       +"\nHora Atendido: "+horaAtendido.format(isoHora)+"\n";
        return resul;
    }
}