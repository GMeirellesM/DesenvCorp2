package com.gmartinho.recursos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance (strategy = InheritanceType.JOINED)
@Table (name = "colecao")
public class ColecaoModel {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column (nullable = false, length = 1024)
    private String titulo;

    @Column (nullable = false, length = 4096)
    private String descricao;

    @Lob
    @Column (nullable = false)
    private String imagem;

    @JsonIgnore
    @OrderBy ("titulo asc")
    @OneToMany (mappedBy = "colecao")
    private List<RecursoModel> recursos;

    public ColecaoModel() {
    }

    public ColecaoModel(Long id, String titulo, String descricao, String imagem, List<RecursoModel> recursos) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagem = imagem;
        this.recursos = recursos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public List<RecursoModel> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<RecursoModel> recursos) {
        this.recursos = recursos;
    }
}
