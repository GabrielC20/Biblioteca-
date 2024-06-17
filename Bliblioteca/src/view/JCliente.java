package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DAO.dao;
import model.Cliente;
import model.ModeloTabela;
import java.awt.Color;

public class JCliente extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textNome;
    private JTextField textCpf;
    private JTextField textdtNasimento;
    private JTextField textGenero;
    private JTextField textEmail;
    private JTable table;
    private JTextField textTel;
    private ArrayList<Cliente> clientes;
    private ModeloTabela modelotabela;
    private dao dao;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JCliente frame = new JCliente(null);
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
    public JCliente(Cliente clienteSelecionado) {
        dao = new dao();
        try {
            clientes = dao.listarClientes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 805, 490);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Nome");
        lblNewLabel.setBounds(10, 26, 45, 14);
        contentPane.add(lblNewLabel);

        textNome = new JTextField();
        textNome.setBounds(10, 40, 300, 20);
        contentPane.add(textNome);
        textNome.setColumns(10);

        JLabel lblCpf = new JLabel("CPF");
        lblCpf.setBounds(10, 71, 45, 14);
        contentPane.add(lblCpf);

        textCpf = new JTextField();
        textCpf.setColumns(10);
        textCpf.setBounds(10, 84, 300, 20);
        contentPane.add(textCpf);

        JLabel lblDataDeNscimento = new JLabel("Data de Nascimento");
        lblDataDeNscimento.setBounds(10, 115, 111, 14);
        contentPane.add(lblDataDeNscimento);

        textdtNasimento = new JTextField();
        textdtNasimento.setColumns(10);
        textdtNasimento.setBounds(10, 130, 300, 20);
        contentPane.add(textdtNasimento);

        JLabel lblGenero = new JLabel("Genero");
        lblGenero.setBounds(523, 26, 59, 14);
        contentPane.add(lblGenero);

        JLabel lblTelefone = new JLabel("Telefone");
        lblTelefone.setBounds(523, 71, 59, 14);
        contentPane.add(lblTelefone);

        textGenero = new JTextField();
        textGenero.setColumns(10);
        textGenero.setBounds(523, 40, 137, 20);
        contentPane.add(textGenero);

        JLabel lblEmail = new JLabel("E-mail");
        lblEmail.setBounds(523, 115, 59, 14);
        contentPane.add(lblEmail);

        textEmail = new JTextField();
        textEmail.setColumns(10);
        textEmail.setBounds(523, 130, 137, 20);
        contentPane.add(textEmail);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(177, 234, 434, 206);
        contentPane.add(scrollPane);

        modelotabela = new ModeloTabela(clientes);

        table = new JTable();
        table.setModel(modelotabela);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    try {
                        Cliente clienteSelecionado = dao.consultarCliente(modelotabela.getValueAt(table.getSelectedRow(), 0).toString());
                        preencherCampos(clienteSelecionado); 
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });




        table.getColumnModel().getColumn(3).setPreferredWidth(130);
        scrollPane.setViewportView(table);

        JButton btnNewButton = new JButton("Cadastrar");
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dao.cadastarCliente(new Cliente(null, textNome.getText(), textCpf.getText(), textdtNasimento.getText(), textGenero.getText(),
                textTel.getText(), textEmail.getText()));
                
                atualizarTabela();
            }
        });
        btnNewButton.setBounds(177, 200, 98, 23);
        contentPane.add(btnNewButton);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBackground(new Color(0, 128, 255));
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = table.getSelectedRow();
                if (linhaSelecionada != -1) {
       
                    Cliente clienteSelecionado = clientes.get(linhaSelecionada);
                    
        
                    String novoNome = textNome.getText();
                    String novoCpf = textCpf.getText();
                    String novoDtNascimento = textdtNasimento.getText();
                    String novoGenero = textGenero.getText();
                    String novoTelefone = textTel.getText();
                    String novoEmail = textEmail.getText();

       
                    dao.alterarCliente(clienteSelecionado.getId(), 
                        new Cliente(clienteSelecionado.getId(), novoNome, novoCpf, novoDtNascimento, novoGenero, novoTelefone, novoEmail));

                    atualizarTabela();
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um cliente para editar.", "Nenhum cliente selecionado", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnEditar.setBounds(350, 200, 89, 23);
        contentPane.add(btnEditar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBackground(new Color(241, 14, 14));
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = table.getSelectedRow();
                if (linhaSelecionada != -1) {
                    int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este cliente?", "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION);
                    if (confirmacao == JOptionPane.YES_OPTION) {
                        Cliente clienteSelecionado = clientes.get(linhaSelecionada);
                        dao.excluirCliente(clienteSelecionado.getId());
                        atualizarTabela();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um cliente para excluir.", "Nenhum cliente selecionado", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btnExcluir.setBounds(522, 200, 89, 23);
        contentPane.add(btnExcluir);

        textTel = new JTextField();
        textTel.setColumns(10);
        textTel.setBounds(523, 84, 137, 20);
        contentPane.add(textTel);
        
        if(clienteSelecionado!= null) {
        	preencherCampos(clienteSelecionado);
        }
    }
    
    private void preencherCampos(Cliente clienteSelecionado) {
        textNome.setText(clienteSelecionado.getNome());
        textCpf.setText(clienteSelecionado.getCpf());
        textdtNasimento.setText(clienteSelecionado.getDtNasimento());
        textGenero.setText(clienteSelecionado.getgenero());
        textTel.setText(clienteSelecionado.getTelefone());
        textEmail.setText(clienteSelecionado.getEmail());
    }
 
  
    private void atualizarTabela() {
        try {
            clientes = dao.listarClientes(); 
            modelotabela.setClientes(clientes); 
            modelotabela.fireTableDataChanged(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





