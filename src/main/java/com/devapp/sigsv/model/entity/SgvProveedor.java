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
@Table(name = AppConstantesSql.TABLE_SGV_PROVEEDOR, schema = AppConstantesSql.SCHEMA_SIGSV)
public class SgvProveedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_proveedor")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = AppConstantesSql.SEQUENCE_ID_SQ_PROVEEDOR)
    @SequenceGenerator(name = AppConstantesSql.SEQUENCE_ID_SQ_PROVEEDOR, sequenceName = AppConstantesSql.SCHEMA_SIGSV+"."+AppConstantesSql.SEQUENCE_SQ_PROVEEDOR, allocationSize = 1)
    private Long idProveedor;

    @Column(name = "num_documento")
    private String numDocumento;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "contacto")
    private String contacto;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;

    @Column(name = "ind_activo")
    private Boolean indActivo;

    @ManyToOne
    @JoinColumn(name = "fk_id_tipo_documento")
    private SgvMultitabla sgvTipoDocumento;

    @ManyToOne
    @JoinColumn(name = "fk_id_proveedor_categoria")
    private SgvProveedorCategoria sgvProveedorcategoria;

}
