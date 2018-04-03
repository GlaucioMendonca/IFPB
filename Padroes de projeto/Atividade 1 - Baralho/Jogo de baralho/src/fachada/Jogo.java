package fachada;

import java.util.ArrayList;

import model.Baralho;
import model.Carta;
import model.Jogador;

public class Jogo {

	private Jogador j1;
	private Jogador j2;
	private Baralho baralho = new Baralho();

	private ArrayList<Carta> maoJ1 = new ArrayList<>();
	private ArrayList<Carta> maoJ2 = new ArrayList<>();

	public Jogo() {
		this.baralho.embaralhar();
	}

	public Jogador getJ1() {
		return j1;
	}

	public void setJ1(Jogador j1) {
		this.j1 = j1;
	}

	public Jogador getJ2() {
		return j2;
	}

	public void setJ2(Jogador j2) {
		this.j2 = j2;
	}

	public ArrayList<Carta> getMaoJ1() {
		return maoJ1;
	}

	public void setMaoJ1(ArrayList<Carta> maoJ1) {
		for (Carta c : maoJ1) {
			this.maoJ1.add(c);
		}
	}

	public ArrayList<Carta> getMaoJ2() {
		return maoJ2;
	}

	public void setMaoJ2(ArrayList<Carta> maoJ2) {
		for (Carta c : maoJ2) {
			this.maoJ2.add(c);
		}
	}

	public Baralho getBaralho() {
		return baralho;
	}

}
