package model;

public enum ValorCarta {
	A(1), Q(11), J(12), K(13);
	
	private int valor;
	
	private ValorCarta (int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}
}
