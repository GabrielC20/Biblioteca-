package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controller.Conexao;
import model.AUTOR;
import model.Cliente;
import model.Emprestimo;
import model.Livro;


public class dao {
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	private static String CADASTRAR_CLIENTE = " INSERT INTO CLIENTES  "
			+ " (ID, NOME, CPF, DT_NASCIMENTO,GENERO, TELEFONE, EMAIL) " + " VALUES (NULL, ?, ?, ?, ?, ?, ?) ";

	private static String CONSULTAR_CLIENTE = " SELECT * FROM CLIENTES  " + " WHERE ID = ? ";

	private static String ALTERAR_CLIENTE = " UPDATE CLIENTES  SET "
			+ " NOME = ?, CPF = ?, DT_NASCIMENTO = ?, GENERO = ?, TELEFONE = ?, EMAIL = ? " + " WHERE ID = ? ";

	private static String EXCLUIR_CLIENTE = " DELETE FROM CLIENTES  " + " WHERE ID = ? ";

	private static String LISTAR_CLIENTES = " SELECT * FROM CLIENTES  " + " WHERE 1=1 ";
	
	
	

	private static String CADASTRAR_LIVRO = " INSERT INTO LIVROS  "
			+ " (ID_LIVRO, nomeLivro, autor, anoPublicacao, genero, qtd, disponibilidade) " + " VALUES (NULL, ?, ?, ?, ?, ?, ?) ";
	private static String CONSULTAR_LIVRO = "SELECT * FROM LIVROS WHERE ID_LIVRO = ?";
	private static String ALTERAR_LIVRO = "UPDATE LIVROS SET nomeLivro = ?, autor = ?, anoPublicacao = ?, genero = ?, qtd = ?, disponibilidade = ? WHERE ID_LIVRO = ?";
	private static String EXCLUIR_LIVRO = "DELETE FROM LIVROS WHERE ID_LIVRO = ?";
	private static String LISTAR_LIVROS = "SELECT * FROM LIVROS " + " WHERE 1=1 ";
	
	
	private static String CADASTRAR_AUTOR = " INSERT INTO AUTOR  "
			+ " (id_autor, nomeAutor, dtNascimento, nacionalidade, biografia) " + " VALUES (NULL, ?, ?, ?, ?) ";
	private static String CONSULTAR_AUTOR = "SELECT * FROM AUTOR WHERE id_autor = ?";
	private static String ALTERAR_AUTOR = "UPDATE AUTOR SET nomeAutor = ?, dtNascimento = ?, nacionalidade = ?, biografia = ? WHERE id_autor = ?";
	private static String EXCLUIR_AUTOR = "DELETE FROM AUTOR WHERE id_autor = ?";
	private static String LISTAR_AUTOR = "SELECT * FROM AUTOR " + " WHERE 1=1 ";
	
	public dao() {

	}

