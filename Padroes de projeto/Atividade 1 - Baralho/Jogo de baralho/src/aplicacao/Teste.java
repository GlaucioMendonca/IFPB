package aplicacao;

import java.util.ArrayList;

import model.AlgAleatorio;
import model.Algoritimo;
import model.Baralho;
import model.Carta;

public class Teste {

	public static void main(String[] args) {

		Baralho b = new Baralho();
		
		print(b.embaralhar());
		System.out.println("\n Mão \n");
		Algoritimo alg = new AlgAleatorio();
		print(alg.distribuirCartas(b.getBaralhoEmbaralhado()));

	}

	public static void print(ArrayList<Carta> baralho) {
		for (Carta a : baralho) {

			switch (a.getNumero()) {

			case 1:
				System.out.println("Carta [nipe=" + a.getNipe() + ", numero=A" + " valor=" + a.getValor() + "]");
				break;
			case 11:
				System.out.println("Carta [nipe=" + a.getNipe() + ", numero=Q" + " valor=" + a.getValor() + "]");
				break;
			case 12:
				System.out.println("Carta [nipe=" + a.getNipe() + ", numero=J" + " valor=" + a.getValor() + "]");
				break;
			case 13:
				System.out.println("Carta [nipe=" + a.getNipe() + ", numero=K" + " valor=" + a.getValor() + "]");
				break;
			default:
				System.out.println(a);
			}
		}
	}

}
