package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

import DAO.dao;
import controller.Conexao;
import model.Cliente;
import model.Emprestimo;
import model.Livro;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class JEmprestimo extends JFrame {
    private JPanel contentPane;
    private JTextField textFieldDataEmprestimo;
    private JTextField textFieldDataDevolucao;
    private JTable table;
    private Choice choiceCliente;
    private Choice choiceLivro;
    private Choice choiceStatus;

    private dao daoInstance;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JEmprestimo frame = new JEmprestimo();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public JEmprestimo() {
        daoInstance = new dao();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 631, 449);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblDataEmprestimo = new JLabel("Data de empréstimo");
        lblDataEmprestimo.setBounds(435, 11, 143, 14);
        contentPane.add(lblDataEmprestimo);

        textFieldDataEmprestimo = new JTextField();
        textFieldDataEmprestimo.setBounds(435, 36, 111, 20);
        contentPane.add(textFieldDataEmprestimo);
        textFieldDataEmprestimo.setColumns(10);

        JLabel lblDataDevolucao = new JLabel("Data de devolução");
        lblDataDevolucao.setBounds(435, 61, 111, 14);
        contentPane.add(lblDataDevolucao);

        textFieldDataDevolucao = new JTextField();
        textFieldDataDevolucao.setColumns(10);
        textFieldDataDevolucao.setBounds(435, 83, 111, 20);
        contentPane.add(textFieldDataDevolucao);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBackground(new Color(255, 255, 255));
        btnCadastrar.setBounds(68, 167, 99, 23);
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarEmprestimo();
            }
        });
        contentPane.add(btnCadastrar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBackground(new Color(0, 128, 255));
        btnEditar.setBounds(336, 167, 89, 23);
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarEmprestimo();
            }
        });
        contentPane.add(btnEditar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBackground(new Color(230, 0, 0));
        btnExcluir.setBounds(457, 167, 89, 23);
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirEmprestimo();
            }
        });
        contentPane.add(btnExcluir);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(68, 215, 478, 184);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Cliente", "Livro", "Data de Empréstimo", "Data de Devolução", "Status"}
        ));
        scrollPane.setViewportView(table);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                    preencherCampos(table.getSelectedRow());
                }
            }
        });

        choiceCliente = new Choice();
        choiceCliente.setBounds(68, 55, 111, 20);
        contentPane.add(choiceCliente);

        Label lblCliente = new Label("Cliente");
        lblCliente.setBounds(68, 30, 62, 22);
        contentPane.add(lblCliente);

        choiceLivro = new Choice();
        choiceLivro.setBounds(68, 111, 111, 20);
        contentPane.add(choiceLivro);

        Label lblLivro = new Label("Livro");
        lblLivro.setBounds(68, 83, 62, 22);
        contentPane.add(lblLivro);

        choiceStatus = new Choice();
        choiceStatus.setBounds(435, 126, 111, 20);
        contentPane.add(choiceStatus);

        JLabel lblStatus = new JLabel("Status");
        lblStatus.setBounds(435, 106, 111, 14);
        contentPane.add(lblStatus);
        
        JButton btnNewButton = new JButton("Relatorio");
        btnNewButton.setBackground(new Color(255, 255, 0));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		JasperReport relatorio;
        		Connection connection = Conexao.getInstancia().abrirConexao();
        		try {
        		relatorio = JasperCompileManager.compileReport("C:\\Users\\Home\\eclipse-workspace\\Bliblioteca\\blibliotecaR\\Blank_A4.jrxml"); 
        		JasperPrint p = JasperFillManager.fillReport(relatorio, null, connection);
        		
        		JasperViewer jasperViewer = new JasperViewer(p, false);
    			
    			jasperViewer.setVisible(true);
        			Conexao.getInstancia().fecharConexao();
        		} catch (JRException e1) {
        								
        		e1.printStackTrace();
        		}
        	}

		
        });
        btnNewButton.setBounds(200, 167, 89, 23);
        contentPane.add(btnNewButton);

        choiceStatus.add("Devolvido");
        choiceStatus.add("Em aberto");
        choiceStatus.add("Atrasado");

        loadChoices();
        loadEmprestimos();
    }



	private void preencherCampos(int selectedRow) {
        String cliente = table.getValueAt(selectedRow, 1).toString();
        String livro = table.getValueAt(selectedRow, 2).toString();
        String dataEmprestimo = table.getValueAt(selectedRow, 3).toString();
        String dataDevolucao = table.getValueAt(selectedRow, 4).toString();
        String status = table.getValueAt(selectedRow, 5).toString();

        choiceCliente.select(cliente);
        choiceLivro.select(livro);
        textFieldDataEmprestimo.setText(dataEmprestimo);
        textFieldDataDevolucao.setText(dataDevolucao);
        choiceStatus.select(status);
    }

    private void cadastrarEmprestimo() {
        try {
            String dataEmprestimo = textFieldDataEmprestimo.getText();
            String dataDevolucao = textFieldDataDevolucao.getText();

        
            if (dataEmprestimo.isEmpty() || dataDevolucao.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos de data.");
                return;
            }


            Emprestimo emprestimo = new Emprestimo(
                null,
                choiceCliente.getSelectedItem(),
                choiceLivro.getSelectedItem(),
                dataEmprestimo,
                dataDevolucao,
                choiceStatus.getSelectedItem()
            );
            daoInstance.cadastrarEmprestimo(emprestimo);
            loadEmprestimos();
            limparCampos(); 
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar empréstimo.");
        }
    }

    private void editarEmprestimo() {
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um empréstimo para editar.");
                return;
            }

            String id = table.getValueAt(selectedRow, 0).toString();
            Emprestimo emprestimo = new Emprestimo(
                id,
                choiceCliente.getSelectedItem(),
                choiceLivro.getSelectedItem(),
                textFieldDataEmprestimo.getText(),
                textFieldDataDevolucao.getText(),
                choiceStatus.getSelectedItem()
            );
            daoInstance.alterarEmprestimo(id, emprestimo);
            loadEmprestimos();
            limparCampos();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao editar empréstimo.");
        }
    }

    private void excluirEmprestimo() {
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um empréstimo para excluir.");
                return;
            }

            String id = table.getValueAt(selectedRow, 0).toString();
            daoInstance.excluirEmprestimo(id);
            loadEmprestimos();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao excluir empréstimo.");
        }
    }

    private void loadChoices() {
        try {
            List<Cliente> clientes = daoInstance.listarClientes();
            for (Cliente cliente : clientes) {
                choiceCliente.add(cliente.getNome());
            }

            List<Livro> livros = daoInstance.listarLivros();
            for (Livro livro : livros) {
                choiceLivro.add(livro.getNomeLivro());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadEmprestimos() {
        try {
            List<Emprestimo> emprestimos = daoInstance.listarEmprestimos();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            for (Emprestimo emprestimo : emprestimos) {
                model.addRow(new Object[]{
                    emprestimo.getId(),
                    emprestimo.getClienteId(),
                    emprestimo.getLivroId(),
                    emprestimo.getDataEmprestimo(),
                    emprestimo.getDataDevolucao(),
                    emprestimo.getStatus()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private void limparCampos() {
        choiceCliente.select(""); 
        choiceLivro.select(""); 
        textFieldDataEmprestimo.setText(""); 
        textFieldDataDevolucao.setText("");
        choiceStatus.select(""); 
    }
}


