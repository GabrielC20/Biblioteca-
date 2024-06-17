package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPrincipal frame = new JPrincipal();
					frame.setLocationRelativeTo(null);
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
	public JPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 834, 490);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(221, 221, 221));
		panel.setBounds(0, 0, 818, 86);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bliblioteca ");
		lblNewLabel.setForeground(new Color(40, 40, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(JPrincipal.class.getResource("/img/book.png")));
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblNewLabel.setBounds(343, 30, 132, 25);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 86, 818, 365);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Cadastrar cliente");
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JCliente jCliente = new JCliente(null);
		        jCliente.setLocationRelativeTo(jCliente);
		        jCliente.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		        jCliente.setVisible(true);
		    }
		});

		btnNewButton.setBounds(10, 88, 213, 210);
		btnNewButton.setIcon(new ImageIcon(JPrincipal.class.getResource("/img/user.png")));
		btnNewButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		panel_1.add(btnNewButton);
		
		JButton btnCadastrarLivro = new JButton("Cadastrar Autor ");
		btnCadastrarLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JAutor jAutor = new JAutor();
				jAutor.setLocationRelativeTo(jAutor);
				jAutor.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				jAutor.setVisible(true);
			}
		});
		btnCadastrarLivro.setIcon(new ImageIcon(JPrincipal.class.getResource("/img/writer.png")));
		btnCadastrarLivro.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btnCadastrarLivro.setBounds(439, 88, 175, 210);
		panel_1.add(btnCadastrarLivro);
		
		JButton btnCadastrarLivro_1 = new JButton("Cadastrar Livro");
		btnCadastrarLivro_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLivro jLivro = new JLivro();
				jLivro.setLocationRelativeTo(jLivro);
				jLivro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				jLivro.setVisible(true);
			}
		});
		btnCadastrarLivro_1.setBounds(233, 88, 196, 210);
		btnCadastrarLivro_1.setIcon(new ImageIcon(JPrincipal.class.getResource("/img/book.png")));
		btnCadastrarLivro_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		panel_1.add(btnCadastrarLivro_1);
		
		JButton btnRealizarEmprestimo = new JButton("Realizar Emprestimo");
		btnRealizarEmprestimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JEmprestimo jEmprestimo = new JEmprestimo();
				jEmprestimo.setLocationRelativeTo(jEmprestimo);
				jEmprestimo.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				jEmprestimo.setVisible(true);
			}
		});
		btnRealizarEmprestimo.setIcon(new ImageIcon(JPrincipal.class.getResource("/img/signing.png")));
		btnRealizarEmprestimo.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btnRealizarEmprestimo.setBounds(624, 87, 175, 213);
		panel_1.add(btnRealizarEmprestimo);
	}
}
