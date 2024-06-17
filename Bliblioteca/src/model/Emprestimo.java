package model;

public class Emprestimo {
    private String id;
    private String clienteId;
    private String livroId;
    private String dataEmprestimo;
    private String dataDevolucao;
    private String status; // Novo campo

    // Atualize o construtor para incluir o novo campo
    public Emprestimo(String id, String clienteId, String livroId, String dataEmprestimo, String dataDevolucao, String status) {
        this.id = id;
        this.clienteId = clienteId;
        this.livroId = livroId;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
    }

    // Getters e Setters para o novo campo
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getLivroId() {
        return livroId;
    }

    public void setLivroId(String livroId) {
        this.livroId = livroId;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        return "Emprestimo [id=" + id + ", clienteId=" + clienteId + ", livroId=" + livroId +
                ", dataEmprestimo=" + dataEmprestimo + ", dataDevolucao=" + dataDevolucao + ", status=" + status + "]";
    }
}
