package sicoob.credivale.senhas.DataBase.entities;

import jakarta.persistence.*;

@Entity
@Table(name="tipoatendimento")
public class TipoAtendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="nome")
    private String nome;

    public TipoAtendimento() {
        this(0L, "");
    }

    public TipoAtendimento(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return nome;
    }

    public void setStatus(String status) {
        this.nome = status;
    }
}