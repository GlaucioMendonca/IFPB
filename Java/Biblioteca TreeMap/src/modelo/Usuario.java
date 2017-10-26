package modelo;

import java.util.ArrayList;

public abstract class Usuario implements Comparable <Usuario> {
	
	private String nome;
	private String email;
	private String senha;
    private int prazo;
	private ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();

	public Usuario(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

    public int getPrazo() {
    	return prazo;
    }

    public void setPrazo(int prazo) {
    	this.prazo = prazo;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void addEmprestimo(Emprestimo emp) {
		emprestimos.add(emp);
	}
	
	public void removerEmprestimo(Emprestimo emp) {
		emprestimos.remove(emp);
	}
	public ArrayList<Emprestimo> getEmprestimos (){
		return emprestimos;
	}
	
	public int compareTo (Usuario outro) {
		if(this.nome.compareTo(outro.getNome()) > 0)
			return 1;
		
		if(this.nome.compareTo(outro.getNome()) < 0)
			return -1;
		
		return 0;
	}

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", email=" + email + ", senha=" + senha + ", prazo=" + prazo + ", emprestimos=" + emprestimos + '}';
    }

}
