package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTabela extends AbstractTableModel {

	private static final String[] colunas = { "ID", "Nome" ,"CPF", "DT_NASCIMENTO", "Genero", "Telefone", "Email"};
	private ArrayList<Cliente> clientes;

	public ModeloTabela(ArrayList<Cliente> clientes) {
		super();
		this.clientes = clientes;
	}

	@Override
	public int getRowCount() {
		return clientes.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	
		Cliente cliente = clientes.get(rowIndex);
		if (columnIndex == 0) {
			return cliente.getId();
		} else if (columnIndex == 1) {
			return cliente.getNome();
		} else if (columnIndex == 2) {
			return cliente.getCpf();
		} else if (columnIndex == 3) {
			return cliente.getDtNasimento();
		} else if (columnIndex == 4) {
			return cliente.getgenero();
		} else if (columnIndex == 5) {
			return cliente.getTelefone();
		} 
		else if (columnIndex == 6) {
			return cliente.getEmail();
		}
		else {
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}

	public void setClientes(ArrayList<Cliente> novosClientes) {
	    this.clientes = novosClientes;
	}
}
