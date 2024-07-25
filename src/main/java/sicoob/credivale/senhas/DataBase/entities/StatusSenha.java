// StatusSenha.java
package sicoob.credivale.senhas.DataBase.entities;

import jakarta.persistence.*;

@Entity
@Table(name="statussenha")
public class StatusSenha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="descricao")
    private String descricao;

    public StatusSenha() {
        this(0L, "");
    }

    public StatusSenha(Long id, String descricao) {
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