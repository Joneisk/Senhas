package sicoob.credivale.senhas.DataBase.entities;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.time.LocalDate;

@Entity
@Table(name="senha")
public class Senha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="senha")
    private int senha;

    @Column(name="horaemissao")
    private LocalTime horaEmissao;

    @Column(name="dataemissao")
    private LocalDate dataEmissao;

    @Column(name="horachamada")
    private LocalTime horaChamada;

    @Column(name="horafimatendimento")
    private LocalTime horafimatendimento;

    @Column(name="numerochamadas")
    private int numeroChamadas;

    @ManyToOne
    @JoinColumn(name="tipoatendimento_id", nullable = false)
    private TipoAtendimento tipoAtendimento;

    @ManyToOne
    @JoinColumn(name="atendente_idatendente", nullable = true)
    private Atendente atendente;

    @ManyToOne
    @JoinColumn(name="statussenha_id", nullable = true)
    private StatusSenha statusSenha;

    public Senha() {
        this(0L, 0,   null ,null, null, null, 0, null, null, null);
    }

    public Senha(Long id, int senha,LocalTime horafimatendimento ,LocalTime horaEmissao, LocalDate dataEmissao, LocalTime horaChamada, int numeroChamadas, TipoAtendimento tipoAtendimento, Atendente atendente, StatusSenha statusSenha) {
        this.id = id;
        this.senha = senha;
        this.horaEmissao = horaEmissao;
        this.dataEmissao = dataEmissao;
        this.horaChamada = horaChamada;
        this.numeroChamadas = numeroChamadas;
        this.tipoAtendimento = tipoAtendimento;
        this.atendente = atendente;
        this.statusSenha = statusSenha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public LocalTime getHoraEmissao() {
        return horaEmissao;
    }

    public void setHoraEmissao(LocalTime horaEmissao) {
        this.horaEmissao = horaEmissao;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public LocalTime getHoraChamada() {
        return horaChamada;
    }

    public void setHoraChamada(LocalTime horaChamada) {
        this.horaChamada = horaChamada;
    }

    public int getNumeroChamadas() {
        return numeroChamadas;
    }

    public void setNumeroChamadas(int numeroChamadas) {
        this.numeroChamadas = numeroChamadas;
    }

    public TipoAtendimento getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(TipoAtendimento tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public StatusSenha getStatusSenha() {
        return statusSenha;
    }

    public void setStatusSenha(StatusSenha statusSenha) {
        this.statusSenha = statusSenha;
    }

    public LocalTime getHorafimatendimento() {
        return horafimatendimento;
    }

    public void setHorafimatendimento(LocalTime horafimatendimento) {
        this.horafimatendimento = horafimatendimento;
    }
}