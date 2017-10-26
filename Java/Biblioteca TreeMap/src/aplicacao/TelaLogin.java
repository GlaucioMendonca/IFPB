package aplicacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.sun.glass.events.KeyEvent;

import fachada.Fachada;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JLabel lblEmail;
	private JLabel lblSenha;
	private JButton btnEntrar;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 199, 172);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 24, 46, 14);
		contentPane.add(lblEmail);

		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(10, 55, 46, 14);
		contentPane.add(lblSenha);

		textField_1 = new JTextField();
		textField_1.setBounds(72, 21, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		btnEntrar = new JButton("Logar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String email = textField_1.getText();
					String senha = new String(passwordField.getPassword());

					Fachada.login(email, senha);
					JOptionPane.showMessageDialog(null,"Pessoa logada no momento: "+
							Fachada.getLogado().getNome()+"\n");
									
					dispose();
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnEntrar.setBounds(52, 96, 86, 23);
		contentPane.add(btnEntrar);
		contentPane.getRootPane().setDefaultButton(btnEntrar);
		passwordField = new JPasswordField();
		passwordField.setBounds(72, 52, 86, 20);
		contentPane.add(passwordField);
	}
}
