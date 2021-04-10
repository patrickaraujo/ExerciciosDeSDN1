import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 
 *
 */
public class UDPServerThread extends Thread
{
    private Socket socket;
    
    public UDPServerThread(Socket socket)
    {
        this.socket = socket;
    }
    
    public void run(){
        try{
        	System.out.println("Recebendo dados...");
            // Obtenção dos canais de entrada e saída da conexão
        	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        	System.out.println("Passou");
        	ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            
            // Obtenção dos parâmetros para o processo de envio de lembretes
            
            Quadrilatero promo = (Quadrilatero) in.readObject();
            
            promo.indicaTipoQuadrilatero(promo.getL1(), promo.getL2(), promo.getL3(), promo.getL4());
            
            out.writeObject(promo);
            
            System.out.println("FIM!");            
        } catch(Exception e){System.out.println(e);} 
    }
    
 // Method for getting the maximum value
    public static int getMax(int[] inputArray){ 
      int maxValue = inputArray[0]; 
      for(int i=1;i < inputArray.length;i++){ 
        if(inputArray[i] > maxValue){ 
           maxValue = inputArray[i]; 
        } 
      } 
      return maxValue; 
    }
   
    // Method for getting the minimum value
    public static int getMin(int[] inputArray){ 
      int minValue = inputArray[0]; 
      for(int i=1;i<inputArray.length;i++){ 
        if(inputArray[i] < minValue){ 
          minValue = inputArray[i]; 
        } 
      } 
      return minValue; 
    } 
}