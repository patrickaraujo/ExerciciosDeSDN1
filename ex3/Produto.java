
public class Produto implements java.io.Serializable {

	String nome;
	int quantidade;

    public Produto(String nome, int quantidade) {	//	ledados
        this.nome = nome;
        this.quantidade = quantidade;
    }
    
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
