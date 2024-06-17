package model;

public class Livro {

	    private String id;
	    private String nomeLivro;
	    private String autor;
	    private String anoPublicacao;
	    private String genero;
	    private int quantidade;
	    private String disponibilidade;

	    // Construtor, getters e setters
	    public Livro(String string, String nomeLivro, String autor, String anoPublicacao, String genero, int quantidade, String disponibilidade) {
	        this.id = string;
	        this.nomeLivro = nomeLivro;
	        this.autor = autor;
	        this.anoPublicacao = anoPublicacao;
	        this.genero = genero;
	        this.quantidade = quantidade;
	        this.disponibilidade = disponibilidade;
	    }

	    public Livro(String string, String string1, String string2, String string3, String string4, String string5, int int1,
				String string6) {
			// TODO Auto-generated constructor stub
		}

		public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getNomeLivro() {
	        return nomeLivro;
	    }

	    public void setNomeLivro(String nomeLivro) {
	        this.nomeLivro = nomeLivro;
	    }

	    public String getAutor() {
	        return autor;
	    }

	    public void setAutor(String autor) {
	        this.autor = autor;
	    }

	    public String getAnoPublicacao() {
	        return anoPublicacao;
	    }

	    public void setAnoPublicacao(String anoPublicacao) {
	        this.anoPublicacao = anoPublicacao;
	    }

	    public String getGenero() {
	        return genero;
	    }

	    public void setGenero(String genero) {
	        this.genero = genero;
	    }

	    public int getQuantidade() {
	        return quantidade;
	    }

	    public void setQuantidade(int quantidade) {
	        this.quantidade = quantidade;
	    }

	    public String getDisponibilidade() {
	        return disponibilidade;
	    }

	    public void setDisponibilidade(String disponibilidade) {
	        this.disponibilidade = disponibilidade;
	    }
	}

