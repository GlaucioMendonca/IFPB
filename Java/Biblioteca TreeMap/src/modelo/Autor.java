package modelo;

import java.util.ArrayList;

public class Autor {
	
	private String nome;
	private ArrayList<Livro> livros = new ArrayList<Livro>();

	public Autor(String nome) {
		this.nome = nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void addLivro(Livro livro) {
		livros.add(livro);
	}
	
	public void removerLivro(Livro livro) {
		// Criar código
	}
	
	public ArrayList<Livro> getLivros() {
		return livros;
	}

}
