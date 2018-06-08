package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.LivroDao;
import Model.LivroM;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({ "unused", "serial" })
public class Pessoaview extends JFrame {

	private JPanel contentPane;
	private JTextField campoID;
	private JTextField campoIdade;
	private JTextField campoNome;
	private JTextField campoEnd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pessoaview frame = new Pessoaview();
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
	public Pessoaview() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Identificador");
		lblNewLabel.setBounds(74, 44, 77, 17);
		contentPane.add(lblNewLabel);
		
		campoID = new JTextField();
		campoID.setBounds(152, 42, 46, 20);
		contentPane.add(campoID);
		campoID.setColumns(10);
		campoID.setText("LOCK");
		campoID.setEnabled(false);
		
		JLabel lblIdade = new JLabel("Ano");
		lblIdade.setBounds(243, 44, 34, 14);
		contentPane.add(lblIdade);
		
		campoIdade = new JTextField();
		campoIdade.setColumns(10);
		campoIdade.setBounds(279, 41, 46, 20);
		contentPane.add(campoIdade);
		
		JLabel lblNome = new JLabel("Autor");
		lblNome.setBounds(74, 89, 57, 20);
		contentPane.add(lblNome);
		
		campoNome = new JTextField();
		campoNome.setBounds(130, 89, 274, 20);
		contentPane.add(campoNome);
		campoNome.setColumns(10);
		
		JLabel lblEndereo = new JLabel("Titulo");
		lblEndereo.setBounds(74, 135, 57, 20);
		contentPane.add(lblEndereo);
		
		campoEnd = new JTextField();
		campoEnd.setColumns(10);
		campoEnd.setBounds(130, 135, 274, 20);
		contentPane.add(campoEnd);
		
		JLabel lblCpf = new JLabel("Genero");
		lblCpf.setBounds(74, 173, 57, 28);
		contentPane.add(lblCpf);
		
		final JFormattedTextField campoCpf = new JFormattedTextField();
		campoCpf.setBounds(130, 177, 124, 20);
		contentPane.add(campoCpf);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				campoIdade.setText("");
				campoNome.setText("");
				campoEnd.setText("");
				campoCpf.setText("");
				System.out.flush();
			}
		});
		btnLimpar.setBounds(74, 320, 89, 23);
		contentPane.add(btnLimpar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				
				if(campoIdade.getText().isEmpty() || campoNome.getText().isEmpty() || campoEnd.getText().isEmpty() || campoCpf.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Há campos não preenchidos", "Sistema", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					LivroM liv = new LivroM();

					liv.setAutor(campoNome.getText());
					liv.setTitulo(campoEnd.getText());
					liv.setGenero(campoCpf.getText());
				try {
				liv.setAno(Integer.parseInt(campoIdade.getText()));
				LivroDao.InserirPessoa(liv);
				JOptionPane.showMessageDialog(null, "Cadastrado com sucesso", "Sistema", JOptionPane.INFORMATION_MESSAGE);
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Falha no cadastro, caiu na Exception!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
						}
					}	
				}	
		});
		btnSalvar.setBounds(315, 320, 89, 23);
		contentPane.add(btnSalvar);
	}
}