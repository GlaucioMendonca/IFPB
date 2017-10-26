package aplicacao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import fachada.Fachada;
import modelo.Autor;
import modelo.Livro;

public class TelaLivros extends JFrame implements MouseListener{

	private JPanel contentPane;
	private JTable table;
	private JButton btnListarTodos;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLivros frame = new TelaLivros();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLivros() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 198, 424, 199);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Titulo", "Quantidade", "Autores"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(true);
		table.getColumnModel().getColumn(1).setResizable(true);
		table.getColumnModel().getColumn(2).setResizable(true);
		table.getColumnModel().getColumn(2).setPreferredWidth(228);
		scrollPane.setViewportView(table);
		
		DefaultTableModel tabModel = (DefaultTableModel) table.getModel(); // alimentador da tabela
		
		btnListarTodos = new JButton("Listar Todos");
		btnListarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String autores = ""; 
				
				if (tabModel.getRowCount() > 0){ 		// verifica se a tabela tem algum conteúdo
		            while (tabModel.getRowCount()>0){ 	// Enquanto tiver algo
		                tabModel.removeRow(0);			// remova o 1º item
		            }         
		        }
				
				for(Livro l: Fachada.listarLivros()){
					for(Autor a: l.getAutores()){
						autores = autores + a.getNome()+". ";
					}
					
					tabModel.addRow(new String [] {l.getTitulo(),Integer.toString(l.getQuantidade()),autores});
					autores = "";
				}
			}
		});
		btnListarTodos.setBounds(10, 164, 150, 23);
		contentPane.add(btnListarTodos);
		
		textField = new JTextField();
		textField.setBounds(10, 42, 236, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 94, 236, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblBuscarPorTitulo = new JLabel("Buscar por titulo do livro");
		lblBuscarPorTitulo.setBounds(10, 23, 150, 20);
		contentPane.add(lblBuscarPorTitulo);
		
		JLabel lblBuscarPorNome = new JLabel("Buscar por nome do autor");
		lblBuscarPorNome.setBounds(10, 71, 150, 23);
		contentPane.add(lblBuscarPorNome);
		
		JButton btnBuscar = new JButton("Buscar"); //busca por titulo
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().equals("")){
					JOptionPane.showMessageDialog(null,"Você não digitou nada!");
				}else{
					String autores = "";
					try{
						if (tabModel.getRowCount() > 0){ 		// verifica se a tabela tem algum conteúdo
				            while (tabModel.getRowCount()>0){ 	// Enquanto tiver algo
				                tabModel.removeRow(0);			// remova o 1º item
				            }         
				        }
						for(Livro l: Fachada.busca(textField.getText())){
							for(Autor a: l.getAutores()){
								autores = autores + a.getNome()+". ";
							}
							
							tabModel.addRow(new String [] {l.getTitulo(),Integer.toString(l.getQuantidade()),autores});
							autores = "";
						}
						textField.setText("");
						textField_1.setText("");
						
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			}
		});
		btnBuscar.setBounds(299, 41, 89, 23);
		contentPane.add(btnBuscar);
		
		JButton btnBuscar_1 = new JButton("Buscar"); // busca por nome do autor
		btnBuscar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_1.getText().equals("")){
					JOptionPane.showMessageDialog(null,"Você não digitou nada!");
				}else{
					try{
						String autores = "";
						
						if (tabModel.getRowCount() > 0){ 		// verifica se a tabela tem algum conteúdo
				            while (tabModel.getRowCount()>0){ 	// Enquanto tiver algo
				                tabModel.removeRow(0);			// remova o 1º item
				            }
						}
						
						for(Livro l: Fachada.buscaPorAutor(textField_1.getText())){
							for(Autor a: l.getAutores()){
								autores = autores + a.getNome()+". ";
							}
							tabModel.addRow(new String [] {l.getTitulo(),Integer.toString(l.getQuantidade()),autores});
							autores = "";
						}
						textField.setText("");
						textField_1.setText("");
						
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			}
		});
		
		table.addMouseListener(this);
		
		btnBuscar_1.setBounds(299, 93, 89, 23);
		contentPane.add(btnBuscar_1);
	}
	public void mouseClicked(MouseEvent event) {
		DefaultTableModel tabModel = (DefaultTableModel) table.getModel();
		JanelaLogado telaLogado = new JanelaLogado();
		TelaLogin login = new TelaLogin();
		 // Se houve dois cliques com o mouse...
		if (event.getClickCount() == 2) {
        	if(table.getSelectedRow()!= -1){
        		if(Fachada.getLogado()==null){ 
        			Object[] options = { "Logar", "Cancelar" };
        			int resp = (JOptionPane.showOptionDialog(null, "É Preciso estar logado para fazer emprestimos", "Informação", 
        						   JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]));
        			if (resp == 0) {
        				login.setVisible(true);
        				login.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        			}
        		}else{
                	telaLogado.setVisible(true);
                	telaLogado.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                	telaLogado.setLabel(tabModel.getValueAt(table.getSelectedRow(), 0).toString());
        			System.out.println(tabModel.getValueAt(table.getSelectedRow(), 0));
        		}
        	}
        }
	}// evento q sera executado caso o mouse click no label
	public void mouseEntered(MouseEvent arg0) {
		
	}// evento q sera executado caso o mouse entre no label
	public void mouseExited(MouseEvent arg0) {
		
	}// evento q sera executado caso o mouse saia do label
	public void mousePressed(MouseEvent arg0) {
		
	}// evento q sera executado caso o mouse seja pressionado no label
	public void mouseReleased(MouseEvent arg0) {
		
	}// evento q sera executado caso o mouse seja largado no label
	
}
