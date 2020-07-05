package com.devapp.sigsv.model.entity;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = AppConstantesSql.TABLE_SGV_COMPRA, schema = AppConstantesSql.SCHEMA_SIGSV)
public class SgvCompra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_compra")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = AppConstantesSql.SEQUENCE_ID_SQ_COMPRA)
    @SequenceGenerator(name = AppConstantesSql.SEQUENCE_ID_SQ_COMPRA, sequenceName = AppConstantesSql.SCHEMA_SIGSV+"."+AppConstantesSql.SEQUENCE_SQ_COMPRA, allocationSize = 1)
    private Long idCompra;
    
    @Column(name = "num_comprobante")
    private String numComprobante;
    
    @Column(name = "fecha")
    private Date fecha;
    
    @Column(name = "valor_compra")
    private Double valorCompra;
    
    @Column(name = "igv")
    private Double igv;
    
    @Column(name = "importe_total")
    private Double importeTotal;
    
    @Column(name = "ind_activo")
    private Boolean indActivo;

    @ManyToOne
    @JoinColumn(name = "fk_id_proveedor")
    private SgvProveedor sgvProveedor;
    
    /*@ManyToOne
    @JoinColumn(name = "fk_id_usuario")
    private SgvUsuario sgvUsuario;*/
    
    @ManyToOne
    @JoinColumn(name = "fk_id_tipo_comprobante")
    private SgvMultitabla sgvTipoComprobante;

}
