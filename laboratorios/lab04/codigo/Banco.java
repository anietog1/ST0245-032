
/**
 * Class where the queues of people will be sorted in the correct order
 *
 * David Trefftz, Agustin Nieto
 * 1.0
 */
import java.util.LinkedList;
public class Banco
{
    public LinkedList<String> fila ;
    public LinkedList<String>[] filas ;
    public int cajeros ;

    /**
     * Simulates introducing every element of an array of queues in one of two cashiers.
     */
    public static void simular(LinkedList<String>[] filas){
        int i = 0 ;
        int cajero = 0 ;
        do{

            /**
             *  Checks if there is an item available in the selected queue and removes the first item of the queue.
             */
            
            if (!filas[i].isEmpty()){
                String s = filas[i].poll() ;
                System.out.println("El sujeto " + s + " está ubicado en el cajero " + (cajero+1));
                cajero ++;
                cajero%=2;
            }
            i++  ;
            i%=4 ;
        } while (clientela(filas)) ; 
    }

    /**
     * Checks if the array of queues still has any items left.
     */
    public static boolean clientela(LinkedList<String>[] filas){
        for(int i = 0 ; i< filas.length ; i++){
            if(!filas[i].isEmpty()){
                return true ;
            }
        }
        return false ;
    }
    
    public static void main (String [] args){
          LinkedList<String> [] filasClientes = new LinkedList[4];
        filasClientes[0] = new LinkedList<>();
        filasClientes[1] = new LinkedList<>();
        filasClientes[2] = new LinkedList<>();
        filasClientes[3] = new LinkedList<>();
        
        filasClientes[0].add("Abelardo");
        filasClientes[0].add("Alberto");
        
        filasClientes[1].add("Bernardo");
        filasClientes[1].add("Benedicto");
        filasClientes[1].add("Betania");
        filasClientes[1].add("Brujilda") ;
        
        filasClientes[2].add("Camilo");
        filasClientes[2].add("Cassidy");
        
        filasClientes[3].add("Danilo");
        simular(filasClientes) ;
    }
}
