package model;

public class AUTOR {
    private String id_autor;
    private String nome_autor;
    private String dtNascimento;
    private String nacionalidade;
    private String biografia;

    public AUTOR(String nome_autor, String dtNascimento, String nacionalidade, String biografia) {
        this.nome_autor = nome_autor;
        this.dtNascimento = dtNascimento;
        this.nacionalidade = nacionalidade;
        this.biografia = biografia;
    }

    // Getter e Setter para o ID do autor
    public String getId_autor() {
        return id_autor;
    }

    public void setId_autor(String id_autor) {
        this.id_autor = id_autor;
    }

    // Outros getters e setters
    public String getNome_autor() {
        return nome_autor;
    }

    public void setNome_autor(String nome_autor) {
        this.nome_autor = nome_autor;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
}

