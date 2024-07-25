package sicoob.credivale.senhas.DataBase.entities;

import jakarta.persistence.*;

@Entity
@Table(name="tipoatendimento")
public class TipoAtendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="descricao")
    private String descricao;

    public TipoAtendimento() {
        this(0L, "");
    }

    public TipoAtendimento(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}