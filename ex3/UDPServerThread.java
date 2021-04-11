import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * 
 *
 */
public class UDPServerThread extends Thread {
	private Socket socket;
	private ArrayList<Produto> produtos;

	public UDPServerThread(Socket socket, ArrayList<Produto> produtos) {
		this.socket = socket;
		this.produtos = produtos;
	}

	public void run() {
		try {
			System.out.println("Recebendo dados...");
			// Obtenção dos canais de entrada e saída da conexão
			
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			// Obtenção dos parâmetros para o processo de envio de lembretes
			
			Produto promo = (Produto) in.readObject();
			//	Produto promo = new Produto("Detergente", 20);
			System.out.println();
			
			String fim = "TERMINAR";
			
			if (!(promo.getNome().toUpperCase().equals(fim))) {
				String saida = "";
				boolean encontrou = false;
				for (Produto element : produtos) {
					if (element.getNome().contains(promo.getNome())) {
						encontrou = true;
						int qtd = element.getQuantidade();
						qtd += promo.getQuantidade();
						if (qtd >= 0) {
							element.quantidade = qtd;
							saida = "Estoque do Produto " + "\"" + element.getNome() + "\"" + " atualizado.\nQuantidade:\t" + element.getQuantidade();
						} else
							saida = "Não é possível fazer a saída de estoque.\nQuantidade menor que o valor desejado.\nProduto:\t"+element.getNome()+"\nQuantidade:\t" + element.getQuantidade();
					}
				}
				if (!encontrou){
					if (promo.quantidade > 0) {
						produtos.add(promo);
						saida = "Produto " + "\"" + promo.getNome() + "\""+ " cadastrado.\nQuantidade:\t" + promo.getQuantidade();
					}
					else
						saida = "Produto " + "\"" + promo.getNome() + "\""+ " inexistente!";
				}
				out.writeUTF(saida);
			}

			System.out.println("FIM!");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}