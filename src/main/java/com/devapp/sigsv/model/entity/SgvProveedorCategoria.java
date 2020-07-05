package com.devapp.sigsv.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.devapp.sigsv.util.AppConstantesSql;

import lombok.Data;

@Data
@Entity
@Table(name = AppConstantesSql.TABLE_SGV_PROVEEDOR_CATEGORIA, schema = AppConstantesSql.SCHEMA_SIGSV)
public class SgvProveedorCategoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_proveedor_categoria")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = AppConstantesSql.SEQUENCE_ID_SQ_PROVEEDOR_CATEGORIA)
    @SequenceGenerator(name = AppConstantesSql.SEQUENCE_ID_SQ_PROVEEDOR_CATEGORIA, sequenceName = AppConstantesSql.SCHEMA_SIGSV+"."+AppConstantesSql.SEQUENCE_SQ_PROVEEDOR_CATEGORIA, allocationSize = 1)
    private Long idProveedorCategoria;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "detalle")
    private String detalle;

    @Column(name = "ind_activo")
    private Boolean indActivo;

}
