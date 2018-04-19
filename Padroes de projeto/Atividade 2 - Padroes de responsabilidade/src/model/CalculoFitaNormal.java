package model;

public class CalculoFitaNormal implements CalculaValorAluguel{

	@Override
	public Double calcular(int dias) {
		double valor = 0.0;
		valor += 2;
		if(dias > 2) {
			valor += (dias - 2) * 1.5;
		}
		return valor;
	}
	
	
}
