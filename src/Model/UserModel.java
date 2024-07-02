package Model;

public class UserModel {

    private int id_pessoa;
    private String nome;
    private String email;
    private String cpf;
    private String data_nascimento;
    private Float mensalidade;

    public int getid_pessoa() {
        return id_pessoa;
    }
    public void setid_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getData_nascimento() {
        return data_nascimento;
    }
    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
    public Float getMensalidade() {
        return mensalidade;
    }
    public void setMensalidade(Float mensalidade) {
        this.mensalidade = mensalidade;
    }

    

}
