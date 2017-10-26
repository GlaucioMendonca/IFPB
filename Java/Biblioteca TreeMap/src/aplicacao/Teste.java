/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;
import fachada.Fachada;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.*;
import java.util.ArrayList;
/**
 *
 * @author Teobaldo
 */
public class Teste {
    public static void main(String[] args)
    {
        
        ArrayList <String> autores = new ArrayList<String>(); 
        try {
          /*  Fachada.cadastrarUsuario("Victor","Victor@","1");
            Fachada.cadastrarUsuario("Galucio","Galucio@","1");
            Fachada.cadastrarUsuario("Josefa","Foseja@","1");
            Fachada.cadastrarUsuario("Motorista","Motorista@","1");
            Fachada.cadastrarUsuario("Admin","Admin@","1");*/
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "cadastro incorreto !");
        }
        
        autores.add("Paulo");
        autores.add("João");
        
        try {            
            Fachada.cadastrarLivro("Livro", autores, 2);
            Fachada.cadastrarLivro("Livro2", autores, 2);
            Fachada.cadastrarLivro("EOQ", autores,1);
        } catch (Exception ex) {
            System.out.println(ex);
        }        
        System.out.println("LISTAR LIVROS");
        for(Livro livro : Fachada.listarLivros())
        {
            System.out.println(livro.toString());          
        }
        System.out.println("LISTAR LIVROS BUSCANDO POR NOME");
        try
        {
            System.out.println(Fachada.busca("Liv").toString());
    
        }catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println("LISTAR LIVROS BUSCANDO POR NOME DO AUTOR");
        try
        {
            for(Livro livro:Fachada.buscaPorAutor("Paulo"))
                System.out.println(livro.toString());
    
        }catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println("CRIAR EMPRESTIMO");
        try {
            Fachada.login("Victor@","1");
            Fachada.criarEmprestimo("EOQ");
            Fachada.logoff();
            Fachada.login("Galucio@","1");
            Fachada.criarEmprestimo("Livro");
        } catch (Exception ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("LISTAR EMPRESTIMOS");
        try {
            for(Emprestimo e : Fachada.listarEmprestimos())
            {
                System.out.println(e.toString());
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println("LISTAR EMPRESTIMOS DO USUARIO LOGADO");
        try {
            for(Emprestimo e : Fachada.listarMeusEmprestimos())
            {
                System.out.println(e.toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("LISTAR LIVROS");
        for(Livro livro : Fachada.listarLivros())
        {
            System.out.println(livro.toString());          
        }
        try {
            Fachada.criarDevolucao(2);
        } catch (Exception ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("LISTAR EMPRESTIMOS DO USUARIO LOGADO");
        try {
            for(Emprestimo e : Fachada.listarMeusEmprestimos())
            {
                System.out.println(e.toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
        Emprestimo e = Fachada.listarEmprestimos().get(0);
        LocalDate data = LocalDate.parse("2017-08-18");
        e.setDatadev(data);
       // e.calculaMulta();
        System.out.println(e.toString());
        
     }         
}
