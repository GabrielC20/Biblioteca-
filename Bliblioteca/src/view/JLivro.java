package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.dao;
import model.AUTOR;
import model.Livro;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Color;

public class JLivro extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldLivro;
    private JComboBox<String> comboBoxAutor;

    private JTextField textFieldAno;
    private JTextField textFieldGenero;
    private JTextField textFieldQtd;
    private JTextField textFieldDisponibilidade;
    private JTable table;
    private DefaultTableModel model;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JLivro frame = new JLivro();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public JLivro() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 788, 490);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Nome do Livro");
        lblNewLabel.setBounds(44, 36, 89, 14);
        contentPane.add(lblNewLabel);

        textFieldLivro = new JTextField();
        textFieldLivro.setBounds(44, 50, 140, 20);
        contentPane.add(textFieldLivro);
        textFieldLivro.setColumns(10);

        JLabel lblAutor = new JLabel("Autor");
        lblAutor.setBounds(44, 84, 37, 14);
        contentPane.add(lblAutor);

        comboBoxAutor = new JComboBox<>();
        comboBoxAutor.setBounds(44, 98, 140, 20);
        contentPane.add(comboBoxAutor);

        JLabel lblDataDeLancamento = new JLabel("Ano de Publicação");
        lblDataDeLancamento.setBounds(44, 129, 105, 14);
        contentPane.add(lblDataDeLancamento);

        textFieldAno = new JTextField();
        textFieldAno.setColumns(10);
        textFieldAno.setBounds(44, 148, 140, 20);
        contentPane.add(textFieldAno);

        JLabel lblGnero = new JLabel("Gênero");
        lblGnero.setBounds(549, 36, 46, 14);
        contentPane.add(lblGnero);

        textFieldGenero = new JTextField();
        textFieldGenero.setColumns(10);
        textFieldGenero.setBounds(549, 50, 140, 20);
        contentPane.add(textFieldGenero);

        JLabel lblQuantidade = new JLabel("Quantidade");
        lblQuantidade.setBounds(549, 84, 74, 14);
        contentPane.add(lblQuantidade);

        textFieldQtd = new JTextField();
        textFieldQtd.setColumns(10);
        textFieldQtd.setBounds(549, 98, 140, 20);
        contentPane.add(textFieldQtd);

        textFieldDisponibilidade = new JTextField();
        textFieldDisponibilidade.setColumns(10);
        textFieldDisponibilidade.setBounds(549, 148, 140, 20);
        contentPane.add(textFieldDisponibilidade);

        JLabel lblDisponibilidade = new JLabel("Disponibilidade");
        lblDisponibilidade.setBounds(549, 129, 80, 14);
        contentPane.add(lblDisponibilidade);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(141, 223, 489, 217);
        contentPane.add(scrollPane);

        model = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "ID", "Nome Livro", "Autor", "Ano de Publicação", "Gênero", "Quantidade", "Disponibilidade"
            }
        );
        table = new JTable();
        table.setModel(model);
        scrollPane.setViewportView(table);

        JButton btnNewButton = new JButton("Cadastrar");
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.setBounds(140, 189, 98, 23);
        contentPane.add(btnNewButton);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBackground(new Color(0, 128, 255));
        btnEditar.setBounds(341, 189, 89, 23);
        contentPane.add(btnEditar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBackground(new Color(232, 40, 40));
        btnExcluir.setBounds(540, 189, 89, 23);
        contentPane.add(btnExcluir);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarLivro();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarLivro();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirLivro();
            }
        });

        listarAutores();  // Chama o método para listar os autores
        listarLivros();

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        String idLivro = model.getValueAt(selectedRow, 0).toString();
                        String nomeLivro = model.getValueAt(selectedRow, 1).toString();
                        String autor = model.getValueAt(selectedRow, 2).toString();
                        String ano = model.getValueAt(selectedRow, 3).toString();
                        String genero = model.getValueAt(selectedRow, 4).toString();
                        String quantidade = model.getValueAt(selectedRow, 5).toString();
                        String disponibilidade = model.getValueAt(selectedRow, 6).toString();

                        textFieldLivro.setText(nomeLivro);
                        comboBoxAutor.setSelectedItem(autor);
                        textFieldAno.setText(ano);
                        textFieldGenero.setText(genero);
                        textFieldQtd.setText(quantidade);
                        textFieldDisponibilidade.setText(disponibilidade);
                    }
                }
            }
        });

    }

    private void listarAutores() {
        try {
            ArrayList<AUTOR> autores = new dao().listarAutores();
            comboBoxAutor.removeAllItems();  
            for (AUTOR autor : autores) {
                comboBoxAutor.addItem(autor.getNome_autor());
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao listar autores: " + e.getMessage());
        }
    }


    private void listarLivros() {
        try {
            model.setRowCount(0);
            ArrayList<Livro> livros = new dao().listarLivros(); 

     
            for (Livro livro : livros) {
                Object[] row = { livro.getId(), livro.getNomeLivro(), livro.getAutor(), livro.getAnoPublicacao(), livro.getGenero(), livro.getQuantidade(), livro.getDisponibilidade() };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void cadastrarLivro() {
        try {
            String nomeLivro = textFieldLivro.getText();
            String autor = (String) comboBoxAutor.getSelectedItem();  
            String ano = textFieldAno.getText();
            String genero = textFieldGenero.getText();
            int quantidade = Integer.parseInt(textFieldQtd.getText());
            String disponibilidade = textFieldDisponibilidade.getText();
            Livro livro = new Livro(null, nomeLivro, autor, ano, genero, quantidade, disponibilidade);
            new dao().cadastrarLivro(livro);
            listarLivros();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar livro: Quantidade inválida");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar livro: " + e.getMessage());
        }
    }



    private void editarLivro() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) { 
            try {
                String idLivro = model.getValueAt(selectedRow, 0).toString();
                String nomeLivro = textFieldLivro.getText();
                String autor = (String) comboBoxAutor.getSelectedItem();  
                String ano = textFieldAno.getText();
                String genero = textFieldGenero.getText();
                int quantidade = Integer.parseInt(textFieldQtd.getText());
                String disponibilidade = textFieldDisponibilidade.getText();
                Livro livro = new Livro(idLivro, nomeLivro, autor, ano, genero, quantidade, disponibilidade);
                new dao().alterarLivro(idLivro, livro);
                listarLivros();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro ao editar livro: Quantidade inválida");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao editar livro: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um livro para editar");
        }
    }



    private void excluirLivro() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) { 
            try {
                String idLivro = model.getValueAt(selectedRow, 0).toString();
                new dao().excluirLivro(idLivro);
                listarLivros();
                JOptionPane.showMessageDialog(null, "Livro excluído com sucesso");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir livro: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um livro para excluir");
        }
    }
    
}






