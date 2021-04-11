import java.net.*;
import java.io.*;

/**
 * 
 *
 */
public class UDPClient 
{
    public static void main( String[] args ) throws Exception
    {
        // Estabelece conexão com o servidor de lembretes
    	InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("IP Address:- " + inetAddress.getHostAddress());
        
        Socket socket = new Socket("192.168.0.209", 8765);
        
        // Obtem os canais de entrada e saída da conexão
        
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Enviando!");
        DataInputStream in = new DataInputStream(socket.getInputStream());
        

        // Define os parâmetros para o servidor de lembretes
        Produto novo = new Produto("Detergente", 30);
        
        // Envia os parâmetros para o servidor de lembretes
        
        out.writeObject(novo);
        
        // Recebe os lembretes do servidor
//        for (int i = 0; i<total; i++){
//            // Aguarda servidor responder com o lembrete e o captura
//            String msg = in.readUTF();
//            // Exibe o lembrete 
//            System.out.println(msg);
//        }
        
        String msg = in.readUTF();
        System.out.println(msg);
        System.out.println("FIM!");
        
 
    }
}