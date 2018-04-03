package model;

import java.util.ArrayList;
import java.util.Random;

public class AlgAleatorio implements Algoritimo {

	@Override
	public ArrayList<Carta> distribuirCartas(ArrayList<Carta> baralho) {
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		
		int rdm;
		
		Random r = new Random();
		rdm = r.nextInt(baralho.size());
		cartas.add(baralho.get(rdm));
		baralho.remove(baralho.get(rdm));
		
		rdm = r.nextInt(baralho.size());
		cartas.add(baralho.get(rdm));
		baralho.remove(baralho.get(rdm));
		
		return cartas;
	}
	

}
