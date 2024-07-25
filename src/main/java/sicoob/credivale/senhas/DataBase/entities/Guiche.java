package sicoob.credivale.senhas.DataBase.entities;

import jakarta.persistence.*;

@Entity
@Table(name="guiche")
public class Guiche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name="atendente_idatendente", nullable = true)
    private Atendente atendente;

    public Guiche() {
        this(0L, "", null);
    }

    public Guiche(Long id, String nome, Atendente atendente) {
        this.id = id;
        this.nome = nome;
        this.atendente = atendente;
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

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }
}