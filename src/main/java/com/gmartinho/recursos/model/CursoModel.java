package com.gmartinho.recursos.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "curso")
@PrimaryKeyJoinColumn (name = "id")
public class CursoModel extends ColecaoModel {

    @DateTimeFormat (pattern = "yyyy-mm-dd")
    @Temporal (TemporalType.DATE)
    @Column (name = "data_registro", nullable = false)
    private Date dataRegistro;

    public CursoModel(){
    }

    public CursoModel(Date dataRegistro) {
        super();
        this.dataRegistro = dataRegistro;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
