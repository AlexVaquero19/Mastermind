package listaespera;

public class Identificador {
    
    String[] array = {"W","Z","R","X","S","A","T","C","E","J"};
    
    public String id() {  
        int n = (int) (Math.random()*1000);
        n = n%10;
        String s = array[n];
        s = s.concat(String.valueOf(n));

        n = (int) (Math.random()*1000);
        n = n%10;
        s = s.concat(array[n]);
        s = s.concat(String.valueOf(n));  
        
        return s;
    }
}