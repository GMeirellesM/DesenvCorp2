package com.gmartinho.recursos.model;

import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "recurso")
public class RecursoModel {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column (nullable = false, length = 1024)
    private String titulo;

    @Column (nullable = false, length = 4096)
    private String descricao;

    @Column (nullable = false)
    private String link;

    @Lob
    @Column (nullable = false)
    private String imagem;

    @DateTimeFormat (pattern = "yyyy-mm-dd")
    @Temporal (TemporalType.DATE)
    @Column (name = "data_criacao")
    private Date dataCriacao;

    @DateTimeFormat (pattern = "yyyy-mm-dd")
    @Temporal (TemporalType.DATE)
    @Column (name = "data_registro")
    private Date dataRegistro;

    @ElementCollection
    @Column (name = "palavras_chave")
    private Set<String> palavrasChave;

    @OrderBy ("nome asc, sobrenome asc")
    @ManyToMany (mappedBy = "recursos")
    private List<AutorModel> autores;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "colecao_id")
    private ColecaoModel colecao;

    public RecursoModel(){
    }

    public RecursoModel(Long id, String titulo, String descricao, String link, String imagem, Date dataCriacao, Date dataRegistro, Set<String> palavrasChave, List<AutorModel> autores, ColecaoModel colecao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.link = link;
        this.imagem = imagem;
        this.dataCriacao = dataCriacao;
        this.dataRegistro = dataRegistro;
        this.palavrasChave = palavrasChave;
        this.autores = autores;
        this.colecao = colecao;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Set<String> getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(Set<String> palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    public List<AutorModel> getAutores() {
        return autores;
    }

    public void setAutores(List<AutorModel> autores) {
        this.autores = autores;
    }

    public ColecaoModel getColecao() {
        return colecao;
    }

    public void setColecao(ColecaoModel colecao) {
        this.colecao = colecao;
    }
}
