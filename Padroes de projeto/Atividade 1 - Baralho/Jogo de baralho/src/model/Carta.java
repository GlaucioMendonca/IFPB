package model;

public class Carta {

	private Nipes nipe;
	// Numero da carta de (A) até (K)
	private int numero;
	// Soma do numero com o valor do nipe
	private int valor;

	public Carta(String nipe, int numero) {
		this.nipe = verificarNipe(nipe);
		this.numero = numero;
		this.valor = numero + this.nipe.getValor();
	}

	public Carta(String nipe) {
		this.nipe = verificarNipe(nipe);
	}
	
	public Nipes getNipe() {
		return nipe;
	}

	public void setNipe(Nipes nipe) {
		this.nipe = nipe;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		if (numero.equals("A")) this.numero = ValorCarta.A.getValor();
		if (numero.equals("Q")) this.numero = ValorCarta.Q.getValor();
		if (numero.equals("K")) this.numero = ValorCarta.K.getValor();
		if (numero.equals("J")) this.numero = ValorCarta.J.getValor();
		
		this.valor = this.numero + this.nipe.getValor();
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	private Nipes verificarNipe(String n) {

		Nipes aux = null;

		if (n.equals("OURO"))
			return aux = Nipes.OURO;
		if (n.equals("COPAS"))
			return aux = Nipes.COPAS;
		if (n.equals("PAUS"))
			return aux = Nipes.PAUS;
		if (n.equals("ESPADA"))
			return aux = Nipes.ESPADA;

		return aux;
	}
	
	@Override
	public String toString() {
		return "Carta [nipe=" + nipe + ", numero=" + numero + ", valor=" + valor + "]";
	}
}
