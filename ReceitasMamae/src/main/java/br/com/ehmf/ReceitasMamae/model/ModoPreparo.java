package br.com.ehmf.ReceitasMamae.model;

import jakarta.persistence.*;

@Entity
public class ModoPreparo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    // Getters e Setters
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

    @Override
    public String toString() {
        return "ModoPreparo{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}