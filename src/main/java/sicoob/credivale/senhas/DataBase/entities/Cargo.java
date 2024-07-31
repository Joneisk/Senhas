package sicoob.credivale.senhas.DataBase.entities;


import jakarta.persistence.*;

@Entity
@Table(name="cargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="nome")
    private String nome;
    @Column(name="nivel")
    private char nivel;


    public Cargo() {
        this(0L,"",' ');
    }

    public Cargo(Long id, String nome, char nivel) {
        this.id = id;
        this.nome = nome;
        this.nivel = nivel;
    }

    public char getNivel() {
        return nivel;
    }

    public void setNivel(char nivel) {
        this.nivel = nivel;
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
}
