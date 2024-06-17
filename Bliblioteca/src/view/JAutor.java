package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import DAO.dao;
import model.AUTOR;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;

public class JAutor extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldNome;
    private JTextField textFieldDtNascimento;
    private JTextField textFieldNacionalidade;
    private JTextField textFieldBiografia;
    private JTextField textFieldId; 
    private JTable table;
    private DefaultTableModel tableModel;
    private dao dao;

    /**
     * Create the frame.
     */
    public JAutor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 709, 436);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNomeAutor = new JLabel("Nome do autor");
        lblNomeAutor.setBounds(90, 38, 98, 14);
        contentPane.add(lblNomeAutor);

        textFieldNome = new JTextField();
        textFieldNome.setBounds(90, 52, 134, 20);
        contentPane.add(textFieldNome);
        textFieldNome.setColumns(10);

        JLabel lblDataDeNascimento = new JLabel("Data de nascimento");
        lblDataDeNascimento.setBounds(90, 83, 117, 14);
        contentPane.add(lblDataDeNascimento);

        textFieldDtNascimento = new JTextField();
        textFieldDtNascimento.setColumns(10);
        textFieldDtNascimento.setBounds(90, 97, 134, 20);
        contentPane.add(textFieldDtNascimento);

        JLabel lblNacionalidade = new JLabel("Nacionalidade");
        lblNacionalidade.setBounds(458, 38, 89, 14);
        contentPane.add(lblNacionalidade);

        textFieldNacionalidade = new JTextField();
        textFieldNacionalidade.setColumns(10);
        textFieldNacionalidade.setBounds(458, 52, 134, 20);
        contentPane.add(textFieldNacionalidade);

        JLabel lblBiografia = new JLabel("Biografia");
        lblBiografia.setBounds(458, 83, 77, 14);
        contentPane.add(lblBiografia);

        textFieldBiografia = new JTextField();
        textFieldBiografia.setColumns(10);
        textFieldBiografia.setBounds(458, 97, 134, 20);
        contentPane.add(textFieldBiografia);

        textFieldId = new JTextField();
        textFieldId.setVisible(false);
        contentPane.add(textFieldId);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBackground(new Color(255, 255, 255));
        btnCadastrar.setBounds(90, 176, 98, 23);
        contentPane.add(btnCadastrar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBackground(new Color(0, 128, 255));
        btnEditar.setBounds(304, 176, 89, 23);
        contentPane.add(btnEditar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBackground(new Color(234, 21, 21));
        btnExcluir.setBounds(503, 176, 89, 23);
        contentPane.add(btnExcluir);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(95, 210, 502, 176);
        contentPane.add(scrollPane);

        tableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] { "Nome do autor", "Data de Nascimento", "Nacionalidade", "Biografia", "ID" }) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        table = new JTable(tableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(105);
        table.getColumnModel().getColumn(1).setPreferredWidth(112);
        table.getColumnModel().getColumn(2).setPreferredWidth(98);
        table.getColumnModel().getColumn(4).setPreferredWidth(0); 
        scrollPane.setViewportView(table);

        dao = new dao();


        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = textFieldNome.getText();
                String dtNascimento = textFieldDtNascimento.getText();
                String nacionalidade = textFieldNacionalidade.getText();
                String biografia = textFieldBiografia.getText();


                dao.cadastrarAutor(new AUTOR(nome, dtNascimento, nacionalidade, biografia));

              
                updateTable();

         
                clearFields();
            }
        });




   
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String id = textFieldId.getText(); 
                    String nome = textFieldNome.getText();
                    String dtNascimento = textFieldDtNascimento.getText();
                    String nacionalidade = textFieldNacionalidade.getText();
                    String biografia = textFieldBiografia.getText();

               
                    dao.alterarAutor(id, new AUTOR(nome, dtNascimento, nacionalidade, biografia));

         
                    updateTable();

                    clearFields();
                }
            }
        });

   
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String id = textFieldId.getText(); 


              
                    dao.excluirAutor(id);

                 
                    updateTable();

                    
                    clearFields();
                }
            }
        });



        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    Object nomeObj = tableModel.getValueAt(row, 0);
                    Object dtNascimentoObj = tableModel.getValueAt(row, 1);
                    Object nacionalidadeObj = tableModel.getValueAt(row, 2);
                    Object biografiaObj = tableModel.getValueAt(row, 3);
                    Object idObj = tableModel.getValueAt(row, 4);

                    
                    textFieldNome.setText(nomeObj != null ? nomeObj.toString() : "");
                    textFieldDtNascimento.setText(dtNascimentoObj != null ? dtNascimentoObj.toString() : "");
                    textFieldNacionalidade.setText(nacionalidadeObj != null ? nacionalidadeObj.toString() : "");
                    textFieldBiografia.setText(biografiaObj != null ? biografiaObj.toString() : "");
                    textFieldId.setText(idObj != null ? idObj.toString() : "");

                }
            }
        });



        
        updateTable();
    }

    
    private void updateTable() {
   
        clearTable();


        ArrayList<AUTOR> autores = null;
        try {
            autores = dao.listarAutores();
        } catch (Exception e) {
            e.printStackTrace();
   
        }


        if (autores != null) {
            for (AUTOR autor : autores) {
           
                tableModel.addRow(new Object[] { 
                    autor.getNome_autor(), 
                    autor.getDtNascimento(), 
                    autor.getNacionalidade(), 
                    autor.getBiografia(), 
                    autor.getId_autor() 
                });
            }
        }


        table.getColumnModel().getColumn(4).setMinWidth(0);
        table.getColumnModel().getColumn(4).setMaxWidth(0);
        table.getColumnModel().getColumn(4).setWidth(0);
        table.getColumnModel().getColumn(4).setPreferredWidth(0);
    }




    private void clearTable() {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
    }


    private void clearFields() {
        textFieldNome.setText("");
        textFieldDtNascimento.setText("");
        textFieldNacionalidade.setText("");
        textFieldBiografia.setText("");
        textFieldId.setText(""); 
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JAutor frame = new JAutor();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}