	public void cadastarCliente(Cliente cliente) {
		Connection connection = Conexao.getInstancia().abrirConexao();

		String query = CADASTRAR_CLIENTE;
		try {
			preparedStatement = connection.prepareStatement(query);

			int i = 1;


			preparedStatement.setString(i++, cliente.getNome());
			preparedStatement.setString(i++, cliente.getCpf());
			preparedStatement.setString(i++, cliente.getDtNasimento());
			preparedStatement.setString(i++, cliente.getgenero());
			preparedStatement.setString(i++, cliente.getTelefone());
			preparedStatement.setString(i++, cliente.getEmail());
			
			preparedStatement.execute();
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Cliente incluído com sucesso ");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}

	}
	
	public Cliente consultarCliente(String id) throws Exception {
		Connection connection = Conexao.getInstancia().abrirConexao();
		Cliente cliente = null;
		String query = CONSULTAR_CLIENTE;
		try {
			preparedStatement = connection.prepareStatement(query);

			int i = 1;

	
			preparedStatement.setString(i++, id);

			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
			    cliente = new Cliente(
				        resultSet.getString("ID"),
				        resultSet.getString("Nome"),
				        resultSet.getString("CPF"),
				        resultSet.getString("DT_NASCIMENTO"),
				        resultSet.getString("Genero"),
				        resultSet.getString("Telefone"),
				        resultSet.getString("Email") 
			    );
			}

			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		if(cliente == null) {
			JOptionPane.showMessageDialog(null, "Não possível localizar o cliente selecionado ", "", JOptionPane.WARNING_MESSAGE);
			throw new Exception("Não possível localizar o cliente selecionado");
		}
		return cliente;

	}

	public void alterarCliente(String id, Cliente cliente) {
		Connection connection = Conexao.getInstancia().abrirConexao();

		String query = ALTERAR_CLIENTE;
		try {
			preparedStatement = connection.prepareStatement(query);

			int i = 1;

			preparedStatement.setString(i++, cliente.getNome());
			preparedStatement.setString(i++, cliente.getCpf());
			preparedStatement.setString(i++, cliente.getDtNasimento());
			preparedStatement.setString(i++, cliente.getgenero());
			preparedStatement.setString(i++, cliente.getTelefone());
			preparedStatement.setString(i++, cliente.getEmail());
			
			
			preparedStatement.setString(i++, id);

			preparedStatement.execute();
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso ");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}

	}
	
	public void excluirCliente(String id) {
		Connection connection = Conexao.getInstancia().abrirConexao();

		String query = EXCLUIR_CLIENTE;
		try {
			preparedStatement = connection.prepareStatement(query);

			int i = 1;

			preparedStatement.setString(i++, id);

			preparedStatement.execute();
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso ");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}

	}
	
	public ArrayList<Cliente> listarClientes() throws Exception {
	    Connection connection = Conexao.getInstancia().abrirConexao();
	    ArrayList<Cliente> clientes = new ArrayList<>();
	    String query = LISTAR_CLIENTES;
	    try {
	        preparedStatement = connection.prepareStatement(query);
	        resultSet = preparedStatement.executeQuery();
	        
	        while (resultSet.next()) {
	            clientes.add(new Cliente(
	                resultSet.getString("ID"),
	                resultSet.getString("nome"),
	                resultSet.getString("CPF"),
	                resultSet.getString("DT_NASCIMENTO"),
	                resultSet.getString("Genero"),
	                resultSet.getString("Telefone"),
	                resultSet.getString("Email")
	            ));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        fecharConexao();
	    }
	    if (clientes.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Não há clientes cadastrados", "", JOptionPane.WARNING_MESSAGE);
	        throw new Exception("Não há clientes cadastrados");
	    }
	    return clientes;
	}

	private void fecharConexao() {

		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			Conexao.getInstancia().fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	// Métodos CRUD para livros
	public Livro consultarLivro(String id) throws Exception {
	    Connection connection = Conexao.getInstancia().abrirConexao();
	    Livro livro = null;
	    try {
	        preparedStatement = connection.prepareStatement(CONSULTAR_LIVRO);
	        preparedStatement.setString(1, id);
	        resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            livro = new Livro(
	                    resultSet.getString("ID_LIVRO"),
	                    resultSet.getString("nomeLivro"),
	                    resultSet.getString("autor"),
	                    resultSet.getString("anoPublicacao"),
	                    resultSet.getString("genero"),
	                    resultSet.getInt("qtd"),
	                    resultSet.getString("disponibilidade")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        fecharConexao();
	    }
	    if (livro == null) {
	        JOptionPane.showMessageDialog(null, "Livro não encontrado", "", JOptionPane.WARNING_MESSAGE);
	        throw new Exception("Livro não encontrado");
	    }
	    return livro;
	}

	public void alterarLivro(String id, Livro livro) {
	    Connection connection = Conexao.getInstancia().abrirConexao();

	    try {
	        preparedStatement = connection.prepareStatement(ALTERAR_LIVRO);

	        preparedStatement.setString(1, livro.getNomeLivro());
	        preparedStatement.setString(2, livro.getAutor());
	        preparedStatement.setString(3, livro.getAnoPublicacao());
	        preparedStatement.setString(4, livro.getGenero());
	        preparedStatement.setInt(5, livro.getQuantidade());
	        preparedStatement.setString(6, livro.getDisponibilidade());
	        preparedStatement.setString(7, id);

	        preparedStatement.execute();
	        connection.commit();

	        JOptionPane.showMessageDialog(null, "Livro alterado com sucesso");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        fecharConexao();
	    }
	}

	public void excluirLivro(String id) {
	    Connection connection = Conexao.getInstancia().abrirConexao();

	    try {
	        preparedStatement = connection.prepareStatement(EXCLUIR_LIVRO);
	        preparedStatement.setString(1, id);
	        preparedStatement.execute();
	        connection.commit();
	        JOptionPane.showMessageDialog(null, "Livro excluído com sucesso");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        fecharConexao();
	    }
	}

	
	public ArrayList<Livro> listarLivros() throws Exception {
	    Connection connection = Conexao.getInstancia().abrirConexao();
	    ArrayList<Livro> livros = new ArrayList<>();
	    try {
	        preparedStatement = connection.prepareStatement(LISTAR_LIVROS);
	        resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            livros.add(new Livro(
	                    resultSet.getString("ID_LIVRO"),
	                    resultSet.getString("nomeLivro"),
	                    resultSet.getString("autor"),
	                    resultSet.getString("anoPublicacao"),
	                    resultSet.getString("genero"),
	                    resultSet.getInt("qtd"),
	                    resultSet.getString("disponibilidade")
	            ));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        fecharConexao();
	    }
		return livros;
	}

	public void cadastrarLivro(Livro novoLivro) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    String query = CADASTRAR_LIVRO;

	    try {
	
	        connection = Conexao.getInstancia().abrirConexao();
	        if (connection == null) {
	            throw new SQLException("Falha ao obter conexão com o banco de dados.");
	        }
	        System.out.println("Conexão com o banco de dados estabelecida.");

	
	        connection.setAutoCommit(false);


	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, novoLivro.getNomeLivro());
	        preparedStatement.setString(2, novoLivro.getAutor());
	        preparedStatement.setString(3, novoLivro.getAnoPublicacao());
	        preparedStatement.setString(4, novoLivro.getGenero());
	        preparedStatement.setInt(5, novoLivro.getQuantidade());
	        preparedStatement.setString(6, novoLivro.getDisponibilidade());

	
	        int rowsAffected = preparedStatement.executeUpdate();
	        if (rowsAffected > 0) {
	      
	            connection.commit();
	            System.out.println("Livro cadastrado com sucesso. Linhas afetadas: " + rowsAffected);
	            JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso");
	        } else {
	      
	            connection.rollback();
	            System.out.println("Nenhuma linha foi afetada pelo comando de inserção.");
	            JOptionPane.showMessageDialog(null, "Falha ao cadastrar o livro. Nenhuma linha foi afetada.");
	        }
	    } catch (SQLException e) {
	
	        try {
	            if (connection != null) {
	                connection.rollback();
	            }
	        } catch (SQLException rollbackException) {
	            rollbackException.printStackTrace();
	        }
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Erro ao cadastrar livro: " + e.getMessage());
	    } finally {
	
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	
	// CRUD AUTOR
	public void cadastrarAutor(AUTOR novoAutor) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    String query = CADASTRAR_AUTOR;
	    
	    

	    try {
	        connection = Conexao.getInstancia().abrirConexao();
	        if (connection == null) {
	            throw new SQLException("Falha ao obter conexão com o banco de dados.");
	        }

	        connection.setAutoCommit(false);

	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, novoAutor.getNome_autor());
	        preparedStatement.setString(2, novoAutor.getDtNascimento());
	        preparedStatement.setString(3, novoAutor.getNacionalidade());
	        preparedStatement.setString(4, novoAutor.getBiografia());

	        int rowsAffected = preparedStatement.executeUpdate();
	        if (rowsAffected > 0) {
	            connection.commit();
	            JOptionPane.showMessageDialog(null, "Autor cadastrado com sucesso");
	        } else {
	            connection.rollback();
	            JOptionPane.showMessageDialog(null, "Falha ao cadastrar o autor. Nenhuma linha foi afetada.");
	        }
	    } catch (SQLException e) {
	        try {
	            if (connection != null) {
	                connection.rollback();
	            }
	        } catch (SQLException rollbackException) {
	            rollbackException.printStackTrace();
	        }
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Erro ao cadastrar autor: " + e.getMessage());
	    } finally {
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public AUTOR consultarAutor(String id) throws Exception {
	    Connection connection = Conexao.getInstancia().abrirConexao();
	    AUTOR autor = null;
	    try {
	        preparedStatement = connection.prepareStatement(CONSULTAR_AUTOR);
	        preparedStatement.setString(1, id);
	        resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            autor = new AUTOR(
	                resultSet.getString("nomeAutor"),
	                resultSet.getString("dtNascimento"),
	                resultSet.getString("nacionalidade"),
	                resultSet.getString("biografia")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        fecharConexao();
	    }
	    if (autor == null) {
	        JOptionPane.showMessageDialog(null, "Autor não encontrado", "", JOptionPane.WARNING_MESSAGE);
	        throw new Exception("Autor não encontrado");
	    }
	    return autor;
	}
	
	public void alterarAutor(String id, AUTOR autor) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        connection = Conexao.getInstancia().abrirConexao();
	        if (connection == null) {
	            throw new SQLException("Falha ao obter conexão com o banco de dados.");
	        }
	        String query = ALTERAR_AUTOR;
	        preparedStatement = connection.prepareStatement(query);

	        int i = 1;
	        preparedStatement.setString(i++, autor.getNome_autor());
	        preparedStatement.setString(i++, autor.getDtNascimento());
	        preparedStatement.setString(i++, autor.getNacionalidade());
	        preparedStatement.setString(i++, autor.getBiografia());
	        preparedStatement.setString(i++, id);

	        int rowsAffected = preparedStatement.executeUpdate(); // Use executeUpdate() ao invés de execute()
	        connection.commit();

	        if (rowsAffected > 0) {
	            JOptionPane.showMessageDialog(null, "Autor alterado com sucesso");
	        } else {
	            JOptionPane.showMessageDialog(null, "Nenhuma linha foi alterada. Verifique se o ID está correto.");
	        }

	    } catch (SQLException e) {
	        try {
	            if (connection != null) {
	                connection.rollback();
	            }
	        } catch (SQLException rollbackException) {
	            rollbackException.printStackTrace();
	        }
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Erro ao alterar autor: " + e.getMessage());
	    } finally {
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


	
	
	public void excluirAutor(String id) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        connection = Conexao.getInstancia().abrirConexao();
	        if (connection == null) {
	            throw new SQLException("Falha ao obter conexão com o banco de dados.");
	        }
	        String query = EXCLUIR_AUTOR;
	        preparedStatement = connection.prepareStatement(query);

	        int i = 1;
	        preparedStatement.setString(i++, id);

	        int rowsAffected = preparedStatement.executeUpdate(); // Use executeUpdate() ao invés de execute()
	        connection.commit();

	        if (rowsAffected > 0) {
	            JOptionPane.showMessageDialog(null, "Autor excluído com sucesso");
	        } else {
	            JOptionPane.showMessageDialog(null, "Nenhuma linha foi excluída. Verifique se o ID está correto.");
	        }

	    } catch (SQLException e) {
	        try {
	            if (connection != null) {
	                connection.rollback();
	            }
	        } catch (SQLException rollbackException) {
	            rollbackException.printStackTrace();
	        }
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Erro ao excluir autor: " + e.getMessage());
	    } finally {
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}



	public ArrayList<AUTOR> listarAutores() throws Exception {
	    Connection connection = Conexao.getInstancia().abrirConexao();
	    ArrayList<AUTOR> autores = new ArrayList<>();
	    String query = LISTAR_AUTOR;
	    try {
	        preparedStatement = connection.prepareStatement(query);
	        resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            AUTOR autor = new AUTOR(
	                resultSet.getString("nomeAutor"),
	                resultSet.getString("dtNascimento"),
	                resultSet.getString("nacionalidade"),
	                resultSet.getString("biografia")
	            );
	            autor.setId_autor(resultSet.getString("id_autor"));  
	            autores.add(autor);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        fecharConexao();
	    }
	    if (autores.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Não há autores cadastrados", "", JOptionPane.WARNING_MESSAGE);
	        throw new Exception("Não há autores cadastrados");
	    }
	    return autores;
	}


	
    // CRUD de Empréstimos
	public void cadastrarEmprestimo(Emprestimo emprestimo) {
	    String sql = "INSERT INTO emprestimo (nomeCliente, nomeLivro, dataEmprestimo, dataDevolucao, Status) VALUES (?, ?, ?, ?, ?)";
	    try (Connection conn = Conexao.getInstancia().abrirConexao(); 
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, emprestimo.getClienteId());
	        stmt.setString(2, emprestimo.getLivroId());
	        stmt.setString(3, emprestimo.getDataEmprestimo());
	        stmt.setString(4, emprestimo.getDataDevolucao());
	        stmt.setString(5, emprestimo.getStatus());

	        System.out.println("Executando SQL: " + stmt.toString());
	        int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected > 0) {
	            JOptionPane.showMessageDialog(null, "Empréstimo cadastrado com sucesso.");
	        } else {
	            JOptionPane.showMessageDialog(null, "Nenhum empréstimo foi cadastrado.");
	        }

	        if (!conn.getAutoCommit()) {
	            conn.commit();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}




	public void alterarEmprestimo(String id, Emprestimo emprestimo) {
	    String sql = "UPDATE emprestimo SET nomeCliente = ?, nomeLivro = ?, dataEmprestimo = ?, dataDevolucao = ?, Status = ? WHERE id_emprestimo = ?";
	    try (Connection conn = Conexao.getInstancia().abrirConexao(); 
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        System.out.println("Conexão aberta: " + (conn != null));
	        stmt.setString(1, emprestimo.getClienteId());
	        stmt.setString(2, emprestimo.getLivroId());
	        stmt.setString(3, emprestimo.getDataEmprestimo());
	        stmt.setString(4, emprestimo.getDataDevolucao());
	        stmt.setString(5, emprestimo.getStatus()); 
	        stmt.setString(6, id);

	        System.out.println("Executando SQL: " + stmt.toString());
	        int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected > 0) {
	        	JOptionPane.showMessageDialog(null, "Empréstimo atualizado com sucesso.");
	        } else {
	        	JOptionPane.showMessageDialog(null, "Nenhum empréstimo foi atualizado. Verifique o ID.");
	        }

	        if (!conn.getAutoCommit()) {
	            conn.commit();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void excluirEmprestimo(String id) {
	    String sql = "DELETE FROM emprestimo WHERE id_emprestimo = ?";
	    try (Connection conn = Conexao.getInstancia().abrirConexao(); 
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        System.out.println("Conexão aberta: " + (conn != null));
	        stmt.setString(1, id);

	        System.out.println("Executando SQL: " + stmt.toString());
	        int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected > 0) {
	        	JOptionPane.showMessageDialog(null, "Empréstimo excluído com sucesso.");
	        } else {
	        	JOptionPane.showMessageDialog(null, "Nenhum empréstimo foi excluído. Verifique o ID.");
	        }

	 
	        if (!conn.getAutoCommit()) {
	            conn.commit();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	public List<Emprestimo> listarEmprestimos() {
	    String sql = "SELECT e.id_emprestimo, e.nomeCliente, e.nomeLivro, e.dataEmprestimo, e.dataDevolucao, e.Status FROM EMPRESTIMO e";
	    List<Emprestimo> emprestimos = new ArrayList<>();
	    try (Connection conn = Conexao.getInstancia().abrirConexao(); 
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet resultSet = stmt.executeQuery()) {
	        while (resultSet.next()) {
	            Emprestimo emprestimo = new Emprestimo(
	                resultSet.getString("id_emprestimo"),
	                resultSet.getString("nomeCliente"),
	                resultSet.getString("nomeLivro"),
	                resultSet.getString("dataEmprestimo"),
	                resultSet.getString("dataDevolucao"),
	                resultSet.getString("Status")
	            );
	            emprestimos.add(emprestimo);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return emprestimos;
	}




}

	


