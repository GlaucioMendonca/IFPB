package model;

import java.util.ArrayList;

public class AlgPrimeiras implements Algoritimo {

	@Override
	public ArrayList<Carta> distribuirCartas(ArrayList<Carta> baralho) {
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		
		cartas.add(baralho.get(0));
		cartas.add(baralho.get(1));
		
		return cartas;
	}

}
