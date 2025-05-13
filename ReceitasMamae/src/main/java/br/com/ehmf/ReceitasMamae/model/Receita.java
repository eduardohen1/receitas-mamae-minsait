package br.com.ehmf.ReceitasMamae.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "receita_id")
    private List<Ingrediente> ingredientes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "modo_preparo_id")
    private ModoPreparo modoPreparo;

    // Getters e Setters
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

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public ModoPreparo getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(ModoPreparo modoPreparo) {
        this.modoPreparo = modoPreparo;
    }
}