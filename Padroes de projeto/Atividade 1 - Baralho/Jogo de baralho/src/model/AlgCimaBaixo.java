package model;

import java.util.ArrayList;

public class AlgCimaBaixo implements Algoritimo {

	@Override
	public ArrayList<Carta> distribuirCartas(ArrayList<Carta> baralho) {
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		cartas.add(baralho.get(0));
		cartas.add(baralho.get(baralho.size()-1));
		return cartas;
	}

}
