package model;

public class CalculoFitaLancamento implements CalculaValorAluguel{

	@Override
	public Double calcular(int dias) {
		double valor = 0.0;
		valor += dias * 3;
		return valor;
	}


}
