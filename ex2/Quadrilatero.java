
public class Quadrilatero implements java.io.Serializable {
	
	int l1;
	int l2;
	int l3;
	int l4;
	String tipoObjeto;

    public Quadrilatero(int l1, int l2, int l3, int l4) {	//	ledados
        leDados(l1 ,l2, l3, l4);
        //	indicaTipoQuadrilatero(l1, l2, l3, l4);
    }
	
    public void leDados(int l1, int l2, int l3, int l4) {	//	ledados
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
        this.l4 = l4;
    }
    
    public void indicaTipoQuadrilatero(int a, int b, int c, int d) {
    	
    	if((a==b) && (a==c) && (a==d) && (b==c) && (b==d) && (c==d))
    		this.tipoObjeto = "Quadrado";
    	else if (a == b && c == d)
    		this.tipoObjeto = "Retângulo";
        else if (a == d && c == b)
        	this.tipoObjeto = "Retângulo";
        else if (a == c && d == b)
        	this.tipoObjeto = "Retângulo";
        else
        	this.tipoObjeto = "Quadrilátero";
    	
    }
    
    public String mostraDados() {
    	String retorno = "Dados do objeto:\n\nLado 1:\t"+l1+"\nLado 2:\t"+l2+"\nLado 3:\t"+l3+"\nLado 4:\t"+l4+"\nTipo do Quadrilátero:\t"+tipoObjeto;
    	return retorno;
    }

	public int getL1() {
		return l1;
	}

	public void setL1(int l1) {
		this.l1 = l1;
	}

	public int getL2() {
		return l2;
	}

	public void setL2(int l2) {
		this.l2 = l2;
	}

	public int getL3() {
		return l3;
	}

	public void setL3(int l3) {
		this.l3 = l3;
	}

	public int getL4() {
		return l4;
	}

	public void setL4(int l4) {
		this.l4 = l4;
	}

	public String getTipoObjeto() {
		return tipoObjeto;
	}

	public void setTipoObjeto(String tipoObjeto) {
		this.tipoObjeto = tipoObjeto;
	}
    
    
    
}
