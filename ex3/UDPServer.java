import java.net.*;
import java.util.ArrayList;

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
        
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        
        System.out.println("Recebendo Informações...");
        while (true){
            // Aguarda cliente solicitar conexão
            Socket connection = server.accept(); 
            
            // Cria uma nova thread para tratar a conexão aceita
            UDPServerThread lembrete = new UDPServerThread(connection, produtos);
            
            // Inicia a thread
            lembrete.start();
        } 
        
    }
}