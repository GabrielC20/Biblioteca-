package model;

public class Cliente {
	protected String id;
	protected String nome;
	protected String cpf;
	protected String dtNasimento;
	protected String genero;
	protected String telefone;
	protected String email;
	
	
	public Cliente(){
		
	}
	
	public Cliente(String id, String nome, String cpf, String dtNasimento, String genero,  String telefone, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dtNasimento = dtNasimento;
		this.genero = genero;
		this.telefone = telefone;
		this.email = email;
	}
	
	public Cliente(String string, String string2, String string3, String string4, String string5, String string6) {
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDtNasimento() {
		return dtNasimento;
	}
	
	public String getgenero() {
		return genero;
	}

	public void setgenero(String genero) {
		this.genero = genero;
	}

	public void setDtNasimento(String dtNasimento) {
		this.dtNasimento = dtNasimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
	    return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", dtNasimento=" + dtNasimento +
	            ", genero=" + genero + ", telefone=" + telefone + ", email=" + email + "]";
	}

	
}
