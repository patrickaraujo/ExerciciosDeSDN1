import java.net.*;

/**
 * 
 *
 */
public class UDPServer
{
    public static void main( String[] args ) throws Exception
    {
    	InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("IP Address:- " + inetAddress.getHostAddress());
        
        ServerSocket server = new ServerSocket(8765);
        
        System.out.println("Recebendo Informações...");
        while (true){
            // Aguarda cliente solicitar conexão
            Socket connection = server.accept(); 
            
            // Cria uma nova thread para tratar a conexão aceita
            UDPServerThread lembrete = new UDPServerThread(connection);
            
            // Inicia a thread
            lembrete.start();
        } 
        
    }
}