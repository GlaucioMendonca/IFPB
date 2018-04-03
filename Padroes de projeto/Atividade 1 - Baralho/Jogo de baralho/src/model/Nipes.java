package model;

public enum Nipes {
	
	OURO(1), COPAS(2), ESPADA(3), PAUS(4);
	
	private int valor;
	
	private Nipes (int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}
	
}
