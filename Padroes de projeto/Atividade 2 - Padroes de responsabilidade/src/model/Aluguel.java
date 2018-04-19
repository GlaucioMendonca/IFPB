package model;

public class Aluguel {
	private Fita fita;
	private int diasAlugada;

	public Aluguel(Fita fita, int diasAlugada) {
		this.fita = fita;
		this.diasAlugada = diasAlugada;
	}

	public Fita getFita() {
		return fita;
	}

	public int getDiasAlugada() {
		return diasAlugada;
	}

	public Double getValorAluguel (){
		double valor = 0;
		CalculaValorAluguel calculo;
		
		if (this.fita.getCódigoDePreço() == Fita.NORMAL){
			calculo = new CalculoFitaNormal();
			valor = calculo.calcular(diasAlugada);
		}
		

		if (this.fita.getCódigoDePreço() == Fita.LANÇAMENTO){
			calculo = new CalculoFitaLancamento();
			valor = calculo.calcular(diasAlugada);
		}
		
		
		if (this.fita.getCódigoDePreço() == Fita.INFANTIL){
			calculo = new CalculoFitaInfantil();
			valor = calculo.calcular(diasAlugada);
		}
		
		return valor;
    
	}

}
