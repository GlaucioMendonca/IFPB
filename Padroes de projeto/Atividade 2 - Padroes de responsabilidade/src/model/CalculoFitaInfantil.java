package model;

public class CalculoFitaInfantil implements CalculaValorAluguel{

	@Override
	public Double calcular(int dias) {
		double valor = 0.0;
		valor += 1.5;
		if(dias > 3) {
			valor += (dias - 3) * 1.5;
		}
		return valor;
	}

}
