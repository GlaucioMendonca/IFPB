package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
public class Emprestimo {
	
	private int id;
	private Usuario usuario;
	private Livro livro;
	private LocalDate dataemp;
	private LocalDate datadev = null;
	private double multa;
        
	public Emprestimo(int id, Usuario usuario, Livro livro) {
		this.usuario = usuario;
		this.livro = livro;
		this.dataemp = LocalDate.now();
                this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public LocalDate getDataemp() {
		return dataemp;
	}

	public void setDataemp(LocalDate dataemp) {
		this.dataemp = dataemp;
	}

	public LocalDate getDatadev() {
		return datadev;
	}

	public void setDatadev(LocalDate datadev) {
		this.datadev = datadev;
	}

	public double getMulta() {
		return multa;
	}
       
	public void setMulta(double multa) {
		this.multa = multa;
	}
                       
        

         @Override
        public String toString() {
            return "Emprestimo "+"id=" + id + ", usuario=" + usuario.getNome() + ", livro=" + livro.getTitulo() + ", dataemp=" + dataemp + ", datadev=" + datadev + ", multa=" + multa;
      }
        
}
