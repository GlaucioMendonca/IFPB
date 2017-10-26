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
public class Funcionario extends Usuario{
    private String departamento;

    public Funcionario(String nome, String email, String senha, String d) {
        super(nome, email, senha);
        this.setPrazo(30);
        this.departamento = d;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

   
}
