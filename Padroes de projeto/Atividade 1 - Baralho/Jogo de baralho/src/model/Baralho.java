package model;

import java.util.ArrayList;
import java.util.Collections;

public class Baralho {
	
	private ArrayList<Carta> baralho = new ArrayList <Carta>();
	private ArrayList<Carta> baralhoEmbaralhado = new ArrayList <Carta>();
	
	public Baralho () {
		inicializar();
	}
	
	public void inicializar() {
		Carta carta;
		int i = 2;
		
		while(i<=10) {
			
			carta = new Carta("OURO",i);
			baralho.add(carta);
			
			carta = null;
			
			carta = new Carta("COPAS",i);
			baralho.add(carta);
			
			carta = null;
			
			carta = new Carta("ESPADA",i);
			baralho.add(carta);
			
			carta = null;
			
			carta = new Carta("PAUS",i);
			baralho.add(carta);
			
			carta = null;
			i++;
		}
		
		for(i=0; i<=3; i++) {
			
			String nipe = null;
			if(i == 0) nipe = "OURO";
			if(i == 1) nipe = "PAUS";
			if(i == 2) nipe = "ESPADA";
			if(i == 3) nipe = "COPAS";
			
			carta = new Carta(nipe);
			carta.setNumero("A");
			baralho.add(carta);
			carta = null;
			
			carta = new Carta(nipe);
			carta.setNumero("Q");
			baralho.add(carta);
			carta = null;
			
			carta = new Carta(nipe);
			carta.setNumero("J");
			baralho.add(carta);
			carta = null;
			
			carta = new Carta(nipe);
			carta.setNumero("K");
			baralho.add(carta);
			carta = null;
		}
		
	}

	public ArrayList<Carta> getBaralho() {
		return baralho;
	}
	
	public ArrayList<Carta> getBaralhoEmbaralhado() {
		return baralhoEmbaralhado;
	}

	public ArrayList<Carta> embaralhar () {
		this.baralhoEmbaralhado = this.baralho;
		Collections.shuffle(this.baralhoEmbaralhado);
		
		return baralhoEmbaralhado;
	}
	
}
