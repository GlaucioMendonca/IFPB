/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Teobaldo
 */
public class Aluno extends Usuario{
    private String curso;
    
    public Aluno(String nome, String email, String senha, String c) {
        super(nome, email, senha);
        this.setPrazo(20);
        this.curso = c;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    
}
