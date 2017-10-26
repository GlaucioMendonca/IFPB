package aplicacao;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class TelaListarLivros extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */	
	
	public void CloseFrame(){
	    super.dispose();
	}
	/**
	 * Create the frame.
	 */
	public TelaListarLivros() {
		
				
		
		super.setTitle("Livros");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 503, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Listar Todos os Livros");
		btnNewButton.setBounds(63, 367, 181, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sair");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CloseFrame();
			}
		});
		btnNewButton_1.setBounds(302, 367, 89, 23);
		contentPane.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setToolTipText("Digite o titulo do livro");
		textField.setBounds(62, 276, 191, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("Digite o nome do autor do livro");
		textField_1.setBounds(62, 321, 191, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Buscar");
		btnNewButton_2.setBounds(302, 275, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Buscar");
		btnNewButton_3.setBounds(302, 320, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JLabel lblBuscarPorTitulo = new JLabel("Buscar por titulo");
		lblBuscarPorTitulo.setBounds(62, 260, 100, 14);
		contentPane.add(lblBuscarPorTitulo);
		
		JLabel lblBuscarPorAutor = new JLabel("Buscar por autor");
		lblBuscarPorAutor.setBounds(62, 307, 100, 14);
		contentPane.add(lblBuscarPorAutor);		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(63, 11, 386, 238);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] array = Fachada.listarLivros().toString().split("},");
				textArea.setText("Lista de livros disponiveis:");
				for (String l: array){
					textArea.setText(textArea.getText()+"\n"+l.trim());
				}
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					textArea.setText(Fachada.busca(textField.getText()).toString());
				}catch (Exception e2){
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
				
			}
		});
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String[] array = Fachada.buscaPorAutor(textField_1.getText()).toString().split("},");
					textArea.setText("Livros do autor: "+textField_1.getText());
					for (String l: array){
						textArea.setText(textArea.getText()+"\n"+l.trim());
					}
				}catch (Exception e2){
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
				
			}
		});
		
	}
	
	
}
