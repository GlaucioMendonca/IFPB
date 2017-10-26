package modelo;

import java.util.ArrayList;

public class Livro {
	
	private String titulo;
	private int quantidade;
	private ArrayList<Autor> autores = new ArrayList<Autor>();
    private ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
        
    private String nomeAutor(ArrayList<Autor> autores)
        {
            String texto = "";
            for(Autor autor:autores)
            {
                texto+=' '+ autor.getNome()+'.';
            }
            return texto;
        }
	public Livro(String titulo, int quantidade) {
		this.titulo = titulo;
		this.quantidade = quantidade;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public void adicionarAutor(Autor autor) {
		autores.add(autor);
	}
	
	public void removerAutor(Autor autor) {
		// Criar o código
	}
	
	public ArrayList<Autor> getAutores() {
		return autores;
	}

        public void setEmprestimos(ArrayList<Emprestimo> emprestimos) {
            this.emprestimos = emprestimos;
        }

        public ArrayList<Emprestimo> getEmprestimos() {
            return emprestimos;
        }
        public void adicionaEmprestimo(Emprestimo e)
        {
            this.emprestimos.add(e);
        }
        public boolean removerEmprestimo(Emprestimo e)
        {
            return this.emprestimos.remove(e);
        }

    @Override
    public String toString() {
        return "Livro{" + "titulo=" + titulo + ", quantidade=" + quantidade + ", autores=" + nomeAutor(autores) + '}';
    }
	
}
