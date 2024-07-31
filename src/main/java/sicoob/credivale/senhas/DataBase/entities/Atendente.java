package sicoob.credivale.senhas.DataBase.entities;


import jakarta.persistence.*;

@Entity
@Table(name="atendente")
public class Atendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idatendente")
    private Long id;
    @Column(name="nome")
    private String nome;
    @Column(name="status")
    private int status;
    @Column(name="login")
    private String login;
    @Column(name="unidade")
    private String unidade;

    @Column(name="senha")
    private String senha;
    @Column(name="email")
    private String email;
    @Column(name="cpf")
    private String cpf;
    @Column(name="seqatendimento")
    private String seqatendimento;


    @ManyToOne
    @JoinColumn(name="cargo_id", nullable = false)
    private Cargo cargo;


    public Atendente() {
        this(0L,"","",0,"","","","","",null);
    }

    public Atendente(Long id, String nome, String unidade, int status, String login, String senha, String email, String cpf, String seqatendimento, Cargo cargo) {
        this.id = id;
        this.nome = nome;
        this.unidade = unidade;
        this.status = status;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.cpf = cpf;
        this.seqatendimento = seqatendimento;
        this.cargo = cargo;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSeqatendimento() {
        return seqatendimento;
    }

    public void setSeqatendimento(String seqatendimento) {
        this.seqatendimento = seqatendimento;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}

