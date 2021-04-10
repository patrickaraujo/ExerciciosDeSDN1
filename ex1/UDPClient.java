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
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        // Define os parâmetros para o servidor de lembretes
        int n1 = 10;
        int n2 = 20;
        int n3 = 30;

        // Envia os parâmetros para o servidor de lembretes
        out.writeInt(n1);
        out.writeInt(n2);
        out.writeInt(n3);
        
        // Recebe os lembretes do servidor
//        for (int i = 0; i<total; i++){
//            // Aguarda servidor responder com o lembrete e o captura
//            String msg = in.readUTF();
//            // Exibe o lembrete 
//            System.out.println(msg);
//        }
        String msg = in.readUTF();
        System.out.println(msg);
        
 
    }
}
