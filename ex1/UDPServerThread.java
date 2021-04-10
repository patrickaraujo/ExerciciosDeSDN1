import java.io.DataInputStream;
import java.io.DataOutputStream;
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
            
            // Obtenção dos canais de entrada e saída da conexão
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            // Obtenção dos parâmetros para o processo de envio de lembretes
            int arr[] = new int[3]; 
            int n1 = in.readInt();
            int n2 = in.readInt();
            int n3 = in.readInt();
            arr[0] = n1;
            arr[1] = n2;
            arr[2] = n3;
            
            if(n1>=0) {
	            System.out.println("Números\n\nPrimeiro Número:\t"+n1+"\nSegundo Número:\t"+n2+"\nTerceiro Número:\t"+n3);	            
	            
	            int menor = getMin(arr);
	            int maior = getMax(arr);
	            out.writeUTF("Números\n\nMenor Número:\t"+menor+"\nMaior Número:\t"+maior);
	            
	            // Envio dos lembretes para o cliente que requisitou
	//            for (int i = 1; i <= total; i++){
	//                
	//                Thread.sleep(1000*intervalo);
	//            }
            }
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