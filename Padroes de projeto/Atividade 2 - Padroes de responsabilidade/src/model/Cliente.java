package model;

import java.util.*;

public class Cliente {

	private String nome;
	private Collection<Aluguel> fitasAlugadas = new ArrayList<Aluguel>();

	public Cliente(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void adicionaAluguel(Aluguel aluguel) {
		fitasAlugadas.add(aluguel);
	}

	public String extrato() {
		final String fimDeLinha = System.getProperty("line.separator");
		double valorTotal = 0.0;

		int pontosDeAlugadorFrequente = 0;

		String resultado = "Registro de Alugueis de " + getNome() + fimDeLinha;

		Iterator<Aluguel> alugueis = fitasAlugadas.iterator();

		while (alugueis.hasNext()) {

			double valorCorrente = 0.0;

			Aluguel cada = (Aluguel) alugueis.next();
			// determina valores para cada linha

			valorCorrente += cada.getValorAluguel();

			// trata de pontos de alugador frequente
			pontosDeAlugadorFrequente++;
			// adiciona bonus para aluguel de um lançamento por pelo menos 2
			// dias
			if (cada.getFita().getCódigoDePreço() == Fita.LANÇAMENTO && cada.getDiasAlugada() > 1) {
				pontosDeAlugadorFrequente++;
			}

			// mostra valores para este aluguel
			resultado += "\t" + cada.getFita().getTítulo() + "\t" + valorCorrente + fimDeLinha;
			valorTotal += valorCorrente;
		} // while

		// adiciona rodapé
		resultado += "Valor total devido: " + valorTotal + fimDeLinha;
		resultado += "Voce acumulou " + pontosDeAlugadorFrequente + " pontos de alugador frequente";
		return resultado;
	}
}
