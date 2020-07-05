package com.devapp.sigsv.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.devapp.sigsv.util.AppConstantesSql;

import lombok.Data;

@Data
@Entity
@Table(name = AppConstantesSql.TABLE_SGV_USUARIO, schema = AppConstantesSql.SCHEMA_SIGSV)
public class SgvUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = AppConstantesSql.SEQUENCE_ID_SQ_USUARIO)
    @SequenceGenerator(name = AppConstantesSql.SEQUENCE_ID_SQ_USUARIO, sequenceName = AppConstantesSql.SCHEMA_SIGSV+"."+AppConstantesSql.SEQUENCE_SQ_USUARIO,  allocationSize = 1)
    private Long idUsuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "ap_paterno")
    private String apPatenro;

    @Column(name = "ap_materno")
    private String apMaterno;

    @Column(name = "dni")
    private String dni;

    @Column(name = "ind_activo")
    private Boolean indActivo;

    @Column(name = "clave")
    private String clave;

    @ManyToOne
    @JoinColumn(name = "id_tipo_usuario")
    private SgvMultitabla sgvTipoUsuario;

}
